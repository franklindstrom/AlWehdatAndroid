package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/12/2016.
 */
public class DownloadFragment extends Fragment {
    public View btn_download_image, btn_download_video, btn_download_song;
    public ImageView btn_download_image_icon, btn_download_video_icon, btn_download_song_icon;
    public TextView btn_download_image_title, btn_download_video_title, btn_download_song_title;
    public TextView btn_download_image_counts, btn_download_video_counts, btn_download_song_counts;

    public DownloadFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_download, container, false);

        DataManager.prevFragment = 1;

        btn_download_image = rootView.findViewById(R.id.btn_download_image);
        btn_download_video = rootView.findViewById(R.id.btn_download_video);
        btn_download_song = rootView.findViewById(R.id.btn_download_song);
        btn_download_image_icon = (ImageView) rootView.findViewById(R.id.btn_download_image_icon);
        btn_download_video_icon = (ImageView) rootView.findViewById(R.id.btn_download_video_icon);
        btn_download_song_icon = (ImageView) rootView.findViewById(R.id.btn_download_song_icon);
        btn_download_image_title = (TextView) rootView.findViewById(R.id.btn_download_image_title);
        btn_download_video_title = (TextView) rootView.findViewById(R.id.btn_download_video_title);
        btn_download_song_title = (TextView) rootView.findViewById(R.id.btn_download_song_title);
        btn_download_image_counts = (TextView) rootView.findViewById(R.id.btn_download_image_counts);
        btn_download_video_counts = (TextView) rootView.findViewById(R.id.btn_download_video_counts);
        btn_download_song_counts = (TextView) rootView.findViewById(R.id.btn_download_song_counts);

        onUpdateUI();
        return rootView;
    }
    
    // update UI via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            btn_download_image.setScaleX(-1.0f);
            btn_download_video.setScaleX(-1.0f);
            btn_download_song.setScaleX(-1.0f);
            btn_download_image_icon.setScaleX(-1.0f);
            btn_download_video_icon.setScaleX(-1.0f);
            btn_download_song_icon.setScaleX(-1.0f);
            btn_download_image_title.setScaleX(-1.0f);
            btn_download_video_title.setScaleX(-1.0f);
            btn_download_song_title.setScaleX(-1.0f);
            btn_download_image_counts.setScaleX(-1.0f);
            btn_download_video_counts.setScaleX(-1.0f);
            btn_download_song_counts.setScaleX(-1.0f);
        } else {
            btn_download_image.setScaleX(1.0f);
            btn_download_video.setScaleX(1.0f);
            btn_download_song.setScaleX(1.0f);
            btn_download_image_icon.setScaleX(1.0f);
            btn_download_video_icon.setScaleX(1.0f);
            btn_download_song_icon.setScaleX(1.0f);
            btn_download_image_title.setScaleX(1.0f);
            btn_download_video_title.setScaleX(1.0f);
            btn_download_song_title.setScaleX(1.0f);
            btn_download_image_counts.setScaleX(1.0f);
            btn_download_video_counts.setScaleX(1.0f);
            btn_download_song_counts.setScaleX(1.0f);
        }
    }
}
