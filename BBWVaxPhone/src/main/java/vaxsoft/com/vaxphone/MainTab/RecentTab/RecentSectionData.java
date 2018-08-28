package vaxsoft.com.vaxphone.MainTab.RecentTab;

import android.text.format.DateUtils;

import static android.text.format.DateUtils.DAY_IN_MILLIS;

class RecentSectionData
{
    private long m_nDayNo;

    RecentSectionData(long nDayNo)
    {
        m_nDayNo = nDayNo;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    long GetDayNo()
    {
        return m_nDayNo;
    }

    String GetTitle()
    {
        long nDateTime = m_nDayNo * 86400 * 1000;
        long lCurrentDay = System.currentTimeMillis();

        return String.valueOf(DateUtils.getRelativeTimeSpanString(nDateTime, lCurrentDay, DAY_IN_MILLIS));
    }
}
