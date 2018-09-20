package com.gystar.master.MainUI.main.WorkOder;

import com.gystar.master.bean.WorkOrderBean;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.pagination.BasePaginationView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WorkOrderContract {
    interface View extends BasePaginationView {

        void noMoreData();

        void haseNoData();

        void getLoadData(WorkOrderBean workOrderBean,boolean isRefesh);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
