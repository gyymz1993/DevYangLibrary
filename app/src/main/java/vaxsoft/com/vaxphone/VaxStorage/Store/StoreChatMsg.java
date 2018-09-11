package vaxsoft.com.vaxphone.VaxStorage.Store;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.VaxStorage.Database.DataSQL;

public class StoreChatMsg
{
    private final String CHAT_MSG_TABLE = "ChatMsgTable";
    private final String COLUMN_ID = "_id";
    private final String COLUMN_OUTBOUND_MSG_TYPE = "OutboundMsgType";
    private final String COLUMN_CONTACT_NAME = "ContactName";
    private final String COLUMN_MESSAGE = "Message";
    private final String COLUMN_TIME = "Time";

    private DataSQL m_objDataSQL = null;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public StoreChatMsg()
    {
        m_objDataSQL = new DataSQL();

        CreateTable();
    }

    private void CreateTable()
    {
        String sCreateTableQuery = "CREATE TABLE IF NOT EXISTS " + CHAT_MSG_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + COLUMN_OUTBOUND_MSG_TYPE + " INTEGER," + COLUMN_CONTACT_NAME + " TEXT,"
                                    + COLUMN_MESSAGE + " TEXT," + COLUMN_TIME + " INTEGER);";


        m_objDataSQL.QueryExec(sCreateTableQuery);
    }

    public void AddChatMsg(String sUsername, String sMessage, boolean bOutboundMsgType)
    {
        sMessage = android.database.DatabaseUtils.sqlEscapeString(sMessage);
        sUsername = android.database.DatabaseUtils.sqlEscapeString(sUsername);

        String sAddChatMsgQuery = "INSERT INTO " + CHAT_MSG_TABLE + "(" + COLUMN_OUTBOUND_MSG_TYPE + "," +  COLUMN_CONTACT_NAME + "," + COLUMN_MESSAGE  +  ") "
                                   + "VALUES ('" + bOutboundMsgType + "', " + sUsername + "," + sMessage + ");";

        m_objDataSQL.QueryExec(sAddChatMsgQuery);
    }

    public ArrayList GetChatMsgAll(String sContactName)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);

        ArrayList pRecordAll = new ArrayList();

        String sGetChatMsgAllQuery = "SELECT * FROM " + CHAT_MSG_TABLE + " WHERE " + COLUMN_CONTACT_NAME + " = " + sContactName + " ORDER BY " + COLUMN_ID + " ASC";

        ArrayList ChatMsgData = m_objDataSQL.QueryExec(sGetChatMsgAllQuery);

        for (int nIndex = 0; nIndex < ChatMsgData.size(); nIndex++)
        {
            ArrayList SubArray = (ArrayList) ChatMsgData.get(nIndex);

            StoreDataChatMsgs objStoreDataChatMsgs = new StoreDataChatMsgs();

            objStoreDataChatMsgs.m_nRecordId = Long.parseLong(String.valueOf(SubArray.get(0)));
            objStoreDataChatMsgs.m_bMsgTypeOutgoing = Boolean.parseBoolean(String.valueOf(SubArray.get(1)));
            objStoreDataChatMsgs.m_sContactName = String.valueOf(SubArray.get(2));
            objStoreDataChatMsgs.m_sMsgText = String.valueOf(SubArray.get(3));

            pRecordAll.add(objStoreDataChatMsgs);
        }

        return pRecordAll;
    }

    public void ClearContactChatMsgs(String sContactName)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);

        String sQuery = "DELETE FROM " + CHAT_MSG_TABLE + " WHERE " + COLUMN_CONTACT_NAME + " = " + sContactName + ";";

        m_objDataSQL.QueryExec(sQuery);
    }

    public void RemoveChatMsg(String sContactName, String sMsg)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);
        sMsg = android.database.DatabaseUtils.sqlEscapeString(sMsg);

        String sRemoveChatMsgQuery = "DELETE FROM " + CHAT_MSG_TABLE + " WHERE " + COLUMN_CONTACT_NAME + " = " + sContactName + " and " + COLUMN_MESSAGE + " = " + sMsg + ";";

        m_objDataSQL.QueryExec(sRemoveChatMsgQuery);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
}

