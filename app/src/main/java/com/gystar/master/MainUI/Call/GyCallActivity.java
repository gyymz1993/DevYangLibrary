package com.gystar.master.MainUI.Call;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gystar.master.MainUI.main.TabFrag.TheBiddingFragment;
import com.gystar.master.Utils.RegexpUtils;
import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.L_;
import com.utils.gyymz.utils.T_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDialNo;

public class GyCallActivity extends MVPBaseActivity<GyCallPresenter> implements GyCallContract.View {

    private static final String KEY_NUMBET = "KEY_NUMBET";
    private static final String KEY_CILEENT = "KEY_CILEENT";
    private static final String KEY_PAGEBEAN = "KEY_PAGEBEAN";
    MyClientPerson.DataBean.PageBean pageBean;
    public static GyCallActivity instance;
    private boolean speakerphoneOn;
    @BindView(R.id.id_ly_mt)
    LinearLayout lyMt;
    @BindView(R.id.tv_time_count)
    Chronometer tv_time_count;
    @BindView(R.id.id_tv_phone_nember)
    TextView id_tv_phone_nember;
    @BindView(R.id.layout_end_call)
    LinearLayout layout_end_call;
    @BindView(R.id.id_tv_client_statu)
    TextView clientStatu;
    @BindView(R.id.id_tv_client_name)
    TextView clientName;

    @BindView(R.id.id_up_call_layout)
    RelativeLayout upCallLayout;

    @BindView(R.id.id_up_end_layout)
    LinearLayout upEndLayout;
    @BindView(R.id.id_tv_back)
    TextView idTvBack;
    @BindView(R.id.id_tv_label)
    TextView idTvGolabel;


    String strCilentName;
    String strCilentNumber;

    public static Intent getLaunchIntent(Context context, String number, String ciletName) {
        Intent intent = new Intent(context, GyCallActivity.class);
        intent.putExtra(KEY_NUMBET, number);
        intent.putExtra(KEY_CILEENT, ciletName);
        return intent;
    }

    public static Intent getLaunchIntent(Context context, MyClientPerson.DataBean.PageBean pageBean) {
        Intent intent = new Intent(context, GyCallActivity.class);
        intent.putExtra(KEY_PAGEBEAN, pageBean);
        return intent;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.gy_calling_fragmen;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        instance = this;
        LoadViewAll();
        if (getIntent() != null) {
            pageBean = (MyClientPerson.DataBean.PageBean) getIntent().getSerializableExtra(KEY_PAGEBEAN);
        }
        if (pageBean == null) {
            strCilentNumber = getIntent().getStringExtra(KEY_NUMBET);
            strCilentName = getIntent().getStringExtra(KEY_CILEENT);
        } else {
            strCilentNumber = pageBean.getPhone();
            strCilentName = pageBean.getName();
        }
        //id_tv_phone_nember.setText(strCilentNumber.substring(0, 7) + "****");
        onStartTimeCount();
        clientName.setText(strCilentName);
        OnClickBtnCall(strCilentNumber);
    }

    private void setSpeakerphoneOn(boolean on) {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (on) {
            audioManager.setSpeakerphoneOn(true);
        } else {
            audioManager.setSpeakerphoneOn(false);//关闭扬声器
            //把声音设定成Earpiece（听筒）出来，设定为正在通话中
            audioManager.setMode(AudioManager.MODE_IN_CALL);
        }
        speakerphoneOn = !speakerphoneOn;
    }

    private void LoadViewAll() {
        tv_time_count.setVisibility(View.GONE);
        layout_end_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickBtnCallEnd();
                onStopTimeCount();
            }
        });
        lyMt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSpeakerphoneOn(!speakerphoneOn);
            }
        });

        idTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        idTvGolabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = LabelStickerActivity.getLaunchIntent(GyCallActivity.this, pageBean);
                startActivity(launchIntent);
                //openActivity(LabelStickerActivity.class);
            }
        });
    }

    /**
     * 拨号
     */
    private void OnClickBtnCall(String number) {
        if (number.length() == 0) return;
        if (VaxPhoneSIP.m_objVaxVoIP.DialCall(number)) {
            clientStatu.setText("正在拨号");
            StoreDialNo objStoreDialNo = new StoreDialNo();
            objStoreDialNo.SaveDialNo(number);
        } else {
            Toast.makeText(getApplicationContext(), "拨号失败", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onStartTimeCount() {
        if (tv_time_count == null) return;
        clientStatu.setText("正在通话");
        tv_time_count.setVisibility(View.VISIBLE);
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - tv_time_count.getBase()) / 1000 / 60);
        tv_time_count.setFormat("0" + String.valueOf(hour) + ":%s");
        tv_time_count.start();
    }

    public void onStopTimeCount() {
        if (tv_time_count == null) return;
        L_.e("onStopTimeCount:" + RegexpUtils.getMinutesTime(tv_time_count.getText().toString()));
        mPresenter.getCallCustomer(pageBean.getId() + "", RegexpUtils.getMinutesTime(tv_time_count.getText().toString()) + "");
        clientStatu.setText("通话结束");
        // tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
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
    public void onDestroy() {
        OnClickBtnCallEnd();
        super.onDestroy();
    }

    @Override
    protected GyCallPresenter getDelegateClass() {
        return new GyCallPresenter();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showNetWorkErrorView() {
    }

    @Override
    public void onCallEnd() {
        TheBiddingFragment.instance.onfreshAllFragment();
        upEndLayout.setVisibility(View.VISIBLE);
        upCallLayout.setVisibility(View.GONE);
        // finish();
    }
}
