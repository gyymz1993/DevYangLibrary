package vaxsoft.com.vaxphone.AccountLogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class AccountInfoFragment extends Fragment
{
    EditText Username, DisplayName, AuthLogin, AuthPassword, DoaminRealm, ServerIP, ServerPort;
    CheckBox RegistrationSIP;
    ImageView ToolbarActionIcon;
    TextView ToolbarTitle;

    //////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_account_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        InitViews(view);
        InitListener();

        SetToolbar();
        GetData();
    }

    private void InitViews(View view)
    {
        ToolbarTitle =  view.findViewById(R.id.label_screen_name);
        ToolbarActionIcon = view.findViewById(R.id.ToolbarActionIcon);

        Username = view.findViewById(R.id.ai_username);
        DisplayName = view.findViewById(R.id.ai_display_name);
        AuthLogin = view.findViewById(R.id.ai_auth_login);
        AuthPassword = view.findViewById(R.id.ai_auth_pwd);
        DoaminRealm = view.findViewById(R.id.ai_domain_realm);
        ServerIP = view.findViewById(R.id.ai_server_ip);
        ServerPort = view.findViewById(R.id.ai_server_port);

        RegistrationSIP = view.findViewById(R.id.ai_sip_registration);
    }

    private void InitListener()
    {
        ToolbarActionIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnClickSave();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void SetToolbar()
    {
        ToolbarTitle.setText(R.string.title_account_info);

        ToolbarActionIcon.setVisibility(View.VISIBLE);
        ToolbarActionIcon.setImageResource(R.drawable.ic_action_check);
    }

    private void OnClickSave()
    {
        String sUsername = Username.getText().toString();
        String sDisplayName = DisplayName.getText().toString();
        String sAuthLogin = AuthLogin.getText().toString();
        String sAuthPassword = AuthPassword.getText().toString();
        String sDoaminRealm = DoaminRealm.getText().toString();
        String sServerIP = ServerIP.getText().toString();
        String sServerPort = ServerPort.getText().toString();

        boolean bRegistrationSIP = RegistrationSIP.isChecked();

        VaxPhoneSIP.SetLoginInfo(sUsername, sDisplayName, sAuthLogin, sAuthPassword, sDoaminRealm, sServerIP, sServerPort, bRegistrationSIP);

        ((AccountLoginActivity) getActivity()).UpdateUI();

        getActivity().onBackPressed();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void GetData()
    {
        StringBuilder sUsername = new StringBuilder();
        StringBuilder sDisplayName = new StringBuilder();
        StringBuilder sAuthLogin = new StringBuilder();
        StringBuilder sAuthPassword = new StringBuilder();
        StringBuilder sDoaminRealm = new StringBuilder();
        StringBuilder sServerIP = new StringBuilder();
        StringBuilder sServerPort = new StringBuilder();

        AtomicBoolean bRegistrationSIP = new AtomicBoolean();

        VaxPhoneSIP.GetLoginInfo(sUsername, sDisplayName, sAuthLogin, sAuthPassword, sDoaminRealm, sServerIP, sServerPort, bRegistrationSIP);

        SetData(sUsername.toString(), sDisplayName.toString(), sAuthLogin.toString(), sAuthPassword.toString(), sDoaminRealm.toString(), sServerIP.toString(), sServerPort.toString(), bRegistrationSIP.get());
    }

    private void SetData(String sUsername, String sDisplayName, String sAuthLogin, String sAuthPassword, String sDoaminRealm, String sServerIP, String sServerPort, boolean bRegistrationSIP)
    {
        if (sUsername.equals(""))
            Username.setText(sAuthLogin);
        else Username.setText(sUsername);

        if (sDisplayName.equals(""))
            DisplayName.setText(sAuthLogin);
        else DisplayName.setText(sDisplayName);

        AuthLogin.setText(sAuthLogin);
        AuthPassword.setText(sAuthPassword);
        DoaminRealm.setText(sDoaminRealm);
        ServerIP.setText(sServerIP);
        ServerPort.setText(sServerPort);

        RegistrationSIP.setChecked(bRegistrationSIP);
    }
}
