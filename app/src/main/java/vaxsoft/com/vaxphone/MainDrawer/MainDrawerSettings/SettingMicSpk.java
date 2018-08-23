package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreSpkMic;

public class SettingMicSpk extends Fragment implements CompoundButton.OnCheckedChangeListener
{
    CheckBox AutoGainSpk, VolumeBoostSpk, AutoGainMic, VolumeBoostMic, EchoCancellation;

    boolean m_bAutoGainSpk , m_bAutoGainMic, m_bVolumeBoostSpk, m_bVolumeBoostMic, m_bEchoCancellation = false;

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_spk_mic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        Toolbar toolbar = view.findViewById(R.id.actionbar);
        ((MainTabActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainTabActivity) getActivity()).getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setTitle("Speaker & Microphone");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        InitView(view);
        InitCheckChangedListeners();
        UpdateUI();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private void InitView(View view)
    {
        AutoGainSpk = view.findViewById(R.id.checkbox_auto_gain_spk);
        VolumeBoostSpk = view.findViewById(R.id.checkbox_volume_boost_spk);
        AutoGainMic = view.findViewById(R.id.checkbox_auto_gain_mic);
        VolumeBoostMic = view.findViewById(R.id.checkbox_volume_boost_mic);
        EchoCancellation = view.findViewById(R.id.checkbox_echo_cancellation);
    }

    private void InitCheckChangedListeners()
    {
        AutoGainSpk.setOnCheckedChangeListener(this);
        VolumeBoostSpk.setOnCheckedChangeListener(this);
        AutoGainMic.setOnCheckedChangeListener(this);
        VolumeBoostMic.setOnCheckedChangeListener(this);
        EchoCancellation.setOnCheckedChangeListener(this);
    }

    private void UpdateUI()
    {
        StoreSpkMic objStoreSpkMic = new StoreSpkMic();

        m_bAutoGainSpk = objStoreSpkMic.GetAutoGainSpk();
        AutoGainSpk.setChecked(m_bAutoGainSpk);

        m_bAutoGainMic = objStoreSpkMic.GetAutoGainMic();
        AutoGainMic.setChecked(m_bAutoGainMic);

        m_bVolumeBoostSpk = objStoreSpkMic.GetVolumeBoostSpk();
        VolumeBoostSpk.setChecked(m_bVolumeBoostSpk);

        m_bVolumeBoostMic = objStoreSpkMic.GetVolumeBoostMic();
        VolumeBoostMic.setChecked(m_bVolumeBoostMic);

        m_bEchoCancellation = objStoreSpkMic.GetEchoCancellation();
        EchoCancellation.setChecked(m_bEchoCancellation);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked)
    {
        switch (compoundButton.getId())
        {
            case R.id.checkbox_auto_gain_spk:
                OnClickAutoGainSpk(bChecked);
                break;

            case R.id.checkbox_volume_boost_spk:
                OnClickVolBoostSpk(bChecked);
                break;

            case R.id.checkbox_auto_gain_mic:
                OnClickAutoGainMic(bChecked);
                break;

            case R.id.checkbox_volume_boost_mic:
                OnClickVolBoostMic(bChecked);
                break;

            case R.id.checkbox_echo_cancellation:
                OnClickEchoCancellation(bChecked);
                break;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    private void OnClickAutoGainSpk(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.AutoGainSpk(bChecked);

        StoreSpkMic objStoreSpkMic = new StoreSpkMic();
        objStoreSpkMic.SetAutoGainSpk(bChecked);
    }

    private void OnClickVolBoostSpk(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.SetVolumeBoostSpk(bChecked);

        StoreSpkMic objStoreSpkMic = new StoreSpkMic();
        objStoreSpkMic.SetVolumeBoostSpk(bChecked);
    }

    private void OnClickAutoGainMic(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.AutoGainMic(bChecked);

        StoreSpkMic objStoreSpkMic = new StoreSpkMic();
        objStoreSpkMic.SetAutoGainMic(bChecked);
    }

    private void OnClickVolBoostMic(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.SetVolumeBoostMic(bChecked);

        StoreSpkMic objStoreSpkMic = new StoreSpkMic();
        objStoreSpkMic.SetVolumeBoostMic(bChecked);
    }

    private void OnClickEchoCancellation(boolean bChecked)
    {
        VaxPhoneSIP.m_objVaxVoIP.EchoCancellation(bChecked);

        StoreSpkMic objStoreSpkMic = new StoreSpkMic();
        objStoreSpkMic.SetEchoCancellation(bChecked);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialize()
    {
        StoreSpkMic objStoreSpkMic = new StoreSpkMic();

        boolean bEnable = objStoreSpkMic.GetAutoGainSpk();
        VaxPhoneSIP.m_objVaxVoIP.AutoGainSpk(bEnable);

        bEnable = objStoreSpkMic.GetAutoGainMic();
        VaxPhoneSIP.m_objVaxVoIP.AutoGainMic(bEnable);

        bEnable = objStoreSpkMic.GetVolumeBoostSpk();
        VaxPhoneSIP.m_objVaxVoIP.SetVolumeBoostSpk(bEnable);

        bEnable = objStoreSpkMic.GetVolumeBoostMic();
        VaxPhoneSIP.m_objVaxVoIP.SetVolumeBoostMic(bEnable);

        bEnable = objStoreSpkMic.GetEchoCancellation();
        VaxPhoneSIP.m_objVaxVoIP.EchoCancellation(bEnable);
    }
}
