package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

/**
 * Created by golde on 3/13/2016.
 */
public class MatchLeaguePageAdapter extends BaseAdapter {

    public Context context;
    public int[] pageAry;

    public MatchLeaguePageAdapter(Context context, int[] pageAry) {
        this.context = context;
        this.pageAry = pageAry;
    }

    @Override
    public int getCount() {
        return this.pageAry.length;
    }

    @Override
    public Object getItem(int position) {
        return pageAry[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(pageAry[position], null);

        switch (position) {
            case 0:
                ListView match_week_list = (ListView) convertView.findViewById(R.id.match_week_list);
                match_week_list.setAdapter(new MatchWeekOpponenetsListAdapter(context,
                        new int[]{R.drawable.team_mark_ahli, R.drawable.team_mark_ramtha, R.drawable.team_mark_shabab_alordon, R.drawable.team_mark_faisaly, R.drawable.team_mark_that_rass_black, R.drawable.team_mark_hussain_irbid},
                        new int[]{R.drawable.team_mark_jazeera, R.drawable.team_mark_asala, R.drawable.team_mark_koforsom, R.drawable.team_mark_baqaa, R.drawable.mark_small, R.drawable.team_mark_quqasi},
                        new int[]{R.string.team_name_ahli, R.string.team_name_ramtha, R.string.team_name_shabab_alordon, R.string.team_name_faisaly, R.string.team_name_that_rass, R.string.team_name_hussian_irbid},
                        new int[]{R.string.team_name_jazeera, R.string.team_name_asala, R.string.team_name_koforsom, R.string.team_name_baqaa, R.string.team_name_wehdat, R.string.team_name_quqasi},
                        new String[]{"3", "2", "0", "4", "0", "1"},
                        new String[]{"1", "0", "1", "2", "1", "1"},
                        new int[]{
                                R.string.week_time_1,
                                R.string.week_time_2,
                                R.string.week_time_3,
                                R.string.week_time_4,
                                R.string.week_time_5,
                                R.string.week_time_6,
                        }, 0));
                break;
            case 1:
                View league_ranking_header_bar = convertView.findViewById(R.id.league_ranking_header_bar);
                TextView league_ranking_header_title_club = (TextView) convertView.findViewById(R.id.league_ranking_header_title_club);
                TextView league_ranking_header_title_week = (TextView) convertView.findViewById(R.id.league_ranking_header_title_week);
                TextView league_ranking_header_title_goals = (TextView) convertView.findViewById(R.id.league_ranking_header_title_goals);
                TextView league_ranking_header_title_points = (TextView) convertView.findViewById(R.id.league_ranking_header_title_points);

                if (MainActivity.database.getString("lang", "en").equals("ar")) {
                    league_ranking_header_bar.setScaleX(-1.0f);
                    league_ranking_header_title_club.setScaleX(-1.0f);
                    league_ranking_header_title_week.setScaleX(-1.0f);
                    league_ranking_header_title_goals.setScaleX(-1.0f);
                    league_ranking_header_title_points.setScaleX(-1.0f);

                    league_ranking_header_title_club.setGravity(Gravity.RIGHT);
                } else {
                    league_ranking_header_bar.setScaleX(1.0f);
                    league_ranking_header_title_club.setScaleX(1.0f);
                    league_ranking_header_title_week.setScaleX(1.0f);
                    league_ranking_header_title_goals.setScaleX(1.0f);
                    league_ranking_header_title_points.setScaleX(1.0f);

                    league_ranking_header_title_club.setGravity(Gravity.LEFT);
                }

                ListView match_ranking_list = (ListView) convertView.findViewById(R.id.match_ranking_list);
                match_ranking_list.setAdapter(new MatchRankingListAdapter(context,
                        new int[]{R.drawable.mark_small, R.drawable.team_mark_ramtha, R.drawable.team_mark_shabab_alordon, R.drawable.team_mark_koforsom, R.drawable.team_mark_asala, R.drawable.team_mark_faisaly, R.drawable.team_mark_hussain_irbid, R.drawable.team_mark_quqasi, R.drawable.team_mark_that_rass_black},
                        new int[]{R.string.team_name_wehdat, R.string.team_name_ramtha, R.string.team_name_shabab_alordon, R.string.team_name_koforsom, R.string.team_name_asala, R.string.team_name_faisaly, R.string.team_name_hussian_irbid, R.string.team_name_quqasi, R.string.team_name_that_rass},
                        new String[]{"15", "15", "15", "15", "15", "15", "15", "15", "15"},
                        new String[]{"+35", "+22", "+8", "+2", "+7", "0", "+2", "-1", "-3"},
                        new String[]{"40", "35", "26", "26", "25", "24", "23", "21", "21"}));
                break;
            case 2:
                ListView match_opponents_list = (ListView) convertView.findViewById(R.id.match_opponents_list);
                match_opponents_list.setAdapter(new MatchWeekOpponenetsListAdapter(context,
                        new int[]{R.drawable.team_mark_ahli, R.drawable.mark_small, R.drawable.team_mark_koforsom, R.drawable.mark_small, R.drawable.team_mark_that_rass_black, R.drawable.mark_small, R.drawable.team_mark_ramtha},
                        new int[]{R.drawable.mark_small, R.drawable.team_mark_asala, R.drawable.mark_small, R.drawable.team_mark_baqaa, R.drawable.mark_small, R.drawable.team_mark_hussain_irbid, R.drawable.mark_small},
                        new int[]{R.string.team_name_ahli, R.string.team_name_wehdat, R.string.team_name_koforsom, R.string.team_name_wehdat, R.string.team_name_that_rass, R.string.team_name_wehdat, R.string.team_name_ramtha},
                        new int[]{R.string.team_name_wehdat, R.string.team_name_asala, R.string.team_name_wehdat, R.string.team_name_baqaa, R.string.team_name_wehdat, R.string.team_name_hussian_irbid, R.string.team_name_wehdat},
                        new String[]{"0", null, "1", "0", "0", "1", "0"},
                        new String[]{"1", null, "1", "1", "2", "0", "3"},
                        new int[]{
                                R.string.opponents_time_1,
                                R.string.opponents_time_2,
                                R.string.opponents_time_3,
                                R.string.opponents_time_4,
                                R.string.opponents_time_5,
                                R.string.opponents_time_6,
                                R.string.opponents_time_7
                        }, 1));
                break;
            case 3:
                View summary_header_bar = convertView.findViewById(R.id.summary_header_bar);
                TextView summary_header_title_player = (TextView) convertView.findViewById(R.id.summary_header_title_player);
                TextView summary_header_title_goals = (TextView) convertView.findViewById(R.id.summary_header_title_goals);

                if (MainActivity.database.getString("lang", "en").equals("ar")) {
                    summary_header_bar.setScaleX(-1.0f);
                    summary_header_title_player.setScaleX(-1.0f);
                    summary_header_title_goals.setScaleX(-1.0f);

                    summary_header_title_player.setGravity(Gravity.RIGHT);
                } else {
                    summary_header_bar.setScaleX(1.0f);
                    summary_header_title_player.setScaleX(1.0f);
                    summary_header_title_goals.setScaleX(1.0f);

                    summary_header_title_player.setGravity(Gravity.LEFT);
                }

                ListView match_summary_list = (ListView) convertView.findViewById(R.id.match_summary_list);
                match_summary_list.setAdapter(new MatchRankingListAdapter(context,
                        new int[]{R.drawable.mark_small, R.drawable.team_mark_ramtha, R.drawable.team_mark_shabab_alordon, R.drawable.team_mark_koforsom, R.drawable.team_mark_asala, R.drawable.team_mark_faisaly, R.drawable.team_mark_hussain_irbid, R.drawable.team_mark_quqasi, R.drawable.team_mark_that_rass_black},
                        new int[]{R.string.player_name_1, R.string.player_name_2, R.string.player_name_3, R.string.player_name_4, R.string.player_name_5, R.string.player_name_6, R.string.player_name_7, R.string.player_name_8, R.string.player_name_8},
                        new String[]{"", "", "", "", "", "", "", "", ""},
                        new String[]{"11", "9", "9", "7", "7", "7", "7", "3", "3"},
                        new String[]{"", "", "", "", "", "", "", "", ""}));
                break;
        }

        return convertView;
    }
}