package com.example.alejosaag.udemy_newbies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.alejosaag.udemy_newbies.POJO.Coche;
import com.example.alejosaag.udemy_newbies.POJO.Motor;
import com.example.alejosaag.udemy_newbies.di.MotorApplication;

import javax.inject.Inject;
import javax.inject.Named;

public class DaggerActivity extends AppCompatActivity
{
    @Named("gasolina")
    @Inject
    Motor motor;
    @Inject
    Coche coche;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        
        ((MotorApplication)getApplication()).getMotorComponent().inject(this);
        Toast.makeText(DaggerActivity.this, motor.getTipoMotor(), Toast.LENGTH_SHORT).show();
        
        Toast.makeText(DaggerActivity.this, coche.getMotor(), Toast.LENGTH_SHORT).show();
    }
}
