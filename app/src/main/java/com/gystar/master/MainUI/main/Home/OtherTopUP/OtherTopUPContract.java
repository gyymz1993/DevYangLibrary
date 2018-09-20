package com.gystar.master.MainUI.main.Home.OtherTopUP;

import com.gystar.master.bean.ByconditionBean;
import com.gystar.master.bean.HistoryRecBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.pagination.BasePaginationView;

public class OtherTopUPContract {
    interface View extends BasePaginationView {
        void noMoreData();

        void haseNoData();

        void getLoadData(HistoryRecBean historyRecBean, boolean isRefesh);


        void showBycondition(ByconditionBean byconditionBean);

        void hideBycondition();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
