package vaxsoft.com.vaxphone.MainUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class DialogUtil
{
    public static void ShowDialog(Context context, String sMsg)
    {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);

        if (!sMsg.equals(""))
            objBuilder.setMessage(sMsg);

        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        objBuilder.create();
        objBuilder.show();
    }

    public static void VaxErrorDialog(Context context, String message)
    {
        AlertDialog.Builder alertDialogBuilder;
        AlertDialog objAlertDialog;

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Vax Error");
        alertDialogBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        objAlertDialog = alertDialogBuilder.create();
        Window window = objAlertDialog.getWindow();
        if (window != null)
        {
            window.setType(WindowManager.LayoutParams.TYPE_TOAST);
            objAlertDialog.show();
        }
        else
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

