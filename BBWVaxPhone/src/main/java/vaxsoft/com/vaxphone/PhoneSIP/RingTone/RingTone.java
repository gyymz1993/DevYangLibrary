package vaxsoft.com.vaxphone.PhoneSIP.RingTone;

import vaxsoft.com.vaxphone.PhoneSIP.AudioPlayer.AudioPlayer;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class RingTone
{
    private static final int VAX_RING_TONE_GROOVY       = 0;
    private static final int VAX_RING_TONE_DIGITAL_RAIN = 1;
    private static final int VAX_RING_TONE_MAGICAL      = 2;
    private static final int VAX_RING_TONE_DEJA_VU      = 3;
    private static final int VAX_RING_TONE_OFFICE_PHONE = 4;

    private AudioPlayer m_objAudioPlayer = null;

    public RingTone()
    {
        m_objAudioPlayer = new AudioPlayer();
    }

    private void LoadRingTone(int nRingId)
    {
        m_objAudioPlayer.PlayLoad(nRingId);
    }

    public void MuteRingTone()
    {
        StopRingTone();
    }

    public void SetRingTone(int nRingToneId)
    {
        if(nRingToneId == VAX_RING_TONE_GROOVY)
        {
            LoadRingTone(R.raw.ring_tone_groovy);
        }
        else if(nRingToneId == VAX_RING_TONE_DIGITAL_RAIN)
        {
            LoadRingTone(R.raw.ring_tone_digital_rain);
        }
        else if(nRingToneId == VAX_RING_TONE_MAGICAL)
        {
            LoadRingTone(R.raw.ring_tone_magical);
        }
        else if(nRingToneId == VAX_RING_TONE_DEJA_VU)
        {
            LoadRingTone(R.raw.ring_tone_dejavu);
        }
        else if(nRingToneId == VAX_RING_TONE_OFFICE_PHONE)
        {
            LoadRingTone(R.raw.ring_tone_office_phone);
        }
        else
        {
            LoadRingTone(R.raw.ring_tone_digital_rain);
        }
    }

    public void StartRingTone()
    {
        m_objAudioPlayer.PlayStart(true);
    }

    public void StopRingTone()
    {
        m_objAudioPlayer.PlayStop();
    }
}
