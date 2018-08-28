package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Network;

import android.util.Log;

public abstract class NetworkSO extends NetworkRunnableSO
{
    private static NetworkSO m_objNetworkSO = null;

    static
    {
        try
        {
            System.loadLibrary("VaxUserAgentLib");
        }
        catch (UnsatisfiedLinkError e)
        {
            System.err.println("public native code library failed to load.\n" + e);
            Log.e("Tag", e.getMessage());
        }
    }

    NetworkSO()
    {
        m_objNetworkSO = this;

        OnCreatedNetworkOBJ();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////  NATIVE METHODS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public native void OnCreatedNetworkOBJ();
    public native void OnNetworkReachability(boolean bAvailable);

    ////////////////////////////////////////////////////////////////////
    //////////////////////  STATIC   EVENTS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public static boolean NetworkReachability(boolean bEnable)
    {
        return m_objNetworkSO.PostNetworkReachability(bEnable);
    }

    public static boolean IsNetworkAvailable()
    {
        return m_objNetworkSO.PostIsNetworkAvailable();
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    protected abstract boolean OnEventNetworkReachability(boolean bEnable);

    @Override
    protected boolean OnRunnableNetworkReachability(boolean bEnable)
    {
        return OnEventNetworkReachability(bEnable);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    protected abstract boolean OnEventIsNetworkAvailable();

    @Override
    protected boolean OnRunnableIsNetworkAvailable()
    {
        return OnEventIsNetworkAvailable();
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
}
