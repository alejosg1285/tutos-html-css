package com.example.alejosaag.udemy_newbies.di;

import android.app.Application;

/**
 * Created by cliente on 20/11/2017.
 */

public class MotorApplication extends Application
{
    private MotorComponent motorComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        
        motorComponent = DaggerMotorComponent.builder().motorModule(new MotorModule()).build();
    }
    
    public MotorComponent getMotorComponent()
    {
        return motorComponent;
    }
}
