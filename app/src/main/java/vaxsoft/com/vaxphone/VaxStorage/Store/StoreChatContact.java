package vaxsoft.com.vaxphone.VaxStorage.Store;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.VaxStorage.Database.DataSQL;

public class StoreChatContact
{
    private final String CHAT_CONTACT_TABLE = "ChatContactTable";

    private final String COLUMN_ID = "RecordId";
    private final String COLUMN_CONTACT_NAME = "ContactName";
    private final String COLUMN_MISSED_COUNT = "MissedCount";
    private final String COLUMN_PRESENCE = "Presence";
    private final String COLUMN_STATUS = "Status";

    private DataSQL m_objDataSQL = null;
    private int VAX_CHAT_CONTACT_STATUS_UNKNOWN = 5;

    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    public StoreChatContact()
    {
        m_objDataSQL = new DataSQL();

        CreateTable();
    }

    private void CreateTable()
    {
        String sCreateTableQuery = "CREATE TABLE IF NOT EXISTS " + CHAT_CONTACT_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    +  COLUMN_CONTACT_NAME + " TEXT," + COLUMN_MISSED_COUNT + " INTEGER," + COLUMN_PRESENCE + " INTEGER," + COLUMN_STATUS + " INTEGER);";

        m_objDataSQL.QueryExec(sCreateTableQuery);
    }

    public void AddChatContact(String sContactName, long nMissedCount, boolean bPresence)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);
        RemoveChatContact(sContactName);

        String sAddChatContactQuery = "INSERT INTO " + CHAT_CONTACT_TABLE + "(" + COLUMN_CONTACT_NAME + "," + COLUMN_MISSED_COUNT + "," + COLUMN_PRESENCE + ") "
                                       + "VALUES (" + sContactName + ", '" + nMissedCount + "', '" + bPresence + "');";

        m_objDataSQL.QueryExec(sAddChatContactQuery);
    }

    public ArrayList GetChatContactAll()
    {
        String sGetChatContactAll = "SELECT * FROM " + CHAT_CONTACT_TABLE + " ORDER BY " + COLUMN_ID + " ASC"; //DESC

        return m_objDataSQL.QueryExec(sGetChatContactAll);
    }

    public int GetChatContactCount()
    {
        String sCountQuery = "SELECT count(*) FROM " + CHAT_CONTACT_TABLE;

        ArrayList MainList = m_objDataSQL.QueryExec(sCountQuery);
        ArrayList SubList = (ArrayList) MainList.get(0);

        return (int) SubList.get(0);
    }

    public void RemoveChatContactAll()
    {
        String RemoveChatContactAll = "DELETE FROM " + CHAT_CONTACT_TABLE;

        m_objDataSQL.QueryExec(RemoveChatContactAll);
    }

    public void RemoveChatContact(String sContactName)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);

        String sRemoveChatContact = "DELETE FROM " + CHAT_CONTACT_TABLE + " WHERE " + COLUMN_CONTACT_NAME + " = " + sContactName + ";";

        m_objDataSQL.QueryExec(sRemoveChatContact);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public void UpdateContactStatusId(String sContactName, int nStatusId)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);

        String sQuery = "UPDATE " + CHAT_CONTACT_TABLE + " SET " + COLUMN_STATUS + " = " + nStatusId + " WHERE " + COLUMN_CONTACT_NAME + " = " + sContactName + ";";

        m_objDataSQL.QueryExec(sQuery);
    }

    public int GetContactStatusId(String sContactName)
    {
        sContactName = android.database.DatabaseUtils.sqlEscapeString(sContactName);

        String sQuery = "SELECT " + COLUMN_STATUS + " FROM " + CHAT_CONTACT_TABLE + " WHERE " + COLUMN_CONTACT_NAME + " = " + sContactName + ";";

        ArrayList objArrayList = m_objDataSQL.QueryExec(sQuery);
        ArrayList SubList = (ArrayList) objArrayList.get(0);

        if (SubList.get(0) != null)
            return (int) SubList.get(0);

        return VAX_CHAT_CONTACT_STATUS_UNKNOWN;
    }

    public void UpdateMissedCount(String sContactName, long nMissedCount)
    {

    }
}
