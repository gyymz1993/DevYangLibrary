package vaxsoft.com.vaxphone.MainTab.CallTab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vaxsoft.com.vaxphone.CustomViews.TabFragment.CustomTabFragment;
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class CallTabFragment extends CustomTabFragment implements View.OnClickListener
{
    public static CallTabFragment mCallFragment = null;
    private static String m_sLastStatusText = "Account is online";

    TextView TextViewStatus;

    DeviceSurfaceView mDeviceSurfaceView;
    RemoteSurfaceView mRemoteSurfaceView;

    CallIconsFragment mIconsCallFragment = null;

    private RelativeLayout ParentView;

    @SuppressLint("StaticFieldLeak")

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_call, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        mCallFragment = this;
        mIconsCallFragment = new CallIconsFragment(getActivity(), view);

        LoadViewAll(view);
        ActivateClickListeners();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy()
    {
        mCallFragment = null;
        super.onDestroy();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void ActivateClickListeners()
    {
        mDeviceSurfaceView.setOnClickListener(this);
        ParentView.setOnClickListener(this);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void KeepScreenOn(boolean bEnable)
    {
        FragmentActivity objFragmentActivity = getActivity();

        if (objFragmentActivity != null)
        {
            Window objWindow = objFragmentActivity.getWindow();

            if (bEnable)
            {
                objWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                return;
            }

            objWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void AdjustDisplayFrameView()
    {
        if(VaxPhoneSIP.m_objVaxVoIP.IsVideoStreamStarted())
            mCallFragment.mDeviceSurfaceView.AdjustPreviewLayoutToThumbnail();
        else
            mCallFragment.mDeviceSurfaceView.AdjustPreviewLayoutToFullScreen();
    }

    public void OnFragmentActivated()
    {
        mCallFragment = this;

        KeepScreenOn(true);

        mIconsCallFragment.DisplayBtnAll();
        OpenVideoCamera();

        AdjustDisplayFrameView();

        super.OnFragmentActivated();
    }

    public void OnFragmentDeactivated()
    {
        KeepScreenOn(false);

        CloseVideoCamera();
        mCallFragment = null;

        super.OnFragmentDeactivated();
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void OpenVideoCamera()
    {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 3000);
            return;
        }

        VaxPhoneSIP.m_objVaxVoIP.OpenVideoDevice();
    }

    private void CloseVideoCamera()
    {
        if(!VaxPhoneSIP.m_objVaxVoIP.IsLineConnected())
            VaxPhoneSIP.m_objVaxVoIP.CloseVideoDevice();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == 3000)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                VaxPhoneSIP.m_objVaxVoIP.OpenVideoDevice();
            }
            else
            {
                DialogUtil.ShowDialog(getContext(), "Please allow to use camera for video call.");
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void LoadViewAll(View view)
    {
        ParentView = view.findViewById(R.id.ParentView);

        TextViewStatus = view.findViewById(R.id.label_status);
        TextViewStatus.setText(CallTabFragment.m_sLastStatusText);

        mDeviceSurfaceView = view.findViewById(R.id.DeviceSurfaceView);
        mRemoteSurfaceView = view.findViewById(R.id.SurfaceViewRemote);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void OnStatusText(String sMsg)
    {
        TextViewStatus.setText(sMsg);
    }

    private void OnDisconnectCall()
    {
        mIconsCallFragment.DisplayBtnAll();

        if (!mDeviceSurfaceView.isShown())
            mDeviceSurfaceView.setVisibility(View.VISIBLE);

        mRemoteSurfaceView.DrawColorOnRemoteSurfaceView();
    }

    private void OnDialCallStarted()
    {
        mIconsCallFragment.DisplayBtnAll();
    }

    private void OnInitialized()
    {
        mIconsCallFragment.DisplayBtnAll();
        OpenVideoCamera();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialized()
    {
        if (mCallFragment == null)
            return;

        mCallFragment.OnInitialized();
    }

    public static void PostVideoDeviceFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        if (mCallFragment == null)
            return;

        mCallFragment.mDeviceSurfaceView.DisplayFrameRGB(nDeviceId, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    public static void PostStatusText(String sMsg)
    {
        CallTabFragment.m_sLastStatusText = sMsg;

        if (mCallFragment == null)
            return;

        mCallFragment.OnStatusText(sMsg);
    }

    public static void PostDisconnectedCall()
    {
        if (mCallFragment == null)
            return;

        mCallFragment.OnDisconnectCall();
    }

    public static void PostOnVideoRemoteFrameRGB(int nLineNo, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        if (mCallFragment == null)
            return;

        mCallFragment.mRemoteSurfaceView.DisplayFrameRGB(nLineNo, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    public static void OnVideoRemoteStarted(int nLineNo)
    {
        if (mCallFragment == null)
            return;

        mCallFragment.AdjustDisplayFrameView();
    }

    public static void OnVideoRemoteEnded(int nLineNo)
    {
        if (mCallFragment == null)
            return;

        mCallFragment.AdjustDisplayFrameView();
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////

    public static void PostDialCallFailed(int nStatusCode, String sReasonPhrase)
    {

    }

    public static void PostConnectedCall()
    {
        if (mCallFragment == null)
            return;

        mCallFragment.OpenVideoCamera();
    }

    public static void PostDialCallStarted()
    {
        if (mCallFragment == null)
            return;

        mCallFragment.OnDialCallStarted();
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.DeviceSurfaceView:
                OnClickDeviceSurfaceView();
            break;

            case R.id.ParentView:
                OnClickParentView();
                break;
        }
    }

    private void OnClickParentView()
    {
        if (!mDeviceSurfaceView.isShown())
            mDeviceSurfaceView.setVisibility(View.VISIBLE);

        mIconsCallFragment.DisplayBtnAll();
    }

    private void OnClickDeviceSurfaceView()
    {
        if (!VaxPhoneSIP.m_objVaxVoIP.IsLineConnected())
        {
            mIconsCallFragment.DisplayBtnAll();
            return;
        }

        if (mDeviceSurfaceView.isShown())
            mDeviceSurfaceView.setVisibility(View.INVISIBLE);
        else
            mDeviceSurfaceView.setVisibility(View.VISIBLE);
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
}

