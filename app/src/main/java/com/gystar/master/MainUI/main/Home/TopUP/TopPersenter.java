package com.gystar.master.MainUI.main.Home.TopUP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.bean.TopBean;
import com.utils.gyymz.base.BaseAppCompatActivity;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;
import com.utils.gyymz.utils.L_;

import java.util.List;

import three.pay.PayPlatform;
import three.pay.ThirdPayUtils;
import three.pay.bean.PayInfo;
import three.pay.listener.PayListener;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class TopPersenter extends BasePresenterImpl<TopContract.View> implements TopContract.Presenter {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public void rechargeShow() {
        httpRequest("OtherTopPersenter", api.getRechargeShow(),
                new ResponseSubscriber<TopBean>(mvpView) {
                    @Override
                    public void success(TopBean baseData) {
                        if (mvpView != null) {
                            List<TopBean.DataBean> data = baseData.getData();
                            L_.e("rechargeShow" + data.toString());
                            mvpView.getTopBeanList(baseData.data);
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                    }
                });
    }

    public void getBeginrecharge(BaseAppCompatActivity appCompatActivity, TopBean.DataBean currrentDataBean) {
        api.getBeginrecharge(VaxPhoneAPP.getUserId(), currrentDataBean.getId() + "",
                VaxPhoneAPP.getPhoneNumber(), "ymz", "0")
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<BeginrechargeBean>(mvpView) {
                    @Override
                    public void success(BeginrechargeBean baseData) {
                        String payParams = baseData.getData().getPayParams();
                        aliPay(appCompatActivity, payParams);
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                    }
                });
    }

    private void aliPay(AppCompatActivity appCompatActivity, String payParams) {
        PayInfo payInfo = new PayInfo();
        payInfo.setSign(payParams);
        ThirdPayUtils.initialize(appCompatActivity).pay(PayPlatform.AliPay, payInfo, new PayListener() {
            @Override
            public void paySuccess() {
                Intent launchIntent = PayResultActivity.getLaunchIntent(appCompatActivity, true);
                appCompatActivity.startActivity(launchIntent);
            }

            @Override
            public void payFailure(Exception e) {
                Intent launchIntent = PayResultActivity.getLaunchIntent(appCompatActivity, false);
                appCompatActivity.startActivity(launchIntent);
            }

            @Override
            public void userCancel() {
                Intent launchIntent = PayResultActivity.getLaunchIntent(appCompatActivity, false);
                appCompatActivity.startActivity(launchIntent);
            }
        });
    }

}
