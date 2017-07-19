package com.yonoo.naverloginhelper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


class LoginListAdapter extends BaseAdapter{

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
            holder.tvId = tvId;
            holder.tvName = tvName;
            holder.tvPw = tvPw;
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Login login = (Login) getItem(position);
        holder.tvId.setText(login.get_id() + "");
        holder.tvName.setText(login.getId());
        holder.tvPw.setText(login.getPw() + "");
        return convertView;
    }
    private class Holder {
        public TextView tvId;
        public TextView tvName;
        public TextView tvPw;
    }
}



