package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Network;

import android.content.Context;
import android.os.Looper;

public class Network  extends Thread
{
    private Context mContext;

    public Network(Context objContext)
    {
        mContext = objContext;

        this.start();
    }

    public void run()
    {
        Looper.prepare();

        NetworkSys objNetworkSys = new NetworkSys(mContext);

        Looper.loop();
    }
}