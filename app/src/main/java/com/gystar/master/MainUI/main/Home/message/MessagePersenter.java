package com.gystar.master.MainUI.main.Home.message;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.MessageBean;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.T_;

import java.util.List;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class MessagePersenter extends ListPagePresenter<MessageContract.View> implements MessageContract.Presenter {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public void getgetQueryInformation(final boolean reload) {
        if (!doPagination(reload)) {
            return;
        }
        api.getQueryInformation(VaxPhoneAPP.getUserId())
                .compose(new ResponseTransformer<>())
                .subscribe(new PaginationSubscriber<MessageBean>(mvpView,this, reload) {
                    @Override
                    public void requestError(String exception) {
                        T_.showCustomToast(exception);
                    }

                    @Override
                    protected void onDataNotNull(MessageBean beginrechargeBean) {
                        if (mvpView != null) {
                            //  mvpView.getClientList(beginrechargeBean, reload);
                            T_.showCustomToast("onDataNotNull");
                        }
                    }

                    @Override
                    protected Object getCondition(MessageBean messageBean, boolean dataNotNull) {
                        return getListResult(messageBean, dataNotNull);
                    }

                    @Override
                    protected List getListResult(MessageBean messageBean, boolean dataNotNull) {
                        return messageBean.data;
                    }

                    @Override
                    protected void onDataIsNull() {
                        if (mvpView != null) {
                            T_.showCustomToast("暂无数据");
                            mvpView.hasNoData();
                        }
                    }

                    @Override
                    protected void onNotMoreData() {
                        T_.showCustomToast("没有更多数据");
                    }

                });

    }


}
