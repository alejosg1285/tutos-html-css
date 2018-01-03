package com.example.alejosaag.udemy_newbies;

import android.widget.ImageView;

/**
 * Created by cliente on 17/11/2017.
 */

public class TrafficLIghtModel implements ITrafficLight.Model
{
    private ITrafficLight.Presenter presenter;
    
    public TrafficLIghtModel(ITrafficLight.Presenter presenter)
    {
        this.presenter = presenter;
    }
    
    @Override
    public void turnOnLightModel(ImageView imageView, int resource)
    {
        //Se haria cualquier operacion y se retorna al presentador.
        presenter.turnOnLightPresenter(imageView, resource);
    }
}
