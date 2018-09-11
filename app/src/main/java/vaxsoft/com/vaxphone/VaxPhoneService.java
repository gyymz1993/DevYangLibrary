package vaxsoft.com.vaxphone;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class VaxPhoneService extends Service
{
    public static Boolean mServiceStarted = false;
    public static VaxPhoneService m_objVaxService = null;
    public static Boolean m_bForegroundService = false;

    public VaxPhoneService()
    {
        m_objVaxService = this;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public static void StartForeground(int nNotificationId, Notification objNotification)
    {
        m_bForegroundService = true;
        m_objVaxService.startForeground(nNotificationId, objNotification);
    }

    public static void StopForeground(boolean bRemoveNotification)
    {
        m_bForegroundService = false;
        VaxPhoneService.StartService();
        m_objVaxService.stopForeground(bRemoveNotification);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public static void RestartService()
    {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) // Not supported Android 8.0 and above
            return;

        if(m_bForegroundService)
            return;

        Context objContext = VaxPhoneAPP.getAppContext();

        Intent broadcastIntent = new Intent("VaxPhoneSIP.RESTART_VAX_SERVICE");
        objContext.sendBroadcast(broadcastIntent);
    }

    public static void StartService()
    {
        Context objContext = VaxPhoneAPP.getAppContext();
        Intent objIntent = new Intent(objContext, VaxPhoneSIP.class);

        objContext.startService(objIntent);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        if(!mServiceStarted)
        {
            mServiceStarted = true;
            OnServiceStarted();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);

        if(!mServiceStarted)
        {
            mServiceStarted = true;
            OnServiceStarted();
        }

        return START_STICKY;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    protected void OnServiceStarted()
    {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /*
    private static boolean isServiceRunning()
    {
        Context objContext = VaxPhoneAPP.getAppContext();
        ActivityManager manager = (ActivityManager) objContext.getSystemService(objContext.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            String sClassName = ".VaxPhoneSIP";

            //Log.e ("Vax-Service", service.service.getClassName());

            if (service.service.getClassName().contains(sClassName))
            {
                Log.e ("Vax-Service", "Yes: MyServiceRunning");
                return true;
            }
        }
        Log.e ("Vax-Service", "No: MyServiceRunning");
        return false;
    }

    public static boolean isServiceRunningInForeground()
    {
        Context objContext = VaxPhoneAPP.getAppContext();
        ActivityManager manager = (ActivityManager) objContext.getSystemService(objContext.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            String sClassName = ".VaxPhoneSIP";

            if (service.service.getClassName().contains(sClassName))
            {
                if(service.foreground)
                {
                    Log.e("Vax-Service", "Yes: Foreground");
                }
                else
                {
                    Log.e ("Vax-Service", "No: Foreground");
                }

                return service.foreground;
            }
        }

        Log.e ("Vax-Service", "Not exist or running");

        return false;
    }
    */
}
