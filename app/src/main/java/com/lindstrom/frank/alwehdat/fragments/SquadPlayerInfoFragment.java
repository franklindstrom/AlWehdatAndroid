package com.lindstrom.frank.alwehdat.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lindstrom.frank.alwehdat.MainActivity;
import com.lindstrom.frank.alwehdat.R;
import com.lindstrom.frank.alwehdat.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by golde on 3/9/2016.
 */
public class SquadPlayerInfoFragment extends Fragment {
    // squas player info header bar
    public RelativeLayout squad_player_info_header_bar;
    public ImageView squad_player_info_header_mark;
    public TextView player_name;

    // more info button
    public RelativeLayout btn_more_info;
    public TextView btn_more_info_title, player_number;

    // player photo layout
    public ImageView player_photo, btn_prev_player, btn_next_player;

    // more info layout
    public LinearLayout more_info_pan;
    public RelativeLayout btn_more_info_on_pan;
    public TextView btn_more_info_title_on_pan, player_number_on_pan;
    public TextView title_birthday, player_birthday;
    public TextView title_age, player_age;
    public TextView title_height, player_height;
    public TextView title_weight, player_weight;
    public TextView title_nationality, player_nationality;
    public TextView title_position, player_position;
    public TextView title_brief, player_brief;

    public Animation animUp, animDown;
    public ArrayList<Drawable> playerPhotos = new ArrayList<Drawable>();
    public int[] playerNames;
    public int[] playerNumbers;

