package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.SongPlayActivity;
import com.lindstrom.frank.alwehdat.adapters.SongListAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/10/2016.
 */
public class SongFragment extends Fragment {
    // song header bar
    public RelativeLayout song_header_bar;
    public TextView song_header_title;
    public ImageView song_header_mark;
    public Button btn_image, btn_video;

    public ListView song_container;

    public SongFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_song, container, false);

        song_header_bar = (RelativeLayout) rootView.findViewById(R.id.song_header_bar);
        song_header_title = (TextView) rootView.findViewById(R.id.song_header_title);
        song_header_mark = (ImageView) rootView.findViewById(R.id.song_header_mark);

        song_container = (ListView) rootView.findViewById(R.id.song_container);
        song_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().startActivity(new Intent(getActivity(), SongPlayActivity.class));
            }
        });
        song_container.setAdapter(new SongListAdapter(getActivity(),
                new int[] {R.drawable.item_image16, R.drawable.item_image20, R.drawable.item_image24, R.drawable.item_image28, R.drawable.item_image26, R.drawable.item_image27},
                new int[] {R.string.song_item_title_1, R.string.song_item_title_2, R.string.song_item_title_3, R.string.song_item_title_4, R.string.song_item_title_5, R.string.song_item_title_6},
                new int[] {R.string.song_item_name_1, R.string.song_item_name_2, R.string.song_item_name_3, R.string.song_item_name_4, R.string.song_item_name_5, R.string.song_item_name_6},
                new String[] {"2:30", "1:15", "2:15", "0:51", "5:27", "1:19"}));

        btn_image = (Button) rootView.findViewById(R.id.btn_image);
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.gotoNextFragment(new ImageFragment(), getActivity());
            }
        });

        btn_video = (Button) rootView.findViewById(R.id.btn_video);
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.gotoNextFragment(new VideoFragment(), getActivity());
            }
        });

        onUpdateUI();
        return rootView;
    }

    // update UI via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            song_header_bar.setScaleX(-1.0f);
            song_header_title.setScaleX(-1.0f);
            song_header_mark.setScaleX(-1.0f);
            btn_image.setScaleX(-1.0f);
            btn_video.setScaleX(-1.0f);
        } else {
            song_header_bar.setScaleX(1.0f);
            song_header_title.setScaleX(1.0f);
            song_header_mark.setScaleX(1.0f);
            btn_image.setScaleX(1.0f);
            btn_video.setScaleX(1.0f);
        }
    }
}
