/*
* Copyright 2011 Lauri Nevala.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.lindstrom.frank.alwehdat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    public static final int FIRST_DAY_OF_WEEK = 0; // Sunday = 0, Monday = 1
    public Context mContext;
    public Calendar calendar;
    public int day;
    
    public CalendarAdapter(Context c, int year, int month, int day) {
        this.mContext = c;
        this.calendar = Calendar.getInstance();
        this.calendar.set(year, month, day);
        this.calendar.set(Calendar.DAY_OF_MONTH, 1);
        this.day = day;
        refreshDays();
    }

    public int getCount() {
        return days.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView dayView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.calendar_item, null);
        }

        dayView = (TextView) v.findViewById(R.id.calendar_item_day);
        dayView.setText(days[position]);

        View container = v.findViewById(R.id.calendar_item_day_container);
        
        // disable empty days from the beginning
        if (days[position].equals("")) {
            v.setEnabled(false);
        } else {
            // mark current day as focused
            if (days[position].equals("" + day)) {
                container.setBackgroundResource(R.drawable.red_radius);
            }

            v.setEnabled(true);
        }

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            dayView.setScaleX(-1.0f);
        } else {
            dayView.setScaleX(1.0f);
        }
        return v;
    }
    
    public void refreshDays() {
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        
        // figure size of the array
        if (firstDay == 1) {
            days = new String[lastDay + (FIRST_DAY_OF_WEEK * 6)];
        } else {
            days = new String[lastDay + firstDay - (FIRST_DAY_OF_WEEK + 1)];
        }
        
        int j = FIRST_DAY_OF_WEEK;
        
        // populate empty days before first real day
        if (firstDay > 1) {
            for (j = 0; j < firstDay - FIRST_DAY_OF_WEEK; j++) {
                days[j] = "";
            }
        } else {
            for (j = 0; j < FIRST_DAY_OF_WEEK * 6; j++) {
                days[j] = "";
            }
            j = FIRST_DAY_OF_WEEK * 6 + 1; // sunday => 1, monday => 7
        }
        
        // populate days
        int dayNumber = 1;
        for (int i = j - 1; i < days.length; i++) {
            days[i] = "" + dayNumber;
            dayNumber++;
        }
    }

    // references to our items
    public String[] days;
}