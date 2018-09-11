package vaxsoft.com.vaxphone.MainTab.ChatTab;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreChatContact;

public class ChatContactRowData
{
    private long m_nMissedCount;
    private int m_nStatusId;

    private String m_sContactName;

    private Boolean m_bSelected;
    private Boolean m_bPresence;

    public ChatContactRowData()
    {
        m_nMissedCount = 0;
        m_bSelected = false;

        m_nStatusId = VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_UNKNOWN;
        m_bPresence = false;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public long GetMissedCount()
    {
        return m_nMissedCount;
    }

    public void SetMissedCount(long nValue)
    {
        if(nValue == 0)
            m_nMissedCount = 0;

        else if(nValue == -1)
            m_nMissedCount++;

        else
            m_nMissedCount = nValue;

        StoreChatContact objStoreChatContact = new StoreChatContact();
        objStoreChatContact.UpdateMissedCount(m_sContactName, m_nMissedCount);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public String GetContactName()
    {
        return m_sContactName;
    }

    public void SetContactName(String sName)
    {
        m_sContactName = sName;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public void SetStatusId(int nStatusId)
    {
        m_nStatusId = nStatusId;
    }

    public int GetStatusId()
    {
        return m_nStatusId;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public void SetContactSelected(boolean bSelected)
    {
        m_bSelected = bSelected;
    }

    public boolean IsContactSelected()
    {
        return m_bSelected;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public void SetPresence(boolean bPresence)
    {
        m_bPresence = bPresence;
    }

    public boolean GetPresence()
    {
        return m_bPresence;
    }
}
