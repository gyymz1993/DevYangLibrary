package com.gystar.master.MainUI.Login;

import android.util.Log;

import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.UserBean;
import com.utils.gyymz.comhttp.HttpUtils;
import com.utils.gyymz.comhttp.callback.StringCallBack;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;
import com.utils.gyymz.utils.L_;
import com.utils.gyymz.utils.T_;

import java.util.HashMap;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public void doCodeLogin(String number, String code) {
        api.getLoginVerfy(number, code).compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<UserBean>(mvpView) {
                    @Override
                    public void success(UserBean baseData) {
                        L_.e(baseData.toString());
                        if (mvpView != null) {
                            mvpView.doLogin(baseData);
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showToastReal(exception);
                        if (mvpView != null) {
                            mvpView.hideLoading();
                        }
                    }
                });
    }

    public void getCode(String phoneNumber) {
        api.getLoginSMS(phoneNumber)
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(mvpView) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        if (mvpView != null) {
                            T_.showCustomToast("获取验证码成功");
                            mvpView.getCode();
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showToastReal(exception);
                        if (mvpView != null) {
                            mvpView.hideLoading();
                        }
                    }
                });

    }

    private void commHttp(String number, String code) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("phone", number);
        stringStringHashMap.put("smscode", code);
        HttpUtils.getInstance().executeGet(AppConfig.LOGINVERFY, stringStringHashMap, new StringCallBack() {
            @Override
            protected void onXError(String exception) {
                T_.showCustomToast("exception" + exception);
            }

            @Override
            protected void onSuccess(String response) {
                T_.showCustomToast("onSuccess" + response);
                L_.e(response);
            }
        });
    }


}
