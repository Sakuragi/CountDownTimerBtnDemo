# CountDownTimerBtnDemo
这是一个倒计时按钮控件，基于CountDownTimer实现。
一个简单的使用方式：
开始倒计时10秒:
```
                get_ver_btn.startCountDownTimer(10000,1000);  //设置倒计时时间，间隔
                get_ver_btn.setStartCountDownText("再次获取");  //设置倒计时开始时按钮上的显示文字
                
```
最后在Destroy中对button进行处理：  
```
    protected void onDestroy() {
        super.onDestroy();
        get_ver_btn.onDestroy();
    }
```
