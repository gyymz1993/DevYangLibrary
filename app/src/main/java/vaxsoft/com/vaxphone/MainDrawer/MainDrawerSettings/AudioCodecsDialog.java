package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreAudioCodecs;

public class AudioCodecsDialog extends DialogFragment
{
    final CharSequence[] aAudioCodecs = {"G711u-Law", "G711a-Law", "GSM", "iLBC", "G729"};
    boolean[] m_aVoiceCodecs = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle(R.string.choose_audio_codecs);
        objBuilder.setMultiChoiceItems(aAudioCodecs, GetCheckedItems(), new DialogInterface.OnMultiChoiceClickListener()
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

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    private void OnClickCheckBox(int nSelectedCodec, boolean bChecked)
    {
        if (m_aVoiceCodecs == null)
            m_aVoiceCodecs = GetCheckedItems();

        if (nSelectedCodec == VaxUserAgentLib.VAX_CODEC_G711U)
            m_aVoiceCodecs[VaxUserAgentLib.VAX_CODEC_G711U] = bChecked;

        else if (nSelectedCodec == VaxUserAgentLib.VAX_CODEC_G711A)
            m_aVoiceCodecs[VaxUserAgentLib.VAX_CODEC_G711A] = bChecked;

        else if (nSelectedCodec == VaxUserAgentLib.VAX_CODEC_GSM610)
            m_aVoiceCodecs[VaxUserAgentLib.VAX_CODEC_GSM610] = bChecked;

        else if (nSelectedCodec == VaxUserAgentLib.VAX_CODEC_ILBC)
            m_aVoiceCodecs[VaxUserAgentLib.VAX_CODEC_ILBC] = bChecked;

        else if (nSelectedCodec == VaxUserAgentLib.VAX_CODEC_G729)
            m_aVoiceCodecs[VaxUserAgentLib.VAX_CODEC_G729] = bChecked;
    }

    private void OnClickOKButton()
    {
        if (m_aVoiceCodecs == null)
            m_aVoiceCodecs = GetCheckedItems();

        StoreAudioCodecs objStoreCodecs = new StoreAudioCodecs();
        objStoreCodecs.SetAudioCodecs(m_aVoiceCodecs);

        for(int nCount = 0; nCount < VaxUserAgentLib.VAX_VOICE_CODEC_TOTAL; nCount++)
        {
            int nCodecNo = nCount;
            boolean bEnabled = m_aVoiceCodecs[nCount];

            if(bEnabled)
                VaxPhoneSIP.m_objVaxVoIP.SelectVoiceCodec(nCodecNo);
            else
                VaxPhoneSIP.m_objVaxVoIP.DeselectVoiceCodec(nCodecNo);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    private boolean[] GetCheckedItems()
    {
        boolean[] aVoiceCodecs = new boolean[VaxUserAgentLib.VAX_VOICE_CODEC_TOTAL];

        StoreAudioCodecs objStoreCodecs = new StoreAudioCodecs();
        objStoreCodecs.GetAudioCodecs(aVoiceCodecs);

        m_aVoiceCodecs = aVoiceCodecs;

        return m_aVoiceCodecs;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialize()
    {
        AudioCodecsDialog.ApplyVoiceCodec();
    }

    private static void ApplyVoiceCodec()
    {
        boolean[] aVoiceCodecs = new boolean[VaxUserAgentLib.VAX_VOICE_CODEC_TOTAL];

        StoreAudioCodecs objStoreCodecs = new StoreAudioCodecs();
        objStoreCodecs.GetAudioCodecs(aVoiceCodecs);

        VaxPhoneSIP.m_objVaxVoIP.DeselectAllVoiceCodec();

        for(int nCount = 0; nCount < VaxUserAgentLib.VAX_VOICE_CODEC_TOTAL; nCount++)
        {
            int nCodecNo = nCount;
            boolean bEnabled = aVoiceCodecs[nCount];

            if(bEnabled)
            {
                VaxPhoneSIP.m_objVaxVoIP.SelectVoiceCodec(nCodecNo);
            }
            else
                VaxPhoneSIP.m_objVaxVoIP.DeselectVoiceCodec(nCodecNo);
        }
    }
}