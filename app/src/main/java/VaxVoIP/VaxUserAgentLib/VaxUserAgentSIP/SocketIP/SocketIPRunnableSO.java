package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.SocketIP;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Runnable.VaxRunnable;

abstract class SocketIPRunnableSO
{
    private VaxRunnable m_objRunnable = null;

    SocketIPRunnableSO()
    {
        m_objRunnable = new VaxRunnable();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnGetStartIP implements Runnable
    {
        public void run()
        {
            OnRunnableGetStartIP();
        }
    }

    void PostGetStartIP()
    {
        OnGetStartIP objGetStartIP = new OnGetStartIP();

        m_objRunnable.PostRunnableMsg(objGetStartIP, true);
    }

    protected abstract void OnRunnableGetStartIP();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnGetNextIP implements Runnable
    {
        String m_sIP;

        public void run()
        {
            m_sIP = OnRunnableGetNextIP();
        }
    }

    String PostGetNextIP()
    {
        OnGetNextIP objGetNextIP = new OnGetNextIP();

        m_objRunnable.PostRunnableMsg(objGetNextIP, true);

        return objGetNextIP.m_sIP;
    }

    protected abstract String OnRunnableGetNextIP();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
}
