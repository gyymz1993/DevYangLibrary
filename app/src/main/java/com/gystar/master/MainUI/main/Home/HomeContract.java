package com.gystar.master.MainUI.main.Home;

import com.gystar.master.bean.HomeDataBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

public class HomeContract {
    interface View extends BaseView {
        void getHomeData(HomeDataBean baseData);

        void buyTopPriceBuy();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
