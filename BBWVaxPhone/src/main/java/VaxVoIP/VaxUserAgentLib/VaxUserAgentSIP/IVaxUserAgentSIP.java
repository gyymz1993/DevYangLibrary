package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP;

public interface IVaxUserAgentSIP
{
     void OnInitialized();
     void OnUnInitialized();

     void OnConnectingToRegister();
     void OnTryingToRegister();
     void OnFailToRegister(int nStatusCode, String sReasonPhrase);
     void OnSuccessToRegister();

     void OnConnectingToReRegister();
     void OnTryingToReRegister();
     void OnFailToReRegister(int nStatusCode, String sReasonPhrase);
     void OnSuccessToReRegister();

     void OnTryingToUnRegister();
     void OnFailToUnRegister();
     void OnSuccessToUnRegister();

     void OnTryingToRegisterREC();
     void OnFailToRegisterREC(int nStatusCode, String sReasonPhrase);
     void OnSuccessToRegisterREC();
     void OnTryingToReRegisterREC();
     void OnFailToReRegisterREC(int nStatusCode, String sReasonPhrase);

     void OnSuccessToReRegisterREC();
     void OnTryingToUnRegisterREC();
     void OnFailToUnRegisterREC();
     void OnSuccessToUnRegisterREC();

     void OnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo);
     void OnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase);
     void OnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact);

     void OnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort);
     void OnHungupCall(int nLineNo);

     void OnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI);
     void OnIncomingCallEnded(String sCallId);

     void OnTransferCallAccepted(int nLineNo);
     void OnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase);

     void OnPlayWaveDone(int nLineNo);
     void OnDigitDTMF(int nLineNo, String sDigit);
     void OnMsgNOTIFY(String sMsg);
     void OnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount);

     void OnRingToneStarted(String sCallId);
     void OnRingToneEnded(String sCallId);

     void OnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort);
     void OnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort);

     void OnAudioSessionLost(int nLineNo);
     void OnSuccessToHold(int nLineNo);
     void OnTryingToHold(int nLineNo);
     void OnFailToHold(int nLineNo);
     void OnSuccessToUnHold(int nLineNo);
     void OnTryingToUnHold();
     void OnFailToUnHold(int nLineNo);

     void OnChatContactStatus(String sUserName, int nStatusId);
     void OnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit);
     void OnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit);
     void OnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit);
     void OnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit);
     void OnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact);
     void OnChatRecvMsgTypingStart(String sUserName);
     void OnChatRecvMsgTypingStop(String sUserName);

     void OnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM);
     void OnDetectedAMD(int nLineNo, boolean bIsHuman);

     void OnHoldCall(int nLineNo);
     void OnUnHoldCall(int nLineNo);

     void OnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight);
     void OnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight);
     void OnVideoRemoteStarted(int nLineNo);
     void OnVideoRemoteEnded(int nLineNo);

     void OnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase);
     void OnServerConnectedREC(int nLineNo);
     void OnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase);
     void OnServerHungupREC(int nLineNo);

     void OnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId);
     void OnNetworkReachability(boolean bAvailable);

     void OnAudioDeviceMicVU(int nLevelVU);
     void OnAudioDeviceSpkVU(int nLevelVU);

     void OnBusyLampSubscribeSuccess(String sUserName);
     void OnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase);
     void OnBusyLampContactStatus(String sUserName, int nStatusId);

     void OnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode);


}
