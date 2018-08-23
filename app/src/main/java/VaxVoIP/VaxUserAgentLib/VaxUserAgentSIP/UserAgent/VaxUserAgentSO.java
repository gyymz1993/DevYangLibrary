package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.UserAgent;

import android.util.Log;

public abstract class VaxUserAgentSO extends VaxUserAgentRunnableSO
{
    private static VaxUserAgentSO m_objUserAgentSO = null;

    static
    {
        try
        {
            System.loadLibrary("VaxUserAgentLib");
        }
        catch (UnsatisfiedLinkError e)
        {
            System.err.println("public native code library failed to load.\n" + e);
            Log.e("Tag",e.getMessage());
        }
    }

    public VaxUserAgentSO()
    {
        m_objUserAgentSO = this;

        OnCreatedVaxUserAgentOBJ();
    }

    ////////////////////////////////////////////////////////////////////
    //////////////////////  NATIVE METHODS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public native void OnCreatedVaxUserAgentOBJ();

    public native boolean SetLicenceKey(String sBindKey, Object objContext);

    public native boolean Initialize(String sListenIP, int nListenPort, String sDisplayName, String sUserName, String sAuthLogin,
                                     String sAuthPwd, String sDomainRealm, String sServerAddr, int nServerPort, String sProxyAddr, int nProxyPort, boolean bUseSoundDevice, Object objContext);
    public native void UnInitialize();

    public native boolean RegisterToProxy(int nExpire);
    public native boolean UnRegisterToProxy();

    public native boolean OpenLine(int nLineNo, String sRTPRxIP, int nRxAudioPortRTP, int nRxVideoPortRTP);
    public native boolean OpenLineREC(int nLineNo, String sRTPRxIP, int nAudioPortRTP);
    public native boolean CloseLine(int nLineNo);

    public native int GetVaxErrorCode();
    public native String GetVaxErrorMsg();

    public native String GetCallId(int nLineNo);
    public native boolean AudioDeviceVU(boolean bActivate, boolean bMicVU, boolean bSpkVU);

    public native int GetAudioInDevTotal();
    public native int GetAudioOutDevTotal();

    public native String GetAudioInDevName(int nDeviceId);
    public native String GetAudioOutDevName(int nDeviceId);

    public native boolean DialCall(int nLineNo, String sCallerName, String sCallerId, String sDialNo, int nInputDeviceId, int nOutputDeviceId);
    public native boolean DisconnectCall(int nLineNo);

    public native boolean AcceptCall(int nLineNo, String sCallId, int nInputDeviceId, int nOutputDeviceId);
    public native boolean RejectCall(String sCallId);

    public native boolean TransferCallBlind(int nLineNo, String sToUserName);

    public native boolean TransferCallConsult(int nLineNoA, int nLineNoB);

    public native boolean HoldLine(int nLineNo);
    public native boolean UnHoldLine(int nLineNo);

    public native boolean IsLineOpen(int nLineNo);
    public native boolean IsLineHold(int nLineNo);
    public native boolean IsLineBusy(int nLineNo);
    public native boolean IsLineConnected(int nLineNo);

    public native boolean EnableKeepAlive(int nSeconds);
    public native void DisableKeepAlive();

    public native void DeselectAllVoiceCodec();
    public native void SelectAllVoiceCodec();

    public native boolean SelectVoiceCodec(int nCodecNo);
    public native boolean DeselectVoiceCodec(int nCodecNo);

    public native void DeselectAllVideoCodec();
    public native void SelectAllVideoCodec();

    public native boolean SelectVideoCodec(int nCodecNo);
    public native boolean DeselectVideoCodec(int nCodecNo);

    public native boolean DigitDTMF(int nLineNo, String sDigit);
    public native boolean SetVolumeDTMF(int nVolume);
    public native int GetVolumeDTMF();

    public native boolean ForceDigitDTMF(int nLineNo, int nTypeId, boolean bEnable);

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public native boolean MuteMic(boolean bMute);
    public native boolean MuteSpk(boolean bMute);

