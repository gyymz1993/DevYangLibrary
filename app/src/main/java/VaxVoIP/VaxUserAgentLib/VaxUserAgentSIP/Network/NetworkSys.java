package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.ConnectionService;

public class NetworkSys extends NetworkSO implements INetwork
{
    private NetworkReceiver mNetworkReceiver = null;

    NetworkSys(Context objContext)
    {
        mNetworkReceiver = new NetworkReceiver(this, objContext);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected boolean OnEventNetworkReachability(boolean bEnable)
    {
        if (mNetworkReceiver == null)
            return false;

        if (!bEnable)
        {
            mNetworkReceiver.UnRegisterReciever();
            return true;
        }

        mNetworkReceiver.RegisterReceiver();
        return true;
    }

    @Override
    protected boolean OnEventIsNetworkAvailable()
    {
        return mNetworkReceiver.IsNetworkAvailable();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
}
