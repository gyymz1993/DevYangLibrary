package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreRingtones;

public class RingtonesDialog extends DialogFragment
{
    final CharSequence[] RingtonesDialogOptions = {"Groovy", "Digital Rain", "Magical", "Deja Vu", "Office Phone"};
    int m_nSelectedRingToneId = -1;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle(R.string.choose_ringtone);
        objBuilder.setSingleChoiceItems(RingtonesDialogOptions, GetSelectedItem(), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int nRingToneId)
            {
                m_nSelectedRingToneId = nRingToneId;
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                    VaxPhoneSIP.m_objVaxVoIP.SetRingtone(m_nSelectedRingToneId);

                    StoreRingtones objStoreRingtones = new StoreRingtones();
                    objStoreRingtones.SetRingtone(m_nSelectedRingToneId);

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
        StoreRingtones objStoreRingtones = new StoreRingtones();
        m_nSelectedRingToneId = objStoreRingtones.GetRingtone();

        return m_nSelectedRingToneId;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////             VAXVOIP EVENTS             /////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialized()
    {
        StoreRingtones objStoreRingtones = new StoreRingtones();
        int nToneId = objStoreRingtones.GetRingtone();

        VaxPhoneSIP.m_objVaxVoIP.SetRingtone(nToneId);
    }
}
