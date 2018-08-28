package VaxVoIP.VaxUserAgentLib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.IVaxUserAgentSIP;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.VaxUserAgentSIP;

public class VaxUserAgentLib implements IVaxUserAgentSIP
{
    public static final int VAX_VIDEO_CODEC_TOTAL  = 3;

    public static final int VAX_CODEC_VP8   = 0;
    public static final int VAX_CODEC_H263  = 1;
    public static final int VAX_CODEC_H263P = 2;

    //////////////////////////////////////////////////////////////////////////////

    public static final int VAX_VIDEO_QUALITY_TOTAL    = 5;

    public static final int VAX_VIDEO_QUALITY_LOW      = 0;
    public static final int VAX_VIDEO_QUALITY_STANDARD = 1;
    public static final int VAX_VIDEO_QUALITY_MEDIUM   = 2;
    public static final int VAX_VIDEO_QUALITY_HIGH     = 3;
    public static final int VAX_VIDEO_QUALITY_MAX      = 4;

    ////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_VOICE_CODEC_TOTAL   = 5;

    public static final int VAX_CODEC_G711U  = 0;
    public static final int VAX_CODEC_G711A  = 1;
    public static final int VAX_CODEC_GSM610 = 2;
    public static final int VAX_CODEC_ILBC   = 3;
    public static final int VAX_CODEC_G729   = 4;

    /////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_RING_TONE_GROOVY        = 0;
    public static final int VAX_RING_TONE_DIGITAL_RAIN  = 1;
    public static final int VAX_RING_TONE_MAGICAL       = 2;
    public static final int VAX_RING_TONE_DEJA_VU       = 3;
    public static final int VAX_RING_TONE_OFFICE_PHONE  = 4;

    /////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_CALL_HISTORY_TYPE_OUTBOUND	= 0;
    public static final int VAX_CALL_HISTORY_TYPE_INBOUND	= 1;
    public static final int VAX_CALL_HISTORY_TYPE_MISSED	= 2;
    public static final int VAX_CALL_HISTORY_TYPE_REJECTED	= 3;

    /////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_CHAT_CONTACT_STATUS_ONLINE	 = 0;
    public static final int VAX_CHAT_CONTACT_STATUS_OFFLINE	 = 1;
    public static final int VAX_CHAT_CONTACT_STATUS_AWAY	 = 2;
    public static final int VAX_CHAT_CONTACT_STATUS_ON_PHONE = 3;
    public static final int VAX_CHAT_CONTACT_STATUS_BUSY	 = 4;
    public static final int VAX_CHAT_CONTACT_STATUS_UNKNOWN	 = 5;

    /////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_VOICE_PITCH_GRANDPA_DRUNK    =  4;
    public static final int VAX_VOICE_PITCH_TEEN_BOY         =  8;
    public static final int VAX_VOICE_PITCH_HOUSE_HOLD_REBOT = 12;
    public static final int VAX_VOICE_PITCH_HELIUM_INHALED   = 16;
    public static final int VAX_VOICE_PITCH_CHIPMUNK         = 20;

    /////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_DIGIT_DTMF_RFC2833  = 0;
    public static final int VAX_DIGIT_DTMF_SIP_INFO = 1;
    public static final int VAX_DIGIT_DTMF_INBAND   = 2;

    //////////////////////////////////////////////////////////////////////////////////

    public static final int VAX_CUSTOM_HEADER_REQ_INVITE = 0;
    public static final int VAX_CUSTOM_HEADER_REQ_REFER  = 1;

    private VaxUserAgentSIP m_objVaxUserAgentSIP = null;
    private Context mContext = null;

    public VaxUserAgentLib(Context objContext)
    {
        mContext = objContext;
        m_objVaxUserAgentSIP = new VaxUserAgentSIP(this, objContext);
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////    METHODS     /////////////////////////////
    ///////////////////////////////////////////////////////////////////

    private void ShowMsg(Context context, String sMsg)
    {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);

        if (!sMsg.equals(""))
            objBuilder.setMessage(sMsg);

        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        objBuilder.create();
        objBuilder.show();
    }

    public boolean SetLicenceKey(String sKey, Context objContextShowMsg)
    {
        if(sKey == "TRIAL-LICENSE-KEY" || sKey.isEmpty())
            ShowMsg(objContextShowMsg, "VaxVoIP SDK is free to use for 30 days.\n\nIf you want to continue using it after the trial period, you must pay the License fee.\n\nThank you for using VaxVoIP SDK!\n\nWeb: www.vaxvoip.com\nEmail: info@vaxvoip.com");

        return m_objVaxUserAgentSIP.SetLicenceKey(sKey, mContext);
    }

