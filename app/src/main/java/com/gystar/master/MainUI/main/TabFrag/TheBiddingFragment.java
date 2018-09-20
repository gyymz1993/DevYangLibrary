package com.gystar.master.MainUI.main.TabFrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.utils.gyymz.base.BaseAppCompatFragment;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.utils.L_;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

/**
 * 竞拍
 */
public class TheBiddingFragment extends MVPLazyFragment<MePresenter> {


    public static TheBiddingFragment instance;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp_movies)
    ViewPager vpMovies;
    private String[] titles = new String[]{"全部客户", "已拨打", "已购买"};
    private ArrayList<BaseAppCompatFragment> fragments = new ArrayList<>();
    public static String KEY_STATE = "KEY_STATE";

    @Override
    protected int getLayoutId() {
        return R.layout.gy_fragment_me;
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        instance = this;
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new MyClientFragment());
        }
        InnerPagerAdapter pagerAdapter = new InnerPagerAdapter(getFragmentManager(), fragments);
        vpMovies.setAdapter(pagerAdapter);
        vpMovies.setOffscreenPageLimit(2);
        tabLayout.setViewPager(vpMovies, titles);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onfreshAllFragment() {
        for (int i = 0; i < fragments.size(); i++) {
            MyClientFragment myClientFragment = (MyClientFragment) fragments.get(i);
            myClientFragment.onfreshData();
        }
    }


    public void setCurrentItem(int position) {
        MyClientFragment myClientFragment = (MyClientFragment) fragments.get(position);
        myClientFragment.onfreshData();
        TheBiddingFragment.instance.vpMovies.setCurrentItem(position, true);

    }

    @Override
    protected MePresenter getDelegateClass() {
        return new MePresenter();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }


    class InnerPagerAdapter extends FragmentPagerAdapter {
        private List<BaseAppCompatFragment> fragments;

        public InnerPagerAdapter(FragmentManager fm, ArrayList<BaseAppCompatFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            MyClientFragment fragment = (MyClientFragment) fragments.get(position);
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_STATE, getIntegerforTitle(position));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 覆写destroyItem并且空实现,这样每个Fragment中的视图就不会被销毁
            // super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    public int getIntegerforTitle(int position) {
        String title = titles[position];
        // 查询状态（0.全部，1.进行中，2.未开始）
        if (title.equals("已收藏")) {
            return 1;
        } else if (title.equals("已拨打")) {
            return 1;
        } else if (title.equals("已购买")) {
            return 2;
        } else if (title.equals("全部客户")) {
            return 0;
        } else {
            return 0;
        }
    }


}
