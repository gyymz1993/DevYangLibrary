package com.gystar.master.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gystar.master.CustomViews.RVDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.utils.gyymz.mvp.base.BasePresenterImpl;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.statuslayout.OnStatusChildClickListener;
import com.utils.gyymz.statuslayout.StatusLayoutManager;
import com.utils.gyymz.vary.MyLoadViewFactory;
import com.utils.gyymz.wiget.NavigationBarView;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;


/**
 * 我的客户
 */
public abstract class BaseRefreshActivity<P extends BasePresenterImpl> extends MVPBaseActivity<P> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
    protected MyLoadViewFactory myLoadViewFactory;
    BaseRefreshAdapter baseRefreshAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.a_base_refresh_fragment;
    }


    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initRefresh();
        //myLoadViewFactory.madeLoadView().showLoading();
    }



    /**
     * 初始化刷新
     */
    private void initRefresh() {
        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // T_.showCustomToast("加载更多");
                BaseRefreshActivity.this.onLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // T_.showCustomToast("刷新");
                BaseRefreshActivity.this.onRefresh();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RVDecoration());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        myLoadViewFactory = new MyLoadViewFactory(recyclerView);
        baseRefreshAdapter = getBaseRefreshAdapter();
        if (baseRefreshAdapter == null) return;
        recyclerView.setAdapter(baseRefreshAdapter);

    }

    public abstract BaseRefreshAdapter getBaseRefreshAdapter();

    public abstract void onLoadMore();

    public abstract void onRefresh();

    public void getClientList(boolean reload) {
        if (reload) {
            refreshLayout.finishRefresh(true);
            refreshLayout.setNoMoreData(false);
            refreshLayout.setEnableLoadMore(true);
            //baseRefreshAdapter.setListData(page);
        } else {
            //baseRefreshAdapter.addAll(page);
            refreshLayout.finishLoadMore(true);
        }
    }

    public void noMoreData() {
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadMore(true);
        /*不许加载更多*/
        refreshLayout.setEnableLoadMore(false);
        //显示没有更多数据
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    private StatusLayoutManager statusLayoutManager;

    private void setupStatusLayoutManager() {
        statusLayoutManager = new StatusLayoutManager.Builder(recyclerView)

                // 设置重试事件监听器
                .setOnStatusChildClickListener(new OnStatusChildClickListener() {
                    @Override
                    public void onEmptyChildClick(View view) {
                        // ToastUtils.show(MainActivity.this, "空数据状态布局");
                        statusLayoutManager.showLoadingLayout();
                        //getData(1000);
                    }

                    @Override
                    public void onErrorChildClick(View view) {
                        // ToastUtils.show(MainActivity.this, "出错状态布局");
                        statusLayoutManager.showLoadingLayout();
                        // getData(1000);
                    }

                    @Override
                    public void onCustomerChildClick(View view) {

                    }
                })
                .build();
        statusLayoutManager.showLoadingLayout();
    }
}
