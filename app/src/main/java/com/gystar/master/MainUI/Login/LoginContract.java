package com.gystar.master.MainUI.Login;


import com.gystar.master.bean.UserBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

public class LoginContract {
    interface View extends BaseView {
        void getCode();
        void doLogin(UserBean userBean);

        /**
         * 加载完成时隐藏加载框
         */
        void hideLoading();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
