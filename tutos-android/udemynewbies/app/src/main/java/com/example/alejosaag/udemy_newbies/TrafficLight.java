package com.example.alejosaag.udemy_newbies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrafficLight extends AppCompatActivity implements View.OnClickListener, ITrafficLight.View
{
    @BindView(R.id.lightRed)
    ImageView imgRed;
    @BindView(R.id.lightYellow)
    ImageView imgYellow;
    @BindView(R.id.lightGreen)
    ImageView imgGreen;
    
    private ITrafficLight.Presenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        ButterKnife.bind(this);
        
        this.presenter = new TrafficLightPresenter(this);
        
        presenter.turnOffLightsPresenter();
    }
    
    @Override
    public void turnOffLights()
    {
        imgRed.setImageResource(R.mipmap.light_off_round);
        imgYellow.setImageResource(R.mipmap.light_off_round);
        imgGreen.setImageResource(R.mipmap.light_off_round);
    }
    
    @Override
    public void turnOnLight(ImageView imageView, int resource)
    {
        imageView.setImageResource(resource);
    }
    
    @Override
    @OnClick({R.id.btnRed, R.id.btnYellow, R.id.btnGreen})
    public void onClick(View view)
    {
        presenter.turnOffLightsPresenter();
        switch (view.getId())
        {
            case R.id.btnRed:
                //imgRed.setImageResource(R.mipmap.light_red_round);
                presenter.turnOnLight(imgRed, R.mipmap.light_red_round);
                break;
            case R.id.btnYellow:
                //imgYellow.setImageResource(R.mipmap.light_yellow_round);
                presenter.turnOnLight(imgYellow, R.mipmap.light_yellow_round);
                break;
            case R.id.btnGreen:
                //imgGreen.setImageResource(R.mipmap.light_green_round);
                presenter.turnOnLight(imgGreen, R.mipmap.light_green_round);
                break;
            default:
                break;
        }
    }
}
