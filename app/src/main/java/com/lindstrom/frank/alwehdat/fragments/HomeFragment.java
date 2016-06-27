package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {
//    public Typeface custom_font;

    // home title bar
    public RelativeLayout home_title_bar;
    public TextView home_title, home_details;
    public ImageView home_mark;

    // item news
    public LinearLayout item_news_title_bar;
    public TextView item_news_title;
    public ImageView item_news_icon;
    public RelativeLayout item_news_content;
    public TextView item_news_topic, item_news_details;
    public ImageView btn_prev_news, btn_next_news;

    // item image
    public RelativeLayout item_image_title_bar;
    public TextView item_image_title;
    public ImageView item_image_icon;
    public RelativeLayout item_image_content;
    public TextView item_image_topic, item_image_details;

    // item video
    public RelativeLayout item_video_title_bar;
    public TextView item_video_title;
    public ImageView item_video_icon;
    public RelativeLayout item_video_content;
    public TextView item_video_topic, item_video_details;

    // ads bar
    public ImageView team_mark_1, team_mark_2;
    public TextView ads_title, ads_time, team_name_1, team_name_2;

    public RelativeLayout item_news, item_images, item_videos;
    
    public HomeFragment() {
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        DataManager.prevFragment = 0;
//        custom_font = Typeface.createFromAsset(getActivity().getAssets(), "HelveticaNeueW23forSKY-Reg.ttf");

        home_title_bar = (RelativeLayout) rootView.findViewById(R.id.home_title_bar);
        home_title = (TextView) rootView.findViewById(R.id.home_title);
        home_details = (TextView) rootView.findViewById(R.id.home_details);
        home_mark = (ImageView) rootView.findViewById(R.id.home_mark);

        item_news_title_bar = (LinearLayout) rootView.findViewById(R.id.item_news_title_bar);
        item_news_title = (TextView) rootView.findViewById(R.id.item_news_title);
        item_news_icon = (ImageView) rootView.findViewById(R.id.item_news_icon);
        item_news_content = (RelativeLayout) rootView.findViewById(R.id.item_news_content);
        item_news_topic = (TextView) rootView.findViewById(R.id.item_news_topic);
        item_news_details = (TextView) rootView.findViewById(R.id.item_news_details);
        btn_prev_news = (ImageView) rootView.findViewById(R.id.btn_prev_news);
        btn_next_news = (ImageView) rootView.findViewById(R.id.btn_next_news);

        item_image_title_bar = (RelativeLayout) rootView.findViewById(R.id.item_image_title_bar);
        item_image_title = (TextView) rootView.findViewById(R.id.item_image_title);
        item_image_icon = (ImageView) rootView.findViewById(R.id.item_image_icon);
        item_image_content = (RelativeLayout) rootView.findViewById(R.id.item_image_content);
        item_image_topic = (TextView) rootView.findViewById(R.id.item_image_topic);
        item_image_details = (TextView) rootView.findViewById(R.id.item_image_details);

        item_video_title_bar = (RelativeLayout) rootView.findViewById(R.id.item_video_title_bar);
        item_video_title = (TextView) rootView.findViewById(R.id.item_video_title);
        item_video_icon = (ImageView) rootView.findViewById(R.id.item_video_icon);
        item_video_content = (RelativeLayout) rootView.findViewById(R.id.item_video_content);
        item_video_topic = (TextView) rootView.findViewById(R.id.item_video_topic);
        item_video_details = (TextView) rootView.findViewById(R.id.item_video_details);

        team_mark_1 = (ImageView) rootView.findViewById(R.id.team_mark_1);
        team_mark_2 = (ImageView) rootView.findViewById(R.id.team_mark_2);
        ads_title = (TextView) rootView.findViewById(R.id.ads_title);
        ads_time = (TextView) rootView.findViewById(R.id.ads_time);
        team_name_1 = (TextView) rootView.findViewById(R.id.team_name_1);
        team_name_2 = (TextView) rootView.findViewById(R.id.team_name_2);

//        onSetCustomFonts();
        onUpdateUI();

        item_news = (RelativeLayout) rootView.findViewById(R.id.item_news);
        item_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.prevFragment = 1;
                DataManager.news_from = 0;
                MainActivity.displayPage(3);
            }
        });

        item_images = (RelativeLayout) rootView.findViewById(R.id.item_images);
        item_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.prevFragment = 1;
                DataManager.setSelectedMenuItemIndex(2);
                MainActivity.customActionBar();
                DataManager.gotoNextFragment(new ImageFragment(), getActivity());
            }
        });

        item_videos = (RelativeLayout) rootView.findViewById(R.id.item_videos);
        item_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.prevFragment = 1;
                DataManager.setSelectedMenuItemIndex(2);
                MainActivity.customActionBar();
                DataManager.gotoNextFragment(new VideoFragment(), getActivity());
            }
        });
        return rootView;
    }

