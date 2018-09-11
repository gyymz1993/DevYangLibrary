package vaxsoft.com.vaxphone.MainTab.ChatTab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.CustomViews.RecyclerView.CustomRecyclerView;
import vaxsoft.com.vaxphone.CustomViews.RecyclerView.ICustomRecyclerView;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreChatMsg;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreDataChatMsgs;

public class ChatMsgRecyclerView extends CustomRecyclerView implements ICustomRecyclerView
{
    private static String m_sContactName = "";

    private ArrayList mListData = null;
    private LayoutInflater mLayoutInflater;

    private static ChatMsgRecyclerView mChatMsgRecyclerView = null;

    ////////////////////////////////////////////////////////////////////////////////////////

    public ChatMsgRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        mChatMsgRecyclerView = this;

        super.m_sActionModeTitle = "Delete Chat Messages";

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setStackFromEnd(true);
        setLayoutManager(linearLayoutManager);

        mLayoutInflater = LayoutInflater.from(context);

        OnAppear();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private void OnAppear()
    {
        mListData = new ArrayList();

        LoadChatMessageAll();
    }

    public void OnDisAppear()
    {
        mChatMsgRecyclerView = null;
        m_sContactName = "";

        mListData = null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private void LoadChatMessageAll()
    {
        StoreChatMsg objStoreChatMsg = new StoreChatMsg();
        ArrayList MessageAllList = objStoreChatMsg.GetChatMsgAll(m_sContactName);

        for (int nRecordNo = 0; nRecordNo < MessageAllList.size(); nRecordNo++)
        {
            StoreDataChatMsgs objStoreDataChatMsgs = (StoreDataChatMsgs) MessageAllList.get(nRecordNo);
            AddChatMessageText(objStoreDataChatMsgs.m_sContactName, objStoreDataChatMsgs.m_sMsgText, objStoreDataChatMsgs.m_bMsgTypeOutgoing);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public RecyclerView.ViewHolder OnViewHolderCreate(ViewGroup parent, int viewType)
    {
        View objView = mLayoutInflater.inflate(R.layout.layout_conversation_row, parent, false);
        return new ChatMsgViewHolder(objView);
    }

    @Override
    public void OnViewHolderBindUI(RecyclerView.ViewHolder holder, Boolean bDeleteActivated, Boolean bDeleteSelected, int nPosition, int nViewType)
    {
        holder.itemView.setTag(holder);
        holder.itemView.setSelected(false);

        ChatMsgRowData objChatMsgRowData = (ChatMsgRowData) mListData.get(nPosition);

        boolean bOutboundMsgType = objChatMsgRowData.IsChatMsgTypeOutgoing();
        String sMessage = objChatMsgRowData.GetChatMsgText();

        if (bOutboundMsgType)
        {
            ((ChatMsgViewHolder) holder).SendMsg.setText(sMessage);
            ((ChatMsgViewHolder) holder).SendMsg.setVisibility(VISIBLE);
            ((ChatMsgViewHolder) holder).RecieveMsg.setVisibility(GONE);
        }
        else
        {
            ((ChatMsgViewHolder) holder).RecieveMsg.setText(sMessage);
            ((ChatMsgViewHolder) holder).RecieveMsg.setVisibility(VISIBLE);
            ((ChatMsgViewHolder) holder).SendMsg.setVisibility(GONE);
        }
    }

    @Override
    public void OnViewHolderRowClicked(RecyclerView.ViewHolder holder, int nPosition)
    {

    }

    @Override
    public void OnViewHolderDeleted(int nPosition, int nViewType)
    {
        ChatMsgRowData objChatMsgRowData = (ChatMsgRowData) mListData.get(nPosition);
        String sMsg = objChatMsgRowData.GetChatMsgText();

        StoreChatMsg objStoreChatMsg = new StoreChatMsg();
        objStoreChatMsg.RemoveChatMsg(m_sContactName, sMsg);

        mListData.remove(nPosition);
    }

    @Override
    public void OnViewHolderDeleteSelected(RecyclerView.ViewHolder holder, boolean bDeleteEnabled, int nPosition, int nViewType)
    {

    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private class ChatMsgViewHolder extends RecyclerView.ViewHolder
    {
        TextView RecieveMsg;
        TextView SendMsg;

        ChatMsgViewHolder(View itemView)
        {
            super(itemView);

            RecieveMsg = itemView.findViewById(R.id.text_view_receiver);
            SendMsg = itemView.findViewById(R.id.text_view_sender);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    public static void PostChatMessageText(String sUserName, String sMsgText, boolean bMsgTypeOutgoing)
    {
        if (mChatMsgRecyclerView == null)
            return;

        mChatMsgRecyclerView.AddChatMessageText(sUserName, sMsgText, bMsgTypeOutgoing);
    }

    private void AddChatMessageText(String sUserName, String sMsgText, boolean bMsgTypeOutgoing)
    {
        ChatMsgRowData objChatMsgRowData = new ChatMsgRowData();
        objChatMsgRowData.SetChatMsg(sMsgText, bMsgTypeOutgoing);

        mListData.add(objChatMsgRowData);
        super.AddRowAtEnd(VIEW_TYPE_ROW);

        this.scrollToPosition(mListData.size() - 1);
    }

    public static void PostMessageViewData(ChatContactRowData objChatContactRowData)
    {
        m_sContactName = objChatContactRowData.GetContactName();
    }

    /////////////////////////////////////////////////////////////////////////////////////
}
