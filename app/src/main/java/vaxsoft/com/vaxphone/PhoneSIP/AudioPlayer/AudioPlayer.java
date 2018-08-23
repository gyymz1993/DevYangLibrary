package vaxsoft.com.vaxphone.PhoneSIP.AudioPlayer;

import android.content.Context;
import android.media.MediaPlayer;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class AudioPlayer
{
    private MediaPlayer mMediaPlayer = null;
    private int m_nMediaId = -1;
    private Context mAppContext = null;

    public AudioPlayer()
    {
        mMediaPlayer = null;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////

    public void PlayLoad(int nMediaId)
    {
        PlayUnload();

        m_nMediaId = nMediaId;
        mAppContext = VaxPhoneAPP.getAppContext();
    }

    private void PlayUnload()
    {
        if (mMediaPlayer == null)
            return;

        mMediaPlayer.stop();
        mMediaPlayer.release();

        mMediaPlayer = null;
    }

    public void PlayStart(Boolean bLoop)
    {
        PlayStop();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(mAppContext, m_nMediaId);

        mMediaPlayer.setLooping(bLoop);
        mMediaPlayer.start();
    }

    public void PlayStop()
    {
        if(mMediaPlayer == null)
            return;

        mMediaPlayer.stop();
        mMediaPlayer.release();

        mMediaPlayer = null;
    }

}
