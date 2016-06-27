package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.ImageActivity;
import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.adapters.ImageAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by golde on 3/10/2016.
 */
public class ImageFragment extends Fragment {
    // image header bar
    public RelativeLayout image_header_bar;
    public TextView image_header_title;
    public ImageView image_header_mark;
    public Button btn_song, btn_video;

    public GridView image_container;

    public DisplayMetrics dm;
    public int WIDTH, HEIGHT;
    public ArrayList<Drawable> imageAry = new ArrayList<Drawable>();

    public ImageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);

        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;

        for (int i = R.drawable.item_image14; i <= R.drawable.item_image31; i++) {
            imageAry.add(getResources().getDrawable(i));
        }

        image_header_bar = (RelativeLayout) rootView.findViewById(R.id.image_header_bar);
        image_header_title = (TextView) rootView.findViewById(R.id.image_header_title);
        image_header_mark = (ImageView) rootView.findViewById(R.id.image_header_mark);

        image_container = (GridView) rootView.findViewById(R.id.image_container);
        image_container.setNumColumns(4);
        image_container.setAdapter(new ImageAdapter(getActivity(), imageAry, WIDTH / 4 - 20, WIDTH / 4 - 20));
        image_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().startActivity(new Intent(getActivity(), ImageActivity.class));
            }
        });

        btn_song = (Button) rootView.findViewById(R.id.btn_song);
        btn_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.gotoNextFragment(new SongFragment(), getActivity());
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
            image_header_bar.setScaleX(-1.0f);
            image_header_title.setScaleX(-1.0f);
            image_header_mark.setScaleX(-1.0f);
            btn_song.setScaleX(-1.0f);
            btn_video.setScaleX(-1.0f);
        } else {
            image_header_bar.setScaleX(1.0f);
            image_header_title.setScaleX(1.0f);
            image_header_mark.setScaleX(1.0f);
            btn_song.setScaleX(1.0f);
            btn_video.setScaleX(1.0f);
        }
    }
}
