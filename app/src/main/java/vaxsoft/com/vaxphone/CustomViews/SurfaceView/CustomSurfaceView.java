package vaxsoft.com.vaxphone.CustomViews.SurfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import vaxsoft.com.vaxphone.R;

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    private Context mContext;

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    private boolean m_bIsHolderAvailable = false;

    private int m_nFrameWidth = 0;
    private int m_nFrameHeight = 0;

    private Bitmap mDisplayBitmap = null;

    private Thread mThread = null;
    private Handler mThreadHandler = null;

    public CustomSurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        mContext = context;
        mSurfaceView = this;
        mSurfaceHolder = mSurfaceView.getHolder();

        mSurfaceHolder.addCallback(this);

        StartThread();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void DisplayFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        if(!m_bIsHolderAvailable)
            return;

        PostDisplayRGB(nDeviceId, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    private void DisplayRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        if(!m_bIsHolderAvailable)
            return;

        if(m_nFrameWidth != nFrameWidth || m_nFrameHeight != nFrameHeight)
        {
            mDisplayBitmap = Bitmap.createBitmap(nFrameWidth, nFrameHeight, Bitmap.Config.ARGB_8888);
        }

        Buffer objFrameSrc = ByteBuffer.wrap(pFrameRGB);
        mDisplayBitmap.copyPixelsFromBuffer(objFrameSrc);

        Canvas canvas = null;

        try
        {
            canvas = mSurfaceHolder.lockCanvas(null);

            RectF bitmapRect = new RectF(0, 0, mDisplayBitmap.getWidth(), mDisplayBitmap.getHeight());
            RectF SurfaceViewRect = new RectF(0, 0, mSurfaceView.getWidth(), mSurfaceView.getHeight());

            Matrix DisplayMatrix = new Matrix();
            DisplayMatrix.setRectToRect(bitmapRect, SurfaceViewRect, Matrix.ScaleToFit.FILL);

            canvas.drawBitmap(mDisplayBitmap , DisplayMatrix , null);
        }
        catch (Exception ignored)
        {

        }
        finally
        {
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void DrawColorOnRemoteSurfaceView()
    {
        int nColor = mContext.getResources().getColor(R.color.GrayColor);

        Canvas canvas = mSurfaceHolder.lockCanvas(null);

        try
        {
            canvas.drawColor(nColor, PorterDuff.Mode.SRC_IN );
        }
        catch (Exception ignored)
        {

        }
        finally
        {
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void AdjustPreviewLayoutToThumbnail()
    {
        int nWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, mContext.getResources().getDisplayMetrics());
        int nHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130, mContext.getResources().getDisplayMetrics());

        RelativeLayout.LayoutParams objLayoutParams = (RelativeLayout.LayoutParams) mSurfaceView.getLayoutParams();

        objLayoutParams.width = nWidth;
        objLayoutParams.height = nHeight;

        objLayoutParams.removeRule(RelativeLayout.BELOW);
        objLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mSurfaceView.setLayoutParams(objLayoutParams);
    }

    public void AdjustPreviewLayoutToFullScreen()
    {
        RelativeLayout.LayoutParams objLayoutParams = (RelativeLayout.LayoutParams) mSurfaceView.getLayoutParams();

        objLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        objLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        objLayoutParams.addRule(RelativeLayout.BELOW, R.id.label_status);

        mSurfaceView.setLayoutParams(objLayoutParams);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        m_bIsHolderAvailable = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        m_bIsHolderAvailable = false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private class DataRGB implements Runnable
    {
        public int m_nDeviceId;
        public byte[] m_aFrameRGB;
        public int m_nFrameSize;
        public int m_nFrameWidth;
        public int m_nFrameHeight;

        public void run()
        {
            DisplayRGB(m_nDeviceId, m_aFrameRGB, m_nFrameSize, m_nFrameWidth, m_nFrameHeight);
        }
    }

    private void StartThread()
    {
        mThread = new Thread(new Runnable()
        {
            public void run()
            {
                Looper.prepare();

                mThreadHandler = new Handler() {
                    public void handleMessage(Message msg) {

                    }
                };

                Looper.loop();
            };
        });

        mThread.start();
    }

    private void PostDisplayRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        DataRGB objDataRGB = new DataRGB();

        objDataRGB.m_nDeviceId = nDeviceId;
        objDataRGB.m_aFrameRGB = aFrameRGB;
        objDataRGB.m_nFrameSize = nFrameSize;
        objDataRGB.m_nFrameWidth = nFrameWidth;
        objDataRGB.m_nFrameHeight = nFrameHeight;

        mThreadHandler.post(objDataRGB);
    }
}
