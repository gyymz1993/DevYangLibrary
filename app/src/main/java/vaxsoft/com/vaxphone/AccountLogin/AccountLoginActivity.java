package vaxsoft.com.vaxphone.AccountLogin;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.MainUtil.DialogUtil;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class AccountLoginActivity extends AppCompatActivity
{
    private static AccountLoginActivity mLoginActivity = null;
    private static String m_sLastStatusText = "Account is offline";

    private final int ID_REQUEST_READ_CONTACTS = 1000;

    EditText EditTextUsername, EditTextPassword, EditTextServerAddr;
    Switch SwitchRegistrationSIP;
    TextView TextViewStatus;
    Button BtnLogin;

    private Handler mDelayHandler = null;

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mLoginActivity = this;
        mDelayHandler = null;

        VaxPhoneSIP.m_objVaxVoIP.NetworkReachability(true);

        InitViews();
        UpdateUI();
    }

    private void InitViews()
    {
        EditTextUsername = findViewById(R.id.username);
        EditTextPassword = findViewById(R.id.password);
        EditTextServerAddr = findViewById(R.id.server_ip_domain);

        SwitchRegistrationSIP = findViewById(R.id.sip_registration);

        BtnLogin = findViewById(R.id.BtnLogin);
        TextViewStatus = findViewById(R.id.status);
    }

    public void UpdateUI()
    {
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder AuthPassword = new StringBuilder();
        StringBuilder DoaminRealm = new StringBuilder();
        StringBuilder ServerIP = new StringBuilder();
        StringBuilder ServerPort = new StringBuilder();
        AtomicBoolean RegistrationSIP = new AtomicBoolean();

        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, AuthPassword, DoaminRealm, ServerIP, ServerPort, RegistrationSIP);

        String sUsername = Username.toString();
        String sDisplayName = DisplayName.toString();
        String sAuthLogin = AuthLogin.toString();
        String sPassword = AuthPassword.toString();
        String sDoaminRealm = DoaminRealm.toString();
        String sServerIP = ServerIP.toString();
        String sServerPort = ServerPort.toString();
        boolean bRegistrationSIP = RegistrationSIP.get();

       // EditTextUsername.setText(AuthLogin);
       // EditTextPassword.setText(sPassword);

        String sServerAddr = "";

        if (sServerIP.length() != 0)
        {
            if(sServerPort.length() == 0)
                sServerPort = "5060";

            sServerAddr = sServerIP + ":" + sServerPort;
        }

        //EditTextServerAddr.setText(sServerAddr);
        SwitchRegistrationSIP.setChecked(bRegistrationSIP);

        if(VaxPhoneSIP.m_objVaxVoIP.IsOnline())
        {
            BtnLogin.setText("LOG OUT");
        }
        else
        {
            BtnLogin.setText("LOG IN");
        }

        TextViewStatus.setText(m_sLastStatusText);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void BtnAccountInfo(View view)
    {
        UpdateLogInInfo();

        AccountInfoFragment objAccountInfo = new AccountInfoFragment();

        FragmentManager objFragManager = getSupportFragmentManager();
        FragmentTransaction objFragTransaction = objFragManager.beginTransaction();

        objFragTransaction.add(android.R.id.content, objAccountInfo ,"Account Info");
        objFragTransaction.addToBackStack("Account Info");
        objFragTransaction.commit();
    }

    public void BtnLogIn(View view)
    {
        if (!UpdateLogInInfo())
            return;

        if(VaxPhoneSIP.m_objVaxVoIP.IsOnline())
        {
            StopOpenMainTabWithDelay();

            VaxPhoneSIP.m_objVaxVoIP.UnInitialize();
            BtnLogin.setText("LOG IN");

            return;
        }

        if(!Initialize())
            return;

        BtnLogin.setText("LOG OUT");
    }

    private boolean Initialize()
    {
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder AuthPassword = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();
        StringBuilder ServerIP = new StringBuilder();
        StringBuilder ServerPort = new StringBuilder();
        AtomicBoolean RegistrationSIP = new AtomicBoolean();

        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, AuthPassword, DomainRealm, ServerIP, ServerPort, RegistrationSIP);

        String sUsername = Username.toString();
        String sDisplayName = DisplayName.toString();
        String sAuthLogin = AuthLogin.toString();
        String sAuthPassword = AuthPassword.toString();
        String sDomainRealm = DomainRealm.toString();
        String sServerIP = ServerIP.toString();
        int nServerPort = Integer.parseInt(ServerPort.toString());
        boolean bRegistrationSIP = RegistrationSIP.get();

        if(!VaxPhoneSIP.m_objVaxVoIP.Initialize(sDisplayName, sUsername, sAuthLogin, sAuthPassword, sDomainRealm, sServerIP, nServerPort, bRegistrationSIP, this))
            return false;

        if(!bRegistrationSIP)
            StartOpenMainTabWithDelay();

        return true;
    }

    public void BtnGetDemoAccount(View view)
    {
        ShowTestAccountInfo();

        long nDateTime = System.currentTimeMillis();
        String sDateTime = String.valueOf(nDateTime);

        //EditTextUsername.setText(sDateTime);
       // EditTextPassword.setText(sDateTime);

       // EditTextServerAddr.setText("demo.vaxvoip.com:8891");

        SwitchRegistrationSIP.setEnabled(true);
    }

    private void ShowTestAccountInfo()
    {
        String sMsg = "Demo accounts are only for account-to-account VoIP calls. Use demo accounts in different devices to dial/receive VoIP calls.\n\nDial 000 for self testing.\n\n[Powered by VaxVoIP Server SDK]";

        DialogUtil.ShowDialog(this, sMsg);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean UpdateLogInInfo()
    {
        String sAuthLogin = EditTextUsername.getText().toString();
        String sServerAddr = EditTextServerAddr.getText().toString();

        if (sAuthLogin.length() <= 0)
        {
            DialogUtil.ShowDialog(this, "Please enter Auth Login");
            return false;
        }

        if (sServerAddr.length() <= 0)
        {
            DialogUtil.ShowDialog(this, "Please enter SIP Server address");
            return false;
        }

        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();

        VaxPhoneSIP.m_objVaxVoIP.GetLoginInfo(Username, DisplayName, AuthLogin, null, DomainRealm, null, null, null);

        String sServerIP;
        String sServerPort;

        if (sServerAddr.contains(":"))
        {
            String aServerAddr[] = sServerAddr.split(":");

            sServerIP = aServerAddr[0];
            sServerPort = aServerAddr[aServerAddr.length - 1];
        }
        else
        {
            sServerIP = sServerAddr;
            sServerPort = "5060";
        }

        boolean bRegistrationSIP = SwitchRegistrationSIP.isChecked();

        if (!AuthLogin.toString().equals(sAuthLogin))
        {
            Username.setLength(0);
            Username.append(sAuthLogin);

            DisplayName.setLength(0);
            DisplayName.append(sAuthLogin);

            DomainRealm.setLength(0);
            DomainRealm.append(sServerIP);
        }

        String sAuthPwd = EditTextPassword.getText().toString();

        VaxPhoneSIP.m_objVaxVoIP.SetLoginInfo(Username.toString(), DisplayName.toString(), sAuthLogin, sAuthPwd, DomainRealm.toString(), sServerIP, sServerPort, bRegistrationSIP);
        return true;
    }

    void StartOpenMainTabWithDelay()
    {
        StopOpenMainTabWithDelay();

        mDelayHandler = new Handler();

        mDelayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent objIntent = new Intent(AccountLoginActivity.this, MainTabActivity.class);
                startActivity(objIntent);

                mDelayHandler = null;
                finish();
            }
        }, 1000);

    }

    void StopOpenMainTabWithDelay()
    {
        if (mDelayHandler != null)
        {
            mDelayHandler.removeCallbacksAndMessages(null);
            mDelayHandler = null;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    private void OnStatusText(String sMsg)
    {
        TextViewStatus.setText(sMsg);
    }

    public void OnSuccessToRegister()
    {
        StartOpenMainTabWithDelay();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public static void PostStatusText(String sMsg)
    {
        m_sLastStatusText = sMsg;

        if (mLoginActivity == null)
            return;

        mLoginActivity.OnStatusText(sMsg);
    }

    public static void PostSuccessToRegister()
    {
        if (mLoginActivity == null)
            return;

        mLoginActivity.OnSuccessToRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    private void AskContactPermission()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, ID_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int nRequestCode, @NonNull String[] sPermissions, @NonNull int[] aGrantResults)
    {
        if (nRequestCode == ID_REQUEST_READ_CONTACTS)
        {
            /*
            if (aGrantResults.length > 0 && aGrantResults[0] == PackageManager.PERMISSION_DENIED)
            {
                boolean bResult = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS);

                if (bResult)
                    DialogUtil.ShowDialog(this, "Please allow VaxPhone to read contacts so you can use full features of app.");

                if (!bResult)
                    DialogUtil.ShowDialog(this, "Please Allow Manually");
            }
            */
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        AskContactPermission();
    }

    @Override
    protected void onDestroy()
    {
        mLoginActivity = null;
        super.onDestroy();
    }
}
