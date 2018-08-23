package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP;

import android.content.Context;
import android.util.Log;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.AudioDevice.AudioDevice;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.SocketIP.SocketIP;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.UserAgent.VaxUserAgent;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.VideoDevice.VideoDevice;
import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Network.Network;

public class VaxUserAgentSIP extends VaxUserAgent
{
    static
    {
        try
        {
            System.loadLibrary("avcodec");
            System.loadLibrary("avfilter");
            System.loadLibrary("avformat");
            System.loadLibrary("avutil");
            System.loadLibrary("swresample");
            System.loadLibrary("swscale");
        }
        catch (UnsatisfiedLinkError e)
        {
            System.err.println("public native code library failed to load.\n" + e);
            Log.e("Tag",e.getMessage());
        }
    }

    private IVaxUserAgentSIP m_iVaxUserAgentSIP;

    private AudioDevice m_objAudioDevice = null;
    private SocketIP m_objSocketIP = null;
    private VideoDevice m_objVideoDevice = null;
    private Network m_objNetwork = null;

    public VaxUserAgentSIP(IVaxUserAgentSIP iVaxUserAgentSIP, Context objContext)
    {
        m_iVaxUserAgentSIP = iVaxUserAgentSIP;

        m_objAudioDevice = new AudioDevice(objContext);
        m_objSocketIP = new SocketIP();
        m_objVideoDevice = new VideoDevice(objContext);
        m_objNetwork = new Network(objContext);
    }

    ////////////////////////////////////////////////////////////////////
    /////////////////////////   EVENTS   //////////////////////////////
    ///////////////////////////////////////////////////////////////////

    @Override
    protected void EventOnInitialized()
    {
        m_iVaxUserAgentSIP.OnInitialized();
    }

    @Override
    protected void EventOnUnInitialized()
    {
        m_iVaxUserAgentSIP.OnUnInitialized();
    }

    @Override
    protected void EventOnConnectingToRegister()
    {
        m_iVaxUserAgentSIP.OnConnectingToRegister();
    }

    @Override
    protected void EventOnTryingToRegister()
    {
        m_iVaxUserAgentSIP.OnTryingToRegister();
    }

