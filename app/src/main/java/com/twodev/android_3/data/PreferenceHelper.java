package com.twodev.android_3.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private static volatile PreferenceHelper instance;
    private SharedPreferences preferences;
    private String NAME = "setting";
    private String ISSHOWN = "isShown";


    public PreferenceHelper(Context context) {
        instance = this;
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) new PreferenceHelper(context);
        return instance;
    }

    public boolean isShown() {
        return preferences.getBoolean(ISSHOWN, false);
    }

    public void setIsShow() {
        preferences.edit().putBoolean(ISSHOWN, true).apply();
    }

}
