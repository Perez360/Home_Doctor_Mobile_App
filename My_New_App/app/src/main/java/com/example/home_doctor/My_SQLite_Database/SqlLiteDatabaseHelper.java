package com.example.home_doctor.My_SQLite_Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SqlLiteDatabaseHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "home_doctor";
    final static String TABLE_NAME = "my_docotors";
    final static String ID_COL = "id";
    final static String DOCTOR_NAME = "doctor_name";
    final static String DOCTOR_MSG = "message";
    final static int DB_VERSION = 1;
    final static String DOCTOR_MSG_TIME = "time";
    final static String DOCTOR_MSG_DATE = "date";
    final static String DOCTOR_LOCATION = "location";
    Context context;
    String id;

    public SqlLiteDatabaseHelper(Context context, String id) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        this.id = id;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DOCTOR_NAME + " VARCHAR(255),"
                + DOCTOR_LOCATION + " VARCHAR(255),"
                + DOCTOR_MSG + " VARCHAR(1000),"
                + DOCTOR_MSG_TIME + " VARCHAR(255),"
                + DOCTOR_MSG_DATE + " VARCHAR(255)"
                + ")";
        db.execSQL(query);
    }

    public int getRowCount() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int result = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_NAME);
        return result;
    }

    public long insertData(String doctorname, String msg, String time, String date, String location) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DOCTOR_NAME, doctorname);
        contentValues.put(DOCTOR_MSG, msg);
        contentValues.put(DOCTOR_MSG_DATE, date);
        contentValues.put(DOCTOR_MSG_TIME, time);
        contentValues.put(DOCTOR_LOCATION, location);
        long result = -1;
        try {
            result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
           // MyToast.makeText(context, "Inserted", MyToast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    public String getData(int index) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  " + TABLE_NAME, null);
        String result = cursor.getString(index);
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
