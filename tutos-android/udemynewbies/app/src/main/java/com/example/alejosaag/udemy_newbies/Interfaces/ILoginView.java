package com.example.alejosaag.udemy_newbies.Interfaces;

/**
 * Created by cliente on 21/11/2017.
 */

public interface ILoginView
{
    void showProgress();
    
    void hideProgress();
    
    void setErrorUser();
    
    void setErrorPassword();
    
    void navigateToHome();
}
