package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.lindstrom.frank.alwehdat.VideoPlayActivity;
import com.lindstrom.frank.alwehdat.adapters.DefaultListAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by golde on 3/10/2016.
 */
public class VideoFragment extends Fragment {
    // video header bar
    public RelativeLayout video_header_bar;
    public TextView video_header_title;
    public ImageView video_header_mark;
    public Button btn_image, btn_song;
    
    public ListView video_list;

    public ArrayList<Drawable> imageAry = new ArrayList<Drawable>();

    public VideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        video_header_bar = (RelativeLayout) rootView.findViewById(R.id.video_header_bar);
        video_header_title = (TextView) rootView.findViewById(R.id.video_header_title);
        video_header_mark = (ImageView) rootView.findViewById(R.id.video_header_mark);
        
        video_list = (ListView) rootView.findViewById(R.id.video_list);
        video_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().startActivity(new Intent(getActivity(), VideoPlayActivity.class));
            }
        });

        imageAry.add(getResources().getDrawable(R.drawable.item_image30));
        imageAry.add(getResources().getDrawable(R.drawable.item_image11));
        imageAry.add(getResources().getDrawable(R.drawable.item_image12));
        imageAry.add(getResources().getDrawable(R.drawable.item_image31));
        imageAry.add(getResources().getDrawable(R.drawable.item_image13));
        video_list.setAdapter(new DefaultListAdapter(getActivity(), imageAry,
                new int[]{R.string.video_item_title_1, R.string.video_item_title_2, R.string.video_item_title_3, R.string.video_item_title_4, R.string.video_item_title_5},
                new int[]{R.string.video_item_content_1, R.string.video_item_content_2, R.string.video_item_content_3, R.string.video_item_content_4, R.string.video_item_content_5},
                new int[]{R.string.video_item_date_1, R.string.video_item_date_2, R.string.video_item_date_3, R.string.video_item_date_4, R.string.video_item_date_5}));

        btn_image = (Button) rootView.findViewById(R.id.btn_image);
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.loadLocaleSetting(getActivity());
                DataManager.gotoNextFragment(new ImageFragment(), getActivity());
            }
        });

        btn_song = (Button) rootView.findViewById(R.id.btn_song);
        btn_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.loadLocaleSetting(getActivity());
                DataManager.gotoNextFragment(new SongFragment(), getActivity());
            }
        });

        onUpdateUI();
        return rootView;
    }

    // update UI via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            video_header_bar.setScaleX(-1.0f);
            video_header_title.setScaleX(-1.0f);
            video_header_mark.setScaleX(-1.0f);
            btn_image.setScaleX(-1.0f);
            btn_song.setScaleX(-1.0f);
        } else {
            video_header_bar.setScaleX(1.0f);
            video_header_title.setScaleX(1.0f);
            video_header_mark.setScaleX(1.0f);
            btn_image.setScaleX(1.0f);
            btn_song.setScaleX(1.0f);
        }
    }
}
