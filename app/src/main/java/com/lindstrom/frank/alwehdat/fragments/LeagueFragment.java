package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.adapters.MatchLeaguePageAdapter;
import com.lindstrom.frank.alwehdat.adapters.MatchTabAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/12/2016.
 */
public class LeagueFragment extends Fragment {
    public RelativeLayout league_header_bar;
    public ImageView league_header_image;
    public TextView league_header_title;
    public ImageView league_header_mark;
    public Gallery tabBar, league_page_container;
    public int[] tab_items = new int[] {R.string.match_tab_item_week, R.string.match_tab_item_ranking, R.string.match_tab_item_opponents, R.string.match_tab_item_summary};

    public LeagueFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_league, container, false);

        DataManager.prevFragment = 1;

        league_header_bar = (RelativeLayout) rootView.findViewById(R.id.league_header_bar);
        league_header_image = (ImageView) rootView.findViewById(R.id.league_header_image);
        league_header_title = (TextView) rootView.findViewById(R.id.league_header_title);
        league_header_mark = (ImageView) rootView.findViewById(R.id.league_header_mark);

        tabBar = (Gallery) rootView.findViewById(R.id.tab_container);
        tabBar.setCallbackDuringFling(true);
        tabBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                league_page_container.setSelection(position, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        tabBar.setAdapter(new MatchTabAdapter(getActivity(), tab_items));

        league_page_container = (Gallery) rootView.findViewById(R.id.league_page_container);
        league_page_container.setCallbackDuringFling(true);
        league_page_container.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tabBar.setSelection(position, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        league_page_container.setAdapter(new MatchLeaguePageAdapter(getActivity(), new int[] {R.layout.match_week_layout, R.layout.match_league_ranking_layout, R.layout.match_opponenets_layout, R.layout.match_summary_layout}));

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            league_header_bar.setScaleX(-1.0f);
            league_header_title.setScaleX(-1.0f);
            league_header_mark.setScaleX(-1.0f);
            tabBar.setScaleX(-1.0f);
        } else {
            league_header_bar.setScaleX(1.0f);
            league_header_title.setScaleX(1.0f);
            league_header_mark.setScaleX(1.0f);
            tabBar.setScaleX(1.0f);
        }
        return rootView;
    }
}
