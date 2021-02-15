package me.ss.alab2_02_12_21.controller;

import me.ss.alab2_02_12_21.model.AllQuestions;

public class Score {
    public final int CORRECT_ANSWER = 20;
    public final int SKIP_QUESTION = -5;
    public final int WRONG_ANSWER = -5;
    public int score;

    public Score() { score = 0; }
    AllQuestions allQs = new AllQuestions();

    public void correctAnswer() {score += CORRECT_ANSWER;}
    public void skippedQuestion() {score += SKIP_QUESTION;}
    public void wrongAnswer() {score += WRONG_ANSWER;}

    public int getScore() {return score;}


}
