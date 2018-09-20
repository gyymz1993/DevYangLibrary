package com.gystar.master.MainUI.Login;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import com.gystar.master.MoudleContrl.VaxPhoneSIPLogin;
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
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;


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
        onInitVaxsoft();
        setOnclickListener();
        initKeyBroadListener();
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
                    showProgressDialogWithText("正在登陆");
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
      //  dismissProgressDialog();
        UserBean.DataBean data = userBean.getData();
        if (data != null) {
            String number1 = idEdNumber.getText().toString();
            SpUtils.getInstance().saveString(AppConfig.USER_ID, data.getID() + "");
            SpUtils.getInstance().saveString(AppConfig.USER_PHONE,number1);
            updateDrawerUI();
            if (data.getState() == 1) {
                openActivity(IninActivity.class);
            } else {
                openActivity(MainTabActivity.class);
            }
            finish();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void updateDrawerUI() {
        StringBuilder sAuthLogin = new StringBuilder();
        StringBuilder sDoaminRealm = new StringBuilder();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(null, null, sAuthLogin, null, sDoaminRealm, null, null, null);
    }


    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showNetWorkErrorView() {

    }



    private void onInitVaxsoft() {
        VaxPhoneSIP.m_objVaxVoIP.NetworkReachability(true);
        VaxPhoneSIP.m_objVaxVoIP.OpenVideoDevice();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
                        , Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS
                }, 3000);
                return;
            }
        }
        VaxPhoneSIPLogin.login(getApplicationContext());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 3000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                VaxPhoneSIPLogin.login(getApplicationContext());
            } else {
                DialogUtil.ShowDialog(this, "Please allow to use camera for video call.");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
