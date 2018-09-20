package com.utils.gyymz.mvp.base;


import android.view.View;

import com.lsjr.net.R;
import com.utils.gyymz.base.BaseAppCompatActivity;
import com.utils.gyymz.wiget.NavigationBarView;

import java.lang.reflect.ParameterizedType;


/**
 * Created by qiyue on 2016/4/5.
 */
public abstract class MVPBaseActivity<P extends BasePresenter> extends BaseAppCompatActivity implements BaseView {
    public P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = getDelegateClass();
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected abstract P getDelegateClass();

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {

    }


    protected NavigationBarView showNavigationBarCanBack() {
        showNavigationBarView().setBackgroundRes(NavigationBarView.NavigationViewType.LEFT_IV, R.drawable.return_icon)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
        return getNavigationBarView();
    }

}
