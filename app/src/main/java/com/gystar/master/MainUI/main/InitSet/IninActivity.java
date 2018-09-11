package com.gystar.master.MainUI.main.InitSet;

import android.os.Bundle;

import com.utils.gyymz.base.BaseAppCompatFragment;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.FragmentActivity;
import com.utils.gyymz.wiget.NavigationBarView;

import vaxsoft.com.vaxphone.R;

import static com.gystar.master.MainUI.main.InitSet.CitySelectFragment.CITY_SELECT;

public class IninActivity extends FragmentActivity {
    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected BaseAppCompatFragment getFirstFragment() {
        return CitySelectFragment.newInstance(CITY_SELECT);
    }

    @Override
    protected BasePresenter getDelegateClass() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_init;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        showNavigationBarView().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "个人设置")
                .setText(NavigationBarView.NavigationViewType.RIGHT_TEXT, "跳过");
    }
}
