package com.gystar.master.MainUI.main.WorkOder;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.MyClientPerson;
import com.gystar.master.bean.WorkOrderBean;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.T_;

import java.util.List;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WorkOrderPresenter extends ListPagePresenter<WorkOrderContract.View> implements WorkOrderContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public WorkOrderPresenter() {
        super();
    }

    public void getWorkOrderShow(final boolean reload) {
        if (!doPagination(reload)) {
            return;
        }
        api.getWorkOrderShow(VaxPhoneAPP.getUserId(), getPageNo())
                .compose(new ResponseTransformer<>())
                .subscribe(new PaginationSubscriber<WorkOrderBean>(mvpView,this, reload) {
                    @Override
                    public void requestError(String exception) {
                        T_.showCustomToast(exception);
                    }

                    @Override
                    protected void onDataNotNull(WorkOrderBean dataBeanCallBack) {
                        if (mvpView != null) {
                            mvpView.getLoadData(dataBeanCallBack,reload);
                        }
                    }

                    @Override
                    protected Object getCondition(WorkOrderBean dataBeanCallBack, boolean dataNotNull) {
                        return getListResult(dataBeanCallBack, dataNotNull);
                    }

                    @Override
                    protected List<WorkOrderBean.DataBean.PageBean> getListResult(WorkOrderBean dataBeanCallBack, boolean dataNotNull) {
                        return dataBeanCallBack.data.getPage();
                    }


                    @Override
                    protected void onDataIsNull() {
                        T_.showToastReal("暂无数据");
                        if (mvpView != null) {
                            mvpView.haseNoData();
                        }
                    }

                    @Override
                    protected void onNotMoreData() {
                        if (mvpView != null) {
                            mvpView.noMoreData();
                        }
                    }

                });

    }


}
