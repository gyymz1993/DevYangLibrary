package vaxsoft.com.vaxphone.MainDrawer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.AccountLogin.AccountLoginActivity;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerStatus.StatusAdapter;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerStatus.StatusData;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.SettingsFragment;
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class MainDrawerMenu implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private Spinner mSpinnerStatus;

    private TextView TextViewUserInfo;

    private MainTabActivity mMainTabActivity = null;

    public MainDrawerMenu(MainTabActivity activity)
    {
        mMainTabActivity = activity;

        InitViews();
    }

    private void InitViews()
    {
        mDrawerLayout = mMainTabActivity.findViewById(R.id.drawer_layout);
        mNavigationView = mMainTabActivity.findViewById(R.id.nav_view);

        View NavHeaderView = mNavigationView.getHeaderView(0);
        TextViewUserInfo =  NavHeaderView.findViewById(R.id.userinfo);

        mSpinnerStatus = NavHeaderView.findViewById(R.id.spinner);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void SetMainDrawerMenu(Toolbar Toolbar)
    {
        ActionBarDrawerToggle objDrawerToggle = new ActionBarDrawerToggle(mMainTabActivity, mDrawerLayout, Toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(objDrawerToggle);
        objDrawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int nId = item.getItemId();

        switch (nId)
        {
            case R.id.nav_home:
                OnClickHome();
                break;

            case R.id.nav_settings:
                OnClickSettings();
                break;

            case R.id.nav_rate:
                OnClickRateVaxPhone();
                break;

            case R.id.nav_contactUs:
                OnClickContactUs();
                break;

            case R.id.Log_out:
                OnClickLogOut();
                break;
        }

        DrawerLayout drawer = mMainTabActivity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void OnClickHome()
    {
        mMainTabActivity.mViewPager.setCurrentItem(1);
    }

    private void OnClickRateVaxPhone()
    {
        Uri uri = Uri.parse("market://details?id=" + mMainTabActivity.getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try
        {
            mMainTabActivity.startActivity(intent);
        }
        catch (ActivityNotFoundException e)
        {
            mMainTabActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mMainTabActivity.getPackageName())));
        }
    }

    private void OnClickContactUs()
    {
        String sUrl = "http://www.vaxvoip.com";
        Uri uri = Uri.parse(sUrl);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        try
        {
            mMainTabActivity.startActivity(intent);
        }
        catch (Exception e)
        {
            DialogUtil.ShowDialog(mMainTabActivity, "No internet browser found!");
            e.printStackTrace();
        }
    }

    private void OnClickLogOut()
    {
        Intent objIntent = new Intent(mMainTabActivity, AccountLoginActivity.class);
        mMainTabActivity.startActivity(objIntent);
        mMainTabActivity.finish();
    }

    private void OnClickSettings()
    {
        SettingsFragment objSettingsFragment = new SettingsFragment();

        FragmentManager objFragManager = mMainTabActivity.getSupportFragmentManager();
        FragmentTransaction objFragTransaction = objFragManager.beginTransaction();

        objFragTransaction.replace(android.R.id.content, objSettingsFragment ,"Settings Fragment");
        objFragTransaction.addToBackStack("Settings Fragment");
        objFragTransaction.commit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void UpdateDrawerUI()
    {
        StringBuilder sAuthLogin = new StringBuilder();
        StringBuilder sDoaminRealm = new StringBuilder();

        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(null, null, sAuthLogin, null, sDoaminRealm, null, null, null);

        TextViewUserInfo.setText(String.format("%s@%s", sAuthLogin.toString(), sDoaminRealm.toString()));

        SetSpinner();
    }

    private void SetSpinner()
    {
        final ArrayList<StatusData> StatusList = new ArrayList<>();

        StatusList.add(new StatusData("Online",R.drawable.ic_contact_online));
        StatusList.add(new StatusData("Offline",R.drawable.ic_contact_offline));
        StatusList.add(new StatusData("Away",R.drawable.ic_contact_away));
        StatusList.add(new StatusData("On Phone",R.drawable.ic_contact_on_phone));
        StatusList.add(new StatusData("Busy",R.drawable.ic_contact_busy));

        final StatusAdapter objStatusAdapter = new StatusAdapter(mMainTabActivity, R.layout.spinner_row, R.id.txt,StatusList);
        mSpinnerStatus.setAdapter(objStatusAdapter);

        mSpinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int nSelected, long l)
            {
                view.setBackgroundColor(Color.TRANSPARENT);

                TextView sStatus = view.findViewById(R.id.txt);
                sStatus.setTextColor(Color.WHITE);

                VaxPhoneSIP.m_objVaxVoIP.ChatSetMyStatus(nSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public boolean IsDrawerOpen()
    {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    public void CloseDrawer()
    {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}