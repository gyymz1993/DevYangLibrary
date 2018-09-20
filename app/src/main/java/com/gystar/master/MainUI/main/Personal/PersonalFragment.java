package com.gystar.master.MainUI.main.Personal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.MainUI.main.Home.TopUP.TopUPActivity;
import com.gystar.master.MainUI.main.SettingActivity;
import com.gystar.master.bean.PersonalBean;
import com.utils.gyymz.mvp.base.MVPLazyFragment;
import com.utils.gyymz.utils.SpUtils;
import com.zhouyou.view.segmentedbar.Segment;
import com.zhouyou.view.segmentedbar.SegmentedBarView;
import com.zhouyou.view.segmentedbar.SegmentedBarViewSideRule;

import java.util.ArrayList;

import butterknife.BindView;
import vaxsoft.com.vaxphone.R;

public class PersonalFragment extends MVPLazyFragment<PersonalPersenter> implements PersonalContract.View {

    @BindView(R.id.id_tv_clinet_name)
    TextView tvClientName;
    @BindView(R.id.id_tv_eare)
    TextView tvEare;
    @BindView(R.id.tv_remainder_call_time)
    TextView remainderCallTime;
    @BindView(R.id.id_tv_top_up)
    TextView idTvTopUp;
    @BindView(R.id.id_tv_hu_count)
    TextView idTvhuCount;
    @BindView(R.id.id_tv_call_time_average)
    TextView callTimeAverage;
    @BindView(R.id.id_tv_call_time_num)
    TextView callTimeNum;
    @BindView(R.id.id_ig_setting)
    ImageView igSetting;

    @BindView(R.id.up_count_barView)
    SegmentedBarView upCountBarView;


    @Override
    protected void afterCreate(Bundle var1) {
        super.afterCreate(var1);
        mPresenter.getPersonMessageShow();
        idTvTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(TopUPActivity.class);
            }
        });
        igSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SettingActivity.class);
            }
        });

//        ArrayList<Segment> segments = new ArrayList<>();
//        Segment segment = new Segment(0, 4.5f, "低", Color.TRANSPARENT);
//        segments.add(segment);
//        Segment segment2 = new Segment(4.5f, 6.5f, "中", Color.TRANSPARENT);
//        segments.add(segment2);
//        Segment segment3 = new Segment(6.5f, 20f, "高", Color.TRANSPARENT);
//        segments.add(segment3);
//        upCountBarView.setShowDescriptionText(true);
//        //barView.setGradientBgSegmentColor(Color.RED,Color.GREEN);//通过代码设置渐变
//        upCountBarView.setGradientBgSegmentColor(Color.parseColor("#EF3D2F"),Color.parseColor("#8CC63E"));
//        upCountBarView.setValue(4.96f);//显示的时候用“正常代替” 4.96f
//        upCountBarView.setSegmentSideRule(SegmentedBarViewSideRule.AVERAGE);//通过代码设置规则
//        upCountBarView.setGapWidth(0);
//        upCountBarView.setSegments(segments);
    }

    //设置渐变背景
    private void createBarViewWithBgColors() {
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "一般", Color.TRANSPARENT);
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "正常", Color.TRANSPARENT);
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "槽糕", Color.TRANSPARENT);
        segments.add(segment3);
        upCountBarView.setValue(4.96f);//显示的时候用“正常代替” 4.96f
        upCountBarView.setSegments(segments);
        upCountBarView.setGradientBgSegmentColor(Color.RED,Color.GREEN);//通过代码设置渐变
        upCountBarView.setShowDescriptionText(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void onVisible() {
        mPresenter.getPersonMessageShow();
    }

    @Override
    protected PersonalPersenter getDelegateClass() {
        return new PersonalPersenter();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    @Override
    public void getPersonalData(PersonalBean personalBean) {
        PersonalBean.DataBean personalBeanData = personalBean.getData();
        tvClientName.setText(personalBeanData.getNike_name());
        remainderCallTime.setText(personalBeanData.getRemainder_call_time() + " min");
        idTvhuCount.setText(personalBeanData.getCall_customer_todayNum() + "/" + personalBeanData.getCall_customer_num());
        callTimeAverage.setText(personalBeanData.getCall_time_average() + "");
        callTimeNum.setText(personalBeanData.getCall_time_num() + "");
        SpUtils.getInstance().saveString(AppConfig.USER_PHONE, personalBeanData.getPhone());
        SpUtils.getInstance().saveString(AppConfig.USER_NIKE_NAME, personalBeanData.getNike_name());
        SpUtils.getInstance().setIntValue(AppConfig.CALL_CUSTOMER_NUM, personalBeanData.getRemainder_call_time());
    }
}
