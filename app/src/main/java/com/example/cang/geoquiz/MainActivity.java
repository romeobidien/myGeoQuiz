package com.example.cang.geoquiz;

import android.content.res.Resources;
import android.service.carrier.CarrierService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button mTrueButton, mFalseButton, mNextButton, mPrevButton;
    TextView mTextViewQuestion;
    String[] mQuestions;
    boolean[] mAnswers;
    int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.button_true);
        mTrueButton.setOnClickListener(this);

        mFalseButton = (Button) findViewById(R.id.button_false);
        mFalseButton.setOnClickListener(this);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(this);

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(this);

        init();

        mTextViewQuestion = (TextView) findViewById(R.id.questionView);
        mTextViewQuestion.setText(mQuestions[mCurrentIndex]);
    }

    @Override
    public void onClick(View v) {
        String message;
        switch (v.getId()) {
            case R.id.button_true:
                message = "True Button";
                break;
            case R.id.button_false:
                message = "False Button";
                break;
            case R.id.prev_button:
                message = "Prev Button";
                break;
            case R.id.next_button:
                message = "Next Button";
                break;
            default:
                message = "";
        }

        message = message + " clicked";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        initQuestionBanks();

        mCurrentIndex = 0;
    }

    private void initQuestionBanks() {
        Resources res = getResources();
        mQuestions = res.getStringArray(R.array.questions);
        int[] tmp = res.getIntArray(R.array.answers);
        int length = tmp.length;
        mAnswers = new boolean[length];
        for (int i = 0; i < length; i++) {
            mAnswers[i] = tmp[i] == 1 ? true : false;
        }

    }
}
