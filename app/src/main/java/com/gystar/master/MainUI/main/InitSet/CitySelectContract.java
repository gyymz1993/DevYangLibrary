package com.gystar.master.MainUI.main.InitSet;

import com.gystar.master.bean.CityListBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

import java.util.List;

public class CitySelectContract {
    interface View extends BaseView {
        void setCityData( List<CityListBean.DataBean> data);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
