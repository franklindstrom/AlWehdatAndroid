package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.R;

/**
 * Created by golden on 10/28/2015.
 */
public class MatchTabAdapter extends BaseAdapter {

    public Context context;
    public int[] titleAry;

    public MatchTabAdapter(Context context, int[] dateAry) {
        this.context = context;
        this.titleAry = dateAry;
    }

    @Override
    public int getCount() {
        return this.titleAry.length;
    }

    @Override
    public Object getItem(int position) {
        return titleAry[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.match_tab_items, null);
        }

        final TextView txtTitle = (TextView) convertView.findViewById(R.id.tab_title);
        txtTitle.setText(titleAry[position]);

        return convertView;
    }
}