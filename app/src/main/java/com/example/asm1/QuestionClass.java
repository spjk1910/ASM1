package com.example.asm1;

public abstract class QuestionClass {
    private String question;
    private  String correctAnswer;
    private  String hints[];
    private int currentHintIndex;
    private  boolean isCorrect;

    public QuestionClass(String language, String question, String correctAnswer, String[] hints) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.hints = hints;
    }
}
