package com.example.collinsherman.asteroidzgame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity{

    private static final String TAG = "SettingsActivity";

    private Spinner mColorSpinner;
    private ArrayAdapter<CharSequence> mColorAdapter;
    private Button mApplyButton;
    private ImageView mShip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mColorSpinner = (Spinner) findViewById(R.id.color_spinner);
        mColorAdapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        mColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mColorSpinner.setAdapter(mColorAdapter);

        mShip = (ImageView) findViewById(R.id.ship);
        mShip.setImageResource(R.drawable.red_animation);
        AnimationDrawable redAnimation = (AnimationDrawable)mShip.getDrawable();
        redAnimation.start();

        mApplyButton = (Button) findViewById(R.id.apply_button);
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = mColorSpinner.getSelectedItem().toString();
                Log.d(TAG, "Color:"+ color);
                if (color.equals("Green")){
                    mShip.setImageResource(R.drawable.green_animation);
                    AnimationDrawable greenAnimation = (AnimationDrawable)mShip.getDrawable();
                    greenAnimation.start();
                } else if (color.equals("Blue")){
                    mShip.setImageResource(R.drawable.blue_animation);
                    AnimationDrawable blueAnimation = (AnimationDrawable)mShip.getDrawable();
                    blueAnimation.start();
                } else if (color.equals("Orange")) {
                    mShip.setImageResource(R.drawable.orange_animation);
                    AnimationDrawable orangeAnimation = (AnimationDrawable)mShip.getDrawable();
                    orangeAnimation.start();
                } else if (color.equals("Purple")){
                    mShip.setImageResource(R.drawable.purple_animation);
                    AnimationDrawable purpleAnimation = (AnimationDrawable)mShip.getDrawable();
                    purpleAnimation.start();
                } else if (color.equals("Red")){
                    mShip.setImageResource(R.drawable.red_animation);
                    AnimationDrawable redAnimation = (AnimationDrawable)mShip.getDrawable();
                    redAnimation.start();
                }
            }
        });
    }




}
