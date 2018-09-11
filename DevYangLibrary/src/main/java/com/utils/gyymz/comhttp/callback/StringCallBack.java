package com.utils.gyymz.comhttp.callback;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public abstract class StringCallBack extends BaseCallBack<String> {

    @Override
    public void onNext(String response) {
        Log.e("StringCallBack", "网络请求成功" + response);
        //response = response.replace("null", "\"\"");
        response = response.substring(response.indexOf("(") + 1, response.length() - 1);
        JSONObject jsonObject = JSON.parseObject(response);
        int code = jsonObject.getIntValue(Result.CODE);
        final String msg = jsonObject.getString(Result.RESULT_MSG);
        if (code==Result.CODE_SUCCESS){
            if (!TextUtils.isEmpty(response)) {
                final String finalResponse = response;
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("StringCallBack", "解密数据" + finalResponse);
                        onSuccess(finalResponse);
                    }
                });
            }
        }else {
            mDelivery.post(new Runnable() {
                @Override
                public void run() {
                    onXError(msg);
                    Log.e("TODO",msg+"");
                }
            });
        }
    }


    @Override
    public void onError(final Throwable e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TODO",e.getMessage()+"");
                onXError(e.getMessage());
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    protected abstract void onXError(String exception);


    protected abstract void onSuccess(String response);


}