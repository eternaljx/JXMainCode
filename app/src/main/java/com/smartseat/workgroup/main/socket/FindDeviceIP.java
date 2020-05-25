package com.smartseat.workgroup.main.socket;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.smartseat.workgroup.common.utils.BaseVolume;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP�?��网设备IP
 *
 * @author Administrator
 */
public class FindDeviceIP {

    private Context con;
    private final int DEFAULT_PORT = 988;
    private DatagramSocket udpSocket;

    public void sendUdpCommand(final Context context, final Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramPacket dataPacket = null;
                String udpIP = getUdpServiceIP(context);
                if (udpIP == null)
                    return;
                try {

                    if (udpSocket == null)
                        udpSocket = new DatagramSocket();

                    InetAddress broadcastAddr;
                    broadcastAddr = InetAddress.getByName(udpIP);

                    String order = "HLK";
                    byte[] data = order.getBytes("utf8");
                    dataPacket = new DatagramPacket(data, data.length, broadcastAddr,
                            DEFAULT_PORT);
                    udpSocket.send(dataPacket);

                    byte[] dataReceive = new byte[256];
                    DatagramPacket packetReceive = new DatagramPacket(dataReceive,
                            dataReceive.length);
                    udpSocket.setSoTimeout(1000 * 2);
                    udpSocket.receive(packetReceive);
                    String udpresult = new String(packetReceive.getData(),
                            packetReceive.getOffset(), packetReceive.getLength());
                    String ip = packetReceive.getAddress().getHostAddress();
                    String user = udpresult.substring(udpresult.length() - 11, udpresult.length() - 1);
                    Log.e("UDP检测", "UDP返回数据：" + udpresult + "+" + ip);
                    Message msg = new Message();
                    msg.what = BaseVolume.IS_DEVICE_IP;
                    Bundle bundle = new Bundle();
                    bundle.putString("ip", ip);
                    bundle.putString("user", user);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getUdpServiceIP(Context context) {
        String udpServiceIP = "";
        String ip = getIP(context);
        if (ip == null)
            return ip;
        if (ip != null && ip.startsWith("192.")) {
            String[] strarray = ip.split("\\.");

            for (int i = 0; i < strarray.length - 1; i++) {
                udpServiceIP += strarray[i] + ".";
            }
            udpServiceIP += "255";
        }
        return udpServiceIP;
    }

    private String getIP(Context context) {
        WifiManager wifiService = (WifiManager) context
                .getSystemService(context.WIFI_SERVICE);
        WifiInfo wifiinfo = wifiService.getConnectionInfo();
        int wifiAd = wifiinfo.getIpAddress();
        if (wifiAd == 0)
            return null;
        return intToIp(wifiAd);
    }

    private String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }
}
