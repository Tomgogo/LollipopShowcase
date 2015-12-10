package com.pardus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tom on 2015/12/2.
 */
public class DBAccessHelper extends SQLiteOpenHelper {
    //Database Version.
    private static final int DATABASE_VERSION = 1;
    private static DBAccessHelper sInstance;

    //Writable database instance.
    private SQLiteDatabase mDatabase;
    //Database Name.
    private static final String DATABASE_NAME = "M.db";
    public DBAccessHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * Returns a singleton instance for the database.
     * @param context
     * @return
     */
    public static synchronized DBAccessHelper getInstance(Context context) {
        if (sInstance==null)
            sInstance = new DBAccessHelper(context.getApplicationContext());

        return sInstance;
    }

    private synchronized SQLiteDatabase getDatabase() {
        if (mDatabase==null)
            mDatabase = getWritableDatabase();

        return mDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    protected void finalize() {
        try {
            getDatabase().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
