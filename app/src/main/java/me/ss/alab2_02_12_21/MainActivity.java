package me.ss.alab2_02_12_21;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import me.ss.alab2_02_12_21.controller.NextQuestion;
import me.ss.alab2_02_12_21.controller.Score;
import me.ss.alab2_02_12_21.model.AllQuestions;
import me.ss.alab2_02_12_21.model.Question;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_INDEX = "GAME_MAIN_ACTIVITY";

    private TextView questionView=null;
    private TextView scoreView=null;
    private Button t_button = null;
    private Button f_button = null;
    private Button n_button = null;
    private Button d_button = null;

    private final String T_TAG = "IN_ONCLICK";
    private final String F_TAG = "IN_ONCLICK";
    private final String N_TAG = "IN_ONCLICK";
    private final String D_TAG = "IN_ONCLICK";

    private boolean flag = false;

    AllQuestions allQuestions= new AllQuestions();
    NextQuestion nextQuestion= new NextQuestion();
    Score score = new Score();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        questionView = findViewById(R.id.questionView);
        scoreView = findViewById(R.id.scoreView);

        questionView.setText(R.string.q_start);
        scoreView.setText(R.string.initial_S);

        t_button = findViewById(R.id.t_button);
        f_button = findViewById(R.id.f_button);
        n_button = findViewById(R.id.n_button);
        d_button = findViewById(R.id.d_button);



        t_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(T_TAG,"Clicked True");
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                question = allQuestions.getQuestion(index);
                if (index != 0) {
                    if (question.isQuestionTrue() == true) {
                        score.correctAnswer();
                        scoreView.setText(String.valueOf(score.getScore()));
                    } else {
                        score.wrongAnswer();
                        scoreView.setText(String.valueOf(score.getScore()));
                    }
                } else {
                    scoreView.setText("Please click next to continue.");
                }
                flag = true;
            }
        });

        f_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(F_TAG,"Clicked False");
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                question = allQuestions.getQuestion(index);
                if (question.isQuestionTrue() == false) {
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                } else {
                    score.wrongAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                }
                flag = true;
            }
        });

        n_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(N_TAG,"Clicked Next");
                int pre = nextQuestion.getCurrentQuestion();
                int index = nextQuestion.getNextQuestionIndex();
                Question question = null;
                scoreView.setText(String.valueOf(score.getScore()));
                if (pre < index){
                    try{
                        question = allQuestions.getQuestion(index);
                    } catch (Exception e) {
                        Log.d(TAG_INDEX,"No more questions, you're done!");
                    }
                    questionView.setText(allQuestions.getQuestion(index).getQuestionID());
                    if (! flag) {
                        score.skippedQuestion();
                        scoreView.setText(String.valueOf(score.getScore()));
                    }
                    flag = false;
                } else{
                    questionView.setText("YOU'RE DONE!");
                }

            }
        });
        d_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(D_TAG,"Clicked Done");
                SummaryActivity(v);
            }
        });

    }

    public void SummaryActivity(View v){
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra("score", score.getScore());
        startActivity(intent);
        MainActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}