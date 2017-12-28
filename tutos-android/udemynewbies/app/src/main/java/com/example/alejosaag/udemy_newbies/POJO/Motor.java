package com.example.alejosaag.udemy_newbies.POJO;

/**
 * Created by cliente on 20/11/2017.
 */

public class Motor
{
    private String tipoMotor;
    
    public Motor(String tipoMotor)
    {
        this.tipoMotor = tipoMotor;
    }
    
    public String getTipoMotor()
    {
        return ("Motor con: "+tipoMotor);
    }
}
