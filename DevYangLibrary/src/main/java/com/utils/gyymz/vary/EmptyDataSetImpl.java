package com.utils.gyymz.vary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lsjr.net.R;
import com.utils.gyymz.utils.UIUtils;


public class EmptyDataSetImpl extends RelativeLayout {

    public Context mcontext;
    public EmptyDataSetDelegate delegate;
    private ViewGroup msuperGroup;
    private View mfatherView;
    private ImageView imageBTN;
    private TextView titleV;
    private boolean isFirstCreated;

    public EmptyDataSetImpl(View fatherView) {
        super(fatherView.getContext());
        mcontext = fatherView.getContext();
        this.isFirstCreated = false;
        this.msuperGroup = (ViewGroup) fatherView.getParent();
        this.mfatherView = fatherView;
        ViewGroup.LayoutParams mparam = mfatherView.getLayoutParams();
        this.setLayoutParams(mparam);
        this.imageBTN = new ImageView(mcontext);
        this.imageBTN.setId(R.id.datasetID);
        titleV = new TextView(mcontext);
    }

    public void setDelegate(EmptyDataSetDelegate delegate) {
        this.delegate = delegate;
    }

    public void hasData() {
        switchView(true);
    }

    public void noData() {
        setPHEmptyDataSetWithType(TapNoDataType.PHEmptyNoData);
    }

    public void noNetWork() {
        setPHEmptyDataSetWithType(TapNoDataType.PHEmptyNoDataNoNetwork);
    }

