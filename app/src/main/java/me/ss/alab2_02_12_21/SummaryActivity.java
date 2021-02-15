package me.ss.alab2_02_12_21;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TextView textView;
    private Button q_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int score = getIntent().getIntExtra("score", -1);

        textView = findViewById(R.id.finalS_View);
        textView.setText(String.valueOf(score).toString());
        q_button = findViewById(R.id.q_button);


        q_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quit(v);
            }
        });
    }

    public void Quit(View v){
        SummaryActivity.this.finish();
        System.exit(0);
    }
}