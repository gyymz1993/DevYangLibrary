package com.gystar.master.MainUI.main.WorkOder.remark;

import com.gystar.master.bean.HistoryRemarkBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.pagination.BasePaginationView;

public class RemarkContract {
    public interface View extends BasePaginationView {
        void noMoreData();

        void haseNoData();

        void getLoadData(HistoryRemarkBean historyRemarkBean, boolean isRefesh);

        void remarkSucceed();
    }

    public interface Presenter extends BasePresenter<View> {

    }
}
