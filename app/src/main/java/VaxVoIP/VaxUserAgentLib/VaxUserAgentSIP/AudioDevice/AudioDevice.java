package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice;

import android.content.Context;
import android.os.Looper;

public class AudioDevice extends Thread
{
    private Context mContext;

    public AudioDevice(Context objContext)
    {
        mContext = objContext;

        this.start();
    }

    public void run()
    {
        Looper.prepare();

        AudioDeviceSys ObjAudioDeviceSys = new AudioDeviceSys(mContext);

        Looper.loop();
    }
}
