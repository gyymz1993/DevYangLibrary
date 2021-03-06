package com.gystar.master.MainUI.main.Home;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.bean.HomeDataBean;
import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.PaginationSubscriber;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;
import com.utils.gyymz.pagination.presenter.ListPagePresenter;
import com.utils.gyymz.utils.T_;

import java.util.List;

import retrofit2.http.Query;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public HomePresenter() {
        super();
    }

    public void getHomeshow() {
        api.getHomeshow(VaxPhoneAPP.getUserId())
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<HomeDataBean>(mvpView) {
                    @Override
                    public void success(HomeDataBean baseData) {
                        baseData.getData();
                        if (mvpView != null) {
                            mvpView.getHomeData(baseData);
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
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


}
