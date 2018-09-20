package com.gystar.master.MainUI.Call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gystar.master.bean.MyClientPerson;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.MVPBaseActivity;
import com.utils.gyymz.wiget.NavigationBarView;

import vaxsoft.com.vaxphone.PhoneSIP.Contacts.Contacts;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDialNo;

public class DialpadActivity extends MVPBaseActivity {

    public TextView TextViewStatus;
    public EditText EditText_DialNo;
    public LinearLayout Dialpad_No1, Dialpad_No2, Dialpad_No3, Dialpad_No4, Dialpad_No5, Dialpad_No6,
            Dialpad_No7, Dialpad_No8, Dialpad_No9, Dialpad_Delete, Dialpad_Hold, Dialpad_Contacts,
            Dialpad_Star, Dialpad_No0, Dialpad_Hash, Dialpad_Dial;
    public AppCompatImageView DialIcon;
    public TextView DialBtnText;
    public Contacts m_objContacts = null;
    private static String m_sLastStatusText = "Account is online";
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2001;
    private final int CONTACT_PICKER = 2000;


    @Override
    protected BasePresenter getDelegateClass() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gy_fragment_call_dialpad;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        showNavigationBarCanBack().setText(NavigationBarView.NavigationViewType.CONTEXT_TV, "自由拨号");
        LoadViewAll();
        InitListeners();
        InitObjects();
        SetIcons();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetWorkErrorView() {

    }


    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void LoadViewAll() {
        TextViewStatus = findViewById(R.id.label_dialpad_status);
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
                String sDialNo = EditText_DialNo.getText().toString().trim();
                MyClientPerson.DataBean.PageBean topicBean = new MyClientPerson.DataBean.PageBean();
                topicBean.setName("光银网络");
                topicBean.setPhone(sDialNo);
                Intent launchIntent = GyCallActivity.getLaunchIntent(DialpadActivity.this, topicBean);
                startActivity(launchIntent);
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
        //getActivity().onBackPressed();
    }


    public void OnDialpadClosed() {
    }

    @Override
    public void onDestroy() {
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
