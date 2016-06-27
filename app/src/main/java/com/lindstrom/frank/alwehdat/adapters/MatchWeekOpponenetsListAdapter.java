package com.lindstrom.frank.alwehdat.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.R;

/**
 * Created by golden on 10/28/2015.
 */
public class MatchWeekOpponenetsListAdapter extends BaseAdapter {

    public Context context;
    public int[] nameAry1;
    public int[] nameAry2;
    public int[] dateTimeAry;
    public String[] goalAry1;
    public String[] goalAry2;
    public int[] markAry1;
    public int[] markAry2;
    public int page;

    public MatchWeekOpponenetsListAdapter(Context context, int[] markAry1, int[] markAry2, int[] nameAry1, int[] nameAry2, String[] goalAry1, String[] goalAry2, int[] dateTimeAry, int page) {
        this.context = context;
        this.nameAry1 = nameAry1;
        this.nameAry2 = nameAry2;
        this.goalAry1 = goalAry1;
        this.goalAry2 = goalAry2;
        this.markAry1 = markAry1;
        this.markAry2 = markAry2;
        this.dateTimeAry = dateTimeAry;
        this.page = page;
    }

    @Override
    public int getCount() {
        return this.markAry1.length;
    }

    @Override
    public Object getItem(int position) {
        return markAry1[position];
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
            convertView = mInflater.inflate(R.layout.match_week_opponents_items, null);
        }

        ImageView match_week_team_mark_1 = (ImageView) convertView.findViewById(R.id.match_week_team_mark_1);
        ImageView match_week_team_mark_2 = (ImageView) convertView.findViewById(R.id.match_week_team_mark_2);
        TextView match_week_team_name_1 = (TextView) convertView.findViewById(R.id.match_week_team_name_1);
        TextView match_week_team_name_2 = (TextView) convertView.findViewById(R.id.match_week_team_name_2);
        TextView match_team_scores = (TextView) convertView.findViewById(R.id.match_team_scores);
        TextView match_week_date_time = (TextView) convertView.findViewById(R.id.match_week_date_time);
        String score_text = "";

        if (nameAry1[position] == R.string.team_name_wehdat || nameAry2[position] == R.string.team_name_wehdat) {
            if (page == 0) {
                convertView.setBackgroundColor(Color.parseColor("#F93434"));
                match_week_team_name_1.setTextColor(Color.WHITE);
                match_week_team_name_2.setTextColor(Color.WHITE);
                match_team_scores.setTextColor(Color.WHITE);
                match_week_date_time.setTextColor(Color.WHITE);

                match_week_team_name_1.setTypeface(Typeface.DEFAULT_BOLD);
                match_week_team_name_2.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                if (nameAry1[position] == R.string.team_name_wehdat) {
                    match_week_team_name_1.setTypeface(Typeface.DEFAULT_BOLD);
                    match_week_team_name_2.setTypeface(Typeface.DEFAULT);
                } else if (nameAry2[position] == R.string.team_name_wehdat) {
                    match_week_team_name_1.setTypeface(Typeface.DEFAULT);
                    match_week_team_name_2.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    match_week_team_name_1.setTypeface(Typeface.DEFAULT);
                    match_week_team_name_2.setTypeface(Typeface.DEFAULT);
                }
            }
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            match_week_team_name_1.setTextColor(Color.BLACK);
            match_week_team_name_2.setTextColor(Color.BLACK);
            match_team_scores.setTextColor(Color.BLACK);
            match_week_date_time.setTextColor(Color.parseColor("#666666"));

            match_week_team_name_1.setTypeface(Typeface.DEFAULT);
            match_week_team_name_2.setTypeface(Typeface.DEFAULT);
        }

        if (page == 1) {
            if (goalAry1[position] == null || goalAry2[position] == null) {
                score_text = "- : -";
                match_team_scores.setTextColor(Color.RED);
            } else {
                score_text = goalAry1[position] + " : " + goalAry2[position];
                match_team_scores.setTextColor(Color.BLACK);
            }
        } else if (page == 0) {
            score_text = goalAry1[position] + " : " + goalAry2[position];
        }

        match_week_team_mark_1.setImageResource(markAry1[position]);
        match_week_team_mark_2.setImageResource(markAry2[position]);
        match_week_team_name_1.setText(nameAry1[position]);
        match_week_team_name_2.setText(nameAry2[position]);
        match_team_scores.setText(score_text);
        match_week_date_time.setText(dateTimeAry[position]);
        return convertView;
    }
}