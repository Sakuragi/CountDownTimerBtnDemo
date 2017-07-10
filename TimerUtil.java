package com.example.administrator.countdowntimerbtndemo.utils;

/**
 * Created by Jim on 2017/6/15.
 */

public class TimerUtil extends CountDownTimer {

    private CountDownTimerListener mCountDownTimerListener;

    public TimerUtil(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (mCountDownTimerListener != null) {
            mCountDownTimerListener.startCount(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (mCountDownTimerListener != null) {
            mCountDownTimerListener.finishCount();
        }
    }

    public void setCountDownTimerListener(CountDownTimerListener listener) {
        mCountDownTimerListener = listener;
    }

    public interface CountDownTimerListener {
        void startCount(long millsUtilFinished);

        void finishCount();
    }

}
