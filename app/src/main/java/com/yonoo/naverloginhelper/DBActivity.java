package com.yonoo.naverloginhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class DBActivity extends AppCompatActivity {
//    public static ArrayList<String> list = new ArrayList<>() ;


    public static ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "LOGIN.db", null, 1);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dbHelper.getResult()) ;
//        list.add(dbHelper.getResult());
        for(int i =0 ; dbHelper.getResult().size() > 0 ;i++){

        }
        listview = (ListView) findViewById(R.id.list_view) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;

                // TODO : use strText
            }
        }) ;



        // ???? ?? ?? ??? ??
//        final TextView result = (TextView) findViewById(R.id.result);

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
//                result.setText(dbHelper.getResult());

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
//                result.setText(dbHelper.getResult());
            }
        });

        // DB? ?? ??? ??
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = etPw.getText().toString();

                dbHelper.delete(pw);
//                result.setText(dbHelper.getResult());
            }
        });

        // DB? ?? ??? ??
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                result.setText(dbHelper.getResult());
            }
        });
    }
}
