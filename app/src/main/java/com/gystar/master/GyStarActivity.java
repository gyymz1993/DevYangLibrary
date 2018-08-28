package com.gystar.master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.gystar.master.base.BaseWebMvpActivity;

public class GyStarActivity extends BaseWebMvpActivity {
    @Override
    protected void showTitle(String string) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //http://59.175.150.15:9005/apptel/demo/login/login.html
        initAgentWeb("file:///android_asset/demo.html");
        // initAgentWeb("file:///android_asset/auction.html");
        //initAgentWeb("file:///android_asset/APP8/demo/auction/auction.html");
        //initAgentWeb("http://59.175.150.15:9005/apptel/demo/login/login.html");
        //initAgentWeb("http://59.175.150.15:9005/apptel/demo/demo.html");
        CommWebJsContrl commWebJsContrl = new CommWebJsContrl(this);
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", commWebJsContrl);
    }
}
