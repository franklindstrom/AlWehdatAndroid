package com.lindstrom.frank.alwehdat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.adapters.SlideMenuAdapter;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;
import com.lindstrom.frank.alwehdat.fragments.AboutTakarubFragment;
import com.lindstrom.frank.alwehdat.fragments.AboutWehdatFragment;
import com.lindstrom.frank.alwehdat.fragments.AsiaClFragment;
import com.lindstrom.frank.alwehdat.fragments.CalendarFragment;
import com.lindstrom.frank.alwehdat.fragments.DownloadFragment;
import com.lindstrom.frank.alwehdat.fragments.HomeFragment;
import com.lindstrom.frank.alwehdat.fragments.ImageFragment;
import com.lindstrom.frank.alwehdat.fragments.LeagueFragment;
import com.lindstrom.frank.alwehdat.fragments.NewsFragment;
import com.lindstrom.frank.alwehdat.fragments.SettingFragment;
import com.lindstrom.frank.alwehdat.fragments.SongFragment;
import com.lindstrom.frank.alwehdat.fragments.SportFragment;
import com.lindstrom.frank.alwehdat.fragments.SquadFragment;
import com.lindstrom.frank.alwehdat.fragments.VideoFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by golde on 3/10/2016.
 */
public class MainActivity extends Activity {
    public static Activity activity;
    public static Context _context;
//    public static Typeface custom_font;

    public static SharedPreferences database;
    public static SharedPreferences.Editor editor;

    public static View frame_container;

    // for slide menu
    public static LinearLayout menu_layout;
    public static  GridView menu_container;

    // for action bar
    public static ActionBar mActionBar;
    public static View actionbar;
    public static TextView action_bar_title;
    public static ImageView btn_search_, btn_menu, btn_back;
    public static View search_bar;
    public static EditText input_search;
    public static Animation animUp, animDown, animLeft, animRight;
    public static boolean menu_opened, search_bar_opened;
    public static int WIDTH, HEIGHT;

    public static boolean popup_opened;
    public static View popupView;
    public static PopupWindow popupWindow;
    public static RelativeLayout refreshing_pan;
    public static TextView refreshing_title;
    public static View popupTargetView;

    public static int rate_selected_index, refreshing_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        _context = this.getApplicationContext();
        activity = this;
//        custom_font = Typeface.createFromAsset(getAssets(), "HelveticaNeueW23forSKY-Reg.ttf");

        database = PreferenceManager.getDefaultSharedPreferences(_context);
        editor = database.edit();

        DataManager.device_info = android.os.Build.MANUFACTURER;

        DataManager.loadLocaleSetting(this);

        frame_container = findViewById(R.id.frame_container);

        refreshing_pan = (RelativeLayout) findViewById(R.id.refreshing_pan);
        refreshing_title = (TextView) findViewById(R.id.refreshing_title);
        hideRefreshingScreen();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;

        animUp = AnimationUtils.loadAnimation(this, R.anim.animate_menu_up);
        animDown = AnimationUtils.loadAnimation(this, R.anim.animate_menu_down);
        animLeft = AnimationUtils.loadAnimation(this, R.anim.animate_left);
        animRight = AnimationUtils.loadAnimation(this, R.anim.animate_right);

        popup_opened = false;
        menu_opened = false;
        search_bar_opened = false;
        rate_selected_index = -1;
        refreshing_time = 0;

        menu_layout = (LinearLayout) findViewById(R.id.menu_layout);

