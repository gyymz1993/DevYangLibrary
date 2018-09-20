package com.gystar.master.MainUI.main.Home.TopUP;

import com.gystar.master.bean.TopBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

import java.util.List;

public class TopContract {
    interface View extends BaseView {
        void getTopBeanList(List<TopBean.DataBean> data);
    }

    interface Presenter extends BasePresenter<TopContract.View> {

    }
}
