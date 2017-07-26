package com.yonoo.naverloginhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        dbHelper = new DBHelper( ListActivity.this, "LOGIN", null, 1);
        dbHelper.testDB();

        listView = (ListView) findViewById(R.id.list_view);
        // ListView를 보여준다.
        SelectList();

    }

    public void SelectList(){
        listView.setVisibility(View.VISIBLE);
        // DB Helper가 Null이면 초기화 시켜준다.
        if (dbHelper == null) {
            dbHelper = new DBHelper(ListActivity.this, "TEST", null, 1);
        }
        // 1. Person 데이터를 모두 가져온다.
        List list = dbHelper.getAllData();
        // 2. ListView에 Person 데이터를 모두 보여준다.
        listView.setAdapter(new LoginListAdapter(list, ListActivity.this));
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
