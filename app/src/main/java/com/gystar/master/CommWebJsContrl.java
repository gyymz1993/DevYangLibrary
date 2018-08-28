package com.gystar.master;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class CommWebJsContrl {

    private AppCompatActivity appCompatActivity;

    public CommWebJsContrl(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @JavascriptInterface
    public void lianxi() {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "lianxi");
            }
        });

    }

    @JavascriptInterface
    public void testClick() {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "lianxi");
            }
        });

    }

    @JavascriptInterface
    public void testClick1() {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "lianxi");
            }
        });

    }

//    @JavascriptInterface
//    public String back() {
//        appCompatActivity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("TAG", "hello world");
//
//            }
//        });
//        return "hello world";
//    }

    @JavascriptInterface
    public String callPerson(String number) {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "number" + number);

            }
        });
        return "hello world";
    }


    @JavascriptInterface
    public void callBack(String number) {
      //  Log.e("TAG", "number" + number);
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "number" + number);
            }
        });
    }

    @JavascriptInterface
    public void callAndroid(final String number) {
      //  Log.e("TAG", "number" + number);
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "number" + number);
            }
        });
    }

    @JavascriptInterface//一定要写，不然H5调不到这个方法
    public void back(String ts) {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "number" + "成功调用JAVA方法"+ts);
            }
        });
    }

    @JavascriptInterface//一定要写，不然H5调不到这个方法
    public void lianxiren(String ts) {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "number" + "成功调用JAVA方法"+ts);
            }
        });
    }


    @JavascriptInterface
    public void jcmToast(String result) {
        Toast.makeText(appCompatActivity, result, Toast.LENGTH_LONG).show();
    }

}
