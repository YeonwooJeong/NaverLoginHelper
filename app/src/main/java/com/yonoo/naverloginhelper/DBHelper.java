package com.yonoo.naverloginhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // DBHelper ???? ??? DB ??? ?? ??? ??
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB? ?? ??? ? ???? ??
    @Override
    public void onCreate(SQLiteDatabase db) {
        // ??? ??? ??
        /* ??? MONEYBOOK??, ???? ?? ???? _id ??? ??? ???
        item ??? ??, price ??? ??, create_at ??? ???? ??? ???? ??. */
        db.execSQL("CREATE TABLE LOGIN (_id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, pw TEXT);");
    }

    // DB ?????? ?? ??? ??? ? ???? ??
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String id, String pw) {
        // ?? ??? ???? DB ??
        SQLiteDatabase db = getWritableDatabase();
        // DB? ??? ??? ? ??
        db.execSQL("INSERT INTO LOGIN VALUES(null, '" + id + "','" + pw + "');");
        db.close();
    }

    public void update(String pw, String id) {
        SQLiteDatabase db = getWritableDatabase();
        // ??? ??? ???? ?? ?? ?? ??
        db.execSQL("UPDATE LOGIN SET id=" + id + " WHERE pw='" + pw + "';");
        db.close();
    }

    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        // ??? ??? ???? ? ??
        db.execSQL("DELETE FROM LOGIN WHERE id='" + id + "';");
        db.close();
    }

    public String getResult() {
        // ??? ???? DB ??
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB? ?? ???? ?? ???? ?? Cursor? ???? ???? ?? ?? ??? ??
        Cursor cursor = db.rawQuery("SELECT * FROM LOGIN", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + "\n";
        }

        return result;
    }
}


