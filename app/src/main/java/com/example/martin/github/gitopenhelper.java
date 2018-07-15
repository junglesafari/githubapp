package com.example.martin.github;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class gitopenhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mygithub";
    public static final int DATABASE_VERSION = 1;
    public static gitopenhelper instance;

    public gitopenhelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public static gitopenhelper getInstance(Context context) {
        if (instance == null) {
            instance = new gitopenhelper( context.getApplicationContext() );
            return instance;
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String gitsql = "CREATE TABLE " + contractclass.git.TABLE_NAME +
                " ( " + contractclass.git.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + contractclass.git.COLUMN_NAME + " TEXT ,"
                + contractclass.git.COLUMN_URL+" TEXT UNIQUE ) ";

        sqLiteDatabase.execSQL( gitsql );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
