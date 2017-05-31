package com.example.cliente.udemyandroidcero.Activities;

import android.os.Bundle;

import com.example.cliente.udemyandroidcero.Fragments.DetailsFragment;
import com.example.cliente.udemyandroidcero.R;

public class DetailsActivity extends FragmentActivity
{
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getIntent().getExtras() != null)
        {
            text = getIntent().getStringExtra("text");
        }

        //Obtiene la instancia del fragment del layout de la actividad.
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        //Se invoca el método que renderiza el texto ingresado en el primer fragment.
        detailsFragment.renderText(text);
    }
}
