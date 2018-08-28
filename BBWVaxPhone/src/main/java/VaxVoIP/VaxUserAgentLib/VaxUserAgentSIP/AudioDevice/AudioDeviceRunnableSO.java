package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Runnable.VaxRunnable;

abstract class AudioDeviceRunnableSO
{
    private VaxRunnable m_objRunnable = null;

    AudioDeviceRunnableSO()
    {
        m_objRunnable = new VaxRunnable();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    private class OnCaptureAudioDevice implements Runnable
    {
        boolean m_bResult;

        int m_nInputDeviceId;
        int m_nOutputDeviceId;

        OnCaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId)
        {
            m_nInputDeviceId = nInputDeviceId;
            m_nOutputDeviceId = nOutputDeviceId;
        }

        public void run()
        {
            m_bResult = OnRunnableCaptureAudioDevice(m_nInputDeviceId, m_nOutputDeviceId);
        }
    }

    boolean PostCaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId)
    {
        OnCaptureAudioDevice objCaptureAudioDevice = new OnCaptureAudioDevice(nInputDeviceId, nOutputDeviceId);

        m_objRunnable.PostRunnableMsg(objCaptureAudioDevice, true);
        return objCaptureAudioDevice.m_bResult;
    }

    protected abstract boolean OnRunnableCaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnReleaseAudioDevice implements Runnable
    {
        @Override
        public void run()
        {
            OnRunnableReleaseAudioDevice();
        }
    }

    void PostReleaseAudioDevice()
    {
        OnReleaseAudioDevice objReleaseAudioDevice = new OnReleaseAudioDevice();

        m_objRunnable.PostRunnableMsg(objReleaseAudioDevice, false);
    }

    protected abstract void OnRunnableReleaseAudioDevice();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnEchoCancel implements Runnable
    {
        boolean m_bEnable;

        @Override
        public void run()
        {
            OnRunnableEchoCancel(m_bEnable);
        }
    }

    void PostEchoCancel(boolean bEnable)
    {
        OnEchoCancel objEchoCancel = new OnEchoCancel();
        objEchoCancel.m_bEnable = bEnable;

        m_objRunnable.PostRunnableMsg(objEchoCancel, false);
    }

    protected abstract void OnRunnableEchoCancel(boolean bEnable);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnGetAudioInDevTotal implements Runnable
    {
        int m_nResult;

        public void run()
        {
            m_nResult = OnRunnableGetAudioInDevTotal();
        }
    }

    int PostGetAudioInDevTotal()
    {
        OnGetAudioInDevTotal objGetAudioInDevTotal = new OnGetAudioInDevTotal();

        m_objRunnable.PostRunnableMsg(objGetAudioInDevTotal, true);
        return objGetAudioInDevTotal.m_nResult;
    }

    protected abstract int OnRunnableGetAudioInDevTotal();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnGetAudioOutDevTotal implements Runnable
    {
        int m_nResult;

        public void run()
        {
            m_nResult = OnRunnableGetAudioOutDevTotal();
        }
    }

    int PostGetAudioOutDevTotal()
    {
        OnGetAudioOutDevTotal objGetAudioOutDevTotal = new OnGetAudioOutDevTotal();

        m_objRunnable.PostRunnableMsg(objGetAudioOutDevTotal, true);
        return objGetAudioOutDevTotal.m_nResult;
    }

    protected abstract int OnRunnableGetAudioOutDevTotal();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnPlayAudioToSpk implements Runnable
    {
        byte[] m_aUnitDataBoth = null;
        byte[] m_aUnitDataLeft = null;
        byte[] m_aUnitDataRight = null;

        public void run()
        {
            OnRunnablePlayAudioToSpk(m_aUnitDataBoth, m_aUnitDataLeft, m_aUnitDataRight);
        }
    }

    void PostPlayAudioToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight)
    {
        OnPlayAudioToSpk objPlayAudioToSpk = new OnPlayAudioToSpk();

        objPlayAudioToSpk.m_aUnitDataBoth = aUnitDataBoth;
        objPlayAudioToSpk.m_aUnitDataLeft = aUnitDataLeft;
        objPlayAudioToSpk.m_aUnitDataRight = aUnitDataRight;

        m_objRunnable.PostRunnableMsg(objPlayAudioToSpk, false);
    }

    protected abstract void OnRunnablePlayAudioToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnSpeakerPhone implements Runnable
    {
        boolean m_bEnable;
        boolean m_bResult;

        @Override
        public void run()
        {
            m_bResult = OnRunnableSpeakerPhone(m_bEnable);
        }
    }

    public boolean PostSpeakerPhone(boolean bEnable)
    {
        OnSpeakerPhone objSpeakerPhone = new OnSpeakerPhone();
        objSpeakerPhone.m_bEnable = bEnable;

        m_objRunnable.PostRunnableMsg(objSpeakerPhone, true);
        return objSpeakerPhone.m_bResult;
    }

    protected abstract boolean OnRunnableSpeakerPhone(boolean bEnable);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnIsSpeakerPhone implements Runnable
    {
        boolean m_bResult;

        @Override
        public void run()
        {
            m_bResult = OnRunnableIsSpeakerPhone();
        }
    }

    public boolean PostIsSpeakerPhone()
    {
        OnIsSpeakerPhone objIsSpeakerPhone = new OnIsSpeakerPhone();

        m_objRunnable.PostRunnableMsg(objIsSpeakerPhone, true);
        return objIsSpeakerPhone.m_bResult;
    }

    protected abstract boolean OnRunnableIsSpeakerPhone();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
}
