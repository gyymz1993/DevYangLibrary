package com.gystar.master.MainUI.main.WorkOder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.MainUI.main.WorkOder.remark.RemarkActivity;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.base.BaseRefreshFragment;
import com.gystar.master.bean.WorkOrderBean;
import com.utils.gyymz.wiget.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import vaxsoft.com.vaxphone.R;

public class WorkOrderFragment extends BaseRefreshFragment<WorkOrderPresenter> implements WorkOrderContract.View {

    private List<WorkOrderBean.DataBean.PageBean> mData = new ArrayList<>();

    @Override
    public BaseRefreshAdapter getBaseRefreshAdapter() {
        return new WorkOrderItemAdapter(getContext(), mData);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        mPresenter.getWorkOrderShow(true);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getWorkOrderShow(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getWorkOrderShow(true);
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        showNavigationBarView().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "工单管理");
        mPresenter.getWorkOrderShow(true);

    }

    @Override
    protected WorkOrderPresenter getDelegateClass() {
        return new WorkOrderPresenter();
    }


    @Override
    public void onLoadingCompleted() {

    }



    @Override
    public void onAllPageLoaded() {
        noMoreData();
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
    public void getLoadData(WorkOrderBean workOrderBean, boolean reload) {
        myLoadViewFactory.madeLoadView().restore();
        List<WorkOrderBean.DataBean.PageBean> page = workOrderBean.data.getPage();
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
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    public class WorkOrderItemAdapter extends BaseRefreshAdapter<WorkOrderBean.DataBean.PageBean> {

        public WorkOrderItemAdapter(Context context, List<WorkOrderBean.DataBean.PageBean> datas) {
            super(context, datas, R.layout.item_workorder);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, WorkOrderBean.DataBean.PageBean topicBean, int position) {
            TextView tvName = viewHolder.getView(R.id.id_tv_client_name);
            tvName.setText(topicBean.getName());
            TextView tvTime = viewHolder.getView(R.id.id_tv_time);
            tvTime.setText(topicBean.getOrder_time());
            TextView tvEare = viewHolder.getView(R.id.id_tv_eare);
            tvEare.setText(topicBean.getArea_name());
            TextView clientForm = viewHolder.getView(R.id.id_client_form);
            clientForm.setText(topicBean.getWork_status() + "");
            TextView tvPhone = viewHolder.getView(R.id.id_tv_phone);
            tvPhone.setText(topicBean.getPhone());
            TextView tvRemark = viewHolder.getView(R.id.id_tv_remark);
            tvRemark.setText(topicBean.getRemark());
            viewHolder.getView(R.id.id_btn_goto_remark).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent launchIntent = RemarkActivity.getLaunchIntent(getContext(), topicBean);
                    startActivity(launchIntent);
                    //openActivity(OtherTopUPActivity.class);
                }
            });

        }
    }

}
