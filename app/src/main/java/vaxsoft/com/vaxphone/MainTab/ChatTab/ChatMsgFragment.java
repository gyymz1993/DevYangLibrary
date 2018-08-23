package vaxsoft.com.vaxphone.MainTab.ChatTab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class ChatMsgFragment extends Fragment
{
    EditText TextMessage;
    AppCompatImageButton SendMessage;
    AppCompatButton BtnAcceptPresence;

    RelativeLayout PresenceView, ChatMsgParentView;;

    private ChatMsgRecyclerView mChatMsgRecyclerView;

    private static ChatMsgFragment mChatMsgFragment;

    private static String m_sChatMsgContactName = "";
    private static boolean m_bChatMsgPresence = false;
    private static String m_sAddContactMsg = null;

    private static ChatContactRowData m_objChatContactRowData = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
   {
       return inflater.inflate(R.layout.fragment_conversation, container, false);
   }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        InitViews(view);
        InitClickListeners();

        SetActionBar(view, m_sChatMsgContactName);

        OnAppear();
    }

    @Override
    public void onPause()
    {
        super.onPause();

        InputMethodManager objInputMethodManager = (InputMethodManager) VaxPhoneAPP.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if(objInputMethodManager != null && objInputMethodManager.isActive())
            objInputMethodManager.hideSoftInputFromWindow(ChatMsgParentView.getWindowToken(), 0);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        mChatMsgFragment = null;

        mChatMsgRecyclerView.OnDisAppear();

        if (m_objChatContactRowData != null)
            m_objChatContactRowData.SetContactSelected(false);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    void OnAppear()
    {
        mChatMsgFragment = this;

        if(m_bChatMsgPresence)
            PresenceView.setVisibility(View.GONE);
        else
            PresenceView.setVisibility(View.VISIBLE);

        if (m_sAddContactMsg != null && m_sAddContactMsg.length() > 0)
            TextMessage.setText(m_sAddContactMsg);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void InitViews(View view)
    {
        TextMessage = view.findViewById(R.id.edit_text_message);
        SendMessage = view.findViewById(R.id.ic_send);

        PresenceView = view.findViewById(R.id.presence_view);
        ChatMsgParentView = view.findViewById(R.id.ChatMsgParentView);

        BtnAcceptPresence = view.findViewById(R.id.BtnAcceptPresence);

        mChatMsgRecyclerView = view.findViewById(R.id.recycler_conversation);
    }

    private void InitClickListeners()
    {
        SendMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSendMessage();
            }
        });

        BtnAcceptPresence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                OnClickAcceptPresence();
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void SetActionBar(View view, String sContactName)
    {
        Toolbar toolbar = view.findViewById(R.id.chat_conv_actionbar);

        ((MainTabActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainTabActivity) getActivity()).getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setTitle(null);

            LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View ActionBarView = inflator.inflate(R.layout.status_chat_msg, null);

            AppCompatTextView Title = ActionBarView.findViewById(R.id.contact_name_chat_msg);
            Title.setText(sContactName);

            actionBar.setCustomView(ActionBarView);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public void OnClickAcceptPresence()
    {
        if(!VaxPhoneSIP.m_objVaxVoIP.ChatAddContact(m_sChatMsgContactName, true))
            return;

        PresenceView.setVisibility(View.GONE);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void onClickSendMessage()
    {
        String sMsg = TextMessage.getText().toString();
        if (sMsg.length() == 0) return;

        if (!VaxPhoneSIP.m_objVaxVoIP.ChatSendMessageText(m_sChatMsgContactName, sMsg))
            return;

        TextMessage.getText().clear();
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public static void PostMessageViewData(ChatContactRowData objChatContactRowData, String sAddContactMsg)
    {
        m_sChatMsgContactName = objChatContactRowData.GetContactName();
        m_bChatMsgPresence = objChatContactRowData.GetPresence();
        m_sAddContactMsg = sAddContactMsg;

        m_objChatContactRowData = objChatContactRowData;

        ChatMsgRecyclerView.PostMessageViewData(objChatContactRowData);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
}
