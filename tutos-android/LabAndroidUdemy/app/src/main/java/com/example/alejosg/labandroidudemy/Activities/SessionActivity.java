package com.example.alejosg.labandroidudemy.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alejosg.labandroidudemy.R;
import com.example.alejosg.labandroidudemy.Util.Util;

public class SessionActivity extends AppCompatActivity
{
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_logout:
                logOut();
                return true;
            case R.id.menu_forget_logout:
                Util.removeSharedPreferences(prefs);
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
