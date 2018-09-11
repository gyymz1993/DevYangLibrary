package com.gystar.master;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDialNo;

public class GyCallActivity extends AppCompatActivity {

    private static final String KEY_NUMBET = "KEY_NUMBET";
    private LinearLayout layout_end_call;
    private Chronometer tv_time_count;
    private TextView id_tv_phone_nember;
    public static GyCallActivity instance;

    public static Intent getLaunchIntent(Context context, String number) {
        Intent intent = new Intent(context, GyCallActivity.class);
        intent.putExtra(KEY_NUMBET, number);
        return intent;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.gy_calling_fragmen);
        tv_time_count = findViewById(R.id.tv_time_count);
        LoadViewAll();
        if (getIntent() != null) {
            String number = getIntent().getStringExtra(KEY_NUMBET);
            id_tv_phone_nember.setText(number.substring(0, 7) + "****");
            OnClickBtnCall(number);
        }
    }

    private void LoadViewAll() {
        id_tv_phone_nember = findViewById(R.id.id_tv_phone_nember);
        tv_time_count = findViewById(R.id.tv_time_count);
        tv_time_count.setVisibility(View.GONE);
        layout_end_call = findViewById(R.id.layout_end_call);
        layout_end_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickBtnCallEnd();
                finish();
            }
        });
    }

    /**
     * 拨号
     */
    private void OnClickBtnCall(String number) {
        if (number.length() == 0) return;
        if (VaxPhoneSIP.m_objVaxVoIP.DialCall(number)) {
            StoreDialNo objStoreDialNo = new StoreDialNo();
            objStoreDialNo.SaveDialNo(number);
        } else {
            Toast.makeText(getApplicationContext(), "拨号失败", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onStartTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setVisibility(View.VISIBLE);
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - tv_time_count.getBase()) / 1000 / 60);
        tv_time_count.setFormat("0" + String.valueOf(hour) + ":%s");
        tv_time_count.start();
    }

    public void onStopTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        tv_time_count.stop();
    }

    /**
     * 挂断
     */
    private void OnClickBtnCallEnd() {
        //挂断
        VaxPhoneSIP.m_objVaxVoIP.DisconnectCall();
    }


    @Override
    public void onBackPressed() {

    }


    @Override
    public void finish() {
        OnClickBtnCallEnd();
        super.finish();
    }

    @Override
    public void onDestroy() {
        OnClickBtnCallEnd();
        Log.e("TAG", "onDestroy");
        super.onDestroy();
    }

}
