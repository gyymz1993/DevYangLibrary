package com.gystar.master.MainUI.main.Home.TopUP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gystar.master.MainUI.main.MainTabActivity;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.ActivityUtils;
import com.utils.gyymz.wiget.NavigationBarView;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class PayResultActivity extends MVPBaseActivity {

    private static String KEY_ISSUCCEED = "KEY_ISSUCCEED";
    private boolean paySucceed;
    @BindView(R.id.id_ly_pay_succeed)
    protected LinearLayout lyPaySucceed;
    @BindView(R.id.id_ly_pay_fail)
    protected LinearLayout lyPayFail;
    @BindView(R.id.id_re_pay)
    protected TextView tvRePay;
    @BindView(R.id.id_tv_find_eare)
    protected TextView findCilent;

    @BindView(R.id.id_tv_me)
    protected TextView tvMe;


    @Override
    protected BasePresenter getDelegateClass() {
        return null;
    }

    public static Intent getLaunchIntent(Context context, boolean isSucceed) {
        Intent intent = new Intent(context, PayResultActivity.class);
        intent.putExtra(KEY_ISSUCCEED, isSucceed);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        showNavigationBarCanBack().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "支付结果");
        if (getIntent() != null) {
            paySucceed = getIntent().getBooleanExtra(KEY_ISSUCCEED, false);
        }
        if (paySucceed) {
            lyPaySucceed.setVisibility(View.VISIBLE);
            lyPayFail.setVisibility(View.GONE);
        } else {
            lyPaySucceed.setVisibility(View.GONE);
            lyPayFail.setVisibility(View.VISIBLE);
        }

        tvRePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findCilent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.popAllActivityUntillOne(MainTabActivity.class);
                MainTabActivity.instance.setCurrentItem(0);
            }
        });

        tvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.popAllActivityUntillOne(MainTabActivity.class);
                MainTabActivity.instance.setCurrentItem(1);
            }
        });

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }
}
