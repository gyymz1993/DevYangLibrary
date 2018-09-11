package com.utils.gyymz.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lsjr.net.R;

import butterknife.BindView;


/**
 * 标题栏
 */
public class NavBar extends TranslucentNavBar {


    public NavBar(Context context) {
        super(context);
    }

    public NavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_v_nav;
    }

    @Override
    protected void initView() {
        setColor(0xff333333);
    }

    public void setTitle(String title) {
        //tvTitle.setText(title);
    }

    public void setTitle(int title) {
       // tvTitle.setText(title);
    }

}
