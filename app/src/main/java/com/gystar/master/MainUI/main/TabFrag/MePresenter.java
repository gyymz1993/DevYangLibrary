package com.gystar.master.MainUI.main.TabFrag;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.T_;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MePresenter extends ListPagePresenter<MeContract.View> implements MeContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public MePresenter() {
        super();
    }

    public void getOntermsCustomerList(int state,final boolean reload) {
        if (!doPagination(reload)) {
            return;
        }
        api.getOntermsCustomerList("9", state+"", getPageNo())
                .compose(new ResponseTransformer<>())
                .subscribe(new PaginationSubscriber<MyClientPerson>(this, reload) {
                    @Override
                    public void requestError(String exception) {
                        T_.showCustomToast(exception);

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
                        if (mvpView != null) {
                            mvpView.noMoreData();
                        }
                    }
                });

    }


}
