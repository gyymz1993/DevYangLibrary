package com.gystar.master.CustomViews;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.utils.gyymz.utils.UIUtils;


public class CodeButton extends CountDownTimer {


    private TextView mBtnCode;
    private boolean isOnTick;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CodeButton(TextView button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mBtnCode = button;
    }

    public boolean isOnTick() {
        return isOnTick;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        isOnTick = true;
        mBtnCode.setClickable(false);
        mBtnCode.setEnabled(false);
        mBtnCode.setText(millisUntilFinished / 1000 + "秒" + "后再发送");
        mBtnCode.setTextColor(UIUtils.getColor("#FFFFFF"));
        //mBtnCode.setTextSize(UIUtils.dp2px(12));
        SpannableString spannableString = new SpannableString(mBtnCode.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.WHITE);
        spannableString.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); //时间设为红色
        mBtnCode.setText(spannableString);
    }

    @Override
    public void onFinish() {
        isOnTick = false;
        mBtnCode.setText("重新发送");
        mBtnCode.setClickable(true);
        mBtnCode.setEnabled(true);
        mBtnCode.setTextColor(UIUtils.getColor("#FFFFFF"));
    }
}
