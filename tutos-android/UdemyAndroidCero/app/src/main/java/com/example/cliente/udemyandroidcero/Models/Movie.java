package com.example.cliente.udemyandroidcero.Models;

/**
 * Created by Alejo Saa G on 16/04/2017.
 */

public class Movie
{
    String title;
    int poster;

    public Movie()
    {
    }

    public Movie(String title, int poster)
    {
        this.title = title;
        this.poster = poster;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPoster()
    {
        return poster;
    }

    public void setPoster(int poster)
    {
        this.poster = poster;
    }
}
