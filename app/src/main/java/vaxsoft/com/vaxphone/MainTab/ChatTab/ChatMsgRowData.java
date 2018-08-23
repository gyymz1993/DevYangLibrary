package vaxsoft.com.vaxphone.MainTab.ChatTab;

public class ChatMsgRowData
{
    private String m_sMsgText;
    private boolean m_bMsgTypeOutgoing;

    public ChatMsgRowData()
    {

    }

    public void SetChatMsg(String sMsgText, boolean bMsgTypeOutgoing)
    {
        m_sMsgText = sMsgText;
        m_bMsgTypeOutgoing = bMsgTypeOutgoing;
    }

    public String GetChatMsgText()
    {
        return m_sMsgText;
    }

    public boolean IsChatMsgTypeOutgoing()
    {
        return m_bMsgTypeOutgoing;
    }
}
