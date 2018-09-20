package vaxsoft.com.vaxphone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.gystar.master.MainUI.Call.GyCallActivity;
import com.utils.gyymz.utils.L_;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import VaxVoIP.VaxUserAgentLib.IVaxUserAgentLib;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.AccountLogin.AccountLoginActivity;
import vaxsoft.com.vaxphone.IncomingCall.IncomingCallActivity;
import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.AudioCodecsDialog;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.DigitDTMFDialog;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.NetworkFragment;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.RingtonesDialog;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.SettingMicSpk;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.SettingsFragment;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.TunnelFragment;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.VideoBitrateDialog;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.VideoCodecsDialog;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.VideoQualityDialog;
import vaxsoft.com.vaxphone.MainDrawer.MainDrawerSettings.VoiceChangerDialog;
import vaxsoft.com.vaxphone.MainNotify.VaxPhoneNotify;
import vaxsoft.com.vaxphone.MainTab.CallTab.CallTabFragment;
import vaxsoft.com.vaxphone.MainTab.CallTab.DialpadFragment;
import vaxsoft.com.vaxphone.MainTab.ChatTab.ChatContactRecyclerView;
import vaxsoft.com.vaxphone.MainTab.ChatTab.ChatContactTabFragment;
import vaxsoft.com.vaxphone.MainTab.MainTabActivity;
import vaxsoft.com.vaxphone.MainTab.RecentTab.RecentRecyclerView;
import vaxsoft.com.vaxphone.PhoneSIP.BusyRing.BusyRing;
import vaxsoft.com.vaxphone.PhoneSIP.CallInfo.CallInfo;
import vaxsoft.com.vaxphone.PhoneSIP.DialRing.DialRing;
import vaxsoft.com.vaxphone.PhoneSIP.DigitTone.PlayDTMF;
import vaxsoft.com.vaxphone.PhoneSIP.ProximitySensor.ProximitySensor;
import vaxsoft.com.vaxphone.PhoneSIP.RingTone.RingTone;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreChatContact;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreChatMsg;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreLoginInfo;
import vaxsoft.com.vaxphone.VaxStorage.Store.StoreRecent;

public class VaxPhoneSIP extends VaxPhoneService implements IVaxUserAgentLib {
    public static final int VAX_RING_TONE_GROOVY = 0;
    public static final int VAX_RING_TONE_DIGITAL_RAIN = 1;
    public static final int VAX_RING_TONE_MAGICAL = 2;
    public static final int VAX_RING_TONE_DEJA_VU = 3;
    public static final int VAX_RING_TONE_OFFICE_PHONE = 4;

    public static final int VAX_VOICE_PITCH_GRANDPA_DRUNK = 4;
    public static final int VAX_VOICE_PITCH_TEEN_BOY = 8;
    public static final int VAX_VOICE_PITCH_HOUSE_HOLD_REBOT = 12;
    public static final int VAX_VOICE_PITCH_HELIUM_INHALED = 16;
    public static final int VAX_VOICE_PITCH_CHIPMUNK = 20;

    public static VaxPhoneSIP m_objVaxVoIP = null;
    VaxUserAgentLib mVaxAgentLib;

    private String m_sIncomingCallId = "";
    private String m_sLogFile = "";

    private boolean m_bVideoEnabled = true;
    private boolean m_bVideoDeviceCaptured = false;
    private boolean m_bFrontVideoCameraCaptured = true;
    private boolean m_bMuteMic = false;
    private boolean m_bMuteSpk = false;
    private boolean m_bVoiceChangerEnabled = false;
    private boolean m_bVideoCaptureErrorShown = false;

    private DialRing m_objDialRing;
    private BusyRing m_objBusyRing;
    private RingTone m_objRingTone;
    private PlayDTMF m_objPlayDTMF;
    private ProximitySensor m_objProximitySensor;

    private String m_sCallerName = "";
    private String m_sCallerId = "";

