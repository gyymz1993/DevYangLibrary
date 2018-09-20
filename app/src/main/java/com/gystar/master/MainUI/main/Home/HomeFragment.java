package com.gystar.master.MainUI.main.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.CustomViews.RVDecoration;
import com.gystar.master.MainUI.main.Home.TopUP.TopUPActivity;
import com.gystar.master.MainUI.main.Home.message.MessageActivity;
import com.gystar.master.MainUI.main.MainTabActivity;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.HomeDataBean;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.utils.UIUtils;
import com.utils.gyymz.wiget.ComDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.R;

import static com.utils.gyymz.wiget.ComDialogBuilder.onDialogbtnClickListener.BUTTON_CANCEL;
import static com.utils.gyymz.wiget.ComDialogBuilder.onDialogbtnClickListener.BUTTON_CONFIRM;

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
    @BindView(R.id.id_tv_tops)
    TextView idTvTops;
    /*设置*/
    @BindView(R.id.id_ig_setting)
    ImageView igSetting;


    @BindView(R.id.id_tv_client_name)
    TextView tvClientName;

    @BindView(R.id.id_tv_up_time)
    TextView tvUpTime;


    @Override
    protected int getLayoutId() {
        return R.layout.gy_fragment_home;
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        // showLoadingView();
        showProgressDialog();
        initRvView();
        initView();
        mPresenter.getHomeshow();
    }


    protected void initView() {
        idTvTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(TopUPActivity.class);
            }
        });

        idTvTops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(MessageActivity.class);
            }
        });

        igSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        dismissProgressDialog();
        HomeDataBean.DataBean homeData = baseData.getData();
        mClientPerson = homeData.getCustomers();
        baseRefreshAdapter.setListData(mClientPerson);

        tvClientName.setText(homeData.getNike_name());
        tvUpTime.setText("剩余通话时长: " + homeData.getRemainder_call_time() + " min");

        SpUtils.getInstance().saveString(AppConfig.USER_PHONE, homeData.getPhone());
        SpUtils.getInstance().saveString(AppConfig.USER_NIKE_NAME, homeData.getNike_name());
        SpUtils.getInstance().setIntValue(AppConfig.CALL_CUSTOMER_NUM, homeData.getRemainder_call_time());
    }

    @Override
    public void buyTopPriceBuy() {
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
                           MainTabActivity mainTabActivity = (MainTabActivity)getActivity();
                            mainTabActivity.viewPager.setCurrentItem(2, true);
                            break;
                        default:
                            break;
                    }
                })
                .create().show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    public class HomepageItemAdapter extends BaseRefreshAdapter<HomeDataBean.DataBean.CustomersBean> {

        public HomepageItemAdapter(Context context, List<HomeDataBean.DataBean.CustomersBean> datas) {
            super(context, datas, R.layout.item_myclientfrag);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, HomeDataBean.DataBean.CustomersBean topicBean, int position) {
            //L_.e(topicBean.getName());
            TextView tvclient_name = viewHolder.getView(R.id.id_tv_client_name);
            tvclient_name.setText(topicBean.getName());

            TextView tv_eare = viewHolder.getView(R.id.id_tv_eare);
            tv_eare.setText(topicBean.getArea_name());

            TextView tv_match = viewHolder.getView(R.id.id_tv_match);
            tv_match.setText(topicBean.getPrice() + "");

            TextView tv_time = viewHolder.getView(R.id.id_tv_time);
            tv_time.setText(topicBean.getShelf_time());


            Button btnGoup = viewHolder.getView(R.id.id_tv_go_up);
            if (topicBean.getReceive_call_status() == 0) {
                btnGoup.setEnabled(false);
            } else {
                btnGoup.setEnabled(true);
            }
            Button btnGoBuy = viewHolder.getView(R.id.id_btn_go_buy);
            btnGoBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int remainderCallTime = SpUtils.getInstance().getIntValue(AppConfig.CALL_CUSTOMER_NUM, 0);
                    if (remainderCallTime > topicBean.getPrice()) {
                        //T_.showToastReal("购买成功");
                        mPresenter.getTopPriceBuy(VaxPhoneAPP.getUserId(), topicBean.getId() + "");
                    } else {
                        // T_.showToastReal("余额不足");
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
                                            Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            break;
                                    }
                                })
                                .create().show();
                    }
                }
            });

        }
    }

}
