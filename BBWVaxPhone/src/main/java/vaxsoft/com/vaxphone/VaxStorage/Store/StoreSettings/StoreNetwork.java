package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;

import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreNetwork
{
    private final String LOCAL_PORT_SIP = "VaxNetworkLocalPortSIP";
    private final String CHOOSE_RANDOMLY_SIP = "VaxNetworkChooseRandomlySIP";
    private final String LOCAL_PORT_RTP = "VaxNetworkLocalPortRTP";
    private final String CHOOSE_RANDOMLY_RTP = "VaxNetworkChooseRandomlyRTP";
    private final String AUTO_REACHABILITY = "VaxNetworkAutoReachability";

    private int LOCAL_PORT_SIP_DEFAULT_VALUE = 5060;
    private boolean CHOOSE_RANDOMLY_SIP_DEFAULT_VALUE = true;
    private int LOCAL_PORT_RTP_DEFAULT_VALUE = 8000;
    private boolean CHOOSE_RANDOMLY_RTP_DEFAULT_VALUE = true;
    private boolean AUTO_REACHABILITY_DEFAULT_VALUE = true;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public int GetLocalPortSIP()
    {
        return PreferenceUtil.ReadPreferenceValue(LOCAL_PORT_SIP, LOCAL_PORT_SIP_DEFAULT_VALUE);
    }

    public boolean IsChooseRandomlySIP()
    {
        return PreferenceUtil.ReadPreferenceValue(CHOOSE_RANDOMLY_SIP, CHOOSE_RANDOMLY_SIP_DEFAULT_VALUE);
    }

    public int GetLocalPortRTP()
    {
        return PreferenceUtil.ReadPreferenceValue(LOCAL_PORT_RTP, LOCAL_PORT_RTP_DEFAULT_VALUE);
    }

    public boolean IsChooseRandomlyRTP()
    {
        return PreferenceUtil.ReadPreferenceValue(CHOOSE_RANDOMLY_RTP, CHOOSE_RANDOMLY_RTP_DEFAULT_VALUE);
    }

    public boolean IsAutoReachability()
    {
        return PreferenceUtil.ReadPreferenceValue(AUTO_REACHABILITY, AUTO_REACHABILITY_DEFAULT_VALUE);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public void SetLocalPortSIP(int nPort)
    {
        PreferenceUtil.WritePreferenceValue(LOCAL_PORT_SIP, nPort);
    }

    public void SetChooseRandomlySIP(boolean bRandomly)
    {
        PreferenceUtil.WritePreferenceValue(CHOOSE_RANDOMLY_SIP, bRandomly);
    }

    public void SetLocalPortRTP(int nPort)
    {
        PreferenceUtil.WritePreferenceValue(LOCAL_PORT_RTP, nPort);
    }

    public void SetChooseRandomlyRTP(boolean bRandomly)
    {
        PreferenceUtil.WritePreferenceValue(CHOOSE_RANDOMLY_RTP, bRandomly);
    }

    public void SetAutoReachability(boolean bAutoReachability)
    {
        PreferenceUtil.WritePreferenceValue(AUTO_REACHABILITY, bAutoReachability);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

}
