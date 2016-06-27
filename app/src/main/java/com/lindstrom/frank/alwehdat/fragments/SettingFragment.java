package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

/**
 * Created by golde on 3/11/2016.
 */
public class SettingFragment extends Fragment {
    public RelativeLayout notification_layout, alarm_layout, language_layout;

    public ImageView btn_switch_notification, btn_switch_alarm, btn_switch_language;
    public TextView title_notification, title_alarm, title_language, txt_language;

    public boolean popup_opened;
    public View popupView;
    public PopupWindow popupWindow;

    public SharedPreferences database;

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        DataManager.prevFragment = 1;

        database = PreferenceManager.getDefaultSharedPreferences(getActivity());

        notification_layout = (RelativeLayout) rootView.findViewById(R.id.notification_layout);
        alarm_layout = (RelativeLayout) rootView.findViewById(R.id.alarm_layout);
        language_layout = (RelativeLayout) rootView.findViewById(R.id.language_layout);
        btn_switch_notification = (ImageView) rootView.findViewById(R.id.btn_switch_notification);
        btn_switch_alarm = (ImageView) rootView.findViewById(R.id.btn_switch_alarm);
        btn_switch_language = (ImageView) rootView.findViewById(R.id.btn_switch_language);
        title_notification = (TextView) rootView.findViewById(R.id.title_notification);
        title_alarm = (TextView) rootView.findViewById(R.id.title_alarm);
        title_language = (TextView) rootView.findViewById(R.id.title_language);
        txt_language = (TextView) rootView.findViewById(R.id.txt_language);

        setSwitchImages();
        refreshTitles();
        btn_switch_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.editor.putInt("notification_setting", 1 - MainActivity.database.getInt("notification_setting", 0));
                MainActivity.editor.commit();
                setSwitchImages();
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
            }
        });

        btn_switch_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.editor.putInt("alarm_setting", 1 - MainActivity.database.getInt("alarm_setting", 0));
                MainActivity.editor.commit();
                setSwitchImages();
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
            }
        });

        btn_switch_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!popup_opened) {
                    popupView = getActivity().getLayoutInflater().inflate(R.layout.language_setting_items, null);
                    popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
                    popupWindow.showAsDropDown(v);
                    popupView.findViewById(R.id.lang_en).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataManager.saveLocale(getActivity(), "en");
                            DataManager.loadLocaleSetting(getActivity());
                            txt_language.setText(R.string.lang_en);
                            refreshTitles();
                            popup_opened = false;
                            popupWindow.dismiss();
                        }
                    });

                    popupView.findViewById(R.id.lang_ar).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataManager.saveLocale(getActivity(), "ar");
                            DataManager.loadLocaleSetting(getActivity());
                            txt_language.setText(R.string.lang_ar);
                            refreshTitles();
                            popup_opened = false;
                            popupWindow.dismiss();
                        }
                    });
                    popup_opened = true;
                } else {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
            }
        });
        return rootView;
    }

    // set the switch button images
    public void setSwitchImages() {
        if (MainActivity.database.getInt("notification_setting", 0) == 0) {
            btn_switch_notification.setImageResource(R.drawable.btn_switch_off);
        } else {
            btn_switch_notification.setImageResource(R.drawable.btn_switch_on);
        }

        if (MainActivity.database.getInt("alarm_setting", 0) == 0) {
            btn_switch_alarm.setImageResource(R.drawable.btn_switch_off);
        } else {
            btn_switch_alarm.setImageResource(R.drawable.btn_switch_on);
        }
    }

    // set the title strings
    public void refreshTitles() {
        title_notification.setText(R.string.title_notification);
        title_alarm.setText(R.string.title_alarm);
        title_language.setText(R.string.title_language);
        txt_language.setText(database.getString("lang", "en").equals("en") ? R.string.lang_en : R.string.lang_ar);

        MainActivity.customActionBar();
        MainActivity.setMenuItems();

        onUpdateUI();
    }

    // update ui via locale language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            notification_layout.setScaleX(-1.0f);
            title_notification.setScaleX(-1.0f);
            btn_switch_notification.setScaleX(-1.0f);

            alarm_layout.setScaleX(-1.0f);
            title_alarm.setScaleX(-1.0f);
            btn_switch_alarm.setScaleX(-1.0f);

            language_layout.setScaleX(-1.0f);
            title_language.setScaleX(-1.0f);
            txt_language.setScaleX(-1.0f);
            btn_switch_language.setScaleX(-1.0f);
        } else {
            notification_layout.setScaleX(1.0f);
            title_notification.setScaleX(1.0f);
            btn_switch_notification.setScaleX(1.0f);

            alarm_layout.setScaleX(1.0f);
            title_alarm.setScaleX(1.0f);
            btn_switch_alarm.setScaleX(1.0f);

            language_layout.setScaleX(1.0f);
            title_language.setScaleX(1.0f);
            txt_language.setScaleX(1.0f);
            btn_switch_language.setScaleX(1.0f);
        }
    }
}