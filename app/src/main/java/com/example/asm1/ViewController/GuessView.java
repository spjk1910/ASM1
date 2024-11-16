package com.example.asm1.ViewController;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm1.ProjectClass.Adapter;
import com.example.asm1.ProjectClass.GuessGameClass;
import com.example.asm1.ProjectClass.PrefKeyClass;
import com.example.asm1.ProjectClass.QuestionClass;
import com.example.asm1.R;

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
    private int correctCount = 0;

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
        new android.os.Handler().postDelayed(this::showQuestion, 1000);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog exitDiaglog = new AlertDialog.Builder(GuessView.this).create();
                exitDiaglog.setCancelable(false);
                if (languageCode == "en")
                {
                    exitDiaglog.setTitle("Alert");
                    exitDiaglog.setMessage("Your progress will restart if you leave! Do you want to continue go back ?");
                    exitDiaglog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                            });
                    exitDiaglog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    container.clear();
                                    adapter.notifyDataSetChanged();
                                    finish();}
                            });
                } else {
                    exitDiaglog.setTitle("Cảnh Báo");
                    exitDiaglog.setMessage("Tiến trình chơi của bạn sẽ bị xóa nếu bạn thoát! Bạn có muốn tiếp tục thoát ?");
                    exitDiaglog.setButton(AlertDialog.BUTTON_POSITIVE, "Không",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                            });
                    exitDiaglog.setButton(AlertDialog.BUTTON_NEGATIVE, "Có",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    container.clear();
                                    adapter.notifyDataSetChanged();
                                    finish();}
                            });
                }
                exitDiaglog.show();
            }
        });

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String guess = guessInput.getText().toString().trim();

                    if (guess.isEmpty()) {
                        throw new IllegalArgumentException("Input cannot be empty");
                    }

                    container.add(guess);
                    adapter.notifyItemInserted(container.size() - 1);
                    chatView.smoothScrollToPosition(container.size() - 1);

                    boolean isCorrect = currentQuestion.checkAnswer(guess);
                    if (isCorrect) {
                        correctCount++;
                        AlertDialog continueDiaglog = new AlertDialog.Builder(GuessView.this).create();
                        continueDiaglog.setCancelable(false);
                        if (languageCode == "en")
                        {
                            continueDiaglog.setTitle("Correct! Answer is " + currentQuestion.getCorrectAnswer());
                            continueDiaglog.setMessage("Do you want to continue ?");
                            continueDiaglog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            container.clear();
                                            container.add("(Bot) You got " + correctCount + " strike(s) correct answers!");
                                            adapter.notifyDataSetChanged();
                                            currentQuestion.setCurrentHintIndex(0);
                                            game.startGame(languageCode);
                                            currentQuestion = game.createQuestion();
                                            continueDiaglog.dismiss();
                                            new android.os.Handler().postDelayed(GuessView.this::showQuestion, 1000);}
                                    });
                            continueDiaglog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            continueDiaglog.dismiss();
                                            showResult();}
                                    });
                        } else {
                            continueDiaglog.setTitle("Chính xác! Đáp án là " + currentQuestion.getCorrectAnswer());
                            continueDiaglog.setMessage("Bạn có muốn tiếp tục ?");
                            continueDiaglog.setButton(AlertDialog.BUTTON_POSITIVE, "Có",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            container.clear();
                                            container.add("(Bot) Bạn đạt " + correctCount + " câu trả lời đúng!");
                                            adapter.notifyDataSetChanged();
                                            game.startGame(languageCode);
                                            currentQuestion = game.createQuestion();
                                            continueDiaglog.dismiss();
                                            new android.os.Handler().postDelayed(GuessView.this::showQuestion, 1000);}
                                    });
                            continueDiaglog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            continueDiaglog.dismiss();
                                            showResult();}
                                    });
                        }
                        continueDiaglog.show();
                    } else {
                        String nextHint = currentQuestion.getNextHint();
                        if (nextHint != null) {
                            container.add(nextHint);
                            adapter.notifyItemInserted(container.size() - 1);
                            chatView.smoothScrollToPosition(container.size() - 1);
                        } else {
                            if (languageCode == "en") {
                                container.add("(Bot) No more hints available! You lose!");
                                container.add("(Bot) The answer is " + currentQuestion.getCorrectAnswer());
                            }
                            else {
                                container.add("(Bot) Không còn gợi ý nữa! Bạn thua!");
                                container.add("(Bot) Đáp án là " + currentQuestion.getCorrectAnswer());
                            }
                            adapter.notifyItemInserted(container.size() - 1);
                            chatView.smoothScrollToPosition(container.size() - 1);
                            new android.os.Handler().postDelayed(GuessView.this::showResult, 1000);
                        }
                    }
                    guessInput.setText("");
                } catch (IllegalArgumentException e) {
                    Log.d("Error", e.getMessage());
                }
            }
        });
    }

    private void showResult() {
        SharedPreferences prefs = getSharedPreferences(PrefKeyClass.getPrefsName(), MODE_PRIVATE);
        int languageIndex = prefs.getInt(PrefKeyClass.getLanguageKey(), 0);
        String languageCode = (languageIndex == 1) ? "vi" : "en";
        AlertDialog resultDialog = new AlertDialog.Builder(GuessView.this).create();
        resultDialog.setCancelable(false);
        String result = "";
        if (languageCode == "en") {
            resultDialog.setTitle("Result");
            result += "You got " + correctCount + " out of 15 questions correct!\n";
            if (correctCount >= 1 && correctCount < 5)
            {
                result += "You need to improve your knowledge!";
            } else if (correctCount >= 5 && correctCount < 10)
            {
                result += "You are good! Try to get more!";
            } else if (correctCount >= 10 && correctCount < 15) {
                result += "You are very smart! Keep it up!";
            } else if (correctCount >= 15) {
                result += "You are the best! Congratulations!";
            } else {
                result += "You are the worst! Try again!";
            }
            resultDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Back to menu",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            container.clear();
                            adapter.notifyDataSetChanged();
                            finish();}
                    });
        } else {
            resultDialog.setTitle("Kết Quả");
            result += "Bạn đúng " + correctCount + " trên 15 câu hỏi!\n";
            if (correctCount >= 1 && correctCount < 5)
            {
                result += "Bạn cần cải thiện kiến thức của mình!";
            } else if (correctCount >= 5 && correctCount < 10)
            {
                result += "Bạn làm tốt rồi! Cố gắng đạt được nhiều hơn!";
            } else if (correctCount >= 10 && correctCount < 15) {
                result += "Bạn rất thông minh! Tiếp tục phát huy!";
            } else if (correctCount >= 15) {
                result += "Bạn là người giỏi nhất! Chúc mừng bạn!";
            } else {
                result += "Bạn thật tệ! Hãy thử lại!";
            }
            resultDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Quay về",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            container.clear();
                            adapter.notifyDataSetChanged();
                            finish();}
                    });
        }
        resultDialog.setMessage(result);
        resultDialog.show();
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
