package com.example.vishnu.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vishnu-c on 9/22/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
             sqLiteDatabase.execSQL("CREATE TABLE "+  TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, SURNAME TEXT,MARKS INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertdata(String name, String surname, String marks){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name,COL_2);
        contentValues.put(surname,COL_3);
        contentValues.put(marks,COL_4);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else {
            return true;
        }
    }


}
