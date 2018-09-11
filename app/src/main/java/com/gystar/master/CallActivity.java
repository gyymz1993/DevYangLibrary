package com.gystar.master;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gystar.master.MainUI.fragment.CallingFragment;
import com.gystar.master.MainUI.fragment.DialpadFragment;

import java.util.concurrent.atomic.AtomicBoolean;

import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class CallActivity extends AppCompatActivity {
    private Chronometer tv_time_count;
    private FragmentTransaction transaction;
    private DialpadFragment rightFragment;
    private CallingFragment callingFragment;
    private static CallActivity instance;


    String sAuthLogin = "106001";
    String sAuthPwd = "bbw@2018";
    String sServerAddr = "120.79.114.71:5060";

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.gy_activity_call_dialpad);
        tv_time_count = findViewById(R.id.tv_time_count);
        if (!VaxPhoneSIP.IsOnline()) {
            BtnLogIn();
        }
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getFragmentManager();
        //beginTransaction()
        //Start a series of edit operations on the Fragments associated with this FragmentManager.
        transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        rightFragment = new DialpadFragment();
        transaction.add(R.id.id_call_layout, rightFragment);
        //步骤三：调用commit()方法使得FragmentTransaction实例的改变生效
        //transaction.commit();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        callingFragment = new CallingFragment();
        transaction.add(R.id.ly_calling, callingFragment);
        //步骤三：调用commit()方法使得FragmentTransaction实例的改变生效
        transaction.commit();

    }

    public void showCallFragment(String number) {
        CallingFragment.mCallFragment.setPhoneNumber(number);
        UpdateOtherBtnView();
    }

    public void showDialpadFragment() {
        UpdateOtherBtnView();
    }


    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    private void UpdateOtherBtnView() {
        RelativeLayout linearLayoutCallD = findViewById(R.id.id_call_layout);
        LinearLayout linearLayoutCall = findViewById(R.id.ly_calling);
        TranslateAnimation animate = null;
        int nVisibility;
        if (linearLayoutCall.getVisibility() == View.VISIBLE) {
            nVisibility = View.GONE;
            animate = new TranslateAnimation(0, linearLayoutCall.getWidth(), 0, 0);
            animate.setFillAfter(false);
            linearLayoutCallD.setVisibility(View.VISIBLE);
        } else {
            nVisibility = View.VISIBLE;
            animate = new TranslateAnimation(linearLayoutCall.getWidth(), 0, 0, 0);
            animate.setFillAfter(true);
            linearLayoutCallD.setVisibility(View.GONE);
        }
        animate.setDuration(100);
        linearLayoutCall.startAnimation(animate);
        linearLayoutCall.setVisibility(nVisibility);
    }


    public static void onStartTimeCount() {
        if (CallingFragment.mCallFragment != null) {
            CallingFragment.mCallFragment.onStartTimeCount();
        }
    }

    public static void onStopTimeCount() {
        if (CallingFragment.mCallFragment != null) {
            CallingFragment.mCallFragment.onStopTimeCount();
        }
    }


    private void onClickContactButton() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // m_objContacts.ShowContactView(this, CONTACT_PICKER);
    }

    private void onClickDialButton() {
        boolean bIsLineBusy = VaxPhoneSIP.m_objVaxVoIP.IsLineConnected();
    }

    /**
     * 挂断
     */
    private void OnClickBtnCallEnd() {
        //挂断
        VaxPhoneSIP.m_objVaxVoIP.DisconnectCall();
    }


    public void BtnLogIn() {
        if (!UpdateLogInInfo())
            return;
        if (VaxPhoneSIP.m_objVaxVoIP.IsOnline()) {
            VaxPhoneSIP.m_objVaxVoIP.UnInitialize();
            return;
        }
        if (Initialize()) {
            return;
        }
    }

    @Override
    public void onBackPressed() {

    }

    private boolean Initialize() {
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder AuthPassword = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();
        StringBuilder ServerIP = new StringBuilder();
        StringBuilder ServerPort = new StringBuilder();
        AtomicBoolean RegistrationSIP = new AtomicBoolean();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, AuthPassword, DomainRealm, ServerIP, ServerPort, RegistrationSIP);
        String sUsername = Username.toString();
        String sDisplayName = DisplayName.toString();
        String sAuthLogin = AuthLogin.toString();
        String sAuthPassword = AuthPassword.toString();
        String sDomainRealm = DomainRealm.toString();
        String sServerIP = ServerIP.toString();
        int nServerPort = Integer.parseInt(ServerPort.toString());
        boolean bRegistrationSIP = RegistrationSIP.get();

        if (!VaxPhoneSIP.m_objVaxVoIP.Initialize(sDisplayName, sUsername, sAuthLogin, sAuthPassword, sDomainRealm, sServerIP, nServerPort, bRegistrationSIP)) {
            return false;
        }
        if (!bRegistrationSIP) {
            onClickDialButton();
        }
        return false;
    }


    private boolean UpdateLogInInfo() {
        if (sAuthLogin.length() <= 0) {
            DialogUtil.ShowDialog(this, "Please enter Auth Login");
            return false;
        }
        if (sServerAddr.length() <= 0) {
            DialogUtil.ShowDialog(this, "Please enter SIP Server address");
            return false;
        }
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, null, DomainRealm, null, null, null);
        String sServerIP;
        String sServerPort;
        if (sServerAddr.contains(":")) {
            String aServerAddr[] = sServerAddr.split(":");
            sServerIP = aServerAddr[0];
            sServerPort = aServerAddr[aServerAddr.length - 1];
        } else {
            sServerIP = sServerAddr;
            sServerPort = "5060";
        }
        boolean bRegistrationSIP = true;
        if (!AuthLogin.toString().equals(sAuthLogin)) {
            Username.setLength(0);
            Username.append(sAuthLogin);
            DisplayName.setLength(0);
            DisplayName.append(sAuthLogin);
            DomainRealm.setLength(0);
            DomainRealm.append(sServerIP);
        }
        VaxPhoneSIP.m_objVaxVoIP.SetLoginInfo(Username.toString(), DisplayName.toString(), sAuthLogin, sAuthPwd, DomainRealm.toString(), sServerIP, sServerPort, bRegistrationSIP);
        return true;
    }


    @Override
    public void onDestroy() {
        OnClickBtnCallEnd();
        super.onDestroy();
    }

}
