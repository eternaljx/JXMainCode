package com.smartseat.workgroup.main.socket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.common.utils.NetworkUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * NIO
 */
public class TCPClient {
    private Selector selector;

    SocketChannel socketChannel;

    private String deviceID;

    private String hostIp;

    private int hostListenningPort;

    private static TCPClient s_Tcp = null;
    public boolean isInitialized = false;
    Context con;

    public static synchronized TCPClient instance(String HostIp, int HostListenningPort) {
        return s_Tcp;
    }

    public void clearTCPClient() {
        s_Tcp = null;
    }

    private SocketThreadManager.MobileStatuesEnum mobileStatues;

    /**
     * @param HostIp
     * @param HostListenningPort
     * @throws IOException
     */
    public TCPClient(String deviceID, String HostIp, int HostListenningPort, Context con) {

        this.deviceID = deviceID;
        this.hostIp = HostIp;
        this.hostListenningPort = HostListenningPort;
        sendMsgList = Collections.synchronizedList(new ArrayList<MsgEntity>());
        mobileStatues = SocketThreadManager.MobileStatuesEnum.MobileStatues_Unconected;
        try {
            socketStartConect();
            this.isInitialized = true;
        } catch (Exception e) {
            this.isInitialized = false;
            e.printStackTrace();
        }
    }

    SyncThread mSyncThread;

    private void socketStartConect() {
        mSyncThread = new SyncThread();
        mSyncThread.start();
    }

    public class SyncThread extends Thread {
        @Override
        public void run() {
            synchronized (this) {
                try {
                    initialize();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 锟斤拷始锟斤拷
     *
     * @throws IOException
     */
    public TCPClient initialize() throws Exception {
        Log.e("socket连接", "socket开始创建！device：" + deviceID);
        boolean done = false;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress(hostIp, hostListenningPort), Const.SOCKET_READ_TIMOUT);
            if (socketChannel != null) {
                socketChannel.socket().setTcpNoDelay(false);
                socketChannel.socket().setKeepAlive(true);
                socketChannel.socket().setSoTimeout(Const.SOCKET_READ_TIMOUT);
                socketChannel.configureBlocking(false);
                selector = Selector.open();
                if (selector != null) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    done = true;
                }
            }
            Log.e("socket连接", "socket创建成功！device：" + deviceID);
            if (handler != null) {
            }
            return this;
        } catch (Exception e) {
            Log.e("socket连接", "socket建立失败！e：" + e.toString());
            if (handler != null) {
                handler.sendEmptyMessage(BaseVolume.TASK_ERROR);
            }
            throw e;

        } finally {
            if (!done && selector != null) {
                selector.close();
            }
            if (!done) {
                socketChannel.close();
            }
        }
    }

    static void blockUntil(SelectionKey key, long timeout) throws IOException {

        int nkeys = 0;
        if (timeout > 0) {
            nkeys = key.selector().select(timeout);

        } else if (timeout == 0) {
            nkeys = key.selector().selectNow();
        }

        if (nkeys == 0) {
            throw new SocketTimeoutException();
        }
    }

    public String getSubString(String requestStr, int start, int end) {
        String temp = requestStr;
        return temp.substring(start, end);
    }


    public String getRandom() {
        String random = null;
        Random rand = new Random(System.currentTimeMillis());
        if (((int) (rand.nextInt(99)) < 10)) {
            random = String.valueOf((int) (rand.nextInt(99))) + "1";
        } else {
            random = String.valueOf((int) (rand.nextInt(99)));
        }
        if (random.length() < 2) {
            random = random + "1";
        }
        return random;
    }

    public String getXor(String request) {
        byte[] requestByte = NetworkUtils.hexStringToBytes(request);
        byte temp = NetworkUtils.GetXOR(NetworkUtils.hexStringToBytes(request));
        byte[] temp2 = new byte[requestByte.length + 1];
        for (int i = 0; i < requestByte.length; i++) {
            temp2[i] = requestByte[i];
        }
        temp2[requestByte.length] = temp;
        return NetworkUtils.bytesToHexString(temp2);

    }

    public void sendMsg(byte[] buffer, Handler handler) {
        byte[] bytesSend = buffer;
        MsgEntity msg = new MsgEntity(bytesSend, handler, this);
        String requestStr = NetworkUtils.bytesToHexString(bytesSend);
        Log.e("socket通信：", "添加：" + requestStr + "  ,消息队列数：" + sendMsgList.size());
        sendMsgList.add(msg);
        StartSendMsg();

    }

