package vaxsoft.com.vaxphone.IncomingCall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class IncomingCallActivity extends AppCompatActivity implements View.OnClickListener
{
    public static IncomingCallActivity mIncomingCallActivity = null;

    private AppCompatButton BtnAcceptCall, BtnRejectCall, BtnMuteRing;
    private TextView CallerName, CallerId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        InitViews();
        InitClickListeners();

        Intent objIntent = getIntent();

        if (objIntent != null)
        {
            String sCallerName = objIntent.getStringExtra("CallerName");
            String sCallerId = objIntent.getStringExtra("CallerId");

            CallerName.setText(sCallerName);
            CallerId.setText(sCallerId);
        }
    }

    @Override
    protected void onResume()
    {
        mIncomingCallActivity = this;
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        mIncomingCallActivity = null;
        super.onDestroy();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnIncomingCallEnded(String sCallId)
    {
        this.finish();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private void InitViews()
    {
        BtnAcceptCall = findViewById(R.id.accept_call);
        BtnRejectCall = findViewById(R.id.reject_call);
        BtnMuteRing = findViewById(R.id.mute_ring);

        CallerName = findViewById(R.id.caller_name);
        CallerId = findViewById(R.id.caller_id);
    }

    private void InitClickListeners()
    {
        BtnAcceptCall.setOnClickListener(this);
        BtnRejectCall.setOnClickListener(this);
        BtnMuteRing.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int nId = view.getId();

        switch (nId)
        {
            case R.id.accept_call:
                OnClickAcceptCall();
                break;

            case R.id.reject_call:
                OnClickRejectCall();
                break;

            case R.id.mute_ring:
                OnClickMuteRing();
                break;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    private void OnClickAcceptCall()
    {
        VaxPhoneSIP.m_objVaxVoIP.AcceptCall();

        startActivity(new Intent(this, MainTabActivity.class));

        finish();
    }

    private void OnClickRejectCall()
    {
        VaxPhoneSIP.m_objVaxVoIP.RejectCall();
        finish();
    }

    private void OnClickMuteRing()
    {
        VaxPhoneSIP.m_objVaxVoIP.MuteRingTone();
    }
}
