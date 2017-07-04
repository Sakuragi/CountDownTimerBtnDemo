package com.example.administrator.countdowntimerbtndemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.countdowntimerbtndemo.R;
import com.example.administrator.countdowntimerbtndemo.widget.CountDownTimerButton;

public class MainActivity extends AppCompatActivity {

    private CountDownTimerButton get_ver_btn;
    private EditText username_et;
    private EditText vercode_et;
    private EditText pwd_et;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initCountDownBtn();
    }

    private void initCountDownBtn() {
        get_ver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_ver_btn.setStartCountDownText("再次获取");
                get_ver_btn.startCountDownTimer(10000,1000);
            }
        });
    }

    private void initView(){
        get_ver_btn=(CountDownTimerButton) findViewById(R.id.btn_get_ver);
        username_et=(EditText) findViewById(R.id.et_username);
        vercode_et=(EditText) findViewById(R.id.et_ver_code);
        pwd_et=(EditText) findViewById(R.id.et_pw);
        login_btn=(Button) findViewById(R.id.btn_submit);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        get_ver_btn.onDestroy();
    }
}
