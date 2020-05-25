package com.smartseat.workgroup.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NetworkUtils {
	// 十锟斤拷锟斤拷转锟斤拷为十锟斤拷锟斤拷疲锟斤拷锟斤拷为C8锟斤拷
    //Integer.toHexString(200);
	// 十锟斤拷锟斤拷锟阶拷锟轿拷锟斤拷疲锟斤拷锟斤拷140锟斤拷
	//Integer.parseInt("8C",16);

	  /*
     * Convert byte[] to hex string.锟斤拷锟斤拷锟斤拷锟角匡拷锟皆斤拷byte转锟斤拷锟斤拷int锟斤拷然锟斤拷锟斤拷锟斤拷Integer.toHexString(int)锟斤拷转锟斤拷锟斤拷16锟斤拷锟斤拷锟街凤拷  
     * @param src byte[] data  
     * @return hex string  
     */     
    public static String bytesToHexString(byte[] src){  
        StringBuilder stringBuilder = new StringBuilder("");  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    }  
    /** 
     * Convert hex string to byte[] 
     * @param hexString the hex string 
     * @return byte[] 
     */  
    public static byte[] hexStringToBytes(String hexString) {  
        if (hexString == null || hexString.equals("")) {  
            return null;  
        }  
        hexString = hexString.toUpperCase();  
        int length = hexString.length() / 2;  
        char[] hexChars = hexString.toCharArray();  
        byte[] d = new byte[length];  
        for (int i = 0; i < length; i++) {  
            int pos = i * 2;  
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
        }  
        return d;  
    }  
    /** 
     * Convert char to byte 
     * @param c char 
     * @return byte 
     */  
    public static byte charToByte(char c) {  
        return (byte) "0123456789ABCDEF".indexOf(c);  
    } 
    
    /**
     * @param Cmd
     * @return  锟斤拷取锟斤拷锟斤拷校锟斤拷
     */
    public static byte GetXOR(byte[] Cmd)  
    {  
        byte check = (byte)(Cmd[0] ^ Cmd[1]);  
       for (int i = 2; i < Cmd.length; i++)  
       {  
            check = (byte)(check ^ Cmd[i]);  
        }  
        return check;  
    }  
    
    private static String toHexUtil(int n){
        String rt="";
        switch(n){
        case 10:rt+="A";break;
        case 11:rt+="B";break;
        case 12:rt+="C";break;
        case 13:rt+="D";break;
        case 14:rt+="E";break;
        case 15:rt+="F";break;
        default:
            rt+=n;
        }
        return rt;
    }
    
    
    public static String toHex(int n){
        StringBuilder sb=new StringBuilder();
        if(n/16==0){
            return toHexUtil(n);
        }else{
            String t=toHex(n/16);
            int nn=n%16;
            sb.append(t).append(toHexUtil(nn));
        }
        return sb.toString();
    }
    
    /**
     * 瀛楃涓茶浆ASCII鐮?
     * @param str
     * @return
     */
    public static String parseAscii(String str){
        StringBuilder sb=new StringBuilder();
        byte[] bs=str.getBytes();
        for(int i=0;i<bs.length;i++)
            sb.append(toHex(bs[i]));
        return sb.toString();
    }
    
    public static boolean isTimeOut(String deviceTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date firstDate;
		try {
			firstDate = df.parse(deviceTime);
			Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
			long curti = curDate.getTime();
			long firstti = firstDate.getTime();
			long diff = curti - firstti;
//			Log.e("时间差", "最近一次的时间差:"+diff);
			// 十分钟以后
			if (diff > 1000 * 60 * 10) {
				Log.e("时间差", "超时！");
				return true;
			}
			else {
//				Log.e("时间差", "未超时！");
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
    
    /**
     * 获取当前时间差
     * @param deviceTime
     * @return
     */
    public static String getTimePoor(String deviceTime) {
    	String sTime = "";
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date firstDate;
    	
		try {
			firstDate = df.parse(deviceTime);
			Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
			long curti = curDate.getTime();
			long firstti = firstDate.getTime();
			long diff = curti - firstti;
//			Log.e("时间差", "最近一次的时间差:"+diff);
			// 十分钟以后
			if (diff >= 1000 * 60 * 10) {
				Log.e("时间差", "超时！");
				return "";
			}
			else {
//				Log.e("时间差", "未超时！");
				long sheng = 1000 * 60 * 10 - diff;
				String minute = (sheng/60000) +"";
				String second = ((sheng%60000)/1000)+"";
				if (minute.length() < 2) 
					minute = "0"+minute;
				if (second.length() < 2) 
					second = "0"+second;
				return minute+":"+second;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return sTime;
    }
    
    /**
	 * 获取当前时间
	 * @return
	 * 
	 */
	public static String getNowTime() {
		long time = -1;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
		String sTime = formatter.format(curDate);     
		
		return sTime;
	}
	
	/**
	 * 获取版本号
	 * @return 当前应用的版本号
	 */
	public static String getVersion(Context con) {
	    try {
	        PackageManager manager = con.getPackageManager();
	        PackageInfo info = manager.getPackageInfo(con.getPackageName(), 0);
	        String version = info.versionName;
	        return  version;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "0.0";
	    }
	}
    
}
