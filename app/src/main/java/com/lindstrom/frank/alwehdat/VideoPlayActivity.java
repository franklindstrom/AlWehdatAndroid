package com.lindstrom.frank.alwehdat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by golde on 3/16/2016.
 */
public class VideoPlayActivity extends Activity {
    public Timer timer;
    public VideoView video_player;
    public ImageView btn_video_back, btn_video_share, btn_video_download, btn_play_video;
    public TextView video_time_left;
    public RelativeLayout video_progress;
    public RelativeLayout video_loading_layout;
    public View video_progress_bar, video_paused_layout;
    public float video_duration, current_position, video_speed, progress_bar_width, touchPosX;
    public RelativeLayout.LayoutParams progress_layout_params, progress_bar_layout_params;
    public boolean video_is_playing;
    public ArrayList<Integer> selected_index_ary = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_play_video);

        DataManager.loadLocaleSetting(this);

        System.out.println(MainActivity.database.getString("lang", "en"));

        video_is_playing = false;
        video_duration = 0;
        current_position = 0;

        video_player = (VideoView) findViewById(R.id.video_player);
        btn_video_back = (ImageView) findViewById(R.id.btn_video_back);
        btn_video_share = (ImageView) findViewById(R.id.btn_video_share);
        btn_video_download = (ImageView) findViewById(R.id.btn_video_download);
        btn_play_video = (ImageView) findViewById(R.id.btn_play_video);
        video_time_left = (TextView) findViewById(R.id.video_time_left);
        video_progress = (RelativeLayout) findViewById(R.id.video_progress);
        video_loading_layout = (RelativeLayout) findViewById(R.id.video_loading_layout);
        video_progress_bar = findViewById(R.id.video_progress_bar_red);
        video_paused_layout = findViewById(R.id.video_paused_layout);
        btn_play_video.setVisibility(View.INVISIBLE);
        video_paused_layout.setVisibility(View.INVISIBLE);

        progress_layout_params = (RelativeLayout.LayoutParams) video_progress.getLayoutParams();
        progress_layout_params.width = MainActivity.HEIGHT;
        video_progress.setLayoutParams(progress_layout_params);

        progress_bar_layout_params = (RelativeLayout.LayoutParams) video_progress_bar.getLayoutParams();
        progress_bar_layout_params.width = 0;
        video_progress_bar.setLayoutParams(progress_bar_layout_params);

        video_player.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (video_is_playing) {
                    onPauseVideo();
                } else {
                    onPlayVideo((int) current_position);
                }
                return false;
            }
        });

        video_player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                videoErrorDialog().show();
                return false;
            }
        });

        video_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current_position = 0;
                onPauseVideo();
            }
        });

        btn_video_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_video_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.share_layout, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(VideoPlayActivity.this);
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

        btn_video_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // asldkfj
            }
        });

        btn_play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayVideo((int) current_position);
            }
        });

        onLoadVideo();
    }

    public void onLoadVideo() {
        video_loading_layout.setVisibility(View.VISIBLE);
        video_player.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wehdat_video));
        video_player.requestFocus();
        video_player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                video_is_playing = true;
                video_loading_layout.setVisibility(View.INVISIBLE);
                video_player.start();
                video_duration = video_player.getDuration();
                video_speed = MainActivity.HEIGHT / video_duration;
                onShowVideoProgress();
            }
        });
    }

    // play video
    public void onPlayVideo(int position) {
        video_is_playing = true;
        btn_play_video.setVisibility(View.INVISIBLE);
        video_paused_layout.setVisibility(View.INVISIBLE);
        video_player.seekTo(position);
        video_player.start();
        onShowVideoProgress();
    }

    public void onPauseVideo() {
        video_is_playing = false;
        video_paused_layout.setVisibility(View.VISIBLE);
        btn_play_video.setVisibility(View.VISIBLE);
        video_player.pause();
        timer.cancel();
    }

    public void onShowVideoProgress() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        current_position = video_player.getCurrentPosition();
                        progress_bar_layout_params.width = (int) (video_speed * current_position);
                        video_progress_bar.setLayoutParams(progress_bar_layout_params);
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

        video_time_left.setText((minutes < 10 ? "0" + minutes : minutes) + " : " + (seconds < 10 ? "0" + seconds : seconds));
    }

    public AlertDialog videoErrorDialog() {
        AlertDialog alert = new AlertDialog.Builder(VideoPlayActivity.this)
                .setTitle("Error")
                .setMessage("I cannot play this video. Please try again with another video.")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create();
        return alert;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
