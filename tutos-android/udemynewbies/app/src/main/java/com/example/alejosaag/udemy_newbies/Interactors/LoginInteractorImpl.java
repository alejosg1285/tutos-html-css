package com.example.alejosaag.udemy_newbies.Interactors;

import android.os.Handler;

import com.example.alejosaag.udemy_newbies.Interfaces.ILoginInteractor;
import com.example.alejosaag.udemy_newbies.Interfaces.OnLoginFinishListener;

/**
 * Created by cliente on 21/11/2017.
 */

public class LoginInteractorImpl implements ILoginInteractor
{
    @Override
    public void validateUser(final String username, final String password, final OnLoginFinishListener listener)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (!username.equals("") && !password.equals(""))
                {
                    listener.successOperation();
                }
                else
                {
                    if (username.equals(""))
                    {
                        listener.usernameError();
                    }
                    if (password.equals(""))
                    {
                        listener.passwordError();
                    }
                }
            }
        }, 2000);
    }
}
