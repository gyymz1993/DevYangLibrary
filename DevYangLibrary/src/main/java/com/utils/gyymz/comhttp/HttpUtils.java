package com.utils.gyymz.comhttp;

import android.util.Log;

import com.utils.gyymz.comhttp.callback.BaseCallBack;
import com.utils.gyymz.comhttp.callback.StringCallBack;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建人：$ gyymz1993
 * 创建时间：2017/7/21 10:42
 */

public class HttpUtils {

    /**
     * 内网使用
     */
    private String HTTP_ENCRYPT = "http://192.168.1.250:2918/encrypt/encrypt";

    private static HttpUtils httpUtils;
    private CompositeSubscription mCompositeSubscription;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                httpUtils = new HttpUtils();
            }
        }
        return httpUtils;
    }

    private void loadDataForNet(Observable observable, Subscriber subscriber) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(subscriber));
    }


    /**
     * get 网络请求   没有加密处理
     */
    public void executeGet(String getUrl, final HashMap stringStringHashMap, final StringCallBack stringCallBack) {
        loadDataForNet(DcodeService.getServiceData(getUrl, stringStringHashMap).onErrorResumeNext(new HttpResultFunc<String>(stringCallBack)), stringCallBack);
    }


    /**
     * post 网络请求  没有加密处理
     */
    public void executePost(String getUrl, final HashMap stringStringHashMap, final StringCallBack stringCallBack) {
        loadDataForNet(DcodeService.postServiceData(getUrl, stringStringHashMap).onErrorResumeNext(new HttpResultFunc<String>(stringCallBack)), stringCallBack);
    }

    private class HttpResultFunc<T> implements Func1<Throwable, Observable<T>> {

        BaseCallBack httpSubscriber;
        HttpResultFunc(BaseCallBack httpSubscriber) {
            this.httpSubscriber = httpSubscriber;
        }

        @Override
        public Observable<T> call(Throwable throwable) {
            Log.e("HttpResultFunc", "ApiException.handleException(throwable)" + throwable.getMessage());
            httpSubscriber.onError(throwable);
            return Observable.error(throwable);
        }
    }


}