    public boolean Initialize(String sListenIP, int nListenPort, String sDisplayName, String sUserName, String sAuthLogin,
                              String sAuthPwd, String sDomainRealm, String sServerAddr, int nServerPort, String sProxyAddr, int nProxyPort, boolean bUseSoundDevice)
    {
        return m_objVaxUserAgentSIP.Initialize(sListenIP, nListenPort, sDisplayName, sUserName, sAuthLogin,
                sAuthPwd, sDomainRealm, sServerAddr, nServerPort, sProxyAddr, nProxyPort, bUseSoundDevice, mContext);
    }

    public void UnInitialize()
    {
        m_objVaxUserAgentSIP.UnInitialize();
    }


    public boolean RegisterToProxy(int nExpire)
    {
        return m_objVaxUserAgentSIP.RegisterToProxy(nExpire);
    }

    public boolean UnRegisterToProxy()
    {
        return m_objVaxUserAgentSIP.UnRegisterToProxy();
    }


    public boolean OpenLine(int nLineNo, String sRTPRxIP, int nRxAudioPortRTP, int nRxVideoPortRTP)
    {
        return m_objVaxUserAgentSIP.OpenLine(nLineNo, sRTPRxIP, nRxAudioPortRTP, nRxVideoPortRTP);
    }

    public boolean OpenLineREC(int nLineNo, String sRTPRxIP, int nAudioPortRTP)
    {
        return m_objVaxUserAgentSIP.OpenLineREC(nLineNo, sRTPRxIP, nAudioPortRTP);
    }

    public boolean CloseLine(int nLineNo)
    {
        return m_objVaxUserAgentSIP.CloseLine(nLineNo);
    }


    public int GetVaxErrorCode()
    {
        return m_objVaxUserAgentSIP.GetVaxErrorCode();
    }

    public String GetVaxErrorMsg()
    {
        return m_objVaxUserAgentSIP.GetVaxErrorMsg();
    }

    public String GetCallId(int nLineNo)
    {
        return m_objVaxUserAgentSIP.GetCallId(nLineNo);
    }


    public boolean AudioDeviceVU(boolean bActivate, boolean bMicVU, boolean bSpkVU)
    {
        return m_objVaxUserAgentSIP.AudioDeviceVU(bActivate, bMicVU, bSpkVU);
    }


    public int GetAudioInDevTotal()
    {
        return m_objVaxUserAgentSIP.GetAudioInDevTotal();
    }

    public int GetAudioOutDevTotal()
    {
        return m_objVaxUserAgentSIP.GetAudioOutDevTotal();
    }


    public String GetAudioInDevName(int nDeviceId)
    {
        return m_objVaxUserAgentSIP.GetAudioInDevName(nDeviceId);
    }

    public String GetAudioOutDevName(int nDeviceId)
    {
        return m_objVaxUserAgentSIP.GetAudioOutDevName(nDeviceId);
    }

    public boolean DialCall(int nLineNo, String sCallerName, String sCallerId, String sDialNo, int nInputDeviceId, int nOutputDeviceId)
    {
        return m_objVaxUserAgentSIP.DialCall(nLineNo, sCallerName, sCallerId, sDialNo, nInputDeviceId, nOutputDeviceId);
    }

    public boolean DisconnectCall(int nLineNo)
    {
        return m_objVaxUserAgentSIP.DisconnectCall(nLineNo);
    }


    public boolean AcceptCall(int nLineNo, String sCallId, int nInputDeviceId, int nOutputDeviceId)
    {
        return m_objVaxUserAgentSIP.AcceptCall(nLineNo, sCallId, nInputDeviceId, nOutputDeviceId);
    }

    public boolean RejectCall(String sCallId)
    {
        return m_objVaxUserAgentSIP.RejectCall(sCallId);
    }


    public boolean TransferCallBlind(int nLineNo, String sToUserName)
    {
        return m_objVaxUserAgentSIP.TransferCallBlind(nLineNo, sToUserName);
    }


    public boolean TransferCallConsult(int nLineNoA, int nLineNoB)
    {
        return m_objVaxUserAgentSIP.TransferCallConsult(nLineNoA, nLineNoB);
    }


