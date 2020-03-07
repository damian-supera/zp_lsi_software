package com.example.zp_lsi_software.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zp_lsi_software.models.ExportModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ExportDbAdapter {

    // Informacje ogólne
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_EXPORTS_TABLE = "exports";

    // Kolumny
    private static final String KEY_ID = "_id";
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final int ID_COLUMN = 0;
    private static final String KEY_NAME = "name";
    private static final String NAME_OPTIONS = "TEXT NOT NULL";
    private static final int NAME_COLUMN = 1;
    private static final String KEY_DATE = "date";
    private static final String DATE_OPTIONS = "TEXT NOT NULL";
    private static final int DATE_COLUMN = 2;
    private static final String KEY_HOUR = "hour";
    private static final String HOUR_OPTIONS = "TEXT NOT NULL";
    private static final int HOUR_COLUMN = 3;
    private static final String KEY_USER = "user";
    private static final String USER_OPTIONS = "TEXT NOT NULL";
    private static final int USER_COLUMN = 4;
    private static final String KEY_PLACE = "place";
    private static final String PLACE_OPTIONS = "TEXT NOT NULL";
    private static final int PLACE_COLUMN = 5;

    // stałe do tworzenia i usuwania bazy
    private static final String DB_CREATE_EXPORT_TABLE =
            "CREATE TABLE " + DB_EXPORTS_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS + ", " +
                    KEY_DATE + " " + DATE_OPTIONS + ", " +
                    KEY_HOUR + " " + HOUR_OPTIONS + ", " +
                    KEY_USER + " " + USER_OPTIONS + ", " +
                    KEY_PLACE + " " + PLACE_OPTIONS + ");";
    private static final String DB_DROP_EXPORT_TABLE =
            "DROP TABLE IF EXISTS " + DB_EXPORTS_TABLE;


    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DatabaseHelper databaseHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_EXPORT_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DB_DROP_EXPORT_TABLE);

            onCreate(db);
        }
    }

    public ExportDbAdapter(Context context) {
        this.context = context;
    }

    public ExportDbAdapter open() {

        databaseHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        } catch (SQLException e) {
            sqLiteDatabase = databaseHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public long insertExport(String name, String date, String hour, String user, String place) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_HOUR, hour);
        contentValues.put(KEY_USER, user);
        contentValues.put(KEY_PLACE, place);
        return sqLiteDatabase.insert(DB_EXPORTS_TABLE, null, contentValues);
    }

    public List<Object> getAllExports() {
        String[] columns = {KEY_ID, KEY_NAME, KEY_DATE, KEY_HOUR, KEY_USER, KEY_PLACE};
        Cursor cursor = sqLiteDatabase.query(DB_EXPORTS_TABLE, columns, null, null, null, null, null);

        List<Object> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(new ExportModel(
                    cursor.getString(NAME_COLUMN),
                    cursor.getString(DATE_COLUMN),
                    cursor.getString(HOUR_COLUMN),
                    cursor.getString(USER_COLUMN),
                    cursor.getString(PLACE_COLUMN)));
        }

        cursor.close();

        return list;
    }
}
