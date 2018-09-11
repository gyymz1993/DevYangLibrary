package com.gystar.master;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gystar.master.MoudleContrl.CommWebJsContrl;
import com.gystar.master.base.BaseWebMvpActivity;
import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.MoudleContrl.VaxPhoneSIPLogin;
import com.gystar.master.Config.netApi.ModuleApi;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;

import three.pay.PayPlatform;
import three.pay.ThirdPayUtils;
import three.pay.bean.PayInfo;
import three.pay.listener.PayListener;
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class GyStarActivity extends BaseWebMvpActivity {

    ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    @Override
    protected void showTitle(String string) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            initAgentWeb("http://192.168.1.29/APP8/demo/login/login.html");
            //initAgentWeb("http://59.175.150.15:9005/apptel/demo/login/login.html");
            tvGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getAppBasicInfo();
                }
            });
            CommWebJsContrl commWebJsContrl = new CommWebJsContrl(GyStarActivity.this);
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", commWebJsContrl);
            onInitVaxsoft();
            // UpdateDrawerUI();
        } catch (Exception e) {
        }

    }


    public void getAppBasicInfo() {
        api.getBeginrecharge("9", "1", "13177008851", "ymz", "0")
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<BeginrechargeBean>() {
                    @Override
                    public void success(BeginrechargeBean baseData) {
                        String payParams = baseData.getData().getPayParams();
                        aliPay(payParams);
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_LONG).show();
                    }
                });
    }


    public void aliPay(String payParams) {
        PayInfo payInfo = new PayInfo();
        payInfo.setSign(payParams);
        ThirdPayUtils.initialize(GyStarActivity.this).pay(PayPlatform.AliPay, payInfo, new PayListener() {
            @Override
            public void paySuccess() {
            }

            @Override
            public void payFailure(Exception e) {
            }

            @Override
            public void userCancel() {
            }
        });
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void UpdateDrawerUI() {
        StringBuilder sAuthLogin = new StringBuilder();
        StringBuilder sDoaminRealm = new StringBuilder();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(null, null, sAuthLogin, null, sDoaminRealm, null, null, null);
    }


    private void onInitVaxsoft() {
        VaxPhoneSIP.m_objVaxVoIP.NetworkReachability(true);
        VaxPhoneSIP.m_objVaxVoIP.OpenVideoDevice();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
                        , Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS
                }, 3000);
                return;
            }
        }
        VaxPhoneSIPLogin.login(getApplicationContext());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 3000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  autoLogin();
                VaxPhoneSIPLogin.login(getApplicationContext());
            } else {
                DialogUtil.ShowDialog(this, "Please allow to use camera for video call.");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
