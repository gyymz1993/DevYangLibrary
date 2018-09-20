package com.gystar.master.MainUI.full;


import android.os.Bundle;

import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.L_;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class FuliActivity extends MVPBaseActivity {


    @BindView(R.id.meizi_list)
    public MeiziListView meiziListView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fuli;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }


    @Override
    public void initView() {
        L_.e(meiziListView + "");
        getLifecycle().addObserver(meiziListView);

    }


    @Override
    protected MyCollectPresenter getDelegateClass() {
        return null;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }
}
