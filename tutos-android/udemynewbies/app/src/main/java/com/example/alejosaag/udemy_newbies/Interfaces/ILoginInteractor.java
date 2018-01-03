package com.example.alejosaag.udemy_newbies.Interfaces;

/**
 * Created by cliente on 21/11/2017.
 */

public interface ILoginInteractor
{
    void validateUser(String username, String password, OnLoginFinishListener listener);
}
