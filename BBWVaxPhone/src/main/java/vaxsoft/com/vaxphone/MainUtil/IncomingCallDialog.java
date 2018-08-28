package vaxsoft.com.vaxphone.MainUtil;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class IncomingCallDialog implements View.OnClickListener
{
    private Dialog m_objDialog = null;

    private String m_sCallerName;
    private String m_sCallerId;

    private AppCompatButton BtnAcceptCall, BtnRejectCall, BtnMuteRing, BtnCancel;

    public IncomingCallDialog(Context context, String sCallerName, String sCallerId)
    {
        m_sCallerName = sCallerName;
        m_sCallerId = sCallerId;

        CreateDialog(context);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    private void CreateDialog(Context context)
    {
        m_objDialog = new Dialog(context);

        m_objDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        m_objDialog.setContentView(R.layout.layout_incoming_call);
        m_objDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView CallerName = m_objDialog.findViewById(R.id.caller_name);
        CallerName.setText(m_sCallerName);

        TextView CallerId = m_objDialog.findViewById(R.id.caller_id);
        CallerId.setText(m_sCallerId);

        InitDialogButtons();
        InitDialogClickListners();

        m_objDialog.create();
    }

    private void InitDialogButtons()
    {
        BtnAcceptCall = m_objDialog.findViewById(R.id.accept_call);
        BtnRejectCall = m_objDialog.findViewById(R.id.reject_call);
        BtnMuteRing = m_objDialog.findViewById(R.id.mute_ring);
        BtnCancel = m_objDialog.findViewById(R.id.cancel);
    }

    private void InitDialogClickListners()
    {
        BtnAcceptCall.setOnClickListener(this);
        BtnRejectCall.setOnClickListener(this);
        BtnMuteRing.setOnClickListener(this);
        BtnCancel.setOnClickListener(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void ShowDialog()
    {
        m_objDialog.show();
    }

    public void HideDialog()
    {
        m_objDialog.cancel();
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

            case R.id.cancel:
                OnClickCancel();
            break;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    private void OnClickAcceptCall()
    {
        VaxPhoneSIP.m_objVaxVoIP.AcceptCall();
        m_objDialog.cancel();
    }

    private void OnClickRejectCall()
    {
        VaxPhoneSIP.m_objVaxVoIP.RejectCall();
        m_objDialog.cancel();
    }

    private void OnClickMuteRing()
    {
        VaxPhoneSIP.m_objVaxVoIP.MuteRingTone();
    }

    private void OnClickCancel()
    {
        m_objDialog.cancel();
    }
}
