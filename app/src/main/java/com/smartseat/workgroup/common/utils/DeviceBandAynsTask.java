package com.smartseat.workgroup.common.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.smartseat.workgroup.main.socket.SocketThreadManager;
import com.smartseat.workgroup.main.socket.Utils;
import com.vveye.T2u;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DeviceBandAynsTask extends AsyncTask<String, Integer, String> {

	
	private Handler handler ;
	private String deviceID;
	private String paswd;
	Result resultRes ;
	private Context con;
	public DeviceBandAynsTask(Handler handler , Context con){
		this.handler = handler;
		this.con = con;
		deviceID = "";
		paswd = "";
	}
	
	/**
	 * 异步类处理事件，并返回结果
	 */
	protected String doInBackground(String... params) {
		
		deviceID = params[0];
		paswd = params[1];
		
		if(doDeviceBand()){
			return "ok";
		}
		return null;
	}  
	
	/**
	 *  异步类正在处理时调用的方法，通知UI界面
	 */
	protected void onPreExecute() {  
		handler.sendEmptyMessage(BaseVolume.TASK_START);
	} 
	
    private boolean doDeviceBand() {
    	Log.e("连接设备", "启动远程连接，设备："+deviceID);
    	
		resultRes = P2PConnect();
		if (resultRes.getIsSucessed()) {
			String strIP = Utils.getIPAddress(true);
			try {
				InetAddress ip = InetAddress.getByName(strIP);
				// 获取ip
				BaseVolume.ROMENT_IP =ip.getHostAddress();
//				SocketThreadManager.sharedInstance(con).setServiceAdress(BaseVolume.ROMENT_IP, BaseVolume.ROMENT_PORT);
//				SocketThreadManager.sharedInstance(con).getTcpClient();
//				BusinessOperate.socketMap.put(deviceID, SocketThreadManager.sharedInstance(con));
				if (SocketThreadManager.sharedInstance(con).getTCPClient(deviceID) == null) {
					SocketThreadManager.sharedInstance(con).createSocket(deviceID, BaseVolume.ROMENT_IP, BaseVolume.ROMENT_PORT);
		    	}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
    
    /**  
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）  
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置    
     */  
    protected void onPostExecute(String result) { 
    	
    	if(result == null){
    		
    		Message msg = new Message();
    		msg.what = BaseVolume.TASK_ERROR;
    		Bundle bundle = new Bundle();
    		bundle.putString(BaseVolume.DEVICE_USER, deviceID);
    		msg.setData(bundle);
    		msg.obj = resultRes;
    		handler.sendMessage(msg);
    	}else{
    		Message msg = new Message();
    		msg.what = BaseVolume.TASK_OK;
    		Bundle bundle = new Bundle();
    		bundle.putString(BaseVolume.DEVICE_USER, deviceID);
    		bundle.putString(BaseVolume.DEVICE_PWD, paswd);
    		msg.setData(bundle);
    		handler.sendMessage(msg);
    	}
       
    }  
	

	private Result P2PConnect() {
		
		int intWaitTime = 0;
		int portstatus = 0;
		// -------------SDK锟斤拷始锟斤拷------------------------
		try {
			T2u.Init("nat.vveye.net", (char) 8000, "");
		} catch (Exception e) {
			return new Result(false, "");
		}

		// ---------搜索发现本地设备-------------------------
		byte[] result = new byte[1500];
		int num = T2u.Search(result);
		String tmp;
		if (num > 0) {
			tmp = new String(result);
		

		} 
		/* ***查询映射端口是否已经 查询映射端口是否已经 查询映射端口是否已经 查
		 * 询映射端口是否已经 查询映射端口是否已经 与远端设备 与远端设备 与远端设备 连通
		 */

		while (T2u.Status() == 0) {
			SystemClock.sleep(1000);
			intWaitTime += 1;

			if (intWaitTime > 10) {
				break;
			}
		}
		// -------------------建立 到本地端口映射--------------------
		if (T2u.Status() == 1) {
			int ret;
			// ---------------查询设备是否在线-----------------
			ret = T2u.Query(deviceID);
			if (ret > 0) {
				// ******************本机IP*********************
				// String strIP = Utils.getIPAddress(true);
				// setTitle("IP:" + strIP);
				// --------------创建本地映射端口-----------------
				BaseVolume.ROMENT_PORT = T2u.AddPortV3(deviceID, paswd, "127.0.0.1", (char) 8080,(char) 0);
				
				while(portstatus==0)
				{
					SystemClock.sleep(1000);
					
					portstatus=T2u.PortStatus((char)BaseVolume.ROMENT_PORT);
					System.out.println("portstatus="+portstatus);
					if(portstatus==1)
					{
//						printf("已与远端设备连通");
						return new Result(true, "");
					}else if(portstatus==0)
					{
//						printf("正在建立连接......");
					}else if(portstatus==-1)
					{
//						printf("未找到设备");
						T2u.Exit();
						return new Result(false, "未找到设备");
					
					}else if(portstatus==-5)
					{
//						printf("密码错误");
						T2u.Exit();
						return new Result(false, "密码错误");
					}
				}

			} else {
				return new Result(false, "");
			}
		} else {
			return new Result(false, "");
		}
		if (!isNumeric(String.valueOf(BaseVolume.ROMENT_PORT))) {
			return new Result(false, "");
		}
		// -------------------锟斤拷锟接成癸拷,锟斤拷锟截帮拷钮锟斤拷锟节活动状态--------------------

		 return new Result(true, "");
	}
	
	
	private boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
}


