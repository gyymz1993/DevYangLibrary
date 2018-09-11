package com.gystar.master.MainUI.Login;


import com.gystar.master.bean.UserBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

public class LoginContract {
    interface View extends BaseView {
        void getCode();
        void doLogin(UserBean userBean);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
