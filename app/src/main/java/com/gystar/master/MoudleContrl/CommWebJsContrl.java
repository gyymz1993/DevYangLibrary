package com.gystar.master.MoudleContrl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gystar.master.MainUI.Call.GyCallActivity;
import com.gystar.master.GyStarActivity;
import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.Config.netApi.ModuleApi;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;

import three.pay.PayPlatform;
import three.pay.ThirdPayUtils;
import three.pay.bean.PayInfo;
import three.pay.listener.PayListener;

public class CommWebJsContrl {

    private AppCompatActivity appCompatActivity;
    private ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    public CommWebJsContrl(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @JavascriptInterface
    public void jcmCallUp(String number) {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Intent intent=new Intent(appCompatActivity,CallActivity.class);
                Intent launchIntent = GyCallActivity.getLaunchIntent(appCompatActivity, number,"***");
                appCompatActivity.startActivity(launchIntent);
                Log.e("TAG", "lianxi");
            }
        });

    }

    @JavascriptInterface
    public void jcmToast(String result) {
        Log.e("TAG", "lianxi" + result);
        // Toast.makeText(appCompatActivity, result, Toast.LENGTH_LONG).show();
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Intent intent=new Intent(appCompatActivity,CallActivity.class);
                Intent launchIntent = GyCallActivity.getLaunchIntent(appCompatActivity, result,"***");
                appCompatActivity.startActivity(launchIntent);
                Log.e("TAG", "lianxi");
            }
        });
    }

    @JavascriptInterface
    public void orderPay(String result) {
        Log.e("TAG", "orderPay" + result);
        JSONObject jsonObject = JSON.parseObject(result);
        String user_id = jsonObject.getString("user_id");
        String recid = jsonObject.getString("recid");
        String recharge_phone = jsonObject.getString("recharge_phone");
        String recharge_name = jsonObject.getString("recharge_name");
        String state = jsonObject.getString("state");
        startBeginrecharge(user_id, recid, recharge_phone, recharge_name, state);
    }


    public void startBeginrecharge(String user_id, String recid, String recharge_phone, String recharge_name, String state) {
        api.getBeginrecharge(user_id, recid, recharge_phone, recharge_name, state).compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<BeginrechargeBean>(null) {
                    @Override
                    public void success(BeginrechargeBean baseData) {
                        String payParams = baseData.getData().getPayParams();
                        aliPay(payParams);
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        Toast.makeText(appCompatActivity, exception, Toast.LENGTH_LONG).show();
                    }
                });
    }


    public void aliPay(String payParams) {
        PayInfo payInfo = new PayInfo();
        payInfo.setSign(payParams);
        ThirdPayUtils.initialize(appCompatActivity).pay(PayPlatform.AliPay, payInfo, new PayListener() {
            @Override
            public void paySuccess() {
                String call = "javascript:paySuccess()";
                if (appCompatActivity instanceof GyStarActivity) {
                    GyStarActivity gyStarActivity = (GyStarActivity) appCompatActivity;
                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gyStarActivity.getWebView().loadUrl(call);
                        }
                    });
                }
            }

            @Override
            public void payFailure(Exception e) {
                String call = "javascript:paySuccess()";
                if (appCompatActivity instanceof GyStarActivity) {
                    GyStarActivity gyStarActivity = (GyStarActivity) appCompatActivity;
                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gyStarActivity.getWebView().loadUrl(call);
                        }
                    });
                }
            }

            @Override
            public void userCancel() {
                String call = "javascript:paySuccess()";
                if (appCompatActivity instanceof GyStarActivity) {
                    GyStarActivity gyStarActivity = (GyStarActivity) appCompatActivity;
                    appCompatActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gyStarActivity.getWebView().loadUrl(call);
                        }
                    });

                }
            }
        });
    }


}
