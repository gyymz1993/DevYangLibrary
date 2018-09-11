package vaxsoft.com.vaxphone.MainTab.CallTab;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class CallIconsFragment extends DialpadFragment implements View.OnClickListener
{
    private LinearLayout BtnSwitchCamera, BtnSpkPhone, BtnDialpad, BtnEndCall, BtnHoldCall;

    private Activity mActivity = null;
    private View SideIconsLayout = null;
    private AppCompatImageView BtnVideo, BtnMic, BtnMuteSpk, BtnVoiceChanger;

    private Timer m_objHideIconTick = null;
    private final int HIDE_ICON_DELAY = 8500;  // milliseconds

    CallIconsFragment(Activity activity, View view)
    {
        mActivity = activity;

        LoadViewAll(view);
        ActivateClickListeners();

        UpdateBtnViewAll(true);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void LoadViewAll(View view)
    {
        BtnVideo          = view.findViewById(R.id.videocam);
        BtnMic            = view.findViewById(R.id.icon_mic);
        BtnVoiceChanger   = view.findViewById(R.id.icon_voice_changer);
        BtnMuteSpk        = view.findViewById(R.id.icon_mute_spk);
        BtnSpkPhone       = view.findViewById(R.id.layout_spk_phone);
        BtnDialpad        = view.findViewById(R.id.layout_dialpad);
        BtnSwitchCamera   = view.findViewById(R.id.layout_switch_camera);
        BtnEndCall        = view.findViewById(R.id.layout_end_call);
        BtnHoldCall       = view.findViewById(R.id.layout_hold_call);

        SideIconsLayout   = view.findViewById(R.id.IconsLayout);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void PostHideBtnAll()
    {
        MainTabActivity objActivity = (MainTabActivity) mActivity;

        if (objActivity == null)
            return;

        objActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                UpdateBtnViewAll(false);
            }
        });
    }

    private void StartHideBtnAllTick()
    {
        if(m_objHideIconTick != null)
            m_objHideIconTick.cancel();

        m_objHideIconTick = new Timer();
        m_objHideIconTick.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                PostHideBtnAll();
            }
        }, HIDE_ICON_DELAY);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void ActivateClickListeners()
    {
        BtnSwitchCamera.setOnClickListener(this);
        BtnVideo.setOnClickListener(this);

        BtnMuteSpk.setOnClickListener(this);
        BtnSpkPhone.setOnClickListener(this);
        BtnMic.setOnClickListener(this);

        BtnVoiceChanger.setOnClickListener(this);
        BtnDialpad.setOnClickListener(this);

        BtnEndCall.setOnClickListener(this);
        BtnHoldCall.setOnClickListener(this);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void DisplayBtnAll()
    {
        UpdateBtnViewAll(true);
    }

    private void UpdateBtnViewAll(Boolean bShow)
    {
        StartHideBtnAllTick();

        UpdateOtherBtnView(bShow);
        UpdateEndCallBtnView(bShow);

        UpdateHoldCallBtnView(bShow);
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    private void UpdateOtherBtnView(Boolean bShow)
    {
        if(bShow)
        {
            BtnVideo.setSelected(VaxPhoneSIP.m_objVaxVoIP.IsVideoEnabled());
            BtnVoiceChanger.setSelected(VaxPhoneSIP.m_objVaxVoIP.IsVoiceChangerEnabled());

            BtnMic.setSelected(VaxPhoneSIP.m_objVaxVoIP.IsMuteMic());
            BtnMuteSpk.setSelected(VaxPhoneSIP.m_objVaxVoIP.IsMuteSpk());
            BtnSpkPhone.setSelected(VaxPhoneSIP.m_objVaxVoIP.IsSpeakerPhone());
        }

        int nVisibility;

        TranslateAnimation animate = null;

        if(bShow)
        {
            if(SideIconsLayout.isShown())
                return;

            nVisibility = View.VISIBLE;

            animate = new TranslateAnimation(SideIconsLayout.getWidth(), 0, 0, 0);
            animate.setFillAfter(true);
        }
        else
        {
            if(!SideIconsLayout.isShown())
                return;

            nVisibility = View.GONE;

            animate = new TranslateAnimation(0, SideIconsLayout.getWidth(), 0, 0);
            animate.setFillAfter(false);
        }

        animate.setDuration(250);
        SideIconsLayout.startAnimation(animate);
        SideIconsLayout.setVisibility(nVisibility);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void UpdateEndCallBtnView(Boolean bShow)
    {
        if (!VaxPhoneSIP.m_objVaxVoIP.IsLineBusy())
        {
            bShow = false;
        }

        int nVisibility;
        TranslateAnimation animate = null;

        if(bShow)
        {
            if(BtnEndCall.isShown())
                return;

            nVisibility = View.VISIBLE;
            animate = new TranslateAnimation(0, 0, BtnEndCall.getWidth(), 0);
        }
        else
        {
            if(!BtnEndCall.isShown())
                return;

            nVisibility = View.GONE;
            animate = new TranslateAnimation(0, 0, 0, BtnEndCall.getWidth());
        }

        animate.setDuration(250);
        animate.setFillAfter(false);

        BtnEndCall.startAnimation(animate);
        BtnEndCall.setVisibility(nVisibility);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private void UpdateHoldCallBtnView(Boolean bShow)
    {
        if (!VaxPhoneSIP.m_objVaxVoIP.IsLineBusy())
        {
            bShow = false;
        }

        ////////////////////////////////////////////////////////////

        boolean bIsLineHold = VaxPhoneSIP.m_objVaxVoIP.IsLineHold();
        BtnHoldCall.setSelected(bIsLineHold);

        ////////////////////////////////////////////////////////////

        int nVisibility;

        if(bShow)
        {
            if(BtnHoldCall.isShown())
                return;

            nVisibility = View.VISIBLE;
        }
        else
        {
            if(!BtnHoldCall.isShown())
                return;

            nVisibility = View.INVISIBLE;
        }

        BtnHoldCall.setVisibility(nVisibility);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View view)
    {
        int nViewId = view.getId();

        switch (nViewId)
        {
            case R.id.layout_switch_camera:
                OnClickBtnSwitchCamera();
                break;

            case R.id.videocam:
                OnClickBtnVideo();
                break;

            case R.id.icon_mic:
                OnClickBtnMic();
                break;

            case R.id.icon_voice_changer:
                OnClickBtnVoiceChanger();
                break;

            case R.id.layout_spk_phone:
                OnClickSpkPhone();
                break;

            case R.id.icon_mute_spk:
                OnClickBtnMuteSpk();
                break;

            case R.id.layout_dialpad:
                OnClickBtnDialpad();
                break;

            case R.id.layout_end_call:
                OnClickBtnEndCall();
                break;

            case R.id.layout_hold_call:
                OnClickHoldCall();
                break;
        }

        UpdateBtnViewAll(true);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void OnClickBtnSwitchCamera()
    {
        VaxPhoneSIP.m_objVaxVoIP.SwitchVideoDevice();
    }

    private void OnClickBtnVideo()
    {
        boolean bVideoEnable = VaxPhoneSIP.m_objVaxVoIP.IsVideoEnabled();
        VaxPhoneSIP.m_objVaxVoIP.EnableVideo(!bVideoEnable);
    }

    private void OnClickBtnMic()
    {
        boolean bMuteMic = VaxPhoneSIP.m_objVaxVoIP.IsMuteMic();
        VaxPhoneSIP.m_objVaxVoIP.MuteMic(!bMuteMic);
    }

    private void OnClickBtnVoiceChanger()
    {
        boolean bIsEnabled = VaxPhoneSIP.m_objVaxVoIP.IsVoiceChangerEnabled();
        VaxPhoneSIP.m_objVaxVoIP.VoiceChanger(!bIsEnabled);

        BtnVoiceChanger.setSelected(!bIsEnabled);
    }

    private void OnClickBtnMuteSpk()
    {
        boolean bMuteSpk = VaxPhoneSIP.m_objVaxVoIP.IsMuteSpk();
        VaxPhoneSIP.m_objVaxVoIP.MuteSpk(!bMuteSpk);
    }

    private void OnClickSpkPhone()
    {
        boolean bSpkPhone = VaxPhoneSIP.m_objVaxVoIP.IsSpeakerPhone();
        VaxPhoneSIP.m_objVaxVoIP.SpeakerPhone(!bSpkPhone);
    }

    private void OnClickBtnDialpad()
    {
        FragmentManager objFragManager = ((MainTabActivity) mActivity).getSupportFragmentManager();
        FragmentTransaction objFragTransaction = objFragManager.beginTransaction();

        objFragTransaction.add(android.R.id.content, (DialpadFragment) this, "Dialpad");
        objFragTransaction.addToBackStack("Dialpad");
        objFragTransaction.commit();
    }

    public void OnDialpadClosed()
    {
        UpdateBtnViewAll(true);
    }

    private void OnClickBtnEndCall()
    {
        VaxPhoneSIP.m_objVaxVoIP.DisconnectCall();
    }

    private void OnClickHoldCall()
    {
        boolean bHoldLine = VaxPhoneSIP.m_objVaxVoIP.IsLineHold();

        if (bHoldLine)
        {
            VaxPhoneSIP.m_objVaxVoIP.UnHoldLine();
            return;
        }

        VaxPhoneSIP.m_objVaxVoIP.HoldLine();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
