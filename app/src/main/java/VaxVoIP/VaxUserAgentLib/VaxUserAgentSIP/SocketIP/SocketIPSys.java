package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.SocketIP;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

class SocketIPSys extends SocketIPSO
{
    SocketIPSys()
    {
        m_sListIP = new ArrayList<>();
    }

    private List<String> m_sListIP;

    private void StartIP()
    {
        UpdateListIP();
    }

    private String NextIP()
    {
        if (m_sListIP.isEmpty())
            return "";

        String sIP =  m_sListIP.get(0);
        m_sListIP.remove(0);

        return sIP;
    }


    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////

    private void UpdateListIP()
    {
        m_sListIP.clear();

        try
        {

            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();

                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address)
                    {
                        m_sListIP.add(inetAddress.getHostAddress());
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            ex.printStackTrace();
        }
    }


    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////

    @Override
    protected void OnEventGetStartIP()
    {
        StartIP();
    }

    @Override
    protected String OnEventGetNextIP()
    {
        return NextIP();
    }
}
