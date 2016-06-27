package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
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
import com.lindstrom.frank.alwehdat.adapters.DefaultListAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by golde on 3/15/2016.
 */
public class NewsFragment extends Fragment {
    // news header bar
    public RelativeLayout news_header_bar;
    public TextView news_header_title;
    public ImageView news_header_mark;
    public Button btn_social_media;

    public ListView news_list;

    public ArrayList<Drawable> imageAry = new ArrayList<Drawable>();

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        DataManager.prevFragment = 1;

        news_header_bar = (RelativeLayout) rootView.findViewById(R.id.news_header_bar);
        news_header_title = (TextView) rootView.findViewById(R.id.news_header_title);
        news_header_mark = (ImageView) rootView.findViewById(R.id.news_header_mark);

        news_header_title.setText(DataManager.news_from == 0 ? R.string.news_header_title : R.string.title_sport);

        news_list = (ListView) rootView.findViewById(R.id.news_list);
        news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataManager.prevFragment = 3;
                DataManager.gotoNextFragment(new ShowNewsFragment(), getActivity());
            }
        });

        imageAry.add(getResources().getDrawable(R.drawable.item_image30));
        imageAry.add(getResources().getDrawable(R.drawable.item_image11));
        imageAry.add(getResources().getDrawable(R.drawable.item_image12));
        imageAry.add(getResources().getDrawable(R.drawable.item_image31));
        imageAry.add(getResources().getDrawable(R.drawable.item_image13));
        news_list.setAdapter(new DefaultListAdapter(getActivity(), imageAry,
                new int[]{R.string.news_item_title_1, R.string.news_item_title_2, R.string.news_item_title_3, R.string.news_item_title_4, R.string.news_item_title_5},
                new int[]{R.string.news_item_content_1, R.string.news_item_content_2, R.string.news_item_content_3, R.string.news_item_content_4, R.string.news_item_content_5},
                new int[]{R.string.news_item_date_1, R.string.news_item_date_2, R.string.news_item_date_3, R.string.news_item_date_4, R.string.news_item_date_5}));

        btn_social_media = (Button) rootView.findViewById(R.id.btn_social_media);
        btn_social_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.prevFragment = 3;
                DataManager.gotoNextFragment(new SocialWallFragment(), getActivity());
            }
        });

        onUpdateUI();
        return rootView;
    }

    // update UI via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            news_header_bar.setScaleX(-1.0f);
            news_header_title.setScaleX(-1.0f);
            news_header_mark.setScaleX(-1.0f);
            btn_social_media.setScaleX(-1.0f);
        } else {
            news_header_bar.setScaleX(1.0f);
            news_header_title.setScaleX(1.0f);
            news_header_mark.setScaleX(1.0f);
            btn_social_media.setScaleX(1.0f);
        }
    }
}
