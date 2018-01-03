package com.example.alejosaag.udemy_newbies;

import android.widget.ImageView;

/**
 * Created by cliente on 17/11/2017.
 */

public interface ITrafficLight
{
    interface View
    {
        void turnOffLights();
        
        void turnOnLight(ImageView imageView, int resource);
    }
    
    interface Presenter
    {
        void turnOffLightsPresenter();
        
        void turnOnLightPresenter(ImageView imageView, int resource);
        void turnOnLight(ImageView imageView, int resource);
    }
    
    interface Model
    {
        void turnOnLightModel(ImageView imageView, int resource);
    }
}
