package com.smartseat.workgroup.main.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.main.model.DeviceInfoCache;

import java.util.ArrayList;
import java.util.List;

public class DeviceInfoDao {

	static SQLiteDatabase mDB;
	DBBaseDao mBaseDao;
	
	public DeviceInfoDao(Context context) {
		String filePath = context.getFilesDir().getAbsolutePath() +"/" + BaseVolume.DB_NAME;
		mDB = SQLiteDatabase.openOrCreateDatabase(filePath,null);
		if (mDB != null){ 
			this.mBaseDao = new DBBaseDao(mDB);
		}
		if (!mBaseDao.tabIsExist(DBContent.DeviceInfo.TABLE_NAME)) {
			mDB.execSQL(DBContent.DeviceInfo.getCreateSQL());
		}
	}
	
	/**
	 * 查找某个设备
	 */
	public Boolean isDeviceExist(String deviceId,int style){
		DeviceInfoCache temp = featchDeviceByID(deviceId, style);
		return temp==null?false:true;
	}
	
	public DeviceInfoCache featchDeviceByID(String deviceId){
		String sql = "select * from DeviceInfo where deviceID = '"+ deviceId+"'";
		 List<DeviceInfoCache> temp  =  mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
		if(temp.size()>0)
			return temp.get(0);
		return null;
		
	}
	
	public DeviceInfoCache featchDeviceByID(String deviceId,int style){
		String sql = "select * from DeviceInfo where deviceID = '"+ deviceId+"' and deviceStyle = "+style ;
		 List<DeviceInfoCache> temp  =  mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
		if(temp.size()>0)
			return temp.get(0);
		return null;
		
	}

	/**
	 * 查询所有设备
	 * @param style
	 * @return
	 */
	public ArrayList<DeviceInfoCache> queryAllDevice(int style){
		String sql = "select * from DeviceInfo where deviceStyle = " + style + " ORDER BY devicePlace ASC";
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	private static final SQLiteTemplate.RowMapper<DeviceInfoCache> mRowMapper_MessageData = new SQLiteTemplate.RowMapper<DeviceInfoCache>() {
		public DeviceInfoCache mapRow(Cursor cursor, int rowNum) {
			String ID = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.id));
			String deviceID = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.PID));
			String Pwd = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.devicePwd));
			String time = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.deviceTime));
			int style = cursor.getInt(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.deviceStyle));
			int place = cursor.getInt(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.devicePlace));
			DeviceInfoCache temple = new DeviceInfoCache(deviceID,Pwd,time,style,place);
			
			return temple;
		}
	};
	
	public ArrayList<DeviceInfoCache> queryAllDevice(){
		String sql = "select * from DeviceInfo ORDER BY devicePlace ASC";
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	/*****
	 * 添加设备
	 * @param data
	 * @return
	 */
		public int insertSingleData(DeviceInfoCache data) {
			int result = 0;
			try {
				mDB.insert(DBContent.DeviceInfo.TABLE_NAME,null,makeValues(data));
			} catch (Exception e) {
				e.printStackTrace();
				result = -1;
			}
			return result;
		} 
		
		private ContentValues makeValues(DeviceInfoCache temp) { 
			ContentValues initialValues = new ContentValues();
			initialValues.put(DBContent.DeviceInfo.Columns.PID, temp.getId());
			initialValues.put(DBContent.DeviceInfo.Columns.devicePwd, temp.getPwd());
			initialValues.put(DBContent.DeviceInfo.Columns.deviceTime, temp.getTime());
			initialValues.put(DBContent.DeviceInfo.Columns.deviceStyle, temp.getStyle());
			initialValues.put(DBContent.DeviceInfo.Columns.devicePlace, temp.getPlace());
			return initialValues;
		}
		
		/**
		 * 修改设备参数
		 */
		public int updateData(DeviceInfoCache data) {
			int i = mDB.update(DBContent.DeviceInfo.TABLE_NAME, makeValues(data), "deviceID = '"+data.getId() +"'", null);
			
			return i;
		}

		/**
		 * 修改设备参数
		 */
		public int updateDataByDeviceMac(ContentValues contents,String  deviceId) {
			int i = mDB.update(DBContent.DeviceInfo.TABLE_NAME, contents, "deviceID = '"+deviceId +"'", null);
			
			return i;
		}
		
		public void closeDb() {
			mDB.close();
		}
		
		/**
		 * 删除某个设备
		 */
		public int deleteDataByID(String id) {
			String whereClause = "deviceID = ?";
			int i;
			try {
				i = mDB.delete(DBContent.DeviceInfo.TABLE_NAME,  whereClause, new String[]{id});
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		/**
		 * 删除某个类型的设备
		 */
		public int deleteData(DeviceInfoCache data) {
			String whereClause = "deviceID = ? and deviceStyle = ?";
			int i;
			try {
				i = mDB.delete(DBContent.DeviceInfo.TABLE_NAME,  whereClause, new String[]{data.getId(),data.getStyle()+""});
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		// 删除所有设备
		public void deleteAllData() {
			int i = mDB.delete(DBContent.DeviceInfo.TABLE_NAME, "", null);
			Log.e("删除所有设备","操作返回值："+i);
		}
		
		// 删除所有分享设备
		public void deleteShareAllData() {
			String whereClause = "deviceStyle = ?";
			int i = mDB.delete(DBContent.DeviceInfo.TABLE_NAME,  whereClause, new String[]{BaseVolume.IS_SHARE_ADD+""});
			Log.e("删除所有设备","操作返回值："+i);
		}
	
}