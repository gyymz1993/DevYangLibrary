package com.gystar.master.MainUI.main.Home.message;

import android.os.Bundle;

import com.gystar.master.base.BaseRefreshActivity;
import com.gystar.master.base.BaseRefreshAdapter;
import com.utils.gyymz.BaseApplicationCompat;
import com.utils.gyymz.listener.UserSensorEventListener;
import com.utils.gyymz.wiget.NavigationBarView;

public class MessageActivity extends BaseRefreshActivity<MessagePersenter> implements MessageContract.View {
    @Override
    public BaseRefreshAdapter getBaseRefreshAdapter() {
        return null;
    }

    @Override
    public void onLoadMore() {
        mPresenter.getgetQueryInformation(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getgetQueryInformation(true);
    }

    @Override
    protected MessagePersenter getDelegateClass() {
        return new MessagePersenter();
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        mPresenter.getgetQueryInformation(true);
        showNavigationBarCanBack().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "我的消息");
    }


    @Override
    public void onLoadingCompleted() {

    }

    @Override
    public void onAllPageLoaded() {

    }

    @Override
    public void hasNoData() {
        myLoadViewFactory.madeLoadView().showEmpty();
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadMore(true);
        /*不许加载更多*/
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }
}
