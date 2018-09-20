package com.gystar.master.MainUI.main.Home.message;

import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.pagination.BasePaginationView;

public class MessageContract {
    interface View extends BasePaginationView {

        void hasNoData();
    }

    interface Presenter extends BasePresenter<MessageContract.View> {

    }
}
