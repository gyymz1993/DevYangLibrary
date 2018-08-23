package com.gystar.master;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gystar.master.base.BaseWebMvpActivity;

public class GyStarActivity extends BaseWebMvpActivity {
    @Override
    protected void showTitle(String string) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAgentWeb("http://59.175.150.15:9005/apptel/demo/login/login.html");
    }
}