    public SquadPlayerInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_squad_player_info, container, false);

        animUp = AnimationUtils.loadAnimation(getActivity(), R.anim.animate_up);
        animDown = AnimationUtils.loadAnimation(getActivity(), R.anim.animate_down);

        // squad player info header bar
        squad_player_info_header_bar = (RelativeLayout) rootView.findViewById(R.id.squad_player_info_header_bar);
        squad_player_info_header_mark = (ImageView) rootView.findViewById(R.id.squad_player_info_header_mark);
        player_name = (TextView) rootView.findViewById(R.id.player_name);

        // more info button
        btn_more_info = (RelativeLayout) rootView.findViewById(R.id.btn_more_info);
        btn_more_info_title = (TextView) rootView.findViewById(R.id.btn_more_info_title);
        player_number = (TextView) rootView.findViewById(R.id.player_number);

        // player photo layout
        player_photo = (ImageView) rootView.findViewById(R.id.player_photo);
        btn_prev_player = (ImageView) rootView.findViewById(R.id.btn_prev_player);
        btn_next_player = (ImageView) rootView.findViewById(R.id.btn_next_player);

        // more info layout
        more_info_pan = (LinearLayout) rootView.findViewById(R.id.more_info_pan);

        btn_more_info_on_pan = (RelativeLayout) rootView.findViewById(R.id.btn_more_info_on_pan);
        btn_more_info_title_on_pan = (TextView) rootView.findViewById(R.id.btn_more_info_title_on_pan);
        player_number_on_pan = (TextView) rootView.findViewById(R.id.player_number_on_pan);

        title_birthday = (TextView) rootView.findViewById(R.id.title_birthday);
        player_birthday = (TextView) rootView.findViewById(R.id.player_birthday);

        title_age = (TextView) rootView.findViewById(R.id.title_age);
        player_age = (TextView) rootView.findViewById(R.id.player_age);

        title_height = (TextView) rootView.findViewById(R.id.title_height);
        player_height = (TextView) rootView.findViewById(R.id.player_height);

        title_weight = (TextView) rootView.findViewById(R.id.title_weight);
        player_weight = (TextView) rootView.findViewById(R.id.player_weight);

        title_nationality = (TextView) rootView.findViewById(R.id.title_nationality);
        player_nationality = (TextView) rootView.findViewById(R.id.player_nationality);

        title_position = (TextView) rootView.findViewById(R.id.title_position);
        player_position = (TextView) rootView.findViewById(R.id.player_position);

        title_brief = (TextView) rootView.findViewById(R.id.title_brief);
        player_brief = (TextView) rootView.findViewById(R.id.player_brief);

        if (DataManager.selectedPlayerType == 0) {
            playerPhotos = DataManager.squadPhotoImages;
            playerNames = DataManager.squadNames;
            playerNumbers = DataManager.squadNumbers;
        } else {
            playerPhotos = DataManager.squadMidFielderPhotoImages;
            playerNames = DataManager.squadMidFielderNames;
            playerNumbers = DataManager.squadMidFielderNumbers;
        }

        btn_prev_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataManager.selectedPlayerIndex == 0) DataManager.selectedPlayerIndex = playerNames.length;
                DataManager.selectedPlayerIndex--;

                setInterface();
            }
        });

        btn_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more_info_pan.startAnimation(animUp);
                more_info_pan.setVisibility(View.VISIBLE);
                btn_more_info.setVisibility(View.INVISIBLE);
            }
        });

        btn_next_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.selectedPlayerIndex++;
                if (DataManager.selectedPlayerIndex == playerNames.length) DataManager.selectedPlayerIndex = 0;

                setInterface();
            }
        });

        btn_more_info_on_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more_info_pan.startAnimation(animDown);
                more_info_pan.setVisibility(View.INVISIBLE);
                btn_more_info.setVisibility(View.VISIBLE);
            }
        });

        setInterface();
        onUpdateUI();
        return rootView;
    }

    // set texts
    public void setInterface() {
        player_photo.setBackgroundDrawable(playerPhotos.get(DataManager.selectedPlayerIndex));
        player_name.setText(playerNames[DataManager.selectedPlayerIndex]);
        player_number.setText(playerNumbers[DataManager.selectedPlayerIndex]);
        player_number_on_pan.setText(playerNumbers[DataManager.selectedPlayerIndex]);
    }

    // update UI via language
    public void onUpdateUI() {
        if (MainActivity.database.getString("lang", "en").equals("ar")) {
            squad_player_info_header_bar.setScaleX(-1.0f);
            squad_player_info_header_mark.setScaleX(-1.0f);
            player_name.setScaleX(-1.0f);

            btn_more_info.setScaleX(-1.0f);
            btn_more_info_title.setScaleX(-1.0f);
            player_number.setScaleX(-1.0f);

            more_info_pan.setScaleX(-1.0f);

            btn_more_info_title_on_pan.setScaleX(-1.0f);
            player_number_on_pan.setScaleX(-1.0f);

            title_birthday.setScaleX(-1.0f);
            player_birthday.setScaleX(-1.0f);

            title_age.setScaleX(-1.0f);
            player_age.setScaleX(-1.0f);

            title_height.setScaleX(-1.0f);
            player_height.setScaleX(-1.0f);

            title_weight.setScaleX(-1.0f);
            player_weight.setScaleX(-1.0f);

            title_nationality.setScaleX(-1.0f);
            player_nationality.setScaleX(-1.0f);

            title_position.setScaleX(-1.0f);
            player_position.setScaleX(-1.0f);

            title_brief.setScaleX(-1.0f);
            player_brief.setScaleX(-1.0f);

            title_birthday.setGravity(Gravity.RIGHT);
            player_birthday.setGravity(Gravity.RIGHT);
            title_age.setGravity(Gravity.RIGHT);
            player_age.setGravity(Gravity.RIGHT);
            title_height.setGravity(Gravity.RIGHT);
            player_height.setGravity(Gravity.RIGHT);
            title_weight.setGravity(Gravity.RIGHT);
            player_weight.setGravity(Gravity.RIGHT);
            title_nationality.setGravity(Gravity.RIGHT);
            player_nationality.setGravity(Gravity.RIGHT);
            title_position.setGravity(Gravity.RIGHT);
            player_position.setGravity(Gravity.RIGHT);
        } else {
            squad_player_info_header_bar.setScaleX(1.0f);
            squad_player_info_header_mark.setScaleX(1.0f);
            player_name.setScaleX(1.0f);

            btn_more_info.setScaleX(1.0f);
            btn_more_info_title.setScaleX(1.0f);
            player_number.setScaleX(1.0f);

            more_info_pan.setScaleX(1.0f);

            btn_more_info_title_on_pan.setScaleX(1.0f);
            player_number_on_pan.setScaleX(1.0f);

            title_birthday.setScaleX(1.0f);
            player_birthday.setScaleX(1.0f);

            title_age.setScaleX(1.0f);
            player_age.setScaleX(1.0f);

            title_height.setScaleX(1.0f);
            player_height.setScaleX(1.0f);

            title_weight.setScaleX(1.0f);
            player_weight.setScaleX(1.0f);

            title_nationality.setScaleX(1.0f);
            player_nationality.setScaleX(1.0f);

            title_position.setScaleX(1.0f);
            player_position.setScaleX(1.0f);

            title_brief.setScaleX(1.0f);
            player_brief.setScaleX(1.0f);

            title_birthday.setGravity(Gravity.LEFT);
            player_birthday.setGravity(Gravity.LEFT);
            title_age.setGravity(Gravity.LEFT);
            player_age.setGravity(Gravity.LEFT);
            title_height.setGravity(Gravity.LEFT);
            player_height.setGravity(Gravity.LEFT);
            title_weight.setGravity(Gravity.LEFT);
            player_weight.setGravity(Gravity.LEFT);
            title_nationality.setGravity(Gravity.LEFT);
            player_nationality.setGravity(Gravity.LEFT);
            title_position.setGravity(Gravity.LEFT);
            player_position.setGravity(Gravity.LEFT);
        }
    }
}