    private MsgEntity doneNetDataDict = null;
    private List<MsgEntity> sendMsgList;

    private void StartSendMsg() {
        while (sendMsgList.size() > 0) {
            try {
                doneNetDataDict = sendMsgList.get(0);
                String requestStr = NetworkUtils.bytesToHexString(doneNetDataDict.getBytes());
                sendMsg(doneNetDataDict.getBytes());
                sendMsgList.remove(0);
            } catch (Exception e) {
                if (e == null)
                    return;
                e.printStackTrace();
                Log.e("发送命令", "发送异常，e：" + e.toString());
                if (con != null) {
                    con.sendBroadcast(new Intent(BaseVolume.DEVICE_NO_LINE).putExtra(BaseVolume.DEVICE_ID, getDeviceID()));
                    con.sendBroadcast(new Intent(BaseVolume.CONNECT_DEVICE_ERROR).putExtra(BaseVolume.DEVICE_USER, getDeviceID()));
                }
                closeTCPSocket();
                Message msg = new Message();

                msg.what = BaseVolume.TIME_OUT;
                Bundle bundle = new Bundle();
                bundle.putString(BaseVolume.DEVICE_USER, deviceID);
                msg.setData(bundle);
                doneNetDataDict.getHandler().sendMessage(msg);

                doneNetDataDict = null;
                clearMsgList();
            }
        }
    }

    public MsgEntity getDoneNetDataDict() {
        return doneNetDataDict;
    }

    public void setDoneNetDataDict(MsgEntity doneNetDataDict) {
        this.doneNetDataDict = doneNetDataDict;
    }

    public void doTimeOut(MsgEntity msgEntity) {
        if (sendMsgList.contains(msgEntity)) {
            sendMsgList.remove(msgEntity);
        } else if (doneNetDataDict == msgEntity) {
            doneNetDataDict.getHandler().sendEmptyMessage(BaseVolume.TIME_OUT);
            doneNetDataDict = null;
        }

    }

    public void clearMsgList() {
        sendMsgList.clear();
        mobileStatues = SocketThreadManager.MobileStatuesEnum.MobileStatues_CanSendData;
    }


    /**
     * @param bytes
     * @throws IOException
     */
    public void sendMsg(byte[] bytes) throws Exception {
        ByteBuffer writeBuffer = ByteBuffer.wrap(bytes);
        if (socketChannel == null) {
            throw new SocketConnectException();
        }
        socketChannel.write(writeBuffer);
    }

    /**
     * @return
     */
    public synchronized Selector getSelector() {
        return this.selector;
    }

    /**
     * @return
     */
    public boolean isConnect() {
        boolean isConnect = false;
        if (this.isInitialized) {
            if (socketChannel != null)
                isConnect = this.socketChannel.isConnected();
        }
        return isConnect;
    }

    /**
     * 锟截憋拷socket 锟斤拷锟斤拷锟斤拷锟斤拷
     *
     * @return
     */
    private Handler handler = null;

    public TCPClient reConnect(Handler handler) {
        Log.e("连接异常", "开始重连");
        this.handler = handler;
        closeTCPSocket();

        try {
            isInitialized = true;
            return initialize();
        } catch (IOException e) {
            isInitialized = false;
            e.printStackTrace();
        } catch (Exception e) {
            isInitialized = false;
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟角凤拷乇眨锟酵拷锟斤拷锟斤拷锟揭伙拷锟絪ocket锟斤拷息
     *
     * @return
     */
    public boolean canConnectToServer() {
        try {
            if (socketChannel != null) {
                socketChannel.socket().sendUrgentData(0xff);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void closeTCPSocket() {
        try {
            if (socketChannel != null) {
                socketChannel.close();
            }
        } catch (IOException e) {
        }
        try {
            if (selector != null) {
                selector.close();
            }
        } catch (IOException e) {
        }
    }

    /**
     * 每锟轿讹拷锟斤拷锟斤拷锟捷猴拷锟斤拷要锟斤拷锟斤拷注锟斤拷selector锟斤拷锟斤拷取锟斤拷锟斤拷
     */
    public synchronized void repareRead() {
        if (socketChannel != null) {
            try {
                selector = Selector.open();
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDeviceID() {
        return this.deviceID;
    }

}
