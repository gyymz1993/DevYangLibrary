package com.gystar.master.MainUI.main.Personal;

import com.gystar.master.bean.PersonalBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalContract {
    interface View extends BaseView {

        void getPersonalData(PersonalBean personalBean);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
