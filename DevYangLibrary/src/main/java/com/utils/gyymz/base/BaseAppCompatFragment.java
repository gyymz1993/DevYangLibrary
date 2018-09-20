//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.utils.gyymz.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lsjr.net.R;
import com.utils.gyymz.vary.ILoadViewFactory;
import com.utils.gyymz.vary.MyLoadViewFactory;
import com.utils.gyymz.wiget.LoadingDialog;
import com.utils.gyymz.wiget.NavigationBarView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.VISIBLE;

public abstract class BaseAppCompatFragment extends Fragment {
    public View mView;
    private Unbinder unbinder;
    protected boolean isFirstCreate = true;
    private ILoadViewFactory loadViewFactory;
    private NavigationBarView navigationBarView;
    private LoadingDialog progressDialog;

    public BaseAppCompatFragment() {
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // this.mView = inflater.inflate(getLayoutId(), container, false);
        mView = inflater.inflate(R.layout.fragment_a_base, container, false);
        ViewGroup parent = (ViewGroup) this.mView.getParent();
        if (null != parent) {
            parent.removeView(this.mView);
        }
        addChildView(inflater);
        this.unbinder = ButterKnife.bind(this, this.mView);
        progressDialog = new LoadingDialog(getActivity());
        this.iniPresenter();
        this.afterCreate(savedInstanceState);
        this.initView();
        this.initTitle();
        this.initData();
        return this.mView;
    }

    protected abstract void iniPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle var1);


    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    /**
     * 添加子Fragment的布局文件
     *
     * @param inflater
     */
    private void addChildView(LayoutInflater inflater) {
        FrameLayout container = mView.findViewById(R.id.fl_activity_child_container);
        navigationBarView = mView.findViewById(R.id.id_bar_view);
        navigationBarView.setVisibility(View.GONE);
        if (getLayoutId() == 0) return;
        View child = inflater.inflate(getLayoutId(), null);
        loadViewFactory = new MyLoadViewFactory(child);
        container.addView(child, 0);
    }


    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            this.onVisible();
        } else {
            this.onInvisible();
        }

    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            this.onInvisible();
        } else {
            this.onVisible();
        }

    }

    protected void onVisible() {
        if (this.isFirstCreate) {
            this.lazyLoad();
            this.isFirstCreate = false;
        }

    }

    public void openActivity(Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }

    public void openActivity(Class<?> pClass) {
        Intent intent = new Intent(this.getActivity(), pClass);
        this.startActivity(intent);
    }


    protected void lazyLoad() {
    }

    protected void onInvisible() {
    }


    public NavigationBarView showNavigationBarView() {
        navigationBarView.setVisibility(VISIBLE);
        return navigationBarView;
    }

    public NavigationBarView hideNavigationBarView() {
        navigationBarView.setVisibility(View.GONE);
        return navigationBarView;
    }


    public NavigationBarView getNavigationBarView() {
        return navigationBarView;
    }


    /**
     * 显示加载布局
     */
    public void showLoadingView() {
        loadViewFactory.madeLoadView().showLoading();
    }


    /**
     * 显示原来布局
     */
    public void showRestoreView() {
        loadViewFactory.madeLoadView().restore();
    }


    /**
     * 显示加载的ProgressDialog
     */
    public void showProgressDialog() {
        progressDialog.showProgressDialog();
    }

    /**
     * 显示有加载文字ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param text 需要显示的文字
     */
    public void showProgressDialogWithText(String text) {
        progressDialog.showProgressDialogWithText(text);
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载成功需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressSuccess(String message, long time) {
        progressDialog.showProgressSuccess(message, time);
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressSuccess(String message) {
        progressDialog.showProgressSuccess(message);
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载失败需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressFail(String message, long time) {
        progressDialog.showProgressFail(message, time);
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressFail(String message) {
        progressDialog.showProgressFail(message);
    }

    /**
     * 隐藏加载的ProgressDialog
     */
    public void dismissProgressDialog() {
        progressDialog.dismissProgressDialog();
    }


}