    public boolean HoldLine(int nLineNo)
    {
        return m_objVaxUserAgentSIP.HoldLine(nLineNo);
    }

    public boolean UnHoldLine(int nLineNo)
    {
        return m_objVaxUserAgentSIP.UnHoldLine(nLineNo);
    }


    public boolean IsLineOpen(int nLineNo)
    {
        return m_objVaxUserAgentSIP.IsLineOpen(nLineNo);
    }

    public boolean IsLineHold(int nLineNo)
    {
        return m_objVaxUserAgentSIP.IsLineHold(nLineNo);
    }

    public boolean IsLineBusy(int nLineNo)
    {
        return m_objVaxUserAgentSIP.IsLineBusy(nLineNo);
    }

    public boolean IsLineConnected(int nLineNo)
    {
        return m_objVaxUserAgentSIP.IsLineConnected(nLineNo);
    }


    public boolean EnableKeepAlive(int nSeconds)
    {
        return m_objVaxUserAgentSIP.EnableKeepAlive(nSeconds);
    }

    public void DisableKeepAlive()
    {
        m_objVaxUserAgentSIP.DisableKeepAlive();
    }


    public void DeselectAllVoiceCodec()
    {
        m_objVaxUserAgentSIP.DeselectAllVoiceCodec();
    }

    public void SelectAllVoiceCodec()
    {
        m_objVaxUserAgentSIP.SelectAllVoiceCodec();
    }


    public boolean SelectVoiceCodec(int nCodecNo)
    {
        return m_objVaxUserAgentSIP.SelectVoiceCodec(nCodecNo);
    }

    public boolean DeselectVoiceCodec(int nCodecNo)
    {
        return m_objVaxUserAgentSIP.DeselectVoiceCodec(nCodecNo);
    }


    public void DeselectAllVideoCodec()
    {
        m_objVaxUserAgentSIP.DeselectAllVideoCodec();
    }

    public void SelectAllVideoCodec()
    {
        m_objVaxUserAgentSIP.SelectAllVideoCodec();
    }


    public boolean SelectVideoCodec(int nCodecNo)
    {
        return m_objVaxUserAgentSIP.SelectVideoCodec(nCodecNo);
    }

    public boolean DeselectVideoCodec(int nCodecNo)
    {
        return m_objVaxUserAgentSIP.DeselectVideoCodec(nCodecNo);
    }


    public boolean DigitDTMF(int nLineNo, String sDigit)
    {
        return m_objVaxUserAgentSIP.DigitDTMF(nLineNo, sDigit);
    }

    public boolean SetVolumeDTMF(int nVolume)
    {
        return m_objVaxUserAgentSIP.SetVolumeDTMF(nVolume);
    }

    public int GetVolumeDTMF()
    {
        return m_objVaxUserAgentSIP.GetVolumeDTMF();
    }


    public boolean ForceDigitDTMF(int nLineNo, int nTypeId, boolean bEnable)
    {
        return m_objVaxUserAgentSIP.ForceDigitDTMF(nLineNo, nTypeId, bEnable);
    }


    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public boolean MuteMic(boolean bMute)
    {
        return m_objVaxUserAgentSIP.MuteMic(bMute);
    }

    public boolean MuteSpk(boolean bMute)
    {
        return m_objVaxUserAgentSIP.MuteSpk(bMute);
    }


    public boolean MuteLineSpk(int nLineNo, boolean bMute)
    {
        return m_objVaxUserAgentSIP.MuteLineSpk(nLineNo, bMute);
    }

    public boolean MuteLineMic(int nLineNo, boolean bMute)
    {
        return m_objVaxUserAgentSIP.MuteLineMic(nLineNo, bMute);
    }


    public boolean AutoGainMic(boolean bEnable, int nVolume)
    {
        return m_objVaxUserAgentSIP.AutoGainMic(bEnable, nVolume);
    }

    public boolean AutoGainSpk(boolean bEnable, int nVolume)
    {
        return m_objVaxUserAgentSIP.AutoGainSpk(bEnable, nVolume);
    }


    public boolean SetVolumeMic(int nVolume)
    {
        return m_objVaxUserAgentSIP.SetVolumeMic(nVolume);
    }

    public int GetVolumeMic()
    {
        return m_objVaxUserAgentSIP.GetVolumeMic();
    }


    public boolean SetVolumeSpk(int nVolume)
    {
        return m_objVaxUserAgentSIP.SetVolumeSpk(nVolume);
    }

