package com.utils.gyymz.statusbar;

import android.os.Build;

/**
 */
public class StatusBarExclude {
    public static boolean exclude = false;

    public static void excludeIncompatibleFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar");
        } catch (NoSuchMethodException e) {
            exclude |= Build.BRAND.contains("Meizu");
        }
    }
}
