package com.dnkilic.application41.baris;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by dnkilic on 11/06/16.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "sementha.db";

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QueryResults(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, Result TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QueryResults");
        onCreate(db);
    }

    public void insertResult(String result)
    {
        ContentValues cv = new ContentValues();
        cv.put("Result",result);
        this.getWritableDatabase().insertOrThrow("QueryResults","",cv);
    }

    public void listResults()
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM QueryResults",null);
        while (cursor.moveToNext())
        {
            System.out.println(cursor.getString(0));
            System.out.println(cursor.getString(1));
        }
    }

}