    public native boolean MuteLineSpk(int nLineNo, boolean bMute);
    public native boolean MuteLineMic(int nLineNo, boolean bMute);

    public native boolean AutoGainMic(boolean bEnable, int nVolume);
    public native boolean AutoGainSpk(boolean bEnable, int nVolume);

    public native boolean SetVolumeMic(int nVolume);
    public native int GetVolumeMic();

    public native boolean SetVolumeSpk(int nVolume);
    public native int GetVolumeSpk();

    public native boolean SetLineVolumeSpk(int nLineNo, int nVolume);
    public native int GetLineVolumeSpk(int nLineNo);

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public native boolean IsRecording(int nLineNo);
    public native boolean StartRecording(int nLineNo, String sFileName, int nRecordVoice);
    public native boolean StopRecording(int nLineNo);
    public native boolean ResetRecording(int nLineNo);

    public native boolean IsWaveFilePlaying(int nLineNo);

    public native boolean PlayWaveOpen(int nLineNo, String sFileName);
    public native boolean PlayWaveClose(int nLineNo);

    public native boolean PlayWaveSkipTo(int nLineNo, int nSeconds);
    public native int PlayWaveTotalTime(int nLineNo);

    public native boolean PlayWavePause(int nLineNo);
    public native boolean PlayWaveStart(int nLineNo, boolean bListen);

    public native boolean PlayWaveStop(int nLineNo);
    public native int PlayWavePosition(int nLineNo);

    public native boolean EchoCancellation(boolean bEnable);
    public native boolean DonotDisturb(boolean bEnable);

    public native int GetOutboundCodec(int nLineNo);
    public native int GetInboundCodec(int nLineNo);

    public native boolean SetSessionLostTick(int nMinute);

    public native boolean SetUserAgentSIP(String sUserAgentName);
    public native String GetUserAgentSIP();

    public native boolean SetSubjectSDP(String sSubjectSDP);
    public native String GetSubjectSDP();

    public native boolean ConfAllowLine(int nLineNo, boolean bAllowListen, boolean bAllowSpeak);
    public native boolean LineVoiceChannelSpk(int nLineNo, int nChannel);

    public native boolean ChatFindContact(String sUserName);
    public native boolean ChatAddContact(String sUserName, boolean bPresence);
    public native boolean ChatRemoveContact(String sUserName);

    public native boolean ChatSendMessageTyping(String sUserName, int nUserValue32bit);
    public native boolean ChatSendMessageText(String sUserName, String sMsgText, int nMsgType, int nUserValue32bit);

    public native boolean ChatSubscribeContactAll();
    public native boolean ChatSetMyStatus(int nStatusId);

    public native boolean VoiceChanger(int nPitch);
    public native boolean ForwardCall(boolean bEnable, String sToUserName);

    public native boolean PlayAddPCM(int nLineNo, byte[] aDataPCM, int nSizePCM);
    public native boolean PlayResetPCM(int nLineNo);
    public native boolean CaptureStreamPCM(int nLineNo, boolean bEnable);

    public native boolean DetectAMD(int nLineNo, boolean bEnable, int nAnalysisTime, int nSilenceTime, int nSilenceCount);

    public native boolean AddCustomHeader(int nLineNo, int nReqId, String sName, String sValue);
    public native boolean RemoveCustomHeader(int nLineNo, int nReqId, String sName);
    public native boolean RemoveCustomHeaderAll(int nLineNo, int nReqId);

    public native boolean AddCustomHeaderReferToURI(String sName, String sValue);
    public native boolean RemoveCustomHeaderReferToURI(String sName);
    public native boolean RemoveCustomHeaderAllReferToURI();

    public native boolean ActivateQosSIP(int nPriorityQos);
    public native void DeactivateQosSIP();

    public native boolean ActivateQosRTP(int nLineNo, int nPriorityQos);
    public native void DeactivateQosRTP(int nLineNo);

