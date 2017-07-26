package com.yonoo.naverloginhelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;


class LoginListAdapter extends BaseAdapter{
    int i=0;
    private List list;
    private Context context;

    public LoginListAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
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

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = new LinearLayout(context);
            ((LinearLayout) convertView).setOrientation(LinearLayout.HORIZONTAL);
            TextView tvId = new TextView(context);
            tvId.setPadding(10, 0, 20, 0);
            tvId.setTextColor(Color.rgb(0, 0, 0));
            TextView tvName = new TextView(context);
            tvName.setPadding(20, 0, 20, 0);
            tvName.setTextColor(Color.rgb(0, 0, 0));
            TextView tvPw = new TextView(context);
            tvPw.setPadding(20, 0, 20, 0);
            tvPw.setTextColor(Color.rgb(0, 0, 0));
            ((LinearLayout) convertView).addView(tvId);
            ((LinearLayout) convertView).addView(tvName);
            ((LinearLayout) convertView).addView(tvPw);
            holder = new Holder();
            holder.tv_id = tvId;
            holder.tvId = tvName;
            holder.tvPw = tvPw;
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        System.out.println("position"+position);
        final Login login = (Login) getItem(position);
        holder.tv_id.setTextSize(20);
        holder.tv_id.setText(login.get_id() + "");


        holder.tvId.setTextSize(20);
        holder.tvId.setText(login.getId() + "   |");
        final String id = login.getId();
        holder.tvId.setOnTouchListener(new View.OnTouchListener(){   //터치 이벤트 리스너 등록(누를때와 뗐을때를 구분)

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("ID",id);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context,"ID가 복사되었습니다.",Toast.LENGTH_SHORT).show();
                }

                if(event.getAction()==MotionEvent.ACTION_UP){
                }
                return true;
            }
        });

        holder.tvPw.setTextSize(20);
        holder.tvPw.setText(login.getPw() + "   ");
        final String pw = login.getPw();
        holder.tvPw.setOnTouchListener(new View.OnTouchListener(){   //터치 이벤트 리스너 등록(누를때와 뗐을때를 구분)

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("PW",pw);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context,"PW가 복사되었습니다.",Toast.LENGTH_SHORT).show();
                }

                if(event.getAction()==MotionEvent.ACTION_UP){
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



