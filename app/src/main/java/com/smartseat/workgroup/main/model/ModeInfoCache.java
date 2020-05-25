package com.smartseat.workgroup.main.model;

public class ModeInfoCache {

	private int modeID = -1;
	private String deviceID = "";
	private String OneTime = "-1";
	private String TwoTime = "-1";
	private String ThreeTime = "-1";
	private String FourTime = "-1";
	private String FiveTime = "-1";
	private String modeName = "";
	private String addTime = "";
	
	public ModeInfoCache(int modeID, String deviceID, String name, String oneTime, String twoTime, String threeTime, String fourTime, String fiveTime, String addTime) {
		this.modeID = modeID;
		this.deviceID = deviceID;
		this.OneTime = oneTime;
		this.TwoTime = twoTime;
		this.ThreeTime = threeTime;
		this.FourTime = fourTime;
		this.FiveTime = fiveTime;
		this.modeName = name;
		this.addTime = addTime;
		
	}
	
	public int getModeID() {
		return modeID;
	}

	public void setModeID(int modeID) {
		this.modeID = modeID;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getAddTime() {
		return addTime;
	}



	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}



	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getOneTime() {
		return OneTime;
	}

	public void setOneTime(String oneTime) {
		OneTime = oneTime;
	}

	public String getTwoTime() {
		return TwoTime;
	}

	public void setTwoTime(String twoTime) {
		TwoTime = twoTime;
	}

	public String getThreeTime() {
		return ThreeTime;
	}

	public void setThreeTime(String threeTime) {
		ThreeTime = threeTime;
	}

	public String getFourTime() {
		return FourTime;
	}

	public void setFourTime(String fourTime) {
		FourTime = fourTime;
	}

	public String getFiveTime() {
		return FiveTime;
	}

	public void setFiveTime(String fiveTime) {
		FiveTime = fiveTime;
	}
	
}
