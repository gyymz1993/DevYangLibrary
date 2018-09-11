package com.utils.gyymz;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;

import com.lsjr.net.R;
import com.utils.gyymz.base.BaseAppCompatActivity;
import com.utils.gyymz.comhttp.DcodeService;
import com.utils.gyymz.exception.GlobalExceptionHandler;
import com.utils.gyymz.listener.UserSensorEventListener;
import com.utils.gyymz.utils.SpUtils;
import com.utils.gyymz.utils.UIUtils;

public class BaseApplicationCompat {
    private static Looper mMainThreadLooper = null;
    private static Handler mMainThreadHandler = null;
    private static int mMainThreadId;
    private static Thread mMainThread = null;
    private static Application mApplication;
    private static BaseApplicationCompat mBaseApplication;
    private static boolean isInit = false;

    private BaseApplicationCompat() {
    }

    public static BaseApplicationCompat instance() {
        if (mBaseApplication == null) {
            synchronized (BaseApplicationCompat.class) {
                if (mApplication == null) {
                    mBaseApplication = new BaseApplicationCompat();
                }
            }
        }
        return mBaseApplication;
    }

    public static Application getApplication() {
        if (mApplication == null) {
            throw new NullPointerException("mApplication 为空");
        }
        return mApplication;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public void initialize(Application application) {
        if (isInit) {
            return;
        }
        mApplication = application;
        if (mApplication != null) {
            mMainThreadLooper = mApplication.getMainLooper();
            mMainThreadHandler = new Handler();
            mMainThreadId = android.os.Process.myTid();
            mMainThread = Thread.currentThread();
            initImageLoader();
            isInit = true;
        }
        initSputils();
        DcodeService.initialize(application);
        //异常捕获
        GlobalExceptionHandler.getInstance().init(getApplication(), UIUtils.getString(R.string.app_name));
    }


    private void initImageLoader() {
    }

    /**
     * 检测内存泄露
     */
    public void initLeakCanary() {
    }

    private void initSputils() {
        SpUtils.getInstance().init(mApplication);
    }

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;
    private UserSensorEventListener mUserSensorEventListener;
    private BaseAppCompatActivity baseAppCompatActivity;

    public void setStudio(BaseAppCompatActivity activity, UserSensorEventListener userSensorEventListener) {
        this.mUserSensorEventListener = userSensorEventListener;
        this.baseAppCompatActivity = activity;
        //获取 SensorManager 负责管理传感器
        mSensorManager = ((SensorManager) activity.getSystemService(activity.SENSOR_SERVICE));
        if (mSensorManager != null) {
            //获取加速度传感器
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null) {
                mSensorManager.registerListener(sensorEventListener, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }

    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int type = event.sensor.getType();

            if (type == Sensor.TYPE_ACCELEROMETER) {
                //获取三个方向值
                float[] values = event.values;
                float x = values[0];
                float y = values[1];
                float z = values[2];
                if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math
                        .abs(z) > 17)) {
                    if (mUserSensorEventListener != null) {
                        mUserSensorEventListener.userOnSensorEventListener();
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void showChangeStudio() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getApplication());
        alert.setTitle("提示").setMessage("更换了服务器地址需要重启App" + "\n" + "(下次进入为未登录状态)").setPositiveButton("还是重启",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reStartApp();
                    }
                }).setNegativeButton("重启", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // reStartApp();
            }
        });
        alert.create();
        alert.show();
    }


//    private void reStartApp() {
//        Intent intent = new Intent(ServiceAddressActivity.this, SplashActivity.class);
//        PendingIntent restartIntent = PendingIntent.getActivity(ServiceAddressActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager mgr = (AlarmManager) ServiceAddressActivity.this.getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 50, restartIntent); // 1秒钟后重启应用
//
//        ActivityUtils.removeAllActivity();
//        android.os.Process.killProcess(android.os.Process.myPid());
//        finish();
//    }


    protected void onDestory() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(sensorEventListener);
        }
    }

}


