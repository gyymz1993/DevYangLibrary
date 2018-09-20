package com.gystar.master.MainUI.main.Home.OtherTopUP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.CustomViews.RVDecoration;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.ByconditionBean;
import com.gystar.master.bean.HistoryRecBean;
import com.gystar.master.bean.HistoryRemarkBean;
import com.gystar.master.bean.WorkOrderBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.vary.MyLoadViewFactory;
import com.utils.gyymz.wiget.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class OtherTopUPActivity extends MVPBaseActivity<OtherTopUPPersenter> implements OtherTopUPContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
    @BindView(R.id.id_ly_his_remark)
    protected LinearLayout lyHisRemark;
    @BindView(R.id.id_ig_search)
    protected ImageView igSearch;
    @BindView(R.id.id_ed_content)
    protected EditText edContent;


    @BindView(R.id.id_ly_cilent)
    protected LinearLayout lyCilent;
    @BindView(R.id.id_tv_client_number)
    protected TextView tvClientNumber;
    @BindView(R.id.id_tv_client_name)
    protected TextView tvClientName;


    protected MyLoadViewFactory myLoadViewFactory;
    BaseRefreshAdapter baseRefreshAdapter;
    List<HistoryRecBean.DataBean.PageBean> mdata = new ArrayList<>();

    private static String KEY_FREECRE = "KEY_FREECRE";
    private String phone;

    public static Intent getLaunchIntent(Context context, WorkOrderBean.DataBean.PageBean pageBean) {
        Intent intent = new Intent(context, OtherTopUPActivity.class);
        intent.putExtra(KEY_FREECRE, pageBean);
        return intent;
    }


    @Override
    protected OtherTopUPPersenter getDelegateClass() {
        return new OtherTopUPPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_top_up;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        showNavigationBarCanBack().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "填写备注");
        initRefresh();
        myLoadViewFactory.madeLoadView().showLoading();
        phone = SpUtils.getInstance().getString(AppConfig.USER_PHONE);
        mPresenter.getHistoryrec(phone + "", true);
        igSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edContent.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    mPresenter.searchUserForPhome(phone);
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
                mPresenter.getHistoryrec(phone, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHistoryrec(phone, true);
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
        baseRefreshAdapter = new RemakeItemAdapter(OtherTopUPActivity.this, mdata);
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
    public void getLoadData(HistoryRecBean historyRemarkBean, boolean reload) {
        myLoadViewFactory.madeLoadView().restore();
        HistoryRecBean.DataBean data = historyRemarkBean.getData();
        List<HistoryRecBean.DataBean.PageBean> page = data.getPage();
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
    public void showBycondition(ByconditionBean byconditionBean) {
        lyCilent.setVisibility(View.VISIBLE);
        tvClientNumber.setText(byconditionBean.getData().getPhone());
        tvClientName.setText(byconditionBean.getData().getNike_name());
    }

    @Override
    public void hideBycondition() {
        lyCilent.setVisibility(View.GONE);
    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    public class RemakeItemAdapter extends BaseRefreshAdapter<HistoryRecBean.DataBean.PageBean> {

        public RemakeItemAdapter(Context context, List<HistoryRecBean.DataBean.PageBean> datas) {
            super(context, datas, R.layout.item_remark_his);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, HistoryRecBean.DataBean.PageBean remake, int position) {
            TextView tvTime = viewHolder.getView(R.id.id_tv_time);
            tvTime.setText(remake.getTime());
            TextView tvRemake = viewHolder.getView(R.id.id_tv_remark);
            tvRemake.setText(remake.getPhone());

        }
    }
}
