package com.gystar.master.MainUI.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.gystar.master.MainUI.main.Home.HomeFragment;
import com.gystar.master.MainUI.main.TabFrag.TheBiddingFragment;
import com.gystar.master.MainUI.main.TabFrag.MyClientFragment;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.ys.uilibrary.tab.BottomTabView;
import com.ys.uilibrary.vp.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class MainTabActivity extends MVPBaseActivity {
    @BindView(R.id.viewPager)
    public NoScrollViewPager viewPager;
    @BindView(R.id.bottomTabView)
    public BottomTabView bottomTabView;
    protected FragmentPagerAdapter adapter;
    protected ArrayList<Fragment> fragments = new ArrayList<>();
    protected ArrayList<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
    protected String[] titles = new String[]{"首页", "竞拍", "我的客户"};
    protected Fragment v_1_3homeFrg, design, meFrg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initButtomTab();
        hideNavigationBarView();
    }

    @Override
    protected BasePresenter getDelegateClass() {
        return null;
    }

    protected List<Fragment> getFragments() {
        fragments = new ArrayList<>();
        fragments.add(v_1_3homeFrg);
        fragments.add(design);
        fragments.add(meFrg);
        return fragments;
    }


    protected void initButtomTab() {
        bottomTabView.setTabItemViews(getTabViews());
        bottomTabView.setOnTabItemSelectListener(new BottomTabView.OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                viewPager.setCurrentItem(position, true);
            }
        });
        initParams();
    }


    protected List<BottomTabView.TabItemView> getTabViews() {
        for (int i = 0; i < titles.length; i++) {
            switch (i) {
                case 0:
                    BottomTabView.TabItemView tabItemView1 = new BottomTabView.TabItemView(this, titles[0], R.color.tab_un_selected_color, R.color.tab_selected_color, R.drawable.tab_home_un, R.drawable.tab_home);
                    tabItemViews.add(tabItemView1);
                    break;
                case 1:
                    BottomTabView.TabItemView tabItemView2 = new BottomTabView.TabItemView(this, titles[1], R.color.tab_un_selected_color, R.color.tab_selected_color, R.drawable.tab_jp_un, R.drawable.tab_jp);
                    tabItemViews.add(tabItemView2);
                    break;
                case 2:
                    BottomTabView.TabItemView tabItemView4 = new BottomTabView.TabItemView(this, titles[2], R.color.tab_un_selected_color, R.color.tab_selected_color, R.drawable.tab_me_un, R.drawable.tab_me);
                    tabItemViews.add(tabItemView4);
                    break;
            }
        }
        return tabItemViews;
    }


    public void initParams() {
        v_1_3homeFrg = new HomeFragment();
        design = new TheBiddingFragment();
        meFrg = new MyClientFragment();
        //设置ViewPager的缓存界面数,默认缓存为2
        viewPager.setOffscreenPageLimit(tabItemViews.size());
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomTabView.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
