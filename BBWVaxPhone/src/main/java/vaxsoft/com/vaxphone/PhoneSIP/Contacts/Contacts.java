package vaxsoft.com.vaxphone.PhoneSIP.Contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class Contacts
{
    public Contacts()
    {

    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void ShowContactView(Fragment objFragment, int nRequestCode)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        objFragment.startActivityForResult(intent, nRequestCode);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public String GetPickedContactName(Activity objActivity, Intent data)
    {
        String sContactName = null;
        String sContactNum = null;

        ContentResolver objContentResolver = objActivity.getContentResolver();

        Uri uri = data.getData();
        Cursor objCursor =  objContentResolver.query(uri, null, null, null, null);

        if (objCursor != null && objCursor.moveToFirst())
        {
            sContactName = objCursor.getString(objCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            sContactNum = objCursor.getString(objCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        if (objCursor != null)
        {
            objCursor.close();
        }

        return sContactName;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public String GetPickedContactNum(Activity objActivity, Intent data)
    {
        String sContactNum = null ;

        ContentResolver objContentResolver = objActivity.getContentResolver();

        Uri uri = data.getData();
        Cursor objCursor = objContentResolver.query(uri, null, null, null, null);

        if (objCursor != null)
        {
            objCursor.moveToFirst();
            sContactNum = objCursor.getString(objCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            objCursor.close();
        }

        return sContactNum;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public String GetContactDetailsByNumber(String sPhoneNo, StringBuilder sContactId)
    {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(sPhoneNo));

        String sName = "";

        ContentResolver contentResolver = VaxPhoneAPP.getAppContext().getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID, ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

        try
        {
            if (contactLookup != null && contactLookup.getCount() > 0)
            {
                contactLookup.moveToNext();
                sName = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                sContactId.append(contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID)));
            }
        }

        finally
        {
            if (contactLookup != null)
            {
                contactLookup.close();
            }
        }

        return sName;
    }
}
