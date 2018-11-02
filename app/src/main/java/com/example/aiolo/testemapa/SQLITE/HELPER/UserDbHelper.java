package com.example.aiolo.testemapa.SQLITE.HELPER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aiolo.testemapa.SQLITE.UserContract;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            UserContract.UserEntry.TABLE_NAME + " (" +
            UserContract.UserEntry._ID + " INTEGER PRIMARY KEY, " +
            UserContract.UserEntry.COLUMN_NAME_USUARIO + " VACHAR(250), " +
            UserContract.UserEntry.COLUMN_NAME_LAST_NAME + " VARCHAR(250), " +
            UserContract.UserEntry.COLUMN_NAME_CITY + " VARCHAR(250), " +
            UserContract.UserEntry.COLUMN_NAME_STATE + " VARCHAR(250), " +
            UserContract.UserEntry.COLUMN_NAME_MARITIAL_STATE + " VARCHAR(250), " +
            UserContract.UserEntry.COLUMN_NAME_MOTOCYCLE + " VARCHAR(250), " +
            UserContract.UserEntry.COLUMN_NAME_EMAIL_USER + " VARCHAR(250))";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
            UserContract.UserEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "motoclub";

    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
