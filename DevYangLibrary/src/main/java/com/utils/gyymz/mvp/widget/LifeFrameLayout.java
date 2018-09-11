package com.utils.gyymz.mvp.widget;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.utils.gyymz.mvp.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;


public class LifeFrameLayout<P extends BasePresenter>  extends FrameLayout implements LifecycleObserver{

    protected Unbinder injectView;

    protected P mPresenter;


    public LifeFrameLayout(Context context) {
        super(context);
    }

    public LifeFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LifeFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE() {
        injectView = ButterKnife.bind(this);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START() {
        Log.i("info","MyObserver:ON_START");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME() {
        Log.i("info","MyObserver:ON_RESUME");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE() {
        Log.i("info","MyObserver:ON_PAUSE");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void ON_STOP() {
        Log.i("info","MyObserver:ON_STOP");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY() {
        Log.i("info","MyObserver:ON_DESTROY");
        injectView.unbind();
        if (mPresenter != null) mPresenter.detachView();
    }


}
