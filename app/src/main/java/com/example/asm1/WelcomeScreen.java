package com.example.asm1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Objects;

public class WelcomeScreen extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private final ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
                    int languageIndex = prefs.getInt(PrefKeyClass.getLanguageKey(), 0);
                    String languageCode = (languageIndex == 1) ? "vi" : "en";
                    setLocale(languageCode);
                    recreate();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
        int languageIndex = prefs.getInt(PrefKeyClass.getLanguageKey(), 0);
        String languageCode = (languageIndex == 1) ? "vi" : "en";
        setLocale(languageCode);
        int musicIndex = prefs.getInt(PrefKeyClass.getMusicKey(), 0);
        if (musicIndex == 0)
        {
          startBackgroundMusic();
        }

        setContentView(R.layout.activity_welcome_screen);

        Button startButton = findViewById(R.id.startButton);
        Button settingButton = findViewById(R.id.settingButton);
        Button quitButton = findViewById(R.id.quitButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guessIntenT = new Intent(WelcomeScreen.this, GuessView.class);
                startActivity(guessIntenT);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(WelcomeScreen.this, SettingView.class);
                settingsLauncher.launch(settingIntent);
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private void startBackgroundMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.jingle_bells);
            mediaPlayer.setLooping(true);
        }

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
        int musicIndex = prefs.getInt(PrefKeyClass.getMusicKey(), 0);
        if(musicIndex == 0)
        {
            startBackgroundMusic();
        }
    }

}