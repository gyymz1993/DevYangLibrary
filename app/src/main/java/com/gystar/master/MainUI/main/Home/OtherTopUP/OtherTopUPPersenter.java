package com.gystar.master.MainUI.main.Home.OtherTopUP;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.ByconditionBean;
import com.gystar.master.bean.HistoryRecBean;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.T_;

import java.util.List;

public class OtherTopUPPersenter extends ListPagePresenter<OtherTopUPContract.View> implements OtherTopUPContract.Presenter {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public void getHistoryrec(String phone, final boolean reload) {
        if (!doPagination(reload)) {
            return;
        }
        api.getHistoryrec(phone, getPageNo())
                .compose(new ResponseTransformer<>())
                .subscribe(new PaginationSubscriber<HistoryRecBean>(mvpView, this, reload) {
                    @Override
                    public void requestError(String exception) {
                        T_.showCustomToast(exception);
                    }

                    @Override
                    protected void onDataNotNull(HistoryRecBean beginrechargeBean) {
                        if (mvpView != null) {
                            mvpView.getLoadData(beginrechargeBean, reload);
                        }
                    }

                    @Override
                    protected Object getCondition(HistoryRecBean beginrechargeBean, boolean dataNotNull) {
                        return getListResult(beginrechargeBean, dataNotNull);
                    }

                    @Override
                    protected List<HistoryRecBean.DataBean.PageBean> getListResult(HistoryRecBean beginrechargeBean, boolean dataNotNull) {
                        return beginrechargeBean.getData().getPage();
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


    public void searchUserForPhome(String phoneNumber) {
        api.getBycondition(phoneNumber)
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<ByconditionBean>(mvpView) {
                    @Override
                    public void success(ByconditionBean baseData) {
                        if (mvpView != null) {
                            if (baseData.data != null) {
                                mvpView.showBycondition(baseData);
                            } else {
                                mvpView.hideBycondition();
                            }
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        if (mvpView != null) {
                            mvpView.hideBycondition();
                        }
                    }
                });

    }


}
