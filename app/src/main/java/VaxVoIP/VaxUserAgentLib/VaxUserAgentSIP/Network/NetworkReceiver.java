package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver
{
    INetwork miNetwork = null;
    Context mContext = null;
    boolean mbIsNetworkAvailable = false;

    public NetworkReceiver(INetwork iNetwork, Context objContext)
    {
        mContext = objContext;
        miNetwork = iNetwork;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public boolean IsNetworkAvailable()
    {
        return NetworkReachability();
    }

    public void RegisterReceiver()
    {
        UnRegisterReciever();

        IntentFilter objIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        try
        {
            mContext.registerReceiver(this, objIntentFilter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        mbIsNetworkAvailable = NetworkReachability();
        miNetwork.OnNetworkReachability(mbIsNetworkAvailable);
    }

    public void UnRegisterReciever()
    {
        try
        {
            mContext.unregisterReceiver(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private boolean NetworkReachability()
    {
        ConnectivityManager objConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo objNetworkInfo = objConnectivityManager != null ? objConnectivityManager.getActiveNetworkInfo() : null;

        if (objNetworkInfo == null)
            return false;

        if (objNetworkInfo.getState() == NetworkInfo.State.CONNECTED)
            return true;

        else if (objNetworkInfo.getState() == NetworkInfo.State.DISCONNECTED || objNetworkInfo.getState() == NetworkInfo.State.DISCONNECTING
                || objNetworkInfo.getState() == NetworkInfo.State.SUSPENDED || objNetworkInfo.getState() == NetworkInfo.State.UNKNOWN)
            return false;

        else
            return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onReceive(Context context, Intent intent)
    {
        boolean bAvailable = NetworkReachability();

        if(mbIsNetworkAvailable == bAvailable)
            return;

        mbIsNetworkAvailable = bAvailable;
        miNetwork.OnNetworkReachability(bAvailable);
    }
}
