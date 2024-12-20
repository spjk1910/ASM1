package com.example.asm1.ViewController;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.asm1.ProjectClass.PrefKeyClass;
import com.example.asm1.R;

import java.util.Locale;
import java.util.Objects;

public class SettingView extends AppCompatActivity {
    private TextView backButton;
    private TextView languageChoose, themeChoose, musicChoose;
    private TextView languageDescription, themeDescription, musicDescription;

    private ImageView chevronLeftLanguage, chevronRightLanguage;
    private ImageView chevronLeftTheme, chevronRightTheme;
    private ImageView chevronLeftMusic, chevronRightMusic;

    private int languageIndex;
    private int themeIndex;
    private int musicIndex;

    private String[] languages, languageDescriptions;
    private String[] themes, themeDescriptions;
    private String[] musics, musicDescriptions;

    private MediaPlayer mediaPlayer;

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
        languageIndex = prefs.getInt(PrefKeyClass.getLanguageKey(), 0);
        themeIndex = prefs.getInt(PrefKeyClass.getThemeKey(), 0);
        musicIndex = prefs.getInt(PrefKeyClass.getMusicKey(), 0);

        setContentView(R.layout.activity_setting_view);

        backButton = findViewById(R.id.backButton);

        languageChoose = findViewById(R.id.languageChoose);
        themeChoose = findViewById(R.id.themeChoose);
        musicChoose = findViewById(R.id.musicChoose);
        languageDescription = findViewById(R.id.languageDescription);
        themeDescription = findViewById(R.id.themeDescription);
        musicDescription = findViewById(R.id.musicDescription);

        chevronLeftLanguage = findViewById(R.id.chevronLeftLanguage);
        chevronRightLanguage = findViewById(R.id.chevronRightLanguage);
        chevronLeftTheme = findViewById(R.id.chevronLeftTheme);
        chevronRightTheme = findViewById(R.id.chevronRightTheme);
        chevronLeftMusic = findViewById(R.id.chevronLeftMusic);
        chevronRightMusic = findViewById(R.id.chevronRightMusic);

        languages = getResources().getStringArray(R.array.languageChosen);
        languageDescriptions = getResources().getStringArray(R.array.languageDescription);
        themes = getResources().getStringArray(R.array.themeChosen);
        themeDescriptions = getResources().getStringArray(R.array.themeDescription);
        musics = getResources().getStringArray(R.array.musicChosen);
        musicDescriptions = getResources().getStringArray(R.array.musicDescription);

        updateLanguageDisplay();
        updateThemeDisplay();
        updateMusicDisplay();

        setupListeners();

       if (musicIndex == 0)
       {
           startBackgroundMusic();
       }
    }

    private void updateMusicDisplay() {
        musicChoose.setText(musics[musicIndex]);
        musicDescription.setText(musicDescriptions[musicIndex]);
    }

    private void updateThemeDisplay() {
        themeChoose.setText(themes[themeIndex]);
        themeDescription.setText(themeDescriptions[themeIndex]);
    }

    private void updateLanguageDisplay() {
        languageChoose.setText(languages[languageIndex]);
        languageDescription.setText(languageDescriptions[languageIndex]);
    }

    private void setupListeners() {
        backButton.setOnClickListener(v -> finish());
        chevronLeftLanguage.setOnClickListener(v -> updateLanguage(-1));
        chevronRightLanguage.setOnClickListener(v -> updateLanguage(1));

        chevronLeftMusic.setOnClickListener(v -> updateMusic(-1));
        chevronRightMusic.setOnClickListener(v -> updateMusic(1));

        chevronLeftTheme.setOnClickListener(v -> updateTheme(-1));
        chevronRightTheme.setOnClickListener(v -> updateTheme(1));
    }

    private void updateTheme(int i) {
        themeIndex = (themeIndex + i + themes.length) % themes.length;
        SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
        prefs.edit().putInt(PrefKeyClass.getThemeKey(), themeIndex).apply();
        updateThemeDisplay();

        if (themeIndex == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    private void updateMusic(int i) {
        musicIndex = (musicIndex + i + musics.length) % musics.length;
        SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
        prefs.edit().putInt(PrefKeyClass.getMusicKey(), musicIndex).apply();
        updateMusicDisplay();

        if (musicIndex == 0) {
            if (mediaPlayer == null) {
                startBackgroundMusic();
            } else if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } else {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
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

    private void updateLanguage(int i) {
        languageIndex = (languageIndex + i + languages.length) % languages.length;

        SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
        prefs.edit().putInt(PrefKeyClass.getLanguageKey(), languageIndex).apply();
        updateLanguageDisplay();

        if (languageIndex == 1) {
            setLocale("vi");
        } else {
            setLocale("en");
        }
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
        startActivity(getIntent());
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
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
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}