package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;


import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreGeneralSettings
{
    private String DIAGNOSTIC_LOG = "VaxDiagnosticLog";
    private boolean DIAGNOSTIC_LOG_DEFAULT_VALUE = false;

    /////////////////////////////////////////////////////////////////////////////////////////

    public boolean GetDiagnosticLog()
    {
        return PreferenceUtil.ReadPreferenceValue(DIAGNOSTIC_LOG, DIAGNOSTIC_LOG_DEFAULT_VALUE);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void SetDiagnosticLog(boolean bDiagnosticLog)
    {
        PreferenceUtil.WritePreferenceValue(DIAGNOSTIC_LOG, bDiagnosticLog);
    }

    //////////////////////////////////////////////////////////////////////////////////////

    public void ResetAllSettings()
    {
        PreferenceUtil.ClearAllPrefrenceValues();
    }
}
