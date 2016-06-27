package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.adapters.SocialMediaAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by golde on 3/15/2016.
 */
public class SocialWallFragment extends Fragment {
    // social media header bar
    public RelativeLayout social_media_header;
    public TextView social_media_header_title;
    public ImageView social_media_header_mark;
    public Button btn_goto_news;

    // social media container
    public RelativeLayout social_media_container;
    public ListView social_media_list;

    public ArrayList<Drawable> iconAry = new ArrayList<Drawable>();
    public ArrayList<Drawable> photoAry = new ArrayList<Drawable>();
    public ArrayList<Drawable> imageAry = new ArrayList<Drawable>();

    public SocialWallFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_wall, container, false);

        social_media_header = (RelativeLayout) rootView.findViewById(R.id.social_media_header_bar);
        social_media_header_title = (TextView) rootView.findViewById(R.id.social_media_header_title);
        social_media_header_mark = (ImageView) rootView.findViewById(R.id.social_media_header_mark);

        social_media_container = (RelativeLayout) rootView.findViewById(R.id.social_media_container);
        social_media_list = (ListView) rootView.findViewById(R.id.social_media_list);

        iconAry.add(getResources().getDrawable(R.drawable.social_item_icon_facebook));
        iconAry.add(getResources().getDrawable(R.drawable.social_item_icon_twitter));

        photoAry.add(getResources().getDrawable(R.drawable.social_item_photo_1));
        photoAry.add(getResources().getDrawable(R.drawable.social_item_photo_2));

        imageAry.add(getResources().getDrawable(R.drawable.social_item_content_image_1));
        imageAry.add(getResources().getDrawable(R.drawable.social_item_content_image_2));
        social_media_list.setAdapter(new SocialMediaAdapter(getActivity(), iconAry, photoAry, imageAry,
                new String[]{getResources().getString(R.string.social_media_item_title_1), getResources().getString(R.string.social_media_title_2)},
                new String[]{"@wehdat", "@ashafee1980"},
                new String[]{getResources().getString(R.string.social_media_item_content_1), getResources().getString(R.string.social_media_item_content_2)},
                new String[]{"63", "58"},
                new String[]{"14", "9"},
                new String[]{"12", "8"}));
        social_media_list.setItemsCanFocus(false);

        btn_goto_news = (Button) rootView.findViewById(R.id.btn_goto_news);
        btn_goto_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.gotoNextFragment(new NewsFragment(), getActivity());
            }
        });

        onUpdateUI();
        return rootView;
    }

    // update UI via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            social_media_header.setScaleX(-1.0f);
            social_media_header_title.setScaleX(-1.0f);
            social_media_header_mark.setScaleX(-1.0f);
            btn_goto_news.setScaleX(-1.0f);
            social_media_container.setScaleX(-1.0f);
        } else {
            social_media_header.setScaleX(1.0f);
            social_media_header_title.setScaleX(1.0f);
            social_media_header_mark.setScaleX(1.0f);
            btn_goto_news.setScaleX(1.0f);
            social_media_container.setScaleX(1.0f);
        }
    }
}
