package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreVoiceChanger;

public class VoiceChangerDialog extends DialogFragment
{
    public static final int VAX_VOICE_CHANGER_GRANDPA_DRUNK    = 0;
    public static final int VAX_VOICE_CHANGER_TEEN_BOY         = 1;
    public static final int VAX_VOICE_CHANGER_HOUSE_HOLD_REBOT = 2;
    public static final int VAX_VOICE_CHANGER_HELIUM_INHALED   = 3;
    public static final int VAX_VOICE_CHANGER_CHIPMUNK         = 4;

    final CharSequence[] VideoBitrateDialogOptions = {"Grandpa drunk", "Teen boy", "Household robot", "Helium inhaled", "Chipmunk"};
    int m_nSelectedVoiceChangerId = VAX_VOICE_CHANGER_HELIUM_INHALED;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle(R.string.choose_voice);
        objBuilder.setSingleChoiceItems(VideoBitrateDialogOptions, GetSelectedItem(), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                m_nSelectedVoiceChangerId = which;
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                OnClickOkButton();
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
        StoreVoiceChanger objStoreVoiceChanger = new StoreVoiceChanger();
        m_nSelectedVoiceChangerId = objStoreVoiceChanger.GetVoiceChanger();

        return m_nSelectedVoiceChangerId;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    private void OnClickOkButton()
    {
        StoreVoiceChanger objStoreVoiceChanger = new StoreVoiceChanger();
        objStoreVoiceChanger.SetVoiceChanger(m_nSelectedVoiceChangerId);

        if (VaxPhoneSIP.m_objVaxVoIP.IsVoiceChangerEnabled())
            VaxPhoneSIP.m_objVaxVoIP.VoiceChanger(true);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static int GetSelectedPitchNo()
    {
        StoreVoiceChanger objStoreVoiceChanger = new StoreVoiceChanger();
        int nVoiceChangerId = objStoreVoiceChanger.GetVoiceChanger();

        int aVoicePitch[] = {VaxPhoneSIP.VAX_VOICE_PITCH_GRANDPA_DRUNK, VaxPhoneSIP.VAX_VOICE_PITCH_TEEN_BOY, VaxPhoneSIP.VAX_VOICE_PITCH_HOUSE_HOLD_REBOT, VaxPhoneSIP.VAX_VOICE_PITCH_HELIUM_INHALED, VaxPhoneSIP.VAX_VOICE_PITCH_CHIPMUNK};
        return aVoicePitch[nVoiceChangerId];
    }
}