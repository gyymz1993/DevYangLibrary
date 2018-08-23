package com.gystar.master;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import vaxsoft.com.vaxphone.MainTab.CallTab.DialpadFragment;
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.PhoneSIP.Contacts.Contacts;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDialNo;

public class CallActivity extends AppCompatActivity {
    private static Chronometer tv_time_count;
    public TextView TextViewStatus;
    public TextView ToolbarTitle;
    public EditText EditText_DialNo;
    public LinearLayout Dialpad_No1, Dialpad_No2, Dialpad_No3, Dialpad_No4, Dialpad_No5, Dialpad_No6,
            Dialpad_No7, Dialpad_No8, Dialpad_No9, Dialpad_Delete, Dialpad_Hold, Dialpad_Contacts,
            Dialpad_Star, Dialpad_No0, Dialpad_Hash, Dialpad_Dial, CellEnd;
    public AppCompatImageView DialIcon;
    public TextView DialBtnText;
    public Contacts m_objContacts = null;
    public static DialpadFragment mDialpadFragment = null;
    private static String m_sLastStatusText = "Account is online";
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2001;
    private final int CONTACT_PICKER = 2000;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_dialpad);
        LoadViewAll();
        InitListeners();
        InitObjects();
        SetIcons();
        BtnLogIn();
        String sDialNo = new StoreDialNo().GetDialNo();
        EditText_DialNo.setText(sDialNo);
        ToolbarTitle.setText(R.string.dialpad);
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void LoadViewAll() {
        ToolbarTitle = findViewById(R.id.label_screen_name);
        TextViewStatus = findViewById(R.id.label_dialpad_status);
        TextViewStatus.setText(m_sLastStatusText);
        EditText_DialNo = findViewById(R.id.edittext_enter_number);
        Dialpad_No0 = findViewById(R.id.dialpad_num0);
        Dialpad_No1 = findViewById(R.id.dialpad_num1);
        Dialpad_No2 = findViewById(R.id.dialpad_num2);
        Dialpad_No3 = findViewById(R.id.dialpad_num3);
        Dialpad_No4 = findViewById(R.id.dialpad_num4);
        Dialpad_No5 = findViewById(R.id.dialpad_num5);
        Dialpad_No6 = findViewById(R.id.dialpad_num6);
        Dialpad_No7 = findViewById(R.id.dialpad_num7);
        Dialpad_No8 = findViewById(R.id.dialpad_num8);
        Dialpad_No9 = findViewById(R.id.dialpad_num9);
        Dialpad_Star = findViewById(R.id.dialpad_star);
        Dialpad_Hash = findViewById(R.id.dialpad_hash);
        Dialpad_Delete = findViewById(R.id.dialpad_delete);
        Dialpad_Hold = findViewById(R.id.dialpad_hold);
        Dialpad_Contacts = findViewById(R.id.dialpad_contacts);
        Dialpad_Dial = findViewById(R.id.dialpad_dial);
        DialBtnText = findViewById(R.id.DialText);
        DialIcon = findViewById(R.id.dial_icon);
        CellEnd = findViewById(R.id.call_end);
        tv_time_count = findViewById(R.id.tv_time_count);
        tv_time_count.setFormat("计时:%s");
    }

    public static void onStartTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - tv_time_count.getBase()) / 1000 / 60);
        tv_time_count.setFormat("0" + String.valueOf(hour) + ":%s");
        tv_time_count.start();
        //tv_time_count.start();
        // CharSequence text=tv_time_count.getText();
        // char charAt=text.charAt(text.length()-1);
    }

    public static void onStopTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setBase(SystemClock.elapsedRealtime());//计时器清零
        tv_time_count.stop();
    }

    public static void onResetTimeCount() {
        if (tv_time_count == null) return;
        tv_time_count.setBase(SystemClock.elapsedRealtime());
    }

    private void InitListeners() {
        Dialpad_No0.setOnClickListener(new OnClickListenerEx("0"));
        Dialpad_No1.setOnClickListener(new OnClickListenerEx("1"));
        Dialpad_No2.setOnClickListener(new OnClickListenerEx("2"));
        Dialpad_No3.setOnClickListener(new OnClickListenerEx("3"));
        Dialpad_No4.setOnClickListener(new OnClickListenerEx("4"));
        Dialpad_No5.setOnClickListener(new OnClickListenerEx("5"));
        Dialpad_No6.setOnClickListener(new OnClickListenerEx("6"));
        Dialpad_No7.setOnClickListener(new OnClickListenerEx("7"));
        Dialpad_No8.setOnClickListener(new OnClickListenerEx("8"));
        Dialpad_No9.setOnClickListener(new OnClickListenerEx("9"));
        Dialpad_Star.setOnClickListener(new OnClickListenerEx("*"));
        Dialpad_Hash.setOnClickListener(new OnClickListenerEx("#"));

        Dialpad_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDeleteButton();
            }
        });
        Dialpad_Delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return onLongClickDeleteButton();
            }
        });
        Dialpad_Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHoldButton();
            }
        });
        Dialpad_Contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickContactButton();
            }
        });
        Dialpad_Dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialpad_Dial.setEnabled(false);
                onClickDialButton();
            }
        });
        CellEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialpad_Dial.setEnabled(true);
                OnClickBtnCallEnd();
            }
        });
    }

    private void InitObjects() {
        m_objContacts = new Contacts();
    }


    private void SetIcons() {
        if (VaxPhoneSIP.m_objVaxVoIP.IsLineConnected()) {
            DialIconSelected(true);
            DialBtnText.setText("END");
        }
        Boolean bIsHold = VaxPhoneSIP.m_objVaxVoIP.IsLineHold();
        Dialpad_Hold.setSelected(bIsHold);
    }

    public void setDialState() {
        if (VaxPhoneSIP.m_objVaxVoIP.DisconnectCall()) {
            DialIconSelected(false);
            DialBtnText.setText("DIAL");
        } else {
            DialIconSelected(true);
            DialBtnText.setText("END");
        }
        if (VaxPhoneSIP.m_objVaxVoIP.IsLineConnected()) {
            DialIconSelected(true);
            DialBtnText.setText("END");
        } else {
            DialIconSelected(false);
            DialBtnText.setText("DIAL");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private void onClickDeleteButton() {
        String sNum = EditText_DialNo.getText().toString().trim();
        if (sNum.length() <= 1) {
            EditText_DialNo.getText().clear();
            return;
        }
        sNum = sNum.substring(0, sNum.length() - 1);
        EditText_DialNo.setText(sNum);
    }

    private boolean onLongClickDeleteButton() {
        EditText_DialNo.getText().clear();
        return true;
    }

    private void onClickHoldButton() {
        if (!VaxPhoneSIP.m_objVaxVoIP.IsLineConnected())
            return;
        boolean bHoldLine = VaxPhoneSIP.m_objVaxVoIP.IsLineHold();
        if (!bHoldLine) {
            VaxPhoneSIP.m_objVaxVoIP.HoldLine();
            Dialpad_Hold.setSelected(true);
            return;
        }
        VaxPhoneSIP.m_objVaxVoIP.UnHoldLine();
        Dialpad_Hold.setSelected(false);

    }

    private void onClickContactButton() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // m_objContacts.ShowContactView(this, CONTACT_PICKER);
    }

    private void onClickDialButton() {
        boolean bIsLineBusy = VaxPhoneSIP.m_objVaxVoIP.IsLineConnected();
        OnClickBtnCall();
//        if (bIsLineBusy) {
//
//        } else {
//
//        }
        //onBackPressed();
    }

    /**
     * 挂断
     */
    private void OnClickBtnCallEnd() {
        //挂断
        VaxPhoneSIP.m_objVaxVoIP.DisconnectCall();
        DialIconSelected(false);
        DialBtnText.setText("DIAL");
    }

    /**
     * 拨号
     */
    private void OnClickBtnCall() {

        String sDialNo = EditText_DialNo.getText().toString().trim();
        if (sDialNo.length() == 0) return;
        if (VaxPhoneSIP.m_objVaxVoIP.DialCall(sDialNo)) {
            StoreDialNo objStoreDialNo = new StoreDialNo();
            objStoreDialNo.SaveDialNo(sDialNo);
            DialIconSelected(true);
            DialBtnText.setText("END");
        }
    }


    public void BtnLogIn() {
        if (!UpdateLogInInfo())
            return;

        if (VaxPhoneSIP.m_objVaxVoIP.IsOnline()) {
            //onClickDialButton();
            VaxPhoneSIP.m_objVaxVoIP.UnInitialize();
            return;
        }
        if (Initialize()) {
            return;
        }
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
        if (!VaxPhoneSIP.m_objVaxVoIP.Initialize(sDisplayName, sUsername, sAuthLogin, sAuthPassword, sDomainRealm, sServerIP, nServerPort, bRegistrationSIP, this))
            return false;
        if (!bRegistrationSIP) {
            onClickDialButton();
        }
        return false;
    }


    private boolean UpdateLogInInfo() {
        String sAuthLogin = "106001";
        String sServerAddr = "120.79.114.71:5060";
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
        String sAuthPwd = "bbw@2018";
        VaxPhoneSIP.m_objVaxVoIP.SetLoginInfo(Username.toString(), DisplayName.toString(), sAuthLogin, sAuthPwd, DomainRealm.toString(), sServerIP, sServerPort, bRegistrationSIP);
        return true;
    }


    public void OnDialpadClosed() {
    }

    @Override
    public void onDestroy() {
        mDialpadFragment = null;
        OnDialpadClosed();
        super.onDestroy();
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;

        if (requestCode == CONTACT_PICKER) {
            if (resultCode == RESULT_OK) {
                String sContactNum = m_objContacts.GetPickedContactNum(this, data);
                EditText_DialNo.getText().clear();
                EditText_DialNo.setText(sContactNum);
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private class OnClickListenerEx implements View.OnClickListener {
        String m_sDigit;

        OnClickListenerEx(String sTextNo) {
            m_sDigit = sTextNo;
        }

        public void onClick(View v) {
            EditText_DialNo.append(m_sDigit);
            VaxPhoneSIP.m_objVaxVoIP.PlayDTMF(m_sDigit);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static void PostStatusText(String sMsg) {
        m_sLastStatusText = sMsg;
        if (mDialpadFragment != null)
            mDialpadFragment.OnStatusText(sMsg);
    }

    public void OnStatusText(String sMsg) {
        TextViewStatus.setText(sMsg);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    private void DialIconSelected(boolean bSelected) {
        DialIcon.setSelected(bSelected);
        Dialpad_Dial.setSelected(bSelected);
        DialBtnText.setSelected(bSelected);
    }

}
