package com.example.alejosaag.udemy_newbies.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alejosaag.udemy_newbies.ITrafficLight;
import com.example.alejosaag.udemy_newbies.Interfaces.ILoginPresenter;
import com.example.alejosaag.udemy_newbies.Interfaces.ILoginView;
import com.example.alejosaag.udemy_newbies.Presenters.LoginPresenterImpl;
import com.example.alejosaag.udemy_newbies.R;

public class Login extends AppCompatActivity implements ILoginView
{
    private EditText username, password;
    private ProgressBar progressBar;
    
    private ILoginPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        
        presenter = new LoginPresenterImpl(this);
    }
    
    @Override
    public void showProgress()
    {
        progressBar.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void hideProgress()
    {
        progressBar.setVisibility(View.GONE);
    }
    
    @Override
    public void setErrorUser()
    {
        username.setText("Campo es obligatorio");
    }
    
    @Override
    public void setErrorPassword()
    {
        password.setText("Campo es obligatorio");
    }
    
    @Override
    public void navigateToHome()
    {
        Toast.makeText(this, "Se paso la validacion", Toast.LENGTH_SHORT).show();
    }
    
    public void validacion(View view)
    {
        presenter.validarUser(username.getText().toString(), password.getText().toString());
    }
}
