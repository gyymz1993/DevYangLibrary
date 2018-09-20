package com.utils.gyymz.mvp.base;

public interface BaseView {


    /**
     * 加载完成时隐藏加载框
     */
    void hideLoading();

    /**
     * 显示空列表的提示
     */
    void showNetWorkErrorView();


}
