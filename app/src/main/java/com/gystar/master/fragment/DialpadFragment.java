package com.gystar.master.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatImageView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gystar.master.CallActivity;

import vaxsoft.com.vaxphone.PhoneSIP.Contacts.Contacts;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDialNo;

import static android.app.Activity.RESULT_OK;

public class DialpadFragment extends Fragment {
    public TextView TextViewStatus;
    //public TextView ToolbarTitle;
    public EditText EditText_DialNo;

    public LinearLayout Dialpad_No1, Dialpad_No2, Dialpad_No3, Dialpad_No4, Dialpad_No5, Dialpad_No6,
            Dialpad_No7, Dialpad_No8, Dialpad_No9, Dialpad_Delete, Dialpad_Hold, Dialpad_Contacts,
            Dialpad_Star, Dialpad_No0, Dialpad_Hash, Dialpad_Dial;

    public AppCompatImageView DialIcon;

    public TextView DialBtnText;

    public Contacts m_objContacts = null;

    public static DialpadFragment mDialpadFragment = null;
    private static String m_sLastStatusText = "Account is online";

    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2001;
    private final int CONTACT_PICKER = 2000;


    private LinearLayout CellEnd;
    private static Chronometer tv_time_count;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gy_fragment_call_dialpad, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mDialpadFragment = this;

        LoadViewAll(view);
        InitListeners();
        InitObjects();
        SetIcons();
        Bundle objBundle = getArguments();
        if (objBundle != null) {
            String sDialNo = objBundle.getString("CallerNum");
            if (sDialNo == null) sDialNo = "";

            EditText_DialNo.setText(sDialNo);
        } else {
            String sDialNo = new StoreDialNo().GetDialNo();
            EditText_DialNo.setText(sDialNo);
        }

        //ToolbarTitle.setText(R.string.dialpad);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void LoadViewAll(View view) {
        //  ToolbarTitle = view.findViewById(R.id.label_screen_name);
        TextViewStatus = view.findViewById(R.id.label_dialpad_status);
        // TextViewStatus.setText(DialpadFragment.m_sLastStatusText);
        EditText_DialNo = view.findViewById(R.id.edittext_enter_number);
        Dialpad_No0 = view.findViewById(R.id.dialpad_num0);
        Dialpad_No1 = view.findViewById(R.id.dialpad_num1);
        Dialpad_No2 = view.findViewById(R.id.dialpad_num2);
        Dialpad_No3 = view.findViewById(R.id.dialpad_num3);
        Dialpad_No4 = view.findViewById(R.id.dialpad_num4);
        Dialpad_No5 = view.findViewById(R.id.dialpad_num5);
        Dialpad_No6 = view.findViewById(R.id.dialpad_num6);
        Dialpad_No7 = view.findViewById(R.id.dialpad_num7);
        Dialpad_No8 = view.findViewById(R.id.dialpad_num8);
        Dialpad_No9 = view.findViewById(R.id.dialpad_num9);

        Dialpad_Star = view.findViewById(R.id.dialpad_star);
        Dialpad_Hash = view.findViewById(R.id.dialpad_hash);

        Dialpad_Delete = view.findViewById(R.id.dialpad_delete);
        Dialpad_Hold = view.findViewById(R.id.dialpad_hold);
        Dialpad_Contacts = view.findViewById(R.id.dialpad_contacts);
        Dialpad_Dial = view.findViewById(R.id.dialpad_dial);

        DialBtnText = view.findViewById(R.id.DialText);
        DialIcon = view.findViewById(R.id.dial_icon);


        CellEnd = view.findViewById(R.id.call_end);
        tv_time_count = view.findViewById(R.id.tv_time_count);
        tv_time_count.setFormat("计时:%s");

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
                // onClickDialButton();

                Dialpad_Dial.setEnabled(false);
                OnClickBtnCall();

                String sDialNo = EditText_DialNo.getText().toString().trim();

                CallActivity activity = (CallActivity) getActivity();
                activity.showCallFragment(sDialNo);
                // id_call_layout.setVisibility(View.GONE);
                // lyCall.setVisibility(View.VISIBLE);

            }
        });

        CellEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialpad_Dial.setEnabled(true);
                OnClickBtnCallEnd();
                // id_call_layout.setVisibility(View.VISIBLE);
                // lyCall.setVisibility(View.GONE);
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
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // m_objContacts.ShowContactView(this, CONTACT_PICKER);
    }

//    private void onClickDialButton()
//    {
//        boolean bIsLineBusy = VaxPhoneSIP.m_objVaxVoIP.IsLineConnected();
//
//        if (bIsLineBusy)
//        {
//            if (VaxPhoneSIP.m_objVaxVoIP.DisconnectCall())
//            {
//                DialIconSelected(false);
//                DialBtnText.setText("DIAL");
//            }
//
//            return;
//        }
//
//        String sDialNo = EditText_DialNo.getText().toString().trim();
//        if (sDialNo.length() == 0) return;
//
//        if (VaxPhoneSIP.m_objVaxVoIP.DialCall(sDialNo))
//        {
//            StoreDialNo objStoreDialNo = new StoreDialNo();
//            objStoreDialNo.SaveDialNo(sDialNo);
//
//            DialIconSelected(true);
//            DialBtnText.setText("END");
//        }
//
//        getActivity().onBackPressed();
//    }


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
                String sContactNum = m_objContacts.GetPickedContactNum(getActivity(), data);

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
        DialpadFragment.m_sLastStatusText = sMsg;

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
