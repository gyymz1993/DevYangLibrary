package com.gystar.master.MainUI.main.InitSet;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.adapter.BaseRecyclerHolder;
import com.andview.listener.OnItemClickListener;
import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.MainUI.main.MainTabActivity;
import com.gystar.master.base.BaseRefreshAdapter;
import com.gystar.master.bean.CityListBean;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.utils.T_;
import com.utils.gyymz.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class CitySelectFragment extends MVPLazyFragment<CitySelectPersenter> implements CitySelectContract.View {

    public static final String ARG_VERIFY = "verify";
    public static final int CITY_SELECT = 0;
    public static final int USET_SET = 1;
    private int mAction;
    @BindView(R.id.id_rv_city)
    RecyclerView recyclerView;
    @BindView(R.id.tv_select_city)
    TextView selectCity;
    @BindView(R.id.ry_ct_select)
    RelativeLayout relaCtSelect;
    @BindView(R.id.ry_use_setting)
    RelativeLayout relaUserSetting;
    @BindView(R.id.id_city_tv_next)
    TextView cityTvNext;

    @BindView(R.id.id_rv_user)
    RecyclerView rvUserView;
    @BindView(R.id.ed_tv_nainxian)
    TextView tvNainxian;
    @BindView(R.id.id_tv_user_next)
    TextView tvUserNext;

    @BindView(R.id.ed_user_name)
    EditText edUserName;


    private CityListBean.DataBean currentCity;


    private List<CityListBean.DataBean> data = new ArrayList<>();
    private CitySelectItemAdapter citySelectItemAdapter;

    public static CitySelectFragment newInstance(int action) {
        CitySelectFragment fragment = new CitySelectFragment();
        Bundle arg = new Bundle();
        arg.putInt(ARG_VERIFY, action);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    protected void afterCreate(Bundle var1) {
        super.afterCreate(var1);
        if (getArguments() != null) {
            mAction = getArguments().getInt(ARG_VERIFY);
        }
        if (mAction == CITY_SELECT) {
            relaCtSelect.setVisibility(View.VISIBLE);
            relaUserSetting.setVisibility(View.GONE);
            initRv();
            mPresenter.getCityList();
        } else {
            relaUserSetting.setVisibility(View.VISIBLE);
            relaCtSelect.setVisibility(View.GONE);
            initUserRv();
        }
        initView();
    }

    protected void initView() {
        cityTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(CitySelectFragment.newInstance(CitySelectFragment.USET_SET));
            }
        });
        tvUserNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String phome = SpUtils.getInstance().getString(AppConfig.USER_PHONE);

               if (TextUtils.isEmpty(edUserName.getText().toString())){
                   T_.showCustomToast("请填写用户名");
               }else {
                   mPresenter.getAddusermessage(phome, currentCity.getId() + "",
                           edUserName.getText().toString(), "1");
               }

             //  openActivity(MainTabActivity.class);
              // getHoldingActivity().finish();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_city_select;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected CitySelectPersenter getDelegateClass() {
        return new CitySelectPersenter();
    }


    @Override
    public void setCityData(List<CityListBean.DataBean> data) {
        citySelectItemAdapter.setListData(data);
    }

    @Override
    public void regesterSuccess() {
        openActivity(MainTabActivity.class);
        getHoldingActivity().finish();
    }

    public void initRv() {
        StaggeredGridLayoutManager mGridViewLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mGridViewLayoutManager);
        citySelectItemAdapter = new CitySelectItemAdapter(getContext(), data);
        citySelectItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerHolder baseRecyclerHolder, int position, Object o) {
                citySelectItemAdapter.setSelect(position);
            }
        });
        recyclerView.setAdapter(citySelectItemAdapter);
    }


    public void initUserRv() {
        StaggeredGridLayoutManager mGridViewLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        rvUserView.setLayoutManager(mGridViewLayoutManager);
        List<CityListBean.DataBean> datas = new ArrayList<>();
        CityListBean.DataBean dataBean = new CityListBean.DataBean();
        dataBean.setName("新人");
        datas.add(dataBean);
        CityListBean.DataBean dataBean1 = new CityListBean.DataBean();
        dataBean1.setName("1~3年");
        datas.add(dataBean1);
        CityListBean.DataBean dataBean2 = new CityListBean.DataBean();
        dataBean2.setName("元老");
        datas.add(dataBean2);
        citySelectItemAdapter = new CitySelectItemAdapter(getContext(), datas);
        citySelectItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerHolder baseRecyclerHolder, int position, Object o) {
                citySelectItemAdapter.setSelect(position);
            }
        });
        rvUserView.setAdapter(citySelectItemAdapter);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }


    public class CitySelectItemAdapter extends BaseRefreshAdapter<CityListBean.DataBean> {
        private int selected = 0;
        List<Integer> selects=new ArrayList<>();

        public void setSelect(int position) {
            if (selected == position)
                return;
            selected = position;
            notifyDataSetChanged();
        }

        public CitySelectItemAdapter(Context context, List<CityListBean.DataBean> datas) {
            super(context, datas, R.layout.item_city_select);
        }

        @Override
        protected void convert(BaseRecyclerHolder viewHolder, CityListBean.DataBean var2, int position) {
            TextView cityName = viewHolder.getView(R.id.id_tv_city_name);
            if (position == selected) {
                currentCity = var2;
                cityName.setBackgroundResource(R.drawable.city_selector);
                cityName.setTextColor(UIUtils.getColor(R.color.gytheme));
                selectCity.setText(var2.getName());
                tvNainxian.setText(var2.getName());
            } else {
                cityName.setBackgroundResource(R.drawable.city_un_selector);
                cityName.setTextColor(UIUtils.getColor(R.color.statusBarColor));
            }
            cityName.setText(var2.getName());
        }
    }
}
