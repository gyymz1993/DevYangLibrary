package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.VideoDevice;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Runnable.VaxRunnable;

abstract class VideoDeviceRunnableSO
{
    private VaxRunnable m_objRunnable = null;

    VideoDeviceRunnableSO()
    {
        m_objRunnable = new VaxRunnable();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnOpenVideoDevice implements Runnable
    {
        boolean m_bResult;

        int m_nDeviceId;
        int m_nQuality;

        OnOpenVideoDevice(int nDeviceId, int nQuality)
        {
            m_nDeviceId = nDeviceId;
            m_nQuality = nQuality;
        }

        public void run()
        {
            m_bResult = OnRunnableOpenVideoDevice(m_nDeviceId, m_nQuality);
        }
    }

    boolean PostOpenVideoDevice(int nDeviceId, int nQuality)
    {
        OnOpenVideoDevice objOpenVideoDevice = new OnOpenVideoDevice(nDeviceId, nQuality);

        m_objRunnable.PostRunnableMsg(objOpenVideoDevice, true);
        return objOpenVideoDevice.m_bResult;
    }

    protected abstract boolean OnRunnableOpenVideoDevice(int nDeviceId, int nQuality);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnCloseVideoDevice implements Runnable
    {
        public void run()
        {
            OnRunnableCloseVideoDevice();
        }
    }

    void PostCloseVideoDevice()
    {
        OnCloseVideoDevice objCloseVideoDevice = new OnCloseVideoDevice();

        m_objRunnable.PostRunnableMsg(objCloseVideoDevice, true);
    }

    protected abstract void OnRunnableCloseVideoDevice();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnGetVideoDeviceCount implements Runnable
    {
        int m_bResult;

        public void run()
        {
            m_bResult = OnRunnableGetVideoDeviceCount();
        }
    }

    int PostGetVideoDeviceCount()
    {
        OnGetVideoDeviceCount objGetVideoDeviceCount = new OnGetVideoDeviceCount();

        m_objRunnable.PostRunnableMsg(objGetVideoDeviceCount, true);
        return objGetVideoDeviceCount.m_bResult;
    }

    protected abstract int OnRunnableGetVideoDeviceCount();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnGetVideoDeviceName implements Runnable
    {
        String m_bResult;
        int m_nDeviceId;

        OnGetVideoDeviceName(int nDeviceId)
        {
            m_nDeviceId = nDeviceId;
        }

        public void run()
        {
            m_bResult = OnRunnableGetVideoDeviceName(m_nDeviceId);
        }
    }

    String PostGetVideoDeviceName(int nDeviceId)
    {
        OnGetVideoDeviceName objGetVideoDeviceName = new OnGetVideoDeviceName(nDeviceId);

        m_objRunnable.PostRunnableMsg(objGetVideoDeviceName, true);
        return objGetVideoDeviceName.m_bResult;
    }

    protected abstract String OnRunnableGetVideoDeviceName(int nDeviceId);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

}
