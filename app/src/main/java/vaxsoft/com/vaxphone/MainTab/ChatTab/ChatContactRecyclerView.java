package vaxsoft.com.vaxphone.MainTab.ChatTab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.CustomViews.RecyclerView.CustomRecyclerView;
import vaxsoft.com.vaxphone.CustomViews.RecyclerView.ICustomRecyclerView;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class ChatContactRecyclerView extends CustomRecyclerView implements ICustomRecyclerView
{
    private Context mContext = null;
    private LayoutInflater mLayoutInflater;

    private static ChatContactRecyclerView mChatContactRecyclerView = null;
    public static ArrayList mChatContactData = new ArrayList();

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public ChatContactRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        super.m_sActionModeTitle = "Delete Chat Contacts";
        mContext = context;

        addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void OnFragmentActivated()
    {
        mChatContactRecyclerView = this;

        ChatContactTabFragment.SetMissedCount(0);
        ResetContactSelectedStateAll();

        VaxPhoneSIP.m_objVaxVoIP.ChatSubscribeContactAll();

        RefreshView();
    }

    public void OnFragmentDeactivated()
    {
        mChatContactRecyclerView = null;
    }

    private void ResetContactSelectedStateAll()
    {
        for(int nRowNo = 0; nRowNo < mChatContactData.size(); nRowNo++)
        {
            ChatContactRowData objRowData = (ChatContactRowData) mChatContactData.get(nRowNo);
            objRowData.SetContactSelected(false);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    private void RefreshView()
    {
        super.ClearSectionRowList();

        for (int nIndex = 0; nIndex < mChatContactData.size(); nIndex++)
        {
            super.AddRowAtEnd(VIEW_TYPE_ROW);
        }
    }

    private void UpdateRow(int nPosition)
    {
        super.NotifyItemChanged(nPosition);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ViewHolder OnViewHolderCreate(ViewGroup parent, int viewType)
    {
        View objView = mLayoutInflater.inflate(R.layout.layout_chat_row, parent, false);
        return new ChatContactRowViewHolder(objView);
    }

    @Override
    public void OnViewHolderBindUI(ViewHolder holder, Boolean bDeleteActivated, Boolean bDeleteSelected, int nPosition, int nViewType)
    {
        ChatContactRowData objRowData = (ChatContactRowData) mChatContactData.get(nPosition);
        ChatContactRowViewHolder objViewHolder = (ChatContactRowViewHolder) holder;

        String sContactName = objRowData.GetContactName();
        int nMissedCount = (int) objRowData.GetMissedCount();
        int nStatusId = objRowData.GetStatusId();

        objViewHolder.UpdateView(bDeleteActivated, bDeleteSelected, nStatusId, sContactName, nMissedCount);
    }

    @Override
    public void OnViewHolderRowClicked(ViewHolder holder, int nPosition)
    {
        OpenChatMsgView(nPosition, false);
        NotifyItemChanged(nPosition);
    }

    @Override
    public void OnViewHolderDeleted(int nPosition, int nViewType)
    {
        ChatContactRowData objRowData = (ChatContactRowData) mChatContactData.get(nPosition);

        String sContactName = objRowData.GetContactName();
        mChatContactData.remove(nPosition);

        VaxPhoneSIP.m_objVaxVoIP.ChatRemoveContact(sContactName);
    }

    @Override
    public void OnViewHolderDeleteSelected(ViewHolder holder, boolean bDeleteEnabled, int nPosition, int nViewType)
    {
        ChatContactRowViewHolder objViewHolder = (ChatContactRowViewHolder) holder;
        objViewHolder.UpdateView(true, bDeleteEnabled, -1, "", -1);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void OpenChatMsgView(int nRowId, boolean bNewContact)
    {
        ChatContactRowData objChatContactRowData = (ChatContactRowData) mChatContactData.get(nRowId);

        //////////////////////////////////////////////////

        String sAddContactMsg;

        if(bNewContact)
            sAddContactMsg = "Hi, I'd like to add you as a chat contact.";
        else
            sAddContactMsg = "";

        //////////////////////////////////////////////////

        objChatContactRowData.SetMissedCount(0);
        objChatContactRowData.SetContactSelected(true);

        ChatMsgFragment.PostMessageViewData(objChatContactRowData, sAddContactMsg);

        //////////////////////////////////////////////////

        ChatMsgFragment objChatMsgFragment = new ChatMsgFragment();

        FragmentManager objFragmentManager = ((MainTabActivity) mContext).getSupportFragmentManager();
        FragmentTransaction objFragmentTransaction = objFragmentManager.beginTransaction();

        objFragmentTransaction.replace(android.R.id.content, objChatMsgFragment, "ChatMsgFragment");
        objFragmentTransaction.addToBackStack("ChatMsgFragment");
        objFragmentTransaction.commit();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private void OnChatContactAdded(String sUserName, long nMissedCount, boolean bPresence)
    {
        super.AddRowAtStart(VIEW_TYPE_ROW);
    }

    public static void PostChatContactAdded(String sContactName, long nMissedCount, boolean bPresence)
    {
        ChatContactRowData objRowData = ChatContactRecyclerView.FindChatContactData(sContactName);

        boolean bAdded = false;

        if(objRowData == null)
        {
            objRowData = new ChatContactRowData();
            objRowData.SetContactName(sContactName);

            bAdded = true;
            mChatContactData.add(0, objRowData);
        }

        objRowData.SetMissedCount(nMissedCount);
        objRowData.SetPresence(bPresence);

        if(mChatContactRecyclerView != null && bAdded)
            mChatContactRecyclerView.OnChatContactAdded(sContactName, nMissedCount, bPresence);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public static void PostChatMessageText(String sContactName, boolean isChatContact, String sMsgText, boolean bMsgTypeOutgoing)
    {
        if(!bMsgTypeOutgoing && !ChatContactTabFragment.m_bChatContactActivated)
            ChatContactTabFragment.SetMissedCount(-1);

        ChatContactRowData objRowData = ChatContactRecyclerView.FindChatContactData(sContactName);

        if(!bMsgTypeOutgoing && objRowData != null)
        {
            if(!objRowData.IsContactSelected())
                objRowData.SetMissedCount(-1);
        }

        if(objRowData != null && objRowData.IsContactSelected())
            ChatMsgRecyclerView.PostChatMessageText(sContactName, sMsgText, bMsgTypeOutgoing);

        if(!bMsgTypeOutgoing && mChatContactRecyclerView != null)
            mChatContactRecyclerView.UpdateContactRowView(sContactName);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    private static int FindChatContactPosition(String sContactName)
    {
        for(int nRowNo = 0; nRowNo < mChatContactData.size(); nRowNo++)
        {
            ChatContactRowData objRowData = (ChatContactRowData) mChatContactData.get(nRowNo);

            if(objRowData == null)
                continue;

            String sName = objRowData.GetContactName();

            if(Objects.equals(sName, sContactName))
            {
                return nRowNo;
            }
        }

        return -1;
    }

    private static ChatContactRowData FindChatContactData(String sContactName)
    {
        int nPosition = FindChatContactPosition(sContactName);

        if(nPosition == -1)
            return null;

        return (ChatContactRowData) mChatContactData.get(nPosition);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    private void OnChatContactStatus(String sContactName, int nStatusId)
    {
        ChatContactRowData objRowData = FindChatContactData(sContactName);

        if(objRowData == null)
            return;

        objRowData.SetStatusId(nStatusId);
        UpdateContactRowView(sContactName);
    }

    public static void PostChatContactStatus(String sUserName, int nStatusId)
    {
        if(mChatContactRecyclerView == null)
            return;

        mChatContactRecyclerView.OnChatContactStatus(sUserName, nStatusId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialized()
    {
        VaxPhoneSIP.m_objVaxVoIP.LoadChatContactAll();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void UpdateContactRowView(String sContactName)
    {
        int nPosition = FindChatContactPosition(sContactName);

        if (nPosition != -1)
            UpdateRow(nPosition);
    }
}
