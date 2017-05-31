package com.example.alejosg.labandroidudemy.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.alejosg.labandroidudemy.R;
import com.example.alejosg.labandroidudemy.Util.Util;

public class LoginActivity extends AppCompatActivity
{
    private SharedPreferences prefs;

    private EditText editEmail;
    private EditText editPassword;
    private Switch switchRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if(login(email, password))
                {
                    goToMain();
                    saveOnPreferences(email, password);
                }
            }
        });
    }

    private void goToMain()
    {
        Intent intent = new Intent(this, SessionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void bindUI()
    {
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void setCredentialsIfExist()
    {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            editEmail.setText(email);
            editPassword.setText(password);
            switchRemember.setChecked(true);
        }
    }

    private boolean login(String email, String password)
    {
        if(!isValidEmail(email))
        {
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!isValidPassword(password))
        {
            Toast.makeText(this, "Password not valid, 4 characters or more, please try again", Toast.LENGTH_SHORT).show();
            return false;
        } else
        {
            return true;
        }
    }

    private boolean isValidPassword(String password)
    {
        return password.length() >= 4;
    }

    private boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void saveOnPreferences(String email, String password)
    {
        if(switchRemember.isChecked())
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();
        }
    }
}
