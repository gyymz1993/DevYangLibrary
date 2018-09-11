package com.gystar.master.MainUI.Login;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gystar.master.Config.netApi.AppConfig;
import com.gystar.master.CustomViews.CodeButton;
import com.gystar.master.CustomViews.EdittextListener;
import com.gystar.master.CustomViews.LineEditText;
import com.gystar.master.MainUI.main.InitSet.IninActivity;
import com.gystar.master.MainUI.main.MainTabActivity;
import com.gystar.master.Utils.RegexpUtils;
import com.gystar.master.bean.UserBean;
import com.utils.gyymz.BaseApplicationCompat;
import com.utils.gyymz.listener.UserSensorEventListener;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.utils.T_;
import com.utils.gyymz.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;
import vaxsoft.com.vaxphone.R;


/**
 * Yangshao
 */

public class LoginActivity extends MVPBaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.id_ed_number)
    LineEditText idEdNumber;
    @BindView(R.id.id_ed_code)
    LineEditText idEdCode;
    @BindView(R.id.id_code_btn)
    Button idCodeBtn;
    @BindView(R.id.id_tv_confir)
    TextView idTvConfir;
    private CodeButton codeButton;
    public final int M = 1000;

    @Override
    protected int getLayoutId() {
        return R.layout.gy_activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        BaseApplicationCompat.instance().setStudio(this, new UserSensorEventListener() {
            @Override
            public void userOnSensorEventListener() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        showLoadingView();
        setOnclickListener();
        initKeyBroadListener();
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showRestoreView();
            }
        }, 3000);
    }


    private void initKeyBroadListener() {

    }

    public void setOnclickListener() {
        codeButton = new CodeButton(idCodeBtn, 60 * M, M);
        idEdNumber.addTextChangedListener(new EdittextListener() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0 && !codeButton.isOnTick() && RegexpUtils.isMobile(s.toString())) {
                    idCodeBtn.setEnabled(true);
                } else {
                    idCodeBtn.setEnabled(false);
                }
                if (s.length() != 0 && idEdCode.getText().length() != 0 && RegexpUtils.isMobile(s.toString())) {
                    idTvConfir.setEnabled(true);
                } else {
                    // idTvConfir.setEnabled(false);
                }
            }
        });

        idEdCode.addTextChangedListener(new EdittextListener() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0 && idEdNumber.getText().length() != 0 && RegexpUtils.isMobile(idEdNumber.getText().toString())) {
                    idTvConfir.setEnabled(true);
                } else {
                    // idTvConfir.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected LoginPresenter getDelegateClass() {
        return new LoginPresenter();
    }


    @OnClick({R.id.id_ed_number, R.id.id_ed_code, R.id.id_code_btn, R.id.id_tv_confir})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_ed_number:
                break;
            case R.id.id_ed_code:
                break;
            case R.id.id_code_btn:
                String number = idEdNumber.getText().toString();
                if (!TextUtils.isEmpty(number) && RegexpUtils.isMobile(number)) {
                    // showProgressDialogWithText("获取验证码");
                    T_.showCustomToast("获取验证码成功");
                    mPresenter.getCode(number);
                } else {
                    T_.showCustomToast("请输入正确的手机号码");
                }
                break;
            case R.id.id_tv_confir:
                String number1 = idEdNumber.getText().toString();
                String code = idEdCode.getText().toString();
                if (TextUtils.isEmpty(number1) || !RegexpUtils.isMobile(number1)) {
                    T_.showCustomToast("请输入正确的手机号码");
                } else if (TextUtils.isEmpty(code)) {
                    T_.showCustomToast("请输入验证码");
                } else {
                    T_.showCustomToast("登陆成功");
                    //openActivity(MainTabActivity.class);
                    UIUtils.hideKeyboard(idEdCode);
                    mPresenter.doCodeLogin(number1, code);
                }
                break;
        }
    }

    @Override
    public void getCode() {
        codeButton.start();
        idCodeBtn.setEnabled(false);
        idTvConfir.setEnabled(true);
    }

    @Override
    public void doLogin(UserBean userBean) {
        if (userBean != null && userBean.getID() != null) {
            SpUtils.getInstance().saveString(AppConfig.USER_ID, userBean.getID());
        }
        openActivity(IninActivity.class);
        finish();

//
//        if (userBean.getState().equals("1")) {
//            openActivity(IninActivity.class);
//        } else {
//            openActivity(MainTabActivity.class);
//        }
    }
}
