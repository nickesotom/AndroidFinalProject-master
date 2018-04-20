package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nicolas Soto on 4/19/2018.
 */

public class DbAdapter {
	private static final String DATABASE_NAME = "storingWater.db";
	private static final String DATABASE_TABLE = "waterHad";
	private static final int DATABASE_VERSION = 1;

	public static final String TOTAL_CURRENT_WATER = "currentWater";
	public static final String PROGRESS_MADE = "progress";

	private static final String[] allCols = new String[] {TOTAL_CURRENT_WATER, PROGRESS_MADE};

	private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE +" (" +
			TOTAL_CURRENT_WATER + " integer, " +
			PROGRESS_MADE +  " integer, " + ");";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private final Context mCtx;
	public DbAdapter(Context ctx) {
		this.mCtx = ctx;  //set context
	}
	public void open() throws SQLException {  //open db, if fail throw an exception
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
	}
	public void close() {
		mDbHelper.close();
	}
	public long createWaterData(int consumedWater, int currentProgress) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(TOTAL_CURRENT_WATER, consumedWater);
		initialValues.put(PROGRESS_MADE, currentProgress);

		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}
	public Cursor fetchAllData() {
		return mDb.query(DATABASE_TABLE, allCols, null, null,null,null,null);
	}











	private static class DatabaseHelper extends SQLiteOpenHelper {

		@Override
		public void onCreate(SQLiteDatabase sqLiteDatabase) {

		}

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

		}
	}
}

