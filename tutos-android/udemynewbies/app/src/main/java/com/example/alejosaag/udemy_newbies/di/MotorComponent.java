package com.example.alejosaag.udemy_newbies.di;

import com.example.alejosaag.udemy_newbies.DaggerActivity;
import com.example.alejosaag.udemy_newbies.MainActivity;

import dagger.Component;

/**
 * Created by cliente on 20/11/2017.
 */

@Component(modules = MotorModule.class)
public interface MotorComponent
{
    void inject(DaggerActivity daggerActivity);
}
