package vaxsoft.com.vaxphone.MainTab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerMenu;
import vaxsoft.com.vaxphone.MainTab.CallTab.CallTabFragment;
import vaxsoft.com.vaxphone.MainTab.ChatTab.ChatContactTabFragment;
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.MainUtil.IncomingCallDialog;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.RecentTab.RecentTabFragment;

public class MainTabActivity extends AppCompatActivity
{
    private static final int RECENT_FRAGMENT_TAB_ID = 0;
    private static final int CALL_FRAGMENT_TAB_ID   = 1;
    private static final int CHAT_FRAGMENT_TAB_ID   = 2;

    private final int TOTAL_FRAGMENT_TAB_COUNT = 3;

    private Toolbar mToolbar;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public ViewPager mViewPager;
    private TabLayout mTabLayout;

    private Fragment[] m_aFragTabs;
    private ActionBar mActionBar;

    private IncomingCallDialog m_objIncomingCallDialog = null;
    private MainDrawerMenu m_objMainDrawerMenu = null;

    private static int m_nSelectedTabId = CALL_FRAGMENT_TAB_ID;

    @SuppressLint("StaticFieldLeak")
    private static MainTabActivity mMainTab = null;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaxphone_tabbed);

        mMainTab = this;

        m_objMainDrawerMenu = new MainDrawerMenu(this);
        m_aFragTabs = new Fragment[TOTAL_FRAGMENT_TAB_COUNT];

        LoadViewAll();
        LoadFragmentTabAll();

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();

        m_objMainDrawerMenu.SetMainDrawerMenu(mToolbar);
        m_objMainDrawerMenu.UpdateDrawerUI();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        ActivateTabSelectedListener();
        mViewPager.setCurrentItem(CALL_FRAGMENT_TAB_ID);
        mTabLayout.setupWithViewPager(mViewPager);

        AdjustTabLayout();
    }

    private void LoadViewAll()
    {
        mViewPager = findViewById(R.id.container);
        mTabLayout = findViewById(R.id.tabs);
        mToolbar = findViewById(R.id.toolbar);
    }

    private void AdjustTabLayout()
    {
        mTabLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                for (int nTabId = 0; nTabId < TOTAL_FRAGMENT_TAB_COUNT; nTabId++)
                {
                    TabLayout.Tab objTab = mTabLayout.getTabAt(nTabId);
                    if (objTab == null) continue;

                    objTab.setIcon(mSectionsPagerAdapter.getPageIcon(nTabId));

                    Drawable TabIcon = objTab.getIcon();
                    if (TabIcon == null) continue;

                    TabIcon.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

                     if (nTabId == CALL_FRAGMENT_TAB_ID )
                        TabIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    private void LoadMissedCounts()
    {
        RecentTabFragment.LoadMissedCount();
        ChatContactTabFragment.LoadMissedCount();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        if (m_objMainDrawerMenu.IsDrawerOpen())
            m_objMainDrawerMenu.CloseDrawer();
        else
            super.onBackPressed();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void LoadFragmentTabAll()
    {
        m_aFragTabs[RECENT_FRAGMENT_TAB_ID] = new RecentTabFragment();
        m_aFragTabs[CALL_FRAGMENT_TAB_ID] = new CallTabFragment();
        m_aFragTabs[CHAT_FRAGMENT_TAB_ID] = new ChatContactTabFragment();
    }

    private void ActivateTabSelectedListener()
    {
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager)
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                int nPosition = tab.getPosition();
                OnTabSelected(nPosition);

                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
                int nPosition = tab.getPosition();
                OnTabUnSelected(nPosition);

                if (tab.getIcon() != null)
                   tab.getIcon().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
            }
        });
    }

    private void OnTabSelected(int nTabId)
    {
        m_nSelectedTabId = nTabId;

        switch (nTabId)
        {
            case RECENT_FRAGMENT_TAB_ID:

                SetActionBarTitle("Recent");

                RecentTabFragment objRecentFragment = (RecentTabFragment) m_aFragTabs[RECENT_FRAGMENT_TAB_ID];
                objRecentFragment.OnTabSelected(true);

                break;

            case CALL_FRAGMENT_TAB_ID:

                SetActionBarTitle("Call");

                CallTabFragment objCallFragment = (CallTabFragment) m_aFragTabs[CALL_FRAGMENT_TAB_ID];
                objCallFragment.OnTabSelected(true);

                break;

            case CHAT_FRAGMENT_TAB_ID:

                SetActionBarTitle("Chat Contacts");

                ChatContactTabFragment objChatContactFragment = (ChatContactTabFragment) m_aFragTabs[CHAT_FRAGMENT_TAB_ID];
                objChatContactFragment.OnTabSelected(true);

                break;
        }
    }

    private void OnTabUnSelected(int nTabId)
    {
        switch (nTabId)
        {
            case RECENT_FRAGMENT_TAB_ID:

                RecentTabFragment objRecentFragment = (RecentTabFragment) m_aFragTabs[RECENT_FRAGMENT_TAB_ID];
                objRecentFragment.OnTabSelected(false);

                break;

            case CALL_FRAGMENT_TAB_ID:

                CallTabFragment objCallFragment = (CallTabFragment) m_aFragTabs[CALL_FRAGMENT_TAB_ID];
                objCallFragment.OnTabSelected(false);

                break;

            case CHAT_FRAGMENT_TAB_ID:

                ChatContactTabFragment objChatContactFragment = (ChatContactTabFragment) m_aFragTabs[CHAT_FRAGMENT_TAB_ID];
                objChatContactFragment.OnTabSelected(false);

                break;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void SetActionBarTitle(String sTitle)
    {
        if (mActionBar != null)
            mActionBar.setTitle(sTitle);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case RECENT_FRAGMENT_TAB_ID:
                    return m_aFragTabs[RECENT_FRAGMENT_TAB_ID];

                case CALL_FRAGMENT_TAB_ID:
                    return m_aFragTabs[CALL_FRAGMENT_TAB_ID];

                case CHAT_FRAGMENT_TAB_ID:
                    return m_aFragTabs[CHAT_FRAGMENT_TAB_ID];
            }

            return null;
        }

        @Override
        public int getCount()
        {
            return TOTAL_FRAGMENT_TAB_COUNT;
        }

        int getPageIcon(int position)
        {
            switch (position)
            {
                case RECENT_FRAGMENT_TAB_ID:
                    return R.drawable.ic_recent;

                case CALL_FRAGMENT_TAB_ID:
                    return R.drawable.ic_call;

                case CHAT_FRAGMENT_TAB_ID:
                    return R.drawable.ic_chat;
            }
            return 0;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public static void PostIncomingCall(String sCallerName, String sCallerId)
    {
        if (mMainTab != null)
            mMainTab.OnIncomingCall(sCallerName, sCallerId);
    }

    private void OnIncomingCall(String sCallerName, String sCallerId)
    {
        m_objIncomingCallDialog = new IncomingCallDialog(this, sCallerName, sCallerId);
        m_objIncomingCallDialog.ShowDialog();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static void PostIncomingCallEnded(String sCallId)
    {
        if (mMainTab != null)
            mMainTab.OnIncomingCallEnded(sCallId);
    }

    private void OnIncomingCallEnded(String sCallId)
    {
        if (m_objIncomingCallDialog == null)
            return;

        m_objIncomingCallDialog.HideDialog();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static void SetChatContactBadgeCount(int nMissedCount)
    {
        if (mMainTab != null)
            mMainTab.OnSetChatContactBadgeCount(nMissedCount);
    }

    private void OnSetChatContactBadgeCount(int nMissedCount)
    {
        View ChatView;

        TabLayout.Tab objChatTab = mTabLayout.getTabAt(CHAT_FRAGMENT_TAB_ID);

        if (objChatTab == null)
            return;

        ChatView = objChatTab.getCustomView();

        if (ChatView == null && nMissedCount == 0)
            return;

        if (ChatView != null)
        {
            TextView BadgeView = ChatView.findViewById(R.id.tv_badge);

            if (nMissedCount == 0)
            {
                BadgeView.setVisibility(View.GONE);
                return;
            }

            BadgeView.setText(String.valueOf(nMissedCount));
            BadgeView.setVisibility(View.VISIBLE);

            return;
        }

        View view = getLayoutInflater().inflate(R.layout.layout_chat_badge_view, null);

        TextView Badge = view.findViewById(R.id.tv_badge);
        Badge.setText(String.valueOf(nMissedCount));

        objChatTab.setCustomView(view);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static void SetRecentBadgeCount(int nMissedCount)
    {
        if (mMainTab != null)
            mMainTab.OnSetRecentBadgeCount(nMissedCount);
    }

    private void OnSetRecentBadgeCount(int nMissedCount)
    {
        View RecentView;

        TabLayout.Tab objRecentTab = mTabLayout.getTabAt(RECENT_FRAGMENT_TAB_ID);

        if (objRecentTab == null)
            return;

        RecentView = objRecentTab.getCustomView();

        if (RecentView == null && nMissedCount == 0)
            return;

        if (RecentView != null)
        {
            TextView BadgeView = RecentView.findViewById(R.id.tv_badge);

            if (nMissedCount == 0)
            {
                BadgeView.setVisibility(View.GONE);
                return;
            }

            BadgeView.setText(String.valueOf(nMissedCount));
            BadgeView.setVisibility(View.VISIBLE);

            return;
        }

        View view = getLayoutInflater().inflate(R.layout.layout_recent_badge_view, null);

        TextView Badge = view.findViewById(R.id.tv_badge);
        Badge.setText(String.valueOf(nMissedCount));

        objRecentTab.setCustomView(view);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LoadMissedCounts();
    }

    @Override
    protected void onDestroy()
    {
        mMainTab = null;
        super.onDestroy();
    }
}