    private void setPHEmptyDataSetWithType(TapNoDataType type) {

        imageBTN.setClickable(true);
        Integer verticalOff = delegate == null ? null : delegate.verticalOffsetForEmptyDataSet(this, type);
        if (verticalOff == null) {
            verticalOff = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_offset_v_noData :
                    EmptyDataSetConstants.DataSet_offset_v_noNetWork;
        }

        Integer horizonOff = delegate == null ? null : delegate.horizonOffsetForEmptyDataSet(this, type);
        if (horizonOff == null) {
            horizonOff = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_offset_h_noData :
                    EmptyDataSetConstants.DataSet_offset_h_noNetWork;
        }


        Integer titleimagePadding = delegate == null ? null : delegate.imageTitlePaddingForEmptyDataSet(this, type);
        if (titleimagePadding == null) {
            titleimagePadding = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_Image_title_padding :
                    EmptyDataSetConstants.DataSet_Image_title_padding;
        }

        Integer color = delegate == null ? null : delegate.backgroundColorForEmptyDataSet(this, type);
        if (color == null) {
            color = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_backGround_noData :
                    EmptyDataSetConstants.DataSet_backGround_noNetWork;
        }
        setBackgroundColor(color);

        CharSequence title;
        Drawable draw;
        Integer titleColor;
        Float titleSize;
        Integer mwidth;
        Integer mheight;

        if (delegate.customView() != null) {
            if (!isFirstCreated) {
                RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                param.addRule(RelativeLayout.CENTER_IN_PARENT);
                addView(delegate.customView(), param);
                switchView(true);
                isFirstCreated = true;
            }
            return;
        }

        Button button = delegate == null ? null : delegate.viewForEmptyDataSet(this, type);
        if (button == null) {
            title = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_title_noData : EmptyDataSetConstants.DataSet_title_noNetWork;
            draw = (type == TapNoDataType.PHEmptyNoData) ? new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),
                    EmptyDataSetConstants.DataSet_pic_noData)) : new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),
                    EmptyDataSetConstants.DataSet_pic_noNetWork));
            titleColor = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_titleColor_noData : EmptyDataSetConstants.DataSet_titleColor_noNetWork;
            titleSize = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_titleSize_noData : EmptyDataSetConstants.DataSet_titleSize_noNetWork;
            mwidth = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_width_noData : EmptyDataSetConstants.DataSet_width_noNetWork;
            mheight = (type == TapNoDataType.PHEmptyNoData) ? EmptyDataSetConstants.DataSet_height_noData : EmptyDataSetConstants.DataSet_height_noNetWork;
        } else {
            title = button.getText();
            draw = button.getBackground();
            titleColor = button.getCurrentTextColor();
            titleSize = button.getTextSize();
            mwidth = button.getLayoutParams().width;
            mheight = button.getLayoutParams().height;
        }

        Integer superHeight = getLayoutParams().height == -1 ? msuperGroup.getHeight() : getLayoutParams().height;
        Integer superWidth = getLayoutParams().width == -1 ? msuperGroup.getWidth() : getLayoutParams().width;
       // imageBTN.setImageResource(R.drawable.);
        imageBTN.setScaleType(ImageView.ScaleType.FIT_XY);

        titleV.setText("测试测试");
       // titleV.setTextColor(UIUtils.getColor(R.color.white));
        titleV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleSize);
        titleV.setTextAlignment(TEXT_ALIGNMENT_CENTER);


        RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(mwidth, mheight);
        param1.addRule(RelativeLayout.CENTER_IN_PARENT);
        param1.topMargin = (superHeight - param1.height) / 2 + verticalOff;
        param1.leftMargin = (superWidth - param1.width) / 2 + horizonOff;
        imageBTN.setLayoutParams(param1);

        RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // param2.topMargin = titleimagePadding;
        param2.leftMargin = (superWidth - param2.width) / 2 + horizonOff;
        param2.addRule(RelativeLayout.BELOW, R.id.datasetID);
        param2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        param2.topMargin = UIUtils.dip2px(10);
        titleV.setLayoutParams(param2);

        if (!isFirstCreated) {
            addView(imageBTN);
            addView(titleV);
            isFirstCreated = true;
        }

        final TapNoDataType mtype = type;
        imageBTN.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBTN.setClickable(false);
                Boolean isAllowedClick = delegate == null ? null : delegate.didTapEmptyDataView(EmptyDataSetImpl.this, mtype);
                if (isAllowedClick == null) {
                    isAllowedClick = false;
                }
                if (isAllowedClick) {
                    EmptyDataSetImpl.this.hasData();
                }
            }
        });
        titleV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBTN.setClickable(false);
                Boolean isAllowedClick = delegate == null ? null : delegate.didTapEmptyDataView(EmptyDataSetImpl.this, mtype);
                if (isAllowedClick == null) {
                    isAllowedClick = false;
                }
                if (isAllowedClick) {
                    EmptyDataSetImpl.this.hasData();
                }
            }
        });
        switchView(true);
    }

    private void switchView(boolean isChange) {
        if (isChange) {
            // AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(mcontext, R.anim.alphato1);
            int index = msuperGroup.indexOfChild(mfatherView);
            if (index != -1) {
                msuperGroup.removeView(mfatherView);
            }
            if ((this.getParent() == null) && (index != -1)) {
                msuperGroup.addView(this, index);
                // this.startAnimation(alphaAnimation);}
            } else {
                // AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(mcontext,R.anim.alphato1);
                index = msuperGroup.indexOfChild(this);
                if (index != -1) {
                    msuperGroup.removeView(this);
                }
                if ((mfatherView.getParent() == null) && (index != -1)) {
                    msuperGroup.addView(mfatherView, index);
                    // mfatherView.startAnimation(alphaAnimation);}
                }
            }
        }
    }

    /*
    * 没有网络的时候PHEmptyNoDataNoNetwork;
    * 没有数据的时候PHEmptyNoData;
    * */
    public enum TapNoDataType {
        PHEmptyNoDataNoNetwork,
        PHEmptyNoData,
    }

    /*
    *
    * */
    public interface PHEmptyDataSetDelegate {
        // 点击图片实现加载，通过返回bool值决定视图是否消失,不设置返回null，使用默认配置。
        Boolean didTapEmptyDataView(RelativeLayout layout, TapNoDataType type);

        // 获取自定义的视图，不设置返回null，使用默认配置。
        Button viewForEmptyDataSet(RelativeLayout layout, TapNoDataType type);

        //设置背景颜色
        Integer backgroundColorForEmptyDataSet(RelativeLayout layout, TapNoDataType type);

        //设置垂直偏移量
        Integer verticalOffsetForEmptyDataSet(RelativeLayout layout, TapNoDataType type);

        //设置水平偏移
        Integer horizonOffsetForEmptyDataSet(RelativeLayout layout, TapNoDataType type);

        //设置水平偏移
        Integer imageTitlePaddingForEmptyDataSet(RelativeLayout layout, TapNoDataType type);
        //设置水平偏移

        View customView();
    }

    public abstract static class EmptyDataSetDelegate implements PHEmptyDataSetDelegate {

        @Override
        public Boolean didTapEmptyDataView(RelativeLayout layout, TapNoDataType type) {
            return null;
        }

        @Override
        public Button viewForEmptyDataSet(RelativeLayout layout, TapNoDataType type) {
            return null;
        }

        @Override
        public Integer backgroundColorForEmptyDataSet(RelativeLayout layout, TapNoDataType type) {
            return null;
        }

        @Override
        public Integer verticalOffsetForEmptyDataSet(RelativeLayout layout, TapNoDataType type) {
            return null;
        }

        @Override
        public Integer horizonOffsetForEmptyDataSet(RelativeLayout layout, TapNoDataType type) {
            return null;
        }

        @Override
        public Integer imageTitlePaddingForEmptyDataSet(RelativeLayout layout, TapNoDataType type) {
            return null;
        }
    }
}
