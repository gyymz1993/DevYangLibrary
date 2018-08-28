package vaxsoft.com.vaxphone.MainTab.RecentTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vaxsoft.com.vaxphone.CustomViews.TabFragment.CustomTabFragment;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.CallTab.DialpadFragment;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class RecentTabFragment extends CustomTabFragment
{
    TextView TextViewNoRecords;
    RecentRecyclerView mRecentRecyclerView;

    private static RecentTabFragment mRecentFragment;
    private static int m_nRecentMissedCount = 0;

    private static final String RECENT_MISSED_COUNT_KEY = "Recent Missed Count";

    ////////////////////////////////////////////////////////////////////////////////////

    public RecentTabFragment()
    {

    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        InitViews(view);
        UpdateUI();

        super.onViewCreated(view, savedInstanceState);
    }

    private void InitViews(View view)
    {
        TextViewNoRecords = view.findViewById(R.id.no_records);
        mRecentRecyclerView = view.findViewById(R.id.recent_recyclerview);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void OnFragmentActivated()
    {
        mRecentFragment = this;

        mRecentRecyclerView.OnFragmentActivated();
        UpdateUI();

        super.OnFragmentActivated();
    }

    public void OnFragmentDeactivated()
    {
        mRecentFragment = null;

        if (mRecentRecyclerView != null)
            mRecentRecyclerView.OnFragmentDeactivated();

        super.OnFragmentDeactivated();
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    private void UpdateUI()
    {
        int nCount = VaxPhoneSIP.m_objVaxVoIP.GetCallHistoryCount();

        if (nCount <= 0)
        {
            TextViewNoRecords.setVisibility(View.VISIBLE);
            mRecentRecyclerView.setVisibility(View.GONE);
        }
        else
        {
            if(mRecentRecyclerView == null)
                return;

            mRecentRecyclerView.setVisibility(View.VISIBLE);
            TextViewNoRecords.setVisibility(View.GONE);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public static void LoadMissedCount()
    {
        m_nRecentMissedCount = ReadMissedCount();

        if (m_nRecentMissedCount == 0)
            return;

        MainTabActivity.SetRecentBadgeCount(m_nRecentMissedCount);
    }

    public static void SetMissedCount(int nValue)
    {
        if(nValue == 0)
            m_nRecentMissedCount = 0;
        else
            m_nRecentMissedCount++;

        MainTabActivity.SetRecentBadgeCount(m_nRecentMissedCount);
        StoreMissedCount();
    }

    private static void StoreMissedCount()
    {
        PreferenceUtil.WritePreferenceValue(RECENT_MISSED_COUNT_KEY, m_nRecentMissedCount);
    }

    private static int ReadMissedCount()
    {
        return PreferenceUtil.ReadPreferenceValue(RECENT_MISSED_COUNT_KEY, 0);
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    public static void PostOpenDialPad(String sCallerNum)
    {
        if (mRecentFragment != null)
            mRecentFragment.OpenDialPad(sCallerNum);
    }

    private void OpenDialPad(String sCallerNum)
    {
        Bundle objBundle = new Bundle();
        objBundle.putString("CallerNum", sCallerNum);

        DialpadFragment objDialpadFragment = new DialpadFragment();
        objDialpadFragment.setArguments(objBundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, objDialpadFragment).addToBackStack(null).commit();
    }
}
