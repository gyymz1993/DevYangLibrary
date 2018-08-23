package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Network;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Runnable.VaxRunnable;

abstract class NetworkRunnableSO
{
    private VaxRunnable m_objRunnable = null;

    NetworkRunnableSO()
    {
        m_objRunnable = new VaxRunnable();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnNetworkReachability implements Runnable
    {
        boolean m_bResult;
        boolean m_bEnable;

        public OnNetworkReachability(boolean bEnable)
        {
            m_bEnable = bEnable;
        }

        public void run()
        {
            m_bResult = OnRunnableNetworkReachability(m_bEnable);
        }
    }

    boolean PostNetworkReachability(boolean bEnable)
    {
        OnNetworkReachability objNetworkReachability = new OnNetworkReachability(bEnable);

        m_objRunnable.PostRunnableMsg(objNetworkReachability, true);

        return objNetworkReachability.m_bResult;
    }

    protected abstract boolean OnRunnableNetworkReachability(boolean bEnable);

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private class OnIsNetworkAvailable implements Runnable
    {
        boolean m_bResult;

        @Override
        public void run()
        {
            m_bResult = OnRunnableIsNetworkAvailable();
        }
    }

    boolean PostIsNetworkAvailable()
    {
        OnIsNetworkAvailable objNetwork = new OnIsNetworkAvailable();
        m_objRunnable.PostRunnableMsg(objNetwork, true);

        return objNetwork.m_bResult;
    }

    protected abstract boolean OnRunnableIsNetworkAvailable();
}
