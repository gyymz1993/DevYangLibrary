package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreVideoQuality;

public class VideoQualityDialog extends DialogFragment
{
    final CharSequence[] VideoBitrateDialogOptions = {"Low", "Normal", "Medium", "High", "Maximum"};
    int m_nSelectedItem = -1;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle(R.string.choose_video_quality);
        objBuilder.setSingleChoiceItems(VideoBitrateDialogOptions, GetSelectedItem(), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                m_nSelectedItem = which;
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                StoreVideoQuality objStoreQuality = new StoreVideoQuality();
                objStoreQuality.SetVideoQuality(m_nSelectedItem);

                VaxPhoneSIP.m_objVaxVoIP.ApplyVideoQuality();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        return objBuilder.create();
    }

    private int GetSelectedItem()
    {
        m_nSelectedItem = VideoQualityDialog.GetVideoQuality();

        return m_nSelectedItem;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public static int GetVideoQuality()
    {
        StoreVideoQuality objStoreQuality = new StoreVideoQuality();
        return objStoreQuality.GetVideoQuality();
    }

}