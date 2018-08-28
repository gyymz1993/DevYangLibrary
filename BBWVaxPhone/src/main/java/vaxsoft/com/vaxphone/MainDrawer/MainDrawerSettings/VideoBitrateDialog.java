package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreVideoBitrate;

public class VideoBitrateDialog extends DialogFragment
{
    final CharSequence[] VideoBitrateDialogOptions = {"Low", "Normal", "Medium", "High", "Maximum"};
    int m_nSelectedItem = -1;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle(R.string.choose_video_bitrate);
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
                StoreVideoBitrate objStoreBitrate = new StoreVideoBitrate();
                objStoreBitrate.SetVideoBitrate(m_nSelectedItem);

                VaxPhoneSIP.m_objVaxVoIP.ApplyVideoBitrate(m_nSelectedItem);
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
        m_nSelectedItem =  new StoreVideoBitrate().GetVideoBitrate();

        return m_nSelectedItem;
    }

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    public static void PostInitialized()
    {
        StoreVideoBitrate objStoreBitrate = new StoreVideoBitrate();
        int nQualityBitrate = objStoreBitrate.GetVideoBitrate();

        VaxPhoneSIP.m_objVaxVoIP.ApplyVideoBitrate(nQualityBitrate);
    }
}