    private VaxPhoneNotify mVaxPhoneNotify;
    private boolean m_bVideoStreamStarted = false;

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public VaxPhoneSIP() {
        Log.e("Vax-Service", "VaxPhoneSIP()");

        mVaxAgentLib = new VaxUserAgentLib(VaxPhoneAPP.getAppContext(), this);

        m_objVaxVoIP = this;

        m_objDialRing = new DialRing();
        m_objBusyRing = new BusyRing();

        m_objRingTone = new RingTone();
        m_objPlayDTMF = new PlayDTMF();

        m_objProximitySensor = new ProximitySensor();
        mVaxPhoneNotify = new VaxPhoneNotify(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    protected void OnServiceStarted() {
        Initialize();
    }

    protected void OnServiceDestroy() {
        //UnInitialize();
    }

    protected void OnServiceLowMemory() {
        //UnInitialize();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public boolean SetLicenceKey(String sKey, Context objContextShowMsg) {
        if (sKey.equals("TRIAL-LICENSE-KEY") || sKey.isEmpty())
            ShowMsg(objContextShowMsg, "VaxVoIP SDK is free to use for 30 days.\n\nIf you want to continue using it after the trial period, you must pay the License fee.\n\nThank you for using VaxVoIP SDK!\n\nWeb: www.vaxvoip.com\nEmail: info@vaxvoip.com");

        if (!mVaxAgentLib.SetLicenceKey(sKey))
            return false;

        new StoreLoginInfo().SetLicenceKey(sKey);
        return true;
    }

    public void Initialize() {
        if (!VaxPhoneSIP.IsOnline())
            return;

        StringBuilder Username = new StringBuilder();
        StringBuilder DisplayName = new StringBuilder();
        StringBuilder AuthLogin = new StringBuilder();
        StringBuilder AuthPassword = new StringBuilder();
        StringBuilder DomainRealm = new StringBuilder();
        StringBuilder ServerIP = new StringBuilder();
        StringBuilder ServerPort = new StringBuilder();
        AtomicBoolean RegistrationSIP = new AtomicBoolean();

        VaxPhoneSIP.GetLoginInfo(Username, DisplayName, AuthLogin, AuthPassword, DomainRealm, ServerIP, ServerPort, RegistrationSIP);

        String sUsername = Username.toString();
        String sDisplayName = DisplayName.toString();
        String sAuthLogin = AuthLogin.toString();
        String sAuthPassword = AuthPassword.toString();
        String sDomainRealm = DomainRealm.toString();
        String sServerIP = ServerIP.toString();
        int nServerPort = Integer.parseInt(ServerPort.toString());
        boolean bRegistrationSIP = RegistrationSIP.get();

        String sKey = new StoreLoginInfo().GetLicenceKey();
        mVaxAgentLib.SetLicenceKey(sKey);

        Initialize(sDisplayName, sUsername, sAuthLogin, sAuthPassword, sDomainRealm, sServerIP, nServerPort, bRegistrationSIP);
    }

    public boolean Initialize(String sDisplayName, String sUserName, String sAuthLogin, String sAuthPwd, String sDomainRealm, String sServerAddr, int nServerPort, boolean bRegistrationSIP) {
        int nListenPortSIP = -1;

        if (!NetworkFragment.IsRandomPortSIP())
            nListenPortSIP = NetworkFragment.GetNetworkPortSIP();

        if (!mVaxAgentLib.Initialize("", nListenPortSIP, sDisplayName, sUserName, sAuthLogin, sAuthPwd, sDomainRealm, sServerAddr, nServerPort, "", -1, true))
            return false;

        if (bRegistrationSIP) {
            if (!mVaxAgentLib.RegisterToProxy(1800))
                return false;
        }

        mVaxAgentLib.AutoRegistration(true, -1, 20);
        mVaxAgentLib.EnableKeepAlive(10); // 10 seconds keep alive;

        new StoreLoginInfo().SetLoginStatus(true);

        return true;
    }

    public void UnInitialize() {
        m_bVideoCaptureErrorShown = false;

        new StoreLoginInfo().SetLoginStatus(false);
        mVaxAgentLib.UnInitialize();
    }

    public boolean IsVideoStreamStarted() {
        return m_bVideoStreamStarted;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public static void GetLoginInfo(StringBuilder sUsername, StringBuilder sDisplayName, StringBuilder sAuthLogin, StringBuilder sAuthPassword, StringBuilder sDoaminRealm,
                                    StringBuilder sServerIP, StringBuilder sServerPort, AtomicBoolean bRegistrationSIP) {
        StoreLoginInfo objStore = new StoreLoginInfo();
        objStore.GetLoginInfo(sUsername, sDisplayName, sAuthLogin, sAuthPassword, sDoaminRealm, sServerIP, sServerPort, bRegistrationSIP);
    }

    public static void SetLoginInfo(String sUsername, String sDisplayName, String sAuthLogin, String sAuthPassword, String sDoaminRealm, String sServerIP, String sServerPort, boolean bRegistrationSIP) {
        StoreLoginInfo objStore = new StoreLoginInfo();
        objStore.SetLoginInfo(sUsername, sDisplayName, sAuthLogin, sAuthPassword, sDoaminRealm, sServerIP, sServerPort, bRegistrationSIP);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public int GetChatContactCount() {
        StoreChatContact objStore = new StoreChatContact();
        return objStore.GetChatContactCount();
    }

    public void LoadChatContactAll() {
        StoreChatContact objStore = new StoreChatContact();
        ArrayList aContactDataList = objStore.GetChatContactAll();

        for (int nIndex = 0; nIndex < aContactDataList.size(); nIndex++) {
            ArrayList aSubList = (ArrayList) aContactDataList.get(nIndex);

            String sContactName = (String) aSubList.get(1);

            boolean bPresence = Boolean.parseBoolean(String.valueOf(aSubList.get(3)));
            long nMissedCount = Long.valueOf(String.valueOf(aSubList.get(2)));

            mVaxAgentLib.ChatAddContact(sContactName, bPresence);
            ChatContactRecyclerView.PostChatContactAdded(sContactName, nMissedCount, bPresence);
        }
    }

    public boolean ChatAddContact(String sUserName, boolean bPresence) {
        if (!mVaxAgentLib.ChatAddContact(sUserName, bPresence))
            return false;

        StoreChatContact objStore = new StoreChatContact();
        objStore.AddChatContact(sUserName, 0, bPresence);

        ChatContactTabFragment.PostChatContactAdded();
        ChatContactRecyclerView.PostChatContactAdded(sUserName, 0, bPresence);

        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    public boolean ChatSubscribeContactAll() {
        return mVaxAgentLib.ChatSubscribeContactAll();
    }

    public boolean ChatSetMyStatus(int nStatusId) {
        return mVaxAgentLib.ChatSetMyStatus(nStatusId);
    }

    public boolean ChatSendMessageText(String sUserName, String sMsgText) {
        if (!mVaxAgentLib.ChatSendMessageText(sUserName, sMsgText, 0, 0))
            return false;

        StoreChatMsg objStore = new StoreChatMsg();
        objStore.AddChatMsg(sUserName, sMsgText, true);

        ChatContactRecyclerView.PostChatMessageText(sUserName, mVaxAgentLib.ChatFindContact(sUserName), sMsgText, true);

        return true;
    }

    public boolean ChatRemoveContact(String sUserName) {
        if (!mVaxAgentLib.ChatRemoveContact(sUserName))
            return false;

        StoreChatContact objContactStore = new StoreChatContact();
        objContactStore.RemoveChatContact(sUserName);

        StoreChatMsg objMsgStore = new StoreChatMsg();
        objMsgStore.ClearContactChatMsgs(sUserName);

        ChatContactTabFragment.PostChatContactDeleted();

        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public int GetCallHistoryCount() {
        StoreRecent objStore = new StoreRecent();
        return objStore.GetCallCount();
    }

    public void RemoveCallRecord(int nId) {
        StoreRecent objStore = new StoreRecent();
        objStore.RemoveCallRecord(nId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public void ApplyVideoQuality() {
        if (!m_bVideoDeviceCaptured)
            return;

        CloseVideoDevice();
        OpenVideoDevice();
    }

    public boolean PlayDTMF(String sDigit) {
        m_objPlayDTMF.PlayTone(sDigit);
        return mVaxAgentLib.DigitDTMF(0, sDigit);
    }

    public void SetRingtone(int nRingtone) {
        m_objRingTone.SetRingTone(nRingtone);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public boolean OpenVideoDevice() {
        if (m_bVideoDeviceCaptured == true)
            return true;

        CloseVideoDevice();

        int nQuality = VideoQualityDialog.GetVideoQuality();

        int nDeviceId = 0;

        if (m_bFrontVideoCameraCaptured)
            nDeviceId = 1;

        if (!mVaxAgentLib.OpenVideoDev(nDeviceId, nQuality)) {
            return false;
        }

        m_bVideoDeviceCaptured = true;
        return true;
    }

    public void CloseVideoDevice() {
        m_bVideoDeviceCaptured = false;
        mVaxAgentLib.CloseVideoDev();
    }

    public void SwitchVideoDevice() {
        if (!m_bVideoDeviceCaptured)
            return;

        m_bFrontVideoCameraCaptured = !m_bFrontVideoCameraCaptured;

        CloseVideoDevice();
        OpenVideoDevice();
    }

    public void ApplyVideoBitrate(int nQuality) {
        mVaxAgentLib.VideoCodecBitRate(VaxUserAgentLib.VAX_CODEC_VP8, nQuality);
        mVaxAgentLib.VideoCodecBitRate(VaxUserAgentLib.VAX_CODEC_H263P, nQuality);
        mVaxAgentLib.VideoCodecBitRate(VaxUserAgentLib.VAX_CODEC_H263, nQuality);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public boolean IsMuteMic() {
        return m_bMuteMic;
    }

    public boolean MuteMic(boolean bMute) {
        if (!mVaxAgentLib.MuteMic(bMute))
            return false;

        m_bMuteMic = bMute;
        return true;
    }

    public boolean IsMuteSpk() {
        return m_bMuteSpk;
    }

    public boolean MuteSpk(boolean bMute) {
        if (!mVaxAgentLib.MuteSpk(bMute))
            return false;

        m_bMuteSpk = bMute;
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public boolean IsLineBusy() {
        return mVaxAgentLib.IsLineBusy(0);
    }

    public boolean IsLineConnected() {
        return mVaxAgentLib.IsLineConnected(0);
    }

    public boolean IsLineHold() {
        return mVaxAgentLib.IsLineHold(0);
    }

    public static boolean IsOnline() {
        return new StoreLoginInfo().GetLoginStatus();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public boolean HoldLine() {
        return mVaxAgentLib.HoldLine(0);
    }

    public boolean UnHoldLine() {
        return mVaxAgentLib.UnHoldLine(0);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public boolean IsVideoEnabled() {
        return m_bVideoEnabled;
    }

    public boolean EnableVideo(boolean bEnable) {
        if (!mVaxAgentLib.EnableVideo(0, bEnable, bEnable)) {
            return false;
        }

        m_bVideoEnabled = bEnable;
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private boolean OpenLine() {
        mVaxAgentLib.CloseLine(0);

        int nLocalPortAudioRTP = -1;
        int nLocalPortVideoRTP = -1;

        if (!NetworkFragment.IsRandomPortRTP()) {
            nLocalPortAudioRTP = NetworkFragment.GetNetworkPortRTP();
            nLocalPortVideoRTP = nLocalPortAudioRTP + 2;
        }

        return mVaxAgentLib.OpenLine(0, "", nLocalPortAudioRTP, nLocalPortVideoRTP);
    }

    public boolean DialCall(String sDialNo) {
        if (!OpenLine())
            return false;

        return mVaxAgentLib.DialCall(0, "", "", sDialNo, -1, -1);
    }

    public boolean DisconnectCall() {
        if (!IsLineBusy())
            return false;

        boolean bResult = mVaxAgentLib.DisconnectCall(0);

        OnVaxStatusMsg("Call", "Disconnected");
        OnDisconnectedCall();

        return bResult;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public void DiagnosticLog(boolean bEnable) {
        if (!bEnable) {
            m_sLogFile = "";
            return;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            boolean bCreated = true;

            String sPath = Environment.getExternalStorageDirectory() + "/Android/data/" + VaxPhoneAPP.getAppContext().getPackageName();

            File objPkgDirectory = new File(sPath);

            if (!objPkgDirectory.exists()) {
                bCreated = objPkgDirectory.mkdirs();
            }

            if (bCreated) {
                File objFile = new File(sPath, "VaxLog.txt");
                m_sLogFile = objFile.getPath();
            } else {
                m_sLogFile = "";
            }

            return;
        }

        File AppDir = VaxPhoneAPP.getAppContext().getExternalFilesDir(null);
        if (AppDir == null) return;

        File objFile = new File(AppDir, "VaxLog.txt");
        m_sLogFile = objFile.getPath();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public boolean AutoGainSpk(boolean bEnable) {
        return mVaxAgentLib.AutoGainSpk(bEnable, 20);
    }

    public boolean AutoGainMic(boolean bEnable) {
        return mVaxAgentLib.AutoGainMic(bEnable, 20);
    }

    public boolean SetVolumeBoostSpk(boolean bEnable) {
        int nBoostVol = 0;

        if (bEnable)
            nBoostVol = 20;

        return mVaxAgentLib.SetVolumeSpk(nBoostVol);
    }

    public boolean SetVolumeBoostMic(boolean bEnable) {
        int nBoostVol = 0;

        if (bEnable)
            nBoostVol = 20;

        return mVaxAgentLib.SetVolumeMic(nBoostVol);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public boolean IsVoiceChangerEnabled() {
        return m_bVoiceChangerEnabled;
    }

    public boolean VoiceChanger(boolean bEnable) {
        if (!bEnable) {
            m_bVoiceChangerEnabled = false;
            return mVaxAgentLib.VoiceChanger(-1);
        }

        if (!mVaxAgentLib.VoiceChanger(VoiceChangerDialog.GetSelectedPitchNo()))
            return false;

        m_bVoiceChangerEnabled = true;
        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public boolean ForceDigitDTMF(int nTypeId, boolean bEnable) {
        return mVaxAgentLib.ForceDigitDTMF(0, nTypeId, bEnable);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public void RejectCall() {
        mVaxAgentLib.RejectCall(m_sIncomingCallId);
    }

    public void AcceptCall() {
        if (!OpenLine())
            return;

        mVaxAgentLib.AcceptCall(0, m_sIncomingCallId, -1, -1);
    }

    public void MuteRingTone() {
        m_objRingTone.MuteRingTone();
    }

    public boolean NetworkReachability(boolean bEnable) {
        return mVaxAgentLib.NetworkReachability(bEnable);
    }

    public boolean DialRingEnable(String sFileName) {
        return mVaxAgentLib.DialRingEnable(sFileName);
    }

    public boolean BusyRingEnable(String sFileName) {
        return mVaxAgentLib.BusyRingEnable(sFileName);
    }

    public boolean EchoCancellation(boolean bEnable) {
        return mVaxAgentLib.EchoCancellation(bEnable);
    }

    public boolean SelectVoiceCodec(int nCodecNo) {
        return mVaxAgentLib.SelectVoiceCodec(nCodecNo);
    }

    public boolean SelectVideoCodec(int nCodecNo) {
        return mVaxAgentLib.SelectVideoCodec(nCodecNo);
    }

    public void DeselectAllVideoCodec() {
        mVaxAgentLib.DeselectAllVideoCodec();
    }

    public void DeselectAllVoiceCodec() {
        mVaxAgentLib.DeselectAllVoiceCodec();
    }

    public boolean DeselectVoiceCodec(int nCodecNo) {
        return mVaxAgentLib.DeselectVoiceCodec(nCodecNo);
    }

    public boolean DeselectVideoCodec(int nCodecNo) {
        return mVaxAgentLib.DeselectVideoCodec(nCodecNo);
    }

    public boolean CryptCOMM(boolean bEnable, String sRemoteIP, int nRemotePort) {
        return mVaxAgentLib.CryptCOMM(bEnable, sRemoteIP, nRemotePort);
    }

    public boolean SpeakerPhone(boolean bEnable) {
        return mVaxAgentLib.SpeakerPhone(bEnable);
    }

    public boolean IsSpeakerPhone() {
        return mVaxAgentLib.IsSpeakerPhone();
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public void OnInitialized() {
        OnVaxStatusMsg("Account", "Account is online");

        m_objDialRing.Enable();
        m_objBusyRing.Enable();

        RingtonesDialog.PostInitialized();
        ChatContactRecyclerView.PostInitialized();
        SettingsFragment.PostInitialized();

        VideoBitrateDialog.PostInitialized();
        TunnelFragment.PostInitialized();

        VideoCodecsDialog.PostInitialize();
        AudioCodecsDialog.PostInitialize();

        SettingMicSpk.PostInitialize();
        DigitDTMFDialog.PostInitialize();

        CallTabFragment.PostInitialized();
    }

    public void OnUnInitialized() {
        CloseVideoDevice();
        OnVaxStatusMsg("Account", "Account is offline");
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public void OnConnectingToRegister() {
        OnVaxStatusMsg("Register", "Trying to connect");
    }

    public void OnTryingToRegister() {
        OnVaxStatusMsg("Register", "Registering Account");
    }

    public void OnFailToRegister(int nStatusCode, String sReasonPhrase) {
        OnVaxStatusMsg("Register", sReasonPhrase);
    }

    public void OnSuccessToRegister() {
        OnVaxStatusMsg("Register", "Account Registered");
        AccountLoginActivity.PostSuccessToRegister();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    public void OnConnectingToReRegister() {

    }

    public void OnTryingToReRegister() {

    }

    public void OnFailToReRegister(int nStatusCode, String sReasonPhrase) {
        OnVaxStatusMsg("Re-Register", sReasonPhrase);
    }

    public void OnSuccessToReRegister() {

    }

    public void OnTryingToUnRegister() {
        OnVaxStatusMsg("Unregister", "Un-Registering Account");
    }

    public void OnFailToUnRegister() {
        OnVaxStatusMsg("Unregister", "Un-Register: Failed");
    }

    public void OnSuccessToUnRegister() {
        OnVaxStatusMsg("Unregister", "Account Un-Registered");
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void OnTryingToRegisterREC() {

    }

    public void OnFailToRegisterREC(int nStatusCode, String sReasonPhrase) {

    }

    public void OnSuccessToRegisterREC() {

    }

    public void OnTryingToReRegisterREC() {

    }

    public void OnFailToReRegisterREC(int nStatusCode, String sReasonPhrase) {

    }

    public void OnSuccessToReRegisterREC() {

    }

    public void OnTryingToUnRegisterREC() {

    }

    public void OnFailToUnRegisterREC() {

    }

    public void OnSuccessToUnRegisterREC() {

    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void OnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo) {
        OnVaxStatusMsg("Call", "Connecting");
        CallTabFragment.PostDialCallStarted();
    }

    public void OnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase) {
        OnVaxStatusMsg("Call", "Dialing");
    }

    public void OnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact) {
        OnVaxStatusMsg("Call", sReasonPhrase);
        if (GyCallActivity.instance != null) {
            GyCallActivity.instance.onStopTimeCount();
        }
        CallTabFragment.PostDialCallFailed(nStatusCode, sReasonPhrase);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void OnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort) {
        OnVaxStatusMsg("Call", "Connected");

        L_.e("TAG", "用户接听操作 OnConnectedCall");
        if (GyCallActivity.instance != null) {
            GyCallActivity.instance.onStartTimeCount();
        }

        CallTabFragment.PostConnectedCall();
        m_objProximitySensor.SetProximityMonitoringEnabled(true);
        mVaxPhoneNotify.StartCallNotification(MainTabActivity.class, m_sCallerName, m_sCallerId);
    }

    public void OnHungupCall(int nLineNo) {
        L_.e("TAG", "用户挂断操作 被叫方挂断 OnHungupCall");
        if (GyCallActivity.instance != null) {
            GyCallActivity.instance.onStopTimeCount();
        }
        OnVaxStatusMsg("Call", "Hungup");
        OnDisconnectedCall();
    }

    private void OnDisconnectedCall() {
        L_.e("TAG", "用户操作 主叫方挂断  OnDisconnectedCall");
        if (GyCallActivity.instance != null) {
            GyCallActivity.instance.onStopTimeCount();
        }
        CallTabFragment.PostDisconnectedCall();
        m_objProximitySensor.SetProximityMonitoringEnabled(false);
        mVaxPhoneNotify.StopCallNotification(true);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void OnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI) {
        m_sIncomingCallId = sCallId;

        StringBuilder sResultCallerName = new StringBuilder();
        StringBuilder sResultCallerId = new StringBuilder();
        StringBuilder sResultContactId = new StringBuilder();

        CallInfo.PrepareCallInfo(sCallerName, sCallerId, sResultCallerName, sResultCallerId, sResultContactId);

        sCallerName = sResultCallerName.toString();
        sCallerId = sResultCallerId.toString();

        Intent objIntent = new Intent(this, IncomingCallActivity.class);
        objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        objIntent.putExtra("CallerName", sCallerName);
        objIntent.putExtra("CallerId", sCallerId);

        startActivity(objIntent);

        m_sCallerName = sCallerName;
        m_sCallerId = sCallerId;
    }

    public void OnIncomingCallEnded(String sCallId) {
        if (IncomingCallActivity.mIncomingCallActivity == null)
            return;

        IncomingCallActivity.mIncomingCallActivity.OnIncomingCallEnded(sCallId);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnTransferCallAccepted(int nLineNo) {
        OnVaxStatusMsg("Transfer", "Accepted");
    }

    public void OnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase) {
        OnVaxStatusMsg("Transfer", sReasonPhrase);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnPlayWaveDone(int nLineNo) {

    }

    public void OnDigitDTMF(int nLineNo, String sDigit) {

    }

    public void OnMsgNOTIFY(String sMsg) {

    }

    public void OnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount) {

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnRingToneStarted(String sCallId) {
        m_objRingTone.StartRingTone();
    }

    public void OnRingToneEnded(String sCallId) {
        m_objRingTone.StopRingTone();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private void WriteToLogFile(String sText) {
        if (m_sLogFile.length() == 0)
            return;

        FileWriter objFileWriter;
        try {
            objFileWriter = new FileWriter(m_sLogFile, true);
            objFileWriter.write(sText);
            objFileWriter.flush();
            objFileWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort) {
        if (m_sLogFile.equals(""))
            return;

        String sLogPacket = "Received: " + sFromIP + " \n " + nFromPort + " \n " + sMsgSIP;

        this.WriteToLogFile(sLogPacket);
    }

    public void OnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort) {
        if (m_sLogFile.equals(""))
            return;

        String sLogPacket = "Sent: " + sToIP + " \n " + nToPort + " \n " + sMsgSIP;

        this.WriteToLogFile(sLogPacket);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    public void OnAudioSessionLost(int nLineNo) {

    }

    public void OnSuccessToHold(int nLineNo) {
        OnVaxStatusMsg("Hold", "Successful");
    }

    public void OnTryingToHold(int nLineNo) {
        OnVaxStatusMsg("Hold", "Trying");
    }

    public void OnFailToHold(int nLineNo) {
        OnVaxStatusMsg("Hold", "Failed");
    }

    public void OnSuccessToUnHold(int nLineNo) {
        OnVaxStatusMsg("Unhold", "Successful");
    }

    public void OnTryingToUnHold() {
        OnVaxStatusMsg("Unhold", "Trying");
    }


    public void OnFailToUnHold(int nLineNo) {
        OnVaxStatusMsg("Unhold", "Failed");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void OnChatContactStatus(String sUserName, int nStatusId) {
        ChatContactRecyclerView.PostChatContactStatus(sUserName, nStatusId);
    }

    public void OnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit) {

    }

    public void OnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit) {

    }

    public void OnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit) {

    }

    public void OnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit) {

    }

    public void OnChatRecvMsgText(String sUserName, String sMsgText, boolean bIsChatContact) {
        if (!bIsChatContact)
            ChatAddContact(sUserName, false);

        StoreChatMsg objStore = new StoreChatMsg();
        objStore.AddChatMsg(sUserName, sMsgText, false);

        if (!MainTabActivity.IsAvailableUI())
            mVaxPhoneNotify.StartMsgNotification(sUserName, sMsgText, MainTabActivity.class);

        ChatContactRecyclerView.PostChatMessageText(sUserName, bIsChatContact, sMsgText, false);
    }

    public void OnChatRecvMsgTypingStart(String sUserName) {

    }

    public void OnChatRecvMsgTypingStop(String sUserName) {

    }

    public void OnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM) {

    }


    public void OnDetectedAMD(int nLineNo, boolean bIsHuman) {

    }

    public void OnHoldCall(int nLineNo) {

    }

    public void OnUnHoldCall(int nLineNo) {

    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnVideoDeviceFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight) {
        CallTabFragment.PostVideoDeviceFrameRGB(nDeviceId, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }


    public void OnVideoRemoteFrameRGB(int nLineNo, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight) {
        CallTabFragment.PostOnVideoRemoteFrameRGB(nLineNo, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }


    public void OnVideoRemoteStarted(int nLineNo) {
        m_bVideoStreamStarted = true;
        CallTabFragment.OnVideoRemoteStarted(nLineNo);
    }

    public void OnVideoRemoteEnded(int nLineNo) {
        m_bVideoStreamStarted = false;
        CallTabFragment.OnVideoRemoteEnded(nLineNo);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void OnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase) {

    }

    public void OnServerConnectedREC(int nLineNo) {

    }

    public void OnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase) {

    }

    public void OnServerHungupREC(int nLineNo) {

    }

    public void OnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId) {
        StringBuilder sResultName = new StringBuilder();
        StringBuilder sResultPhoneNo = new StringBuilder();
        StringBuilder sResultContactId = new StringBuilder();

        if (bOutboundCallType)
            CallInfo.PrepareCallInfo(sCallerName, sDialNo, sResultName, sResultPhoneNo, sResultContactId);
        else {
            CallInfo.PrepareCallInfo(sCallerName, sCallerId, sResultName, sResultPhoneNo, sResultContactId);
        }

        StoreRecent objStore = new StoreRecent();
        int nRecordId = objStore.AddCallHistory(bOutboundCallType, sResultName.toString(), sResultPhoneNo.toString(), nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);

        RecentRecyclerView.PostAddCallHistory(nRecordId, bOutboundCallType, sResultName.toString(), sResultPhoneNo.toString(), nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);
    }

    public void OnNetworkReachability(boolean bAvailable) {
        String sMsg;

        if (bAvailable) {
            sMsg = "Network is available";
        } else {
            sMsg = "Network is not available";
        }

        OnVaxStatusMsg("Network", sMsg);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public void OnAudioDeviceMicVU(int nLevelVU) {

    }


    public void OnAudioDeviceSpkVU(int nLevelVU) {
    }

    @Override
    public void OnBusyLampSubscribeSuccess(String sUserName) {

    }

    @Override
    public void OnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase) {

    }

    @Override
    public void OnBusyLampContactStatus(String sUserName, int nStatusId) {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public void OnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode) {
        if (sFuncName.equals("OpenVideoDev")) {
            if (m_bVideoCaptureErrorShown)
                return;

            m_bVideoCaptureErrorShown = true;
        }

        AccountLoginActivity.PostStatusText(sErrorMsg);
        CallTabFragment.PostStatusText(sErrorMsg);
        DialpadFragment.PostStatusText(sErrorMsg);
    }

    private void OnVaxStatusMsg(String sFuncType, String sMsg) {
        if (sFuncType.equals("Account") || sFuncType.equals("Network")) {
            AccountLoginActivity.PostStatusText(sMsg);
            CallTabFragment.PostStatusText(sMsg);
            DialpadFragment.PostStatusText(sMsg);
            return;
        }

        if (sFuncType.equals("Register") || sFuncType.equals("Re-Register") || sFuncType.equals("Unregister")) {
            AccountLoginActivity.PostStatusText(sMsg);
        }

        String sShowMsg = sFuncType + ": " + sMsg;

        CallTabFragment.PostStatusText(sShowMsg);
        DialpadFragment.PostStatusText(sShowMsg);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void ShowMsg(Context context, String sMsg) {
        AlertDialog.Builder alertDialogBuilder;
        AlertDialog objAlertDialog;

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(sMsg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        objAlertDialog = alertDialogBuilder.create();
        Window window = objAlertDialog.getWindow();

        if (window != null)
            objAlertDialog.show();
        else
            Toast.makeText(context, sMsg, Toast.LENGTH_LONG).show();
    }
}
