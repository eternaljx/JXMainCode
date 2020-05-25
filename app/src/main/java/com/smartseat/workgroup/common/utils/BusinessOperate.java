package com.smartseat.workgroup.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.smartseat.workgroup.main.model.DeviceInfoCache;
import com.smartseat.workgroup.main.socket.SocketThreadManager;

import java.util.HashMap;
import java.util.Map;

public class BusinessOperate {
	
	public static Map<String, SocketThreadManager> socketMap = new HashMap<String, SocketThreadManager>();
	
	public void sendCommmand(final Context context, final DeviceInfoCache device, final String cmd, final Handler handler) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (device == null) {
					return;
				}
				// 手动添加的设备
				if (device.getStyle() == BaseVolume.IS_MAN_ADD) {
					byte[] sendBytes = NetworkUtils.hexStringToBytes(cmd);
					// 集合中存在该连接
					if (SocketThreadManager.sharedInstance(context).socketMap.get(device.getId()) != null) {
						SocketThreadManager.sharedInstance(context).socketMap.get(device.getId()).sendMsg(sendBytes, handler);	
					}
					else 
						handler.sendEmptyMessage(BaseVolume.TASK_ERROR);
//					socketMap.get(device.getId()).sendMsg(sendBytes, handler);	
				}
				else {
					// 通过二维码扫描得到的设备，并且未超时
					if (!NetworkUtils.isTimeOut(device.getTime())) {
						byte[] sendBytes = NetworkUtils.hexStringToBytes(cmd);
						// 集合中存在该连接
						if (SocketThreadManager.sharedInstance(context).socketMap.get(device.getId()) != null) {
							SocketThreadManager.sharedInstance(context).socketMap.get(device.getId()).sendMsg(sendBytes, handler);	
						}
						else 
							handler.sendEmptyMessage(BaseVolume.TASK_ERROR);
//						socketMap.get(device.getId()).sendMsg(sendBytes, handler);
					}
					// 已超时
					else {
						SocketThreadManager.sharedInstance(context).deleteTCPClient(device.getId());
						context.sendBroadcast(new Intent(BaseVolume.DEVICE_TIMEOUT).putExtra("deviceID", device.getId()));
					}
				}
			}
		}).start();
	}

}
