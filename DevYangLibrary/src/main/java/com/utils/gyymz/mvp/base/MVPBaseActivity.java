package com.utils.gyymz.mvp.base;


import com.utils.gyymz.base.BaseAppCompatActivity;

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


}
