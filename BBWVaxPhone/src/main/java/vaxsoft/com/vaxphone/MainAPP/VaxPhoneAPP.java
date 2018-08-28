package vaxsoft.com.vaxphone.MainAPP;

import android.app.Application;
import android.content.Context;

import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class VaxPhoneAPP extends Application
{
    private static Context m_objContext = null;
    private VaxPhoneSIP m_objVaxPhoneSIP = null;

    public void onCreate()
    {
        super.onCreate();

        VaxPhoneAPP.m_objContext = getApplicationContext();
        m_objVaxPhoneSIP = new VaxPhoneSIP();
    }

    public static Context getAppContext()
    {
        return VaxPhoneAPP.m_objContext;
    }
}
