package com.utils.gyymz.mvp.base;


import android.os.Bundle;

import com.utils.gyymz.base.BaseAppCompatFragment;

/**
 * 懒加载的Fragment，在界面加载完毕并且可见之后会调用{@link #onVisible()}方法。
 */
public abstract class MVPLazyFragment<P extends BasePresenterImpl> extends MVPBaseFragment<P> implements BaseView {


    private boolean isVisibleBeforeInit = false;

    /**
     * 界面加载完毕并且可见之后调用此方法。<BR />
     * 注意：覆写init方法的时候不能去掉super.init(savedInstanceState);
     */
    protected abstract void onVisible();


    @Override
    protected void afterCreate(Bundle var1) {
        if (isVisibleBeforeInit) onVisible();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (mPresenter == null) {
                isVisibleBeforeInit = true;
            } else {
                onVisible();
            }
        }
    }

    protected FragmentActivity getHoldingActivity() {
        if (getActivity() instanceof FragmentActivity) {
            return (FragmentActivity) getActivity();
        } else {
            throw new ClassCastException("activity must extends BaseActivity");
        }
    }

    protected void pushFragment(BaseAppCompatFragment fragment) {
        getHoldingActivity().pushFragment(fragment);
    }

    protected void popFragment(BaseAppCompatFragment fragment) {
        getHoldingActivity().popFragment();
    }

}
