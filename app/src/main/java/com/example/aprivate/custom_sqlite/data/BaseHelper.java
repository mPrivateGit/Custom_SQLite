package com.example.aprivate.custom_sqlite.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseHelper extends SQLiteOpenHelper {
    public static final String DATBASE_NAME = "cards.db";
    public static final int VERSION = 1;

    public BaseHelper(Context context) {
        super(context, DATBASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mSQL = "create table " + BaseShema.CardTable.TABLE_NAME + "(" +
                " _id integer primary key autoincrement, " +
                BaseShema.Cols.UUID + ", " +
                BaseShema.Cols.TYPE + ", " +
                BaseShema.Cols.BANK + ", " +
                BaseShema.Cols.NUMBER +
                ")";

        db.execSQL(mSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
