package edu.lewisu.cs.nsoto.androidfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "waterInfo";
    private static final String DB_TABLE = "water";

    private static final String KEYID = "ID";
    private static final String KEYWATER = "accumulatedWater";
    private static final String KEYMAXWATER = "maxWater";

    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createWaterTable = "CREATE TABLE " + DB_TABLE + "(" +
                KEYID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEYWATER + " REAL," + KEYMAXWATER + " REAL" + ")";
        db.execSQL(createWaterTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DatabaseHelper", "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS DB_TABLE");
        onCreate(db);
    }

    public void addWater(WaterModel water) {
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();
        cvalues.put(KEYWATER, water.getCurrentWater());
        cvalues.put(KEYMAXWATER, water.getMaxWater());

        sdb.insert(DB_TABLE, null, cvalues);
        sdb.close();
    }

    public WaterModel getWaterAmount(int id) {
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.query(DB_TABLE, new String[] { KEYID, KEYWATER, KEYMAXWATER }, KEYID + " =?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        WaterModel waterAmt = new WaterModel(Float.parseFloat(cursor.getString(1)), Float.parseFloat(cursor.getString(2)));

        sdb.close();
        cursor.close();
        return waterAmt;

    }

    public int updateWater(WaterModel water) {
        SQLiteDatabase sdb = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();
        cvalues.put(KEYWATER, water.getCurrentWater());
        cvalues.put(KEYMAXWATER, water.getMaxWater());

        return sdb.update(DB_TABLE, cvalues, KEYID + " =?",
                new String[] {String.valueOf(1)});
    }

    public void deleteWater() {
        SQLiteDatabase sdb = this.getWritableDatabase();
        sdb.delete(DB_TABLE, KEYID + " =?", new String[] {String.valueOf(1)});
        sdb.close();
    }



}
