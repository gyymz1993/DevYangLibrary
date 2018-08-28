package vaxsoft.com.vaxphone.MainTab.ChatTab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import vaxsoft.com.vaxphone.CustomViews.TabFragment.CustomTabFragment;
import vaxsoft.com.vaxphone.PhoneSIP.Contacts.Contacts;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class ChatContactTabFragment extends CustomTabFragment
{
    TextView TextViewNoRecords;
    FloatingActionButton m_objFAB;

    LinearLayout AddSip, AddFromContacts;
    Animation FabOpen, FabClose, FabRotateClockwise, FabRotateAntiClockwise;

    ChatContactRecyclerView mChatContactRecyclerView = null;

    private Contacts m_objContacts;

    private boolean bIsOpen = false;
    private final int CONTACT_PICKER_RESULT = 1001;

    static int m_nChatContactMissedCount = 0;
    public static Boolean m_bChatContactActivated = false;

    private static final String CHAT_CONTACT_MISSED_COUNT_KEY = "Chat Contact Missed Count";
    private static ChatContactTabFragment mChatContactFragment = null;

    //////////////////////////////////////////////////////////////////////////////////////

    public ChatContactTabFragment()
    {
        mChatContactFragment = this;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        InitViews(view);
        InitClickListeners();

        InitObjects();
        UpdateUI();

        super.onViewCreated(view, savedInstanceState);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private void InitViews(View view)
    {
        m_objFAB = view.findViewById(R.id.MainFab);
        AddSip = view.findViewById(R.id.AddSip);
        AddFromContacts = view.findViewById(R.id.AddFromContacts);

        FabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        FabRotateClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        FabRotateAntiClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anti_clockwise);
        TextViewNoRecords = view.findViewById(R.id.no_records_chat);
        mChatContactRecyclerView = view.findViewById(R.id.recycler_view_chat_contact);
    }

    private void InitClickListeners()
    {
        m_objFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnClickAddButton(v);
            }
        });
    }

    private void InitObjects()
    {
        m_objContacts = new Contacts();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    private void OnClickAddButton(View view)
    {
        if(bIsOpen)
        {
            m_objFAB.startAnimation(FabRotateAntiClockwise);
            AddSip.startAnimation(FabClose);
            AddFromContacts.startAnimation(FabClose);

            AddSip.setClickable(false);
            AddFromContacts.setClickable(false);

            bIsOpen = false;
            return;
        }

        m_objFAB.startAnimation(FabRotateClockwise);
        AddSip.startAnimation(FabOpen);
        AddFromContacts.startAnimation(FabOpen);

        AddSip.setClickable(true);
        AddFromContacts.setClickable(true);

        bIsOpen = true;

        AddSip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnClickAddAccountSIP(v);
            }
        });


        AddFromContacts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnClickAddFromContacts(v);
            }
        });

    }

    private int GetPixelValue(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void OnClickAddAccountSIP(View view)
    {
        AlertDialog.Builder objBuidler = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);

        objBuidler.setTitle(R.string.add_account_sip);

        LinearLayout parentLayout = new LinearLayout(getActivity());

        final EditText AccountSIPEditText = new EditText(getActivity());
        AccountSIPEditText.setHint("Account SIP");
        AccountSIPEditText.setSingleLine();
        AccountSIPEditText.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        int nLeftMargin = GetPixelValue((int)getResources().getDimension(R.dimen.activity_horizontal_margin));
        int nTopMargin = GetPixelValue((int)getResources().getDimension(R.dimen.activity_horizontal_margin));
        int nRightMargin = GetPixelValue((int)getResources().getDimension(R.dimen.activity_horizontal_margin));
        int nBottomMargin = GetPixelValue((int)getResources().getDimension(R.dimen.activity_horizontal_margin));

        layoutParams.setMargins(nLeftMargin, nTopMargin, nRightMargin, nBottomMargin);

        AccountSIPEditText.setLayoutParams(layoutParams);
        parentLayout.addView(AccountSIPEditText);
        objBuidler.setView(parentLayout);

        objBuidler.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String sChatContact = AccountSIPEditText.getText().toString().trim();

                VaxPhoneSIP.m_objVaxVoIP.ChatAddContact(sChatContact, true);

                int nRowId = 0;

                mChatContactRecyclerView.OpenChatMsgView(nRowId, true);

                m_objFAB.performClick();

                UpdateUI();
            }
        });

        objBuidler.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        AlertDialog objAlertDialog = objBuidler.create();
        objAlertDialog.show();
    }

    private void OnClickAddFromContacts(View v)
    {
        m_objContacts.ShowContactView(this, CONTACT_PICKER_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (data == null)
            return;

        String sContactName = m_objContacts.GetPickedContactName(getActivity(), data);

        if (resultCode == -1)
        {
            switch (requestCode)
            {
                case CONTACT_PICKER_RESULT:

                    VaxPhoneSIP.m_objVaxVoIP.ChatAddContact(sContactName, true);

                    m_objFAB.performClick();

                    UpdateUI();

                break;
            }
        }
        else
        {
            Log.w("Main MainActivity", "Error Result");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void OnFragmentActivated()
    {
        mChatContactFragment = this;
        m_bChatContactActivated = true;

        mChatContactRecyclerView.OnFragmentActivated();
        UpdateUI();

        super.OnFragmentActivated();
    }

    public void OnFragmentDeactivated()
    {
        mChatContactFragment = null;
        m_bChatContactActivated = false;

        if (mChatContactRecyclerView != null)
            mChatContactRecyclerView.OnFragmentDeactivated();

        super.OnFragmentDeactivated();
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void UpdateUI()
    {
        int nCount = VaxPhoneSIP.m_objVaxVoIP.GetChatContactCount();

        if (nCount <= 0)
        {
            TextViewNoRecords.setVisibility(View.VISIBLE);
            mChatContactRecyclerView.setVisibility(View.GONE);
        }
        else
        {
            if (mChatContactRecyclerView == null)
                return;

            mChatContactRecyclerView.setVisibility(View.VISIBLE);
            TextViewNoRecords.setVisibility(View.GONE);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    public static void LoadMissedCount()
    {
        m_nChatContactMissedCount = ReadMissedCount();

        if (m_nChatContactMissedCount == 0)
            return;

        MainTabActivity.SetChatContactBadgeCount(m_nChatContactMissedCount);
    }

    public static void SetMissedCount(int nValue)
    {
        if(nValue == 0)
        {
            m_nChatContactMissedCount = 0;
        }
        else
        {
            m_nChatContactMissedCount++;
        }

        MainTabActivity.SetChatContactBadgeCount(m_nChatContactMissedCount);
        StoreMissedCount();
    }

    private static void StoreMissedCount()
    {
        PreferenceUtil.WritePreferenceValue(CHAT_CONTACT_MISSED_COUNT_KEY, m_nChatContactMissedCount);
    }

    private static int ReadMissedCount()
    {
        return PreferenceUtil.ReadPreferenceValue(CHAT_CONTACT_MISSED_COUNT_KEY, 0);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static void PostChatContactAdded()
    {
        if (mChatContactFragment != null)
        {
            mChatContactFragment.UpdateUI();
        }
    }

    public static void PostChatContactDeleted()
    {
        if (mChatContactFragment != null)
        {
            mChatContactFragment.UpdateUI();
        }
    }
}
