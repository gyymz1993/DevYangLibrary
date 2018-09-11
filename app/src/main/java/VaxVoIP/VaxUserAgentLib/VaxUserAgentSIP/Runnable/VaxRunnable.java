package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Runnable;

import android.os.Handler;
import android.os.Looper;

public class VaxRunnable
{
    private Handler m_hLooper = null;

    public VaxRunnable()
    {
        m_hLooper = new Handler(Looper.myLooper());
    }

    private class RunnableMsg implements Runnable
    {
        Runnable m_wParam = null;
        Boolean m_bWait;

        @Override
        public void run()
        {
            m_wParam.run();

            if(m_bWait)
                RunnableNotify(this);
        }
    }

    public void PostRunnableMsg(Runnable wParam, Boolean bWait)
    {
        RunnableMsg objRunnableMsg = new RunnableMsg();

        objRunnableMsg.m_wParam = wParam;
        objRunnableMsg.m_bWait = bWait;

        m_hLooper.post(objRunnableMsg);

        if(bWait)
            RunnableWait(objRunnableMsg);
    }

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    private void RunnableNotify(Runnable runnable)
    {
        Sleep(50);

        synchronized(runnable)
        {
            runnable.notify();
        }
    }

    private void RunnableWait(Runnable runnable)
    {
        try
        {
            synchronized(runnable)
            {
                runnable.wait();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void Sleep(int nMilliSec)
    {
        try
        {
            Thread.sleep(nMilliSec);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
