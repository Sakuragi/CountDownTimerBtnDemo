package com.example.administrator.countdowntimerbtndemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.example.administrator.countdowntimerbtndemo.R;
import com.example.administrator.countdowntimerbtndemo.utils.TimerUtil;


/**
 * Created by Jim on 2017/6/15.
 */

public class CountDownTimerButton extends android.support.v7.widget.AppCompatButton {

    private final String TAG = CountDownTimerButton.class.getSimpleName();
    private final int STATE_STARTCOUNT = 0;
    private final int STATE_STOPCOUNT = 1;
    private String startCountDownStateColor;
    private String stopCountDownStateColor;
    private String startCountDownText;
    private String stopCountDownText;
    private TimerUtil mTimerUtil;
    private CountDownStateChangeListener mCountDownStateChangeListener;
    public interface CountDownStateChangeListener{
        void onStartCount(long millsUtilFinished);
        void onFinishCount();
    }

    public CountDownTimerButton(Context context) {
        this(context, null);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        changeBackGroundColor(STATE_STOPCOUNT);

    }

    public void onDestroy() {
        Log.d(TAG, "run in onDestroy,currentTimeMillis: " + System.currentTimeMillis());
        if (mTimerUtil != null) {
            mTimerUtil.cancel();
            mTimerUtil = null;
        }
        initState();
    }

    private void initState() {
        changeBackGroundColor(STATE_STOPCOUNT);
        setClickable(true);
        setText(stopCountDownText);
    }

    public void setCountDownStateChangeListener(CountDownStateChangeListener listener){
        mCountDownStateChangeListener=listener;
    }

    public void setStartCountDownStateColor(String color) {
        startCountDownStateColor = color;
    }

    public void setStopCountDownColor(String color) {
        stopCountDownStateColor = color;
    }

    public void setStartCountDownText(String startCountDownText) {
        this.startCountDownText = startCountDownText;
    }

    private void setStopCountDownText(String stopCountDownText) {
        this.stopCountDownText = stopCountDownText;
    }

    public void startCountDownTimer(final long millisInFuture, final long countDownInterval) {
        if (mTimerUtil != null) {
            mTimerUtil.cancel();
            mTimerUtil = null;
        }
        setStopCountDownText(getText().toString());
        mTimerUtil = new TimerUtil(millisInFuture, countDownInterval);
        mTimerUtil.setCountDownTimerListener(new TimerUtil.CountDownTimerListener() {
            @Override
            public void startCount(long millsUtilFinished) {
                if (mCountDownStateChangeListener!=null){
                    mCountDownStateChangeListener.onStartCount(millsUtilFinished);
                    return;
                }
                changeBackGroundColor(STATE_STARTCOUNT);
                setClickable(false);
                setText(startCountDownText+"(" + millsUtilFinished / 1000 + ")");
            }

            @Override
            public void finishCount() {
                if (mCountDownStateChangeListener!=null){
                    mCountDownStateChangeListener.onFinishCount();
                    return;
                }
                setText(stopCountDownText);
                setClickable(true);
                changeBackGroundColor(STATE_STOPCOUNT);
            }
        });
        mTimerUtil.start();
    }

    private void changeBackGroundColor(int state) {
        try {
            GradientDrawable drawable = (GradientDrawable) getBackground();
            switch (state) {
                case STATE_STARTCOUNT:
                    if (TextUtils.isEmpty(startCountDownStateColor)) {
                        drawable.setColor(Color.parseColor("#d3d2d6"));
                    } else {
                        drawable.setColor(Color.parseColor(startCountDownStateColor));
                    }
                    break;
                case STATE_STOPCOUNT:
                    if (TextUtils.isEmpty(stopCountDownStateColor)) {
                        drawable.setColor(Color.parseColor("#ffffff"));
                    } else {
                        drawable.setColor(Color.parseColor(stopCountDownStateColor));
                    }
                    break;
                default:
                    break;
            }
        } catch (ClassCastException e) {
            e.fillInStackTrace();
        }

    }


}
