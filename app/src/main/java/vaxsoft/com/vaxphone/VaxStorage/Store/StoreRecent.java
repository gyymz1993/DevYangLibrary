package vaxsoft.com.vaxphone.VaxStorage.Store;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.VaxStorage.Database.DataSQL;

public class StoreRecent
{
    private final String RECENT_TABLE = "RecentTable";

    private final String COLUMN_ID = "_id";
    private final String COLUMN_OUTBOUND_CALL_TYPE = "OutboundCallType";
    private final String COLUMN_CALLER_NAME = "CallerName";
    private final String COLUMN_PHONE_NO = "PhoneNo";
    private final String COLUMN_START_TIME = "StartTime";
    private final String COLUMN_END_TIME = "EndTime";
    private final String COLUMN_DURATION = "Duration";
    private final String COLUMN_DAY_NO = "DayNo";
    private final String COLUMN_HISTORY_TYPE_ID = "HistoryTypeId";

    private DataSQL m_objDataSQL = null;

    /////////////////////////////////////////////////////////////////////////////////////////////

    public StoreRecent()
    {
        m_objDataSQL = new DataSQL();

        CreateTable();
    }

    private void CreateTable()
    {
        String sCreateTableQuery = "CREATE TABLE IF NOT EXISTS " + RECENT_TABLE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                   + COLUMN_OUTBOUND_CALL_TYPE + " INTEGER," + COLUMN_CALLER_NAME + " TEXT," + COLUMN_PHONE_NO + " TEXT,"
                                   + COLUMN_START_TIME + " INTEGER," + COLUMN_END_TIME + " INTEGER,"
                                   + COLUMN_DURATION + " INTEGER," + COLUMN_DAY_NO + " INTEGER," + COLUMN_HISTORY_TYPE_ID + " INTEGER);";

        m_objDataSQL.QueryExec(sCreateTableQuery);
    }

    public int AddCallHistory(boolean bOutboundCallType, String sName, String sPhoneNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {
        sName = android.database.DatabaseUtils.sqlEscapeString(sName);
        sPhoneNo = android.database.DatabaseUtils.sqlEscapeString(sPhoneNo);

        String sAddCallHistory = "INSERT INTO " + RECENT_TABLE + "(" + COLUMN_OUTBOUND_CALL_TYPE + "," +  COLUMN_CALLER_NAME + "," + COLUMN_PHONE_NO + ","
                                + COLUMN_START_TIME + "," + COLUMN_END_TIME + "," + COLUMN_DURATION + "," + COLUMN_DAY_NO + "," + COLUMN_HISTORY_TYPE_ID + ") VALUES ('"
                                + bOutboundCallType + "', " + sName + ", " + sPhoneNo + ", '" + nStartTime + "', '" + nEndTime + "', '"
                                + nDuration + "', '" + nDayNo + "', '" + nHistoryTypeId + "');";

        m_objDataSQL.QueryExec(sAddCallHistory);

        String sQueryGetRecordId = "SELECT * FROM " +  RECENT_TABLE + " WHERE " + COLUMN_ID + " = (SELECT MAX(" + COLUMN_ID + ") FROM " + RECENT_TABLE + ") ORDER BY " + COLUMN_ID + " DESC LIMIT 1";

        ArrayList list = m_objDataSQL.QueryExec(sQueryGetRecordId);
        ArrayList SubArray = (ArrayList) list.get(0);

        int nRecordId = (int) SubArray.get(0);

        return nRecordId;
    }

    public ArrayList GetCallHistoryAll()
    {
        ArrayList pRecordAll = new ArrayList();

        String sGetCallHistory = "SELECT * FROM " + RECENT_TABLE + " ORDER BY " + COLUMN_DAY_NO + " DESC, " +  COLUMN_START_TIME + " DESC"; // DESC ASC

        ArrayList RecentData = m_objDataSQL.QueryExec(sGetCallHistory);

        for (int nIndex = 0; nIndex < RecentData.size(); nIndex++)
        {
            ArrayList SubArray = (ArrayList) RecentData.get(nIndex);

            StoreDataRecents objStoreDataRecent = new StoreDataRecents();

            objStoreDataRecent.m_nRecordId          = Integer.parseInt(String.valueOf(SubArray.get(0)));
            objStoreDataRecent.m_bOutboundCallType  = Boolean.parseBoolean(String.valueOf(SubArray.get(1)));
            objStoreDataRecent.m_sName              = String.valueOf(SubArray.get(2));
            objStoreDataRecent.m_sPhoneNo           = String.valueOf(SubArray.get(3));
            objStoreDataRecent.m_nStartTime         = Long.parseLong(String.valueOf(SubArray.get(4)));
            objStoreDataRecent.m_nEndTime           = Long.parseLong(String.valueOf(SubArray.get(5)));
            objStoreDataRecent.m_nDuration          = Long.parseLong(String.valueOf(SubArray.get(6)));
            objStoreDataRecent.m_nDayNo             = Long.parseLong(String.valueOf(SubArray.get(7)));
            objStoreDataRecent.m_nHistoryTypeId     = Long.parseLong(String.valueOf(SubArray.get(8)));

            pRecordAll.add(objStoreDataRecent);
        }

        return pRecordAll;
    }

    public int GetCallCount()
    {
        String sCountQuery = "SELECT count(*) FROM " + RECENT_TABLE;

        ArrayList MainList = m_objDataSQL.QueryExec(sCountQuery);
        ArrayList SubList = (ArrayList) MainList.get(0);

        return (int) SubList.get(0);
    }

    public void RemoveCallRecord(int nId)
    {
        String sRemoveChatMsgQuery = "DELETE FROM " + RECENT_TABLE + " WHERE " + COLUMN_ID + " = '" + nId + "';";

        m_objDataSQL.QueryExec(sRemoveChatMsgQuery);
    }
}

