package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreCryptTunnel
{
    private final String TUNNEL_IP = "VaxCryptTunnelIP";
    private final String TUNNEL_PORT = "VaxCryptTunnelPort";
    private final String TUNNEL_ENABLE = "VaxCryptTunnelEnable";

    private String TUNNEL_IP_DEFAULT_VALUE = "";
    private String TUNNEL_PORT_DEFAULT_VALUE = "";
    private boolean TUNNEL_ENABLE_DEFAULT_VALUE = false;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void SetTunnelIP(String sIP)
    {
        PreferenceUtil.WritePreferenceValue(TUNNEL_IP, sIP);
    }

    public void SetTunnelPort(String sPort)
    {
        PreferenceUtil.WritePreferenceValue(TUNNEL_PORT, sPort);
    }

    public void SetTunnelEnable(boolean bTunnelEnable)
    {
         PreferenceUtil.WritePreferenceValue(TUNNEL_ENABLE, bTunnelEnable);
    }

    public String GetTunnelIP()
    {
        return PreferenceUtil.ReadPreferenceValue(TUNNEL_IP, TUNNEL_IP_DEFAULT_VALUE);
    }

    public String GetTunnelPort()
    {
        return PreferenceUtil.ReadPreferenceValue(TUNNEL_PORT, TUNNEL_PORT_DEFAULT_VALUE);
    }

    public boolean GetTunnelEnable()
    {
        return PreferenceUtil.ReadPreferenceValue(TUNNEL_ENABLE, TUNNEL_ENABLE_DEFAULT_VALUE);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

}
