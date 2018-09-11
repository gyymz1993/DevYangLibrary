package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;

class MediaMic extends Thread
{
	//private static final int UNIT_SAMPLE_SIZE = 320;
	//private static final int UNIT_PCM_SIZE = 640;
	//private static final int RECORDER_SAMPLE_RATE = 16000;
    //private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    //private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

	private static final int RECORDER_SAMPLE_RATE = 8000;
	private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
	private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

	private static final int UNIT_PCM_SIZE = 160;
	private static final int UNIT_SAMPLE_SIZE = 80;

    private boolean m_bMuteMic = false;
    private boolean m_bStopThread = false;

	private IMediaMic m_iMediaMic;
	private Context mContext;

    private AudioRecord m_objAudioRecord = null;
    private Handler mHandler;

	private Handler mHandlerCurrent = new Handler(Looper.myLooper());
        
    MediaMic(IMediaMic iMediaMic, Context objContext)
	{
		mContext = objContext;
		m_iMediaMic = iMediaMic;

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

/////////////////////////////////////////////////////////////////////////////////////////////	
	
    private class OnOpenMic implements Runnable
    {
    	public void run() 
		{
    		int nMinBuffSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLE_RATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
    		nMinBuffSize = ((int) Math.ceil(((double) nMinBuffSize/UNIT_PCM_SIZE)) * UNIT_PCM_SIZE);
    		
    		int nVerAPI = android.os.Build.VERSION.SDK_INT;
    		
    		if(nVerAPI >= 11)
    			m_objAudioRecord = new AudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION, RECORDER_SAMPLE_RATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, nMinBuffSize);
    		else
    			m_objAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLE_RATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, nMinBuffSize);
    		    
    		m_objAudioRecord.setPositionNotificationPeriod(UNIT_SAMPLE_SIZE);

			m_objAudioRecord.setRecordPositionUpdateListener(new AudioRecord.OnRecordPositionUpdateListener()
			{
				public void onPeriodicNotification(AudioRecord Record)
				{
					if(m_bStopThread || m_objAudioRecord == null)
						return;
					
					byte[] aRecordBuff = new byte[UNIT_PCM_SIZE];
					m_objAudioRecord.read(aRecordBuff, 0, UNIT_PCM_SIZE);
					
					if(m_bMuteMic)
					{
						byte[] aSilencePCM = new byte[UNIT_PCM_SIZE];
						OnAudioPCM(aSilencePCM, UNIT_PCM_SIZE);
					}
					else
					{
						OnAudioPCM(aRecordBuff, UNIT_PCM_SIZE);
					}
				} 

				public void onMarkerReached(AudioRecord Record)
				{
					 
				} 
			}); 

    		m_objAudioRecord.startRecording();
    		RunnableNotify(this);
		}
    }

    boolean OpenMic(int nDeviceId)
    {
		if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
		{
			return false;
		}

    	m_bStopThread = false;

    	OnOpenMic objOpenMic = new OnOpenMic(); 

    	mHandler.post(objOpenMic);
    	RunnableWait(objOpenMic);

		return true;
    }

/////////////////////////////////////////////////////////////////////////////////////////////
	
    private class OnCloseMic implements Runnable
    {
    	public void run() 
    	{
    		if(m_objAudioRecord != null)
    		{
    			m_objAudioRecord.stop();
    	    	m_objAudioRecord.release();
    	    	m_objAudioRecord = null;
    		}

    		RunnableNotify(this);
    	}
    }
    
    void CloseMic()
    {
		//Log.e("MediaMic", "=============================== CloseMic: In ========================================= ");

		m_bStopThread = true;
    	Sleep(100);

    	OnCloseMic objCloseMic = new OnCloseMic();

    	mHandler.post(objCloseMic);
    	RunnableWait(objCloseMic);

		//Log.e("MediaMic", "=============================== CloseMic: Out ========================================= ");

	}

	private void OnAudioPCM(byte[] aDataPCM, int nSizePCM)
	{
		PostMicDataPCM(aDataPCM, nSizePCM);
	}

	private class MediaMicDataRunnable implements Runnable
	{
		byte[] m_aDataPCM = null;
		int m_nSizePCM = 0;

		public void run()
		{
			m_iMediaMic.OnMediaMicPCM(m_aDataPCM, m_nSizePCM);
		}
	}

	private void PostMicDataPCM(byte aDataPCM[], int nSizePCM)
	{
		MediaMicDataRunnable objRunable = new MediaMicDataRunnable();

		objRunable.m_aDataPCM = new byte[nSizePCM];
		System.arraycopy(aDataPCM, 0, objRunable.m_aDataPCM, 0, nSizePCM);
		objRunable.m_nSizePCM = nSizePCM;

		mHandlerCurrent.post(objRunable);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public void Mute(Boolean bEnable)
    {
    	m_bMuteMic = bEnable;
    }
    
	private void RunnableNotify(Runnable runnable)
	{
		Sleep(50);
		
		synchronized(runnable)
		{
			runnable.notify();
		}
	}
	
	private void RunnableWait(Runnable runnable)
	{
		try 
		{
			synchronized(runnable)
			{
				runnable.wait();
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
}