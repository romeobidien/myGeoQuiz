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
    private static final String KEY_CURRENT_INDEX = "current index";
    private static final String KEY_IS_DONE = "Is done";

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

        init();

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
            mIsDone = savedInstanceState.getBooleanArray(KEY_IS_DONE);
        }

        mTrueButton = (Button) findViewById(R.id.button_true);
        mTrueButton.setOnClickListener(this);

        mFalseButton = (Button) findViewById(R.id.button_false);
        mFalseButton.setOnClickListener(this);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(this);

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(this);

        mTextViewQuestion = (TextView) findViewById(R.id.questionView);

        updateUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putBooleanArray(KEY_IS_DONE, mIsDone);
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
        }
    }
}
