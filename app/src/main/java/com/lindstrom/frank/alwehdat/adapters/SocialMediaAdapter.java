package com.lindstrom.frank.alwehdat.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;

import java.util.ArrayList;

/**
 * Created by golde on 3/15/2016.
 */
public class SocialMediaAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Drawable> iconAry;
    public ArrayList<Drawable> photoAry;
    public ArrayList<Drawable> imageAry;
    public String[] titleAry;
    public String[] nameAry;
    public String[] contentAry;
    public String[] commentCounts;
    public String[] replyCounts;
    public String[] likeCounts;

    public SocialMediaAdapter(Context context, ArrayList<Drawable> iconAry, ArrayList<Drawable> photoAry, ArrayList<Drawable> imageAry, String[] titleAry, String[] nameAry, String[] contentAry, String[] commentCounts, String[] replyCounts, String[] likeCounts) {
        this.context = context;
        this.iconAry = iconAry;
        this.photoAry = photoAry;
        this.imageAry = imageAry;
        this.titleAry = titleAry;
        this.nameAry = nameAry;
        this.contentAry = contentAry;
        this.commentCounts = commentCounts;
        this.replyCounts = replyCounts;
        this.likeCounts = likeCounts;
    }

    @Override
    public int getCount() {
        return iconAry.size();
    }

    @Override
    public Object getItem(int position) {
        return iconAry.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.social_media_items, null);
        }

        RelativeLayout social_media_item_content_container = (RelativeLayout) convertView.findViewById(R.id.social_media_item_content_container);
        ImageView icon = (ImageView) convertView.findViewById(R.id.social_item_icon);
        ImageView photo = (ImageView) convertView.findViewById(R.id.social_item_photo);
        ImageView content_image = (ImageView) convertView.findViewById(R.id.social_item_content_image);
        TextView title = (TextView) convertView.findViewById(R.id.social_item_title);
        TextView name = (TextView) convertView.findViewById(R.id.social_item_name);
        TextView content = (TextView) convertView.findViewById(R.id.social_item_content);
        TextView comment_count = (TextView) convertView.findViewById(R.id.social_item_comment_counts);
        TextView reply_count = (TextView) convertView.findViewById(R.id.social_item_reply_counts);
        TextView like_count = (TextView) convertView.findViewById(R.id.social_item_like_counts);

        icon.setBackgroundDrawable(iconAry.get(position));
        photo.setBackgroundDrawable(photoAry.get(position));
        content_image.setBackgroundDrawable(imageAry.get(position));
        title.setText(titleAry[position]);
        name.setText(nameAry[position]);
        content.setText(contentAry[position]);
        comment_count.setText(commentCounts[position]);
        reply_count.setText(replyCounts[position]);
        like_count.setText(likeCounts[position]);

        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            icon.setScaleX(-1.0f);
            photo.setScaleX(-1.0f);
            title.setScaleX(-1.0f);
            name.setScaleX(-1.0f);
            social_media_item_content_container.setScaleX(-1.0f);
        } else {
            icon.setScaleX(1.0f);
            photo.setScaleX(1.0f);
            title.setScaleX(1.0f);
            name.setScaleX(1.0f);
            social_media_item_content_container.setScaleX(1.0f);
        }

        return convertView;
    }
}
