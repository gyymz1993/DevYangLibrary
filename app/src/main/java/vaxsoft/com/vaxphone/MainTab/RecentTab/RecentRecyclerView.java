package vaxsoft.com.vaxphone.MainTab.RecentTab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.CustomViews.RecyclerView.CustomRecyclerView;
import vaxsoft.com.vaxphone.CustomViews.RecyclerView.ICustomRecyclerView;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDataRecents;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreRecent;

public class RecentRecyclerView extends CustomRecyclerView implements ICustomRecyclerView
{
    private static RecentRecyclerView mRecentRecyclerView = null;
    private static Boolean m_bIsRecentActivated = false;

    private ArrayList m_aListData = null;
    private LayoutInflater mLayoutInflater;

    ///////////////////////////////////////////////////////////////////////////////////

    public RecentRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        super.m_sActionModeTitle = "Delete Call Records";
        addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mLayoutInflater = LayoutInflater.from(context);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public void OnFragmentActivated()
    {
        m_bIsRecentActivated = true;
        mRecentRecyclerView = this;

        m_aListData = new ArrayList();
        LoadCallHistoryAll();

        RecentTabFragment.SetMissedCount(0);
    }

    public void OnFragmentDeactivated()
    {
        m_bIsRecentActivated = false;
        mRecentRecyclerView = null;

        m_aListData = null;
        super.ClearSectionRowList();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private void LoadCallHistoryAll()
    {
        StoreRecent objStoreRecent = new StoreRecent();
        ArrayList aListInfoAll = objStoreRecent.GetCallHistoryAll();

        for (int nCount = 0; nCount < aListInfoAll.size(); nCount++)
        {
            StoreDataRecents objStoreData = (StoreDataRecents) aListInfoAll.get(nCount);
            this.AddCallHistory(objStoreData.m_nRecordId, objStoreData.m_bOutboundCallType, objStoreData.m_sName, objStoreData.m_sPhoneNo, objStoreData.m_nStartTime, objStoreData.m_nEndTime, objStoreData.m_nDuration, objStoreData.m_nDayNo, objStoreData.m_nHistoryTypeId);
        }
    }

    private void AddCallHistory(int nRecordId, boolean bOutboundCallType, String sName, String sPhoneNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, long nHistoryTypeId)
    {
        AddSection(nDayNo);
        AddRow(nRecordId, bOutboundCallType, sName, sPhoneNo, nStartTime, nEndTime, nDuration, nHistoryTypeId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public RecyclerView.ViewHolder OnViewHolderCreate(ViewGroup parent, int viewType)
    {
        if (viewType == VIEW_TYPE_SECTION)
        {
            View SectionView = mLayoutInflater.inflate(R.layout.layout_recent_section_heading, parent, false);
            return new RecentSectionViewHolder(SectionView);
        }

        View RowView = mLayoutInflater.inflate(R.layout.layout_recent_section_row, parent, false);
        return new RecentRowViewHolder(RowView);
    }

    @Override
    public void OnViewHolderBindUI(RecyclerView.ViewHolder holder, Boolean bDeleteActivated, Boolean bDeleteSelected, int nPosition, int nViewType)
    {
        holder.itemView.setSelected(bDeleteSelected);

        if (nViewType == VIEW_TYPE_SECTION)
        {
            RecentSectionData objRecentSectionData = (RecentSectionData) m_aListData.get(nPosition);

            String sDay = objRecentSectionData.GetTitle();
           ((RecentSectionViewHolder) holder).UpdateUI(sDay);
        }

        else if (nViewType == VIEW_TYPE_ROW)
        {
            RecentRowData objRowData = (RecentRowData) m_aListData.get(nPosition);

            String sName = objRowData.GetName();
            String sPhoneNo = objRowData.GetPhoneNo();
            String sDate = objRowData.GetDate();
            String sDuration = objRowData.GetDuration();
            long nHistoryTypeId = objRowData.GetHistoryTypeId();
            String sTime = objRowData.GetTime();

            ((RecentRowViewHolder) holder).UpdateUI(sName, sPhoneNo, sDate, sDuration, nHistoryTypeId, sTime);
       }
    }

    @Override
    public void OnViewHolderRowClicked(RecyclerView.ViewHolder holder, int nPosition)
    {
        RecentRowViewHolder objViewHolder = (RecentRowViewHolder) holder;

        String sPhoneNo = objViewHolder.mTextViewPhoneNo.getText().toString();
        RecentTabFragment.PostOpenDialPad(sPhoneNo);
    }

    @Override
    public void OnViewHolderDeleted(int nPosition, int nViewType)
    {
        if (nViewType == super.VIEW_TYPE_SECTION)
        {
            m_aListData.remove(nPosition);
            return;
        }

        Object objData = m_aListData.get(nPosition);
        RecentRowData objRowData = (RecentRowData) objData;

        int nRecordId = objRowData.GetRecordId();
        m_aListData.remove(nPosition);

        VaxPhoneSIP.m_objVaxVoIP.RemoveCallRecord(nRecordId);
    }

    @Override
    public void OnViewHolderDeleteSelected(RecyclerView.ViewHolder holder, boolean bDeleteEnabled, int nPosition, int nViewType)
    {
        holder.itemView.setSelected(bDeleteEnabled);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public static void PostAddCallHistory(int nRecordId, boolean bOutboundCallType, String sName, String sPhoneNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, long nHistoryTypeId)
    {
        if(!m_bIsRecentActivated  && !bOutboundCallType && nHistoryTypeId == VaxUserAgentLib.VAX_CALL_HISTORY_TYPE_MISSED)
            RecentTabFragment.SetMissedCount(-1);

        if(mRecentRecyclerView == null)
            return;

        mRecentRecyclerView.UpdateView(nDayNo, nRecordId, bOutboundCallType, sName, sPhoneNo, nStartTime, nEndTime, nDuration, nHistoryTypeId);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    private void AddSection(long nDayNo)
    {
        for(int nSectionNo = 0; nSectionNo < m_aListData.size(); nSectionNo++)
        {
            Object objData = m_aListData.get(nSectionNo);

            if (!(objData instanceof RecentSectionData))
                continue;

            RecentSectionData objSectionData = (RecentSectionData) objData;
            long nLastDayNo = objSectionData.GetDayNo();

            if(nLastDayNo == nDayNo)
                return;
        }

        RecentSectionData objSectionData = new RecentSectionData(nDayNo);
        m_aListData.add(objSectionData);

        super.AddRowAtEnd(VIEW_TYPE_SECTION);
    }

    private void AddRow(int nRecordId, boolean bOutboundCallType, String sName, String sPhoneNo, long nStartTime, long nEndTime, long nDuration, long nHistoryTypeId)
    {
        RecentRowData objRowData = new RecentRowData();
        objRowData.SetCallInfo(nRecordId, bOutboundCallType, sName, sPhoneNo, nStartTime, nEndTime, nDuration, nHistoryTypeId);

        m_aListData.add(objRowData);

        super.AddRowAtEnd(VIEW_TYPE_ROW);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    public void UpdateView(long nDayNo, int nRecordId, boolean bOutboundCallType, String sName, String sPhoneNo, long nStartTime, long nEndTime, long nDuration, long nHistoryTypeId)
    {
        AddSection(nDayNo);

        RecentRowData objRowData = new RecentRowData();
        objRowData.SetCallInfo(nRecordId, bOutboundCallType, sName, sPhoneNo, nStartTime, nEndTime, nDuration, nHistoryTypeId);

        for(int nSectionRowNo = 0; nSectionRowNo < m_aListData.size(); nSectionRowNo++)
        {
            Object objData = m_aListData.get(nSectionRowNo);

            if (!(objData instanceof RecentSectionData))
                continue;

            RecentSectionData objSectionData = (RecentSectionData) objData;
            long nLastDayNo = objSectionData.GetDayNo();

            if(nLastDayNo == nDayNo)
            {
                int nPosition = nSectionRowNo;
                m_aListData.add(nPosition + 1, objRowData);

                super.AddRowAtPosition(nPosition + 1, VIEW_TYPE_ROW);
                break;
            }
        }
    }
}
