package com.yonoo.naverloginhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "LOGIN.db", null, 1);

        // ???? ?? ?? ??? ??
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etId = (EditText) findViewById(R.id.id);
        final EditText etPw = (EditText) findViewById(R.id.pw);


        // DB? ??? ??
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String pw = etPw.getText().toString();

                dbHelper.insert(id, pw);
                result.setText(dbHelper.getResult());
            }
        });

        // DB? ?? ??? ??
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String pw = etPw.getText().toString();

                dbHelper.update(id, pw);
                result.setText(dbHelper.getResult());
            }
        });

        // DB? ?? ??? ??
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = etPw.getText().toString();

                dbHelper.delete(pw);
                result.setText(dbHelper.getResult());
            }
        });

        // DB? ?? ??? ??
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResult());
            }
        });

    }

    public void mStart(View v) {
        startService(new Intent(this, com.yonoo.naverloginhelper.AlwaysTopServiceTouch.class));
    }

    public void mStop(View v) {
        stopService(new Intent(this, com.yonoo.naverloginhelper.AlwaysTopServiceTouch.class));
    }

}
