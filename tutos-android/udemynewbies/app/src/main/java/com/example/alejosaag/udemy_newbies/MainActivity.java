package com.example.alejosaag.udemy_newbies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alejosaag.udemy_newbies.Views.Login;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view)
    {
        Intent intent;
        switch (view.getId())
        {
            case R.id.btn1:
                intent = new Intent(MainActivity.this, TrafficLight.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, DaggerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent=new Intent(MainActivity.this, GridviewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
