package com.gystar.master.MainUI.Call;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.gystar.master.CustomViews.CheckableTextView;
import com.gystar.master.MainUI.main.TabFrag.MyClientFragment;
import com.gystar.master.MainUI.main.WorkOder.remark.RemarkActivity;
import com.gystar.master.MainUI.main.WorkOder.remark.RemarkContract;
import com.gystar.master.MainUI.main.WorkOder.remark.RemarkPersenter;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.HistoryRemarkBean;
import com.gystar.master.bean.LableSelectBean;
import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.T_;
import com.utils.gyymz.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;


public class LabelStickerActivity extends MVPBaseActivity<RemarkPersenter> implements RemarkContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.id_tv_close)
    TextView tvClose;
    @BindView(R.id.id_tv_confir)
    TextView tvConfir;
    @BindView(R.id.id_ed_content)
    EditText editContent;

    private List<LableSelectBean> mData = new ArrayList<>();
    private static final String KEY_PAGEBEAN = "KEY_PAGEBEAN";
    private MyClientPerson.DataBean.PageBean pageBean;
    private StringBuilder strLable;

    @Override
    protected RemarkPersenter getDelegateClass() {
        return new RemarkPersenter();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
    }

    public static Intent getLaunchIntent(Context context, MyClientPerson.DataBean.PageBean pageBean) {
        Intent intent = new Intent(context, LabelStickerActivity.class);
        intent.putExtra(KEY_PAGEBEAN, pageBean);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_labelsticker;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        if (getIntent() != null) {
            pageBean = (MyClientPerson.DataBean.PageBean) getIntent().getSerializableExtra(KEY_PAGEBEAN);
        }
        initRv();
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvConfir.setOnClickListener(v -> {
            String remind = editContent.getText().toString();
            if (!TextUtils.isEmpty(remind) && !TextUtils.isEmpty(strLable.toString())) {
                mPresenter.getAddWorkorderRemark(pageBean.getId() + "", strLable.append(remind).toString());
            } else {
                T_.showToastReal("请填写备注信息");
            }
        });
    }


    public interface RefreshPriceInterface {
        void refreshPrice(List<LableSelectBean> lableSelectBeans);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void initRv() {
        mData.add(new LableSelectBean("有房"));
        mData.add(new LableSelectBean("有车"));
        mData.add(new LableSelectBean("公积金"));
        mData.add(new LableSelectBean("信用卡"));
        mData.add(new LableSelectBean("企业主"));
        mData.add(new LableSelectBean("业务员"));
        StaggeredGridLayoutManager mGridViewLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mGridViewLayoutManager);
        LableSelectItemAdapter citySelectItemAdapter = new LableSelectItemAdapter(this, mData);
        citySelectItemAdapter.setRefreshPriceInterface(new RefreshPriceInterface() {
            @Override
            public void refreshPrice(List<LableSelectBean> lableSelectBeans) {
                strLable = new StringBuilder();
                for (int i = 0; i < lableSelectBeans.size(); i++) {
                    if (lableSelectBeans.get(i).isChecked()) {
                        strLable.append(lableSelectBeans.get(i).getLableName() + ",");
                    }
                }
            }
        });
        recyclerView.setAdapter(citySelectItemAdapter);
    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void haseNoData() {

    }

    @Override
    public void getLoadData(HistoryRemarkBean historyRemarkBean, boolean isRefesh) {

    }

    @Override
    public void remarkSucceed() {

    }

    @Override
    public void onLoadingCompleted() {

    }

    @Override
    public void onAllPageLoaded() {

    }


    public class LableSelectItemAdapter extends BaseRefreshAdapter<LableSelectBean> {
        private int selected = 0;
        private RefreshPriceInterface refreshPriceInterface;

        public void setSelect(int position) {
            if (selected == position)
                return;
            selected = position;
            notifyDataSetChanged();
        }

        public LableSelectItemAdapter(Context context, List<LableSelectBean> datas) {
            super(context, datas, R.layout.item_lable_select);
        }

        public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface) {
            this.refreshPriceInterface = refreshPriceInterface;
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, LableSelectBean lableSelectBean, int position) {
            CheckableTextView tvlable = viewHolder.getView(R.id.id_tv_label);
            if (lableSelectBean.isChecked()) {
                tvlable.setChecked(true);
                tvlable.setTextColor(UIUtils.getColor(R.color.gytheme));
            } else {
                tvlable.setChecked(false);
                tvlable.setTextColor(UIUtils.getColor(R.color.statusBarColor));
            }
            tvlable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tvlable.isChecked()) {
                        lableSelectBean.setChecked(true);
                    } else {
                        lableSelectBean.setChecked(false);
                    }
                    notifyDataSetChanged();
                    if (refreshPriceInterface != null) {
                        refreshPriceInterface.refreshPrice(getListData());
                    }
                }
            });
            tvlable.setText(lableSelectBean.getLableName());
        }
    }
}
