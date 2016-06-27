package com.lindstrom.frank.alwehdat.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

import java.util.ArrayList;

/**
 * Created by golde on 3/15/2016.
 */
public class ShowNewsFragment extends Fragment {
    public RelativeLayout news_content_footer_bar;
    public TextView news_date;
    public ImageView btn_prev_news_show, btn_next_news_show, btn_share_news;
    public ArrayList<Integer> selected_index_ary = new ArrayList<Integer>();

    public ShowNewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_show_news, container, false);

        news_content_footer_bar = (RelativeLayout) rootView.findViewById(R.id.news_content_footer_bar);
        news_date = (TextView) rootView.findViewById(R.id.news_date);

        btn_prev_news_show = (ImageView) rootView.findViewById(R.id.btn_prev_news_show);
        btn_next_news_show = (ImageView) rootView.findViewById(R.id.btn_next_news_show);
        btn_share_news = (ImageView) rootView.findViewById(R.id.btn_share_news);

        btn_prev_news_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sadfsldkjfslkd
            }
        });

        btn_next_news_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // asdlksdjflkjsdf
            }
        });

        btn_share_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getActivity().getLayoutInflater().inflate(R.layout.share_layout, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setView(view);
                final AlertDialog dialog = alert.create();
                dialog.show();

                View share_layout_title = view.findViewById(R.id.share_layout_title);
                View icon_facebook = view.findViewById(R.id.icon_facebook);
                View icon_twitter = view.findViewById(R.id.icon_twitter);
                View icon_whats_app = view.findViewById(R.id.icon_whats_app);
                View icon_google_plus = view.findViewById(R.id.icon_google_plus);
                View title_facebook = view.findViewById(R.id.title_facebook);
                View title_twitter = view.findViewById(R.id.title_twitter);
                View title_whats_app = view.findViewById(R.id.title_whats_app);
                View title_google_plus = view.findViewById(R.id.title_google_plus);
                View btn_share_with_facebook = view.findViewById(R.id.btn_share_with_facebook);
                View btn_share_with_twitter = view.findViewById(R.id.btn_share_with_twitter);
                View btn_share_with_whats_app = view.findViewById(R.id.btn_share_with_whats_app);
                View btn_share_with_google_plus = view.findViewById(R.id.btn_share_with_google_plus);
                View btn_confirm_share = view.findViewById(R.id.btn_confirm_share);

                btn_share_with_facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected_index_ary.contains(0)) {
                            v.setBackgroundColor(Color.parseColor("#EFEFEF"));
                            selected_index_ary.remove(selected_index_ary.indexOf(0));
                        } else {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                            selected_index_ary.add(0);
                        }
                    }
                });

                btn_share_with_twitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected_index_ary.contains(1)) {
                            v.setBackgroundColor(Color.parseColor("#EFEFEF"));
                            selected_index_ary.remove(selected_index_ary.indexOf(1));
                        } else {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                            selected_index_ary.add(1);
                        }
                    }
                });

                btn_share_with_whats_app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected_index_ary.contains(2)) {
                            v.setBackgroundColor(Color.parseColor("#EFEFEF"));
                            selected_index_ary.remove(selected_index_ary.indexOf(2));
                        } else {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                            selected_index_ary.add(2);
                        }
                    }
                });

                btn_share_with_google_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected_index_ary.contains(3)) {
                            v.setBackgroundColor(Color.parseColor("#EFEFEF"));
                            selected_index_ary.remove(selected_index_ary.indexOf(3));
                        } else {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                            selected_index_ary.add(3);
                        }
                    }
                });

                btn_confirm_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                        dialog.dismiss();
                    }
                });

                if (MainActivity.database.getString("lang", "en").equals("ar")) {
                    view.setScaleX(-1.0f);
                    share_layout_title.setScaleX(-1.0f);
                    icon_facebook.setScaleX(-1.0f);
                    icon_twitter.setScaleX(-1.0f);
                    icon_whats_app.setScaleX(-1.0f);
                    icon_google_plus.setScaleX(-1.0f);
                    title_facebook.setScaleX(-1.0f);
                    title_twitter.setScaleX(-1.0f);
                    title_whats_app.setScaleX(-1.0f);
                    title_google_plus.setScaleX(-1.0f);
                    btn_confirm_share.setScaleX(-1.0f);
                } else {
                    view.setScaleX(1.0f);
                    share_layout_title.setScaleX(1.0f);
                    icon_facebook.setScaleX(1.0f);
                    icon_twitter.setScaleX(1.0f);
                    icon_whats_app.setScaleX(1.0f);
                    icon_google_plus.setScaleX(1.0f);
                    title_facebook.setScaleX(1.0f);
                    title_twitter.setScaleX(1.0f);
                    title_whats_app.setScaleX(1.0f);
                    title_google_plus.setScaleX(1.0f);
                    btn_confirm_share.setScaleX(1.0f);
                }
            }
        });

        onUpdateUI();
        return rootView;
    }

    // update UI via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            news_content_footer_bar.setScaleX(-1.0f);
            news_date.setScaleX(-1.0f);
            btn_share_news.setScaleX(-1.0f);
        } else {
            news_content_footer_bar.setScaleX(1.0f);
            news_date.setScaleX(1.0f);
            btn_share_news.setScaleX(1.0f);
        }
    }
}
