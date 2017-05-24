package com.example.alejosg.labandroidudemy.Util;

import android.content.SharedPreferences;

/**
 * Created by Alejo Saa G on 23/05/2017.
 */

public class Util
{
    public static String getUserMailPrefs(SharedPreferences preferences)
    {
        return preferences.getString("email", "");
    }

    public static String getUserPassPrefs(SharedPreferences preferences)
    {
        return preferences.getString("password", "");
    }

    public static void removeSharedPreferences(SharedPreferences preferences)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }
}
