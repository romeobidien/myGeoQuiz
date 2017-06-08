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
    boolean[] mIsDone;
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

        updateUI();
    }

    @Override
    public void onClick(View v) {
        int len = mQuestions.length;
        switch (v.getId()) {
            case R.id.button_true:
                checkAnser(true);
                break;
            case R.id.button_false:
                checkAnser(false);
                break;
            case R.id.prev_button:
                mCurrentIndex = (mCurrentIndex + len - 1)%len;
                updateUI();
                break;
            case R.id.next_button:
                mCurrentIndex = (mCurrentIndex + 1)%len;
                updateUI();
                break;
            default:
        }
    }

    private void updateUI() {
        mTextViewQuestion.setText(mQuestions[mCurrentIndex]);
        setAnserAvailability(!mIsDone[mCurrentIndex]);
    }

    private void setAnserAvailability(boolean visible) {
        mTrueButton.setEnabled(visible);
        mFalseButton.setEnabled(visible);
    }

    private void checkAnser(boolean answer) {
        String message;
        if (mAnswers[mCurrentIndex] == answer) {
            message = getString(R.string.correct_toast);
        } else {
            message = getString(R.string.incorrect_toast);
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        setAnserAvailability(false);
        mIsDone[mCurrentIndex] = true;
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
        mIsDone = new boolean[length];
        for (int i = 0; i < length; i++) {
            mAnswers[i] = tmp[i] == 1 ? true : false;
            mIsDone[i] = false;
        }
    }
}
