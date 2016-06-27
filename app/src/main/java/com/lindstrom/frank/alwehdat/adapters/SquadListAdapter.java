package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

import java.util.ArrayList;

/**
 * Created by golde on 3/11/2016.
 */
public class SquadListAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Drawable> photoAry;
    public int[] nameAry;
    public int[] yearsAry;
    public int[] numberAry;

    public SquadListAdapter(Context context, ArrayList<Drawable> photoAry, int[] nameAry, int[] yearsAry, int[] numberAry) {
        this.context = context;
        this.photoAry = photoAry;
        this.nameAry = nameAry;
        this.yearsAry = yearsAry;
        this.numberAry = numberAry;
    }

    @Override
    public int getCount() {
        return nameAry.length;
    }

    @Override
    public Object getItem(int position) {
        return nameAry[position];
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
            convertView = mInflater.inflate(R.layout.squad_list_items, null);
        }

        final ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.squad_player_photo);
        final TextView txtFirstName = (TextView) convertView.findViewById(R.id.squad_player_first_name);
        final TextView txtLastName = (TextView) convertView.findViewById(R.id.squad_player_last_name);
        final TextView txtYear = (TextView) convertView.findViewById(R.id.squad_player_years);
        final TextView txtNumber = (TextView) convertView.findViewById(R.id.squad_player_number);

        String first_name = context.getResources().getString(nameAry[position]).split(" ")[0];
        String last_name = context.getResources().getString(nameAry[position]).split(" ")[1];

        imgPhoto.setBackgroundDrawable(photoAry.get(position));
        txtFirstName.setText(first_name);
        txtLastName.setText(last_name);
        txtYear.setText(yearsAry[position]);
        txtNumber.setText(numberAry[position]);

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            convertView.setScaleX(-1.0f);
            imgPhoto.setScaleX(-1.0f);
            txtFirstName.setScaleX(-1.0f);
            txtLastName.setScaleX(-1.0f);
            txtYear.setScaleX(-1.0f);
            txtNumber.setScaleX(-1.0f);
        } else {
            convertView.setScaleX(1.0f);
            imgPhoto.setScaleX(1.0f);
            txtFirstName.setScaleX(1.0f);
            txtLastName.setScaleX(1.0f);
            txtYear.setScaleX(1.0f);
            txtNumber.setScaleX(1.0f);
        }
        return convertView;
    }
}
