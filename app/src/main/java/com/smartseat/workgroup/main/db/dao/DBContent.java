package com.smartseat.workgroup.main.db.dao;

public class DBContent {

	/**
	 * 设备列表
	 * @author Administrator
	 *
	 */
	public static class DeviceInfo {
		
		public static final String TABLE_NAME = "DeviceInfo";
		
		public static final String MODE_NAME = "ModeInfo";
		
		public static final String APPLY_NAME = "ApplyInfo";
		
		public static class Columns {
			// ---------------------------------主机字段-----------------------------------
			/** 自动增长列 */
			public static final String id = "ID";
			/** 产品PID*/
			public static final String PID = "deviceID";
			/** 设备密码 */
			public static final String devicePwd = "devicePwd";
			/** 添加时间*/
			public static final String deviceTime = "deviceTime";
			/** 添加方式 */
			public static final String deviceStyle = "deviceStyle";
			/** 座椅位置 */
			public static final String devicePlace = "devicePlace";
			
			// ------------------------------模式字段---------------------------
			public static final String modeID = "modeID";
			/** 设备ID*/
			public static final String modeDeviceID = "modeDeviceID";
			/** 模式编号 */
			public static final String modeName = "modeName";
			public static final String modeOne= "modeOne";
			public static final String modeTwo= "modeTwo";
			public static final String modeThree= "modeThree";
			public static final String modeFour = "modeFour";
			public static final String modeFive = "modeFive";
			public static final String modeTime = "modeTime";
			
			// ------------------------------应用模式字段---------------------------
			public static final String useID = "useID";
			public static final String useName = "useName";
			public static final String switchOne = "switchOne";
			public static final String switchTwo = "switchTwo";
			public static final String switchThree = "switchThree";
			public static final String switchFour = "switchFour";
			public static final String switchFive = "switchFive";
			public static final String useDeviceID = "useDeviceID";
			
		}
		
		public static String getCreateSQL() {
			return "CREATE TABLE " + TABLE_NAME + "(" + //
					"'"+ Columns.id+"' INTEGER PRIMARY KEY AUTOINCREMENT ," +
	                "'"+ Columns.PID+"' TEXT NOT NULL ," +
	                "'"+ Columns.devicePwd+"' TEXT NOT NULL ," +
	                "'"+ Columns.deviceTime+"' TEXT NOT NULL ," +
	                "'"+ Columns.deviceStyle+"' INTEGER ," +
	                "'"+ Columns.devicePlace+"' INTEGER " +
	                ")";
		}
		
		public static String getCreateModeSQL() {
			return "CREATE TABLE " + MODE_NAME + "(" + 
					"'"+ Columns.modeID+"' INTEGER PRIMARY KEY AUTOINCREMENT ," +
	                "'"+ Columns.modeDeviceID+"' TEXT NOT NULL ," +
	                "'"+ Columns.modeName+"' INTEGER NOT NULL ," +
	                "'"+ Columns.modeOne+"' TEXT NOT NULL ," +
	                "'"+ Columns.modeTwo+"' TEXT NOT NULL ," +
	                "'"+ Columns.modeThree+"' TEXT NOT NULL ," +
	                "'"+ Columns.modeFour+"' TEXT NOT NULL ," +
	                "'"+ Columns.modeFive+"' TEXT NOT NULL ," +
	                "'"+ Columns.modeTime+"' TEXT NOT NULL " +
	                ")";
		}
		
		public static String getCreateUseSQL() {
			return "CREATE TABLE " + APPLY_NAME + "(" + 
					"'"+ Columns.useID+"' INTEGER PRIMARY KEY AUTOINCREMENT ," +
					"'"+ Columns.useName+"' TEXT NOT NULL ," +
	                "'"+ Columns.switchOne+"' TEXT NOT NULL ," +
	                "'"+ Columns.switchTwo+"' TEXT NOT NULL ," +
	                "'"+ Columns.switchThree+"' TEXT NOT NULL ," +
	                "'"+ Columns.switchFour+"' TEXT NOT NULL ," +
	                "'"+ Columns.switchFive+"' TEXT NOT NULL ," +
	                "'"+ Columns.useDeviceID+"' TEXT NOT NULL " +
	                ")";
		}
	}
}
