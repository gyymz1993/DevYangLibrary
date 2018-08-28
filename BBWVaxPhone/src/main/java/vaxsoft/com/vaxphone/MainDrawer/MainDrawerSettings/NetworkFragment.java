package vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreNetwork;

public class NetworkFragment extends Fragment implements ViewGroup.OnFocusChangeListener, CompoundButton.OnCheckedChangeListener
{
    EditText LocalPortSIP, LocalPortRTP;
    CheckBox ChooseRandomlySIP, ChooseRandomlyRTP, AutoReachability;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_network, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        Toolbar toolbar = view.findViewById(R.id.actionbar);
        ((MainTabActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainTabActivity) getActivity()).getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setTitle("Network");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        InitViews(view);
        InitClickListners();
        UpdateUI();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private void InitViews(View view)
    {
        LocalPortSIP = view.findViewById(R.id.edit_text_local_port_sip);
        LocalPortRTP = view.findViewById(R.id.edit_text_local_port_rtp);
        ChooseRandomlySIP = view.findViewById(R.id.checkbox_choose_randomly_sip);
        ChooseRandomlyRTP = view.findViewById(R.id.checkbox_choose_randomly_rtp);
        AutoReachability = view.findViewById(R.id.checkbox_auto_reachability);
    }

    private void InitClickListners()
    {
        LocalPortSIP.setOnFocusChangeListener(this);
        LocalPortRTP.setOnFocusChangeListener(this);
        ChooseRandomlySIP.setOnCheckedChangeListener(this);
        ChooseRandomlyRTP.setOnCheckedChangeListener(this);
        AutoReachability.setOnCheckedChangeListener(this);
    }

    private void UpdateUI()
    {
        StoreNetwork objStoreNetwork = new StoreNetwork();

        String sLocalPortSIP = String.valueOf(objStoreNetwork.GetLocalPortSIP());
        LocalPortSIP.setText(sLocalPortSIP);

        String sLocalPortRTP = String.valueOf(objStoreNetwork.GetLocalPortRTP());
        LocalPortRTP.setText(sLocalPortRTP);

        boolean bChooseRandomlySIP = objStoreNetwork.IsChooseRandomlySIP();
        ChooseRandomlySIP.setChecked(bChooseRandomlySIP);

        boolean bChooseRandomlyRTP = objStoreNetwork.IsChooseRandomlyRTP();
        ChooseRandomlyRTP.setChecked(bChooseRandomlyRTP);

        boolean bAutoReachability = objStoreNetwork.IsAutoReachability();
        AutoReachability.setChecked(bAutoReachability);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onFocusChange(View view, boolean Focus)
    {
        switch (view.getId())
        {
            case R.id.edit_text_local_port_sip:
                if (!Focus)
                    onFocusChangeLocalPortSIP();
                break;

            case R.id.edit_text_local_port_rtp:
                if (!Focus)
                    onFocusChangeLocalPortRTP();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
    {
        switch (compoundButton.getId())
        {
            case R.id.checkbox_choose_randomly_sip:
                onCheckedChangedChooseRandomlySIP(isChecked);
                break;

            case R.id.checkbox_choose_randomly_rtp:
                onCheckedChangedChooseRandomlyRTP(isChecked);
                break;

            case R.id.checkbox_auto_reachability:
                onCheckedChangedAutoReachability(isChecked);
                break;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    private void onFocusChangeLocalPortSIP()
    {
        String sLocalPortSIP = LocalPortSIP.getText().toString().trim();

        if(sLocalPortSIP.length() == 0)
            return;

        int nLocalPortSIP = Integer.parseInt(sLocalPortSIP);

        StoreNetwork objStoreNetwork = new StoreNetwork();
        objStoreNetwork.SetLocalPortSIP(nLocalPortSIP);
    }

    private void onFocusChangeLocalPortRTP()
    {
        String sLocalPortRTP = LocalPortRTP.getText().toString().trim();

        if(sLocalPortRTP.length() == 0)
            return;

        int nLocalPortRTP = Integer.parseInt(sLocalPortRTP);

        StoreNetwork objStoreNetwork = new StoreNetwork();
        objStoreNetwork.SetLocalPortRTP(nLocalPortRTP);
    }

    private void onCheckedChangedChooseRandomlySIP(boolean isChecked)
    {
        StoreNetwork objStoreNetwork = new StoreNetwork();
        objStoreNetwork.SetChooseRandomlySIP(isChecked);
    }

    private void onCheckedChangedChooseRandomlyRTP(boolean isChecked)
    {
        StoreNetwork objStoreNetwork = new StoreNetwork();
        objStoreNetwork.SetChooseRandomlyRTP(isChecked);
    }

    private void onCheckedChangedAutoReachability(boolean isChecked)
    {
        StoreNetwork objStoreNetwork = new StoreNetwork();
        objStoreNetwork.SetAutoReachability(isChecked);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public static int GetNetworkPortSIP()
    {
        StoreNetwork objStore = new StoreNetwork();
        return objStore.GetLocalPortSIP();
    }

    public static boolean IsRandomPortSIP()
    {
        StoreNetwork objStore = new StoreNetwork();
        return objStore.IsChooseRandomlySIP();
    }

    public static int GetNetworkPortRTP()
    {
        StoreNetwork objStore = new StoreNetwork();
        return objStore.GetLocalPortRTP();
    }

    public static boolean IsRandomPortRTP()
    {
        StoreNetwork objStore = new StoreNetwork();
        return objStore.IsChooseRandomlyRTP();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
}
