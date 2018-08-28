package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreSpkMic
{
    private final String AUTO_GAIN_SPK = "VaxAutoGainSpk";
    private final String VOLUME_BOOST_SPK = "VaxVolumeBoostSpk";
    private final String AUTO_GAIN_MIC = "VaxAutoGainMic";
    private final String VOLUME_BOOST_MIC = "VaxVolumeBoostMic";
    private final String ECHO_CANCELLATION = "VaxEchoCancellation";

    private boolean AUTO_GAIN_SPK_DEFAULT_VALUE = false;
    private boolean VOLUME_BOOST_SPK_DEFAULT_VALUE = false;
    private boolean AUTO_GAIN_MIC_DEFAULT_VALUE = false;
    private boolean VOLUME_BOOST_MIC_DEFAULT_VALUE = false;
    private boolean ECHO_CANCELLATION_DEFAULT_VALUE = true;

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public boolean GetAutoGainSpk()
    {
        return PreferenceUtil.ReadPreferenceValue(AUTO_GAIN_SPK, AUTO_GAIN_SPK_DEFAULT_VALUE);
    }

    public boolean GetVolumeBoostSpk()
    {
        return PreferenceUtil.ReadPreferenceValue(VOLUME_BOOST_SPK, VOLUME_BOOST_SPK_DEFAULT_VALUE);
    }

    public boolean GetAutoGainMic()
    {
        return PreferenceUtil.ReadPreferenceValue(AUTO_GAIN_MIC, AUTO_GAIN_MIC_DEFAULT_VALUE);
    }

    public boolean GetVolumeBoostMic()
    {
        return PreferenceUtil.ReadPreferenceValue(VOLUME_BOOST_MIC, VOLUME_BOOST_MIC_DEFAULT_VALUE);
    }

    public boolean GetEchoCancellation()
    {
        return PreferenceUtil.ReadPreferenceValue(ECHO_CANCELLATION, ECHO_CANCELLATION_DEFAULT_VALUE);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public void SetAutoGainSpk(boolean bRandomlySIP)
    {
        PreferenceUtil.WritePreferenceValue(AUTO_GAIN_SPK, bRandomlySIP);
    }

    public void SetVolumeBoostSpk(boolean bRandomlySIP)
    {
        PreferenceUtil.WritePreferenceValue(VOLUME_BOOST_SPK, bRandomlySIP);
    }

    public void SetAutoGainMic(boolean bRandomlySIP)
    {
        PreferenceUtil.WritePreferenceValue(AUTO_GAIN_MIC, bRandomlySIP);
    }

    public void SetVolumeBoostMic(boolean bRandomlySIP)
    {
        PreferenceUtil.WritePreferenceValue(VOLUME_BOOST_MIC, bRandomlySIP);
    }

    public void SetEchoCancellation(boolean bRandomlySIP)
    {
        PreferenceUtil.WritePreferenceValue(ECHO_CANCELLATION, bRandomlySIP);
    }

}
