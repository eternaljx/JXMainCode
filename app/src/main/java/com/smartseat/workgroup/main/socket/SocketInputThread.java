package com.smartseat.workgroup.main.socket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.common.utils.BusinessOperate;
import com.smartseat.workgroup.common.utils.NetworkUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author way
 */
public class SocketInputThread extends Thread {
    public final static int LEN_COMMAN_REQUEST_TOTAL = 40;
    private BusinessOperate mBusinessOperate = new BusinessOperate();
    private boolean isStart = true;

    private SocketThreadManager parent = null;
    private Context context;
    private TCPClient tcp = null;

    public SocketInputThread(Context context, SocketThreadManager parent, TCPClient tcp) {
        this.tcp = tcp;
        this.parent = parent;
        this.context = context;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    @Override
    public void run() {
        while (isStart) {
            if (NetManager.instance(context).isNetworkConnected()) {
//				TCPClient tcp = parent.getTcpClient();
                if (tcp == null)
                    continue;
                if (!tcp.isConnect()) {
                    try {
                        sleep(Const.SOCKET_SLEEP_SECOND * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                readSocket(tcp);
                //	getResponse();
            }
        }
    }

    private String processData = "";
    private String newData = "";
    private boolean isRight = false;

    public void readSocket(TCPClient tcp) {

        Selector selector = tcp.getSelector();
        if (selector == null) {
            return;
        }
        try {
            while (selector.select() > 0) {
                for (SelectionKey sk : selector.selectedKeys()) {
                    if (sk.isReadable()) {
                        SocketChannel sc = (SocketChannel) sk.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(LEN_COMMAN_REQUEST_TOTAL / 2);
                        try {
                            sc.read(buffer);
                        } catch (SocketTimeoutException e) {
                            e.printStackTrace();
                        }

                        buffer.flip();
                        try {
                            byte[] data = buffer.array();
                            String temper = NetworkUtils.bytesToHexString(data);
                            MsgEntity msg = tcp.getDoneNetDataDict();
                            // socket断开连接
                            if (temper.equals("0000000000000000000000000000000000000000")) {
//			    	    		msg.getHandler().sendEmptyMessage(BaseVolume.TIME_OUT);
                                context.sendBroadcast(new Intent(BaseVolume.DEVICE_NO_LINE).putExtra(BaseVolume.DEVICE_ID, tcp.getDeviceID()));
                                SocketThreadManager.sharedInstance(context).deleteTCPClient(tcp.getDeviceID());
                                tcp.closeTCPSocket();
                                return;
                            } else {
                                Log.e("socke接收", "设备：" + tcp.getDeviceID() + "，接收：" + temper);
                                if (temper.substring(0, 2).equals("cc") && temper.substring(temper.length() - 2).equals("dd") && temper.length() == 40) {
                                    Log.e("socke接收", "设备：" + tcp.getDeviceID() + "，接收：" + temper);
                                    processData = processData + temper;
                                    context.sendBroadcast(new Intent(BaseVolume.CLEAR_TIMER).putExtra(BaseVolume.DEVICE_ID, tcp.getDeviceID()));
                                }
                                context.sendBroadcast(new Intent(BaseVolume.CLEAR_COUNT).putExtra(BaseVolume.DEVICE_ID, tcp.getDeviceID()));
                                for (int i = 0; i < processData.length() / 2; i++) {
                                    // 找到了包头CC，清空之前的缓存数据
                                    if (processData.substring(i * 2, (i + 1) * 2).equals("cc") && newData.equals("")) {
                                        newData = processData.substring(i * 2, (i + 1) * 2);
                                        isRight = true;
                                    }
                                    // 找到了包尾DD，且长度为40，即为有效一条数据
                                    else if (processData.substring(i * 2, (i + 1) * 2).equals("dd") && newData.length() == 38) {
                                        newData = newData + processData.substring(i * 2, (i + 1) * 2);
                                        Log.e("socket通信：", "有效数据：" + newData);
                                        if (msg != null) {
                                            Message mesg = new Message();
                                            mesg.what = BaseVolume.MESSAGE;
                                            mesg.obj = msg;
                                            Bundle bundle = new Bundle();
//					    				bundle.putByteArray(BaseVolume.RETURN_MESSAGE, data);
                                            bundle.putString(BaseVolume.RETURN_MESSAGE, newData);
                                            mesg.setData(bundle);
                                            msg.getHandler().sendMessage(mesg);

                                            processData = processData.substring((i + 1) * 2);
                                            newData = "";
                                            isRight = false;
                                        }
                                    }
                                    // 未找到包头包尾，即为包体，递加
                                    else if (isRight) {
                                        newData = newData + processData.substring(i * 2, (i + 1) * 2);
                                    }
                                }
                                newData = "";
                            }
                        } catch (Exception e) {
                            Log.e("socket通信：", "通信返回异常！");
                            e.printStackTrace();
                        }
                        buffer.clear();
                        buffer = null;

                        try {

                            sk.interestOps(SelectionKey.OP_READ);
                            selector.selectedKeys().remove(sk);

                        } catch (CancelledKeyException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            // selector.close();
            // TCPClient.instance().repareRead();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ClosedSelectorException e2) {
        }
    }


}
