package com.lindstrom.frank.alwehdat.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/27/2016.
 */

public class SlideMenuAdapter extends BaseAdapter {
    public Context context;
    public int[] iconAry;
    public int[] titleAry;

    public SlideMenuAdapter(Context context, int[] iconAry, int[] titleAry) {
        this.context = context;
        this.iconAry = iconAry;
        this.titleAry = titleAry;
    }

    @Override
    public int getCount() {
        return iconAry.length;
    }

    @Override
    public Object getItem(int position) {
        return iconAry[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.menu_items, null);

        ImageView icon = (ImageView) convertView.findViewById(R.id.menu_icon);
        TextView title = (TextView) convertView.findViewById(R.id.menu_title);

//        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "HelveticaNeueW23forSKY-Reg.ttf");

        icon.setImageResource(iconAry[position]);
        title.setText(titleAry[position]);
//        title.setTypeface(custom_font);

        System.out.println("current device is " + DataManager.device_info);
        if (DataManager.device_info.equals("HUAWEI") && PreferenceManager.getDefaultSharedPreferences(context).getString("lang", "en").equals("ar")) {
            icon.setScaleX(-1.0f);
            title.setScaleX(-1.0f);
        } else {
            icon.setScaleX(1.0f);
            title.setScaleX(1.0f);
        }
        return convertView;
    }
}