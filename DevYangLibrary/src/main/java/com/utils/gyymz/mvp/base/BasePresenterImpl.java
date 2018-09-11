package com.utils.gyymz.mvp.base;

import android.support.annotation.NonNull;

import com.utils.gyymz.http.rxcall.RxApiManager;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.Observable;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    @NonNull
    protected V mvpView;
    protected Reference<V> mViewRef;
    //protected Map<String, ResponseSubscriber> mRequestMaps = new HashMap<>();
    protected ResponseSubscriber responseSubscriber;

    @NonNull
    @Override
    public void attachView(V view) {
        this.mvpView = view;
        mViewRef = new WeakReference<>(mvpView);
    }

    @Override
    public void detachView() {
        // cancelRequest();
        // mRequestMaps = null;
        this.mvpView = null;
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


    /**
     * 取消单个请求
     *
     * @param key
     */
    public void cancelRequest(String key) {
        RxApiManager.get().cancel(key);
        responseSubscriber.cancelRequest();
    }

    public void cancelRequest() {
        /**同一个presenter 对象在一个Activity使用，所以当Activity销毁时可以全部取消掉**/
        RxApiManager.get().cancelAll();
    }

    /**
     */
    public void httpRequest(String tag, Observable observable, ResponseSubscriber subscriber) {
        // this.mRequestMaps.put(Tag, subscriber);
        RxApiManager.get().add(tag, subscriber);
        this.responseSubscriber = subscriber;
        observable.compose(new ResponseTransformer<>())
                .subscribe(subscriber);
        //    compose(provider.<Long>bindUntilEvent(ActivityEvent.DESTROY))
    }


//    protected <V> ObservableTransformer<V, V> bindToLifeCycle() {
//        return mvpView.bind();
//    }
//
//    protected <V> ObservableTransformer<V, V> bindUntilEvent(ActivityEvent event) {
//        return mvpView.bindUntil(event);
//    }
//
//    protected <V> ObservableTransformer bindUntilEvent(FragmentEvent event) {
//        return mvpView.bindUntil(event);
//    }



}
