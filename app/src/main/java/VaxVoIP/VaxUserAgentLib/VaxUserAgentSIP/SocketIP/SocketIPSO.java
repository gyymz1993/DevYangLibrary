package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.SocketIP;

import android.util.Log;

public abstract class SocketIPSO extends SocketIPRunnableSO
{
    private static SocketIPSO m_objSocketIPSO = null;

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

    public SocketIPSO()
    {
        m_objSocketIPSO = this;

        OnCreatedSocketIPOBJ();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////  NATIVE METHODS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public native void OnCreatedSocketIPOBJ();

    ////////////////////////////////////////////////////////////////////
    //////////////////////  STATIC   EVENTS   //////////////////////////
    ///////////////////////////////////////////////////////////////////


    public static void GetStartIP()
    {
        m_objSocketIPSO.PostGetStartIP();
    }

    public static String GetNextIP()
    {
        return m_objSocketIPSO.PostGetNextIP();
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    protected abstract void  OnEventGetStartIP();

    public void OnRunnableGetStartIP()
    {
        OnEventGetStartIP();
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    protected abstract String OnEventGetNextIP();

    public String OnRunnableGetNextIP()
    {
        return OnEventGetNextIP();
    }
}
