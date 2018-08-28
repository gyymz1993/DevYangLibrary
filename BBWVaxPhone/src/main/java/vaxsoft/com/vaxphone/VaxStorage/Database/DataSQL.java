package vaxsoft.com.vaxphone.VaxStorage.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class DataSQL extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "VaxDB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase m_objSqLiteDatabase = null;
    private Cursor m_objCursor;

    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public DataSQL()
    {
        super(VaxPhoneAPP.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public ArrayList QueryExec(String sQuery)
    {
        if (m_objSqLiteDatabase == null)
            GetWritableDatabase();

        m_objCursor = m_objSqLiteDatabase.rawQuery(sQuery, null);
        m_objCursor.moveToFirst();

        ArrayList<ArrayList> aRowsData = new ArrayList<>();

        for (int nIndexRow = 0; nIndexRow <  m_objCursor.getCount(); nIndexRow++)
        {
            m_objCursor.moveToPosition(nIndexRow);

            ArrayList aColumnData = FillColumns();
            aRowsData.add(nIndexRow, aColumnData);
        }

        return aRowsData;
    }

    private ArrayList FillColumns()
    {
        ArrayList<Object> aColumnData = new ArrayList<Object>();

        for (int nFieldIndex = 0; nFieldIndex < m_objCursor.getColumnCount(); nFieldIndex++)
        {
            if(m_objCursor.getType(nFieldIndex) == Cursor.FIELD_TYPE_STRING)
                aColumnData.add(m_objCursor.getString(nFieldIndex));

            if(m_objCursor.getType(nFieldIndex) == Cursor.FIELD_TYPE_INTEGER)
                aColumnData.add(m_objCursor.getInt(nFieldIndex));

            if(m_objCursor.getType(nFieldIndex) == Cursor.FIELD_TYPE_FLOAT)
                aColumnData.add(m_objCursor.getFloat(nFieldIndex));

            if(m_objCursor.getType(nFieldIndex) == Cursor.FIELD_TYPE_BLOB)
                aColumnData.add(m_objCursor.getBlob(nFieldIndex));

            if(m_objCursor.getType(nFieldIndex) == Cursor.FIELD_TYPE_NULL)
                aColumnData.add(null);
        }

        return aColumnData;
    }

    private void GetWritableDatabase()
    {
        m_objSqLiteDatabase = getWritableDatabase();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
}
