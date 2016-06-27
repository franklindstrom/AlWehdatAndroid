package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

/**
 * Created by golde on 3/15/2016.
 */
public class SongListAdapter extends BaseAdapter {
    public Context context;
    public int[] imageAry;
    public int[] titleAry;
    public int[] nameAry;
    public String[] durationAry;

    public SongListAdapter(Context context, int[] imageAry, int[] titleAry, int[] nameAry, String[] durationAry) {
        this.context = context;
        this.imageAry = imageAry;
        this.titleAry = titleAry;
        this.nameAry = nameAry;
        this.durationAry = durationAry;
    }

    @Override
    public int getCount() {
        return imageAry.length;
    }

    @Override
    public Object getItem(int position) {
        return imageAry[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.song_list_items, null);

        ImageView item_image = (ImageView) convertView.findViewById(R.id.song_list_item_image);
        TextView item_title = (TextView) convertView.findViewById(R.id.song_list_item_title);
        TextView item_name = (TextView) convertView.findViewById(R.id.song_list_item_name);
        TextView item_duration = (TextView) convertView.findViewById(R.id.song_list_item_duration);

        item_image.setBackgroundResource(imageAry[position]);
        item_title.setText(titleAry[position]);
        item_name.setText(nameAry[position]);
        item_duration.setText(durationAry[position]);
        
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            convertView.setScaleX(-1.0f);
            item_image.setScaleX(-1.0f);
            item_title.setScaleX(-1.0f);
            item_name.setScaleX(-1.0f);
            item_duration.setScaleX(-1.0f);
        } else {
            convertView.setScaleX(1.0f);
            item_image.setScaleX(1.0f);
            item_title.setScaleX(1.0f);
            item_name.setScaleX(1.0f);
            item_duration.setScaleX(1.0f);
        }

        return convertView;
    }
}
