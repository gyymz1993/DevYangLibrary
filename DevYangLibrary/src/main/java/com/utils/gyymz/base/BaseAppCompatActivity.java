package com.utils.gyymz.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lsjr.net.R;
import com.utils.gyymz.statusbar.StatusBarCompat;
import com.utils.gyymz.utils.UIUtils;
import com.utils.gyymz.vary.ILoadViewFactory;
import com.utils.gyymz.vary.MyLoadViewFactory;
import com.utils.gyymz.wiget.NavigationBarView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.VISIBLE;

/**
 * 创建人：$ gyymz1993
 * 创建时间：2017/6/28 14:21
 */
public abstract class BaseAppCompatActivity extends RxActivity {

    private ILoadViewFactory loadViewFactory;
    private NavigationBarView navigationBarView;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initPresenter();
        afterCreate(savedInstanceState);
        //setTranslucentStatusBar();
        setImmersionBarBlack();
        // 初始化界面
        initView();
        // 初始化头部
        initTitle();
        // 初始化数据
        initData();
    }

    protected abstract void initPresenter();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化头部
     */
    protected abstract void initTitle();

    @SuppressLint("InflateParams")
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_a_base, null);
        super.setContentView(view);
        initDefaultView(layoutResID);
    }


    /**
     * 初始化默认布局的View
     *
     * @param layoutResId 子View的布局id
     */
    @SuppressLint("RestrictedApi")
    private void initDefaultView(int layoutResId) {
        FrameLayout container = findViewById(R.id.fl_activity_child_container);
        navigationBarView = findViewById(R.id.id_bar_view);
        navigationBarView.setVisibility(View.GONE);
        View childView = LayoutInflater.from(this).inflate(layoutResId, null);
        container.addView(childView, 0);
        loadViewFactory = new MyLoadViewFactory(childView);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除view绑定
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void openActivity(Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(this, pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivity(Class<?> pClass) {
        Intent intent = new Intent(this, pClass);
        startActivity(intent);
    }

    /*跳转到登录页面  登录成功回调到刚刚页面*/
    public void loginToServer(Class<?> c, Activity resultActivcity) {
        Intent loginIntent = new Intent(this, resultActivcity.getClass());
        loginIntent.putExtra("", c);
        startActivity(loginIntent);
        finish();
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void toastCenter(String text) {
        Toast toast = Toast.makeText(getApplicationContext(),
                text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public interface OnTopBarClickListener {
        void onClick();
    }

    public void showLoadingView() {
        loadViewFactory.madeLoadView().showLoading();
    }


    public void showRestoreView() {
        loadViewFactory.madeLoadView().restore();
    }


    /**
     * 设置全屏模式，并将状态栏设置为透明，支持4.4及以上系统
     */
    public void setTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

            setFullScreen();
        }
    }

    /**
     * 设置状态栏为浅色模式，状态栏上的图标都会变为深色。仅支持6.0及以上系统
     */
    public void setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 设置全屏模式
     */
    public void setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 设置系统状态颜色，仅支持6.0及以上系统
     */
    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(color);
        }
    }

    public NavigationBarView showNavigationBarView() {
        navigationBarView.setVisibility(VISIBLE);
        return navigationBarView;
    }

    public NavigationBarView hideNavigationBarView() {
        navigationBarView.setVisibility(View.GONE);
        return navigationBarView;
    }


    protected void setImmersionBarBlack() {
        StatusBarCompat.setStatusBarColor(this, UIUtils.getColor(R.color.gytheme));
    }


    public NavigationBarView getNavigationBarView() {
        return navigationBarView;
    }
}
