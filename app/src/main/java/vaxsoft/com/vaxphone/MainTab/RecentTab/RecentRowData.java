package vaxsoft.com.vaxphone.MainTab.RecentTab;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RecentRowData
{
    private String m_sName;
    private String m_sPhoneNo;

    private int m_nRecordId;

    private long m_nDuration;
    private long m_nDateTime;

    private long m_nHistoryTypeId;

    RecentRowData()
    {
        m_nRecordId = -1;
        m_nHistoryTypeId = 0;
        m_nDuration = 0;
        m_nDateTime = 0;
    }

    void SetCallInfo(int nRecordId, boolean bOutboundCallType, String sName, String sPhoneNo, long nStartTime, long nEndTime, long nDuration, long nHistoryTypeId)
    {
        m_nRecordId = nRecordId;

        m_sName = sName;
        m_sPhoneNo = sPhoneNo;

        m_nDateTime = nStartTime;
        m_nDuration = nDuration;

        m_nHistoryTypeId = nHistoryTypeId;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    int GetRecordId()
    {
        return m_nRecordId;
    }

    String GetName()
    {
        return m_sName;
    }

    long GetHistoryTypeId()
    {
        return m_nHistoryTypeId;
    }

    String GetPhoneNo()
    {
        return m_sPhoneNo;
    }

    String GetTime()
    {
        String sDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(m_nDateTime * 1000));

        Date date = null;
        try
        {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        String sTime24Hour =  new SimpleDateFormat("H:mm").format(date);
        String sTime12Hour = new SimpleDateFormat("h:mm a").format(date);

        return sTime12Hour;
    }

    String GetDate()
    {
        DateFormat OrignalFormat = new SimpleDateFormat("yyyyMMddhhmmss", Locale.ENGLISH);
        DateFormat TargetFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date objDate = null;

        SimpleDateFormat objSDF = new SimpleDateFormat("yyyyMMddhhmmss");

        Long nDateTime = Long.parseLong(objSDF.format(new Date(m_nDateTime * 1000)));

        try
        {
            objDate = OrignalFormat.parse(String.valueOf(nDateTime));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        String sDate = TargetFormat.format(objDate);

        return sDate;
    }

    String GetDuration()
    {
        long nSeconds = m_nDuration % 60;
        long nMinutes = m_nDuration / 60;

        if (nMinutes >= 60)
        {
            long nHours = nMinutes / 60;
            nMinutes %= 60;

            if(nHours >= 24)
            {
                long days = nHours / 24;
                return String.format("%d days %02d:%02d:%02d.", days,nHours%24, nMinutes, nSeconds);
            }

            return String.format("%02d:%02d:%02d", nHours, nMinutes, nSeconds);
        }

        return String.format("00:%02d:%02d", nMinutes, nSeconds);
    }
}
