package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreGeneralSettings;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener
{
    Toolbar Toolbar;

    CheckBox MultiTaskMode, DiagnosticLog;

    LinearLayout EncryptionTunnelRow, VideoBitrateRow, VideoQualityRow, VideoCodecsRow, AudioCodecsRow, SpkMicRow, RingTonesRow, VoiceChangerRow, NetworkRow, DigitDTMFRow;

    AppCompatButton BtnResetAllSettings;

    public SettingsFragment()
    {
        // Required empty public constructor
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        InitViews(view);
        SetActionBar();
        InitClickListeners();

        StoreGeneralSettings objSettings = new StoreGeneralSettings();

        boolean bMultiTaskMode = objSettings.GetMultiTaskMode();
        MultiTaskMode.setChecked(bMultiTaskMode);

        boolean bDiagnosticLog = objSettings.GetDiagnosticLog();
        DiagnosticLog.setChecked(bDiagnosticLog);
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    private void InitViews(View view)
    {
        Toolbar = view.findViewById(R.id.actionbar);

        EncryptionTunnelRow = view.findViewById(R.id.EncryptionTunnelRow);
        VideoBitrateRow = view.findViewById(R.id.VideoBitrateRow);
        VideoQualityRow = view.findViewById(R.id.VideoQualityRow);
        VideoCodecsRow = view.findViewById(R.id.VideoCodecsRow);
        AudioCodecsRow = view.findViewById(R.id.AudioCodecsRow);
        SpkMicRow = view.findViewById(R.id.SpkMicRow);
        RingTonesRow = view.findViewById(R.id.RingTonesRow);
        VoiceChangerRow = view.findViewById(R.id.VoiceChangerRow);
        NetworkRow = view.findViewById(R.id.NetworkRow);
        DigitDTMFRow = view.findViewById(R.id.DigitDTMFRow);
        BtnResetAllSettings = view.findViewById(R.id.ResetAllSettingsRow);

        MultiTaskMode = view.findViewById(R.id.checkbox_multi_task_mode);
        DiagnosticLog = view.findViewById(R.id.checkbox_diagnostic_log);
    }

    private void InitClickListeners()
    {
        EncryptionTunnelRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickEncryptionTunnelRow();
            }
        });

        VideoBitrateRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickVideoBitrateRow();
            }
        });

        VideoQualityRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickVideoQualityRow();
            }
        });

        VideoCodecsRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickVideoCodecsRow();
            }
        });

        AudioCodecsRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickAudioCodecsRow();
            }
        });

        SpkMicRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickSpkMicRow();
            }
        });

        RingTonesRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickRingTonesRow();
            }
        });

        VoiceChangerRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickVoiceChangerRow();
            }
        });

        NetworkRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickNetworkRow();
            }
        });

        DigitDTMFRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickDigitDTMFRow();
            }
        });

        BtnResetAllSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickResetAllSettings();
            }
        });

        MultiTaskMode.setOnCheckedChangeListener(this);
        DiagnosticLog.setOnCheckedChangeListener(this);
    }

    private void SetActionBar()
    {
        ((MainTabActivity) getActivity()).setSupportActionBar(Toolbar);
        ActionBar actionBar = ((MainTabActivity) getActivity()).getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setTitle("Settings");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public void OnClickEncryptionTunnelRow()
    {
        TunnelFragment  objTunnelFragment = new TunnelFragment();

        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragTransaction = objFragManager.beginTransaction();

        objFragTransaction.replace(android.R.id.content, objTunnelFragment ,"Encryption Tunnel");
        objFragTransaction.addToBackStack("Encryption Tunnel");
        objFragTransaction.commit();
    }

    public void OnClickVideoBitrateRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        VideoBitrateDialog objVideoBitrateDialog = new VideoBitrateDialog();
        objVideoBitrateDialog.show(objFragmentTransaction, "Video Bitrate Dialog");
    }

    public void OnClickVideoQualityRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        VideoQualityDialog objVideoQualityDialog = new VideoQualityDialog();
        objVideoQualityDialog.show(objFragmentTransaction, "Video Quality Dialog");
    }

    public void OnClickVideoCodecsRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        VideoCodecsDialog objVideoCodecsDialog = new VideoCodecsDialog();
        objVideoCodecsDialog.show(objFragmentTransaction, "Video Codecs Dialog");
    }

    public void OnClickAudioCodecsRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        AudioCodecsDialog objAudioCodecsDialog = new AudioCodecsDialog();
        objAudioCodecsDialog.show(objFragmentTransaction, "Audio Codecs Dialog");
    }

    public void OnClickSpkMicRow()
    {
        SettingMicSpk objSettingMicSpk = new SettingMicSpk();

        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragTransaction = objFragManager.beginTransaction();

        objFragTransaction.replace(android.R.id.content, objSettingMicSpk,"Speaker & Microphone");
        objFragTransaction.addToBackStack("Speaker & Microphone");
        objFragTransaction.commit();
    }

    public void OnClickRingTonesRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        RingtonesDialog objRingtonesDialog = new RingtonesDialog();
        objRingtonesDialog.show(objFragmentTransaction, "StoreRingtones Dialog");
    }

    public void OnClickVoiceChangerRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        VoiceChangerDialog objVoiceChangerDialog = new VoiceChangerDialog();
        objVoiceChangerDialog.show(objFragmentTransaction, "Voice Changer Dialog");
    }

    public void OnClickNetworkRow()
    {
        NetworkFragment objNetworkFragment = new NetworkFragment();

        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragTransaction = objFragManager.beginTransaction();

        objFragTransaction.replace(android.R.id.content, objNetworkFragment ,"StoreNetwork");
        objFragTransaction.addToBackStack("StoreNetwork");
        objFragTransaction.commit();
    }

    public void OnClickDigitDTMFRow()
    {
        FragmentManager objFragManager = getFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragManager.beginTransaction();

        DigitDTMFDialog objDigitDTMFDialog = new DigitDTMFDialog();
        objDigitDTMFDialog.show(objFragmentTransaction, "Digit DTMF Dialog");
    }

    private void OnClickResetAllSettings()
    {
        ResetDialog objResetDialog = new ResetDialog();
        objResetDialog.show(getFragmentManager(), "Reset All Settings");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean Checked)
    {
        switch (compoundButton.getId())
        {
            case R.id.checkbox_multi_task_mode:
                OnClickMultiTaskMode(Checked);
                break;

            case R.id.checkbox_diagnostic_log:
                OnClickDiagnosticLog(Checked);
                break;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private void OnClickMultiTaskMode(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.BackgroundMode(bChecked);

        StoreGeneralSettings objSettings = new StoreGeneralSettings();
        objSettings.SetMultiTaskMode(bChecked);
    }

    private void OnClickDiagnosticLog(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.DiagnosticLog(bChecked);

        StoreGeneralSettings objSettings = new StoreGeneralSettings();
        objSettings.SetDiagnosticLog(bChecked);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialized()
    {
        StoreGeneralSettings objSettings = new StoreGeneralSettings();

        boolean bMultiTaskMode = objSettings.GetMultiTaskMode();
        VaxPhoneSIP.m_objVaxVoIP.BackgroundMode(bMultiTaskMode);

        boolean bDiagnosticLog = objSettings.GetDiagnosticLog();
        VaxPhoneSIP.m_objVaxVoIP.DiagnosticLog(bDiagnosticLog);
    }
}
