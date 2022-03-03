package com.tenacious.showcurrentactivity;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by sumit on 03/03/2022.
 */
public class AccessibilityUtil {

    public static boolean checkAccessibility(Context context) {
        if (!AccessibilityUtil.isAccessibilitySettingsOn(context)) {
            context.startActivity(
                    new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            );
            return false;
        }
        return true;
    }

    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }

        return false;
    }
}
