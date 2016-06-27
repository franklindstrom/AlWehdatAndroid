package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

import java.util.ArrayList;

/**
 * Created by golden on 10/28/2015.
 */
public class DefaultListAdapter extends BaseAdapter {

    public Context context;
    public int[] titleAry;
    public int[] contentAry;
    public int[] dateAry;
    public ArrayList<Drawable> imageAry;

    public DefaultListAdapter(Context context, ArrayList<Drawable> imageAry, int[] titleAry, int[] contentAry, int[] dateAry) {
        this.context = context;
        this.imageAry = imageAry;
        this.titleAry = titleAry;
        this.contentAry = contentAry;
        this.dateAry = dateAry;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.default_list_items, null);

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.item_image);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.item_title);
        TextView txtContent = (TextView) convertView.findViewById(R.id.item_content);
        TextView txtDate = (TextView) convertView.findViewById(R.id.item_date);

        imgIcon.setBackgroundDrawable(imageAry.get(position));
        txtTitle.setText(titleAry[position]);
        txtContent.setText(contentAry[position]);
        txtDate.setText(dateAry[position]);

        if (txtContent.getText().toString().equals("none")) {
            txtContent.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            convertView.setScaleX(-1.0f);
            imgIcon.setScaleX(-1.0f);
            txtTitle.setScaleX(-1.0f);
            txtContent.setScaleX(-1.0f);
            txtDate.setScaleX(-1.0f);
        } else {
            convertView.setScaleX(1.0f);
            imgIcon.setScaleX(1.0f);
            txtTitle.setScaleX(1.0f);
            txtContent.setScaleX(1.0f);
            txtDate.setScaleX(1.0f);
        }
        return convertView;
    }
}