package com.example.alejosaag.udemy_newbies.Presenters;

import com.example.alejosaag.udemy_newbies.Interactors.LoginInteractorImpl;
import com.example.alejosaag.udemy_newbies.Interfaces.ILoginInteractor;
import com.example.alejosaag.udemy_newbies.Interfaces.ILoginPresenter;
import com.example.alejosaag.udemy_newbies.Interfaces.ILoginView;
import com.example.alejosaag.udemy_newbies.Interfaces.OnLoginFinishListener;

/**
 * Created by cliente on 21/11/2017.
 */

public class LoginPresenterImpl implements ILoginPresenter, OnLoginFinishListener
{
    private ILoginView view;
    private ILoginInteractor interactor;
    
    public LoginPresenterImpl(ILoginView view)
    {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }
    
    @Override
    public void validarUser(String user, String pass)
    {
        if (view != null)
        {
            view.showProgress();
            interactor.validateUser(user, pass, this);
        }
    }
    
    @Override
    public void usernameError()
    {
        if (view != null)
        {
            view.hideProgress();
            view.setErrorUser();
        }
    }
    
    @Override
    public void passwordError()
    {
        if (view != null)
        {
            view.hideProgress();
            view.setErrorPassword();
        }
    }
    
    @Override
    public void successOperation()
    {
        if (view != null)
        {
            view.hideProgress();
            view.navigateToHome();
        }
    }
}