        menu_container = (GridView) findViewById(R.id.menu_container);
        menu_container.setNumColumns(5);
        menu_container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupTargetView = view;
                displayPage(position);
            }
        });

        customActionBar();
        setMenuItems();
        DataManager.gotoNextFragment(new HomeFragment(), activity);
    }

    // set the slide menu items
    public static void setMenuItems() {
        menu_container.setAdapter(new SlideMenuAdapter(_context, DataManager.menuIconImages, DataManager.menuTextStrings));
        if (DataManager.device_info.equals("HUAWEI") && database.getString("lang", "en").equals("ar")) menu_container.setScaleX(-1.0f);
        else menu_container.setScaleX(1.0f);
    }

    // custom setting action bar
    public static void customActionBar() {
        mActionBar = activity.getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(_context);

        actionbar = mInflater.inflate(R.layout.action_bar, null);
        action_bar_title = (TextView) actionbar.findViewById(R.id.action_bar_title);
        btn_back = (ImageView) actionbar.findViewById(R.id.btn_back);
        btn_search_ = (ImageView) actionbar.findViewById(R.id.btn_search_);
        btn_menu = (ImageView) actionbar.findViewById(R.id.btn_menu);
        search_bar = actionbar.findViewById(R.id.search_bar_);
        input_search = (EditText) actionbar.findViewById(R.id.input_search);

        action_bar_title.setText(DataManager.headerTextStrings[DataManager.selectedMenuItemIndex]);
//        action_bar_title.setTypeface(custom_font Typeface.BOLD);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search_bar_opened) onControlSearchBar();
                else if (popup_opened) popupWindow.dismiss();
                else if (menu_opened) onControlMenuBar();
                else if (DataManager.prevFragment >= 0) displayPage(DataManager.prevFragment);
                else activity.finish();
            }
        });

        btn_search_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onControlSearchBar();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search_bar_opened) onControlSearchBar();
                else {
                    onControlMenuBar();
                }
            }
        });

        mActionBar.setCustomView(actionbar);
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        mActionBar.setDisplayShowCustomEnabled(true);

        if (DataManager.selectedMenuItemIndex == 1) {
            btn_back.setVisibility(View.INVISIBLE);
        } else {
            btn_back.setVisibility(View.VISIBLE);
        }
    }

    // show the refresh screen
    public static void showRefreshingScreen(int title) {
        refreshing_pan.setVisibility(View.VISIBLE);
        refreshing_title.setText(title);
    }

    // hide the refresh screen
    public static void hideRefreshingScreen() {
        refreshing_pan.setVisibility(View.INVISIBLE);
    }
    
    // control menu bar
    public static void onControlMenuBar() {
        if (!menu_opened) {
            onOpenMenu();
        } else {
            onCloseMenu();
        }
    }

    // open the slide menu
    public static void onOpenMenu() {
        if (!menu_opened) {
            menu_layout.startAnimation(animDown);
            menu_layout.setVisibility(View.VISIBLE);
            frame_container.setAlpha(0.3f);
            menu_opened = true;
        }
    }

    // close the slide menu
    public static void onCloseMenu() {
        if (popup_opened) {
            popupWindow.dismiss();
            popup_opened = false;
        }

        if (menu_opened) {
            menu_layout.startAnimation(animUp);
            menu_layout.setVisibility(View.INVISIBLE);
            frame_container.setAlpha(1.0f);
            menu_opened = false;
        }
    }

    // control search bar
    public static void onControlSearchBar() {
        if (!search_bar_opened) {
            search_bar.startAnimation(animLeft);
            search_bar.setVisibility(View.VISIBLE);
            search_bar_opened = true;
        } else {
            search_bar.startAnimation(animRight);
            search_bar.setVisibility(View.INVISIBLE);
            input_search.setText("");
            search_bar_opened = false;
        }
    }

    // attach fragments
    public static void displayPage (int index) {
        // display view for selected nav drawer item
        // update the main content by replacing fragments
        if (index < 0) return;
        Fragment fragment = null;

        if (index == 0 || index == 4 || index == 10 || index == 11 || index == 14) DataManager.setSelectedMenuItemIndex(DataManager.selectedMenuItemIndex);
        else DataManager.setSelectedMenuItemIndex(index);
        customActionBar();
        switch (index) {
            case 0:
                if (search_bar_opened) onControlSearchBar();
                else if (popup_opened) popupWindow.dismiss();
                else if (menu_opened) onControlMenuBar();
                else if (DataManager.prevFragment > 0) displayPage(DataManager.prevFragment);
                else activity.finish();
                break;
            case 1:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.gotoNextFragment(new HomeFragment(), activity);
                }
                break;
            case 2:
                if (!popup_opened) {
                    popupView = activity.getLayoutInflater().inflate(R.layout.media_items, null);
                    popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
                    popupWindow.showAsDropDown(popupTargetView);
                    popupView.findViewById(R.id.btn_video).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                            DataManager.gotoNextFragment(new VideoFragment(), activity);
                        }
                    });

                    popupView.findViewById(R.id.btn_image).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            popup_opened = false;
                            onCloseMenu();
                            DataManager.gotoNextFragment(new ImageFragment(), activity);
                        }
                    });

                    popupView.findViewById(R.id.btn_song).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                            DataManager.gotoNextFragment(new SongFragment(), activity);
                        }
                    });
                    popup_opened = true;
                } else {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
                break;
            case 3:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.news_from = 0;
                    DataManager.gotoNextFragment(new NewsFragment(), activity);
                }
                break;
            case 4:
                onControlSearchBar();
                break;
            case 5:
                if (!popup_opened) {
                    popupView = activity.getLayoutInflater().inflate(R.layout.match_items, null);
                    popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
                    popupWindow.showAsDropDown(popupTargetView);
                    popupView.findViewById(R.id.btn_league).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                            DataManager.gotoNextFragment(new LeagueFragment(), activity);
                        }
                    });

                    popupView.findViewById(R.id.btn_asia_cl).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                            DataManager.gotoNextFragment(new AsiaClFragment(), activity);
                        }
                    });
                    popup_opened = true;
                } else {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
                break;
            case 6:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.gotoNextFragment(new SquadFragment(), activity);
                }
                break;
            case 7:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.gotoNextFragment(new CalendarFragment(), activity);
                }
                break;
            case 8:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.news_from = 1;
                    DataManager.gotoNextFragment(new NewsFragment(), activity);
                }
                break;
            case 9:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.gotoNextFragment(new DownloadFragment(), activity);
                }
                break;
            case 10:
                if (!popup_opened) {
                    popupView = activity.getLayoutInflater().inflate(R.layout.share_items, null);
                    popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
                    popupWindow.showAsDropDown(popupTargetView);
                    popupView.findViewById(R.id.btn_share_facebook).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                        }
                    });

                    popupView.findViewById(R.id.btn_share_twitter).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                        }
                    });

                    popupView.findViewById(R.id.btn_share_whats_app).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                        }
                    });
                    popup_opened = true;
                } else {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
                break;
            case 11:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    final View view = activity.getLayoutInflater().inflate(R.layout.rate_app_layout, null);
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setView(view);
                    final AlertDialog dialog = alert.create();
                    dialog.show();

                    View btn_rate_excellent = view.findViewById(R.id.btn_rate_excellent);
                    View btn_rate_very_good = view.findViewById(R.id.btn_rate_very_good);
                    View btn_rate_good = view.findViewById(R.id.btn_rate_good);
                    View btn_rate_poor = view.findViewById(R.id.btn_rate_poor);
                    View btn_rate_bad = view.findViewById(R.id.btn_rate_bad);
                    View btn_rate_excellent_title = view.findViewById(R.id.btn_rate_excellent_title);
                    View btn_rate_very_good_title = view.findViewById(R.id.btn_rate_very_good_title);
                    View btn_rate_good_title = view.findViewById(R.id.btn_rate_good_title);
                    View btn_rate_poor_title = view.findViewById(R.id.btn_rate_poor_title);
                    View btn_rate_bad_title = view.findViewById(R.id.btn_rate_bad_title);
                    View rate_app_title = view.findViewById(R.id.rate_app_title);
                    final int[] viewAry = new int[]{R.id.btn_rate_excellent, R.id.btn_rate_very_good, R.id.btn_rate_good, R.id.btn_rate_poor, R.id.btn_rate_bad};
                    final int[] iconAry = new int[]{R.id.btn_rate_excellent_icon, R.id.btn_rate_very_good_icon, R.id.btn_rate_good_icon, R.id.btn_rate_poor_icon, R.id.btn_rate_bad_icon};
                    final int[] iconNormalImageAry = new int[]{R.drawable.rate_item_image_excellent_normal, R.drawable.rate_item_image_very_good_normal, R.drawable.rate_item_image_good_normal, R.drawable.rate_item_image_poor_normal, R.drawable.rate_item_image_bad_normal};
                    final int[] iconSelectedImageAry = new int[]{R.drawable.rate_item_image_excellent_selected, R.drawable.rate_item_image_very_good_selected, R.drawable.rate_item_image_good_selected, R.drawable.rate_item_image_poor_selected, R.drawable.rate_item_image_bad_selected};
                    final Button btn_cancel_rate = (Button) view.findViewById(R.id.btn_cancel_rate);
                    final Button btn_confirm_rate = (Button) view.findViewById(R.id.btn_confirm_rate);

                    btn_rate_excellent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

                            if (rate_selected_index >= 0 && rate_selected_index != 0) {
                                View prev_selectedView = view.findViewById(viewAry[rate_selected_index]);
                                prev_selectedView.setBackgroundColor(Color.parseColor("#EFEFEF"));
                                ImageView prev_selected_item = (ImageView) view.findViewById(iconAry[rate_selected_index]);
                                prev_selected_item.setBackgroundResource(iconNormalImageAry[rate_selected_index]);
                            }

                            ImageView current_selected_item = (ImageView) view.findViewById(iconAry[0]);
                            current_selected_item.setBackgroundResource(iconSelectedImageAry[0]);
                            rate_selected_index = 0;
                        }
                    });

                    btn_rate_very_good.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

                            if (rate_selected_index >= 0 && rate_selected_index != 1) {
                                View prev_selectedView = view.findViewById(viewAry[rate_selected_index]);
                                prev_selectedView.setBackgroundColor(Color.parseColor("#EFEFEF"));
                                ImageView prev_selected_item = (ImageView) view.findViewById(iconAry[rate_selected_index]);
                                prev_selected_item.setBackgroundResource(iconNormalImageAry[rate_selected_index]);
                            }

                            ImageView current_selected_item = (ImageView) view.findViewById(iconAry[1]);
                            current_selected_item.setBackgroundResource(iconSelectedImageAry[1]);
                            rate_selected_index = 1;
                        }
                    });

                    btn_rate_good.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

                            if (rate_selected_index >= 0 && rate_selected_index != 2) {
                                View prev_selectedView = view.findViewById(viewAry[rate_selected_index]);
                                prev_selectedView.setBackgroundColor(Color.parseColor("#EFEFEF"));
                                ImageView prev_selected_item = (ImageView) view.findViewById(iconAry[rate_selected_index]);
                                prev_selected_item.setBackgroundResource(iconNormalImageAry[rate_selected_index]);
                            }

                            ImageView current_selected_item = (ImageView) view.findViewById(iconAry[2]);
                            current_selected_item.setBackgroundResource(iconSelectedImageAry[2]);
                            rate_selected_index = 2;
                        }
                    });

                    btn_rate_poor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

                            if (rate_selected_index >= 0 && rate_selected_index != 3) {
                                View prev_selectedView = view.findViewById(viewAry[rate_selected_index]);
                                prev_selectedView.setBackgroundColor(Color.parseColor("#EFEFEF"));
                                ImageView prev_selected_item = (ImageView) view.findViewById(iconAry[rate_selected_index]);
                                prev_selected_item.setBackgroundResource(iconNormalImageAry[rate_selected_index]);
                            }

                            ImageView current_selected_item = (ImageView) view.findViewById(iconAry[3]);
                            current_selected_item.setBackgroundResource(iconSelectedImageAry[3]);
                            rate_selected_index = 3;
                        }
                    });

                    btn_rate_bad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

                            if (rate_selected_index >= 0 && rate_selected_index != 4) {
                                View prev_selectedView = view.findViewById(viewAry[rate_selected_index]);
                                prev_selectedView.setBackgroundColor(Color.parseColor("#EFEFEF"));
                                ImageView prev_selected_item = (ImageView) view.findViewById(iconAry[rate_selected_index]);
                                prev_selected_item.setBackgroundResource(iconNormalImageAry[rate_selected_index]);
                            }

                            ImageView current_selected_item = (ImageView) view.findViewById(iconAry[4]);
                            current_selected_item.setBackgroundResource(iconSelectedImageAry[4]);
                            rate_selected_index = 4;
                        }
                    });

                    btn_cancel_rate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn_cancel_rate.setBackgroundColor(Color.parseColor("#B3B3B3"));
                            dialog.dismiss();
                        }
                    });

                    btn_confirm_rate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn_confirm_rate.setBackgroundColor(Color.parseColor("#B3B3B3"));
                            dialog.dismiss();
                        }
                    });

                    if (database.getString("lang", "en").equals("ar")) {
                        view.setScaleX(-1.0f);
                        btn_rate_excellent_title.setScaleX(-1.0f);
                        btn_rate_very_good_title.setScaleX(-1.0f);
                        btn_rate_good_title.setScaleX(-1.0f);
                        btn_rate_poor_title.setScaleX(-1.0f);
                        btn_rate_bad_title.setScaleX(-1.0f);
                        rate_app_title.setScaleX(-1.0f);
                        btn_cancel_rate.setScaleX(-1.0f);
                        btn_confirm_rate.setScaleX(-1.0f);
                    } else {
                        view.setScaleX(1.0f);
                        btn_rate_excellent_title.setScaleX(1.0f);
                        btn_rate_very_good_title.setScaleX(1.0f);
                        btn_rate_good_title.setScaleX(1.0f);
                        btn_rate_poor_title.setScaleX(1.0f);
                        btn_rate_bad_title.setScaleX(1.0f);
                        rate_app_title.setScaleX(1.0f);
                        btn_cancel_rate.setScaleX(1.0f);
                        btn_confirm_rate.setScaleX(1.0f);
                    }
                }
                break;
            case 12:
                if (!popup_opened) {
                    popupView = activity.getLayoutInflater().inflate(R.layout.about_items, null);
                    popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
                    popupWindow.showAsDropDown(popupTargetView);
                    popupView.findViewById(R.id.btn_about_takarub).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                            displayPage(16);
                        }
                    });

                    popupView.findViewById(R.id.btn_about_wehdat).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            onCloseMenu();
                            displayPage(15);

                        }
                    });
                    popup_opened = true;
                } else {
                    popupWindow.dismiss();
                    popup_opened = false;
                }
                break;
            case 13:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    DataManager.gotoNextFragment(new SettingFragment(), activity);
                }
                break;
            case 14:
                if (popup_opened) {
                    popupWindow.dismiss();
                    popup_opened = false;
                } else {
                    onCloseMenu();
                    showRefreshingScreen(R.string.title_refreshing_app_page);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    refreshing_time++;
                                    if (refreshing_time == 3) hideRefreshingScreen();
                                }
                            });
                        }
                    }, 1000, 1000);
                }
                break;
            case 15:
                DataManager.gotoNextFragment(new AboutWehdatFragment(), activity);
                break;
            case 16:
                DataManager.gotoNextFragment(new AboutTakarubFragment(), activity);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (popup_opened) {
            popupWindow.dismiss();
            popup_opened = false;
        } else if (menu_opened) {
            onControlMenuBar();
        } else {
            if (DataManager.prevFragment == 0) finish();
            else displayPage(DataManager.prevFragment);
        }
    }
}