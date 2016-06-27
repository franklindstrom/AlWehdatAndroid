package com.lindstrom.frank.alwehdat.adapters;

/**
 * Created by golde on 3/10/2016.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Drawable> imageAry;
    public int width, height;

    public ImageAdapter(Context context, ArrayList<Drawable> imageAry, int width, int height) {
        this.context = context;
        this.imageAry = imageAry;
        this.width = width;
        this.height = height;
    }

    public int getCount() {
        return this.imageAry.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setBackgroundDrawable(imageAry.get(position));
        return imageView;
    }

}
