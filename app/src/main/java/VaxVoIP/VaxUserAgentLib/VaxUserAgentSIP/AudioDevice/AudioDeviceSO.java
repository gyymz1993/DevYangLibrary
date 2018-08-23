package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice;

import android.util.Log;

public abstract class AudioDeviceSO extends AudioDeviceRunnableSO
{
    private static AudioDeviceSO m_objAudioDeviceSO = null;

    static
    {
        try
        {
            System.loadLibrary("VaxUserAgentLib");
        }
        catch (UnsatisfiedLinkError e)
        {
            Log.e("Tag",e.getMessage());
        }
    }

    public AudioDeviceSO()
    {
        m_objAudioDeviceSO = this;

        OnCreatedAudioDeviceOBJ();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////  NATIVE METHODS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public native void OnCreatedAudioDeviceOBJ();

    public native void OnOpenedDeviceMic();
    public native void OnClosedDeviceMic();
    public native void OnOpenedDeviceSpk();
    public native void OnClosedDeviceSpk();
    public native void OnAudioMicData(byte[] pUnitDataPCM);
    public native void OnAudioPlayFill();

    ////////////////////////////////////////////////////////////////////
    //////////////////////  STATIC   EVENTS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public static boolean CaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId)
    {
        return m_objAudioDeviceSO.PostCaptureAudioDevice(nInputDeviceId, nOutputDeviceId);
    }

    public static void ReleaseAudioDevice()
    {
        m_objAudioDeviceSO.PostReleaseAudioDevice();
    }

    public static void EchoCancel(boolean bEnable)
    {
        m_objAudioDeviceSO.PostEchoCancel(bEnable);
    }

    public static int GetAudioInDevTotal()
    {
        return m_objAudioDeviceSO.PostGetAudioInDevTotal();
    }

    public static int GetAudioOutDevTotal()
    {
        return m_objAudioDeviceSO.PostGetAudioOutDevTotal();
    }

    public static void PlayAudioToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight)
    {
        m_objAudioDeviceSO.PostPlayAudioToSpk(aUnitDataBoth, aUnitDataLeft, aUnitDataRight);
    }

    public static boolean SpeakerPhone(boolean bEnable)
    {
        return m_objAudioDeviceSO.PostSpeakerPhone(bEnable);
    }

    public static boolean IsSpeakerPhone()
    {
        return m_objAudioDeviceSO.PostIsSpeakerPhone();
    }



    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract boolean OnEventCaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId);

    protected boolean  OnRunnableCaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId)
    {
        return OnEventCaptureAudioDevice(nInputDeviceId, nOutputDeviceId);
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract void OnEventReleaseAudioDevice();

    protected void OnRunnableReleaseAudioDevice()
    {
        OnEventReleaseAudioDevice();
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract void OnEventEchoCancel(boolean bEnable);

    protected void OnRunnableEchoCancel(boolean bEnable)
    {
        OnEventEchoCancel(bEnable);
    }

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract int OnEventGetAudioInDevTotal();

    protected int OnRunnableGetAudioInDevTotal()
    {
        return OnEventGetAudioInDevTotal();
    }

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract int OnEventGetAudioOutDevTotal();

    protected int OnRunnableGetAudioOutDevTotal()
    {
        return OnEventGetAudioOutDevTotal();
    }

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected void OnRunnablePlayAudioToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight)
    {
        OnEventPlayAudioToSpk(aUnitDataBoth, aUnitDataLeft, aUnitDataRight);
    }

    protected abstract void OnEventPlayAudioToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight);

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract boolean OnEventSpeakerPhone(boolean bEnable);

    protected boolean OnRunnableSpeakerPhone(boolean bEnable)
    {
        return OnEventSpeakerPhone(bEnable);
    }

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract boolean OnEventIsSpeakerPhone();

    protected boolean OnRunnableIsSpeakerPhone()
    {
        return OnEventIsSpeakerPhone();
    }
}