    @Override
    protected void EventOnFailToRegister(int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnFailToRegister(nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnSuccessToRegister()
    {
        m_iVaxUserAgentSIP.OnSuccessToRegister();
    }

    @Override
    protected void EventOnConnectingToReRegister()
    {
        m_iVaxUserAgentSIP.OnConnectingToReRegister();
    }

    @Override
    protected void EventOnTryingToReRegister()
    {
        m_iVaxUserAgentSIP.OnTryingToReRegister();
    }

    @Override
    protected void EventOnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnFailToReRegister(nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnSuccessToReRegister()
    {
        m_iVaxUserAgentSIP.OnSuccessToReRegister();
    }

    @Override
    protected void EventOnTryingToUnRegister()
    {
        m_iVaxUserAgentSIP.OnTryingToUnRegister();
    }

    @Override
    protected void EventOnFailToUnRegister()
    {
        m_iVaxUserAgentSIP.OnFailToUnRegister();
    }

    @Override
    protected void EventOnSuccessToUnRegister()
    {
        m_iVaxUserAgentSIP.OnSuccessToUnRegister();
    }

    @Override
    protected void EventOnTryingToRegisterREC()
    {
        m_iVaxUserAgentSIP.OnTryingToRegisterREC();
    }

    @Override
    protected void EventOnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnFailToRegisterREC(nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnSuccessToRegisterREC()
    {
        m_iVaxUserAgentSIP.OnSuccessToRegisterREC();
    }

    @Override
    protected void EventOnTryingToReRegisterREC()
    {
        m_iVaxUserAgentSIP.OnTryingToReRegisterREC();
    }

    @Override
    protected void EventOnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnFailToReRegisterREC(nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnSuccessToReRegisterREC()
    {
        m_iVaxUserAgentSIP.OnSuccessToReRegisterREC();
    }

    @Override
    protected void EventOnTryingToUnRegisterREC()
    {
        m_iVaxUserAgentSIP.OnTryingToUnRegisterREC();
    }

    @Override
    protected void EventOnFailToUnRegisterREC()
    {
        m_iVaxUserAgentSIP.OnFailToUnRegisterREC();
    }

    @Override
    protected void EventOnSuccessToUnRegisterREC()
    {
        m_iVaxUserAgentSIP.OnSuccessToUnRegisterREC();
    }

    @Override
    protected void EventOnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {
        m_iVaxUserAgentSIP.OnDialCallStarted(nLineNo, sCallerName, sCallerId, sDialNo);
    }

    @Override
    protected void EventOnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnDialingCall(nLineNo, nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {
        m_iVaxUserAgentSIP.OnDialCallFailed(nLineNo, nStatusCode, sReasonPhrase, sContact);
    }

    @Override
    protected void EventOnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {
        m_iVaxUserAgentSIP.OnConnectedCall(nLineNo, sToRTPIP, nToRTPPort);
    }

    @Override
    protected void EventOnHungupCall(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnHungupCall(nLineNo);
    }

    @Override
    protected void EventOnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {
        m_iVaxUserAgentSIP.OnIncomingCallStarted(sCallId, sCallerName, sCallerId, sDialNo, sFromURI, sToURI);
    }

    @Override
    protected void EventOnIncomingCallEnded(String sCallId)
    {
        m_iVaxUserAgentSIP.OnIncomingCallEnded(sCallId);
    }

    @Override
    protected void EventOnTransferCallAccepted(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnTransferCallAccepted(nLineNo);
    }

    @Override
    protected void EventOnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnTransferCallFailed(nLineNo, nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnPlayWaveDone(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnPlayWaveDone(nLineNo);
    }

    @Override
    protected void EventOnDigitDTMF(int nLineNo, String sDigit)
    {
        m_iVaxUserAgentSIP.OnDigitDTMF(nLineNo, sDigit);
    }

    @Override
    protected void EventOnMsgNOTIFY(String sMsg)
    {
        m_iVaxUserAgentSIP.OnMsgNOTIFY(sMsg);
    }

    @Override
    protected void EventOnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {
        m_iVaxUserAgentSIP.OnVoiceMailMsg(bIsMsgWaiting, nNewMsgCount, nOldMsgCount, nNewUrgentMsgCount, nOldUrgentMsgCount, sMsgAccount);
    }

    @Override
    protected void EventOnRingToneStarted(String sCallId)
    {
        m_iVaxUserAgentSIP.OnRingToneStarted(sCallId);
    }

    @Override
    protected void EventOnRingToneEnded(String sCallId)
    {
        m_iVaxUserAgentSIP.OnRingToneEnded(sCallId);
    }

    @Override
    protected void EventOnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {
        m_iVaxUserAgentSIP.OnIncomingDiagnostic(sMsgSIP, sFromIP, nFromPort);
    }

    @Override
    protected void EventOnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {
        m_iVaxUserAgentSIP.OnOutgoingDiagnostic(sMsgSIP, sToIP, nToPort);
    }

    @Override
    protected void EventOnAudioSessionLost(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnAudioSessionLost(nLineNo);
    }

    @Override
    protected void EventOnSuccessToHold(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnSuccessToHold(nLineNo);
    }

    @Override
    protected void EventOnTryingToHold(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnTryingToHold(nLineNo);
    }

    @Override
    protected void EventOnFailToHold(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnFailToHold(nLineNo);
    }

    @Override
    protected void EventOnSuccessToUnHold(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnSuccessToUnHold(nLineNo);
    }

    @Override
    protected void EventOnTryingToUnHold()
    {
        m_iVaxUserAgentSIP.OnTryingToUnHold();
    }

    @Override
    protected void EventOnFailToUnHold(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnFailToUnHold(nLineNo);
    }

    @Override
    protected void EventOnChatContactStatus(String sUserName, int nStatusId)
    {
        m_iVaxUserAgentSIP.OnChatContactStatus(sUserName, nStatusId);
    }

    @Override
    protected void EventOnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {
        m_iVaxUserAgentSIP.OnChatSendMsgTextSuccess(sUserName, sMsgText, nUserValue32bit);
    }

    @Override
    protected void EventOnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {
        m_iVaxUserAgentSIP.OnChatSendMsgTextFail(sUserName, nStatusCode, sReasonPhrase, sMsgText, nUserValue32bit);
    }

    @Override
    protected void EventOnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {
        m_iVaxUserAgentSIP.OnChatSendMsgTypingSuccess(sUserName, nUserValue32bit);
    }

    @Override
    protected void EventOnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {
        m_iVaxUserAgentSIP.OnChatSendMsgTypingFail(sUserName, nStatusCode, sReasonPhrase, nUserValue32bit);
    }

    @Override
    protected void EventOnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {
        m_iVaxUserAgentSIP.OnChatRecvMsgText(sUserName, sMsgText, IsChatContact);
    }

    @Override
    protected void EventOnChatRecvMsgTypingStart(String sUserName)
    {
        m_iVaxUserAgentSIP.OnChatRecvMsgTypingStart(sUserName);
    }

    @Override
    protected void EventOnChatRecvMsgTypingStop(String sUserName)
    {
        m_iVaxUserAgentSIP.OnChatRecvMsgTypingStop(sUserName);
    }

    @Override
    protected void EventOnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {
        m_iVaxUserAgentSIP.OnVoiceStreamPCM(nLineNo, pDataPCM, nSizePCM);
    }

    @Override
    protected void EventOnDetectedAMD(int nLineNo, boolean bIsHuman)
    {
        m_iVaxUserAgentSIP.OnDetectedAMD(nLineNo, bIsHuman);
    }

    @Override
    protected void EventOnHoldCall(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnHoldCall(nLineNo);
    }

    @Override
    protected void EventOnUnHoldCall(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnUnHoldCall(nLineNo);
    }

    @Override
    protected void EventOnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        m_iVaxUserAgentSIP.OnVideoDeviceFrameRGB(nDeviceId, aFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    @Override
    protected void EventOnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        m_iVaxUserAgentSIP.OnVideoRemoteFrameRGB(nLineNo, aFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
    }

    @Override
    protected void EventOnVideoRemoteStarted(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnVideoRemoteStarted(nLineNo);
    }

    @Override
    protected void EventOnVideoRemoteEnded(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnVideoRemoteEnded(nLineNo);
    }

    @Override
    protected void EventOnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnServerConnectingREC(nLineNo, nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnServerConnectedREC(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnServerConnectedREC(nLineNo);
    }

    @Override
    protected void EventOnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnServerFailedREC(nLineNo, nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnServerHungupREC(int nLineNo)
    {
        m_iVaxUserAgentSIP.OnServerHungupREC(nLineNo);
    }

    @Override
    protected void EventOnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {
        m_iVaxUserAgentSIP.OnAddCallHistory(bOutboundCallType, sCallerName, sCallerId, sDialNo, nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);
    }

    @Override
    protected void EventOnNetworkReachability(boolean bAvailable)
    {
        m_iVaxUserAgentSIP.OnNetworkReachability(bAvailable);
    }

    @Override
    protected void EventOnAudioDeviceMicVU(int nLevelVU)
    {
        m_iVaxUserAgentSIP.OnAudioDeviceMicVU(nLevelVU);
    }

    @Override
    protected void EventOnAudioDeviceSpkVU(int nLevelVU)
    {
        m_iVaxUserAgentSIP.OnAudioDeviceSpkVU(nLevelVU);
    }

    @Override
    protected void EventOnBusyLampSubscribeSuccess(String sUserName)
    {
        m_iVaxUserAgentSIP.OnBusyLampSubscribeSuccess(sUserName);
    }

    @Override
    protected void EventOnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {
        m_iVaxUserAgentSIP.OnBusyLampSubscribeFailed(sUserName, nStatusCode, sReasonPhrase);
    }

    @Override
    protected void EventOnBusyLampContactStatus(String sUserName, int nStatusId)
    {
        m_iVaxUserAgentSIP.OnBusyLampContactStatus(sUserName, nStatusId);
    }

    @Override
    protected void EventOnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
    {
        m_iVaxUserAgentSIP.OnVaxErrorMsg(sFuncName, sErrorMsg, nErrorCode);
    }


}

