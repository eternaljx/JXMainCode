package com.smartseat.workgroup.common.utils;

public class BaseVolume {

	public static boolean IS_REGISTER=false;
	public static final int DISSMISS = 333;
	public static final String DB_NAME = "device.db";
	public  static final int TASK_START = 0;
	public  static final int TASK_OK = 1;
	public  static final int TASK_ERROR = 2;
	public static final String DEVICE_USER = "DEVICE_USER";
	public static final String DEVICE_PWD = "DEVICE_PWD";
	public static final String DEVICE_NAME = "DEVICE_NAME";
	public static final String DEVICE_FIND= "DEVICE_FIND";
	public static final String FINISH_SELF = "FINISH_SELF";
	public static final String ADD_DEVICE = "ADD_DEVICE";
	public static final String ADD_APPLY = "ADD_APPLY";
	public static final String UPDATE_DEVICE = "UPDATE_DEVICE";
	public static final String DEVICE_ONLINE = "DEVICE_ONLINE";
	public static final String DEVICE_NO_LINE = "DEVICE_NO_LINE";
	public static final String DEVICE_UPDATE_NAME = "DEVICE_UPDATE_NAME";
	public static final String DEVICE_DELETE = "DEVICE_DELETE";
	public final static int SCANNIN_GREQUEST_CODE = 1;
	public final static int REG_GREQUEST_CODE = 1;
	public final static String DEVICE_TIMEOUT = "DEVICE_TIMEOUT";
	public final static String INITDATE = "INITDATE";
	public final static String SHARE_TIME = "SHARE_TIME";
	public static final int TIME_OUT = 99;
	public static final int MESSAGE = 12;
	public static final String DEVICE_ID = "DEVICE_ID";
	public static final String CLEAR_COUNT = "CLEAR_COUNT";
//	public static final int PAUSE = 3;
//	public static final int LOOP = 4;
	/** app是否处于外网 */
	public static boolean APP_IS_WAN = true;
	public static final String RETURN_MESSAGE = "RETURN_MESSAGE";
	public static String ROMENT_IP = "";
	public static int ROMENT_PORT = -1;
	public static final int DEFAULT_PORT = 988;
	public static final int LAN_PORT = 8080;
	public static final int IS_DEVICE_IP = 111;
	public static final String SCAN_DEVICE = "SCAN_DEVICE";
	public static final String CONNECT_DEVICE = "CONNECT_DEVICE";
	public static final String CONNECT_DEVICE_ING = "CONNECT_DEVICE_ING";
	public static final String CONNECT_DEVICE_OK = "CONNECT_DEVICE_OK";
	public static final String CONNECT_DEVICE_ERROR = "CONNECT_DEVICE_ERROR";
	/** 防盗功能 */
	public static final String CONTROL_VTD = "aa070000000000000000000000000000000007bb";
	/** 快速进入 */
	public static final String CONTROL_INTO = "aa080000000000000000000000000000000008bb";
	/** 查询时间 */
	//public static final String CONTROL_QUERY_TIME= "aa0a000000000000000000000000000000000abb";
	
	/** 查询时间 */
	public static final String CONTROL_QUERY_TIME= "aaa1000000000000000000000000000000000abb";
	
	
	public static final String CONTROL_CLOSE_ALL= "aa0c000000000000000000000000000000000cbb";
	
	/** 手动添加 */
	public static final int IS_MAN_ADD = 0;
	/** 二维码扫描添加 */
	public static final int IS_SHARE_ADD = 1;
	
	/** 未添加 */
	public static final int NOT_ADD = -1;
	/** 离线 */
	public static final int NOT_ONLINE = 0;
	/** 在线 */
	public static final int IS_ONLINE = 1;
	/** 正在连接 */
	public static final int IS_CONNECTING = 2;
	/** 可以分享 */
	public static final int OPER_TRUE = 3;
	/** 权限不够 */
	public static final int OPER_FALSE = 4;
	/** 未添加，不能分享 */
	public static final int OPER_NOT_ADD = 5;
	
	/** 位置1 */
	public static final int PLACE_ONE = 1;
	/** 位置2 */
	public static final int PLACE_TWO = 2;
	/** 位置3 */
	public static final int PLACE_THREE = 3;
	/** 位置4 */
	public static final int PLACE_FOUR = 4;
	/** 风向 氛围灯等5个设备*/
	public static final int PLACE_EIGHT =8;
	/** 前后向前移动 */
	public static final int PLACE_SEVEN =7;
	//主界面2
	/** 主驾驶座椅旋转和按摩 */
	public static final int PLACE_FIVE=5;
	/** 副驾驶座椅旋转和按摩 */
	public static final int PLACE_SIX=6;
	//新增设备
	//public static
	
	/** 登录次数清空 */
	public static final int CLEAR_LOGIN = 0;
	/** 初次连接 */
	public static final int FIRST_LOGIN = 1;
	
	public static final String CLEAR_TIMER = "CLEAR_TIMER";
	
}
