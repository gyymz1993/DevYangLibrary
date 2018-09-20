package com.gystar.master.MainUI.main.WorkOder.remark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.CustomViews.RVDecoration;
import com.gystar.master.MainUI.main.TabFrag.TheBiddingFragment;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.HistoryRemarkBean;
import com.gystar.master.bean.MyClientPerson;
import com.gystar.master.bean.WorkOrderBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.T_;
import com.utils.gyymz.vary.MyLoadViewFactory;
import com.utils.gyymz.wiget.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class RemarkActivity extends MVPBaseActivity<RemarkPersenter> implements RemarkContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;

    @BindView(R.id.id_ly_his_remark)
    protected LinearLayout lyHisRemark;


    @BindView(R.id.id_tv_confir)
    protected TextView tvConfir;
    @BindView(R.id.id_ed_content)
    protected EditText edContent;


    protected MyLoadViewFactory myLoadViewFactory;
    BaseRefreshAdapter baseRefreshAdapter;
    List<HistoryRemarkBean.DataBean.PageBean> mdata = new ArrayList<>();

    private MyClientPerson.DataBean.PageBean pageBean;
    private static String KEY_FREECRE = "KEY_FREECRE";

    public static Intent getLaunchIntent(Context context, WorkOrderBean.DataBean.PageBean pageBean) {
        Intent intent = new Intent(context, RemarkActivity.class);
        intent.putExtra(KEY_FREECRE, pageBean);
        return intent;
    }

    public static Intent getLaunchIntent(Context context, MyClientPerson.DataBean.PageBean pageBean) {
        Intent intent = new Intent(context, RemarkActivity.class);
        intent.putExtra(KEY_FREECRE, pageBean);
        return intent;
    }



    @Override
    protected RemarkPersenter getDelegateClass() {
        return new RemarkPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remark;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if (getIntent() != null) {
            pageBean = (MyClientPerson.DataBean.PageBean) getIntent().getSerializableExtra(KEY_FREECRE);
        }
        showNavigationBarCanBack().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "填写备注");
        initRefresh();
        myLoadViewFactory.madeLoadView().showLoading();
        mPresenter.getHistoryremark(pageBean.getId() + "", true);
        tvConfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edContent.getText().toString())) {
                    mPresenter.getAddWorkorderRemark(pageBean.getId() + "", edContent.getText().toString());
                } else {
                    T_.showToastReal("请填写备注信息");
                }
            }
        });
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
                mPresenter.getHistoryremark(pageBean.getId() + "", false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHistoryremark(pageBean.getId() + "", true);
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
        myLoadViewFactory = new MyLoadViewFactory(lyHisRemark);
        baseRefreshAdapter = new RemakeItemAdapter(RemarkActivity.this, mdata);
        if (baseRefreshAdapter == null) return;
        recyclerView.setAdapter(baseRefreshAdapter);
    }

    @Override
    public void onLoadingCompleted() {

    }

    @Override
    public void onAllPageLoaded() {
        noMoreData();
    }

    @Override
    public void noMoreData() {
        //T_.showToastReal("没有更多数据");
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadMore(true);
        /*不许加载更多*/
        refreshLayout.setEnableLoadMore(false);
        //显示没有更多数据
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void haseNoData() {
        myLoadViewFactory.madeLoadView().showEmpty();
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadMore(true);
        /*不许加载更多*/
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void getLoadData(HistoryRemarkBean historyRemarkBean, boolean reload) {
        myLoadViewFactory.madeLoadView().restore();
        HistoryRemarkBean.DataBean data = historyRemarkBean.getData();
        List<HistoryRemarkBean.DataBean.PageBean> page = data.getPage();
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
    public void remarkSucceed() {
        TheBiddingFragment.instance.setCurrentItem(2);
        finish();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    public class RemakeItemAdapter extends BaseRefreshAdapter<HistoryRemarkBean.DataBean.PageBean> {

        public RemakeItemAdapter(Context context, List<HistoryRemarkBean.DataBean.PageBean> datas) {
            super(context, datas, R.layout.item_remark_his);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, HistoryRemarkBean.DataBean.PageBean remake, int position) {
            TextView tvTime = viewHolder.getView(R.id.id_tv_time);
            tvTime.setText(remake.getCreate_time());
            TextView tvRemake = viewHolder.getView(R.id.id_tv_remark);
            tvRemake.setText(remake.getRemark());

        }
    }
}
