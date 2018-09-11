package com.utils.gyymz.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utils.gyymz.base.BaseAppCompatFragment;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class MVPBaseFragment<P extends BasePresenterImpl> extends BaseAppCompatFragment implements  BaseView {
    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void iniPresenter() {
        mPresenter = getDelegateClass();
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }


    @Override
    protected void initView() {

    }

    protected abstract P getDelegateClass();
}
