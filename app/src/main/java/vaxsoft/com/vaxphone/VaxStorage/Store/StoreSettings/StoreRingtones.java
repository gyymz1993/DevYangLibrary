package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class StoreRingtones
{
    private final String RINGTONE = "VaxRingtone";
    private final int DEFAULT_RINGTONE = VaxPhoneSIP.VAX_RING_TONE_DIGITAL_RAIN;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void SetRingtone(int nSeletectRingtone)
    {
        PreferenceUtil.WritePreferenceValue(RINGTONE, nSeletectRingtone);
    }

    public int GetRingtone()
    {
        return PreferenceUtil.ReadPreferenceValue(RINGTONE, DEFAULT_RINGTONE);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
