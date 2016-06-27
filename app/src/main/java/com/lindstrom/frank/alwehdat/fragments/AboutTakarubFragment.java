package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/10/2016.
 */
public class AboutTakarubFragment extends Fragment {
    public SharedPreferences database;
    public TextView about_text;

    public AboutTakarubFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_takarub, container, false);

        DataManager.prevFragment = 1;

        about_text = (TextView) rootView.findViewById(R.id.txt_about_takarub);

        database = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (database.getString("lang", "en").equals("ar")) {
            about_text.setGravity(Gravity.RIGHT);
        } else {
            about_text.setGravity(Gravity.LEFT);
        }
        return rootView;
    }
}
