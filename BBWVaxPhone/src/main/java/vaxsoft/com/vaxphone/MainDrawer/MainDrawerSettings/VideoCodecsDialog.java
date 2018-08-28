package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreVideoCodecs;

public class VideoCodecsDialog extends DialogFragment
{
    final CharSequence[] aVideoCodec = {"VP8", "H263" , "H263+"};
    boolean[] m_aVideoCodecs = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        VideoCodecsDialog.ApplyVideoCodec();

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle(R.string.choose_video_codecs);

        objBuilder.setMultiChoiceItems(aVideoCodec, GetCheckedItem() , new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int SelectedIndex, boolean isChecked)
            {
                OnClickCheckBox(SelectedIndex, isChecked);
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                OnClickOKButton();

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

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    private void OnClickCheckBox(int nSelectedCodec, boolean bChecked)
    {
        if (m_aVideoCodecs == null)
            m_aVideoCodecs = GetCheckedItem();

        if (nSelectedCodec == VaxPhoneSIP.VAX_CODEC_VP8)
            m_aVideoCodecs[VaxPhoneSIP.VAX_CODEC_VP8] = bChecked;

        else if (nSelectedCodec == VaxPhoneSIP.VAX_CODEC_H263)
            m_aVideoCodecs[VaxPhoneSIP.VAX_CODEC_H263] = bChecked;

        else if (nSelectedCodec == VaxPhoneSIP.VAX_CODEC_H263P)
            m_aVideoCodecs[VaxPhoneSIP.VAX_CODEC_H263P] = bChecked;
    }

    private void OnClickOKButton()
    {
        if (m_aVideoCodecs == null)
            m_aVideoCodecs = GetCheckedItem();

        StoreVideoCodecs objStoreCodecs = new StoreVideoCodecs();
        objStoreCodecs.SetVideoCodecs(m_aVideoCodecs);

        for(int nCount = 0; nCount < VaxPhoneSIP.VAX_VIDEO_CODEC_TOTAL; nCount++)
        {
            int nCodecNo = nCount;
            boolean bEnabled = m_aVideoCodecs[nCount];

            if(bEnabled)
                VaxPhoneSIP.m_objVaxVoIP.SelectVideoCodec(nCodecNo);
            else
                VaxPhoneSIP.m_objVaxVoIP.DeselectVideoCodec(nCodecNo);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    private boolean[] GetCheckedItem()
    {
        boolean[] aVideoCodecs = new boolean[VaxPhoneSIP.VAX_VIDEO_CODEC_TOTAL];

        StoreVideoCodecs objStoreCodecs = new StoreVideoCodecs();
        objStoreCodecs.GetVideoCodecs(aVideoCodecs);

        m_aVideoCodecs = aVideoCodecs;

        return aVideoCodecs;
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialize()
    {
        VideoCodecsDialog.ApplyVideoCodec();
    }

    public static void ApplyVideoCodec()
    {
        boolean[] aVideoCodecs = new boolean[VaxPhoneSIP.VAX_VIDEO_CODEC_TOTAL];

        StoreVideoCodecs objStoreCodecs = new StoreVideoCodecs();
        objStoreCodecs.GetVideoCodecs(aVideoCodecs);

        VaxPhoneSIP.m_objVaxVoIP.DeselectAllVideoCodec();

        for(int nCount = 0; nCount < VaxPhoneSIP.VAX_VIDEO_CODEC_TOTAL; nCount++)
        {
            int nCodecNo = nCount;
            boolean bEnabled = aVideoCodecs[nCount];

            if(bEnabled)
                VaxPhoneSIP.m_objVaxVoIP.SelectVideoCodec(nCodecNo);
            else
                VaxPhoneSIP.m_objVaxVoIP.DeselectVideoCodec(nCodecNo);
        }
    }
}