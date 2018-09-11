package com.gystar.master.MainUI.full;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.utils.gyymz.mvp.widget.LifeRecyclerView;
import com.utils.gyymz.utils.T_;


/**
 * Created by qiyue on 2018/7/10.
 */

public class MeiziListView extends LifeRecyclerView<MyCollectPresenter> implements MyCollectContract.View {


    public MeiziListView(Context context) {
        super(context);
    }

    public MeiziListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeiziListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void ON_CREATE() {
        super.ON_CREATE();
        StaggeredGridLayoutManager mGridViewLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(mGridViewLayoutManager);
        mPresenter.getAppBasicInfo1();
       // mPresenter.cancelRequest("MyCollectPresenter");
       // mPresenter.getAppBasicInfo();
       // mPresenter.cancelRequest("MyCollectPresenter");

    }


    @Override
    protected MyCollectPresenter getDelegateClass() {
        return new MyCollectPresenter();
    }


    @Override
    public void getDataSucceed(String payParams) {
        T_.showCustomToast(payParams);
    }
}
