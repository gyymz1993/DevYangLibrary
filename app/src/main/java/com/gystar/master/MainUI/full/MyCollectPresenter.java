package com.gystar.master.MainUI.full;


import android.util.Log;

import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.Config.netApi.ModuleApi;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.mvp.base.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyCollectPresenter extends BasePresenterImpl<MyCollectContract.View> implements MyCollectContract.Presenter {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);
    private String TAG = "GyCallPresenter";

    @Override
    public void getAppBasicInfo() {
        httpRequest(TAG, api.getBeginrecharge("9", "1", "13177008851", "ymz", "0"),
                new ResponseSubscriber<BeginrechargeBean>(mvpView) {
                    @Override
                    public void success(BeginrechargeBean baseData) {
                        String payParams = baseData.getData().getPayParams();
                        //mViewRef.get().getDataSucceed(payParams);
                        if (mvpView != null)
                            mvpView.getDataSucceed("22221" + payParams);
                        // aliPay(payParams);
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                    }
                });
        // cancelRequest(TAG);
    }

    public void getAppBasicInfo1() {
        httpRequest(TAG, api.getBeginrecharge("9", "1", "13177008851", "ymz", "0"),
                new ResponseSubscriber<BeginrechargeBean>(mvpView) {
                    @Override
                    public void success(BeginrechargeBean baseData) {
                        String payParams = baseData.getData().getPayParams();
                        //mViewRef.get().getDataSucceed(payParams);
                        if (mvpView != null)
                            mvpView.getDataSucceed("1111" + payParams);
                        // aliPay(payParams);
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                    }
                });
        //cancelRequest(TAG);
    }

}
