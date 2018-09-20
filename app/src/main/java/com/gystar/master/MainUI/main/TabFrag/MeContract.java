package com.gystar.master.MainUI.main.TabFrag;

import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.pagination.BasePaginationView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MeContract {
    interface View extends BasePaginationView {
        void getClientList(MyClientPerson  myClientPerson, boolean reload);

        void noMoreData();

        void hasNoData();


        void loadFaild(String error);



        void buyTopPriceBuy();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
