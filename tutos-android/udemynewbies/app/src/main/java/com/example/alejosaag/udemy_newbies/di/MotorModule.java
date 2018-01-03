package com.example.alejosaag.udemy_newbies.di;

import com.example.alejosaag.udemy_newbies.POJO.Coche;
import com.example.alejosaag.udemy_newbies.POJO.Motor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cliente on 20/11/2017.
 */

@Module
public class MotorModule
{
    @Named("diesel")
    @Provides
    public Motor providesMotorDiesel()
    {
        return new Motor("Diesel");
    }
    
    @Named("gasolina")
    @Provides
    Motor providesMotorGasolina()
    {
        return new Motor("Gasolina");
    }
    
    @Provides
    Coche providesCoche(@Named("diesel") Motor motor)
    {
        return new Coche(motor);
    }
}
