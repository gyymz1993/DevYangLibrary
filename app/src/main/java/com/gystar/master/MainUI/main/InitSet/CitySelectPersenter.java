package com.gystar.master.MainUI.main.InitSet;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.CityListBean;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;
import com.utils.gyymz.utils.T_;

import java.util.List;

import retrofit2.http.Query;


public class CitySelectPersenter extends BasePresenterImpl<CitySelectContract.View> implements CitySelectContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public CitySelectPersenter() {
        super();
    }

    public void getCityList() {
        api.gainArea().compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<CityListBean>(mvpView) {
                    @Override
                    public void success(CityListBean baseData) {
                        List<CityListBean.DataBean> data = baseData.getData();
                        if (mvpView != null) {
                            mvpView.setCityData(data);
                        }
                        // aliPay(appCompatActivity, payParams);
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showCustomToast(exception);
                    }
                });


    }

    public void getAddusermessage(String phone, String cityCode
            , String nickName, String workYears) {
        api.getAddusermessage(phone, cityCode, nickName, workYears).compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(mvpView) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        if (mvpView != null) {
                            mvpView.regesterSuccess();
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                    }
                });


    }

}
