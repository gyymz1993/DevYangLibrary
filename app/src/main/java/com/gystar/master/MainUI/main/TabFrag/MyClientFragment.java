package com.gystar.master.MainUI.main.TabFrag;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.CustomViews.RVDecoration;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.MyClientPerson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.statuslayout.OnStatusChildClickListener;
import com.utils.gyymz.statuslayout.StatusLayoutManager;
import com.utils.gyymz.utils.L_;
import com.utils.gyymz.utils.UIUtils;
import com.utils.gyymz.vary.MyLoadViewFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;


/**
 * 我的客户
 */
public class MyClientFragment extends MVPLazyFragment<MePresenter> implements MeContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    MyLoadViewFactory myLoadViewFactory;
    List<MyClientPerson.DataBean.PageBean> mClientPerson = new ArrayList<>();
    HomepageItemAdapter baseRefreshAdapter;
    private int mStatu = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.gy_fragment_myclient;
    }

    @Override
    protected void onVisible() {
        // mPresenter.getOntermsCustomerList(mStatu, true);

    }


    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Bundle dataBundle = getArguments();
        if (dataBundle != null) {
            mStatu = dataBundle.getInt(TheBiddingFragment.KEY_STATE);
        }
        initRefresh();
//        showNavigationBarView().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "我的客户")
//                .setText(NavigationBarView.NavigationViewType.LEFT_TEXT, "返回");
        myLoadViewFactory.madeLoadView().showLoading();
        mPresenter.getOntermsCustomerList(mStatu, true);

    }

    @Override
    protected MePresenter getDelegateClass() {
        return new MePresenter();
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
                mPresenter.getOntermsCustomerList(mStatu, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // T_.showCustomToast("刷新");
                mPresenter.getOntermsCustomerList(mStatu, true);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RVDecoration());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        baseRefreshAdapter = new HomepageItemAdapter(getActivity(), mClientPerson);
        recyclerView.setAdapter(baseRefreshAdapter);
        myLoadViewFactory = new MyLoadViewFactory(recyclerView);
        //setupStatusLayoutManager();
    }


    public class HomepageItemAdapter extends BaseRefreshAdapter<MyClientPerson.DataBean.PageBean> {

        public HomepageItemAdapter(Context context, List<MyClientPerson.DataBean.PageBean> datas) {
            super(context, datas, R.layout.item_myclientfrag);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, MyClientPerson.DataBean.PageBean topicBean, int position) {
            L_.e(topicBean.getName());
            TextView tvclient_name = viewHolder.getView(R.id.id_tv_client_name);
            tvclient_name.setText(topicBean.getName());

            TextView tv_eare = viewHolder.getView(R.id.id_tv_eare);
            tv_eare.setText(topicBean.getArea_name());

            TextView tv_match = viewHolder.getView(R.id.id_tv_match);
            tv_match.setText(topicBean.getPrice() + "");

            TextView tv_time = viewHolder.getView(R.id.id_tv_time);
            tv_time.setText(topicBean.getShelf_time());

        }
    }


    @Override
    public void onLoadingCompleted() {
        // T_.showCustomToast("onLoadingCompleted");
    }

    @Override
    public void onAllPageLoaded() {
        //T_.showCustomToast("onAllPageLoaded");
        noMoreData();
    }

    @Override
    public void getClientList(MyClientPerson myClientPerson, boolean reload) {
        myLoadViewFactory.madeLoadView().restore();
        // statusLayoutManager.showSuccessLayout();
        MyClientPerson.DataBean data = myClientPerson.getData();
        List<MyClientPerson.DataBean.PageBean> page = data.getPage();
        if (reload) {
            refreshLayout.finishRefresh(true);
            refreshLayout.setNoMoreData(false);
            refreshLayout.setEnableLoadMore(true);
            baseRefreshAdapter.setListData(page);
        } else {
            baseRefreshAdapter.addAll(page);
            refreshLayout.finishLoadMore(true);
        }
    }

    @Override
    public void noMoreData() {
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadMore(true);
        /*不许加载更多*/
        refreshLayout.setEnableLoadMore(false);
        //显示没有更多数据
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    StatusLayoutManager statusLayoutManager;

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
