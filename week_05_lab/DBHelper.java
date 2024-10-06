package com.example.week_05_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "name database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "names";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + "INTEGER NOT NULL name_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + "VARCHAR(200) NOT NULL)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // update version
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }
     // api
    boolean addName(String name){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        //return -1 if the transaction is unsuccessful
        return sqLiteDatabase.insert(TABLE_NAME,null, cv) != -1;
    }
    Cursor getAllNames(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME, null );
    }
    boolean updateName(int id, String name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues ();
        cv. put (COLUMN_NAME, name) ;

        // return the affected row
        return sqLiteDatabase.update(
                TABLE_NAME, cv, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }
    boolean deleteName(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(
                TABLE_NAME, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }

}
