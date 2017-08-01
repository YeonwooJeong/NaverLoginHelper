package com.yonoo.naverloginhelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;


class LoginListAdapter extends BaseAdapter {
    int i = 0;
    private List list;
    private Context context;
    private DBHelper dbHelper;
    private DBActivity dbActivity;

    public LoginListAdapter(List list, Context context, DBHelper dbHelper) {
        this.list = list;
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {

        return position;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view).setText(cursor.getString(0) + " / " + cursor.getString(1));
    }




    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        final Login login = (Login) getItem(position);
        if (convertView == null) {
            convertView = new LinearLayout(context);
            ((LinearLayout) convertView).setOrientation(LinearLayout.HORIZONTAL);

            ((LinearLayout) convertView).setGravity(LinearLayout.HORIZONTAL);


            Button delete = new Button(context);
            delete.setText(R.string.delete);
            delete.setTextSize(13);
            delete.setPadding(2, 0, 2, 0);
            delete.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            System.out.println("position"+position);
//                            dbActivity.DeleteList(login.get_id());
                            dbHelper.delete(login.get_id());
                        }
                    }
            );


            TextView tvId = new TextView(context);
            tvId.setPadding(10, 0, 20, 0);
            tvId.setGravity(Gravity.LEFT);
            tvId.setTextColor(Color.rgb(0, 0, 0));
            TextView tvName = new TextView(context);
            tvName.setPadding(20, 0, 20, 0);
            tvName.setGravity(Gravity.LEFT);
            tvName.setTextColor(Color.rgb(0, 0, 0));
            TextView tvPw = new TextView(context);
            tvPw.setPadding(20, 0, 20, 0);
            tvPw.setGravity(Gravity.LEFT);
            tvPw.setTextColor(Color.rgb(0, 0, 0));
            ((LinearLayout) convertView).addView(tvId);
            ((LinearLayout) convertView).addView(tvName);
            ((LinearLayout) convertView).addView(tvPw);
            ((LinearLayout) convertView).addView(delete);
            holder = new Holder();
            holder.tv_id = tvId;
            holder.tvId = tvName;
            holder.tvPw = tvPw;
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.tv_id.setTextSize(20);
        holder.tv_id.setText(position+1 + "");
//        holder.tv_id.setText(login.get_id() + "");


        holder.tvId.setTextSize(15);
        holder.tvId.setText(login.getId() + "   |");
        final String id = login.getId();
        holder.tvId.setOnTouchListener(new View.OnTouchListener() {   //터치 이벤트 리스너 등록(누를때와 뗐을때를 구분)

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("ID", id);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context, "ID가 복사되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    context.startActivity(intent);

                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                return true;
            }
        });

        holder.tvPw.setTextSize(15);
        holder.tvPw.setText(login.getPw() + "   ");
        final String pw = login.getPw();
        holder.tvPw.setOnTouchListener(new View.OnTouchListener() {   //터치 이벤트 리스너 등록(누를때와 뗐을때를 구분)

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("PW", pw);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context, "PW가 복사되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    context.startActivity(intent);
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                return true;
            }
        });

        return convertView;


    }

    private class Holder {
        //기능확인
        public TextView tv_id;
        public TextView tvId;
        public TextView tvPw;
    }



}



