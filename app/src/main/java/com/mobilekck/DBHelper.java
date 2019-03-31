package com.mobilekck;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MainBazisMobileDb";
    public static final String TABLE_CONTACTS = "owners";

    public static final String KEY_ID = "_id";
    public static final String KEY_IIN = "iin";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_ADRESS = "adress";
    public static final String KEY_WALLET = "wallet";
    public static final String KEY_BILL = "bill";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_IIN + " text," + KEY_NAME + " text," + KEY_SURNAME + " text,"
                + KEY_LASTNAME + " text," + KEY_ADRESS + " text," + KEY_WALLET + " text," + KEY_BILL + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}