//    // set custom fonts to all of the text view
//    public void onSetCustomFonts() {
//        // normal
////        home_details.setTypeface(custom_font);
////        item_news_title.setTypeface(custom_font);
////        item_news_details.setTypeface(custom_font);
////        item_image_title.setTypeface(custom_font);
////        item_video_title.setTypeface(custom_font);
////        ads_time.setTypeface(custom_font);
//
//        // bold
////        home_title.setTypeface(custom_font, Typeface.BOLD);
////        item_news_topic.setTypeface(custom_font, Typeface.BOLD);
////        item_image_topic.setTypeface(custom_font, Typeface.BOLD);
////        item_image_details.setTypeface(custom_font, Typeface.BOLD);
////        item_video_topic.setTypeface(custom_font, Typeface.BOLD);
////        item_video_details.setTypeface(custom_font, Typeface.BOLD);
////        ads_title.setTypeface(custom_font, Typeface.BOLD);
////        team_name_1.setTypeface(custom_font, Typeface.BOLD);
////        team_name_2.setTypeface(custom_font, Typeface.BOLD);
//    }

    // update ui via locale language setting
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            home_title_bar.setScaleX(-1.0f);
            home_title.setScaleX(-1.0f);
            home_details.setScaleX(-1.0f);
            home_mark.setScaleX(-1.0f);

            item_news_title_bar.setScaleX(-1.0f);
            item_news_title.setScaleX(-1.0f);
            item_news_icon.setScaleX(-1.0f);
            item_news_content.setScaleX(-1.0f);
            item_news_topic.setScaleX(-1.0f);
            item_news_details.setScaleX(-1.0f);

            item_image_title_bar.setScaleX(-1.0f);
            item_image_title.setScaleX(-1.0f);
            item_image_icon.setScaleX(-1.0f);
            item_image_content.setScaleX(-1.0f);
            item_image_topic.setScaleX(-1.0f);
            item_image_details.setScaleX(-1.0f);

            item_video_title_bar.setScaleX(-1.0f);
            item_video_title.setScaleX(-1.0f);
            item_video_icon.setScaleX(-1.0f);
            item_video_content.setScaleX(-1.0f);
            item_video_topic.setScaleX(-1.0f);
            item_video_details.setScaleX(-1.0f);
        } else {
            home_title_bar.setScaleX(1.0f);
            home_title.setScaleX(1.0f);
            home_details.setScaleX(1.0f);
            home_mark.setScaleX(1.0f);

            item_news_title_bar.setScaleX(1.0f);
            item_news_title.setScaleX(1.0f);
            item_news_icon.setScaleX(1.0f);
            item_news_content.setScaleX(1.0f);
            item_news_topic.setScaleX(1.0f);
            item_news_details.setScaleX(1.0f);

            item_image_title_bar.setScaleX(1.0f);
            item_image_title.setScaleX(1.0f);
            item_image_icon.setScaleX(1.0f);
            item_image_content.setScaleX(1.0f);
            item_image_topic.setScaleX(1.0f);
            item_image_details.setScaleX(1.0f);

            item_video_title_bar.setScaleX(1.0f);
            item_video_title.setScaleX(1.0f);
            item_video_icon.setScaleX(1.0f);
            item_video_content.setScaleX(1.0f);
            item_video_topic.setScaleX(1.0f);
            item_video_details.setScaleX(1.0f);
        }
    }
}
