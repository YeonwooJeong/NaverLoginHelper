package com.yonoo.naverloginhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private DBHelper dbHelper;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }
    @Override public void onCreate(SQLiteDatabase db) {
        try {
            String DROP_SQL = "drop table if exists LOGIN";
            db.execSQL(DROP_SQL);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in DROP_SQL", ex);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE LOGIN ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" ID TEXT, ");
        sb.append(" PW TEXT ) ");
        try {
            db.execSQL(sb.toString());
        } catch(Exception ex) {
            Log.e(TAG, "Exception in CREATE_SQL", ex);
        }

        Toast.makeText(context, R.string.make, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, R.string.upgrade, Toast.LENGTH_SHORT).show();
    }


    public void addLogin(Login login) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO LOGIN ( ");
        sb.append(" ID, PW ) ");
        sb.append(" VALUES ( ?, ? ) ");
        if(login.getId().equals("") || login.getPw().equals("")){
            Toast.makeText(context, R.string.nodata, Toast.LENGTH_SHORT).show();
        }else{
            db.execSQL(sb.toString(),
                    new Object[]{
                            login.getId(),
                            login.getPw(),
                    });;
            Toast.makeText(context, R.string.insert, Toast.LENGTH_SHORT).show();
        }

    }

public void delete(int position) {
    SQLiteDatabase db = getWritableDatabase();
    db.execSQL("DELETE FROM LOGIN WHERE _ID=" + position + ";");
//    db.execSQL("DELETE FROM LOGIN WHERE _ID=2;");
    db.close();


}


    public List getAllData() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, ID, PW FROM LOGIN ");
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



