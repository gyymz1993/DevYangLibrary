package com.gystar.master.MainUI.main.Personal;

import android.util.Log;

import com.gystar.master.Config.netApi.ModuleApi;
import com.gystar.master.bean.PersonalBean;
import com.utils.gyymz.http.subscriber.ApiFactory;
import com.utils.gyymz.http.subscriber.ResponseSubscriber;
import com.utils.gyymz.http.subscriber.ResponseTransformer;
import com.utils.gyymz.mvp.base.BasePresenterImpl;
import com.utils.gyymz.utils.T_;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class PersonalPersenter extends BasePresenterImpl<PersonalContract.View> implements PersonalContract.Presenter {


    public ModuleApi api = ApiFactory.getFactory().create(ModuleApi.class);


    public void getPersonMessageShow() {
        api.getPersonMessageShow(VaxPhoneAPP.getUserId())
                .compose(new ResponseTransformer<>())
                .subscribe(new ResponseSubscriber<PersonalBean>(mvpView) {
                    @Override
                    public void success(PersonalBean baseData) {
                        if (mvpView != null) {
                            mvpView.getPersonalData(baseData);
                        }
                    }

                    @Override
                    public void requestError(String exception) {
                        Log.e("TAG", exception);
                        T_.showCustomToast(exception);
                    }
                });

    }


}
