package com.example.alejosaag.udemy_newbies;

import android.widget.ImageView;

/**
 * Created by cliente on 17/11/2017.
 */

public class TrafficLightPresenter implements ITrafficLight.Presenter
{
    private ITrafficLight.View view;
    private ITrafficLight.Model model;
    
    public TrafficLightPresenter(ITrafficLight.View view)
    {
        this.view = view;
        this.model = new TrafficLIghtModel(this);
    }
    
    @Override
    public void turnOffLightsPresenter()
    {
        if(this.view!=null) {
            view.turnOffLights();
        }
    }
    
    @Override
    public void turnOnLightPresenter(ImageView imageView, int resource)
    {
        if(this.view!=null) {
            view.turnOnLight(imageView, resource);
        }
    }
    
    @Override
    public void turnOnLight(ImageView imageView, int resource)
    {
        if(view!=null) {
            model.turnOnLightModel(imageView, resource);
        }
    }
}
