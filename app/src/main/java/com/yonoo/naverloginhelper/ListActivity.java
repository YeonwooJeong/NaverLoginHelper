package com.yonoo.naverloginhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private DBHelper dbHelper;
    public static DBActivity dbActivity = new DBActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView = (ListView) findViewById(R.id.list_view);
        dbHelper = new DBHelper( ListActivity.this, "LOGIN.db", null, 1);
        SelectList();
    }

    public void SelectList(){
        listView.setVisibility(View.VISIBLE);
        // DB Helper가 Null이면 초기화 시켜준다.
        if (dbHelper == null) {
            dbHelper = new DBHelper(ListActivity.this, "LOGIN", null, 1);
        }
        // 1. Person 데이터를 모두 가져온다.
        final List list = dbHelper.getAllData();
        // 2. ListView에 Person 데이터를 모두 보여준다.
        listView.setAdapter(new ListAdapter(list, ListActivity.this, dbHelper));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String listPosition = list.get(position).toString();
                Toast.makeText(getApplicationContext(),"포지션"+listPosition,Toast.LENGTH_SHORT).show();
                // get TextView's Text.
//                        String strText = (String) parent.getItemAtPosition(position) ;

                // TODO : use strText
            }
        });
    }

    //액티비티가 종료 될 때 디비를 닫아준다
    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }




}
