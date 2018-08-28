package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.VoiceChangerDialog;
import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreVoiceChanger
{
    private final String VOICE_CHANGER = "Voice Changer";
    private final int DEFAULT_VOICE_CHANGER = VoiceChangerDialog.VAX_VOICE_CHANGER_HELIUM_INHALED;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void SetVoiceChanger(int nVoiceChanger)
    {
        PreferenceUtil.WritePreferenceValue(VOICE_CHANGER, nVoiceChanger);
    }

    public int GetVoiceChanger()
    {
        return PreferenceUtil.ReadPreferenceValue(VOICE_CHANGER, DEFAULT_VOICE_CHANGER);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
