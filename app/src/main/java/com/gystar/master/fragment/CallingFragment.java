package com.gystar.master.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gystar.master.CallActivity;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class CallingFragment extends CustomTabFragment implements View.OnClickListener {
    public static CallingFragment mCallFragment = null;
    private LinearLayout layout_end_call;
    private Chronometer tv_time_count;
    private TextView id_tv_phone_nember;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gy_calling_fragmen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mCallFragment = this;
        LoadViewAll(view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mCallFragment = null;
        super.onDestroy();
    }


    private void KeepScreenOn(boolean bEnable) {
        Activity objFragmentActivity = getActivity();

        if (objFragmentActivity != null) {
            Window objWindow = objFragmentActivity.getWindow();

            if (bEnable) {
                objWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                return;
            }

            objWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }


    public void setPhoneNumber(String phone_nember) {
        this.id_tv_phone_nember.setText(phone_nember);
    }
///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void OnFragmentActivated() {
        mCallFragment = this;
        KeepScreenOn(true);
        super.OnFragmentActivated();
    }

    public void OnFragmentDeactivated() {
        KeepScreenOn(false);
        mCallFragment = null;
        super.OnFragmentDeactivated();
    }


    private void LoadViewAll(View view) {
        id_tv_phone_nember = view.findViewById(R.id.id_tv_phone_nember);
        tv_time_count = view.findViewById(R.id.tv_time_count);
        tv_time_count.setVisibility(View.GONE);
        layout_end_call = view.findViewById(R.id.layout_end_call);
        layout_end_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickBtnCallEnd();
                CallActivity activity = (CallActivity) getActivity();
                activity.showDialpadFragment();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 挂断
     */
    private void OnClickBtnCallEnd() {
        //挂断
        VaxPhoneSIP.m_objVaxVoIP.DisconnectCall();
        //DialIconSelected(false);
        //DialBtnText.setText("DIAL");
    }

    public void onStartTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setVisibility(View.VISIBLE);
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - tv_time_count.getBase()) / 1000 / 60);
        tv_time_count.setFormat("0" + String.valueOf(hour) + ":%s");
        tv_time_count.start();
        //tv_time_count.start();
        // CharSequence text=tv_time_count.getText();
        // char charAt=text.charAt(text.length()-1);
    }

    public void onStopTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        tv_time_count.stop();
    }

    public void onResetTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setBase(SystemClock.elapsedRealtime());
    }

}

