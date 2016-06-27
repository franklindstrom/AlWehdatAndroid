package com.lindstrom.frank.alwehdat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by golde on 3/16/2016.
 */
public class SongPlayActivity extends Activity {
    public MediaPlayer mediaPlayer;
    public Timer timer;
    public TextView song_time_left;
    public RelativeLayout song_progress, song_progress_bar;
    public float song_duration, current_position, song_speed, progress_bar_width, touchPosX;
    public RelativeLayout.LayoutParams progress_layout_params, progress_bar_layout_params;
    public boolean song_is_playing;
    public ImageView btn_play_song, btn_pause_song, btn_share_song, btn_download_song, btn_prev_song, btn_next_song;
    public ArrayList<Integer> selected_index_ary = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_play_song);

        DataManager.loadLocaleSetting(this);
    
        song_time_left = (TextView) findViewById(R.id.song_time_left);
        song_progress = (RelativeLayout) findViewById(R.id.song_back_layout);
        song_progress_bar = (RelativeLayout) findViewById(R.id.song_progress_bar);
    
        progress_layout_params = (RelativeLayout.LayoutParams) song_progress.getLayoutParams();
    
        progress_bar_layout_params = (RelativeLayout.LayoutParams) song_progress_bar.getLayoutParams();
        progress_bar_layout_params.width = 0;
        song_progress_bar.setLayoutParams(progress_bar_layout_params);

        btn_play_song = (ImageView) findViewById(R.id.btn_song_play);
        btn_pause_song = (ImageView) findViewById(R.id.btn_song_pause);
        btn_share_song = (ImageView) findViewById(R.id.btn_song_share);
        btn_download_song = (ImageView) findViewById(R.id.btn_song_download);
        btn_prev_song = (ImageView) findViewById(R.id.btn_song_backward);
        btn_next_song = (ImageView) findViewById(R.id.btn_song_forward);

        btn_play_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song_is_playing = true;
                onShowHideButtons();
                onPlaySong((int) current_position);
            }
        });

        btn_pause_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song_is_playing = false;
                onShowHideButtons();
                onPauseSong();
            }
        });

        btn_share_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.share_layout, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(SongPlayActivity.this);
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

        btn_download_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // asdaslkdaslkjasd
            }
        });

        btn_prev_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song_is_playing = true;
                onShowHideButtons();
                onPauseSong();
                onPlaySong((int) (current_position - 5000));
            }
        });

        btn_next_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song_is_playing = true;
                onShowHideButtons();
                onPauseSong();
                onPlaySong((int) (current_position + 5000));
            }
        });

        onLoadSong();
    }

    public void onLoadSong() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wehdat_music));
            mediaPlayer.setLooping(false);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    song_duration = mediaPlayer.getDuration();
                    song_speed = progress_layout_params.width / song_duration;

                    song_is_playing = true;
                    onShowHideButtons();
                    onShowSongProgress();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    song_is_playing = false;
                    onShowHideButtons();
                    onPauseSong();
                    current_position = 0;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void onShowSongProgress() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        current_position = mediaPlayer.getCurrentPosition();
                        progress_bar_layout_params.width = (int) (song_speed * current_position);
                        song_progress_bar.setLayoutParams(progress_bar_layout_params);
                        onShowTimeTitle();
                    }
                });
            }
        }, 100, 100);
    }
    
    public void onShowTimeTitle() {
        int seconds = (int) (current_position / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        
        song_time_left.setText((minutes < 10 ? "0" + minutes : minutes) + " : " + (seconds < 10 ? "0" + seconds : seconds));
    }

    // show or hide play and pause button
    public void onShowHideButtons() {
        if (song_is_playing) {
            btn_play_song.setVisibility(View.INVISIBLE);
            btn_pause_song.setVisibility(View.VISIBLE);
        } else {
            btn_play_song.setVisibility(View.VISIBLE);
            btn_pause_song.setVisibility(View.INVISIBLE);
        }
    }

    // pause song
    public void onPauseSong() {
        mediaPlayer.pause();
        timer.cancel();
    }

    // play song
    public void onPlaySong(int position) {
        mediaPlayer.seekTo(position);
        mediaPlayer.start();
        onShowSongProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}