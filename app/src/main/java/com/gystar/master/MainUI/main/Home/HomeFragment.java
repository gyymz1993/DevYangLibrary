package com.gystar.master.MainUI.main.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.CustomViews.RVDecoration;
import com.gystar.master.MainUI.main.MainTabActivity;
import com.gystar.master.MainUI.main.TopUP.TopUPActivity;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.HomeDataBean;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.utils.L_;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vaxsoft.com.vaxphone.R;

/**
 * 首页
 */

public class HomeFragment extends MVPLazyFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<HomeDataBean.DataBean.CustomersBean> mClientPerson = new ArrayList<>();
    HomepageItemAdapter baseRefreshAdapter;
    @BindView(R.id.id_tv_app_name)
    TextView idTvAppName;
    @BindView(R.id.id_tv_top_up)
    TextView idTvTopUp;

    @Override
    protected int getLayoutId() {
        return R.layout.gy_fragment_home;
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        showLoadingView();
        initRvView();
        mPresenter.getHomeshow();
        idTvTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(TopUPActivity.class);
            }
        });
    }


    //初始化刷新
    private void initRvView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RVDecoration());
        recyclerView.setNestedScrollingEnabled(false);
        baseRefreshAdapter = new HomepageItemAdapter(getActivity(), mClientPerson);
        recyclerView.setAdapter(baseRefreshAdapter);
    }


    @Override
    protected HomePresenter getDelegateClass() {
        return new HomePresenter();
    }


    @OnClick({R.id.id_tv_app_name, R.id.id_tv_top_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_tv_app_name:
                break;
            case R.id.id_tv_top_up:
                break;
        }
    }

    @Override
    public void getHomeData(HomeDataBean baseData) {
        showRestoreView();
        HomeDataBean.DataBean homeData = baseData.getData();
        mClientPerson = homeData.getCustomers();
        baseRefreshAdapter.setListData(mClientPerson);
    }

    public class HomepageItemAdapter extends BaseRefreshAdapter<HomeDataBean.DataBean.CustomersBean> {

        public HomepageItemAdapter(Context context, List<HomeDataBean.DataBean.CustomersBean> datas) {
            super(context, datas, R.layout.item_myclientfrag);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, HomeDataBean.DataBean.CustomersBean topicBean, int position) {
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

}
