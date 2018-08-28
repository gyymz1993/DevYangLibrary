package VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.UserAgent;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentSIP.Runnable.VaxRunnable;

abstract class VaxUserAgentRunnableSO
{
    private VaxRunnable m_objRunnable = null;

    VaxUserAgentRunnableSO()
    {
        m_objRunnable = new VaxRunnable();
    }

    //////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    class OnInitialized implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnInitialized();
        }
    }

    void PostOnInitialized()
    {
        OnInitialized objOnInitialized = new OnInitialized();
        m_objRunnable.PostRunnableMsg(objOnInitialized, false);
    }

    protected abstract void RunnableOnInitialized();

    //////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    class OnUnInitialized implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnUnInitialized();
        }
    }

    void PostOnUnInitialized()
    {
        OnUnInitialized objOnUnInitialized = new OnUnInitialized();
        m_objRunnable.PostRunnableMsg(objOnUnInitialized, false);
    }

    protected abstract void RunnableOnUnInitialized();

    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    class OnConnectingToRegister implements Runnable
    {

        @Override
        public void run()
        {
            RunnableOnConnectingToRegister();
        }
    }

    void PostOnConnectingToRegister()
    {
        OnConnectingToRegister objOnConnectingToRegister = new OnConnectingToRegister();
        m_objRunnable.PostRunnableMsg(objOnConnectingToRegister, false);
    }

    protected abstract void RunnableOnConnectingToRegister();

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    class OnTryingToRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnTryingToRegister();
        }
    }

    void PostOnTryingToRegister()
    {
        OnTryingToRegister objOnTryingToRegister = new OnTryingToRegister();

        m_objRunnable.PostRunnableMsg(objOnTryingToRegister, false);
    }

    protected abstract void RunnableOnTryingToRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToRegister implements Runnable
    {
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnFailToRegister(int nStatusCode, String sReasonPhrase)
        {
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnFailToRegister(m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnFailToRegister(int nStatusCode, String sReasonPhrase)
    {
        OnFailToRegister objOnFailToRegister = new OnFailToRegister(nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnFailToRegister, false);
    }

    protected abstract void RunnableOnFailToRegister(int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnSuccessToRegister();
        }
    }

    void PostOnSuccessToRegister()
    {
        OnSuccessToRegister objOnSuccessToRegister = new OnSuccessToRegister();

        m_objRunnable.PostRunnableMsg(objOnSuccessToRegister, false);
    }

    protected abstract void RunnableOnSuccessToRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnConnectingToReRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnConnectingToReRegister();
        }
    }

    void PostOnConnectingToReRegister()
    {
        OnConnectingToReRegister objOnConnectingToReRegister = new OnConnectingToReRegister();

        m_objRunnable.PostRunnableMsg(objOnConnectingToReRegister, false);
    }

    protected abstract void RunnableOnConnectingToReRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToReRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnTryingToReRegister();
        }
    }

    void PostOnTryingToReRegister()
    {
        OnTryingToReRegister objOnTryingToReRegister = new OnTryingToReRegister();

        m_objRunnable.PostRunnableMsg(objOnTryingToReRegister, false);
    }

    protected abstract void RunnableOnTryingToReRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToReRegister implements Runnable
    {
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnFailToReRegister(int nStatusCode, String sReasonPhrase)
        {
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnFailToReRegister(m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnFailToReRegister(int nStatusCode, String sReasonPhrase)
    {
        OnFailToReRegister objOnFailToReRegister = new OnFailToReRegister(nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnFailToReRegister, false);
    }

    protected abstract void RunnableOnFailToReRegister(int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToReRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnSuccessToReRegister();
        }
    }

    void PostOnSuccessToReRegister()
    {
        OnSuccessToReRegister objOnSuccessToReRegister = new OnSuccessToReRegister();

        m_objRunnable.PostRunnableMsg(objOnSuccessToReRegister, false);
    }

    protected abstract void RunnableOnSuccessToReRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToUnRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnTryingToUnRegister();
        }
    }

    void PostOnTryingToUnRegister()
    {
        OnTryingToUnRegister objOnTryingToUnRegister = new OnTryingToUnRegister();

        m_objRunnable.PostRunnableMsg(objOnTryingToUnRegister, false);
    }

    protected abstract void RunnableOnTryingToUnRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToUnRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnFailToUnRegister();
        }
    }

    void PostOnFailToUnRegister()
    {
        OnFailToUnRegister objOnFailToUnRegister = new OnFailToUnRegister();

        m_objRunnable.PostRunnableMsg(objOnFailToUnRegister, false);
    }

    protected abstract void RunnableOnFailToUnRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToUnRegister implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnSuccessToUnRegister();
        }
    }

    void PostOnSuccessToUnRegister()
    {
        OnSuccessToUnRegister objOnSuccessToUnRegister = new OnSuccessToUnRegister();

        m_objRunnable.PostRunnableMsg(objOnSuccessToUnRegister, false);
    }

    protected abstract void RunnableOnSuccessToUnRegister();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnTryingToRegisterREC();
        }
    }

    void PostOnTryingToRegisterREC()
    {
        OnTryingToRegisterREC objOnTryingToRegisterREC = new OnTryingToRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnTryingToRegisterREC, false);
    }

    protected abstract void RunnableOnTryingToRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToRegisterREC implements Runnable
    {
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
        {
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnFailToRegisterREC(m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnFailToRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        OnFailToRegisterREC objOnFailToRegisterREC = new OnFailToRegisterREC(nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnFailToRegisterREC, false);
    }

    protected abstract void RunnableOnFailToRegisterREC(int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnSuccessToRegisterREC();
        }
    }

    void PostOnSuccessToRegisterREC()
    {
        OnSuccessToRegisterREC objOnSuccessToRegisterREC = new OnSuccessToRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnSuccessToRegisterREC, false);
    }

    protected abstract void RunnableOnSuccessToRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToReRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnTryingToReRegisterREC();
        }
    }

    void PostOnTryingToReRegisterREC()
    {
        OnTryingToReRegisterREC objOnTryingToReRegisterREC = new OnTryingToReRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnTryingToReRegisterREC, false);
    }

    protected abstract void RunnableOnTryingToReRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToReRegisterREC implements Runnable
    {
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
        {
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnFailToReRegisterREC(m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnFailToReRegisterREC(int nStatusCode, String sReasonPhrase)
    {
        OnFailToReRegisterREC objOnFailToReRegisterREC = new OnFailToReRegisterREC(nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnFailToReRegisterREC, false);
    }

    protected abstract void RunnableOnFailToReRegisterREC(int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToReRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnSuccessToReRegisterREC();
        }
    }

    void PostOnSuccessToReRegisterREC()
    {
        OnSuccessToReRegisterREC objOnSuccessToReRegisterREC = new OnSuccessToReRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnSuccessToReRegisterREC, false);
    }

    protected abstract void RunnableOnSuccessToReRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToUnRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnTryingToUnRegisterREC();
        }
    }

    void PostOnTryingToUnRegisterREC()
    {
        OnTryingToUnRegisterREC objOnTryingToUnRegisterREC = new OnTryingToUnRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnTryingToUnRegisterREC, false);
    }

    protected abstract void RunnableOnTryingToUnRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToUnRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnFailToUnRegisterREC();
        }
    }

    void PostOnFailToUnRegisterREC()
    {
        OnFailToUnRegisterREC objOnFailToUnRegisterREC = new OnFailToUnRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnFailToUnRegisterREC, false);
    }

    protected abstract void RunnableOnFailToUnRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToUnRegisterREC implements Runnable
    {
        @Override
        public void run()
        {
            RunnableOnSuccessToUnRegisterREC();
        }
    }

    void PostOnSuccessToUnRegisterREC()
    {
        OnSuccessToUnRegisterREC objOnSuccessToUnRegisterREC = new OnSuccessToUnRegisterREC();

        m_objRunnable.PostRunnableMsg(objOnSuccessToUnRegisterREC, false);
    }

    protected abstract void RunnableOnSuccessToUnRegisterREC();

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnDialCallStarted implements Runnable
    {
        int m_nLineNo;
        String m_sCallerName;
        String m_sCallerId;
        String m_sDialNo;

        public OnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
        {
            m_nLineNo = nLineNo;
            m_sCallerName = sCallerName;
            m_sCallerId = sCallerId;
            m_sDialNo = sDialNo;
        }

        @Override
        public void run()
        {
            RunnableOnDialCallStarted(m_nLineNo, m_sCallerName, m_sCallerId, m_sDialNo);
        }
    }

    void PostOnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo)
    {
        OnDialCallStarted objOnDialCallStarted = new OnDialCallStarted(nLineNo, sCallerName, sCallerId, sDialNo);

        m_objRunnable.PostRunnableMsg(objOnDialCallStarted, false);
    }

    protected abstract void RunnableOnDialCallStarted(int nLineNo, String sCallerName, String sCallerId, String sDialNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnDialingCall implements Runnable
    {
        int m_nLineNo;
        int m_nStatusCode;
        String m_sReasonPhrase;


        public OnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
        {
            m_nLineNo = nLineNo;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnDialingCall(m_nLineNo, m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        OnDialingCall objOnDialingCall = new OnDialingCall(nLineNo, nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnDialingCall, false);
    }

    protected abstract void RunnableOnDialingCall(int nLineNo, int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnDialCallFailed implements Runnable
    {
        int m_nLineNo;
        int m_nStatusCode;
        String m_sReasonPhrase;
        String m_sContact;

        public OnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
        {
            m_nLineNo = nLineNo;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
            m_sContact = sContact;
        }

        @Override
        public void run()
        {
            RunnableOnDialCallFailed(m_nLineNo, m_nStatusCode, m_sReasonPhrase, m_sContact);
        }
    }

    void PostOnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact)
    {
        OnDialCallFailed objOnDialCallFailed = new OnDialCallFailed(nLineNo, nStatusCode, sReasonPhrase, sContact);

        m_objRunnable.PostRunnableMsg(objOnDialCallFailed, false);
    }

    protected abstract void RunnableOnDialCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase, String sContact);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnConnectedCall implements Runnable
    {
        int m_nLineNo;
        String m_sToRTPIP;
        int m_nToRTPPort;


        public OnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
        {
            m_nLineNo = nLineNo;
            m_sToRTPIP = sToRTPIP;
            m_nToRTPPort = nToRTPPort;
        }

        @Override
        public void run()
        {
            RunnableOnConnectedCall(m_nLineNo, m_sToRTPIP, m_nToRTPPort);
        }
    }

    void PostOnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort)
    {
        OnConnectedCall objOnConnectedCall = new OnConnectedCall(nLineNo, sToRTPIP, nToRTPPort);

        m_objRunnable.PostRunnableMsg(objOnConnectedCall, false);
    }

    protected abstract void RunnableOnConnectedCall(int nLineNo, String sToRTPIP, int nToRTPPort);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnHungupCall implements Runnable
    {
        int m_nLineNo;

        public OnHungupCall(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnHungupCall(m_nLineNo);
        }
    }

    void PostOnHungupCall(int nLineNo)
    {
        OnHungupCall objOnHungupCall = new OnHungupCall(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnHungupCall, false);
    }

    protected abstract void RunnableOnHungupCall(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnIncomingCallStarted implements Runnable
    {
        String m_sCallId;
        String m_sCallerName;
        String m_sCallerId;
        String m_sDialNo;
        String m_sFromURI;
        String m_sToURI;

        public OnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
        {
            m_sCallId = sCallId;
            m_sCallerName = sCallerName;
            m_sCallerId = sCallerId;
            m_sDialNo = sDialNo;
            m_sFromURI = sFromURI;
            m_sToURI = sToURI ;
        }

        @Override
        public void run()
        {
            RunnableOnIncomingCallStarted(m_sCallId, m_sCallerName, m_sCallerId, m_sDialNo, m_sFromURI, m_sToURI);
        }
    }

    void PostOnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI)
    {
        OnIncomingCallStarted objOnIncomingCallStarted = new OnIncomingCallStarted(sCallId, sCallerName, sCallerId, sDialNo, sFromURI, sToURI);

        m_objRunnable.PostRunnableMsg(objOnIncomingCallStarted, false);
    }

    protected abstract void RunnableOnIncomingCallStarted(String sCallId, String sCallerName, String sCallerId, String sDialNo, String sFromURI, String sToURI);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnIncomingCallEnded implements Runnable
    {
        String m_sCallId;

        public OnIncomingCallEnded(String sCallId)
        {
            m_sCallId = sCallId;
        }

        @Override
        public void run()
        {
            RunnableOnIncomingCallEnded(m_sCallId);
        }
    }

    void PostOnIncomingCallEnded(String sCallId)
    {
        OnIncomingCallEnded objOnIncomingCallEnded = new OnIncomingCallEnded(sCallId);

        m_objRunnable.PostRunnableMsg(objOnIncomingCallEnded, false);
    }

    protected abstract void RunnableOnIncomingCallEnded(String sCallId);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private class OnTransferCallAccepted implements Runnable
    {
        int m_LineNo;

        public OnTransferCallAccepted(int nLineNo)
        {
            m_LineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnTransferCallAccepted(m_LineNo);
        }
    }

    void PostOnTransferCallAccepted(int nLineNo)
    {
        OnTransferCallAccepted objOnTransferCallAccepted = new OnTransferCallAccepted(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnTransferCallAccepted, false);
    }

    protected abstract void RunnableOnTransferCallAccepted(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTransferCallFailed implements Runnable
    {
        int m_nLineNo;
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
        {
            m_nLineNo = nLineNo;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnTransferCallFailed(m_nLineNo, m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        OnTransferCallFailed objOnTransferCallFailed = new OnTransferCallFailed(nLineNo, nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnTransferCallFailed, false);
    }

    protected abstract void RunnableOnTransferCallFailed(int nLineNo, int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnPlayWaveDone implements Runnable
    {
        int m_nLineNo;

        public OnPlayWaveDone(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnPlayWaveDone(m_nLineNo);
        }
    }

    void PostOnPlayWaveDone(int nLineNo)
    {
        OnPlayWaveDone objOnPlayWaveDone = new OnPlayWaveDone(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnPlayWaveDone, false);
    }

    protected abstract void RunnableOnPlayWaveDone(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnDigitDTMF implements Runnable
    {
        int m_nLineNo;
        String m_sDigit;

        public OnDigitDTMF(int nLineNo, String sDigit)
        {
            m_nLineNo = nLineNo;
            m_sDigit = sDigit;
        }

        @Override
        public void run()
        {
            RunnableOnDigitDTMF(m_nLineNo, m_sDigit);
        }
    }

    void PostOnDigitDTMF(int nLineNo, String sDigit)
    {
        OnDigitDTMF objOnDigitDTMF = new OnDigitDTMF(nLineNo, sDigit);

        m_objRunnable.PostRunnableMsg(objOnDigitDTMF, false);
    }

    protected abstract void RunnableOnDigitDTMF(int nLineNo, String sDigit);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnMsgNOTIFY implements Runnable
    {
        String m_sMsg;

        public OnMsgNOTIFY(String sMsg)
        {
            m_sMsg = sMsg;
        }

        @Override
        public void run()
        {
            RunnableOnMsgNOTIFY(m_sMsg);
        }
    }

    void PostOnMsgNOTIFY(String sMsg)
    {
        OnMsgNOTIFY objOnMsgNOTIFY = new OnMsgNOTIFY(sMsg);

        m_objRunnable.PostRunnableMsg(objOnMsgNOTIFY, false);
    }

    protected abstract void RunnableOnMsgNOTIFY(String sMsg);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVoiceMailMsg implements Runnable
    {
        boolean m_bIsMsgWaiting;
        int m_nNewMsgCount;
        int m_nOldMsgCount;
        int m_nNewUrgentMsgCount;
        int m_nOldUrgentMsgCount;
        String m_sMsgAccount;

        public OnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
        {
            m_bIsMsgWaiting = bIsMsgWaiting;
            m_nNewMsgCount = nNewMsgCount;
            m_nOldMsgCount = nOldMsgCount;
            m_nNewUrgentMsgCount = nNewUrgentMsgCount;
            m_nOldUrgentMsgCount = nOldUrgentMsgCount;
            m_sMsgAccount = sMsgAccount;
        }

        @Override
        public void run()
        {
            RunnableOnVoiceMailMsg(m_bIsMsgWaiting, m_nNewMsgCount, m_nOldMsgCount, m_nNewUrgentMsgCount, m_nOldUrgentMsgCount, m_sMsgAccount);
        }
    }

    void PostOnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount)
    {
        OnVoiceMailMsg objOnVoiceMailMsg = new OnVoiceMailMsg(bIsMsgWaiting, nNewMsgCount, nOldMsgCount, nNewUrgentMsgCount, nOldUrgentMsgCount, sMsgAccount);

        m_objRunnable.PostRunnableMsg(objOnVoiceMailMsg, false);
    }

    protected abstract void RunnableOnVoiceMailMsg(boolean bIsMsgWaiting, int nNewMsgCount, int nOldMsgCount, int nNewUrgentMsgCount, int nOldUrgentMsgCount, String sMsgAccount);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnRingToneStarted implements Runnable
    {
        String m_sCallId;

        public OnRingToneStarted(String sCallId)
        {
            m_sCallId = sCallId;
        }

        @Override
        public void run()
        {
            RunnableOnRingToneStarted(m_sCallId);
        }
    }

    void PostOnRingToneStarted(String sCallId)
    {
        OnRingToneStarted objOnRingToneStarted = new OnRingToneStarted(sCallId);

        m_objRunnable.PostRunnableMsg(objOnRingToneStarted, false);
    }

    protected abstract void RunnableOnRingToneStarted(String sCallId);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnRingToneEnded implements Runnable
    {
        String m_sCallId;

        public OnRingToneEnded(String sCallId)
        {
            m_sCallId = sCallId;
        }

        @Override
        public void run()
        {
            RunnableOnRingToneEnded(m_sCallId);
        }
    }

    void PostOnRingToneEnded(String sCallId)
    {
        OnRingToneEnded objOnRingToneEnded = new OnRingToneEnded(sCallId);

        m_objRunnable.PostRunnableMsg(objOnRingToneEnded, false);
    }

    protected abstract void RunnableOnRingToneEnded(String sCallId);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnIncomingDiagnostic implements Runnable
    {
        String m_sMsgSIP;
        String m_sFromIP;
        int m_nFromPort;

        public OnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
        {
            m_sMsgSIP = sMsgSIP;
            m_sFromIP = sFromIP;
            m_nFromPort = nFromPort;
        }

        @Override
        public void run()
        {
            RunnableOnIncomingDiagnostic(m_sMsgSIP, m_sFromIP, m_nFromPort);
        }
    }

    void PostOnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort)
    {
        OnIncomingDiagnostic objOnIncomingDiagnostic = new OnIncomingDiagnostic(sMsgSIP, sFromIP, nFromPort);

        m_objRunnable.PostRunnableMsg(objOnIncomingDiagnostic, false);
    }

    protected abstract void RunnableOnIncomingDiagnostic(String sMsgSIP, String sFromIP, int nFromPort);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnOutgoingDiagnostic implements Runnable
    {
        String m_sMsgSIP;
        String m_sToIP;
        int m_nToPort;

        public OnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
        {
            m_sMsgSIP = sMsgSIP;
            m_sToIP = sToIP;
            m_nToPort = nToPort;
        }

        @Override
        public void run()
        {
            RunnableOnOutgoingDiagnostic(m_sMsgSIP, m_sToIP, m_nToPort);
        }
    }

    void PostOnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort)
    {
        OnOutgoingDiagnostic objOnOutgoingDiagnostic = new OnOutgoingDiagnostic(sMsgSIP, sToIP, nToPort);

        m_objRunnable.PostRunnableMsg(objOnOutgoingDiagnostic, false);
    }

    protected abstract void RunnableOnOutgoingDiagnostic(String sMsgSIP, String sToIP, int nToPort);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnAudioSessionLost implements Runnable
    {
        int m_nLineNo;

        public OnAudioSessionLost(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnAudioSessionLost(m_nLineNo);
        }
    }

    void PostOnAudioSessionLost(int nLineNo)
    {
        OnAudioSessionLost objOnAudioSessionLost = new OnAudioSessionLost(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnAudioSessionLost, false);
    }

    protected abstract void RunnableOnAudioSessionLost(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToHold implements Runnable
    {
        int m_nLineNo;

        public OnSuccessToHold(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnSuccessToHold(m_nLineNo);
        }
    }

    void PostOnSuccessToHold(int nLineNo)
    {
        OnSuccessToHold objOnSuccessToHold = new OnSuccessToHold(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnSuccessToHold, false);
    }

    protected abstract void RunnableOnSuccessToHold(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToHold implements Runnable
    {
        int m_nLineNo;

        public OnTryingToHold(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnTryingToHold(m_nLineNo);
        }
    }

    void PostOnTryingToHold(int nLineNo)
    {
        OnTryingToHold objOnTryingToHold = new OnTryingToHold(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnTryingToHold, false);
    }

    protected abstract void RunnableOnTryingToHold(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToHold implements Runnable
    {
        int m_nLineNo;

        public OnFailToHold(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnFailToHold(m_nLineNo);
        }
    }

    void PostOnFailToHold(int nLineNo)
    {
        OnFailToHold objOnFailToHold = new OnFailToHold(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnFailToHold, false);
    }

    protected abstract void RunnableOnFailToHold(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnSuccessToUnHold implements Runnable
    {
        int m_nLineNo;

        public OnSuccessToUnHold(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnSuccessToUnHold(m_nLineNo);
        }
    }

    void PostOnSuccessToUnHold(int nLineNo)
    {
        OnSuccessToUnHold objOnSuccessToUnHold = new OnSuccessToUnHold(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnSuccessToUnHold, false);
    }

    protected abstract void RunnableOnSuccessToUnHold(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnTryingToUnHold implements Runnable
    {
        int m_nLineNo;

        public OnTryingToUnHold(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnTryingToUnHold(m_nLineNo);
        }
    }

    void PostOnTryingToUnHold(int nLineNo)
    {
        OnTryingToUnHold objOnTryingToUnHold = new OnTryingToUnHold(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnTryingToUnHold, false);
    }

    protected abstract void RunnableOnTryingToUnHold(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnFailToUnHold implements Runnable
    {
        int m_nLineNo;

        public OnFailToUnHold(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnFailToUnHold(m_nLineNo);
        }
    }

    void PostOnFailToUnHold(int nLineNo)
    {
        OnFailToUnHold objOnFailToUnHold = new OnFailToUnHold(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnFailToUnHold, false);
    }

    protected abstract void RunnableOnFailToUnHold(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatContactStatus implements Runnable
    {
        String m_sUserName;
        int m_nStatusId;

        public OnChatContactStatus(String sUserName, int nStatusId)
        {
           m_sUserName = sUserName;
           m_nStatusId = nStatusId;
        }

        @Override
        public void run()
        {
            RunnableOnChatContactStatus(m_sUserName, m_nStatusId);
        }
    }

    void PostOnChatContactStatus(String sUserName, int nStatusId)
    {
        OnChatContactStatus objOnChatContactStatus = new OnChatContactStatus(sUserName, nStatusId);

        m_objRunnable.PostRunnableMsg(objOnChatContactStatus, false);
    }

    protected abstract void RunnableOnChatContactStatus(String sUserName, int nStatusId);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatSendMsgTextSuccess implements Runnable
    {
        String m_sUserName;
        String m_sMsgText;
        int m_nUserValue32bit;

        public OnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
        {
            m_sUserName = sUserName;
            m_sMsgText = sMsgText;
            m_nUserValue32bit = nUserValue32bit;
        }

        @Override
        public void run()
        {
            RunnableOnChatSendMsgTextSuccess(m_sUserName, m_sMsgText, m_nUserValue32bit);
        }
    }

    void PostOnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit)
    {
        OnChatSendMsgTextSuccess objOnChatSendMsgTextSuccess = new OnChatSendMsgTextSuccess(sUserName, sMsgText, nUserValue32bit);

        m_objRunnable.PostRunnableMsg(objOnChatSendMsgTextSuccess, false);
    }

    protected abstract void RunnableOnChatSendMsgTextSuccess(String sUserName, String sMsgText, int nUserValue32bit);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatSendMsgTextFail implements Runnable
    {
        String m_sUserName;
        int m_nStatusCode;
        String m_sReasonPhrase;
        String m_sMsgText;
        int m_nUserValue32bit;

        public OnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
        {
            m_sUserName = sUserName;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
            m_sMsgText = sMsgText;
            m_nUserValue32bit = nUserValue32bit;
        }

        @Override
        public void run()
        {
            RunnableOnChatSendMsgTextFail(m_sUserName, m_nStatusCode, m_sReasonPhrase, m_sMsgText, m_nUserValue32bit);
        }
    }

    void PostOnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit)
    {
        OnChatSendMsgTextFail objOnChatSendMsgTextFail = new OnChatSendMsgTextFail(sUserName, nStatusCode, sReasonPhrase, sMsgText, nUserValue32bit);

        m_objRunnable.PostRunnableMsg(objOnChatSendMsgTextFail, false);
    }

    protected abstract void RunnableOnChatSendMsgTextFail(String sUserName, int nStatusCode, String sReasonPhrase, String sMsgText, int nUserValue32bit);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatSendMsgTypingSuccess implements Runnable
    {
        String m_sUserName;
        int m_UserValue32bit;

        public OnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
        {
            m_sUserName = sUserName;
            m_UserValue32bit = nUserValue32bit;
        }

        @Override
        public void run()
        {
            RunnableOnChatSendMsgTypingSuccess(m_sUserName, m_UserValue32bit);
        }
    }

    void PostOnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit)
    {
        OnChatSendMsgTypingSuccess objOnChatSendMsgTypingSuccess = new OnChatSendMsgTypingSuccess(sUserName, nUserValue32bit);

        m_objRunnable.PostRunnableMsg(objOnChatSendMsgTypingSuccess, false);
    }

    protected abstract void RunnableOnChatSendMsgTypingSuccess(String sUserName, int nUserValue32bit);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatSendMsgTypingFail implements Runnable
    {
        String m_sUserName;
        int m_nStatusCode;
        String m_sReasonPhrase;
        int m_nUserValue32bit;


        public OnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
        {
            m_sUserName = sUserName;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
            m_nUserValue32bit = nUserValue32bit;
        }

        @Override
        public void run()
        {
            RunnableOnChatSendMsgTypingFail(m_sUserName, m_nStatusCode, m_sReasonPhrase, m_nUserValue32bit);
        }
    }

    void PostOnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit)
    {
        OnChatSendMsgTypingFail objOnChatSendMsgTypingFail = new OnChatSendMsgTypingFail(sUserName, nStatusCode, sReasonPhrase, nUserValue32bit);

        m_objRunnable.PostRunnableMsg(objOnChatSendMsgTypingFail, false);
    }

    protected abstract void RunnableOnChatSendMsgTypingFail(String sUserName, int nStatusCode, String sReasonPhrase, int nUserValue32bit);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatRecvMsgText implements Runnable
    {
        String m_sUserName;
        String m_sMsgText;
        boolean m_IsChatContact;

        public OnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
        {
            m_sUserName = sUserName;
            m_sMsgText = sMsgText;
            m_IsChatContact = IsChatContact;
        }

        @Override
        public void run()
        {
            RunnableOnChatRecvMsgText(m_sUserName, m_sMsgText, m_IsChatContact);
        }
    }

    void PostOnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact)
    {
        OnChatRecvMsgText objOnChatRecvMsgText = new OnChatRecvMsgText(sUserName, sMsgText, IsChatContact);

        m_objRunnable.PostRunnableMsg(objOnChatRecvMsgText, false);
    }

    protected abstract void RunnableOnChatRecvMsgText(String sUserName, String sMsgText, boolean IsChatContact);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatRecvMsgTypingStart implements Runnable
    {
        String m_sUserName;

        public OnChatRecvMsgTypingStart(String sUserName)
        {
            m_sUserName = sUserName;
        }

        @Override
        public void run()
        {
            RunnableOnChatRecvMsgTypingStart(m_sUserName);
        }
    }

    void PostOnChatRecvMsgTypingStart(String sUserName)
    {
        OnChatRecvMsgTypingStart objOnChatRecvMsgTypingStart = new OnChatRecvMsgTypingStart(sUserName);

        m_objRunnable.PostRunnableMsg(objOnChatRecvMsgTypingStart, false);
    }

    protected abstract void RunnableOnChatRecvMsgTypingStart(String sUserName);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnChatRecvMsgTypingStop implements Runnable
    {
        String m_sUserName;

        public OnChatRecvMsgTypingStop(String sUserName)
        {
            m_sUserName = sUserName;
        }

        @Override
        public void run()
        {
            RunnableOnChatRecvMsgTypingStop(m_sUserName);
        }
    }

    void PostOnChatRecvMsgTypingStop(String sUserName)
    {
        OnChatRecvMsgTypingStop objOnChatRecvMsgTypingStop = new OnChatRecvMsgTypingStop(sUserName);

        m_objRunnable.PostRunnableMsg(objOnChatRecvMsgTypingStop, false);
    }

    protected abstract void RunnableOnChatRecvMsgTypingStop(String sUserName);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVoiceStreamPCM implements Runnable
    {
        int m_nLineNo;
        byte[] m_pDataPCM;
        int m_nSizePCM;

        public OnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
        {
            m_nLineNo = nLineNo;
            m_pDataPCM = pDataPCM;
            m_nSizePCM = nSizePCM;
        }

        @Override
        public void run()
        {
            RunnableOnVoiceStreamPCM(m_nLineNo, m_pDataPCM, m_nSizePCM);
        }
    }

    void PostOnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM)
    {
        OnVoiceStreamPCM objOnVoiceStreamPCM = new OnVoiceStreamPCM(nLineNo, pDataPCM, nSizePCM);

        m_objRunnable.PostRunnableMsg(objOnVoiceStreamPCM, false);
    }

    protected abstract void RunnableOnVoiceStreamPCM(int nLineNo, byte[] pDataPCM, int nSizePCM);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnDetectedAMD implements Runnable
    {
        int m_nLineNo;
        boolean m_bIsHuman;

        public OnDetectedAMD(int nLineNo, boolean bIsHuman)
        {
            m_nLineNo = nLineNo;
            m_bIsHuman = bIsHuman;
        }

        @Override
        public void run()
        {
            RunnableOnDetectedAMD(m_nLineNo, m_bIsHuman);
        }
    }

    void PostOnDetectedAMD(int nLineNo, boolean bIsHuman)
    {
        OnDetectedAMD objOnDetectedAMD = new OnDetectedAMD(nLineNo, bIsHuman);

        m_objRunnable.PostRunnableMsg(objOnDetectedAMD, false);
    }

    protected abstract void RunnableOnDetectedAMD(int nLineNo, boolean bIsHuman);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnHoldCall implements Runnable
    {
        int m_nLineNo;

        public OnHoldCall(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnHoldCall(m_nLineNo);
        }
    }

    void PostOnHoldCall(int nLineNo)
    {
        OnHoldCall objOnHoldCall = new OnHoldCall(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnHoldCall, false);
    }

    protected abstract void RunnableOnHoldCall(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnUnHoldCall implements Runnable
    {
        int m_nLineNo;

        public OnUnHoldCall(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnUnHoldCall(m_nLineNo);
        }
    }

    void PostOnUnHoldCall(int nLineNo)
    {
        OnUnHoldCall objOnUnHoldCall = new OnUnHoldCall(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnUnHoldCall, false);
    }

    protected abstract void RunnableOnUnHoldCall(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVideoDeviceFrameRGB implements Runnable
    {
        int m_nDeviceId;
        byte[] m_aFrameRGB;
        int m_nFrameSize;
        int m_nFrameWidth;
        int m_nFrameHeight;

        public OnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
        {
            m_nDeviceId = nDeviceId;
            m_aFrameRGB = aFrameRGB;
            m_nFrameSize = nFrameSize;
            m_nFrameWidth = nFrameWidth;
            m_nFrameHeight = nFrameHeight;
        }

        @Override
        public void run()
        {
            RunnableOnVideoDeviceFrameRGB(m_nDeviceId, m_aFrameRGB, m_nFrameSize, m_nFrameWidth, m_nFrameHeight);
        }
    }

    void PostOnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        OnVideoDeviceFrameRGB objOnVideoDeviceFrameRGB = new OnVideoDeviceFrameRGB(nDeviceId, aFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
        m_objRunnable.PostRunnableMsg(objOnVideoDeviceFrameRGB, false);
    }

    protected abstract void RunnableOnVideoDeviceFrameRGB(int nDeviceId, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVideoRemoteFrameRGB implements Runnable
    {
        int m_nLineNo;
        byte[] m_aFrameRGB;
        int m_nFrameSize;
        int m_nFrameWidth;
        int m_nFrameHeight;

        public OnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
        {
            m_nLineNo = nLineNo;
            m_aFrameRGB = aFrameRGB;
            m_nFrameSize = nFrameSize;
            m_nFrameWidth = nFrameWidth;
            m_nFrameHeight = nFrameHeight;
        }

        @Override
        public void run()
        {
            RunnableOnVideoRemoteFrameRGB(m_nLineNo, m_aFrameRGB, m_nFrameSize, m_nFrameWidth, m_nFrameHeight);
        }
    }

    void PostOnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight)
    {
        OnVideoRemoteFrameRGB objOnVideoRemoteFrameRGB = new OnVideoRemoteFrameRGB(nLineNo, aFrameRGB, nFrameSize, nFrameWidth, nFrameHeight);
        m_objRunnable.PostRunnableMsg(objOnVideoRemoteFrameRGB, false);
    }

    protected abstract void RunnableOnVideoRemoteFrameRGB(int nLineNo, byte[] aFrameRGB, int nFrameSize, int nFrameWidth, int nFrameHeight);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVideoRemoteStarted implements Runnable
    {
        int m_nLineNo;

        public OnVideoRemoteStarted(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnVideoRemoteStarted(m_nLineNo);
        }
    }

    void PostOnVideoRemoteStarted(int nLineNo)
    {
        OnVideoRemoteStarted objOnVideoRemoteStarted = new OnVideoRemoteStarted(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnVideoRemoteStarted, false);
    }

    protected abstract void RunnableOnVideoRemoteStarted(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVideoRemoteEnded implements Runnable
    {
        int m_nLineNo;

        public OnVideoRemoteEnded(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnVideoRemoteEnded(m_nLineNo);
        }
    }

    void PostOnVideoRemoteEnded(int nLineNo)
    {
        OnVideoRemoteEnded objOnVideoRemoteEnded = new OnVideoRemoteEnded(nLineNo);
        m_objRunnable.PostRunnableMsg(objOnVideoRemoteEnded, false);
    }

    protected abstract void RunnableOnVideoRemoteEnded(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnServerConnectingREC implements Runnable
    {
        int m_nLineNo;
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
        {
            m_nLineNo = nLineNo;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnServerConnectingREC(m_nLineNo, m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        OnServerConnectingREC objOnServerConnectingREC = new OnServerConnectingREC(nLineNo, nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnServerConnectingREC, false);
    }

    protected abstract void RunnableOnServerConnectingREC(int nLineNo, int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnServerConnectedREC implements Runnable
    {
        int m_nLineNo;
        public OnServerConnectedREC(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnServerConnectedREC(m_nLineNo);
        }
    }

    void PostOnServerConnectedREC(int nLineNo)
    {
        OnServerConnectedREC objOnServerConnectedREC = new OnServerConnectedREC(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnServerConnectedREC, false);
    }

    protected abstract void RunnableOnServerConnectedREC(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnServerFailedREC implements Runnable
    {
        int m_nLineNo;
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
        {
            m_nLineNo = nLineNo;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnServerFailedREC(m_nLineNo, m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostOnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase)
    {
        OnServerFailedREC objOnServerFailedREC = new OnServerFailedREC(nLineNo, nStatusCode, sReasonPhrase);

        m_objRunnable.PostRunnableMsg(objOnServerFailedREC, false);
    }

    protected abstract void RunnableOnServerFailedREC(int nLineNo, int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnServerHungupREC implements Runnable
    {
        int m_nLineNo;

        public OnServerHungupREC(int nLineNo)
        {
            m_nLineNo = nLineNo;
        }

        @Override
        public void run()
        {
            RunnableOnServerHungupREC(m_nLineNo);
        }
    }

    void PostOnServerHungupREC(int nLineNo)
    {
        OnServerHungupREC objOnServerHungupREC = new OnServerHungupREC(nLineNo);

        m_objRunnable.PostRunnableMsg(objOnServerHungupREC, false);
    }

    protected abstract void RunnableOnServerHungupREC(int nLineNo);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private class OnAddCallHistory implements Runnable
    {
        boolean m_bOutboundCallType;
        String m_sCallerName;
        String m_sCallerId;
        String m_sDialNo;
        long m_nStartTime;
        long m_nEndTime;
        long m_nDuration;
        long m_nDayNo;
        int m_nHistoryTypeId;

        public OnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
        {
            m_bOutboundCallType = bOutboundCallType;
            m_sCallerName = sCallerName;
            m_sCallerId = sCallerId;
            m_sDialNo = sDialNo;
            m_nStartTime = nStartTime;
            m_nEndTime = nEndTime;
            m_nDuration = nDuration;
            m_nDayNo = nDayNo;
            m_nHistoryTypeId = nHistoryTypeId;
        }

        @Override
        public void run()
        {
            RunnableOnAddCallHistory(m_bOutboundCallType, m_sCallerName, m_sCallerId, m_sDialNo, m_nStartTime, m_nEndTime, m_nDuration, m_nDayNo, m_nHistoryTypeId);
        }
    }

    void PostOnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId)
    {
        OnAddCallHistory objOnAddCallHistory = new OnAddCallHistory(bOutboundCallType, sCallerName, sCallerId, sDialNo, nStartTime, nEndTime, nDuration, nDayNo, nHistoryTypeId);
        m_objRunnable.PostRunnableMsg(objOnAddCallHistory, false);
    }

    protected abstract void RunnableOnAddCallHistory(boolean bOutboundCallType, String sCallerName, String sCallerId, String sDialNo, long nStartTime, long nEndTime, long nDuration, long nDayNo, int nHistoryTypeId);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    private class OnNetworkReachability implements Runnable
    {
        boolean m_bAvailable;

        public OnNetworkReachability(boolean bAvailable)
        {
            m_bAvailable = bAvailable;
        }

        @Override
        public void run()
        {
            RunnableOnNetworkReachability(m_bAvailable);
        }
    }

    void PostOnNetworkReachability(boolean bAvailable)
    {
        OnNetworkReachability objOnNetworkReachability = new OnNetworkReachability(bAvailable);
        m_objRunnable.PostRunnableMsg(objOnNetworkReachability, false);
    }

    protected abstract void RunnableOnNetworkReachability(boolean bAvailable);

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private class OnAudioDeviceMicVU implements Runnable
    {
        int m_nLevelVU;

        public OnAudioDeviceMicVU(int nLevelVU)
        {
            m_nLevelVU = nLevelVU;
        }

        @Override
        public void run()
        {
            RunnableOnAudioDeviceMicVU(m_nLevelVU);
        }
    }

    void PostOnAudioDeviceMicVU(int nLevelVU)
    {
        OnAudioDeviceMicVU objOnAudioDeviceMicVU = new OnAudioDeviceMicVU(nLevelVU);

        m_objRunnable.PostRunnableMsg(objOnAudioDeviceMicVU, false);
    }

    protected abstract void RunnableOnAudioDeviceMicVU(int nLevelVU);

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private class OnAudioDeviceSpkVU implements Runnable
    {
        int m_nLevelVU;

        public OnAudioDeviceSpkVU(int nLevelVU)
        {
            m_nLevelVU = nLevelVU;
        }

        @Override
        public void run()
        {
            RunnableOnAudioDeviceSpkVU(m_nLevelVU);
        }
    }

    void PostOnAudioDeviceSpkVU(int nLevelVU)
    {
        OnAudioDeviceSpkVU objOnAudioDeviceSpkVU = new OnAudioDeviceSpkVU(nLevelVU);
        m_objRunnable.PostRunnableMsg(objOnAudioDeviceSpkVU, false);
    }

    protected abstract void RunnableOnAudioDeviceSpkVU(int nLevelVU);

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private class OnBusyLampSubscribeSuccess implements Runnable
    {
        String m_sUserName;

        public OnBusyLampSubscribeSuccess(String sUserName)
        {
            m_sUserName = sUserName;
        }

        @Override
        public void run()
        {
            RunnableOnBusyLampSubscribeSuccess(m_sUserName);
        }
    }

    void PostBusyLampSubscribeSuccess(String sUserName)
    {
        OnBusyLampSubscribeSuccess objOnBusyLampSubscribeSuccess = new OnBusyLampSubscribeSuccess(sUserName);
        m_objRunnable.PostRunnableMsg(objOnBusyLampSubscribeSuccess, false);
    }

    protected abstract void RunnableOnBusyLampSubscribeSuccess(String sUserName);

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private class OnBusyLampSubscribeFailed implements Runnable
    {
        String m_sUserName;
        int m_nStatusCode;
        String m_sReasonPhrase;

        public OnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
        {
            m_sUserName = sUserName;
            m_nStatusCode = nStatusCode;
            m_sReasonPhrase = sReasonPhrase;
        }

        @Override
        public void run()
        {
            RunnableOnBusyLampSubscribeFailed(m_sUserName, m_nStatusCode, m_sReasonPhrase);
        }
    }

    void PostBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase)
    {
        OnBusyLampSubscribeFailed objOnBusyLampSubscribeFailed = new OnBusyLampSubscribeFailed(sUserName, nStatusCode, sReasonPhrase);
        m_objRunnable.PostRunnableMsg(objOnBusyLampSubscribeFailed, false);
    }

    protected abstract void RunnableOnBusyLampSubscribeFailed(String sUserName, int nStatusCode, String sReasonPhrase);

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private class OnBusyLampContactStatus implements Runnable
    {
        String m_sUserName;
        int m_nStatusId;

        public OnBusyLampContactStatus(String sUserName, int nStatusId)
        {
            m_sUserName = sUserName;
            m_nStatusId = nStatusId;
        }

        @Override
        public void run()
        {
            RunnableOnBusyLampContactStatus(m_sUserName, m_nStatusId);
        }
    }

    void PostBusyLampContactStatus(String sUserName, int nStatusId)
    {
        OnBusyLampContactStatus objOnBusyLampContactStatus = new OnBusyLampContactStatus(sUserName, nStatusId);
        m_objRunnable.PostRunnableMsg(objOnBusyLampContactStatus, false);
    }

    protected abstract void RunnableOnBusyLampContactStatus(String sUserName, int nStatusCode);

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    private class OnVaxErrorMsg implements Runnable
    {
        String m_sFuncName;
        String m_sErrorMsg;
        int m_nErrorCode;

        public OnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
        {
            m_sFuncName = sFuncName;
            m_sErrorMsg = sErrorMsg;
            m_nErrorCode = nErrorCode;
        }

        @Override
        public void run()
        {
            RunnableOnVaxErrorMsg(m_sFuncName, m_sErrorMsg, m_nErrorCode);
        }
    }

    void PostOnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode)
    {
        OnVaxErrorMsg objOnVaxErrorMsg = new OnVaxErrorMsg(sFuncName, sErrorMsg, nErrorCode);

        m_objRunnable.PostRunnableMsg(objOnVaxErrorMsg, false);
    }

    protected abstract void RunnableOnVaxErrorMsg(String sFuncName, String sErrorMsg, int nErrorCode);

}

