package com.utils.gyymz.mvp.base;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
