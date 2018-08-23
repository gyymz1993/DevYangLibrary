package vaxsoft.com.vaxphone.PhoneSIP.DialRing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class DialRing
{
    private String m_sFilePath;

    public DialRing()
    {
        boolean bCreated = true;

        File AppDir = VaxPhoneAPP.getAppContext().getFilesDir();

        if (AppDir == null)
            return;

        File RingTonesDir = new File(AppDir, "RingTones");

        if (!RingTonesDir.exists())
            bCreated = RingTonesDir.mkdirs();

        if (bCreated)
        {
            File objFile = new File(RingTonesDir, "aDialRing.wav");
            m_sFilePath = objFile.getPath();

            if (objFile.exists())
                return;

            try
            {
                CopyFileToStorage();
            }
            catch (Exception ignored){}
        }

    }

    private void CopyFileToStorage() throws IOException
    {
        InputStream objInputStream = VaxPhoneAPP.getAppContext().getResources().openRawResource(R.raw.busy_ring);

        FileOutputStream objFileOutputStream = null;

        byte[] aBuffer = new byte[64000];

        try
        {
            objFileOutputStream = new FileOutputStream(m_sFilePath);

            while (true)
            {
                int nRead = objInputStream.read(aBuffer);
                if(nRead <= 0) break;

                objFileOutputStream.write(aBuffer, 0, nRead);
                if(nRead < aBuffer.length) break;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (objInputStream != null)
                objInputStream.close();

            if (objFileOutputStream != null)
                objFileOutputStream.close();
        }
    }

    public void Enable()
    {
        VaxPhoneSIP.m_objVaxVoIP.DialRingEnable(m_sFilePath);
    }
}
