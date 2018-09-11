package VaxVoIP.VaxUserAgentLib;

import android.content.Context;

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

    public static final int VAX_CHAT_MESSAGE_TYPE_PLAIN	     = 0;
    public static final int VAX_CHAT_MESSAGE_TYPE_HTML	     = 1;

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

    /////////////////////////////////////////////////////////////////////////////////////

    private Context mContext = null;
    private IVaxUserAgentLib mIVaxUserAgentLib = null;
    private VaxUserAgentSIP mVaxUserAgentSIP = null;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    public VaxUserAgentLib(Context objContext, IVaxUserAgentLib iVaxUserAgentLib)
    {
        mContext = objContext;
        mIVaxUserAgentLib = iVaxUserAgentLib;

        mVaxUserAgentSIP = new VaxUserAgentSIP(this, objContext);
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////    METHODS     /////////////////////////////
    ///////////////////////////////////////////////////////////////////

    /*
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
    */

    public boolean SetLicenceKey(String sKey)
    {
        //if(sKey == "TRIAL-LICENSE-KEY" || sKey.isEmpty())
          //  ShowMsg(objContextShowMsg, "VaxVoIP SDK is free to use for 30 days.\n\nIf you want to continue using it after the trial period, you must pay the License fee.\n\nThank you for using VaxVoIP SDK!\n\nWeb: www.vaxvoip.com\nEmail: info@vaxvoip.com");

        return mVaxUserAgentSIP.SetLicenceKey(sKey, mContext);
    }

    public boolean Initialize(String sListenIP, int nListenPort, String sDisplayName, String sUserName, String sAuthLogin,
                              String sAuthPwd, String sDomainRealm, String sServerAddr, int nServerPort, String sProxyAddr, int nProxyPort, boolean bUseSoundDevice)
    {
        return mVaxUserAgentSIP.Initialize(sListenIP, nListenPort, sDisplayName, sUserName, sAuthLogin,
                sAuthPwd, sDomainRealm, sServerAddr, nServerPort, sProxyAddr, nProxyPort, bUseSoundDevice, mContext);
    }

    public void UnInitialize()
    {
        mVaxUserAgentSIP.UnInitialize();
    }


    public boolean RegisterToProxy(int nExpire)
    {
        return mVaxUserAgentSIP.RegisterToProxy(nExpire);
    }

    public boolean UnRegisterToProxy()
    {
        return mVaxUserAgentSIP.UnRegisterToProxy();
    }


    public boolean OpenLine(int nLineNo, String sRTPRxIP, int nRxAudioPortRTP, int nRxVideoPortRTP)
    {
        return mVaxUserAgentSIP.OpenLine(nLineNo, sRTPRxIP, nRxAudioPortRTP, nRxVideoPortRTP);
    }

    public boolean OpenLineREC(int nLineNo, String sRTPRxIP, int nAudioPortRTP)
    {
        return mVaxUserAgentSIP.OpenLineREC(nLineNo, sRTPRxIP, nAudioPortRTP);
    }

    public boolean CloseLine(int nLineNo)
    {
        return mVaxUserAgentSIP.CloseLine(nLineNo);
    }


    public int GetVaxErrorCode()
    {
        return mVaxUserAgentSIP.GetVaxErrorCode();
    }

    public String GetVaxErrorMsg()
    {
        return mVaxUserAgentSIP.GetVaxErrorMsg();
    }

    public String GetCallId(int nLineNo)
    {
        return mVaxUserAgentSIP.GetCallId(nLineNo);
    }


    public boolean AudioDeviceVU(boolean bActivate, boolean bMicVU, boolean bSpkVU)
    {
        return mVaxUserAgentSIP.AudioDeviceVU(bActivate, bMicVU, bSpkVU);
    }


    public int GetAudioInDevTotal()
    {
        return mVaxUserAgentSIP.GetAudioInDevTotal();
    }

    public int GetAudioOutDevTotal()
    {
        return mVaxUserAgentSIP.GetAudioOutDevTotal();
    }


    public String GetAudioInDevName(int nDeviceId)
    {
        return mVaxUserAgentSIP.GetAudioInDevName(nDeviceId);
    }

    public String GetAudioOutDevName(int nDeviceId)
    {
        return mVaxUserAgentSIP.GetAudioOutDevName(nDeviceId);
    }

    public boolean DialCall(int nLineNo, String sCallerName, String sCallerId, String sDialNo, int nInputDeviceId, int nOutputDeviceId)
    {
        return mVaxUserAgentSIP.DialCall(nLineNo, sCallerName, sCallerId, sDialNo, nInputDeviceId, nOutputDeviceId);
    }

    public boolean DisconnectCall(int nLineNo)
    {
        return mVaxUserAgentSIP.DisconnectCall(nLineNo);
    }


    public boolean AcceptCall(int nLineNo, String sCallId, int nInputDeviceId, int nOutputDeviceId)
    {
        return mVaxUserAgentSIP.AcceptCall(nLineNo, sCallId, nInputDeviceId, nOutputDeviceId);
    }

    public boolean RejectCall(String sCallId)
    {
        return mVaxUserAgentSIP.RejectCall(sCallId);
    }


    public boolean TransferCallBlind(int nLineNo, String sToUserName)
    {
        return mVaxUserAgentSIP.TransferCallBlind(nLineNo, sToUserName);
    }


    public boolean TransferCallConsult(int nLineNoA, int nLineNoB)
    {
        return mVaxUserAgentSIP.TransferCallConsult(nLineNoA, nLineNoB);
    }


    public boolean HoldLine(int nLineNo)
    {
        return mVaxUserAgentSIP.HoldLine(nLineNo);
    }

    public boolean UnHoldLine(int nLineNo)
    {
        return mVaxUserAgentSIP.UnHoldLine(nLineNo);
    }


    public boolean IsLineOpen(int nLineNo)
    {
        return mVaxUserAgentSIP.IsLineOpen(nLineNo);
    }

    public boolean IsLineHold(int nLineNo)
    {
        return mVaxUserAgentSIP.IsLineHold(nLineNo);
    }

    public boolean IsLineBusy(int nLineNo)
    {
        return mVaxUserAgentSIP.IsLineBusy(nLineNo);
    }

    public boolean IsLineConnected(int nLineNo)
    {
        return mVaxUserAgentSIP.IsLineConnected(nLineNo);
    }


    public boolean EnableKeepAlive(int nSeconds)
    {
        return mVaxUserAgentSIP.EnableKeepAlive(nSeconds);
    }

    public void DisableKeepAlive()
    {
        mVaxUserAgentSIP.DisableKeepAlive();
    }


    public void DeselectAllVoiceCodec()
    {
        mVaxUserAgentSIP.DeselectAllVoiceCodec();
    }

    public void SelectAllVoiceCodec()
    {
        mVaxUserAgentSIP.SelectAllVoiceCodec();
    }


    public boolean SelectVoiceCodec(int nCodecNo)
    {
        return mVaxUserAgentSIP.SelectVoiceCodec(nCodecNo);
    }

    public boolean DeselectVoiceCodec(int nCodecNo)
    {
        return mVaxUserAgentSIP.DeselectVoiceCodec(nCodecNo);
    }


    public void DeselectAllVideoCodec()
    {
        mVaxUserAgentSIP.DeselectAllVideoCodec();
    }

    public void SelectAllVideoCodec()
    {
        mVaxUserAgentSIP.SelectAllVideoCodec();
    }


    public boolean SelectVideoCodec(int nCodecNo)
    {
        return mVaxUserAgentSIP.SelectVideoCodec(nCodecNo);
    }

    public boolean DeselectVideoCodec(int nCodecNo)
    {
        return mVaxUserAgentSIP.DeselectVideoCodec(nCodecNo);
    }


    public boolean DigitDTMF(int nLineNo, String sDigit)
    {
        return mVaxUserAgentSIP.DigitDTMF(nLineNo, sDigit);
    }

    public boolean SetVolumeDTMF(int nVolume)
    {
        return mVaxUserAgentSIP.SetVolumeDTMF(nVolume);
    }

    public int GetVolumeDTMF()
    {
        return mVaxUserAgentSIP.GetVolumeDTMF();
    }


    public boolean ForceDigitDTMF(int nLineNo, int nTypeId, boolean bEnable)
    {
        return mVaxUserAgentSIP.ForceDigitDTMF(nLineNo, nTypeId, bEnable);
    }


    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public boolean MuteMic(boolean bMute)
    {
        return mVaxUserAgentSIP.MuteMic(bMute);
    }

    public boolean MuteSpk(boolean bMute)
    {
        return mVaxUserAgentSIP.MuteSpk(bMute);
    }


    public boolean MuteLineSpk(int nLineNo, boolean bMute)
    {
        return mVaxUserAgentSIP.MuteLineSpk(nLineNo, bMute);
    }

    public boolean MuteLineMic(int nLineNo, boolean bMute)
    {
        return mVaxUserAgentSIP.MuteLineMic(nLineNo, bMute);
    }


    public boolean AutoGainMic(boolean bEnable, int nVolume)
    {
        return mVaxUserAgentSIP.AutoGainMic(bEnable, nVolume);
    }

    public boolean AutoGainSpk(boolean bEnable, int nVolume)
    {
        return mVaxUserAgentSIP.AutoGainSpk(bEnable, nVolume);
    }


    public boolean SetVolumeMic(int nVolume)
    {
        return mVaxUserAgentSIP.SetVolumeMic(nVolume);
    }

    public int GetVolumeMic()
    {
        return mVaxUserAgentSIP.GetVolumeMic();
    }


    public boolean SetVolumeSpk(int nVolume)
    {
        return mVaxUserAgentSIP.SetVolumeSpk(nVolume);
    }

    public int GetVolumeSpk()
    {
        return mVaxUserAgentSIP.GetVolumeSpk();
    }


    public boolean SetLineVolumeSpk(int nLineNo, int nVolume)
    {
        return mVaxUserAgentSIP.SetLineVolumeSpk(nLineNo, nVolume);
    }

    public int GetLineVolumeSpk(int nLineNo)
    {
        return mVaxUserAgentSIP.GetLineVolumeSpk(nLineNo);
    }


    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public boolean IsRecording(int nLineNo)
    {
        return mVaxUserAgentSIP.IsRecording(nLineNo);
    }

    public boolean StartRecording(int nLineNo, String sFileName, int nRecordVoice)
    {
        return mVaxUserAgentSIP.StartRecording(nLineNo, sFileName, nRecordVoice);
    }

    public boolean StopRecording(int nLineNo)
    {
        return mVaxUserAgentSIP.StopRecording(nLineNo);
    }

    public boolean ResetRecording(int nLineNo)
    {
        return mVaxUserAgentSIP.ResetRecording(nLineNo);
    }


    public boolean IsWaveFilePlaying(int nLineNo)
    {
        return mVaxUserAgentSIP.IsWaveFilePlaying(nLineNo);
    }


    public boolean PlayWaveOpen(int nLineNo, String sFileName)
    {
        return mVaxUserAgentSIP.PlayWaveOpen(nLineNo, sFileName);
    }

    public boolean PlayWaveClose(int nLineNo)
    {
        return mVaxUserAgentSIP.PlayWaveClose(nLineNo);
    }


    public boolean PlayWaveSkipTo(int nLineNo, int nSeconds)
    {
        return mVaxUserAgentSIP.PlayWaveSkipTo(nLineNo, nSeconds);
    }

    public int PlayWaveTotalTime(int nLineNo)
    {
        return mVaxUserAgentSIP.PlayWaveTotalTime(nLineNo);
    }


    public boolean PlayWavePause(int nLineNo)
    {
        return mVaxUserAgentSIP.PlayWavePause(nLineNo);
    }

    public boolean PlayWaveStart(int nLineNo, boolean bListen)
    {
        return mVaxUserAgentSIP.PlayWaveStart(nLineNo, bListen);
    }


    public boolean PlayWaveStop(int nLineNo)
    {
        return mVaxUserAgentSIP.PlayWaveStop(nLineNo);
    }

    public int PlayWavePosition(int nLineNo)
    {
        return mVaxUserAgentSIP.PlayWavePosition(nLineNo);
    }


    public boolean EchoCancellation(boolean bEnable)
    {
        return mVaxUserAgentSIP.EchoCancellation(bEnable);
    }

    public boolean DonotDisturb(boolean bEnable)
    {
        return mVaxUserAgentSIP.DonotDisturb(bEnable);
    }


    public int GetOutboundCodec(int nLineNo)
    {
        return mVaxUserAgentSIP.GetOutboundCodec(nLineNo);
    }

    public int GetInboundCodec(int nLineNo)
    {
        return mVaxUserAgentSIP.GetInboundCodec(nLineNo);
    }


    public boolean SetSessionLostTick(int nMinute)
    {
        return mVaxUserAgentSIP.SetSessionLostTick(nMinute);
    }


    public boolean SetUserAgentSIP(String sUserAgentName)
    {
        return mVaxUserAgentSIP.SetUserAgentSIP(sUserAgentName);
    }

    public String GetUserAgentSIP()
    {
        return mVaxUserAgentSIP.GetUserAgentSIP();
    }


    public boolean SetSubjectSDP(String sSubjectSDP)
    {
        return mVaxUserAgentSIP.SetSubjectSDP(sSubjectSDP);
    }

    public String GetSubjectSDP()
    {
        return mVaxUserAgentSIP.GetSubjectSDP();
    }


    public boolean ConfAllowLine(int nLineNo, boolean bAllowListen, boolean bAllowSpeak)
    {
        return mVaxUserAgentSIP.ConfAllowLine(nLineNo, bAllowListen, bAllowSpeak);
    }

    public boolean LineVoiceChannelSpk(int nLineNo, int nChannel)
    {
        return mVaxUserAgentSIP.LineVoiceChannelSpk(nLineNo, nChannel);
    }


    public boolean ChatFindContact(String sUserName)
    {
        return mVaxUserAgentSIP.ChatFindContact(sUserName);
    }

    public boolean ChatAddContact(String sUserName, boolean bPresence)
    {
        return mVaxUserAgentSIP.ChatAddContact(sUserName, bPresence);
    }

    public boolean ChatRemoveContact(String sUserName)
    {
        return mVaxUserAgentSIP.ChatRemoveContact(sUserName);
    }


    public boolean ChatSendMessageTyping(String sUserName, int nUserValue32bit)
    {
        return mVaxUserAgentSIP.ChatSendMessageTyping(sUserName, nUserValue32bit);
    }

    public boolean ChatSendMessageText(String sUserName, String sMsgText, int nMsgType, int nUserValue32bit)
    {
        return mVaxUserAgentSIP.ChatSendMessageText(sUserName, sMsgText, nMsgType, nUserValue32bit);
    }


    public boolean ChatSubscribeContactAll()
    {
        return mVaxUserAgentSIP.ChatSubscribeContactAll();
    }

    public boolean ChatSetMyStatus(int nStatusId)
    {
        return mVaxUserAgentSIP.ChatSetMyStatus(nStatusId);
    }


    public boolean VoiceChanger(int nPitch)
    {
        return mVaxUserAgentSIP.VoiceChanger(nPitch);
    }

    public boolean ForwardCall(boolean bEnable, String sToUserName)
    {
        return mVaxUserAgentSIP.ForwardCall(bEnable, sToUserName);
    }


    public boolean PlayAddPCM(int nLineNo, byte[] aDataPCM, int nSizePCM)
    {
        return mVaxUserAgentSIP.PlayAddPCM(nLineNo, aDataPCM, nSizePCM);
    }

    public boolean PlayResetPCM(int nLineNo)
    {
        return mVaxUserAgentSIP.PlayResetPCM(nLineNo);
    }

    public boolean CaptureStreamPCM(int nLineNo, boolean bEnable)
    {
        return mVaxUserAgentSIP.CaptureStreamPCM(nLineNo, bEnable);
    }


    public boolean DetectAMD(int nLineNo, boolean bEnable, int nAnalysisTime, int nSilenceTime, int nSilenceCount)
    {
        return mVaxUserAgentSIP.DetectAMD(nLineNo, bEnable, nAnalysisTime, nSilenceTime, nSilenceCount);
    }


    public boolean AddCustomHeader(int nLineNo, int nReqId, String sName, String sValue)
    {
        return mVaxUserAgentSIP.AddCustomHeader(nLineNo, nReqId, sName, sValue);
    }

    public boolean RemoveCustomHeader(int nLineNo, int nReqId, String sName)
    {
        return mVaxUserAgentSIP.RemoveCustomHeader(nLineNo, nReqId, sName);
    }

    public boolean RemoveCustomHeaderAll(int nLineNo, int nReqId)
    {
        return mVaxUserAgentSIP.RemoveCustomHeaderAll(nLineNo, nReqId);
    }

    public boolean AddCustomHeaderReferToURI(String sName, String sValue)
    {
        return mVaxUserAgentSIP.AddCustomHeaderReferToURI(sName, sValue);
    }
    public boolean RemoveCustomHeaderReferToURI(String sName)
    {
        return mVaxUserAgentSIP.RemoveCustomHeaderReferToURI(sName);
    }
    public boolean RemoveCustomHeaderAllReferToURI()
    {
        return mVaxUserAgentSIP.RemoveCustomHeaderAllReferToURI();
    }

    public boolean ActivateQosSIP(int nPriorityQos)
    {
        return mVaxUserAgentSIP.ActivateQosSIP(nPriorityQos);
    }

    public void DeactivateQosSIP()
    {
        mVaxUserAgentSIP.DeactivateQosSIP();
    }


    public boolean ActivateQosRTP(int nLineNo, int nPriorityQos)
    {
        return mVaxUserAgentSIP.ActivateQosRTP(nLineNo, nPriorityQos);
    }

    public void DeactivateQosRTP(int nLineNo)
    {
        mVaxUserAgentSIP.DeactivateQosRTP(nLineNo);
    }


    public int GetCountPacketLost(int nLineNo)
    {
        return mVaxUserAgentSIP.GetCountPacketLost(nLineNo);
    }

    public int GetSizeJitterBuffer(int nLineNo)
    {
        return mVaxUserAgentSIP.GetSizeJitterBuffer(nLineNo);
    }

    public boolean ChangeMEDIA(int nLineNo, int nInputDeviceId, int nOutputDeviceId)
    {
        return mVaxUserAgentSIP.ChangeMEDIA(nLineNo, nInputDeviceId, nOutputDeviceId);
    }

    public int GetVideoDevTotal()
    {
        return mVaxUserAgentSIP.GetVideoDevTotal();
    }

    public String GetVideoDevName(int nDeviceId)
    {
        return mVaxUserAgentSIP.GetVideoDevName(nDeviceId);
    }


    public boolean OpenVideoDev(int nDeviceId, int nQuality)
    {
        return mVaxUserAgentSIP.OpenVideoDev(nDeviceId, nQuality);
    }

    public void CloseVideoDev()
    {
        mVaxUserAgentSIP.CloseVideoDev();
    }


    public boolean CryptCOMM(boolean bEnable, String sRemoteIP, int nRemotePort)
    {
        return mVaxUserAgentSIP.CryptCOMM(bEnable, sRemoteIP, nRemotePort);
    }


    public boolean DialCallToREC(int nLineNo, String sDialNo)
    {
        return mVaxUserAgentSIP.DialCallToREC(nLineNo, sDialNo);
    }

    public boolean RegisterToProxyREC(boolean bRegister, int nExpire, String sUserName, String sLoginId, String sLoginPwd, String sDisplayName, String sDomainRealm, String sProxySIP)
    {
        return mVaxUserAgentSIP.RegisterToProxyREC(bRegister, nExpire, sUserName, sLoginId, sLoginPwd, sDisplayName, sDomainRealm, sProxySIP);
    }

    public boolean UnRegisterToProxyREC()
    {
        return mVaxUserAgentSIP.UnRegisterToProxyREC();
    }


    public boolean BackgroundMode(boolean bEnable)
    {
        return mVaxUserAgentSIP.BackgroundMode(bEnable);
    }

    public boolean SpeakerPhone(boolean bEnable)
    {
        return mVaxUserAgentSIP.SpeakerPhone(bEnable);
    }

    public boolean IsSpeakerPhone()
    {
        return mVaxUserAgentSIP.IsSpeakerPhone();
    }

    public boolean DialRingEnable(String sFileName)
    {
        return mVaxUserAgentSIP.DialRingEnable(sFileName);
    }

    public boolean DialRingDisable()
    {
        return mVaxUserAgentSIP.DialRingDisable();
    }


    public boolean BusyRingEnable(String sFileName)
    {
        return mVaxUserAgentSIP.BusyRingEnable(sFileName);
    }

    public boolean BusyRingDisable()
    {
        return mVaxUserAgentSIP.BusyRingDisable();
    }

    public boolean EnableVideo(int nLineNo, boolean bOutbound, boolean bInbound)
    {
        return mVaxUserAgentSIP.EnableVideo(nLineNo, bOutbound, bInbound);
    }

    public boolean IsNetworkAvailable()
    {
        return mVaxUserAgentSIP.IsNetworkAvailable();
    }

    public boolean NetworkReachability(boolean bEnable)
    {
        return mVaxUserAgentSIP.NetworkReachability(bEnable);
    }

    public boolean AutoRegistration(boolean bEnable, int nTryCount, int nTryAfterSeconds)
    {
        return mVaxUserAgentSIP.AutoRegistration(bEnable, nTryCount, nTryAfterSeconds);
    }

    public boolean VideoCodecBitRate(int nCodecNo, int nQuality)
    {
        return mVaxUserAgentSIP.VideoCodecBitRate(nCodecNo, nQuality);
    }

    public int BusyLampGetContactStatus(String sUserName)
    {
        return mVaxUserAgentSIP.BusyLampGetContactStatus(sUserName);
    }

    public boolean BusyLampFindContact(String sUserName)
    {
        return mVaxUserAgentSIP.BusyLampFindContact(sUserName);
    }

    public boolean BusyLampAddContact(String sUserName)
    {
        return mVaxUserAgentSIP.BusyLampAddContact(sUserName);
    }

    public boolean BusyLampRemoveContact(String sUserName)
    {
        return mVaxUserAgentSIP.BusyLampRemoveContact(sUserName);
    }

    public boolean BusyLampSubscribeContactAll()
    {
        return mVaxUserAgentSIP.BusyLampSubscribeContactAll();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////    EVENTS      //////////////////////////////
    ///////////////////////////////////////////////////////////////////

    @Override
    public void OnInitialized()
    {
        mIVaxUserAgentLib.OnInitialized();
    }

    @Override
    public void OnUnInitialized()
    {
        mIVaxUserAgentLib.OnUnInitialized();
    }

    @Override
    public void OnConnectingToRegister()
    {
        mIVaxUserAgentLib.OnConnectingToRegister();
    }

    public void OnTryingToRegister()
    {
        mIVaxUserAgentLib.OnTryingToRegister();
    }

    public void OnFailToRegister(int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnFailToRegister(nStatusCode, sReasonPhrase);
    }

    public void OnSuccessToRegister()
    {
        mIVaxUserAgentLib.OnSuccessToRegister();
    }

    @Override
    public void OnConnectingToReRegister()
    {
        mIVaxUserAgentLib.OnConnectingToReRegister();
    }

    public void OnTryingToReRegister()
    {
        mIVaxUserAgentLib.OnTryingToReRegister();
    }

    public void OnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnFailToReRegister(nStatusCode, sReasonPhrase);
    }

    public void OnSuccessToReRegister()
    {
        mIVaxUserAgentLib.OnSuccessToReRegister();
    }

    public void OnTryingToUnRegister()
    {
        mIVaxUserAgentLib.OnTryingToUnRegister();
    }

    public void OnFailToUnRegister()
    {
        mIVaxUserAgentLib.OnFailToUnRegister();
    }

    public void OnSuccessToUnRegister()
    {
        mIVaxUserAgentLib.OnSuccessToUnRegister();
    }

    public void OnTryingToRegisterREC()
    {
        mIVaxUserAgentLib.OnTryingToRegisterREC();
    }

    public void OnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnFailToRegisterREC(nStatusCode, sReasonPhrase);
    }

    public void OnSuccessToRegisterREC()
    {
        mIVaxUserAgentLib.OnSuccessToRegisterREC();
    }

    public void OnTryingToReRegisterREC()
    {
        mIVaxUserAgentLib.OnTryingToReRegisterREC();
    }

    public void OnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnFailToReRegisterREC(nStatusCode, sReasonPhrase);
    }

    public void OnSuccessToReRegisterREC()
    {
        mIVaxUserAgentLib.OnSuccessToReRegisterREC();
    }

    public void OnTryingToUnRegisterREC()
    {
        mIVaxUserAgentLib.OnTryingToUnRegisterREC();
    }

    public void OnFailToUnRegisterREC()
    {
        mIVaxUserAgentLib.OnFailToUnRegisterREC();
    }

    public void OnSuccessToUnRegisterREC()
    {
        mIVaxUserAgentLib.OnSuccessToUnRegisterREC();
    }

    public void OnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {
        mIVaxUserAgentLib.OnDialCallStarted(nLineNo, sCallerName, sCallerId, sDialNo);
    }

    public void OnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnDialingCall(nLineNo, nStatusCode, sReasonPhrase);
    }

    public void OnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {
        mIVaxUserAgentLib.OnDialCallFailed(nLineNo, nStatusCode, sReasonPhrase, sContact);
    }

    public void OnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {
        mIVaxUserAgentLib.OnConnectedCall(nLineNo, sToRTPIP, nToRTPPort);
    }

    public void OnHungupCall(int nLineNo)
    {
        mIVaxUserAgentLib.OnHungupCall(nLineNo);
    }

    public void OnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {
        mIVaxUserAgentLib.OnIncomingCallStarted(sCallId, sCallerName, sCallerId, sDialNo, sFromURI, sToURI);
    }

    public void OnIncomingCallEnded(String sCallId)
    {
        mIVaxUserAgentLib.OnIncomingCallEnded(sCallId);
    }

    public void OnTransferCallAccepted(int nLineNo)
    {
        mIVaxUserAgentLib.OnTransferCallAccepted(nLineNo);
    }

    public void OnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnTransferCallFailed(nLineNo, nStatusCode, sReasonPhrase);
    }

    public void OnPlayWaveDone(int nLineNo)
    {
        mIVaxUserAgentLib.OnPlayWaveDone(nLineNo);
    }

    public void OnDigitDTMF(int nLineNo, String sDigit)
    {
        mIVaxUserAgentLib.OnDigitDTMF(nLineNo, sDigit);
    }

    public void OnMsgNOTIFY(String sMsg)
    {
        mIVaxUserAgentLib.OnMsgNOTIFY(sMsg);
    }

    public void OnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {
        mIVaxUserAgentLib.OnVoiceMailMsg(bIsMsgWaiting, nNewMsgCount, nOldMsgCount, nNewUrgentMsgCount, nOldUrgentMsgCount, sMsgAccount);
    }

    @Override
    public void OnRingToneStarted(String sCallId)
    {
        mIVaxUserAgentLib.OnRingToneStarted(sCallId);
    }

    @Override
    public void OnRingToneEnded(String sCallId)
    {
        mIVaxUserAgentLib.OnRingToneEnded(sCallId);
    }

    public void OnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {
        mIVaxUserAgentLib.OnIncomingDiagnostic(sMsgSIP, sFromIP, nFromPort);
    }

    public void OnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {
        mIVaxUserAgentLib.OnOutgoingDiagnostic(sMsgSIP, sToIP, nToPort);
    }

    public void OnAudioSessionLost(int nLineNo)
    {
        mIVaxUserAgentLib.OnAudioSessionLost(nLineNo);
    }

    public void OnSuccessToHold(int nLineNo)
    {
        mIVaxUserAgentLib.OnSuccessToHold(nLineNo);
    }

    public void OnTryingToHold(int nLineNo)
    {
        mIVaxUserAgentLib.OnTryingToHold(nLineNo);
    }

    public void OnFailToHold(int nLineNo)
    {
        mIVaxUserAgentLib.OnFailToHold(nLineNo);
    }

    public void OnSuccessToUnHold(int nLineNo)
    {
        mIVaxUserAgentLib.OnSuccessToUnHold(nLineNo);
    }

    public void OnTryingToUnHold()
    {
        mIVaxUserAgentLib.OnTryingToUnHold();
    }

    public void OnFailToUnHold(int nLineNo)
    {
        mIVaxUserAgentLib.OnFailToUnHold(nLineNo);
    }

    public void OnChatContactStatus(String sUserName, int nStatusId)
    {
        mIVaxUserAgentLib.OnChatContactStatus(sUserName, nStatusId);
    }

    public void OnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {
        mIVaxUserAgentLib.OnChatSendMsgTextSuccess(sUserName, sMsgText, nUserValue32bit);
    }

    public void OnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {
        mIVaxUserAgentLib.OnChatSendMsgTextFail(sUserName, nStatusCode, sReasonPhrase, sMsgText, nUserValue32bit);
    }

    public void OnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {
        mIVaxUserAgentLib.OnChatSendMsgTypingSuccess(sUserName, nUserValue32bit);
    }

    public void OnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {
        mIVaxUserAgentLib.OnChatSendMsgTypingFail(sUserName, nStatusCode, sReasonPhrase, nUserValue32bit);
    }

    @Override
    public void OnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {
        mIVaxUserAgentLib.OnChatRecvMsgText(sUserName, sMsgText, IsChatContact);
    }

    public void OnChatRecvMsgTypingStart(String sUserName)
    {
        mIVaxUserAgentLib.OnChatRecvMsgTypingStart(sUserName);
    }

    public void OnChatRecvMsgTypingStop(String sUserName)
    {
        mIVaxUserAgentLib.OnChatRecvMsgTypingStop(sUserName);
    }

    public void OnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {
        mIVaxUserAgentLib.OnVoiceStreamPCM(nLineNo, pDataPCM, nSizePCM);
    }

    public void OnDetectedAMD(int nLineNo, boolean bIsHuman)
    {
        mIVaxUserAgentLib.OnDetectedAMD(nLineNo, bIsHuman);
    }

    public void OnHoldCall(int nLineNo)
    {
        mIVaxUserAgentLib.OnHoldCall(nLineNo);
    }

    public void OnUnHoldCall(int nLineNo)
    {
        mIVaxUserAgentLib.OnUnHoldCall(nLineNo);
    }

    public void OnVideoDeviceFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        mIVaxUserAgentLib.OnVideoDeviceFrameRGB(nDeviceId, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    public void OnVideoRemoteFrameRGB(int nLineNo, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        mIVaxUserAgentLib.OnVideoRemoteFrameRGB(nLineNo, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    public void OnVideoRemoteStarted(int nLineNo)
    {
        mIVaxUserAgentLib.OnVideoRemoteStarted(nLineNo);
    }

    public void OnVideoRemoteEnded(int nLineNo)
    {
        mIVaxUserAgentLib.OnVideoRemoteEnded(nLineNo);
    }

    public void OnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnServerConnectingREC(nLineNo, nStatusCode, sReasonPhrase);
    }

    public void OnServerConnectedREC(int nLineNo)
    {
        mIVaxUserAgentLib.OnServerConnectedREC(nLineNo);
    }

    public void OnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnServerFailedREC(nLineNo, nStatusCode, sReasonPhrase);
    }

    public void OnServerHungupREC(int nLineNo)
    {
        mIVaxUserAgentLib.OnServerHungupREC(nLineNo);
    }

    public void OnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {
        mIVaxUserAgentLib.OnAddCallHistory(bOutboundCallType, sCallerName, sCallerId, sDialNo, nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);
    }

    @Override
    public void OnNetworkReachability(boolean bAvailable)
    {
        mIVaxUserAgentLib.OnNetworkReachability(bAvailable);
    }

    @Override
    public void OnAudioDeviceMicVU(int nLevelVU)
    {
        mIVaxUserAgentLib.OnAudioDeviceMicVU(nLevelVU);
    }

    @Override
    public void OnAudioDeviceSpkVU(int nLevelVU)
    {
        mIVaxUserAgentLib.OnAudioDeviceSpkVU(nLevelVU);
    }

    @Override
    public void OnBusyLampSubscribeSuccess(String sUserName)
    {
        mIVaxUserAgentLib.OnBusyLampSubscribeSuccess(sUserName);
    }

    @Override
    public void OnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {
        mIVaxUserAgentLib.OnBusyLampSubscribeFailed(sUserName, nStatusCode, sReasonPhrase);
    }

    @Override
    public void OnBusyLampContactStatus(String sUserName, int nStatusId)
    {
        mIVaxUserAgentLib.OnBusyLampContactStatus(sUserName, nStatusId);
    }

    @Override
    public void OnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
    {
        mIVaxUserAgentLib.OnVaxErrorMsg(sFuncName, sErrorMsg, nErrorCode);
    }
}
