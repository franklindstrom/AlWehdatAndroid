package com.lindstrom.frank.alwehdat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by golde on 3/15/2016.
 */
public class ImageActivity extends Activity {
    public Bitmap bitmap;
    public RelativeLayout image_show_layout, image_loading_layout;
    public ImageView btn_image_back, btn_image_share, btn_image_download;
    public TextView txt_loading_title;

    public ArrayList<Integer> selected_index_ary = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_show_image);

        image_show_layout = (RelativeLayout) findViewById(R.id.image_show_layout);
        image_loading_layout = (RelativeLayout) findViewById(R.id.image_loading_layout);
        btn_image_back = (ImageView) findViewById(R.id.btn_image_back);
        btn_image_share = (ImageView) findViewById(R.id.btn_image_share);
        btn_image_download = (ImageView) findViewById(R.id.btn_image_download);
        txt_loading_title = (TextView) findViewById(R.id.image_loading_title);
        txt_loading_title.setText(R.string.title_loading);

        btn_image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_image_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.share_layout, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(ImageActivity.this);
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

        btn_image_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_loading_title.setText(R.string.title_downloading);
                image_loading_layout.setVisibility(View.VISIBLE);
                Date now = new Date();
                DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String format_date = format.format(now);

                OutputStream fOut = null;
                try {
                    File root = new File(Environment.getExternalStorageDirectory()
                            + File.separator + "Wehdat" + File.separator);
                    root.mkdirs();
                    File sdImageMainDirectory = new File(root, "Wehdat_" + format_date + ".jpg");
                    fOut = new FileOutputStream(sdImageMainDirectory);
                } catch (Exception e) {
                    Toast.makeText(ImageActivity.this, "Error occured. Please try again later.", Toast.LENGTH_SHORT).show();
                }

                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    image_loading_layout.setVisibility(View.INVISIBLE);
                    Toast.makeText(ImageActivity.this, "Image saved as /Wehdat/Wehdat_" + format_date + ".jpg", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        new LoadImage().execute("http://www.alweehdat.net/wp-content/uploads/1126.jpg");
    }

    public class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            image_loading_layout.setVisibility(View.INVISIBLE);
            if (image != null) {
                Drawable drawable = new BitmapDrawable(bitmap);
                image_show_layout.setBackgroundDrawable(drawable);
            } else {
                Toast.makeText(ImageActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
