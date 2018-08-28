package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.VideoDevice;

import android.util.Log;

public abstract class VideoDeviceSO extends VideoDeviceRunnableSO
{
    private static VideoDeviceSO m_objVideoDeviceSO = null;

    static
    {
        try
        {
            System.loadLibrary("VaxUserAgentLib");
        }
        catch (UnsatisfiedLinkError e)
        {
            System.err.println("public native code library failed to load.\n" + e);
            Log.e("Tag",e.getMessage());
        }
    }

    public VideoDeviceSO()
    {
        m_objVideoDeviceSO = this;

        OnCreatedVideoDeviceOBJ();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////  NATIVE METHODS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public native void OnCreatedVideoDeviceOBJ();

    public native void OnVideoDeviceFrame(int nDeviceId, int nFrameRate, int nOrientation, byte[] aFrameY, byte[] aFrameU, byte[] aFrameV, int nSizeY, int nSizeUV, int nRowStrideY, int nRowStrideUV, int nPixelStrideUV, int nWidth, int nHeight);

    ////////////////////////////////////////////////////////////////////
    //////////////////////  STATIC   EVENTS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public static boolean OpenVideoDevice(int nDeviceId, int nQuality)
    {
        return m_objVideoDeviceSO.PostOpenVideoDevice(nDeviceId, nQuality);
    }

    public static void CloseVideoDevice()
    {
        m_objVideoDeviceSO.PostCloseVideoDevice();
    }

    public static int GetVideoDeviceCount()
    {
        return m_objVideoDeviceSO.PostGetVideoDeviceCount();
    }

    public static String GetVideoDeviceName(int nDeviceId)
    {
        return m_objVideoDeviceSO.PostGetVideoDeviceName(nDeviceId);
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract boolean OnEventOpenVideoDevice(int nDeviceId, int nQuality);

    protected boolean OnRunnableOpenVideoDevice(int nDeviceId, int nQuality)
    {
        return OnEventOpenVideoDevice(nDeviceId, nQuality);
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract void OnEventCloseVideoDevice();

    protected void OnRunnableCloseVideoDevice()
    {
        OnEventCloseVideoDevice();
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract int OnEventGetVideoDeviceCount();

    protected int OnRunnableGetVideoDeviceCount()
    {
        return OnEventGetVideoDeviceCount();
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    protected abstract String OnEventGetVideoDeviceName(int nDeviceId);

    protected String  OnRunnableGetVideoDeviceName(int nDeviceId)
    {
        return OnEventGetVideoDeviceName(nDeviceId);
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

}
