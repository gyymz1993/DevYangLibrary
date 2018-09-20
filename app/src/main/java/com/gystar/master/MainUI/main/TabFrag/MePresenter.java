package com.gystar.master.MainUI.main.TabFrag;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.L_;
import com.utils.gyymz.utils.T_;

import java.util.List;

import retrofit2.http.Query;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MePresenter extends ListPagePresenter<MeContract.View> implements MeContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public MePresenter() {
        super();
    }

    public void getOntermsCustomerList(int state, final boolean reload) {
        if (!doPagination(reload)) {
            return;
        }
        api.getOntermsCustomerList(VaxPhoneAPP.getUserId(), state + "", getPageNo())
                .compose(new ResponseTransformer<>())
                .subscribe(new PaginationSubscriber<MyClientPerson>(mvpView, this, reload) {
                    @Override
                    public void requestError(String exception) {
                        T_.showCustomToast("requestError" + exception);
                    }

                    @Override
                    protected void onDataNotNull(MyClientPerson beginrechargeBean) {
                        if (mvpView != null) {
                            mvpView.getClientList(beginrechargeBean, reload);
                        }
                    }

                    @Override
                    protected Object getCondition(MyClientPerson beginrechargeBean, boolean dataNotNull) {
                        return getListResult(beginrechargeBean, dataNotNull);
                    }

                    @Override
                    protected List<MyClientPerson.DataBean.PageBean> getListResult(MyClientPerson beginrechargeBean, boolean dataNotNull) {
                        return beginrechargeBean.getData().getPage();
                    }


                    @Override
                    protected void onDataIsNull() {
                        T_.showToastReal("暂无数据");
                        if (mvpView != null) {
                            mvpView.hasNoData();
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


    public void getTopPriceBuy(String userId, String customerId) {
        api.getTopPriceBuy(userId, customerId)
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(mvpView) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        T_.showToastReal(baseData.msg);
                        if (mvpView != null) {
                            mvpView.buyTopPriceBuy();
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showToastReal(exception);
                    }
                });

    }

    public void getPricebuybatch(String customerIds) {
        api.getPricebuybatch(customerIds, VaxPhoneAPP.getUserId())
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(mvpView) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        T_.showToastReal(baseData.msg);
                        if (mvpView != null) {
                            mvpView.buyTopPriceBuy();
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showToastReal(exception);
                    }
                });

    }


}
