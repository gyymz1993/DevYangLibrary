package vaxsoft.com.vaxphone.VaxStorage.Store;

import java.util.concurrent.atomic.AtomicBoolean;

import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreLoginInfo
{
    private final String USERNAME = "VaxStoreLoginInfo_Username";
    private final String DISPLAY_NAME = "VaxStoreLoginInfo_DisplayName";
    private final String AUTH_LOGIN = "VaxStoreLoginInfo_AuthLogin";
    private final String PASSWORD = "VaxStoreLoginInfo_Password";
    private final String DOMAIN_REALM = "VaxStoreLoginInfo_DomainRealm";
    private final String SERVER_IP = "VaxStoreLoginInfo_Server_IP";
    private final String SERVER_PORT = "VaxStoreLoginInfo_Server_Port";
    private final String SIP_REGISTRATION = "VaxStoreLoginInfo_Registration_SIP";

    private String USERNAME_DEFAULT_VALUE = "";
    private String DISPLAY_NAME_DEFAULT_VALUE = "";
    private String AUTH_LOGIN_DEFAULT_VALUE = "";
    private String PASSWORD_DEFAULT_VALUE = "";
    private String DOMAIN_REALM_DEFAULT_VALUE = "";
    private String SERVER_IP_DEFAULT_VALUE = "";
    private String SERVER_PORT_DEFAULT_VALUE = "5060";
    private boolean SIP_REGISTRATION_DEFAULT_VALUE = true;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void GetLoginInfo(StringBuilder sUsername, StringBuilder sDisplayName, StringBuilder sAuthLogin, StringBuilder sAuthPassword, StringBuilder sDoaminRealm,
                             StringBuilder sServerIP, StringBuilder sServerPort, AtomicBoolean bRegistrationSIP)
    {
        if (sUsername != null)
            sUsername.append(PreferenceUtil.ReadPreferenceValue(USERNAME, USERNAME_DEFAULT_VALUE));

        if (sDisplayName != null)
        sDisplayName.append(PreferenceUtil.ReadPreferenceValue(DISPLAY_NAME, DISPLAY_NAME_DEFAULT_VALUE));

        if (sAuthLogin != null)
            sAuthLogin.append(PreferenceUtil.ReadPreferenceValue(AUTH_LOGIN, AUTH_LOGIN_DEFAULT_VALUE));

        if (sAuthPassword != null)
            sAuthPassword.append(PreferenceUtil.ReadPreferenceValue(PASSWORD, PASSWORD_DEFAULT_VALUE));

        if (sDoaminRealm != null)
            sDoaminRealm.append(PreferenceUtil.ReadPreferenceValue(DOMAIN_REALM, DOMAIN_REALM_DEFAULT_VALUE));

        if (sServerIP != null)
            sServerIP.append(PreferenceUtil.ReadPreferenceValue(SERVER_IP, SERVER_IP_DEFAULT_VALUE));

        if (sServerPort != null)
            sServerPort.append(PreferenceUtil.ReadPreferenceValue(SERVER_PORT, SERVER_PORT_DEFAULT_VALUE));

        if (bRegistrationSIP != null)
            bRegistrationSIP.set(PreferenceUtil.ReadPreferenceValue(SIP_REGISTRATION, SIP_REGISTRATION_DEFAULT_VALUE));
    }

    public void SetLoginInfo(String sUsername, String sDisplayName, String sAuthLogin, String sAuthPassword, String sDoaminRealm, String sServerIP, String sServerPort, boolean bRegistrationSIP)
    {
        if (sUsername != null)
             PreferenceUtil.WritePreferenceValue(USERNAME, sUsername);

        if (sDisplayName != null)
            PreferenceUtil.WritePreferenceValue(DISPLAY_NAME, sDisplayName);

        if (sAuthLogin != null)
            PreferenceUtil.WritePreferenceValue(AUTH_LOGIN, sAuthLogin);

        if (sAuthPassword != null)
            PreferenceUtil.WritePreferenceValue(PASSWORD, sAuthPassword);

        if (sDoaminRealm != null)
            PreferenceUtil.WritePreferenceValue(DOMAIN_REALM, sDoaminRealm);

        if (sServerIP != null)
            PreferenceUtil.WritePreferenceValue(SERVER_IP, sServerIP);

        if (sServerPort != null)
            PreferenceUtil.WritePreferenceValue(SERVER_PORT, sServerPort);

        PreferenceUtil.WritePreferenceValue(SIP_REGISTRATION, bRegistrationSIP);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
