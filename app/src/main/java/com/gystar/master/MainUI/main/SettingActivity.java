package com.gystar.master.MainUI.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.gystar.master.MainUI.Call.DialpadActivity;
import com.gystar.master.MainUI.main.InitSet.IninActivity;
import com.utils.gyymz.BaseApplicationCompat;
import com.utils.gyymz.listener.UserSensorEventListener;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.wiget.MyOneLineView;
import com.utils.gyymz.wiget.NavigationBarView;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class SettingActivity extends MVPBaseActivity implements MyOneLineView.OnRootClickListener, MyOneLineView.OnArrowClickListener {


    @BindView(R.id.ll_mine)
    LinearLayout llMine;

    @Override
    protected BasePresenter getDelegateClass() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

        showNavigationBarCanBack().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "用户设置");
        //使用示例，通过Java代码来创建MyOnelineView
        //icon + 文字 + 箭头
        llMine.addView(new MyOneLineView(this)
                .initMine(0, "个人设置", "", true)
                .setOnRootClickListener(this, 1));


        llMine.addView(new MyOneLineView(this)
                .initMine(0, "自由拨号", "", true)
                .setOnRootClickListener(this, 3));

        llMine.addView(new MyOneLineView(this)
                .initMine(0, "退出登录", "", true)
                .setOnRootClickListener(this, 2));


        BaseApplicationCompat.instance().setStudio(this, new UserSensorEventListener() {
            @Override
            public void userOnSensorEventListener() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // openActivity(MessageActivity.class);
                    }
                });
            }
        });
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    @Override
    public void onArrowClick(View view) {

    }

    @Override
    public void onRootClick(View view) {
        switch ((int) view.getTag()) {
            case 1:
                openActivity(IninActivity.class);
                break;
            case 2:
                break;
            case 3:
                openActivity(DialpadActivity.class);
                break;
        }
    }
}
