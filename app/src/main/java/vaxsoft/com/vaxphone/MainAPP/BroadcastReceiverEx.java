package vaxsoft.com.vaxphone.MainAPP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class BroadcastReceiverEx extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String sAction = intent.getAction();

        if (sAction == null)
            return;

        if (sAction.equals(Intent.ACTION_BOOT_COMPLETED))
        {
            VaxPhoneSIP.StartService();
        }

        else if (intent.getAction().equals("VaxPhoneSIP.RESTART_VAX_SERVICE"))
        {
            VaxPhoneSIP.StartService();
        }
    }
}
