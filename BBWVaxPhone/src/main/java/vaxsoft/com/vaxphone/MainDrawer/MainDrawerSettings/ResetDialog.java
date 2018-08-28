package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreGeneralSettings;

public class ResetDialog extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder objBuilder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuilder.setTitle("Reset Settings");
        objBuilder.setMessage("Are you sure you want to reset all settings?");

        objBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                StoreGeneralSettings objSettings = new StoreGeneralSettings();
                objSettings.ResetAllSettings();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        return objBuilder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
    }
}
