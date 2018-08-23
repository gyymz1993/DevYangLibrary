package vaxsoft.com.vaxphone.PhoneSIP.CallInfo;

import vaxsoft.com.vaxphone.PhoneSIP.Contacts.Contacts;

public class CallInfo
{
    public static void PrepareCallInfo(String sName, String sPhoneNo, StringBuilder sResultName, StringBuilder sResultPhoneNo, StringBuilder sResultContactId)
    {
        Contacts objContacts = new Contacts();

        if(sName.length() != 0 && sPhoneNo.length() != 0)
        {
            String sContactName = objContacts.GetContactDetailsByNumber(sPhoneNo, sResultContactId);

            if (sContactName.equals(""))
                sResultName.append(sName);

            sResultPhoneNo.append(sPhoneNo);
        }

        else if(sName.length() == 0 && sPhoneNo.length() != 0)
        {
            String sContactName = objContacts.GetContactDetailsByNumber(sPhoneNo, sResultContactId);

            if (sContactName.equals(""))
                sResultName.append("No Name");

            sResultPhoneNo.append(sPhoneNo);
        }

        else if(sName.length() != 0 && sPhoneNo.length() == 0)
        {
            sResultName.append(sName);
            sResultPhoneNo.append("Unknown");
        }

        else if(sName.length() == 0 && sPhoneNo.length() == 0)
        {
            sResultName.append("No Name");
            sResultPhoneNo.append("Unknown");
        }
    }
}
