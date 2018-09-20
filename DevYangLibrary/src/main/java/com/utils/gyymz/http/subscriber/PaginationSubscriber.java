package com.utils.gyymz.http.subscriber;


import com.utils.gyymz.mvp.base.BaseView;
import com.utils.gyymz.pagination.PaginationBridge;
import com.utils.gyymz.utils.T_;

import java.util.List;

/**
 * 使用此类来订阅分页的请求结果。参考{@link ResponseSubscriber}。
 */
public abstract class PaginationSubscriber<T> extends ResponseSubscriber<T> {

    private PaginationBridge bridge;
    private boolean reload;
    private BaseView mView;

    /**
     * @param bridge 默认实现了此接口
     * @param reload 是否是刷新操作
     */
    public PaginationSubscriber(BaseView view, PaginationBridge bridge, boolean reload) {
        super(view);
        this.mView = view;
        this.bridge = bridge;
        this.reload = reload;
    }

    @Override
    public void success(T t) {
        if (t instanceof ResponseHandler.IBaseData) {
            //T_.showToastReal("t instanceof ResponseHandler.IBaseData");
            ResponseHandler.IBaseData data = (ResponseHandler.IBaseData) t;
            /**
             * 刷新中  如果没有数据则为空
             */
            if (mView != null) mView.hideLoading();
            if (checkDataNotNull(data) && checkListNotNull(getListResult(t, checkDataNotNull(data)))) {
                onDataNotNull(t);
            } else {
                if (reload) {
                    onDataIsNull();
                } else {
                    onNotMoreData();
                }
            }
            Object condition = getCondition(t, checkDataNotNull(data));
            if (bridge != null) bridge.setCondition(condition);
        } else {
            //T_.showToastReal("t not instanceof ResponseHandler.IBaseData");
            onDataNotNull(t);
            if (bridge != null) bridge.setCondition(null);
        }
    }


    @Override
    public void onError(Throwable e) {
        if (mView != null) mView.hideLoading();
        e.printStackTrace();
        super.onError(e);
    }


    /**
     * 当请求成功，并且数据不为空的情况下会回调此函数
     */
    protected abstract void onDataNotNull(T t);

    /**
     * 提供分页条件，如服务器返回的总页数，或者是服务器返回的分页集合数据。需要根据不同的分页策略返回不同的条件。
     */
    protected abstract Object getCondition(T t, boolean dataNotNull);

    /**
     * 提供分页结果的List数据
     */
    protected abstract List getListResult(T t, boolean dataNotNull);

    /**
     * 当请求成功，并且数据为空的情况下会回调此函数，默认会调用BaseView的showEmptyHint方法。
     */
    protected abstract void onDataIsNull();


    /**
     * 当请求成功，没有更多数据
     */
    protected abstract void onNotMoreData();


}