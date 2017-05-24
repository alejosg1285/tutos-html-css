package com.example.alejosg.labandroidudemy.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.alejosg.labandroidudemy.Activities.LoginActivity;
import com.example.alejosg.labandroidudemy.MainActivity;
import com.example.alejosg.labandroidudemy.R;
import com.example.alejosg.labandroidudemy.Util.Util;

public class SplashScreenActivity extends AppCompatActivity
{
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);

        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if(!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) && !TextUtils.isEmpty(Util.getUserPassPrefs(prefs)))
        {
            startActivity(intentMain);
        } else
        {
            startActivity(intentLogin);
        }

        finish();
    }
}
