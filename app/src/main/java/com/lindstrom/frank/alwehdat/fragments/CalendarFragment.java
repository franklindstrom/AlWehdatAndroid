package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.adapters.CalendarAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.Calendar;

/**
 * Created by golde on 3/10/2016.
 */
public class CalendarFragment extends Fragment {
    public int year, month, day, hour, min;
    public String ap;
    public Calendar calendar;
    public GridView calendar_container;

    public RelativeLayout calendar_layout;
    public TextView txtPrevMonth, txtCurrentMonth, txtNextMonth;
    public LinearLayout week_bar;
    public int[] week_items = new int[] {R.id.week_sun, R.id.week_mon, R.id.week_tue, R.id.week_wed, R.id.week_thu, R.id.week_fri, R.id.week_sat};
    public TextView calendar_time, match_type, match_content;
    public ImageView btn_prev_month, btn_next_month;
    public int[] month_title_long_ary, month_title_short_ary;

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        DataManager.prevFragment = 1;

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        ap = (calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
        calendar.set(year, month, day);

        calendar_container = (GridView) rootView.findViewById(R.id.calendar_container);
        calendar_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        calendar_container.setAdapter(new CalendarAdapter(getActivity(), year, month, day));

        calendar_layout = (RelativeLayout) rootView.findViewById(R.id.calendar_layout);
        week_bar = (LinearLayout) rootView.findViewById(R.id.week_bar);
        txtPrevMonth = (TextView) rootView.findViewById(R.id.txtPrevMonth);
        txtCurrentMonth = (TextView) rootView.findViewById(R.id.txtCurrentMonth);
        txtNextMonth = (TextView) rootView.findViewById(R.id.txtNextMonth);
        calendar_time = (TextView) rootView.findViewById(R.id.calendar_time);
        match_type = (TextView) rootView.findViewById(R.id.match_type);
        match_content = (TextView) rootView.findViewById(R.id.match_content);
        btn_prev_month = (ImageView) rootView.findViewById(R.id.btn_prev_month);
        btn_next_month = (ImageView) rootView.findViewById(R.id.btn_next_month);

        month_title_long_ary = new int[] {R.string.month_long_1, R.string.month_long_2, R.string.month_long_3, R.string.month_long_4, R.string.month_long_5, R.string.month_long_6, R.string.month_long_7, R.string.month_long_8, R.string.month_long_9, R.string.month_long_10, R.string.month_long_11, R.string.month_long_12};
        month_title_short_ary = new int[] {R.string.month_short_1, R.string.month_short_2, R.string.month_short_3, R.string.month_short_4, R.string.month_short_5, R.string.month_short_6, R.string.month_short_7, R.string.month_short_8, R.string.month_short_9, R.string.month_short_10, R.string.month_short_11, R.string.month_short_12};

        calendar_time.setText(hour + " : " + (min < 10 ? "0" + min : min) + " " + ap);

        btn_prev_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                txtPrevMonth.setText(month_title_short_ary[(month == 0 ? month_title_long_ary.length - 1 : month - 1)]);
                txtCurrentMonth.setText(month_title_long_ary[month]);
                txtNextMonth.setText(month_title_short_ary[(month == month_title_long_ary.length - 1 ? 0 : month + 1)]);
                calendar_container.setAdapter(new CalendarAdapter(getActivity(), year, month, day));
            }
        });

        btn_next_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                txtPrevMonth.setText(month_title_short_ary[(month == 0 ? month_title_long_ary.length - 1 : month - 1)]);
                txtCurrentMonth.setText(month_title_long_ary[month]);
                txtNextMonth.setText(month_title_short_ary[(month == month_title_long_ary.length - 1 ? 0 : month + 1)]);
                calendar_container.setAdapter(new CalendarAdapter(getActivity(), year, month, day));
            }
        });

        txtPrevMonth.setText(month_title_short_ary[(month == 0 ? month_title_long_ary.length : month - 1)]);
        txtCurrentMonth.setText(month_title_long_ary[month]);
        txtNextMonth.setText(month_title_short_ary[(month == month_title_long_ary.length ? 0 : month + 1)]);

        onUpdateUI(rootView);
        return rootView;
    }
    
    // update UI via locale language
    public void onUpdateUI(View view) {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            week_bar.setScaleX(-1.0f);
            for (int i = 0; i < week_items.length; i++) {
                TextView week_item = (TextView) view.findViewById(week_items[i]);
                week_item.setScaleX(-1.0f);
            }

            calendar_layout.setScaleX(-1.0f);
            calendar_time.setScaleX(-1.0f);
            match_type.setScaleX(-1.0f);
            match_content.setScaleX(-1.0f);
        } else {
            week_bar.setScaleX(1.0f);
            for (int i = 0; i < week_items.length; i++) {
                TextView week_item = (TextView) view.findViewById(week_items[i]);
                week_item.setScaleX(1.0f);
            }

            calendar_layout.setScaleX(1.0f);
            calendar_time.setScaleX(1.0f);
            match_type.setScaleX(1.0f);
            match_content.setScaleX(1.0f);
        }
    }
}