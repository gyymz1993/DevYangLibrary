package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class MediaSpk extends Thread
{
	private static final int SPEAKER_SAMPLE_RATE = 8000;
	private static final int SPEAKER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
	private static final int SPEAKER_CHANNELS = AudioFormat.CHANNEL_OUT_MONO;

	private static final int UNIT_PCM_SIZE = 160;
	private static final int UNIT_SAMPLE_SIZE = 80;

//	private static final int SPEAKER_SAMPLE_RATE = 16000;
//	private static final int SPEAKER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
//	private static final int SPEAKER_CHANNELS = AudioFormat.CHANNEL_OUT_MONO;
//	private static final int UNIT_PCM_SIZE = 640;
//	private static final int UNIT_SAMPLE_SIZE = 320;
	
	private Handler mHandler;

	private boolean m_bMuteSpk = false;
	private boolean m_bStopThread = false;
	
	private AudioTrack m_objAudioTrack = null;
	private IMediaSpk m_iMediaSpk = null;

	private Context mContext;

	MediaSpk(IMediaSpk iMediaSpk, Context objContext)
	{
		mContext = objContext;
		m_iMediaSpk = iMediaSpk;

		super.start();
		super.setPriority(MAX_PRIORITY);
	}
	
	public void run() 
	{
		Looper.prepare();
				
        mHandler = new Handler()
        {
            public void handleMessage(Message msg)
            {

            }
        };

        Looper.loop();
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

	private class OnOpenSpk implements Runnable
	{
		public void run() 
		{
			int nMinBuffSize = AudioTrack.getMinBufferSize(SPEAKER_SAMPLE_RATE, SPEAKER_CHANNELS, SPEAKER_AUDIO_ENCODING);
			
			if(nMinBuffSize <= 1280)
				nMinBuffSize = 1280;
			else
				nMinBuffSize = ((int) Math.ceil(((double) nMinBuffSize/UNIT_PCM_SIZE)) * UNIT_PCM_SIZE);
				
			m_objAudioTrack = new AudioTrack(AudioManager.STREAM_VOICE_CALL, SPEAKER_SAMPLE_RATE, SPEAKER_CHANNELS, SPEAKER_AUDIO_ENCODING, nMinBuffSize, AudioTrack.MODE_STREAM);
			m_objAudioTrack.setPositionNotificationPeriod(UNIT_SAMPLE_SIZE);
			
			m_objAudioTrack.setPlaybackPositionUpdateListener(new AudioTrack.OnPlaybackPositionUpdateListener()
			{
				public void onPeriodicNotification(AudioTrack track)
				{
					OnFillDataPCM();
				} 

				public void onMarkerReached(AudioTrack track)
				{
					 
				} 
			}); 

			AudioManager objAudioMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
			objAudioMgr.setMode(AudioManager.MODE_IN_COMMUNICATION);

			byte[] aData = new byte[nMinBuffSize * 2];
			PlaySpk(aData, nMinBuffSize * 2);

			m_objAudioTrack.play();

			RunnableNotify(this);
		}
	}
	
	Boolean OpenSpk(int nDeviceId)
	{
		m_bStopThread = false;
		
		OnOpenSpk objOpenSpk = new OnOpenSpk();
		PostMsg(objOpenSpk, true);

		return true;
	}

/////////////////////////////////////////////////////////////////////////////////////////////
	
	private class OnPlaySpk implements Runnable
	{
		byte[] m_aDataPCM = null;
		public int m_nSizePCM;
				
		public void run() 
		{
			if(m_bStopThread || m_objAudioTrack == null)
				return;

			m_objAudioTrack.write(m_aDataPCM, 0, m_nSizePCM);
		}
	}

	private void PlaySpk(byte[] aData, int nDataSize)
	{
		if(m_bStopThread || m_objAudioTrack == null)
			return;
		
		OnPlaySpk objPlaySpk = new OnPlaySpk();
		
		objPlaySpk.m_aDataPCM = aData;
		objPlaySpk.m_nSizePCM = nDataSize;
		
		if(m_bMuteSpk)
		{
			byte[] aSilencePCM = new byte[nDataSize];
			objPlaySpk.m_aDataPCM = aSilencePCM;

			//System.arraycopy(aSilencePCM, 0, objPlaySpk.m_aDataPCM, 0, nDataSize);
		}
		else
		{
			//System.arraycopy(aData, 0, objPlaySpk.m_aDataPCM, 0, nDataSize);
		}

		PostMsg(objPlaySpk, false);
	}

	public void PlayToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight)
	{
		PlaySpk(aUnitDataBoth, aUnitDataBoth.length);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////
	
	private class OnCloseSpk implements Runnable
	{
		public void run() 
		{
			if(m_objAudioTrack != null)
			{
				m_objAudioTrack.stop();
				m_objAudioTrack.release();
				m_objAudioTrack = null;
			}
			
			RunnableNotify(this);
		}
	}

	void CloseSpk()
	{
		m_bStopThread = true;
		Sleep(100);
		
		OnCloseSpk objCloseSpk = new OnCloseSpk();
		PostMsg(objCloseSpk, true);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private class OnSpeakerPhone implements Runnable
	{
		boolean m_bResult = false;
		boolean m_bEnable = false;

		public void run()
		{
			AudioManager objAudioMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

			if(m_bEnable)
			{
				if (objAudioMgr.isSpeakerphoneOn() == false)
				{
					objAudioMgr.setMode(AudioManager.MODE_IN_COMMUNICATION);
					objAudioMgr.setSpeakerphoneOn(true);
				}
			}
			else
			{
				if (objAudioMgr.isSpeakerphoneOn() == true)
				{
					objAudioMgr.setMode(AudioManager.MODE_IN_COMMUNICATION);
					objAudioMgr.setSpeakerphoneOn(false);
				}
			}

			m_bResult = true;

			RunnableNotify(this);
		}
	}

	boolean SpeakerPhone(boolean bEnable)
	{
		OnSpeakerPhone objSpeakerPhone = new OnSpeakerPhone();
		objSpeakerPhone.m_bEnable = bEnable;

		PostMsg(objSpeakerPhone, true);

		return objSpeakerPhone.m_bResult;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private class OnIsSpeakerPhone implements Runnable
	{
		boolean m_bResult = false;

		public void run()
		{
			AudioManager objAudioMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
			m_bResult = objAudioMgr.isSpeakerphoneOn();

			RunnableNotify(this);
		}
	}

	boolean IsSpeakerPhone()
	{
		OnIsSpeakerPhone objIsSpeakerPhone = new OnIsSpeakerPhone();

		PostMsg(objIsSpeakerPhone, true);
		return objIsSpeakerPhone.m_bResult;
	}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void Mute(boolean bEnable)
    {
    	m_bMuteSpk = bEnable;
    }
	
	private void PostMsg(Runnable objRunnable, boolean bWait)
	{
		mHandler.post(objRunnable);

		if (bWait)
			RunnableWait(objRunnable);
	}
	
	private void RunnableNotify(Runnable objRunnable)
	{
		Sleep(20);
		
		synchronized(objRunnable)
		{
			objRunnable.notify();
		}
	}
	
	private void RunnableWait(Runnable objRunnable)
	{
		try 
		{
			synchronized(objRunnable)
			{
				objRunnable.wait();
			}
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private void Sleep(int nMilliSec)
	{
		try 
		{
			Thread.sleep(nMilliSec);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private void OnFillDataPCM()
	{
		if(m_bStopThread || m_objAudioTrack == null)
			return;

		//for(int nCount = 0; nCount < 2; nCount++)
		{
			m_iMediaSpk.OnSpkAudioPlayFill();

//			if (!bFilled[0])
//			{
//				byte[] aSilencePCM = new byte[UNIT_PCM_SIZE];
//				System.arraycopy(aSilencePCM, 0, aUnitDataBoth, 0, UNIT_PCM_SIZE);
//			}
//
//			PlaySpk(aUnitDataBoth, aUnitDataBoth.length);


			//Log.d("Media Device SPK", "-------------------OnFillDataPCM: Not Filled-----------------");
		}
	}
}
