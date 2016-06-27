package com.lindstrom.frank.alwehdat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by golde on 3/9/2016.
 */
public class SplashActivity extends Activity {
    public Timer timer;
    public TextView title_loading, title_design_company;
    public ImageView mark;
    public int time;

    public ConnectivityManager connectivityManager;
    public NetworkInfo networkInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_splash);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "HelveticaNeueW23forSKY-Reg.ttf");

        title_loading = (TextView) findViewById(R.id.title_loading);
        title_design_company = (TextView) findViewById(R.id.title_design_company);

//        title_loading.setTypeface(custom_font);
//        title_design_company.setTypeface(custom_font);

        mark = (ImageView) findViewById(R.id.mark);
        time = 0;

        DataManager.loadLocaleSetting(this);

        title_loading.setText(R.string.title_loading);
        title_design_company.setText(R.string.title_design_company);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        if (time == 3) {
                            timer.cancel();
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }, 1000, 1000);

        if (networkInfo == null || !networkInfo.isAvailable()) {
            mark.setVisibility(View.INVISIBLE);
            title_loading.setVisibility(View.INVISIBLE);
            title_design_company.setVisibility(View.INVISIBLE);
            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Check your connection and try again")
                    .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            mark.setVisibility(View.VISIBLE);
                            title_loading.setVisibility(View.VISIBLE);
                            title_design_company.setVisibility(View.VISIBLE);
                            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                        }
                    }).create().show();
        }
    }
}
