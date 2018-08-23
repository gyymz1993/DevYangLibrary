package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice;

import android.content.Context;

class AudioDeviceSys extends AudioDeviceSO implements IMediaSpk, IMediaMic
{
    private MediaMic m_objMediaMic = null;
    private MediaSpk m_objMediaSpk = null;

    public AudioDeviceSys(Context objContext)
    {
        m_objMediaMic = new MediaMic(this, objContext);
        m_objMediaSpk = new MediaSpk(this, objContext);
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    public void OnOpenedDeviceMic()
    {
        super.OnOpenedDeviceMic();
    }

    public void OnClosedDeviceMic()
    {
        super.OnClosedDeviceMic();
    }

    public void OnOpenedDeviceSpk()
    {
        super.OnOpenedDeviceSpk();
    }

    public void OnClosedDeviceSpk()
    {
        super.OnClosedDeviceSpk();
    }

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    @Override
    public void OnSpkAudioPlayFill()
    {
        super.OnAudioPlayFill();
    }

    @Override
    public void OnMediaMicPCM(byte[] aDataPCM, int nSizePCM)
    {
        super.OnAudioMicData(aDataPCM);
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    @Override
    protected boolean OnEventCaptureAudioDevice(int nInputDeviceId, int nOutputDeviceId)
    {
        if(!m_objMediaMic.OpenMic(nInputDeviceId))
            return false;

        if(!m_objMediaSpk.OpenSpk(nOutputDeviceId))
        {
            m_objMediaMic.CloseMic();
            return false;
        }

        return true;
    }

    @Override
    protected void OnEventReleaseAudioDevice()
    {
        m_objMediaMic.CloseMic();
        m_objMediaSpk.CloseSpk();
    }

    @Override
    protected void OnEventEchoCancel(boolean bEnable)
    {

    }

    @Override
    protected int OnEventGetAudioInDevTotal()
    {
        return 1;
    }

    @Override
    protected int OnEventGetAudioOutDevTotal()
    {
        return 1;
    }

    @Override
    protected void OnEventPlayAudioToSpk(byte[] aUnitDataBoth, byte[] aUnitDataLeft, byte[] aUnitDataRight)
    {
        m_objMediaSpk.PlayToSpk(aUnitDataBoth, aUnitDataLeft, aUnitDataRight);
    }

    @Override
    protected boolean OnEventSpeakerPhone(boolean bEnable)
    {
        return m_objMediaSpk.SpeakerPhone(bEnable);
    }

    @Override
    protected boolean OnEventIsSpeakerPhone()
    {
        return m_objMediaSpk.IsSpeakerPhone();
    }
}
