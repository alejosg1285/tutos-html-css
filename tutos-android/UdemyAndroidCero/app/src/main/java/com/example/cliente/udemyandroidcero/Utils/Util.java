package com.example.cliente.udemyandroidcero.Utils;

import android.content.SharedPreferences;

/**
 * Created by Alejo Saa G on 29/05/2017.
 */

public class Util
{
    public static String getUserMailPrefs(SharedPreferences prefs)
    {
        return prefs.getString("email", "");
    }

    public static String getUserPassPrefs(SharedPreferences prefs)
    {
        return prefs.getString("pass", "");
    }
}
