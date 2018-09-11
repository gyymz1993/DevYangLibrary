package com.gystar.master.MainUI.main.TopUP;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.andview.listener.OnItemClickListener;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.TopBean;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.T_;
import com.utils.gyymz.utils.UIUtils;
import com.utils.gyymz.wiget.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class TopUPActivity extends MVPBaseActivity<TopPersenter> implements TopContract.View {


    @BindView(R.id.id_recyvleview)
    RecyclerView idRecyvleview;
    @BindView(R.id.id_tv_buy)
    TextView tvBuy;
    /**
     * 当前选择的充值包
     */
    TopBean.DataBean currrentDataBean;
    TopUPItemAdapter topUPItemAdapter;
    List<TopBean.DataBean> mData = new ArrayList<>();

    @Override
    protected TopPersenter getDelegateClass() {
        return new TopPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_top_up;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        StaggeredGridLayoutManager mGridViewLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        idRecyvleview.setLayoutManager(mGridViewLayoutManager);
        topUPItemAdapter = new TopUPItemAdapter(getBaseContext(), mData);
        topUPItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerHolder baseRecyclerHolder, int position, Object o) {
                topUPItemAdapter.setSelect(position);
            }
        });
        idRecyvleview.setAdapter(topUPItemAdapter);
        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currrentDataBean!=null){
                    mPresenter.getBeginrecharge(TopUPActivity.this,currrentDataBean);
                }else {
                    T_.showCustomToast("请选择充值套餐");
                }

            }
        });
        showNavigationBarView().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "充值")
                .setText(NavigationBarView.NavigationViewType.RIGHT_TEXT, "历史");
        mPresenter.rechargeShow();
    }


    @Override
    public void getTopBeanList(List<TopBean.DataBean> data) {
        topUPItemAdapter.setListData(data);
    }


    public class TopUPItemAdapter extends BaseRefreshAdapter<TopBean.DataBean> {
        private int selected = 0;

        public void setSelect(int position) {
            if (selected == position)
                return;
            int lastSelected = selected;
            selected = position;
            notifyItemChanged(lastSelected);
            notifyItemChanged(position);
        }

        public TopUPItemAdapter(Context context, List<TopBean.DataBean> datas) {
            super(context, datas, R.layout.item_top_up);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, TopBean.DataBean var2, int position) {
            View thumbSelected_bg = viewHolder.getView(R.id.rl_layout);
            TextView textViewUp = viewHolder.getView(R.id.tv_up_text);
            if (position == selected) {
                currrentDataBean = var2;
                thumbSelected_bg.setBackgroundResource(R.drawable.price_select_style);
            } else {
                thumbSelected_bg.setBackgroundResource(R.drawable.price_un_select_style);
            }
            textViewUp.setText(var2.getDuration() + "小时" + UIUtils.getString(R.string.newLines)
                    + var2.getPrice() + "元");
        }
    }

}
