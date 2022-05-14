package com.brindha.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class dbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "example.db";

    public static final String TABLE_NAME = "Example_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_NUMBER = "NUMBER";
    Context context;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " INTERGER );";

        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(SQL_DELETE);
        onCreate(db);


    }

    public void insertData(String name, int num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, num);

        // Insert new row
        // Insert SQL Statement
        long result = db.insert(TABLE_NAME, null, values);

        // To check data is inserted or not
        if (result == -1) {
            Toast.makeText(context, "Data not saved ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String id, String name, int num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, num);

        // Which row to update , based on the ID
        // Update SQL Statement
        long result = db.update(TABLE_NAME, values, "ID = ?", new String[]{id});


        // To check data updated or not
        if (result == -1) {
            Toast.makeText(context, "Data not Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL Statement for Delete
        long result = db.delete(TABLE_NAME, "ID = ?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Data not Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData() {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL Statement
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor;


    }

    public Cursor searchData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();


        // Which row to search , based on the ID
        String[] columns = new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_NUMBER};
        Cursor cursor = db.query(TABLE_NAME, columns, "ID = ?", new String[]{id},
                null, null, null, null);

        return cursor;

    }


    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}



