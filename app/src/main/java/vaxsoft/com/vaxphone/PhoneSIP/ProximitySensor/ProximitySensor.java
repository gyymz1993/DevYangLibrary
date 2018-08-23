package vaxsoft.com.vaxphone.PhoneSIP.ProximitySensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

import static android.content.Context.POWER_SERVICE;

public class ProximitySensor implements SensorEventListener
{
    private SensorManager mSensorManager;
    private PowerManager.WakeLock mWakeLock;

    public ProximitySensor()
    {
        mSensorManager = null;
        mWakeLock = null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    public void SetProximityMonitoringEnabled(boolean bEnabled)
    {
        if (bEnabled)
        {
            RegisterSensor();
            return;
        }

        UnRegisterSensor();
    }

    private void RegisterSensor()
    {
        Sensor mProximity;

        mSensorManager = (SensorManager) VaxPhoneAPP.getAppContext().getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager != null)
        {
            mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);

            PowerManager objPowerManager = (PowerManager) VaxPhoneAPP.getAppContext().getSystemService(POWER_SERVICE);
            if (objPowerManager != null)
                mWakeLock = objPowerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "VaxPhoneSIP");
        }
    }

    private void UnRegisterSensor()
    {
        if (mSensorManager != null)
            mSensorManager.unregisterListener(this);
    }

    //////////////////////////////////////////////////////////////////////////////////
    ///////////////////////       SENSOR EVENTS         //////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        float fDistance = sensorEvent.values[0];

        if (fDistance < 1.0)
        {
            if(!mWakeLock.isHeld())
                mWakeLock.acquire(10*60*1000L);
        }
        else
        {
            if(mWakeLock.isHeld())
                mWakeLock.release();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
}
