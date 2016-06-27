package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/10/2016.
 */
public class AboutWehdatFragment extends Fragment {
    public SharedPreferences database;
    public LinearLayout bottom_layout;
    public ImageView photo;
    public TextView top_part, bottom_part;

    public AboutWehdatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_wehdat, container, false);

        DataManager.prevFragment = 1;

        bottom_layout = (LinearLayout) rootView.findViewById(R.id.bottom_layout);
        photo = (ImageView) rootView.findViewById(R.id.photo);
        top_part = (TextView) rootView.findViewById(R.id.top_part);
        bottom_part = (TextView) rootView.findViewById(R.id.bottom_part);

        database = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (database.getString("lang", "en").equals("ar")) {
            bottom_layout.setScaleX(-1.0f);
            photo.setScaleX(-1.0f);
            bottom_part.setScaleX(-1.0f);

            top_part.setGravity(Gravity.RIGHT);
            bottom_part.setGravity(Gravity.RIGHT);
        } else {
            bottom_layout.setScaleX(1.0f);
            photo.setScaleX(1.0f);
            bottom_part.setScaleX(1.0f);

            top_part.setGravity(Gravity.LEFT);
            bottom_part.setGravity(Gravity.LEFT);
        }
        return rootView;
    }
}
