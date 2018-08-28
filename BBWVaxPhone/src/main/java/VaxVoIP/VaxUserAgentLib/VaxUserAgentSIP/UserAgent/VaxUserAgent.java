package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.UserAgent;

public class VaxUserAgent extends VaxUserAgentSO
{
    protected VaxUserAgent()
    {

    }


    ////////////////////////////////////////////////////////////////////
    /////////////////////////   EVENTS   //////////////////////////////
    ///////////////////////////////////////////////////////////////////

    @Override
    protected void EventOnInitialized()
    {

    }

    @Override
    protected void EventOnUnInitialized()
    {

    }

    @Override
    protected void EventOnConnectingToRegister()
    {

    }

    @Override
    protected void EventOnTryingToRegister()
    {

    }

    @Override
    protected void EventOnFailToRegister(int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnSuccessToRegister()
    {

    }

    @Override
    protected void EventOnConnectingToReRegister()
    {

    }

    @Override
    protected void EventOnTryingToReRegister()
    {

    }

    @Override
    protected void EventOnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnSuccessToReRegister()
    {

    }

    @Override
    protected void EventOnTryingToUnRegister()
    {

    }

    @Override
    protected void EventOnFailToUnRegister()
    {

    }

    @Override
    protected void EventOnSuccessToUnRegister()
    {

    }

    @Override
    protected void EventOnTryingToRegisterREC()
    {

    }

    @Override
    protected void EventOnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnSuccessToRegisterREC()
    {

    }

    @Override
    protected void EventOnTryingToReRegisterREC()
    {

    }

    @Override
    protected void EventOnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnSuccessToReRegisterREC()
    {

    }

    @Override
    protected void EventOnTryingToUnRegisterREC()
    {

    }

    @Override
    protected void EventOnFailToUnRegisterREC()
    {

    }

    @Override
    protected void EventOnSuccessToUnRegisterREC()
    {

    }

    @Override
    protected void EventOnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {

    }

    @Override
    protected void EventOnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {

    }

    @Override
    protected void EventOnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {

    }

    @Override
    protected void EventOnHungupCall(int nLineNo)
    {

    }

    @Override
    protected void EventOnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {

    }

    @Override
    protected void EventOnIncomingCallEnded(String sCallId)
    {

    }

    @Override
    protected void EventOnTransferCallAccepted(int nLineNo)
    {

    }

    @Override
    protected void EventOnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnPlayWaveDone(int nLineNo)
    {

    }

    @Override
    protected void EventOnDigitDTMF(int nLineNo, String sDigit)
    {

    }

    @Override
    protected void EventOnMsgNOTIFY(String sMsg)
    {

    }

    @Override
    protected void EventOnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {

    }

    @Override
    protected void EventOnRingToneStarted(String sCallId)
    {

    }

    @Override
    protected void EventOnRingToneEnded(String sCallId)
    {

    }

    @Override
    protected void EventOnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {

    }

    @Override
    protected void EventOnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {

    }

    @Override
    protected void EventOnAudioSessionLost(int nLineNo)
    {

    }

    @Override
    protected void EventOnSuccessToHold(int nLineNo)
    {

    }

    @Override
    protected void EventOnTryingToHold(int nLineNo)
    {

    }

    @Override
    protected void EventOnFailToHold(int nLineNo)
    {

    }

    @Override
    protected void EventOnSuccessToUnHold(int nLineNo)
    {

    }

    @Override
    protected void EventOnTryingToUnHold()
    {

    }

    @Override
    protected void EventOnFailToUnHold(int nLineNo)
    {

    }

    @Override
    protected void EventOnChatContactStatus(String sUserName, int nStatusId)
    {

    }

    @Override
    protected void EventOnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {

    }

    @Override
    protected void EventOnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {

    }

    @Override
    protected void EventOnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {

    }

    @Override
    protected void EventOnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {

    }

    @Override
    protected void EventOnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {

    }

    @Override
    protected void EventOnChatRecvMsgTypingStart(String sUserName)
    {

    }

    @Override
    protected void EventOnChatRecvMsgTypingStop(String sUserName)
    {

    }

    @Override
    protected void EventOnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {

    }

    @Override
    protected void EventOnDetectedAMD(int nLineNo, boolean bIsHuman)
    {

    }

    @Override
    protected void EventOnHoldCall(int nLineNo)
    {

    }

    @Override
    protected void EventOnUnHoldCall(int nLineNo)
    {

    }

    @Override
    protected void EventOnVideoDeviceFrameRGB(int nDeviceId, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {

    }

    @Override
    protected void EventOnVideoRemoteFrameRGB(int nLineNo, byte[] pFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {

    }

    @Override
    protected void EventOnVideoRemoteStarted(int nLineNo)
    {

    }

    @Override
    protected void EventOnVideoRemoteEnded(int nLineNo)
    {

    }

    @Override
    protected void EventOnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnServerConnectedREC(int nLineNo)
    {

    }

    @Override
    protected void EventOnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnServerHungupREC(int nLineNo)
    {

    }

    @Override
    protected void EventOnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {

    }

    @Override
    protected void EventOnNetworkReachability(boolean bAvailable)
    {

    }

    @Override
    protected void EventOnAudioDeviceMicVU(int nLevelVU)
    {

    }

    @Override
    protected void EventOnAudioDeviceSpkVU(int nLevelVU)
    {

    }

    @Override
    protected void EventOnBusyLampSubscribeSuccess(String sUserName)
    {

    }

    @Override
    protected void EventOnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {

    }

    @Override
    protected void EventOnBusyLampContactStatus(String sUserName, int nStatusId)
    {

    }

    @Override
    protected void EventOnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
    {

    }
}
