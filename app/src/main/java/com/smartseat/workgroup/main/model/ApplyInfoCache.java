package com.smartseat.workgroup.main.model;

import java.io.Serializable;

/**
 * 应用的相关属性
 * @author Denny
 *
 */
public class ApplyInfoCache implements Serializable{

	private String name;
	private String switchOne;
	private String switchTwo;
	private String switchThree;
	private String switchFour;
	private String switchFive;
	private String useDeviceID;
	private int useID;
	private boolean isOpen = false;
	
	public ApplyInfoCache(String name, String one, String two, String three, String four, String five, String deviceID) {
		this.name = name;
		this.switchOne = one;
		this.switchTwo = two;
		this.switchThree = three;
		this.switchFour = four;
		this.switchFive = five;
		this.useDeviceID = deviceID;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getUseID() {
		return useID;
	}

	public void setUseID(int useID) {
		this.useID = useID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSwitchOne() {
		return switchOne;
	}

	public void setSwitchOne(String switchOne) {
		this.switchOne = switchOne;
	}

	public String getSwitchTwo() {
		return switchTwo;
	}

	public void setSwitchTwo(String switchTwo) {
		this.switchTwo = switchTwo;
	}

	public String getSwitchThree() {
		return switchThree;
	}

	public void setSwitchThree(String switchThree) {
		this.switchThree = switchThree;
	}

	public String getSwitchFour() {
		return switchFour;
	}

	public void setSwitchFour(String switchFour) {
		this.switchFour = switchFour;
	}

	public String getSwitchFive() {
		return switchFive;
	}

	public void setSwitchFive(String switchFive) {
		this.switchFive = switchFive;
	}
	
	public String getUseDeviceID() {
		return useDeviceID;
	}

	public String[]  getUseDeviceIDList() {
		
		return useDeviceID.split("_");
	}

	public void setUseDeviceID(String useDeviceID) {
		this.useDeviceID = useDeviceID;
	}
	
	
	
}
