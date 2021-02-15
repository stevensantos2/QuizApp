package me.ss.alab2_02_12_21.model;

import me.ss.alab2_02_12_21.R;

public class AllQuestions {
    private int currentQuestion;
    public Question[] AllQuestions = {
            new Question(R.string.q_start, true),
            new Question(R.string.q_hokage, true),
            new Question(R.string.q_beast, false),
            new Question(R.string.q_avatar, false),
            new Question(R.string.q_ging, true)
    };
    public AllQuestions(){
        currentQuestion = 0;

    }
    public Question getQuestion(int index) {
        index = index % AllQuestions.length;
        return AllQuestions[index];
    }
}
