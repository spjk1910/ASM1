package com.example.asm1.ProjectClass;

public class QuestionClass {
    private String question;
    private  String correctAnswer;
    private  String hints[];
    private int currentHintIndex;
    private  boolean isCorrect;

    public QuestionClass(String question, String correctAnswer, String[] hints) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.hints = hints;
        this.currentHintIndex = 0;
        this.isCorrect = false;
    }

    public String getNextHint() {
        if (currentHintIndex < hints.length) {
            return hints[currentHintIndex++];
        } else {
            return null;
        }
    }

    public boolean checkAnswer(String answer) {
        isCorrect = correctAnswer.equalsIgnoreCase(answer.trim());
        return isCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCurrentHintIndex(int currentHintIndex) {
        this.currentHintIndex = currentHintIndex;
    }
}
