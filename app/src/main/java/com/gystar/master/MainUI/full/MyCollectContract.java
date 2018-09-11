package com.gystar.master.MainUI.full;

import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

public class MyCollectContract {

    public interface View extends BaseView {
        void getDataSucceed(String result);
    }

    public  interface Presenter extends BasePresenter<View> {
        void getAppBasicInfo();
    }
}