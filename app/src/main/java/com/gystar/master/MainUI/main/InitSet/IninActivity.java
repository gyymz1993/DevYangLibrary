package com.gystar.master.MainUI.main.InitSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.MainUI.main.MainTabActivity;
import com.utils.gyymz.base.BaseAppCompatFragment;
import com.utils.gyymz.http.callback.DataBeanCallBack;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.FragmentActivity;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.wiget.NavigationBarView;

import vaxsoft.com.vaxphone.R;

import static com.gystar.master.MainUI.main.InitSet.CitySelectFragment.CITY_SELECT;

public class IninActivity extends FragmentActivity {

    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected BaseAppCompatFragment getFirstFragment() {
        return CitySelectFragment.newInstance(CITY_SELECT);
    }

    @Override
    protected BasePresenter getDelegateClass() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_init;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        showNavigationBarView().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "个人设置")
                .setText(NavigationBarView.NavigationViewType.RIGHT_TEXT, "跳过").setOnClickListener(
                NavigationBarView.NavigationViewType.RIGHT_TEXT, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phome = SpUtils.getInstance().getString(AppConfig.USER_PHONE);
                        getAddusermessage(phome, 1 + "", "用户" + phome, "1");
                    }
                });
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }

    /**
     * 如果用户点击跳过直接注册
     * <p>
     * mPresenter.getAddusermessage(phome, currentCity.getId() + "",
     * "用户"+phome, "1");
     */
    public void getAddusermessage(String phone, String cityCode
            , String nickName, String workYears) {
        api.getAddusermessage(phone, cityCode, nickName, workYears).compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<DataBeanCallBack>(null) {
                    @Override
                    public void success(DataBeanCallBack baseData) {
                        openActivity(MainTabActivity.class);
                        finish();
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                    }
                });


    }
}
