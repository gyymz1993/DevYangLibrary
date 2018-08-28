package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.VideoDevice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

class VideoDeviceSys extends VideoDeviceSO
{
    private CameraDevice mCameraDevice;

    private ImageReader mImageReader;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private CaptureRequest mPreviewRequest;
    private CameraCaptureSession mCameraCaptureSession;

    private Context mContext;
    private Timer mTickFrameRate = null;

    private int m_nOrientation = 0;
    private int m_nDeviceId = 0;

    private int m_nFrameRatePosted = 15;
    private int m_nFrameRateCalc = 0;
    private int m_nFrameRateMax = 0;
    private int m_nShowNextFrameNo = 0;

    private Boolean m_bFrameSkip = false;
    private Boolean m_bFrameSkipAll = true;

    private double m_fCalcShowFrameNo = 0.0;
    private double m_fCalcShowFrameCount = 0.0;

    ///////////////////////////////////////////////////////////////////////////////////

    VideoDeviceSys(Context context)
    {
        mContext = context;
    }

    @SuppressLint("MissingPermission")
    public boolean OpenDevice(int nDeviceId, int nQuality)
    {
        CloseDevice();

        String sCameraId = GetCameraHardwareId(nDeviceId);
        if(sCameraId.length() == 0) return false;

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }

        int[] aWidth  = {176, 352, 640, 1280, 1920};
        int[] aHeight = {144, 288, 480, 720, 1080};

        int nWidth = aWidth[nQuality];
        int nHeight = aHeight[nQuality];

        m_nDeviceId = nDeviceId;
        m_nOrientation = GetOrientation();

        SetUpCameraOutput(nWidth, nHeight);

        try
        {
            CameraManager objCamMgr = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
            objCamMgr.openCamera(sCameraId, mStateCallback, null);

        }
        catch (CameraAccessException e)
        {
            return false;
        }

        m_nFrameRatePosted = 15;
        m_nFrameRateMax = 0;
        m_nFrameRateCalc = 0;

        m_nShowNextFrameNo = 0;
        m_fCalcShowFrameNo = 0.0;
        m_fCalcShowFrameCount = 0.0;

        m_bFrameSkip = false;
        m_bFrameSkipAll = true;