    public int GetVolumeSpk()
    {
        return m_objVaxUserAgentSIP.GetVolumeSpk();
    }


    public boolean SetLineVolumeSpk(int nLineNo, int nVolume)
    {
        return m_objVaxUserAgentSIP.SetLineVolumeSpk(nLineNo, nVolume);
    }

    public int GetLineVolumeSpk(int nLineNo)
    {
        return m_objVaxUserAgentSIP.GetLineVolumeSpk(nLineNo);
    }


    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public boolean IsRecording(int nLineNo)
    {
        return m_objVaxUserAgentSIP.IsRecording(nLineNo);
    }

    public boolean StartRecording(int nLineNo, String sFileName, int nRecordVoice)
    {
        return m_objVaxUserAgentSIP.StartRecording(nLineNo, sFileName, nRecordVoice);
    }

    public boolean StopRecording(int nLineNo)
    {
        return m_objVaxUserAgentSIP.StopRecording(nLineNo);
    }

    public boolean ResetRecording(int nLineNo)
    {
        return m_objVaxUserAgentSIP.ResetRecording(nLineNo);
    }


    public boolean IsWaveFilePlaying(int nLineNo)
    {
        return m_objVaxUserAgentSIP.IsWaveFilePlaying(nLineNo);
    }


    public boolean PlayWaveOpen(int nLineNo, String sFileName)
    {
        return m_objVaxUserAgentSIP.PlayWaveOpen(nLineNo, sFileName);
    }

    public boolean PlayWaveClose(int nLineNo)
    {
        return m_objVaxUserAgentSIP.PlayWaveClose(nLineNo);
    }


    public boolean PlayWaveSkipTo(int nLineNo, int nSeconds)
    {
        return m_objVaxUserAgentSIP.PlayWaveSkipTo(nLineNo, nSeconds);
    }

    public int PlayWaveTotalTime(int nLineNo)
    {
        return m_objVaxUserAgentSIP.PlayWaveTotalTime(nLineNo);
    }


    public boolean PlayWavePause(int nLineNo)
    {
        return m_objVaxUserAgentSIP.PlayWavePause(nLineNo);
    }

    public boolean PlayWaveStart(int nLineNo, boolean bListen)
    {
        return m_objVaxUserAgentSIP.PlayWaveStart(nLineNo, bListen);
    }


    public boolean PlayWaveStop(int nLineNo)
    {
        return m_objVaxUserAgentSIP.PlayWaveStop(nLineNo);
    }

    public int PlayWavePosition(int nLineNo)
    {
        return m_objVaxUserAgentSIP.PlayWavePosition(nLineNo);
    }


    public boolean EchoCancellation(boolean bEnable)
    {
        return m_objVaxUserAgentSIP.EchoCancellation(bEnable);
    }

    public boolean DonotDisturb(boolean bEnable)
    {
        return m_objVaxUserAgentSIP.DonotDisturb(bEnable);
    }


    public int GetOutboundCodec(int nLineNo)
    {
        return m_objVaxUserAgentSIP.GetOutboundCodec(nLineNo);
    }

    public int GetInboundCodec(int nLineNo)
    {
        return m_objVaxUserAgentSIP.GetInboundCodec(nLineNo);
    }


    public boolean SetSessionLostTick(int nMinute)
    {
        return m_objVaxUserAgentSIP.SetSessionLostTick(nMinute);
    }


    public boolean SetUserAgentSIP(String sUserAgentName)
    {
        return m_objVaxUserAgentSIP.SetUserAgentSIP(sUserAgentName);
    }

    public String GetUserAgentSIP()
    {
        return m_objVaxUserAgentSIP.GetUserAgentSIP();
    }


    public boolean SetSubjectSDP(String sSubjectSDP)
    {
        return m_objVaxUserAgentSIP.SetSubjectSDP(sSubjectSDP);
    }

    public String GetSubjectSDP()
    {
        return m_objVaxUserAgentSIP.GetSubjectSDP();
    }


    public boolean ConfAllowLine(int nLineNo, boolean bAllowListen, boolean bAllowSpeak)
    {
        return m_objVaxUserAgentSIP.ConfAllowLine(nLineNo, bAllowListen, bAllowSpeak);
    }

    public boolean LineVoiceChannelSpk(int nLineNo, int nChannel)
    {
        return m_objVaxUserAgentSIP.LineVoiceChannelSpk(nLineNo, nChannel);
    }


