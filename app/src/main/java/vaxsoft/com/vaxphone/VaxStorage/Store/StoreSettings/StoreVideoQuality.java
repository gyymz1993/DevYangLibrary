package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreVideoQuality
{
    private final String VIDEO_QUALITY = "Video Quality";
    private final int DEFAULT_VIDEO_QUALITY = VaxUserAgentLib.VAX_VIDEO_QUALITY_STANDARD;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void SetVideoQuality(int nBitrate)
    {
        PreferenceUtil.WritePreferenceValue(VIDEO_QUALITY, nBitrate);
    }

    public int GetVideoQuality()
    {
        return PreferenceUtil.ReadPreferenceValue(VIDEO_QUALITY, DEFAULT_VIDEO_QUALITY);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
