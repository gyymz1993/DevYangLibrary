package com.gystar.master.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CheckService extends Service {
    private static final String PackageName = "com.gystar.master.service";
    private final Timer timerMail = new Timer();
    private ActivityManager activityManager = null;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        // mLogUtils.e("   开启检查服务" , 2);
        timerMail.schedule(new TimerTask() {
            @Override
            public void run() {
                //  mLogUtils.e("   发送消息", 2);
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        }, 15000, 5000);
        super.onCreate();
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                if (!isBackgroundRunning()) {
                    //Log.e("TAG", "   退出操作");
                    //logout();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    });

    private boolean isBackgroundRunning() {
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (activityManager == null) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> processList = activityManager.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : processList) {
            if (info.baseActivity.getPackageName().startsWith(PackageName)) {
                return true;
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        if (timerMail != null) {
            timerMail.cancel();
        }
        //mLogUtils.e("   销毁服务", 2);
        Log.e("TAG", "销毁服务");
        super.onDestroy();
    }

    public void logout() {
        Log.e("TAG", "退出操作");
    }
}