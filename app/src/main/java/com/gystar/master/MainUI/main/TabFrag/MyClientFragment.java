package com.gystar.master.MainUI.main.TabFrag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.CustomViews.RVDecoration;
import com.gystar.master.MainUI.Call.GyCallActivity;
import com.gystar.master.MainUI.main.Home.TopUP.TopUPActivity;
import com.gystar.master.MainUI.main.WorkOder.remark.RemarkActivity;
import com.gystar.master.Utils.RegexpUtils;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.MyClientPerson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.statuslayout.OnStatusChildClickListener;
import com.utils.gyymz.statuslayout.StatusLayoutManager;
import com.utils.gyymz.utils.L_;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.utils.T_;
import com.utils.gyymz.utils.UIUtils;
import com.utils.gyymz.vary.MyLoadViewFactory;
import com.utils.gyymz.wiget.ComDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.R;

import static com.utils.gyymz.wiget.ComDialogBuilder.onDialogbtnClickListener.BUTTON_CANCEL;
import static com.utils.gyymz.wiget.ComDialogBuilder.onDialogbtnClickListener.BUTTON_CONFIRM;


/**
 * 我的客户
 */
public class MyClientFragment extends MVPLazyFragment<MePresenter> implements MeContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.id_ly_client_buy)
    LinearLayout lyClientBuy;
    @BindView(R.id.id_tv_client_buy)
    TextView tvClientBuy;
    @BindView(R.id.id_ly_price_total)
    LinearLayout lyPriceTotal;
    @BindView(R.id.id_tv_price)
    TextView tvPrice;
    @BindView(R.id.id_tv_go_buy)
    TextView tvGoBuy;

    private int mStatu = 0;
    private MyLoadViewFactory myLoadViewFactory;
    private HomepageItemAdapter baseRefreshAdapter;
    private List<MyClientPerson.DataBean.PageBean> mClientPerson = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.gy_fragment_myclient;
    }

    @Override
    protected void onVisible() {
    }


    public void onfreshData() {
        if (mPresenter != null) {
            mPresenter.getOntermsCustomerList(mStatu, true);
        }
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Bundle dataBundle = getArguments();
        if (dataBundle != null) {
            mStatu = dataBundle.getInt(TheBiddingFragment.KEY_STATE);
        }
        initRefresh();
        myLoadViewFactory.madeLoadView().showLoading();
        mPresenter.getOntermsCustomerList(mStatu, true);
        if (mStatu == 0) {
            lyClientBuy.setVisibility(View.VISIBLE);
            lyClientBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isShowBuy = !baseRefreshAdapter.isShowBuy;
                    baseRefreshAdapter.setShowBuy(isShowBuy);
                }
            });
        } else {
            lyClientBuy.setVisibility(View.GONE);
        }
        tvGoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ids.size() == 0) {
                    T_.showCustomToast("请选择意向客户");
                } else {
                    mPresenter.getPricebuybatch(RegexpUtils.getIdString(ids));
                }
            }
        });
    }

    @Override
    protected MePresenter getDelegateClass() {
        return new MePresenter();
    }


    /**
     * 初始化刷新
     */
    List<Integer> ids = new ArrayList<>();

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
        baseRefreshAdapter.setRefreshPriceInterface(new RefreshPriceInterface() {
            @Override
            public void refreshPrice(List<MyClientPerson.DataBean.PageBean> page) {
                int price = 0;
                for (int i = 0; i < page.size(); i++) {
                    if (page.get(i).isChecked()) {
                        ids.add(page.get(i).getId());
                        price += page.get(i).getPrice();
                    }
                }
                tvPrice.setText(price + "");
            }
        });
        recyclerView.setAdapter(baseRefreshAdapter);
        myLoadViewFactory = new MyLoadViewFactory(recyclerView);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {
        myLoadViewFactory.madeLoadView().showFail("");
        refreshLayout.finishRefresh(false);
        refreshLayout.finishLoadMore(false);
        refreshLayout.setEnableLoadMore(false);
    }


    public class HomepageItemAdapter extends BaseRefreshAdapter<MyClientPerson.DataBean.PageBean> {

        private boolean isShowBuy;
        private RefreshPriceInterface refreshPriceInterface;


        public HomepageItemAdapter(Context context, List<MyClientPerson.DataBean.PageBean> datas) {
            super(context, datas, R.layout.item_myclientfrag);
        }

        public void setShowBuy(boolean isShow) {
            isShowBuy = isShow;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, MyClientPerson.DataBean.PageBean topicBean, int position) {
            LinearLayout lyRoot = viewHolder.getView(R.id.id_ly_root);
            TextView tvclient_name = viewHolder.getView(R.id.id_tv_client_name);
            tvclient_name.setText(topicBean.getName());
            TextView tv_eare = viewHolder.getView(R.id.id_tv_eare);
            tv_eare.setText(topicBean.getArea_name());
            TextView tv_match = viewHolder.getView(R.id.id_tv_match);
            tv_match.setText(topicBean.getPrice() + "");
            TextView tv_time = viewHolder.getView(R.id.id_tv_time);
            tv_time.setText(topicBean.getShelf_time());
            Button btnGoUp = viewHolder.getView(R.id.id_tv_go_up);
            Button btnBuyUp = viewHolder.getView(R.id.id_btn_go_buy);
            LinearLayout layRemark = viewHolder.getView(R.id.id_lay_remark);
            TextView tvRemark = viewHolder.getView(R.id.id_tv_remark);
            LinearLayout lyChecke = viewHolder.getView(R.id.id_ly_checke);
            CheckBox clientChecke = viewHolder.getView(R.id.all_chekbox);
            TextView tvCanCallTime = viewHolder.getView(R.id.id_tv_can_call_time);
            tvCanCallTime.setText(topicBean.getCan_call_time());
            if (topicBean.isChecked()) {
                clientChecke.setChecked(true);
            } else {
                clientChecke.setChecked(false);
            }
            clientChecke.setOnClickListener(view -> {
                if (((CheckBox) view).isChecked()) {
                    topicBean.setChecked(true);
                } else {
                    topicBean.setChecked(false);
                }
                if (refreshPriceInterface != null) {
                    refreshPriceInterface.refreshPrice(getListData());
                }
            });

            if (isShowBuy) {
                lyPriceTotal.setVisibility(View.VISIBLE);
                lyChecke.setVisibility(View.VISIBLE);
                tvClientBuy.setText("取消");
                lyClientBuy.setBackground(UIUtils.getDrawable(R.drawable.un_call_end_button_state));
            } else {
                lyPriceTotal.setVisibility(View.GONE);
                lyChecke.setVisibility(View.GONE);
                tvClientBuy.setText("一键购买");
                lyClientBuy.setBackground(UIUtils.getDrawable(R.drawable.call_end_button_state));
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) lyRoot.getLayoutParams();
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
            if (mStatu == 2) {
                layoutParams.height = UIUtils.dip2px(190);
                layRemark.setVisibility(View.VISIBLE);
                tvRemark.setText(topicBean.getRemark());
                btnBuyUp.setText("备注");
                btnBuyUp.setOnClickListener(v -> {
                    Intent launchIntent = RemarkActivity.getLaunchIntent(getContext(), topicBean);
                    startActivity(launchIntent);
                });
            } else {
                layoutParams.height = UIUtils.dip2px(160);
                layRemark.setVisibility(View.GONE);
                btnBuyUp.setText("购买");
                btnBuyUp.setOnClickListener(v -> {
                    int remainderCallTime = SpUtils.getInstance().getIntValue(AppConfig.CALL_CUSTOMER_NUM, 0);
                    if (remainderCallTime > topicBean.getPrice()) {
                        mPresenter.getTopPriceBuy(VaxPhoneAPP.getUserId(), topicBean.getId() + "");
                        mClientPerson.remove(topicBean);
                    } else {
                        showDialog();
                    }
                });
            }

            btnGoUp.setOnClickListener(v -> {
                int remainderCallTime = SpUtils.getInstance().getIntValue(AppConfig.CALL_CUSTOMER_NUM, 0);
                if (remainderCallTime > topicBean.getPrice()) {
                    Intent launchIntent = GyCallActivity.getLaunchIntent(getContext(), topicBean);
                    getActivity().startActivity(launchIntent);
                } else {
                    showDialog();
                }

            });

            if (topicBean.getReceive_call_status() == 1) {
                btnGoUp.setBackground(UIUtils.getDrawable(R.drawable.login_code_shape_enabled));
                btnGoUp.setEnabled(false);
            } else {
                btnGoUp.setBackground(UIUtils.getDrawable(R.drawable.login_code_shape_unenabled));
                btnGoUp.setEnabled(true);
            }

        }

        public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface) {
            this.refreshPriceInterface = refreshPriceInterface;
        }


    }

    public void showDialog() {
        new ComDialogBuilder(getContext())
                .setTouchOutSideCancelable(false)
                .setCancelable(false)
                .showCancelButton(true)
                .setMessage("余额不足")
                .setConfirmButtonText("充值")
                .setCancelButtonText("取消")
                .setDialogAnimation(ComDialogBuilder.DIALOG_ANIM_SLID_BOTTOM).setButtonClickListener(true,
                (context, dialog, whichBtn) -> {
                    switch (whichBtn) {
                        case BUTTON_CONFIRM:
                            // Toast.makeText(context, "点击了充值按钮", Toast.LENGTH_SHORT).show();
                            openActivity(TopUPActivity.class);
                            break;
                        case BUTTON_CANCEL:
                            break;
                        default:
                            break;
                    }
                })
                .create().show();
    }


    public interface RefreshPriceInterface {
        void refreshPrice(List<MyClientPerson.DataBean.PageBean> page);
    }


    @Override
    public void onLoadingCompleted() {
    }

    @Override
    public void onAllPageLoaded() {
        noMoreData();
    }


    @Override
    public void getClientList(MyClientPerson myClientPerson, boolean reload) {
        myLoadViewFactory.madeLoadView().restore();
        MyClientPerson.DataBean data = myClientPerson.getData();
        List<MyClientPerson.DataBean.PageBean> page = data.getPage();
        if (reload) {
            refreshLayout.finishRefresh(true);
            refreshLayout.setNoMoreData(false);
            refreshLayout.setEnableLoadMore(true);
            baseRefreshAdapter.setListData(page);
            tvPrice.setText(0 + "");
        } else {
            baseRefreshAdapter.addAll(page);
            refreshLayout.finishLoadMore(true);
        }
        if (mStatu == 0) {
            lyClientBuy.setVisibility(View.VISIBLE);
        } else {
            lyClientBuy.setVisibility(View.GONE);
        }
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
    public void hasNoData() {
        lyClientBuy.setVisibility(View.GONE);
        myLoadViewFactory.madeLoadView().showEmptSrc(R.drawable.ic_network);
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadMore(true);
        /*不许加载更多*/
        refreshLayout.setEnableLoadMore(false);
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


    @Override
    public void loadFaild(String error) {
        lyClientBuy.setVisibility(View.GONE);
        myLoadViewFactory.madeLoadView().showFail(error);
        refreshLayout.finishRefresh(false);
        refreshLayout.finishLoadMore(false);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void buyTopPriceBuy() {
        /**
         * 购买成功  移除已经购买过的
         */
        mPresenter.getOntermsCustomerList(mStatu, true);
        new ComDialogBuilder(getContext())
                .setTouchOutSideCancelable(false)
                .setCancelable(false)
                .showCancelButton(false)
                .setTitleMessage("成功出价")
                .setMessage("竞拍成功后," + UIUtils.getString(R.string.newLines) +
                        "系统会第一时间通知你的竞拍情况")
                .setConfirmButtonText("确认")
                .setDialogAnimation(ComDialogBuilder.DIALOG_ANIM_SLID_BOTTOM).setButtonClickListener(true,
                (context, dialog, whichBtn) -> {
                    switch (whichBtn) {
                        case BUTTON_CONFIRM:
                            TheBiddingFragment.instance.setCurrentItem(2);
                            break;
                        default:
                            break;
                    }
                })
                .create().show();

    }
}
