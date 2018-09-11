package com.utils.gyymz.mvp.widget;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by qiyue on 2018/7/6.
 */

public abstract class LifeRecyclerView<P extends BasePresenter> extends RecyclerView implements LifecycleObserver ,BaseView{

    protected Unbinder injectView;

    protected P mPresenter;


    public LifeRecyclerView(Context context) {
        super(context);
    }

    public LifeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LifeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE() {
        injectView = ButterKnife.bind(this);
        mPresenter = getDelegateClass();
        if (mPresenter != null) mPresenter.attachView(this);
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START() {
        Log.i("info", "MyObserver:ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME() {
        Log.i("info", "MyObserver:ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE() {
        Log.i("info", "MyObserver:ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void ON_STOP() {
        Log.i("info", "MyObserver:ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY() {
        Log.i("info", "MyObserver:ON_DESTROY");
        injectView.unbind();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected abstract P getDelegateClass();

}

