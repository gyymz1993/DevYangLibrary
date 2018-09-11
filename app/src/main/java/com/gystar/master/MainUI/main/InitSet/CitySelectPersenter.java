package com.gystar.master.MainUI.main.InitSet;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.CityListBean;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;

import java.util.List;


public class CitySelectPersenter extends BasePresenterImpl<CitySelectContract.View> implements CitySelectContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public CitySelectPersenter() {
        super();
    }

    public void getCityList() {
        api.gainArea().compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<CityListBean>() {
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
                    }
                });


    }


}