    public native int GetCountPacketLost(int nLineNo);
    public native int GetSizeJitterBuffer(int nLineNo);

    public native boolean ChangeMEDIA(int nLineNo, int nInputDeviceId, int nOutputDeviceId);

    public native int GetVideoDevTotal();
    public native String GetVideoDevName(int nDeviceId);

    public native boolean OpenVideoDev(int nDeviceId, int nQuality);
    public native void CloseVideoDev();

    public native boolean CryptCOMM(boolean bEnable, String sRemoteIP, int nRemotePort);

    public native boolean DialCallToREC(int nLineNo, String sDialNo);
    public native boolean RegisterToProxyREC(boolean bRegister, int nExpire, String sUserName, String sLoginId, String sLoginPwd, String sDisplayName, String sDomainRealm, String sProxySIP);
    public native boolean UnRegisterToProxyREC();

    public native boolean BackgroundMode(boolean bEnable);
    public native boolean SpeakerPhone(boolean bEnable);
    public native boolean IsSpeakerPhone();

    public native boolean DialRingEnable(String sFileName);
    public native boolean DialRingDisable();

    public native boolean BusyRingEnable(String sFileName);
    public native boolean BusyRingDisable();

    public native boolean EnableVideo(int nLineNo, boolean bOutbound, boolean bInbound);

    public native boolean IsNetworkAvailable();
    public native boolean NetworkReachability(boolean bEnable);

    public native boolean AutoRegistration(boolean bEnable, int nTryCount, int nTryAfterSeconds);
    public native boolean VideoCodecBitRate(int nCodecNo, int nQuality);

    public native int BusyLampGetContactStatus(String sUserName);
    public native boolean BusyLampFindContact(String sUserName);
    public native boolean BusyLampAddContact(String sUserName);
    public native boolean BusyLampRemoveContact(String sUserName);
    public native boolean BusyLampSubscribeContactAll();

    ////////////////////////////////////////////////////////////////////
    //////////////////////  STATIC   EVENTS   //////////////////////////
    ///////////////////////////////////////////////////////////////////

    public static void OnInitialized()
    {
        m_objUserAgentSO.PostOnInitialized();
    }

    public static void OnUnInitialized()
    {
        m_objUserAgentSO.PostOnUnInitialized();
    }

    public static void OnConnectingToRegister()
    {
        m_objUserAgentSO.PostOnConnectingToRegister();
    }

    public static void OnTryingToRegister()
    {
        m_objUserAgentSO.PostOnTryingToRegister();
    }

    public static void OnFailToRegister(int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnFailToRegister(nStatusCode, sReasonPhrase);
    }

    public static void OnSuccessToRegister()
    {
        m_objUserAgentSO.PostOnSuccessToRegister();
    }

    public static void OnConnectingToReRegister()
    {
        m_objUserAgentSO.PostOnConnectingToReRegister();
    }

    public static void OnTryingToReRegister()
    {
        m_objUserAgentSO.PostOnTryingToReRegister();
    }

