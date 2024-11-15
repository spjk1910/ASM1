package com.example.asm1;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuessView extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private Adapter adapter;
    private RecyclerView chatView;
    private List<String> container ;
    private EditText guessInput;
    private AppCompatImageView guessButton;
    private AppCompatImageView backButton;
    private QuestionClass currentQuestion;
    private GuessGameClass game;

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
        int musicIndex = prefs.getInt(PrefKeyClass.getMusicKey(), 0);
        if (musicIndex == 0)
        {
            startBackgroundMusic();
        }

        setContentView(R.layout.activity_guess_view);

        chatView = findViewById(R.id.chatView);
        guessButton = findViewById(R.id.guessButton);
        guessInput = findViewById(R.id.guessInput);
        backButton = findViewById(R.id.backButton);

        container = new ArrayList<>();
        if (languageCode == "en")
        {
            container.add("(Bot) Welcome to Tech Tease Quiz Bot!");
            container.add("(Bot) I will give you 3 hints to help you guess");
            container.add("(Bot) Try to guess the algorithm");
        } else if(languageCode == "vi")
        {
            container.add("(Bot) Chào mừng đến với Tech Tease Quiz Bot!");
            container.add("(Bot) Tôi sẽ cho bạn 3 gợi ý để đoán");
            container.add("(Bot) Hãy cố gắng đoán xem thuật toán nào đang được nhắc đến");
        }

        adapter = new Adapter(container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatView.setLayoutManager(layoutManager);
        chatView.setAdapter(adapter);
        chatView.smoothScrollToPosition(container.size() - 1);

        game = new GuessGameClass();
        game.startGame(languageCode);
        currentQuestion = game.createQuestion();
        new android.os.Handler().postDelayed(this::showQuestion, 2000);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String guess = guessInput.getText().toString().trim();

                    if (guess.isEmpty()) {
                        throw new IllegalArgumentException("Guess cannot be empty");
                    }

                    container.add(guess);
                    adapter.notifyItemInserted(container.size() - 1);
                    chatView.smoothScrollToPosition(container.size() - 1);

                    boolean isCorrect = currentQuestion.checkAnswer(guess);
                    if (isCorrect) {
                        Toast.makeText(GuessView.this, "Correct!", Toast.LENGTH_SHORT).show();
                        container.add("(Bot) Congratulations! You guessed correctly.");
                        adapter.notifyItemInserted(container.size() - 1);
                        chatView.smoothScrollToPosition(container.size() - 1);
                    } else {
                        String nextHint = currentQuestion.getNextHint();
                        if (nextHint != null) {
                            container.add(nextHint);
                            adapter.notifyItemInserted(container.size() - 1);
                            chatView.smoothScrollToPosition(container.size() - 1);
                        } else {
                            Toast.makeText(GuessView.this, "No more hints available", Toast.LENGTH_SHORT).show();
                        }
                    }
                    guessInput.setText("");
                } catch (IllegalArgumentException e) {
                    Toast.makeText(GuessView.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(v -> finish());

    }

    private void showQuestion() {
        container.add(currentQuestion.getQuestion());
        container.add(currentQuestion.getNextHint());
        adapter.notifyItemInserted(container.size() - 1);
        chatView.smoothScrollToPosition(container.size() - 1);
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

    private void startBackgroundMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.jingle_bells);
            mediaPlayer.setLooping(true);
        }

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
