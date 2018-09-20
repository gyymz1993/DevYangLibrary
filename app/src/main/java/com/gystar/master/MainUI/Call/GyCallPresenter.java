package com.gystar.master.MainUI.Call;


import com.gystar.master.Config.netApi.ModuleApi;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GyCallPresenter extends BasePresenterImpl<GyCallContract.View> implements GyCallContract.Presenter {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);
    private String TAG = "GyCallPresenter";

    public void getCallCustomer(String customer_id, String call_duration) {
        api.getCallCustomer(VaxPhoneAPP.getUserId(), customer_id, call_duration).
                compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(mvpView) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        if (mvpView!=null){
                            mvpView.onCallEnd();
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                    }
                });
    }

}
