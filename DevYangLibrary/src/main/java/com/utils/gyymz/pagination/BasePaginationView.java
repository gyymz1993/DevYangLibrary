package com.utils.gyymz.pagination;

import com.utils.gyymz.mvp.base.BaseView;


public interface BasePaginationView extends BaseView {
    /**
     * 一次页面加载完成操作
     */
    void onLoadingCompleted();

    /**
     * 所有页面均加载完成
     */
    void onAllPageLoaded();
}
