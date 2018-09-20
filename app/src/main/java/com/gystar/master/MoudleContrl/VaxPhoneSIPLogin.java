package com.gystar.master.MoudleContrl;

import android.content.Context;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class VaxPhoneSIPLogin {

    protected static String sAuthLogin = "119001";
    protected static String sAuthPwd = "whgyw@2018";
    protected static String sServerAddr = "59.175.150.29:5060";
    private static Context mContext;

    public static void login(Context context) {
        mContext = context;
        autoLogin();
    }

    private static void autoLogin() {
        UpdateLogInInfo();
        Initialize();
        UpdateDrawerUI();
        if (VaxPhoneSIP.m_objVaxVoIP.IsOnline()) {
            Log.e("TAG", "登陆成功");
        } else {
            Log.e("TAG", "登陆失败");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    public static void UpdateDrawerUI() {
        StringBuilder sAuthLogin = new StringBuilder();
        StringBuilder sDoaminRealm = new StringBuilder();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(null, null, sAuthLogin, null, sDoaminRealm, null, null, null);
    }

    private static void UpdateLogInInfo() {
        int max = 45;
        int min = 1;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        if (s < 10) {
            sAuthLogin = "11900" + s;
        } else {
            sAuthLogin = "1190" + s;
        }
        Log.e("TAG", "登陆账号" + sAuthLogin);
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, null, DomainRealm, null, null, null);
        String sServerIP;
        String sServerPort;
        if (sServerAddr.contains(":")) {
            String aServerAddr[] = sServerAddr.split(":");
            sServerIP = aServerAddr[0];
            sServerPort = aServerAddr[aServerAddr.length - 1];
        } else {
            sServerIP = sServerAddr;
            sServerPort = "5060";
        }
        boolean bRegistrationSIP = true;
        if (!AuthLogin.toString().equals(sAuthLogin)) {
            Username.setLength(0);
            Username.append(sAuthLogin);
            DisplayName.setLength(0);
            DisplayName.append(sAuthLogin);
            DomainRealm.setLength(0);
            DomainRealm.append(sServerIP);
        }
        VaxPhoneSIP.m_objVaxVoIP.SetLoginInfo(Username.toString(), DisplayName.toString(), sAuthLogin, sAuthPwd, DomainRealm.toString(), sServerIP, sServerPort, bRegistrationSIP);
    }


    private static boolean Initialize() {
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder AuthPassword = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();
        StringBuilder ServerIP = new StringBuilder();
        StringBuilder ServerPort = new StringBuilder();
        AtomicBoolean RegistrationSIP = new AtomicBoolean();
        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, AuthPassword, DomainRealm, ServerIP, ServerPort, RegistrationSIP);
        String sUsername = Username.toString();
        String sDisplayName = DisplayName.toString();
        String sAuthLogin = AuthLogin.toString();
        String sAuthPassword = AuthPassword.toString();
        String sDomainRealm = DomainRealm.toString();
        String sServerIP = ServerIP.toString();
        int nServerPort = Integer.parseInt(ServerPort.toString());
        boolean bRegistrationSIP = RegistrationSIP.get();
        if (!VaxPhoneSIP.m_objVaxVoIP.Initialize(sDisplayName, sUsername, sAuthLogin, sAuthPassword, sDomainRealm, sServerIP,
                nServerPort, bRegistrationSIP)) {
            return false;
        }
        if (!bRegistrationSIP) {
            VaxPhoneSIP.m_objVaxVoIP.IsLineConnected();
        }
        return true;
    }


}
