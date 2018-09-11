package com.gystar.master.MainUI.main.TabFrag;

import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;
import com.utils.gyymz.pagination.BasePaginationView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MeContract {
    interface View extends BasePaginationView {
        void getClientList(MyClientPerson  myClientPerson, boolean reload);

        void noMoreData();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
