package com.gystar.master.MainUI.main.WorkOder.remark;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.CityListBean;
import com.gystar.master.bean.HistoryRemarkBean;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.T_;

import java.util.List;

import retrofit2.http.Query;

public class RemarkPersenter extends ListPagePresenter<RemarkContract.View> implements RemarkContract.Presenter {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);


    public void getHistoryremark(String work_id, final boolean reload) {
        if (!doPagination(reload)) {
            return;
        }
        api.getHistoryremark(work_id, getPageNo())
                .compose(new ResponseTransformer<>())
                .subscribe(new PaginationSubscriber<HistoryRemarkBean>(mvpView, this, reload) {
                    @Override
                    public void requestError(String exception) {
                        T_.showCustomToast(exception);
                    }

                    @Override
                    protected void onDataNotNull(HistoryRemarkBean beginrechargeBean) {
                        if (mvpView != null) {
                            mvpView.getLoadData(beginrechargeBean, reload);
                        }
                    }

                    @Override
                    protected Object getCondition(HistoryRemarkBean beginrechargeBean, boolean dataNotNull) {
                        return getListResult(beginrechargeBean, dataNotNull);
                    }

                    @Override
                    protected List<HistoryRemarkBean.DataBean.PageBean> getListResult(HistoryRemarkBean beginrechargeBean, boolean dataNotNull) {
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


    public void getAddWorkorderRemark(String work_id, String remark) {
        api.getAddWorkorderRemark(work_id, remark).compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(mvpView) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        if (mvpView!=null){
                            mvpView.remarkSucceed();
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showCustomToast(exception);
                    }
                });


    }


}
