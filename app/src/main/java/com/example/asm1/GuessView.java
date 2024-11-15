package com.example.asm1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class GuessView extends AppCompatActivity {
    private ConstraintLayout botMessage;
    private RecyclerView chatView;
    private EditText guessInput;
    private Button guessButton;
    private QuestionClass currentQuestion;
    private GuessGameClass game;
    private ArrayList<String> bot = new ArrayList<>();
    private int hintIndex;

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

        if (languageCode == "en")
        {
            bot.add("Welcome to Tech Tease Quiz Bot!");
            bot.add("I will give you 3 hints to help you guess");
            bot.add("Try to guess the algorithm");
        } else if(languageCode == "vi")
        {
            bot.add("Chào mừng đến với Tech Tease Quiz Bot!");
            bot.add("Tôi sẽ cho bạn 3 gợi ý để đoán");
            bot.add("Hãy cố gắng đoán xem thuật toán nào đang được nhắc đến");
        }

        setContentView(R.layout.activity_guess_view);

    }

//    private void handleGuess() {
//        String guess = guessInput.getText().toString();
//        if (currentQuestion.checkAnswer(guess)) {
//            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Try again.", Toast.LENGTH_SHORT).show();
//            displayNextHint();
//        }
//        guessInput.getText().clear();
//    }

    private void start(String languageCode)
    {
        String[] dynamicHints;
        if ("vi".equals(languageCode)) {
            dynamicHints = currentQuestion.g();  // Method to get Vietnamese hints
        } else {
            dynamicHints = currentQuestion.getEnglishHints();  // Method to get English hints
        }
    }
}