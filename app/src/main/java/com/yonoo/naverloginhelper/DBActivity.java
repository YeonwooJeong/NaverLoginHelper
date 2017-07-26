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

    private Button btnInsertDatabase,btnSelectAllData;
    private ListView listView;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        dbHelper = new DBHelper( DBActivity.this, "LOGIN", null, 1);
        dbHelper.testDB();

        listView = (ListView) findViewById(R.id.list_view);
        // ListView를 보여준다.
        SelectList();


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
                SelectList();
            }
        });

        btnSelectAllData = (Button) findViewById(R.id.select);
        btnSelectAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SelectList();
            }
        });
    }

    public void DeleteList(){

        int count, checked ;
        count = listView.getAdapter().getCount();

        if (count > 0) {
            // 현재 선택된 아이템의 position 획득.
            checked = listView.getCheckedItemPosition();

            if (checked > -1 && checked < count) {
                // 아이템 삭제
                dbHelper.delete(count);

                // listview 선택 초기화.
                listView.clearChoices();

                // listview 갱신.
                SelectList();
            }
        }
    }

    public void SelectList(){
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
//                        String strText = (String) parent.getItemAtPosition(position) ;

                // TODO : use strText
            }
        });
    }


}
