package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
	public Cursor grabWater() throws SQLException {
		Cursor mCursor = mDb.query(DATABASE_TABLE, allCols, TOTAL_CURRENT_WATER + "=0", null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public Cursor grabProgress() throws SQLException {
		Cursor mCursor = mDb.query(DATABASE_TABLE, allCols, PROGRESS_MADE + "=0",null,null,null,
				null,null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public boolean updateWaterData(int consumedWater, int progressMade) {
		ContentValues args = new ContentValues();
		args.put(TOTAL_CURRENT_WATER, consumedWater);
		args.put(PROGRESS_MADE, progressMade);


		/* Not really sure what to put as the third argument. We don't have any rows
			just a column. Not sure if I need a rowId
		 */
		return mDb.update(DATABASE_TABLE, args,??? , null);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int i, int i1) {
			Log.w("DatabaseHelper", "Upgrading database from version " + i + " to "
					+ i1 + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
}

