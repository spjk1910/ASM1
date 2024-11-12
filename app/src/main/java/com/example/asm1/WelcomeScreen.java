package com.example.asm1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private final ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    SharedPreferences prefs = getSharedPreferences(SettingView.getPrefsName(), MODE_PRIVATE);
                    int languageIndex = prefs.getInt(SettingView.getLanguageKey(), 0);
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

        SharedPreferences prefs = getSharedPreferences(SettingView.getPrefsName(), MODE_PRIVATE);
        int languageIndex = prefs.getInt(SettingView.getLanguageKey(), 0);
        String languageCode = (languageIndex == 1) ? "vi" : "en";
        setLocale(languageCode);

        setContentView(R.layout.activity_welcome_screen);

        Button startButton = findViewById(R.id.startButton);
        Button settingButton = findViewById(R.id.settingButton);
        Button quitButton = findViewById(R.id.quitButton);

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

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
    }
}