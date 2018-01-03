package com.example.alejosaag.udemy_newbies.POJO;

/**
 * Created by cliente on 20/11/2017.
 */

public class Coche
{
    private Motor motor;
    
    public Coche(Motor motor)
    {
        this.motor = motor;
    }
    
    public String getMotor()
    {
        return ("Coche con "+ motor.getTipoMotor());
    }
}
