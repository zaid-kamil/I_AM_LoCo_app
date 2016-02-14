package com.xaidworkz.www.i_am_loco_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xaidi on 11/26/2015.
 */
public class DBAdapter implements Constants {

    SQLiteHelper helper;

    public DBAdapter(Context context) {
        helper = new SQLiteHelper(context);
    }

    private static class SQLiteHelper extends SQLiteOpenHelper{
        public SQLiteHelper(Context context) {
            super(context, DB_NAME,null, DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
