package com.smartseat.workgroup.main.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.smartseat.workgroup.common.utils.BaseVolume;
import com.smartseat.workgroup.main.model.ApplyInfoCache;
import java.util.ArrayList;

public class ApplyInfoDao {

	static SQLiteDatabase mDB;
	DBBaseDao mBaseDao;
	
	public ApplyInfoDao(Context context) {
		String filePath = context.getFilesDir().getAbsolutePath() +"/" + BaseVolume.DB_NAME;
		mDB = SQLiteDatabase.openOrCreateDatabase(filePath,null);
		if (mDB != null){ 
			this.mBaseDao = new DBBaseDao(mDB);
		}
		if (!mBaseDao.tabIsExist(DBContent.DeviceInfo.APPLY_NAME)) {
			mDB.execSQL(DBContent.DeviceInfo.getCreateUseSQL());
		}
	}
	
	/**
	 * 查询单个模式
	 * @param name
	 * @return
	 */
	public ArrayList<ApplyInfoCache> queryApplyByName(String name){
		String sql = "select * from ApplyInfo where useName = '"+ name+"'";
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	/**
	 * 查询所有应用
	 * @return
	 */
	public ArrayList<ApplyInfoCache> queryAllApply(){
		String sql = "select * from ApplyInfo " ;
		return mBaseDao.queryForListBySql(sql, mRowMapper_MessageData, null);
	}
	
	private static final SQLiteTemplate.RowMapper<ApplyInfoCache> mRowMapper_MessageData = new SQLiteTemplate.RowMapper<ApplyInfoCache>() {
		public ApplyInfoCache mapRow(Cursor cursor, int rowNum) {
			int useID = cursor.getInt(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.useID));
			String name = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.useName));
			String one = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.switchOne));
			String two = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.switchTwo));
			String three = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.switchThree));
			String four = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.switchFour));
			String five = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.switchFive));
			String useDeviceID = cursor.getString(cursor.getColumnIndex(DBContent.DeviceInfo.Columns.useDeviceID));
			ApplyInfoCache temple = new ApplyInfoCache(name,one,two,three,four,five,useDeviceID);
			temple.setUseID(useID);
			return temple;
		}
	};
	
	/**
	 * 修改Apply参数
	 */
	public int updateNameByApply(ApplyInfoCache apply) {
		int i = mDB.update(DBContent.DeviceInfo.APPLY_NAME, makeValues(apply), "useID = "+apply.getUseID(), null);
		return i;
	}
	
	/*****
	 * 添加应用
	 * @param data
	 * @return
	 */
		public int insertSingleData(ApplyInfoCache data) {
			int result = 0;
			try {
				mDB.insert(DBContent.DeviceInfo.APPLY_NAME,null,makeValues(data));
			} catch (Exception e) {
				e.printStackTrace();
				result = -1;
			}
			return result;
		} 
		
		private ContentValues makeValues(ApplyInfoCache temp) { 
			ContentValues initialValues = new ContentValues();
			initialValues.put(DBContent.DeviceInfo.Columns.useName, temp.getName());
			initialValues.put(DBContent.DeviceInfo.Columns.switchOne, temp.getSwitchOne());
			initialValues.put(DBContent.DeviceInfo.Columns.switchTwo, temp.getSwitchTwo());
			initialValues.put(DBContent.DeviceInfo.Columns.switchThree, temp.getSwitchThree());
			initialValues.put(DBContent.DeviceInfo.Columns.switchFour, temp.getSwitchFour());
			initialValues.put(DBContent.DeviceInfo.Columns.switchFive, temp.getSwitchFive());
			initialValues.put(DBContent.DeviceInfo.Columns.useDeviceID, temp.getUseDeviceID());
			return initialValues;
		}

		public void closeDb() {
			mDB.close();
		}
		
		/**
		 * 删除某个应用
		 */
		public int deleteApplyByUseID(int id) {
			String whereClause = "useID = "+id;
			int i = 0;
			try {
				i = mDB.delete(DBContent.DeviceInfo.APPLY_NAME,  whereClause, null);
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		/**
		 * 删除所有应用
		 */
		public int deleteAllApply() {
			int i = 0;
			try {
				i = mDB.delete(DBContent.DeviceInfo.APPLY_NAME,  "", null);
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
	
}