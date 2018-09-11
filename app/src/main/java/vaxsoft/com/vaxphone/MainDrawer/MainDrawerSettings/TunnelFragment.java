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
import vaxsoft.com.vaxphone.VaxPhoneSIP;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings.StoreCryptTunnel;

public class TunnelFragment extends Fragment
{
    EditText TunnelIP, TunnelPort;
    CheckBox TunnelEnable;
    private String m_sTunnelIP;
    private String m_sTunnelPort;
    private boolean m_bEnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tunnel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        Toolbar toolbar = view.findViewById(R.id.actionbar);

        ((MainTabActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainTabActivity) getActivity()).getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setTitle("Encryption Tunnel");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        InitViews(view);
        InitListeners();

        UpdateUI();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void PostInitialized()
    {
        StoreCryptTunnel objStoreCryptTunnel = new StoreCryptTunnel();

        String sIP = objStoreCryptTunnel.GetTunnelIP();
        String sPort = objStoreCryptTunnel.GetTunnelPort();
        boolean bEnable = objStoreCryptTunnel.GetTunnelEnable();

        VaxPhoneSIP.m_objVaxVoIP.CryptCOMM(bEnable, sIP, Integer.parseInt(sPort));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void InitViews(View view)
    {
        TunnelIP = view.findViewById(R.id.tunnel_ip);
        TunnelPort = view.findViewById(R.id.tunnel_port);
        TunnelEnable = view.findViewById(R.id.tunnel_activation);
    }

    private void InitListeners()
    {
        OnTunnelIPFocusChange();
        OnTunnelPortFocusChange();
        OnTunnelEnableStateChange();
    }

    private void UpdateUI()
    {
        StoreCryptTunnel objStoreCryptTunnel = new StoreCryptTunnel();

        m_sTunnelIP = objStoreCryptTunnel.GetTunnelIP();

        if (m_sTunnelIP.equals(""))
            TunnelIP.setHint(getContext().getString((R.string.tunnel_server_ip)));
        else
            TunnelIP.setText(m_sTunnelIP);

        m_sTunnelPort = objStoreCryptTunnel.GetTunnelPort();

        if (m_sTunnelPort.equals(""))
             TunnelPort.setHint(getContext().getString(R.string.tunnel_server_port));
        else
            TunnelPort.setText(m_sTunnelPort);

        m_bEnable = objStoreCryptTunnel.GetTunnelEnable();
        TunnelEnable.setChecked(m_bEnable);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    private void OnTunnelIPFocusChange()
    {
        TunnelIP.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    m_sTunnelIP = TunnelIP.getText().toString().trim();

                    StoreCryptTunnel objStoreCryptTunnel = new StoreCryptTunnel();
                    objStoreCryptTunnel.SetTunnelIP(m_sTunnelIP);
                }
            }
        });
    }

    private void OnTunnelPortFocusChange()
    {
        TunnelPort.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    m_sTunnelPort = String.valueOf(TunnelPort.getText().toString().trim());

                    StoreCryptTunnel objStoreCryptTunnel = new StoreCryptTunnel();
                    objStoreCryptTunnel.SetTunnelPort(m_sTunnelPort);
                }
            }
        });
    }

    private void OnTunnelEnableStateChange()
    {
        TunnelEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                m_bEnable = isChecked;

                StoreCryptTunnel objStoreCryptTunnel = new StoreCryptTunnel();
                objStoreCryptTunnel.SetTunnelEnable(m_bEnable);

                VaxPhoneSIP.m_objVaxVoIP.CryptCOMM(m_bEnable, m_sTunnelIP, !m_sTunnelPort.equals("") ? Integer.parseInt(m_sTunnelPort) : 8891);
            }
        });
    }
}