    public boolean ChatFindContact(String sUserName)
    {
        return m_objVaxUserAgentSIP.ChatFindContact(sUserName);
    }

    public boolean ChatAddContact(String sUserName, boolean bPresence)
    {
        return m_objVaxUserAgentSIP.ChatAddContact(sUserName, bPresence);
    }

    public boolean ChatRemoveContact(String sUserName)
    {
        return m_objVaxUserAgentSIP.ChatRemoveContact(sUserName);
    }


    public boolean ChatSendMessageTyping(String sUserName, int nUserValue32bit)
    {
        return m_objVaxUserAgentSIP.ChatSendMessageTyping(sUserName, nUserValue32bit);
    }

    public boolean ChatSendMessageText(String sUserName, String sMsgText, int nMsgType, int nUserValue32bit)
    {
        return m_objVaxUserAgentSIP.ChatSendMessageText(sUserName, sMsgText, nMsgType, nUserValue32bit);
    }


    public boolean ChatSubscribeContactAll()
    {
        return m_objVaxUserAgentSIP.ChatSubscribeContactAll();
    }

    public boolean ChatSetMyStatus(int nStatusId)
    {
        return m_objVaxUserAgentSIP.ChatSetMyStatus(nStatusId);
    }


    public boolean VoiceChanger(int nPitch)
    {
        return m_objVaxUserAgentSIP.VoiceChanger(nPitch);
    }

    public boolean ForwardCall(boolean bEnable, String sToUserName)
    {
        return m_objVaxUserAgentSIP.ForwardCall(bEnable, sToUserName);
    }


    public boolean PlayAddPCM(int nLineNo, byte[] aDataPCM, int nSizePCM)
    {
        return m_objVaxUserAgentSIP.PlayAddPCM(nLineNo, aDataPCM, nSizePCM);
    }

    public boolean PlayResetPCM(int nLineNo)
    {
        return m_objVaxUserAgentSIP.PlayResetPCM(nLineNo);
    }

    public boolean CaptureStreamPCM(int nLineNo, boolean bEnable)
    {
        return m_objVaxUserAgentSIP.CaptureStreamPCM(nLineNo, bEnable);
    }


    public boolean DetectAMD(int nLineNo, boolean bEnable, int nAnalysisTime, int nSilenceTime, int nSilenceCount)
    {
        return m_objVaxUserAgentSIP.DetectAMD(nLineNo, bEnable, nAnalysisTime, nSilenceTime, nSilenceCount);
    }


    public boolean AddCustomHeader(int nLineNo, int nReqId, String sName, String sValue)
    {
        return m_objVaxUserAgentSIP.AddCustomHeader(nLineNo, nReqId, sName, sValue);
    }

    public boolean RemoveCustomHeader(int nLineNo, int nReqId, String sName)
    {
        return m_objVaxUserAgentSIP.RemoveCustomHeader(nLineNo, nReqId, sName);
    }

    public boolean RemoveCustomHeaderAll(int nLineNo, int nReqId)
    {
        return m_objVaxUserAgentSIP.RemoveCustomHeaderAll(nLineNo, nReqId);
    }

    public boolean AddCustomHeaderReferToURI(String sName, String sValue)
    {
        return m_objVaxUserAgentSIP.AddCustomHeaderReferToURI(sName, sValue);
    }
    public boolean RemoveCustomHeaderReferToURI(String sName)
    {
        return m_objVaxUserAgentSIP.RemoveCustomHeaderReferToURI(sName);
    }
    public boolean RemoveCustomHeaderAllReferToURI()
    {
        return m_objVaxUserAgentSIP.RemoveCustomHeaderAllReferToURI();
    }

    public boolean ActivateQosSIP(int nPriorityQos)
    {
        return m_objVaxUserAgentSIP.ActivateQosSIP(nPriorityQos);
    }

    public void DeactivateQosSIP()
    {
        m_objVaxUserAgentSIP.DeactivateQosSIP();
    }


    public boolean ActivateQosRTP(int nLineNo, int nPriorityQos)
    {
        return m_objVaxUserAgentSIP.ActivateQosRTP(nLineNo, nPriorityQos);
    }

    public void DeactivateQosRTP(int nLineNo)
    {
        m_objVaxUserAgentSIP.DeactivateQosRTP(nLineNo);
    }


