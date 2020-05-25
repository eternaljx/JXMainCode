package com.smartseat.workgroup.main.socket;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


public class SocketThreadManager
{

enum MobileStatuesEnum {
    MobileStatues_Unconected ,//娌℃湁鑾峰彇鎵嬫満ID
    MobileStatues_CanSendData,//鍙互鍙戦�鏁版嵁
    MobileStatues_TrySendData,//閲嶅彂
    MobileStatues_WaitingRecvSelfData,//绛夊埌鎸囦护鎿嶄綔缁撴灉鍙嶉
    MobileStatues_WaitingRecvOtherData,
    MobileStatues_WaitingReloadDevice_Other,//鍏朵粬鎵嬫満锛屾鍦ㄦ煡鎵剧绾胯澶�
    MobileStatues_WaitingReloadDevice_Self,//褰撳墠鎵嬫満鐢ㄦ埛锛屾鍦ㄦ煡鎵剧绾胯澶�
};
 
	protected static final String TAG = "鎺ユ敹鏁版嵁";
	private static SocketThreadManager s_SocketManager = null;
	final static long READ_GAP = 100;
	static Thread sendThread = null;

	protected boolean isCanSendCommand = true;
	private static Context context ;
	private  String HostIp = "";
	private  int HostListenningPort = -1;
	private String deviceID = "";
	
	public static SocketThreadManager sharedInstance( Context  context)
	{
		SocketThreadManager.context =context;
		if (s_SocketManager == null)
		{
			s_SocketManager = new SocketThreadManager();
		}
		return s_SocketManager;
	}
	
	/**
	 * 创建Socket
	 * @param context
	 */
	public Map<String, TCPClient> socketMap = new HashMap<String, TCPClient>();
	public void createSocket(String id,String ip,int port) {
		Log.e("创建连接createSocket_TCPClient",id+"|"+ip+"|"+port);
		this.HostIp = ip;
		this.HostListenningPort = port;
		this.deviceID = id;
		TCPClient tcpClient = new TCPClient(deviceID,HostIp, HostListenningPort,context);
		
		SocketInputThread mInputThread = new SocketInputThread(context,this,tcpClient);
		mInputThread.start();
		mInputThread.setStart(true);
		socketMap.put(deviceID, tcpClient);
		
	}
	
	public TCPClient getTCPClient(String deviceID) {
		return socketMap.get(deviceID);
	}
	
	public void deleteTCPClient(String deviceID) {
		if (socketMap.get(deviceID) != null) {
			socketMap.get(deviceID).closeTCPSocket();
		}
		socketMap.remove(deviceID);
	}
	
	public void clearTCPClientList() {
		socketMap.clear();
	}

	public boolean isConnect(String deviceID) {
		if (socketMap.get(deviceID) != null) {
			return socketMap.get(deviceID).isConnect();
		}
		else 
			return false;
	}
	
	public void reConnect(final String deviceID,final Handler handler) {
		new Thread(){
			public void run() {
				TCPClient tcpClient = socketMap.get(deviceID).reConnect(handler);
				socketMap.put(deviceID, tcpClient);
			};
			
		}.start();
		
	}
	
}
