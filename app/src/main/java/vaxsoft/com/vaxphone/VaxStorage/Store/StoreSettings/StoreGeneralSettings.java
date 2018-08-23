package vaxsoft.com.vaxphone.VaxStorage.Store.StoreSettings;


import vaxsoft.com.vaxphone.MainUtil.PreferenceUtil;

public class StoreGeneralSettings
{
    private String MULTI_TASK_MODE = "VaxMultiTaskMode";
    private String DIAGNOSTIC_LOG = "VaxDiagnosticLog";

    private boolean MULTI_TASK_MODE_DEFAULT_VALUE = false;
    private boolean DIAGNOSTIC_LOG_DEFAULT_VALUE = false;

    /////////////////////////////////////////////////////////////////////////////////////////

    public boolean GetMultiTaskMode()
    {
        return PreferenceUtil.ReadPreferenceValue(MULTI_TASK_MODE, MULTI_TASK_MODE_DEFAULT_VALUE);
    }

    public boolean GetDiagnosticLog()
    {
        return PreferenceUtil.ReadPreferenceValue(DIAGNOSTIC_LOG, DIAGNOSTIC_LOG_DEFAULT_VALUE);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void SetMultiTaskMode(boolean bMultiTaskMode)
    {
        PreferenceUtil.WritePreferenceValue(MULTI_TASK_MODE, bMultiTaskMode);
    }

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