        mTickFrameRate = new Timer();
        mTickFrameRate.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                OnTimerTick();
            }
        }, 1200, 1000);

        return true;
    }

    public void CloseDevice()
    {
        m_bFrameSkipAll = true;

        if(mTickFrameRate != null)
        {
            mTickFrameRate.cancel();
            mTickFrameRate = null;
        }

        if(mCameraCaptureSession != null)
        {
            mCameraCaptureSession.close();
            mCameraCaptureSession = null;
        }

        if(mCameraDevice != null)
        {
            mCameraDevice.close();
            mCameraDevice = null;
        }

        if(mImageReader != null)
        {
            mImageReader.close();
            mImageReader = null;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private int GetOrientation()
    {
        String sCameraId = GetCameraHardwareId(m_nDeviceId);
        if(sCameraId.length() == 0) return 0;

        CameraManager objCamMgr = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        CameraCharacteristics objCamCharc = null;

        try
        {
            objCamCharc = objCamMgr.getCameraCharacteristics(sCameraId);
        }
        catch (CameraAccessException e)
        {
            return 0;
        }

        int nOrientation = objCamCharc.get(CameraCharacteristics.SENSOR_ORIENTATION);

        if(objCamCharc.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT)
        {
            if(nOrientation == 90)  // Problem on some old devices.
            {
                nOrientation = 270;
            }
        }

        return nOrientation;
    }

    private void SetUpCameraOutput(int nWidth, int nHeight)
    {
        try
        {
            mImageReader = ImageReader.newInstance(nWidth, nHeight, ImageFormat.YUV_420_888, 1);
            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private final int VAX_FRAME_RATE = 14;

    private void OnTimerTick()
    {
        m_bFrameSkipAll = false;

        if(m_nFrameRateCalc > 0)
            m_nFrameRatePosted = m_nFrameRateCalc;

        //Log.i("VaxLog", "Max FrameRate: " + m_nFrameRateMax);
        //Log.i("VaxLog", "Posted FrameRate: " + m_nFrameRatePosted);

        if(m_nFrameRateMax <= VAX_FRAME_RATE)
        {
            m_fCalcShowFrameNo = 0.0;
            m_bFrameSkip = false;
        }
        else
        {
            m_fCalcShowFrameNo = 0.0;
            m_fCalcShowFrameCount = (double) m_nFrameRateMax / VAX_FRAME_RATE;

            m_bFrameSkip = true;
        }

        m_nShowNextFrameNo = 0;
        m_nFrameRateCalc = 0;
        m_nFrameRateMax = 0;

    }

    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener()
    {
        @Override
        public void onImageAvailable(ImageReader reader)
        {
            Image objImage = reader.acquireLatestImage();

            if (objImage == null)
                return;

            if(m_bFrameSkipAll)
            {
                objImage.close();
                return;
            }

            /////////////////////////////////////////////////////////////////

            int nFrameNo = m_nFrameRateMax;
            m_nFrameRateMax++;

            //Log.i("SysVideoDevice", "Show FrameNo: " + m_nShowNextFrameNo);

            if((m_bFrameSkip == true) && (m_nShowNextFrameNo != nFrameNo))
            {
                objImage.close();
                return;
            }

            m_nFrameRateCalc++;

            m_fCalcShowFrameNo = m_fCalcShowFrameNo + m_fCalcShowFrameCount;
            m_nShowNextFrameNo = (int) Math.floor(m_fCalcShowFrameNo);

            /////////////////////////////////////////////////////////////////

            Image.Plane[] aPlanes = objImage.getPlanes();

            int nWidth = objImage.getWidth();
            int nHeight = objImage.getHeight();

            ByteBuffer objBuffDataY = aPlanes[0].getBuffer();
            int nRowStrideY = aPlanes[0].getRowStride();
            int nSizeY = objBuffDataY.remaining();
            byte[] aBuffY = new byte[nSizeY];
            objBuffDataY.get(aBuffY);

            ByteBuffer objBuffDataU = aPlanes[1].getBuffer();
            int nRowStrideUV = aPlanes[1].getRowStride();
            int nPixelStrideUV = aPlanes[1].getPixelStride();
            int nSizeUV = objBuffDataU.remaining();
            byte[] aBuffU = new byte[nSizeUV];
            objBuffDataU.get(aBuffU);

            ByteBuffer objBuffDataV = aPlanes[2].getBuffer();
            int nSizeV = objBuffDataV.remaining();
            byte[] aBuffV = new byte[nSizeV];
            objBuffDataV.get(aBuffV);

            OnVideoDeviceFrame(m_nDeviceId, m_nFrameRatePosted, m_nOrientation, aBuffY, aBuffU, aBuffV, nSizeY, nSizeUV, nRowStrideY, nRowStrideUV, nPixelStrideUV, nWidth, nHeight);

            objImage.close();
        }
    };

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void CameraPreviewSession()
    {
        try
        {
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mPreviewRequestBuilder.addTarget(mImageReader.getSurface());

            mCameraDevice.createCaptureSession(Collections.singletonList(mImageReader.getSurface()), new CameraCaptureSession.StateCallback()
                    {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession)
                        {
                            if (mCameraDevice == null)
                                return;

                            mCameraCaptureSession = cameraCaptureSession;

                            try
                            {
                                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

                                mPreviewRequest = mPreviewRequestBuilder.build();

                                mCameraCaptureSession.setRepeatingRequest(mPreviewRequest, null , null);
                            }
                            catch (CameraAccessException e)
                            {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession)
                        {

                        }
                    }, null
            );
        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback()
    {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice)
        {
            mCameraDevice = cameraDevice;
            CameraPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice)
        {
            cameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i)
        {
            cameraDevice.close();
            mCameraDevice = null;
        }
    };

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private String GetCameraHardwareId(int nDeviceId)
    {
        String[] aDeviceIdList = GetDeviceIdList();
        if(aDeviceIdList == null) return "";

        int nDeviceTotal = aDeviceIdList.length;

        if(nDeviceId < 0 || nDeviceId >= nDeviceTotal)
            return "";

        return aDeviceIdList[nDeviceId];
    }

    private String[] GetDeviceIdList()
    {
        String[] aDeviceList = null;
        try
        {
            CameraManager objCamMgr = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
            aDeviceList = objCamMgr.getCameraIdList();
        }
        catch (CameraAccessException e)
        {
            return null;
        }

        return aDeviceList;
    }

    public int GetDeviceCount()
    {
        String[] aDeviceIdList = GetDeviceIdList();
        if(aDeviceIdList == null) return 0;

        return aDeviceIdList.length;
    }

    public String GetDeviceName(int nDeviceId)
    {
        String sCameraId = GetCameraHardwareId(nDeviceId);
        if(sCameraId.length() == 0) return "";

        CameraCharacteristics objCamCharc = null;

        try
        {
            CameraManager objCamMgr = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
            objCamCharc = objCamMgr.getCameraCharacteristics(sCameraId);
        }
        catch (CameraAccessException e)
        {
            return "";
        }

        if(objCamCharc.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT)
        {
            return "Front Camera";
        }

        return "Back Camera";
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected boolean OnEventOpenVideoDevice(int nDeviceId, int nQuality)
    {
        return OpenDevice(nDeviceId, nQuality);
    }

    @Override
    protected void OnEventCloseVideoDevice()
    {
        CloseDevice();
    }

    @Override
    protected int OnEventGetVideoDeviceCount()
    {
        return GetDeviceCount();
    }

    @Override
    protected String OnEventGetVideoDeviceName(int nDeviceId)
    {
        return GetDeviceName(nDeviceId);
    }
}
