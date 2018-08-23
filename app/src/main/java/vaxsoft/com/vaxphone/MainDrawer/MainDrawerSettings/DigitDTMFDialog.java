package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreDigitDTMF;

public class DigitDTMFDialog extends DialogFragment
{
    final CharSequence[] mTypeNameDTMF = {"Outband (RFC2833)", "SIP INFO", "Inband (Voice Based)"};
    int m_nSelectedTypeDTMF = VaxPhoneSIP.VAX_DIGIT_DTMF_RFC2833;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        objBuilder.setTitle(R.string.choose_digit_dtmf);

        objBuilder.setSingleChoiceItems(mTypeNameDTMF, GetSelectedItem(), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int nSelectedTypeDTMF)
            {
                m_nSelectedTypeDTMF = nSelectedTypeDTMF;
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

    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    private void OnClickOkButton()
    {
        VaxPhoneSIP.m_objVaxVoIP.ForceDigitDTMF(m_nSelectedTypeDTMF, true);

        StoreDigitDTMF objStore = new StoreDigitDTMF();
        objStore.SetDigitDTMF(m_nSelectedTypeDTMF);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    private int GetSelectedItem()
    {
        StoreDigitDTMF objStore = new StoreDigitDTMF();
        m_nSelectedTypeDTMF = objStore.GetDigitDTMF();

        if(m_nSelectedTypeDTMF == -1)
            m_nSelectedTypeDTMF = VaxPhoneSIP.VAX_DIGIT_DTMF_RFC2833;

        return m_nSelectedTypeDTMF;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialize()
    {
        StoreDigitDTMF objStoreDigitDTMF = new StoreDigitDTMF();
        int nSeletedTypeDTMF = objStoreDigitDTMF.GetDigitDTMF();

        if(nSeletedTypeDTMF == -1)
            nSeletedTypeDTMF = VaxPhoneSIP.VAX_DIGIT_DTMF_RFC2833;

        VaxPhoneSIP.m_objVaxVoIP.ForceDigitDTMF(nSeletedTypeDTMF, true);
    }
}