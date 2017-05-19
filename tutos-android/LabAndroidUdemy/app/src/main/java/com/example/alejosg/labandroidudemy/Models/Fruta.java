package com.example.alejosg.labandroidudemy.Models;

/**
 * Created by Lenovo on 19/04/2017.
 */

public class Fruta
{
    private String nombre;
    private String descripcion;
    private int imgIcono;
    private int imgBackgroud;

    public Fruta()
    {
    }

    public Fruta(String nombre, String descripcion, int imgIcono, int imgBackgroud)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgIcono = imgIcono;
        this.imgBackgroud = imgBackgroud;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public int getImgIcono()
    {
        return imgIcono;
    }

    public void setImgIcono(int imgIcono)
    {
        this.imgIcono = imgIcono;
    }

    public int getImgBackgroud()
    {
        return imgBackgroud;
    }

    public void setImgBackgroud(int imgBackgroud)
    {
        this.imgBackgroud = imgBackgroud;
    }
}
