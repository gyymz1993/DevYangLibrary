package vaxsoft.com.vaxphone.MainUtil;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import vaxsoft.com.vaxphone.MainAPP.VaxPhoneAPP;

public class PreferenceUtil
{
    private static SharedPreferences GetPreferences()
    {
        return PreferenceManager.getDefaultSharedPreferences(VaxPhoneAPP.getAppContext());
    }

    private static SharedPreferences.Editor GetPreferencesEditor()
    {
        return GetPreferences().edit();
    }

    public static String ReadPreferenceValue(String sKey, String sDefValue)
    {
        return GetPreferences().getString(sKey, sDefValue);
    }

    public static int ReadPreferenceValue(String sKey, int sDefValue)
    {
        return GetPreferences().getInt(sKey, sDefValue);
    }

    public static boolean ReadPreferenceValue(String sKey, boolean sDefValue)
    {
        return GetPreferences().getBoolean(sKey, sDefValue);
    }

    public static void WritePreferenceValue(String sKey, String prefsValue)
    {
        GetPreferencesEditor().putString(sKey, prefsValue).apply();
    }

    public static void WritePreferenceValue(String sKey, int prefsValue)
    {
        GetPreferencesEditor().putInt(sKey, prefsValue).apply();
    }

    public static void WritePreferenceValue(String sKey, boolean prefsValue)
    {
        GetPreferencesEditor().putBoolean(sKey, prefsValue).apply();
    }

    public static boolean ContainsPreferenceKey(String sKey)
    {
        return GetPreferences().contains(sKey);
    }

    public static void RemovePreferenceValue(String sKey)
    {
        GetPreferencesEditor().remove(sKey).apply();
    }

    public static void ClearAllPrefrenceValues()
    {
        GetPreferencesEditor().clear().commit();
    }
}
