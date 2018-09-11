package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreVideoCodecs
{
    private final String VIDEO_CODEC_VP8 = "VaxVideoCodecVP8";
    private final String VIDEO_CODEC_H263 = "VaxVideoCodecH263";
    private final String VIDEO_CODEC_H263P = "VaxVideoCodecH263P";

    private final boolean VIDEO_CODEC_VP8_DEFAULT_VALUE = true;
    private final boolean VIDEO_CODEC_H263P_DEFAULT_VALUE = false;
    private final boolean VIDEO_CODEC_H263_DEFAULT_VALUE = false;

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public void GetVideoCodecs(boolean[] aCodecs)
    {
        aCodecs[VaxUserAgentLib.VAX_CODEC_VP8] = PreferenceUtil.ReadPreferenceValue(VIDEO_CODEC_VP8, VIDEO_CODEC_VP8_DEFAULT_VALUE);
        aCodecs[VaxUserAgentLib.VAX_CODEC_H263] = PreferenceUtil.ReadPreferenceValue(VIDEO_CODEC_H263, VIDEO_CODEC_H263_DEFAULT_VALUE);
        aCodecs[VaxUserAgentLib.VAX_CODEC_H263P] = PreferenceUtil.ReadPreferenceValue(VIDEO_CODEC_H263P, VIDEO_CODEC_H263P_DEFAULT_VALUE);
    }

    public void SetVideoCodecs(boolean[] aCodecs)
    {
        PreferenceUtil.WritePreferenceValue(VIDEO_CODEC_VP8, aCodecs[VaxUserAgentLib.VAX_CODEC_VP8]);
        PreferenceUtil.WritePreferenceValue(VIDEO_CODEC_H263, aCodecs[VaxUserAgentLib.VAX_CODEC_H263]);
        PreferenceUtil.WritePreferenceValue(VIDEO_CODEC_H263P, aCodecs[VaxUserAgentLib.VAX_CODEC_H263P]);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
}
