package vaxsoft.com.vaxphone.MainNotify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;

import vaxsoft.com.vaxphone.MainTab.ChatTab.ChatContactTabFragment;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class VaxPhoneNotify extends ContextWrapper
{
    private Context mContext;
    private BroadcastReceiver mBroadcastReceiverCall = null;

    private NotificationManager mNotificationManager;

    private static final String PRIMARY_CHANNEL = "default";

    private static final String APP_ICON = "AppIcon";
    private static final String CALL_NOTIFICATION = "Call";
    private static final String MSG_NOTIFICATION = "Message";

    private static final int CALL_NOTIFICATION_ID = 1001;
    private static final int MSG_NOTIFICATION_ID = 1002;

    private static final int CALL_FRAGMENT_TAB_INDEX = 1;
    private static final int CHAT_FRAGMENT_TAB_INDEX = 2;

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public VaxPhoneNotify(Context base)
    {
        super(base);

        mContext = base;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void StartCallNotification(Class objClass, String sCallerName, String sCallerId)
    {
        ActionReceiverCall(true);

        PendingIntent objContentIntent = CreatePendingIntentActivity(objClass, CALL_FRAGMENT_TAB_INDEX);

        int nIcon  = GetSmallIcon(CALL_NOTIFICATION);
        Bitmap LargeIcon = GetLargeIcon(APP_ICON);

        Intent objIntent = new Intent();
        objIntent.setAction("VAX_NOTIFY_DISCONNECT_CALL");

        int nFlag = PendingIntent.FLAG_UPDATE_CURRENT;

        PendingIntent objPendingIntent = CreatePendingIntentBroadcast(objIntent, nFlag);

        Action objAction = new Action(R.drawable.ic_call_end, "End Call", objPendingIntent);

        Notification objNotification = CreateNotification(sCallerName, "Ongoing Call", nIcon, LargeIcon, objContentIntent, true, false,
                                                          Notification.PRIORITY_DEFAULT, true, -1, true, -1, objAction);

        StartForeground(CALL_NOTIFICATION_ID, objNotification);
    }

    public void StopCallNotification(boolean bRemoveNotification)
    {
        ActionReceiverCall(false);

        StopForeground(bRemoveNotification);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private PendingIntent CreatePendingIntentActivity(Class activity, int nSelectedTab)
    {
        Intent objIntent = new Intent(mContext, activity);
        objIntent.putExtra("SelectedTab", nSelectedTab);

        return PendingIntent.getActivity(mContext, 0, objIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private PendingIntent CreatePendingIntentBroadcast(Intent objIntent, int nFlag)
    {
        return PendingIntent.getBroadcast(mContext, 0, objIntent, nFlag);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void StartMsgNotification(String sUserName, String sMsgText, Class objClass)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel objNotificationChannel = new NotificationChannel(PRIMARY_CHANNEL, "Default-Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            objNotificationChannel.setDescription("VaxPhone Default Notifications");

            NotificationManager objNotificationManager = GetNotificationManager();

            if (objNotificationManager != null)
                objNotificationManager.createNotificationChannel(objNotificationChannel);
        }

        PendingIntent objPendingIntent = CreatePendingIntentActivity(objClass, CHAT_FRAGMENT_TAB_INDEX);

        int nIcon = GetSmallIcon(MSG_NOTIFICATION);
        Bitmap LargeIcon = GetLargeIcon(MSG_NOTIFICATION);

        int nMissedMsgCount = ChatContactTabFragment.ReadMissedCount() + 1;

        String sTitle = "Chat Messages ";

        String sMsg = "You have " + nMissedMsgCount + " new";
        String sContentText = nMissedMsgCount > 1 ? sMsg + " messages." : sMsg + " message.";

        Notification objNotification = CreateNotification(sTitle, sContentText, nIcon, LargeIcon, objPendingIntent, false, true,
                                                          Notification.PRIORITY_MAX, true, -1, false, Notification.DEFAULT_ALL,
                                                          null);

        ShowNotification(MSG_NOTIFICATION_ID, objNotification);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private void ActionReceiverCall(Boolean bActivate)
    {
        if(!bActivate)
        {
            if(mBroadcastReceiverCall == null)
                return;

            try
            {
                mContext.unregisterReceiver(mBroadcastReceiverCall);
            }
            catch(IllegalArgumentException e){}

            return;
        }

        mBroadcastReceiverCall = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                VaxPhoneSIP.m_objVaxVoIP.DisconnectCall();
            }
        };

        IntentFilter objIntentFilter = new IntentFilter("VAX_NOTIFY_DISCONNECT_CALL");
        mContext.registerReceiver(mBroadcastReceiverCall, objIntentFilter);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private Notification CreateNotification(String sContentTitle, String sContentText, int nSmallIcon, Bitmap LargeIcon, PendingIntent sContentIntent, boolean bOngoing,
                                            boolean bAutoCancel, int sPriority, boolean bSetShowWhen, long nSetWhen, boolean bUsesChronometer, int nSetDefaults, Action ActionPostv)
    {
        NotificationCompat.Builder objNotificationBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL);

        if (sContentTitle != null)
            objNotificationBuilder.setContentTitle(sContentTitle);

        if (sContentText != null)
            objNotificationBuilder.setContentText(sContentText);

        if (nSmallIcon != -1)
            objNotificationBuilder.setSmallIcon(nSmallIcon);

        if (LargeIcon != null)
            objNotificationBuilder.setLargeIcon(LargeIcon);

       // if (sTicker != null) //Sets the "ticker" text which is sent to accessibility services.
         //   objNotificationBuilder.setTicker(sTicker);

        if (sContentIntent != null)
            objNotificationBuilder.setContentIntent(sContentIntent);

        if (sPriority != -1)
            objNotificationBuilder.setPriority(sPriority);

        if (nSetWhen != -1) //Set the time that the event occurred.
            objNotificationBuilder.setWhen(nSetWhen);

        if (ActionPostv != null)
            objNotificationBuilder.addAction(ActionPostv);

        //if (ActionNegatv != null)
          //  objNotificationBuilder.addAction(ActionNegatv);

        objNotificationBuilder.setAutoCancel(bAutoCancel);
        objNotificationBuilder.setUsesChronometer(bUsesChronometer);
        objNotificationBuilder.setShowWhen(bSetShowWhen);
        objNotificationBuilder.setOngoing(bOngoing);
        objNotificationBuilder.setDefaults(nSetDefaults);
        objNotificationBuilder.setChannelId(PRIMARY_CHANNEL);

        return objNotificationBuilder.build();
    }

    private void ShowNotification(int nNotificationId, Notification objNotification)
    {
        NotificationManager objNotificationManager = GetNotificationManager();

        if (objNotificationManager != null)
            objNotificationManager.notify(nNotificationId, objNotification);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private void StartForeground(int nNotificationId, Notification objNotification)
    {
        VaxPhoneSIP.StartForeground(nNotificationId, objNotification);
    }

    private void StopForeground(boolean bRemoveNotification)
    {
       VaxPhoneSIP.StopForeground(bRemoveNotification);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private NotificationManager GetNotificationManager()
    {
        if (mNotificationManager == null)
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        return mNotificationManager;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private int GetSmallIcon(String sAction)
    {
        int nIcon = -1;

        switch (sAction)
        {
            case CALL_NOTIFICATION:
                nIcon = R.drawable.ic_call;
                break;

            case MSG_NOTIFICATION:
                nIcon = R.drawable.ic_chat;
                break;
        }

        return nIcon;
    }

    private Bitmap GetLargeIcon(String sAction)
    {
        Bitmap bitmap = null;

        switch (sAction)
        {
            case CALL_NOTIFICATION:
                bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);
                break;

            case APP_ICON:
                bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);
                break;

            case MSG_NOTIFICATION:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                break;
        }

        return bitmap;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
