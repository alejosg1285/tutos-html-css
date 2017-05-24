package com.example.alejosg.labandroidudemy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alejosg.labandroidudemy.Activities.CityActivity;
import com.example.alejosg.labandroidudemy.Activities.FruitWorld;
import com.example.alejosg.labandroidudemy.Activities.LoginActivity;

public class MainActivity extends AppCompatActivity
{
    //private SharedPreferences prefs;

    private Button btnFruit;
    private Button btnRealm;
    private Button btnPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mostrar icono en ActionBar.
        inforceIconBar();

        //prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        btnFruit = (Button) findViewById(R.id.labFruit);
        btnFruit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, FruitWorld.class);
                startActivity(intent);
            }
        });
        btnRealm = (Button) findViewById(R.id.labCity);
        btnRealm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                startActivity(intent);
            }
        });
        btnPrefs=(Button)findViewById(R.id.labPrefs);
        btnPrefs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //Forzar visualizar icono en ActionBar.
    private void inforceIconBar()
    {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
