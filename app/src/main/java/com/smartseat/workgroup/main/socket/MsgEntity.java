package com.smartseat.workgroup.main.socket;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;


/**
 *鎸囦护
 * @author Administrator
 *
 */
public class MsgEntity
{
	//鍙戦�鎸囦护
	private byte [] bytes;
	//鍙ユ焺
	private Handler mHandler;
	
	private Timer timer;
	
	private TCPClient tcp;
	public MsgEntity( byte [] bytes, Handler handler,TCPClient tcp)
	{	
		 this.bytes = bytes;
		 mHandler = handler;
		 timer = new Timer();
//		 timer.schedule(task, 1000*5);  
		 this.tcp = tcp;
	}
	
	
	
	public byte []  getBytes()
	{
		return this.bytes;
	}
	
	public Handler getHandler()
	{
		return mHandler;
	}


	/***
	 * 超时处理
	 */
	TimerTask task = new TimerTask() {
		public void run() {
			tcp.doTimeOut(MsgEntity.this);
		}
	};
}
