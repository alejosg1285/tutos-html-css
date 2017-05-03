package com.example.alejosg.labandroidudemy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alejosg.labandroidudemy.Activities.FruitWorld;

public class MainActivity extends AppCompatActivity
{
    private Button btnFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mostrar icono en ActionBar.
        inforceIconBar();

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
    }

    //Forzar visualizar icono en ActionBar.
    private void inforceIconBar()
    {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
