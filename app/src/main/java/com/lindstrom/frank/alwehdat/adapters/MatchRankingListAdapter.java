package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

/**
 * Created by golden on 10/28/2015.
 */
public class MatchRankingListAdapter extends BaseAdapter {

    public Context context;
    public int[] nameAry;
    public String[] weekAry;
    public String[] goalAry;
    public String[] pointAry;
    public int[] markAry;

    public MatchRankingListAdapter(Context context, int[] markAry, int[] nameAry, String[] weekAry, String[] goalAry, String[] pointAry) {
        this.context = context;
        this.nameAry = nameAry;
        this.weekAry = weekAry;
        this.goalAry = goalAry;
        this.pointAry = pointAry;
        this.markAry = markAry;
    }

    @Override
    public int getCount() {
        return this.markAry.length;
    }

    @Override
    public Object getItem(int position) {
        return markAry[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.match_ranking_items, null);
        }

        TextView ranking_team_no = (TextView) convertView.findViewById(R.id.ranking_team_no);
        ImageView ranking_team_mark = (ImageView) convertView.findViewById(R.id.ranking_team_mark);
        TextView ranking_team_name = (TextView) convertView.findViewById(R.id.ranking_team_name);
        TextView ranking_team_week = (TextView) convertView.findViewById(R.id.ranking_team_week);
        TextView ranking_team_goals = (TextView) convertView.findViewById(R.id.ranking_team_goals);
        TextView ranking_team_points = (TextView) convertView.findViewById(R.id.ranking_team_points);

        ranking_team_no.setText((position + 1) + ".");
        ranking_team_mark.setImageResource(markAry[position]);
        ranking_team_name.setText(nameAry[position]);
        ranking_team_week.setText(weekAry[position]);
        ranking_team_goals.setText(goalAry[position]);
        ranking_team_points.setText(pointAry[position]);

        if (nameAry[position] == R.string.team_name_wehdat) {
            convertView.setBackgroundColor(Color.parseColor("#F93434"));
            ranking_team_no.setTextColor(Color.WHITE);
            ranking_team_name.setTextColor(Color.WHITE);
            ranking_team_week.setTextColor(Color.WHITE);
            ranking_team_goals.setTextColor(Color.WHITE);
            ranking_team_points.setTextColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ranking_team_no.setTextColor(Color.BLACK);
            ranking_team_name.setTextColor(Color.BLACK);
            ranking_team_week.setTextColor(Color.BLACK);
            ranking_team_goals.setTextColor(Color.BLACK);
            ranking_team_points.setTextColor(Color.BLACK);
        }

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            convertView.setScaleX(-1.0f);
            ranking_team_no.setScaleX(-1.0f);
            ranking_team_mark.setScaleX(-1.0f);
            ranking_team_name.setScaleX(-1.0f);
            ranking_team_week.setScaleX(-1.0f);
            ranking_team_goals.setScaleX(-1.0f);
            ranking_team_points.setScaleX(-1.0f);

            ranking_team_name.setGravity(Gravity.RIGHT);
        } else {
            convertView.setScaleX(1.0f);
            ranking_team_no.setScaleX(1.0f);
            ranking_team_mark.setScaleX(1.0f);
            ranking_team_name.setScaleX(1.0f);
            ranking_team_week.setScaleX(1.0f);
            ranking_team_goals.setScaleX(1.0f);
            ranking_team_points.setScaleX(1.0f);

            ranking_team_name.setGravity(Gravity.LEFT);
        }
        return convertView;
    }
}