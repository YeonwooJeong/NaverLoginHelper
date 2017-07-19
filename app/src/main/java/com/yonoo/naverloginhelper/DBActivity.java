package com.yonoo.naverloginhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


public class DBActivity extends AppCompatActivity {
//    public static ArrayList<String> list = new ArrayList<>() ;

    private Button btnInsertDatabase,btnSelectAllData;
    private ListView listView;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        dbHelper = new DBHelper( DBActivity.this, "LOGIN", null, 1);
        dbHelper.testDB();


        btnInsertDatabase = (Button) findViewById(R.id.insert);
        btnInsertDatabase.setOnClickListener(new View.OnClickListener()

        {
            @Override public void onClick (View v){
                final EditText etId = (EditText) findViewById(R.id.id);
                etId.setHint("ID을 입력하세요.");
                final EditText etPw = (EditText) findViewById(R.id.pw);
                etPw.setHint("PW를 입력하세요.");

                String id = etId.getText().toString();
                String pw = etPw.getText().toString();
                if (dbHelper == null) {
                    dbHelper = new DBHelper(DBActivity.this, "TEST", null, 1);
                }
                Login login = new Login();
                login.setId(id);
                login.setPw(pw);
                dbHelper.addLogin(login);
            }
        });

        listView = (ListView) findViewById(R.id.list_view);
        btnSelectAllData = (Button) findViewById(R.id.select);
        btnSelectAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ListView를 보여준다.
                listView.setVisibility(View.VISIBLE);
                // DB Helper가 Null이면 초기화 시켜준다.
                if (dbHelper == null) {
                    dbHelper = new DBHelper(DBActivity.this, "TEST", null, 1);
                }
                // 1. Person 데이터를 모두 가져온다.
                List list = dbHelper.getAllData();
                // 2. ListView에 Person 데이터를 모두 보여준다.
                listView.setAdapter(new LoginListAdapter(list, DBActivity.this));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {

                        // get TextView's Text.
                        String strText = (String) parent.getItemAtPosition(position) ;

                        // TODO : use strText
                    }
                }) ;
            }
        });
    }


}
