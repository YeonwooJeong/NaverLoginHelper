package com.yonoo.naverloginhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }
    @Override public void onCreate(SQLiteDatabase db) {

        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE LOGIN ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" ID TEXT, ");
        sb.append(" PW TEXT ) ");
        db.execSQL(sb.toString());
        Toast.makeText(context, "Table ????", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "??? ??????.", Toast.LENGTH_SHORT).show();
    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }

    public void addLogin(Login login) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO LOGIN ( ");
        sb.append(" ID, PW ) ");
        sb.append(" VALUES ( ?, ? ) ");
        db.execSQL(sb.toString(),
                new Object[]{
                        login.getId(),
                        login.getPw(),
                        });;
        Toast.makeText(context, "Insert ??", Toast.LENGTH_SHORT).show();
    }


    public List getAllData() { StringBuffer sb = new StringBuffer(); sb.append(" SELECT _ID, ID, PW FROM LOGIN ");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString(), null);
        List list = new ArrayList();
        Login login = null;
        while (cursor.moveToNext()) {
            login = new Login();
            login.set_id(cursor.getInt(0));
            login.setId(cursor.getString(1));
            login.setPw(cursor.getString(2));
            list.add(login);
        }
        return list;
    }


}



