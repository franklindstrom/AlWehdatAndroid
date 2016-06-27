package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.adapters.SquadListAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/11/2016.
 */
public class SquadFragment extends Fragment {
    public TextView midfielder_title;
    public ListView squad_player_list, squad_midfielder_list;

    public SquadFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_squad, container, false);

        DataManager.prevFragment = 1;

        midfielder_title = (TextView) rootView.findViewById(R.id.midfielder_title);

        squad_player_list = (ListView) rootView.findViewById(R.id.squad_player_list);
        squad_player_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataManager.prevFragment = 6;
                DataManager.selectedPlayerType = 0;
                DataManager.selectedPlayerIndex = position;
                DataManager.gotoNextFragment(new SquadPlayerInfoFragment(), getActivity());
            }
        });

        DataManager.squadPhotoImages.clear();
        for (int i = R.drawable.squad_photo_1; i <= R.drawable.squad_photo_4; i++) {
            DataManager.squadPhotoImages.add(getResources().getDrawable(i));
        }

        DataManager.squadNames = new int[]{R.string.squad_item_name_1, R.string.squad_item_name_2, R.string.squad_item_name_3, R.string.squad_item_name_4};
        DataManager.squadYears = new int[]{R.string.squad_item_age_1, R.string.squad_item_age_2, R.string.squad_item_age_3, R.string.squad_item_age_4};
        DataManager.squadNumbers = new int[]{R.string.squad_item_number_1, R.string.squad_item_number_2, R.string.squad_item_number_3, R.string.squad_item_number_4};

        squad_player_list.setAdapter(new SquadListAdapter(getActivity(), DataManager.squadPhotoImages, DataManager.squadNames, DataManager.squadYears, DataManager.squadNumbers));

        squad_midfielder_list = (ListView) rootView.findViewById(R.id.squad_midfielder_list);
        DataManager.squadMidFielderPhotoImages.clear();
        DataManager.squadMidFielderPhotoImages.add(getResources().getDrawable(R.drawable.squad_photo_5));

        DataManager.squadMidFielderNames = new int[]{R.string.squad_item_name_5};
        DataManager.squadMidFielderYears = new int[]{R.string.squad_item_age_5};
        DataManager.squadMidFielderNumbers = new int[]{R.string.squad_item_number_5};
        squad_midfielder_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataManager.prevFragment = 6;
                DataManager.selectedPlayerType = 1;
                DataManager.selectedPlayerIndex = position;
                DataManager.gotoNextFragment(new SquadPlayerInfoFragment(), getActivity());
            }
        });

        squad_midfielder_list.setAdapter(new SquadListAdapter(getActivity(), DataManager.squadMidFielderPhotoImages, DataManager.squadMidFielderNames, DataManager.squadMidFielderYears, DataManager.squadMidFielderNumbers));

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            midfielder_title.setGravity(Gravity.LEFT);
        } else {
            midfielder_title.setGravity(Gravity.RIGHT);
        }
        return rootView;
    }
}