    public int GetCountPacketLost(int nLineNo)
    {
        return m_objVaxUserAgentSIP.GetCountPacketLost(nLineNo);
    }

    public int GetSizeJitterBuffer(int nLineNo)
    {
        return m_objVaxUserAgentSIP.GetSizeJitterBuffer(nLineNo);
    }

    public boolean ChangeMEDIA(int nLineNo, int nInputDeviceId, int nOutputDeviceId)
    {
        return m_objVaxUserAgentSIP.ChangeMEDIA(nLineNo, nInputDeviceId, nOutputDeviceId);
    }

    public int GetVideoDevTotal()
    {
        return m_objVaxUserAgentSIP.GetVideoDevTotal();
    }

    public String GetVideoDevName(int nDeviceId)
    {
        return m_objVaxUserAgentSIP.GetVideoDevName(nDeviceId);
    }


    public boolean OpenVideoDev(int nDeviceId, int nQuality)
    {
        return m_objVaxUserAgentSIP.OpenVideoDev(nDeviceId, nQuality);
    }

    public void CloseVideoDev()
    {
        m_objVaxUserAgentSIP.CloseVideoDev();
    }


    public boolean CryptCOMM(boolean bEnable, String sRemoteIP, int nRemotePort)
    {
        return m_objVaxUserAgentSIP.CryptCOMM(bEnable, sRemoteIP, nRemotePort);
    }


    public boolean DialCallToREC(int nLineNo, String sDialNo)
    {
        return m_objVaxUserAgentSIP.DialCallToREC(nLineNo, sDialNo);
    }

    public boolean RegisterToProxyREC(boolean bRegister, int nExpire, String sUserName, String sLoginId, String sLoginPwd, String sDisplayName, String sDomainRealm, String sProxySIP)
    {
        return m_objVaxUserAgentSIP.RegisterToProxyREC(bRegister, nExpire, sUserName, sLoginId, sLoginPwd, sDisplayName, sDomainRealm, sProxySIP);
    }

    public boolean UnRegisterToProxyREC()
    {
        return m_objVaxUserAgentSIP.UnRegisterToProxyREC();
    }


    public boolean BackgroundMode(boolean bEnable)
    {
        return m_objVaxUserAgentSIP.BackgroundMode(bEnable);
    }

    public boolean SpeakerPhone(boolean bEnable)
    {
        return m_objVaxUserAgentSIP.SpeakerPhone(bEnable);
    }

    public boolean IsSpeakerPhone()
    {
        return m_objVaxUserAgentSIP.IsSpeakerPhone();
    }

    public boolean DialRingEnable(String sFileName)
    {
        return m_objVaxUserAgentSIP.DialRingEnable(sFileName);
    }

    public boolean DialRingDisable()
    {
        return m_objVaxUserAgentSIP.DialRingDisable();
    }


    public boolean BusyRingEnable(String sFileName)
    {
        return m_objVaxUserAgentSIP.BusyRingEnable(sFileName);
    }

    public boolean BusyRingDisable()
    {
        return m_objVaxUserAgentSIP.BusyRingDisable();
    }

    public boolean EnableVideo(int nLineNo, boolean bOutbound, boolean bInbound)
    {
        return m_objVaxUserAgentSIP.EnableVideo(nLineNo, bOutbound, bInbound);
    }

    public boolean IsNetworkAvailable()
    {
        return m_objVaxUserAgentSIP.IsNetworkAvailable();
    }

    public boolean NetworkReachability(boolean bEnable)
    {
        return m_objVaxUserAgentSIP.NetworkReachability(bEnable);
    }

    public boolean AutoRegistration(boolean bEnable, int nTryCount, int nTryAfterSeconds)
    {
        return m_objVaxUserAgentSIP.AutoRegistration(bEnable, nTryCount, nTryAfterSeconds);
    }

    public boolean VideoCodecBitRate(int nCodecNo, int nQuality)
    {
        return m_objVaxUserAgentSIP.VideoCodecBitRate(nCodecNo, nQuality);
    }

    public int BusyLampGetContactStatus(String sUserName)
    {
        return m_objVaxUserAgentSIP.BusyLampGetContactStatus(sUserName);
    }

    public boolean BusyLampFindContact(String sUserName)
    {
        return m_objVaxUserAgentSIP.BusyLampFindContact(sUserName);
    }

    public boolean BusyLampAddContact(String sUserName)
    {
        return m_objVaxUserAgentSIP.BusyLampAddContact(sUserName);
    }

