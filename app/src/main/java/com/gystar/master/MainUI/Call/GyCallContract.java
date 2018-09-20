package com.gystar.master.MainUI.Call;

import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

public class GyCallContract {

    public interface View extends BaseView {

        void onCallEnd();
    }

    public  interface Presenter extends BasePresenter<View> {
    }
}