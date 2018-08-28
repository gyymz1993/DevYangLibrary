package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.SocketIP;

import android.os.Looper;

public class SocketIP extends Thread
{
    public SocketIP()
    {
        this.start();

    }

    public void run()
    {
        Looper.prepare();

        SocketIPSys ObjSocketIPSys = new SocketIPSys();

        Looper.loop();
    }
}