    public boolean BusyLampRemoveContact(String sUserName)
    {
        return m_objVaxUserAgentSIP.BusyLampRemoveContact(sUserName);
    }

    public boolean BusyLampSubscribeContactAll()
    {
        return m_objVaxUserAgentSIP.BusyLampSubscribeContactAll();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////    EVENTS      //////////////////////////////
    ///////////////////////////////////////////////////////////////////

    @Override
    public void OnInitialized()
    {

    }

    @Override
    public void OnUnInitialized()
    {

    }

    @Override
    public void OnConnectingToRegister()
    {

    }

    public void OnTryingToRegister()
    {

    }

    public void OnFailToRegister(int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnSuccessToRegister()
    {

    }

    @Override
    public void OnConnectingToReRegister()
    {

    }

    public void OnTryingToReRegister()
    {

    }

    public void OnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnSuccessToReRegister()
    {

    }

    public void OnTryingToUnRegister()
    {

    }

    public void OnFailToUnRegister()
    {

    }

    public void OnSuccessToUnRegister()
    {

    }

    public void OnTryingToRegisterREC()
    {

    }

    public void OnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnSuccessToRegisterREC()
    {

    }

    public void OnTryingToReRegisterREC()
    {

    }

    public void OnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnSuccessToReRegisterREC()
    {

    }

    public void OnTryingToUnRegisterREC()
    {

    }

    public void OnFailToUnRegisterREC()
    {

    }

    public void OnSuccessToUnRegisterREC()
    {

    }

    public void OnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {

    }

    public void OnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {

    }

    public void OnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {

    }

    public void OnHungupCall(int nLineNo)
    {

    }

    public void OnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {

    }

    public void OnIncomingCallEnded(String sCallId)
    {

    }

    public void OnTransferCallAccepted(int nLineNo)
    {

    }

    public void OnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnPlayWaveDone(int nLineNo)
    {

    }

    public void OnDigitDTMF(int nLineNo, String sDigit)
    {

    }

    public void OnMsgNOTIFY(String sMsg)
    {

    }

    public void OnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {

    }

    @Override
    public void OnRingToneStarted(String sCallId)
    {

    }

    @Override
    public void OnRingToneEnded(String sCallId)
    {

    }

    public void OnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {

    }

    public void OnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {

    }

    public void OnAudioSessionLost(int nLineNo)
    {

    }

    public void OnSuccessToHold(int nLineNo)
    {

    }

    public void OnTryingToHold(int nLineNo)
    {

    }

    public void OnFailToHold(int nLineNo)
    {

    }

    public void OnSuccessToUnHold(int nLineNo)
    {

    }

    public void OnTryingToUnHold()
    {

    }

    public void OnFailToUnHold(int nLineNo)
    {

    }

    public void OnChatContactStatus(String sUserName, int nStatusId)
    {

    }

    public void OnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {

    }

    public void OnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {

    }

    public void OnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {

    }

    public void OnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {

    }

    @Override
    public void OnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {

    }

    public void OnChatRecvMsgTypingStart(String sUserName)
    {

    }

    public void OnChatRecvMsgTypingStop(String sUserName)
    {

    }

    public void OnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {

    }

    public void OnDetectedAMD(int nLineNo, boolean bIsHuman)
    {

    }

    public void OnHoldCall(int nLineNo)
    {

    }

    public void OnUnHoldCall(int nLineNo)
    {

    }

    public void OnVideoDeviceFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {

    }

    public void OnVideoRemoteFrameRGB(int nLineNo, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {

    }

    public void OnVideoRemoteStarted(int nLineNo)
    {

    }

    public void OnVideoRemoteEnded(int nLineNo)
    {

    }

    public void OnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnServerConnectedREC(int nLineNo)
    {

    }

    public void OnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    public void OnServerHungupREC(int nLineNo)
    {

    }

    public void OnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {

    }

    @Override
    public void OnNetworkReachability(boolean bAvailable)
    {

    }

    @Override
    public void OnAudioDeviceMicVU(int nLevelVU)
    {

    }

    @Override
    public void OnAudioDeviceSpkVU(int nLevelVU)
    {

    }

    @Override
    public void OnBusyLampSubscribeSuccess(String sUserName)
    {

    }

    @Override
    public void OnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    public void OnBusyLampContactStatus(String sUserName, int nStatusId)
    {

    }

    @Override
    public void OnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode) {

    }
}
