package com.example.cang.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    Button mShowButton;
    TextView mAnswerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerTextView = (TextView) findViewById(R.id.cheat_textView);
        mShowButton = (Button) findViewById(R.id.show_cheat_button);

        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answer = CheatActivity.this.getIntent().getBooleanExtra(MainActivity.EXTRA_ANSWER, false);
                mAnswerTextView.setText(answer + "");
            }
        });
    }
}
