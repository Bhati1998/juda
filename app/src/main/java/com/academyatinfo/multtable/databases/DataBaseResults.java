package com.academyatinfo.multtable.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.academyatinfo.multtable.models.Result;

import java.util.ArrayList;

/**
 * Created by abde on 14/02/17.
 */

public class DataBaseResults {
    private static String DATABASE_NAME = "MULTTABLE";
    private static String DATABASE_TABLE = "TABLERESULTS";
    private static final int DATABASE_VERSION = 1;

    // columns tables
    private static final String KEY_ID = "_id";
    private static final String KEY_RESULT = "_result";
    private static final String KEY_TIME = "_time";
    private static final String KEY_DATE = "_date";
    private static final String KEY_NAME = "_name";
    private final Context context;
    SQLiteDatabase sqLiteDatabase;
    DBhelber dbhelber;

    public DataBaseResults(Context context) {
        this.context = context;
    }

    public class DBhelber extends SQLiteOpenHelper {

        public DBhelber(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NAME + " TEXT NOT NULL,"
                    + KEY_RESULT + " TEXT NOT NULL,"
                    + KEY_TIME + " TEXT NOT NULL,"
                    + KEY_DATE + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop Table iF exist " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public void open() {
        dbhelber = new DBhelber(context);
        sqLiteDatabase = dbhelber.getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
        dbhelber.close();
    }


    public void insertResult(Result result) {
        /*final ContentValues content = new ContentValues();
        content.put(KEY_NAME, result.getName());
        content.put(KEY_RESULT, result.getResult());
        content.put(KEY_TIME, result.getTime());
        content.put(KEY_DATE, result.getDate());
        sqLiteDatabase.insert(DATABASE_TABLE, null, content);*/
    }

    public ArrayList<Result> getResults() {
        ArrayList<Result> arrayResults = new ArrayList<>();
        /*Result result;
        String[] columns = {KEY_NAME, KEY_RESULT, KEY_TIME, KEY_DATE};
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = new Result();
            result.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            result.setResult(cursor.getString(cursor.getColumnIndex(KEY_RESULT)));
            result.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            result.setDate((cursor.getString(cursor.getColumnIndex(KEY_DATE))));
            arrayResults.add(result);
        }*/
        return arrayResults;
    }

    public boolean checkCursor() {
        String[] columns = {KEY_NAME, KEY_RESULT, KEY_TIME, KEY_DATE};
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
            return true;
        return false;
    }
}
