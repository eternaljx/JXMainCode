package com.smartseat.workgroup.main.model;

import com.smartseat.workgroup.common.utils.BaseVolume;

import java.io.Serializable;

/**
 * 设备对象
 * @author Denny
 *
 */
public class DeviceInfoCache implements Serializable{

	private String id;
	
	/** 0：离线，1：在线，2：正在连接 */
	private int OnLine = BaseVolume.NOT_ONLINE;
	
	private String pwd;
	
	private String time;
	
	/** 0：手动添加。1：二维码扫描添加 */
	private int style = BaseVolume.IS_MAN_ADD;
	/**座位位置 1,2,3,4 */
	private int place = 0;
	
	private boolean isSelect = false;
	
	private int connectCount = 0;
	
	private String ip;
	
	
	/**
	 * 
	 * @param id 设备账号
	 * @param name 设备名称
	 * @param pwd 设备密码
	 * @param time 添加时间
	 * @param style 添加方式
	 * @param place 座椅位置
	 */
	public DeviceInfoCache(String id, String pwd, String time, int style, int place) {
		this.id = id;
		this.pwd = pwd;
		this.time = time;
		this.style = style;
		this.place = place;
	}
	
	public int getConnectCount() {
		return connectCount;
	}

	public void setConnectCount(int connectCount) {
		this.connectCount = connectCount;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public int getOnLine() {
		return OnLine;
	}

	public void setOnLine(int onLine) {
		OnLine = onLine;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public DeviceInfoCache setIp(String _ip){
		this.ip=_ip;
		return this;
	}
	public String getIp(){
		return this.id;
	}
}
