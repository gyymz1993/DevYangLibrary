package vaxsoft.com.vaxphone.PhoneSIP.BusyRing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class BusyRing
{
    private String m_sFilePath;

    public BusyRing()
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
            File objFile = new File(RingTonesDir, "aBusyRing.wav");
            m_sFilePath = objFile.getPath();

            if (objFile.exists())
                return;

            try
            {
                CopyFileToStorage();
            }
            catch (Exception ignored)
            {}
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
        VaxPhoneSIP.m_objVaxVoIP.BusyRingEnable(m_sFilePath);
    }

}

/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////

//public class BusyRing
//{
//    private String m_sFilePath;
//
//    public BusyRing()
//    {
//        boolean bCreated = true;
//
//        String sPath = Environment.getExternalStorageDirectory() + "/Android/data/" + VaxPhoneAPP.getAppContext().getPackageName() + "/Files";
////        String sPath = VaxPhoneAPP.getAppContext().getExternalFilesDir("files");
//
////        File objPkgDirectory = VaxPhoneAPP.getAppContext().getExternalFilesDir("Files");
//        File objPkgDirectory = new File(sPath);
//
//        if (objPkgDirectory == null)
//            return;
//
//        if (!objPkgDirectory.exists())
//        {
//            bCreated = objPkgDirectory.mkdirs();
//        }
//
//        m_sFilePath = Environment.getExternalStorageDirectory() + "/Android/data/" + VaxPhoneAPP.getAppContext().getPackageName() + "/Files/aBusyRing.wav";
//
//        if (bCreated)
//        {
//            File objFile = new File(objPkgDirectory, "aBusyRing.wav");
//            m_sFilePath = objFile.getPath();
//
//            if (objFile.exists())
//                return;
//
//            try
//            {
//                CopyFileToStorage();
//            }
//            catch (Exception ignored)
//            {}
//        }
//    }
//
//    private void CopyFileToStorage() throws IOException
//    {
//        InputStream objInputStream = VaxPhoneAPP.getAppContext().getResources().openRawResource(R.raw.busy_ring);
//
//        FileOutputStream objFileOutputStream = null;
//
//        byte[] aBuffer = new byte[1024];
//        int nRead;
//
//        try
//        {
//            objFileOutputStream = new FileOutputStream(m_sFilePath);
//
//            while ((nRead = objInputStream.read(aBuffer)) > 0)
//            {
//                objFileOutputStream.write(aBuffer, 0, nRead);
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//
//        finally
//        {
//            if (objInputStream != null)
//                objInputStream.close();
//
//            if (objFileOutputStream != null)
//                objFileOutputStream.close();
//        }
//    }
//
//    public void Enable()
//    {
//        VaxPhoneSIP.m_objVaxVoIP.BusyRingEnable(m_sFilePath);
//    }
//
//}

/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////

//public class BusyRing
//{
//    private String m_sFilePath;
//
//    public BusyRing()
//    {
//        boolean bCreated = true;
//
//        File AppDir = VaxPhoneAPP.getAppContext().getFilesDir();
//
//        if (AppDir == null)
//            return;
//
//        String sRingTones = AppDir.getPath() + "/RingTones";
//
//        File RingTonesDir = new File(AppDir.getAbsolutePath(), "RingTones");
//
//        if (!RingTonesDir.exists())
//            bCreated = RingTonesDir.mkdirs();
//
//        if (bCreated)
//        {
//            File objFile = new File(RingTonesDir, "aBusyRing.wav");
//            m_sFilePath = objFile.getPath();
//
//            if (objFile.exists())
//                return;
//
//            try
//            {
//                CopyFileToStorage();
//            }
//            catch (Exception ignored)
//            {}
//        }
//    }
//
//    private void CopyFileToStorage() throws IOException
//    {
//        InputStream objInputStream = VaxPhoneAPP.getAppContext().getResources().openRawResource(R.raw.busy_ring);
//
//        FileOutputStream objFileOutputStream = null;
//
//        byte[] aBuffer = new byte[1024];
//        int nRead;
//
//        try
//        {
//            objFileOutputStream = new FileOutputStream(m_sFilePath);
//
//            while ((nRead = objInputStream.read(aBuffer)) > 0)
//                objFileOutputStream.write(aBuffer, 0, nRead);
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//
//        finally
//        {
//            if (objInputStream != null)
//                objInputStream.close();
//
//            if (objFileOutputStream != null)
//                objFileOutputStream.close();
//        }
//    }
//
//    public void Enable()
//    {
//        VaxPhoneSIP.m_objVaxVoIP.BusyRingEnable(m_sFilePath);
//    }
//
//}
