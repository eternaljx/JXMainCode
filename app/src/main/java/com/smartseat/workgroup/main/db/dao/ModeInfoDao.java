package com.smartseat.workgroup.main.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.main.model.ModeInfoCache;

import java.util.ArrayList;

public class ModeInfoDao {

	static SQLiteDatabase mDB;
	DBBaseDao mBaseDao;
	
	public ModeInfoDao(Context context) {
		String filePath = context.getFilesDir().getAbsolutePath() +"/" + BaseVolume.DB_NAME;
		mDB = SQLiteDatabase.openOrCreateDatabase(filePath,null);
		if (mDB != null){ 
			this.mBaseDao = new DBBaseDao(mDB);
		}
		if (!mBaseDao.tabIsExist(DBContent.DeviceInfo.MODE_NAME)) {
			mDB.execSQL(DBContent.DeviceInfo.getCreateModeSQL());
		}
	}
	
	/**
	 * 查询单个模式
	 * @param modeName
	 * @return
	 */
	public ArrayList<ModeInfoCache> queryModeByModeName(String modeName){
		String sql = "select * from ModeInfo where modeName = '"+ modeName+"'";
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	/**
	 * 查询单个模式
	 * @param modeID
	 * @return
	 */
	public ArrayList<ModeInfoCache> queryModeByModeID(int modeID){
		String sql = "select * from ModeInfo where modeID = "+ modeID;
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	/**
	 * 查询某个设备下的所有模式
	 * @param deviceId
	 * @return
	 */
	public ArrayList<ModeInfoCache> queryAllModeByDeviceID(String deviceId){
		String sql = "select * from ModeInfo where modeDeviceID = '"+ deviceId+"' ORDER BY modeTime DESC" ;// 根据添加时间降序排列
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	private static final SQLiteTemplate.RowMapper<ModeInfoCache> mRowMapper_MessageData = new SQLiteTemplate.RowMapper<ModeInfoCache>() {
		public ModeInfoCache mapRow(Cursor cursor, int rowNum) {
			int modeID = cursor.getInt(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeID));
			String deviceID = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeDeviceID));
			String one = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeOne));
			String two = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeTwo));
			String three = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeThree));
			String four = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeFour));
			String five = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeFive));
			String name = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeName));
			String addTime = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.modeTime));
			ModeInfoCache temple = new ModeInfoCache(modeID,deviceID, name,one,two,three,four,five,addTime);
			
			return temple;
		}
	};
	
	/*****
	 * 添加设备
	 * @param data
	 * @return
	 */
		public int insertSingleData(ModeInfoCache data) {
			int result = 0;
			try {
				mDB.insert(DBContent.DeviceInfo.MODE_NAME,null,makeValues(data));
			} catch (Exception e) {
				e.printStackTrace();
				result = -1;
			}
			return result;
		} 
		
		private ContentValues makeValues(ModeInfoCache temp) { 
			ContentValues initialValues = new ContentValues();
			initialValues.put(DBContent.DeviceInfo.Columns.modeDeviceID, temp.getDeviceID());
			initialValues.put(DBContent.DeviceInfo.Columns.modeName, temp.getModeName());
			initialValues.put(DBContent.DeviceInfo.Columns.modeOne, temp.getOneTime());
			initialValues.put(DBContent.DeviceInfo.Columns.modeTwo, temp.getTwoTime());
			initialValues.put(DBContent.DeviceInfo.Columns.modeThree, temp.getThreeTime());
			initialValues.put(DBContent.DeviceInfo.Columns.modeFour, temp.getFourTime());
			initialValues.put(DBContent.DeviceInfo.Columns.modeFive, temp.getFiveTime());
			initialValues.put(DBContent.DeviceInfo.Columns.modeTime, temp.getAddTime());
			return initialValues;
		}

		/**
		 * 修改设备参数
		 */
		public int updateNameByMode(ModeInfoCache mode) {
			
			int i = mDB.update(DBContent.DeviceInfo.MODE_NAME, makeValues(mode), "modeID = "+mode.getModeID(), null);
			
			return i;
		}
		
		public void closeDb() {
			mDB.close();
		}
		
		/**
		 * 删除某个模式
		 */
		public int deleteModeByModeID(int id) {
			String whereClause = "modeID = "+id;
			int i = 0;
			try {
				i = mDB.delete(DBContent.DeviceInfo.MODE_NAME,  whereClause, null);
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		/**
		 * 删除某个设备的所有模式
		 */
		public int deleteAllModeByDeviceID(String deviceID) {
			String whereClause = "modeDeviceID = '"+deviceID+"'";
			int i = 0;
			try {
				i = mDB.delete(DBContent.DeviceInfo.MODE_NAME,  whereClause, null);
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		/**
		 * 删除所有模式
		 */
		public int deleteAllMode() {
			int i = 0;
			try {
				i = mDB.delete(DBContent.DeviceInfo.MODE_NAME,  "", null);
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
	
}