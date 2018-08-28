package vaxsoft.com.vaxphone.MainAPP;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

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


        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            Log.d("app", " onViewInitFinished is " + arg0);
        }

        @Override
        public void onCoreInitFinished() {
        }
    };

    public static Context getAppContext()
    {
        return VaxPhoneAPP.m_objContext;
    }
}
