package com.lindstrom.frank.alwehdat.datamanager;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.SplashActivity;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by golde on 3/9/2016.
 */
public class DataManager {
    // device info
    public static String device_info;

    // change the language by the setting
    public static void changeLanguageSetting(Activity activity, String lang) {
        if (lang.equalsIgnoreCase("")) return;
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
    }

    // save the language
    public static void saveLocale(Activity activity, String lang) {
        SharedPreferences database = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = database.edit();
        editor.putString("lang", lang);
        editor.commit();
        loadLocaleSetting(activity);
    }

    // load locale setting
    public static void loadLocaleSetting(Activity activity) {
        String langPref = "lang";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String language = prefs.getString(langPref, "en");
        System.out.println("locale language is " + language);
        changeLanguageSetting(activity, language);
    }

    // the previous fragment
    public static int prevFragment = 1;

    // selected menu item index
    public static int selectedMenuItemIndex = 1;

    // set the selected menu item index
    public static void setSelectedMenuItemIndex(int index) {
        selectedMenuItemIndex = index;
    }

    // button text string for slide menu
    public static int[] menuTextStrings = new int[] {
            R.string.btn_back,
            R.string.btn_home,
            R.string.btn_media,
            R.string.btn_news,
            R.string.btn_search,
            R.string.btn_matches,
            R.string.btn_squad,
            R.string.btn_calendar,
            R.string.btn_sports,
            R.string.btn_downloads,
            R.string.btn_share,
            R.string.btn_rate,
            R.string.btn_about,
            R.string.btn_settings,
            R.string.btn_refresh
    };

    // button text string for slide menu
    public static int[] headerTextStrings = new int[] {
            R.string.header_back,
            R.string.header_home,
            R.string.header_media,
            R.string.header_news,
            R.string.header_search,
            R.string.header_matches,
            R.string.header_squad,
            R.string.header_calendar,
            R.string.header_sports,
            R.string.header_downloads,
            R.string.header_share,
            R.string.header_rate,
            R.string.header_about,
            R.string.header_settings,
            R.string.header_refresh,
            R.string.header_about_wehdat,
            R.string.header_about_takarub
    };

    // normal button images for slide menu
    public static int[] menuIconImages = new int[] {
            R.drawable.btn_back_normal,
            R.drawable.btn_home_normal,
            R.drawable.btn_media_normal,
            R.drawable.btn_news_normal,
            R.drawable.btn_search_normal,
            R.drawable.btn_match_normal,
            R.drawable.btn_squad_normal,
            R.drawable.btn_calendar_normal,
            R.drawable.btn_sports_normal,
            R.drawable.btn_download_normal,
            R.drawable.btn_share_normal,
            R.drawable.btn_rate_normal,
            R.drawable.btn_about_normal,
            R.drawable.btn_setting_normal,
            R.drawable.btn_refresh_normal
    };

    // squad player photo ary
    public static ArrayList<Drawable> squadPhotoImages = new ArrayList<Drawable>();

    // squad player name
    public static int[] squadNames;

    // squad player years
    public static int[] squadYears;

    // squad player number
    public static int[] squadNumbers;

    // squad player photo ary
    public static ArrayList<Drawable> squadMidFielderPhotoImages = new ArrayList<Drawable>();

    // squad player name
    public static int[] squadMidFielderNames;

    // squad player years
    public static int[] squadMidFielderYears;

    // squad player number
    public static int[] squadMidFielderNumbers;

    // the selected player index
    public static int selectedPlayerIndex = 0;

    // the selected player type
    public static int selectedPlayerType = 0;

    // flag that is defined by Sports or Home fragment
    public static int news_from;

    /**
     * created: 2016-03-09
     * <p>
     * goto the next fragment from the current fragment
     */
    public static void gotoNextFragment(Fragment newfragment, Activity activity) {
        if (newfragment != null) {
            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.frame_container, newfragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
