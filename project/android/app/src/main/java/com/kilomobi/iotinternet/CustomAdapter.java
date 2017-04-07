/*
 * Copyright (c) KiloMobi - 2017.
 */

package com.kilomobi.iotinternet;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fabrice on 21/07/16.
 */
public class CustomAdapter extends BaseAdapter {

    List<JsonModel> entryList;
    Context context;

    public CustomAdapter(Context context, List<JsonModel> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public int getCount() {
        return entryList.size();
    }

    @Override
    public Object getItem(int position) {
        return entryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.item, null);
            viewHolder = new ViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.item_rl);
            viewHolder.tvDecibel = (TextView) convertView.findViewById(R.id.item_tvNetwork);
            viewHolder.tvTimestamp = (TextView) convertView.findViewById(R.id.item_tvTimestamp);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        double value = entryList.get(position).getLocalNetwork();
        viewHolder.tvDecibel.setText(String.valueOf(value));
        viewHolder.tvTimestamp.setText(entryList.get(position).getTimestamp());

        if (value < 10)
            viewHolder.relativeLayout.setBackgroundColor(Color.parseColor("#A2C257"));
        else if (value < 25)
            viewHolder.relativeLayout.setBackgroundColor(Color.parseColor("#FCD59C"));
        else
            viewHolder.relativeLayout.setBackgroundColor(Color.parseColor("#CD5555"));

        return convertView;
    }

    private class ViewHolder {
        RelativeLayout relativeLayout;
        TextView tvDecibel;
        TextView tvTimestamp;
    }
}