    public static void OnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnFailToReRegister(nStatusCode, sReasonPhrase);
    }

    public static void OnSuccessToReRegister()
    {
        m_objUserAgentSO.PostOnSuccessToReRegister();
    }

    public static void OnTryingToUnRegister()
    {
        m_objUserAgentSO.PostOnTryingToUnRegister();
    }

    public static void OnFailToUnRegister()
    {
        m_objUserAgentSO.PostOnFailToUnRegister();
    }

    public static void OnSuccessToUnRegister()
    {
        m_objUserAgentSO.PostOnSuccessToUnRegister();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public static void OnTryingToRegisterREC()
    {
        m_objUserAgentSO.PostOnTryingToRegisterREC();
    }

    public static void OnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnFailToRegisterREC(nStatusCode, sReasonPhrase);
    }

    public static void OnSuccessToRegisterREC()
    {
        m_objUserAgentSO.PostOnSuccessToRegisterREC();
    }

    public static void OnTryingToReRegisterREC()
    {
        m_objUserAgentSO.PostOnTryingToReRegisterREC();
    }

    public static void OnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnFailToReRegisterREC(nStatusCode, sReasonPhrase);
    }

    public static void OnSuccessToReRegisterREC()
    {
        m_objUserAgentSO.PostOnSuccessToReRegisterREC();
    }

    public static void OnTryingToUnRegisterREC()
    {
        m_objUserAgentSO.PostOnTryingToUnRegisterREC();
    }

    public static void OnFailToUnRegisterREC()
    {
        m_objUserAgentSO.PostOnFailToUnRegisterREC();
    }

    public static void OnSuccessToUnRegisterREC()
    {
        m_objUserAgentSO.PostOnSuccessToUnRegisterREC();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public static void OnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {
        m_objUserAgentSO.PostOnDialCallStarted(nLineNo, sCallerName, sCallerId, sDialNo);

    }

    public static void OnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnDialingCall(nLineNo, nStatusCode, sReasonPhrase);
    }

    public static void OnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {
        m_objUserAgentSO.PostOnDialCallFailed(nLineNo, nStatusCode, sReasonPhrase, sContact);
    }

    public static void OnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {
        m_objUserAgentSO.PostOnConnectedCall(nLineNo, sToRTPIP, nToRTPPort);
    }

    public static void OnHungupCall(int nLineNo)
    {
        m_objUserAgentSO.PostOnHungupCall(nLineNo);
    }

    public static void OnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {
        m_objUserAgentSO.PostOnIncomingCallStarted(sCallId, sCallerName, sCallerId, sDialNo, sFromURI, sToURI);
    }

    public static void OnIncomingCallEnded(String sCallId)
    {
        m_objUserAgentSO.PostOnIncomingCallEnded(sCallId);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public static void OnTransferCallAccepted(int nLineNo)
    {
        m_objUserAgentSO.PostOnTransferCallAccepted(nLineNo);
    }
    public static void OnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnTransferCallFailed(nLineNo, nStatusCode, sReasonPhrase);
    }

    public static void OnPlayWaveDone(int nLineNo)
    {
        m_objUserAgentSO.PostOnPlayWaveDone(nLineNo);
    }
    public static void OnDigitDTMF(int nLineNo, String sDigit)
    {
        m_objUserAgentSO.PostOnDigitDTMF(nLineNo, sDigit);
    }

    public static void OnMsgNOTIFY(String sMsg)
    {
        m_objUserAgentSO.PostOnMsgNOTIFY(sMsg);
    }
    public static void OnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {
        m_objUserAgentSO.PostOnVoiceMailMsg(bIsMsgWaiting, nNewMsgCount, nOldMsgCount, nNewUrgentMsgCount, nOldUrgentMsgCount, sMsgAccount);
    }

    public static void OnRingToneStarted(String sCallId)
    {
        m_objUserAgentSO.PostOnRingToneStarted(sCallId);
    }

    public static void OnRingToneEnded(String sCallId)
    {
        m_objUserAgentSO.PostOnRingToneEnded(sCallId);
    }

    public static void OnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {
        m_objUserAgentSO.PostOnIncomingDiagnostic(sMsgSIP, sFromIP, nFromPort);
    }

    public static void OnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {
        m_objUserAgentSO.PostOnOutgoingDiagnostic(sMsgSIP, sToIP, nToPort);
    }

    public static void OnAudioSessionLost(int nLineNo)
    {
        m_objUserAgentSO.PostOnAudioSessionLost(nLineNo);
    }

    public static void OnSuccessToHold(int nLineNo)
    {
        m_objUserAgentSO.PostOnSuccessToHold(nLineNo);
    }
    public static void OnTryingToHold(int nLineNo)
    {
        m_objUserAgentSO.PostOnTryingToHold(nLineNo);
    }
    public static void OnFailToHold(int nLineNo)
    {
        m_objUserAgentSO.PostOnFailToHold(nLineNo);
    }

    public static void OnSuccessToUnHold(int nLineNo)
    {
        m_objUserAgentSO.PostOnSuccessToUnHold(nLineNo);
    }
    public static void OnTryingToUnHold(int nLineNo)
    {
        m_objUserAgentSO.PostOnTryingToUnHold(nLineNo);
    }
    public static void OnFailToUnHold(int nLineNo)
    {
        m_objUserAgentSO.PostOnFailToUnHold(nLineNo);
    }

    public static void OnChatContactStatus(String sUserName, int nStatusId)
    {
        m_objUserAgentSO.PostOnChatContactStatus(sUserName, nStatusId);
    }

    public static void OnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {
        m_objUserAgentSO.PostOnChatSendMsgTextSuccess(sUserName, sMsgText, nUserValue32bit);
    }
    public static void OnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {
        m_objUserAgentSO.PostOnChatSendMsgTextFail(sUserName, nStatusCode, sReasonPhrase, sMsgText, nUserValue32bit);
    }

    public static void OnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {
        m_objUserAgentSO.PostOnChatSendMsgTypingSuccess(sUserName, nUserValue32bit);
    }
    public static void OnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {
        m_objUserAgentSO.PostOnChatSendMsgTypingFail(sUserName, nStatusCode, sReasonPhrase, nUserValue32bit);
    }

    public static void OnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {
        m_objUserAgentSO.PostOnChatRecvMsgText(sUserName, sMsgText, IsChatContact);
    }
    public static void OnChatRecvMsgTypingStart(String sUserName)
    {
        m_objUserAgentSO.PostOnChatRecvMsgTypingStart(sUserName);
    }
    public static void OnChatRecvMsgTypingStop(String sUserName)
    {
        m_objUserAgentSO.PostOnChatRecvMsgTypingStop(sUserName);
    }

    public static void OnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {
        m_objUserAgentSO.PostOnVoiceStreamPCM(nLineNo, pDataPCM, nSizePCM);
    }
    public static void OnDetectedAMD(int nLineNo, boolean bIsHuman)
    {
        m_objUserAgentSO.PostOnDetectedAMD(nLineNo, bIsHuman);
    }

    public static void OnHoldCall(int nLineNo)
    {
        m_objUserAgentSO.PostOnHoldCall(nLineNo);
    }
    public static void OnUnHoldCall(int nLineNo)
    {
        m_objUserAgentSO.PostOnUnHoldCall(nLineNo);
    }

    public static void OnVideoDeviceFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        m_objUserAgentSO.PostOnVideoDeviceFrameRGB(nDeviceId, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    public static void OnVideoRemoteFrameRGB(int nLineNo, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        m_objUserAgentSO.PostOnVideoRemoteFrameRGB(nLineNo, pFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    public static void OnVideoRemoteStarted(int nLineNo)
    {
        m_objUserAgentSO.PostOnVideoRemoteStarted(nLineNo);
    }
    public static void OnVideoRemoteEnded(int nLineNo)
    {
        m_objUserAgentSO.PostOnVideoRemoteEnded(nLineNo);
    }

    public static void OnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnServerConnectingREC(nLineNo, nStatusCode, sReasonPhrase);
    }
    public static void OnServerConnectedREC(int nLineNo)
    {
        m_objUserAgentSO.PostOnServerConnectedREC(nLineNo);
    }
    public static void OnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostOnServerFailedREC(nLineNo, nStatusCode, sReasonPhrase);
    }
    public static void OnServerHungupREC(int nLineNo)
    {
        m_objUserAgentSO.PostOnServerHungupREC(nLineNo);
    }

    public static void OnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {
        m_objUserAgentSO.PostOnAddCallHistory(bOutboundCallType, sCallerName, sCallerId, sDialNo, nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);
    }

    public static void OnNetworkReachability(boolean bAvailable)
    {
        m_objUserAgentSO.PostOnNetworkReachability(bAvailable);
    }

    public static void OnAudioDeviceMicVU(int nLevelVU)
    {
        m_objUserAgentSO.PostOnAudioDeviceMicVU(nLevelVU);
    }
    public static void OnAudioDeviceSpkVU(int nLevelVU)
    {
        m_objUserAgentSO.PostOnAudioDeviceSpkVU(nLevelVU);
    }

    public static void OnBusyLampSubscribeSuccess(String sUserName)
    {
        m_objUserAgentSO.PostBusyLampSubscribeSuccess(sUserName);
    }

    public static void OnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {
        m_objUserAgentSO.PostBusyLampSubscribeFailed(sUserName, nStatusCode, sReasonPhrase);
    }

    public static void OnBusyLampContactStatus(String sUserName, int nStatusId)
    {
        m_objUserAgentSO.PostBusyLampContactStatus(sUserName, nStatusId);
    }

    public static void OnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
    {
        m_objUserAgentSO.PostOnVaxErrorMsg(sFuncName, sErrorMsg, nErrorCode);
    }

    //////////////////////////////////////////////////
    ////////////   RUNNABLE EVENTS     ///////////////
    /////////////////////////////////////////////////

    protected abstract void EventOnInitialized();

    public void RunnableOnInitialized()
    {
        EventOnInitialized();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnUnInitialized();

    public void RunnableOnUnInitialized()
    {
        EventOnUnInitialized();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnConnectingToRegister();

    public void RunnableOnConnectingToRegister()
    {
        EventOnConnectingToRegister();
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToRegister();

    public void RunnableOnTryingToRegister()
    {
        EventOnTryingToRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToRegister(int nStatusCode, String sReasonPhrase);

    public void RunnableOnFailToRegister(int nStatusCode, String sReasonPhrase)
    {
        EventOnFailToRegister(nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToRegister();

    public void RunnableOnSuccessToRegister()
    {
        EventOnSuccessToRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnConnectingToReRegister();

    public void RunnableOnConnectingToReRegister()
    {
        EventOnConnectingToReRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToReRegister();

    public void RunnableOnTryingToReRegister()
    {
        EventOnTryingToReRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToReRegister(int nStatusCode, String sReasonPhrase);

    public void RunnableOnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {
        EventOnFailToReRegister(nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToReRegister();

    public void RunnableOnSuccessToReRegister()
    {
        EventOnSuccessToReRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToUnRegister();

    public void RunnableOnTryingToUnRegister()
    {
        EventOnTryingToUnRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToUnRegister();

    public void RunnableOnFailToUnRegister()
    {
        EventOnFailToUnRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToUnRegister();

    public void RunnableOnSuccessToUnRegister()
    {
        EventOnSuccessToUnRegister();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToRegisterREC();

    public void RunnableOnTryingToRegisterREC()
    {
        EventOnTryingToRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToRegisterREC(int nStatusCode, String sReasonPhrase);

    public void RunnableOnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        EventOnFailToRegisterREC(nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToRegisterREC();

    public void RunnableOnSuccessToRegisterREC()
    {
        EventOnSuccessToRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToReRegisterREC();

    public void RunnableOnTryingToReRegisterREC()
    {
        EventOnTryingToReRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToReRegisterREC(int nStatusCode, String sReasonPhrase);

    public void RunnableOnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        EventOnFailToReRegisterREC(nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToReRegisterREC();

    public void RunnableOnSuccessToReRegisterREC()
    {
        EventOnSuccessToReRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToUnRegisterREC();

    public void RunnableOnTryingToUnRegisterREC()
    {
        EventOnTryingToUnRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToUnRegisterREC();

    public void RunnableOnFailToUnRegisterREC()
    {
        EventOnFailToUnRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToUnRegisterREC();

    public void RunnableOnSuccessToUnRegisterREC()
    {
        EventOnSuccessToUnRegisterREC();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo);

    public void RunnableOnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {
        EventOnDialCallStarted(nLineNo, sCallerName, sCallerId, sDialNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase);

    public void RunnableOnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        EventOnDialingCall(nLineNo, nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact);

    public void RunnableOnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {
        EventOnDialCallFailed(nLineNo, nStatusCode, sReasonPhrase, sContact);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort);

    public void RunnableOnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {
        EventOnConnectedCall(nLineNo, sToRTPIP, nToRTPPort);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnHungupCall(int nLineNo);

    public void RunnableOnHungupCall(int nLineNo)
    {
        EventOnHungupCall(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI);

    public void RunnableOnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {
        EventOnIncomingCallStarted(sCallId, sCallerName, sCallerId, sDialNo, sFromURI, sToURI);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnIncomingCallEnded(String sCallId);

    public void RunnableOnIncomingCallEnded(String sCallId)
    {
        EventOnIncomingCallEnded(sCallId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTransferCallAccepted(int nLineNo);

    public void RunnableOnTransferCallAccepted(int nLineNo)
    {
        EventOnTransferCallAccepted(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase);

    public void RunnableOnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        EventOnTransferCallFailed(nLineNo, nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnPlayWaveDone(int nLineNo);

    public void RunnableOnPlayWaveDone(int nLineNo)
    {
        EventOnPlayWaveDone(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnDigitDTMF(int nLineNo, String sDigit);

    public void RunnableOnDigitDTMF(int nLineNo, String sDigit)
    {
        EventOnDigitDTMF(nLineNo, sDigit);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnMsgNOTIFY(String sMsg);

    public void RunnableOnMsgNOTIFY(String sMsg)
    {
        EventOnMsgNOTIFY(sMsg);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount);

    public void RunnableOnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {
        EventOnVoiceMailMsg(bIsMsgWaiting, nNewMsgCount, nOldMsgCount, nNewUrgentMsgCount, nOldUrgentMsgCount, sMsgAccount);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnRingToneStarted(String sCallId);

    public void RunnableOnRingToneStarted(String sCallId)
    {
        EventOnRingToneStarted(sCallId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////


    protected abstract void EventOnRingToneEnded(String sCallId);

    public void RunnableOnRingToneEnded(String sCallId)
    {
        EventOnRingToneEnded(sCallId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////


    protected abstract void EventOnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort);

    public void RunnableOnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {
        EventOnIncomingDiagnostic(sMsgSIP, sFromIP, nFromPort);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort);

    public void RunnableOnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {
        EventOnOutgoingDiagnostic(sMsgSIP, sToIP, nToPort);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnAudioSessionLost(int nLineNo);

    public void RunnableOnAudioSessionLost(int nLineNo)
    {
        EventOnAudioSessionLost(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToHold(int nLineNo);

    public void RunnableOnSuccessToHold(int nLineNo)
    {
        EventOnSuccessToHold(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToHold(int nLineNo);

    public void RunnableOnTryingToHold(int nLineNo)
    {
        EventOnTryingToHold(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToHold(int nLineNo);

    public void RunnableOnFailToHold(int nLineNo)
    {
        EventOnFailToHold(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnSuccessToUnHold(int nLineNo);

    public void RunnableOnSuccessToUnHold(int nLineNo)
    {
        EventOnSuccessToUnHold(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnTryingToUnHold();

    public void RunnableOnTryingToUnHold(int nLineNo)
    {
        EventOnTryingToUnHold();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnFailToUnHold(int nLineNo);

    public void RunnableOnFailToUnHold(int nLineNo)
    {
        EventOnFailToUnHold(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatContactStatus(String sUserName, int nStatusId);

    public void RunnableOnChatContactStatus(String sUserName, int nStatusId)
    {
        EventOnChatContactStatus(sUserName, nStatusId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit);

    public void RunnableOnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {
        EventOnChatSendMsgTextSuccess(sUserName, sMsgText, nUserValue32bit);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit);

    public void RunnableOnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {
        EventOnChatSendMsgTextFail(sUserName, nStatusCode, sReasonPhrase, sMsgText, nUserValue32bit);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit);

    public void RunnableOnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {
        EventOnChatSendMsgTypingSuccess(sUserName, nUserValue32bit);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit);

    public void RunnableOnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {
        EventOnChatSendMsgTypingFail(sUserName, nStatusCode, sReasonPhrase, nUserValue32bit);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact);

    public void RunnableOnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {
        EventOnChatRecvMsgText(sUserName, sMsgText, IsChatContact);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatRecvMsgTypingStart(String sUserName);

    public void RunnableOnChatRecvMsgTypingStart(String sUserName)
    {
        EventOnChatRecvMsgTypingStart(sUserName);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnChatRecvMsgTypingStop(String sUserName);

    public void RunnableOnChatRecvMsgTypingStop(String sUserName)
    {
        EventOnChatRecvMsgTypingStop(sUserName);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM);

    public void RunnableOnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {
        EventOnVoiceStreamPCM(nLineNo, pDataPCM, nSizePCM);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnDetectedAMD(int nLineNo, boolean bIsHuman);

    public void RunnableOnDetectedAMD(int nLineNo, boolean bIsHuman)
    {
        EventOnDetectedAMD(nLineNo, bIsHuman);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnHoldCall(int nLineNo);

    public void RunnableOnHoldCall(int nLineNo)
    {
        EventOnHoldCall(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnUnHoldCall(int nLineNo);

    public void RunnableOnUnHoldCall(int nLineNo)
    {
        EventOnUnHoldCall(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight);

    public void RunnableOnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        EventOnVideoDeviceFrameRGB(nDeviceId, aFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight);

    public void RunnableOnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        EventOnVideoRemoteFrameRGB(nLineNo, aFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVideoRemoteStarted(int nLineNo);

    public void RunnableOnVideoRemoteStarted(int nLineNo)
    {
        EventOnVideoRemoteStarted(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVideoRemoteEnded(int nLineNo);

    public void RunnableOnVideoRemoteEnded(int nLineNo)
    {
        EventOnVideoRemoteEnded(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase);

    public void RunnableOnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        EventOnServerConnectingREC(nLineNo, nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnServerConnectedREC(int nLineNo);

    public void RunnableOnServerConnectedREC(int nLineNo)
    {
        EventOnServerConnectedREC(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase);

    public void RunnableOnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        EventOnServerFailedREC(nLineNo, nStatusCode, sReasonPhrase);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnServerHungupREC(int nLineNo);

    public void RunnableOnServerHungupREC(int nLineNo)
    {
        EventOnServerHungupREC(nLineNo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId);

    public void RunnableOnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {
        EventOnAddCallHistory(bOutboundCallType, sCallerName, sCallerId, sDialNo, nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    protected abstract void EventOnNetworkReachability(boolean bAvailable);

    public void RunnableOnNetworkReachability(boolean bAvailable)
    {
        EventOnNetworkReachability(bAvailable);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    protected abstract void EventOnAudioDeviceMicVU(int nLevelVU);

    public void RunnableOnAudioDeviceMicVU(int nLevelVU)
    {
        EventOnAudioDeviceMicVU(nLevelVU);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    protected abstract void EventOnAudioDeviceSpkVU(int nLevelVU);

    public void RunnableOnAudioDeviceSpkVU(int nLevelVU)
    {
        EventOnAudioDeviceSpkVU(nLevelVU);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnBusyLampSubscribeSuccess(String sUserName);

    public void RunnableOnBusyLampSubscribeSuccess(String sUserName)
    {
        EventOnBusyLampSubscribeSuccess(sUserName);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase);

    public void RunnableOnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {
        EventOnBusyLampSubscribeFailed(sUserName, nStatusCode, sReasonPhrase);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnBusyLampContactStatus(String sUserName, int nStatusId);

    public void RunnableOnBusyLampContactStatus(String sUserName, int nStatusId)
    {
        EventOnBusyLampContactStatus(sUserName, nStatusId);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    protected abstract void EventOnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode);

    public void RunnableOnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
    {
        EventOnVaxErrorMsg(sFuncName, sErrorMsg, nErrorCode);
    }

}

