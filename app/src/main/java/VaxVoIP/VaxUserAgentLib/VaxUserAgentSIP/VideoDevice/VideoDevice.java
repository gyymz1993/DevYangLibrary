package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.VideoDevice;

import android.content.Context;
import android.os.Looper;

public class VideoDevice extends Thread
{
    private Context mContext;

    public VideoDevice(Context objContext)
    {
        mContext = objContext;

        this.start();
    }

    public void run()
    {
        Looper.prepare();

        VideoDeviceSys ObjVideoDeviceSys = new VideoDeviceSys(mContext);

        Looper.loop();
    }
}