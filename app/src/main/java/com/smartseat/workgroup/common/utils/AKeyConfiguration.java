package com.smartseat.workgroup.common.utils;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 */
public class AKeyConfiguration {

	String strip = "";
	String str = "";
	String ssidv = "";
	String strparam= "";
	String strwps = "";
	String strpdw = "";
	String fliename = "";
	private static DhcpInfo dhcpInfo;
	WifiManager wifimsg;
	int ip = 0;
	public AKeyConfiguration(String ssidv, String strwps, String strpdw, WifiManager wifimsg) {
		this.ssidv = ssidv;
		this.strwps = strwps;
		this.strpdw = strpdw;
		this.wifimsg = wifimsg;
	}
	
	public void configuration() {
		dhcpInfo = wifimsg.getDhcpInfo();
		 
		ip = dhcpInfo.gateway;		
		if(ip != 0){
			strip = ((ip & 0xff) + "." + (ip >> 8 & 0xff) + "." 
				+ (ip >> 16 & 0xff) + "." + (ip >> 248 & 0xff));
		}
		//Toast.makeText(context, "浣犲綋鍓嶄富IP:"+strip, Toast.LENGTH_SHORT).show();
		strparam ="netmode=2&dhcpd=0&wifi_conf="+ssidv+","+strwps+","+strpdw+"&dhcpc=1&net_commit=1&reconn=1";
		str = "POST /goform/ser2netconfigAT HTTP/1.1\r\n";
		str +="Host: "+strip+"\r\n";
		str +="Connection: keep-alive\r\n";
		str +="Authorization: Basic YWRtaW46YWRtaW4=\r\n";
		str +="Content-Length: "+strparam.length()+"\r\n\r\n";
		str +=strparam+"\r\n\r\n";
		
		fliename = CreateFile(str);
		//status = sendPost(strip,fliename);
		Thread thread = new SendPost(strip,fliename);
		thread.start();
	}
	
	public String CreateFile(String str){
		//String result = "";
		String path ="";
		path = Environment.getExternalStorageDirectory().getPath()+"/sendtxt.txt";
		File file = new File(path);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(str);
			FileOutputStream os = new FileOutputStream(file);
			DataOutputStream out = new DataOutputStream(os);
			out.writeShort(2);
			out.writeUTF("");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public class SendPost extends Thread{
		private String url = "";
		private String Tpath = "";
		public SendPost(String str,String filename){
			this.url = str;
			this.Tpath = filename;
		}
		
		public void run() {
			Socket socket;
			try {
				socket = new Socket(url,80);
				InputStream in = new FileInputStream(Tpath);
				OutputStream out = socket.getOutputStream();
				byte buffer [] = new byte[4*024];
				int temp = 0;
				while((temp = in.read(buffer))!= -1){
					out.write(buffer,0,temp);
				}
				out.flush();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
