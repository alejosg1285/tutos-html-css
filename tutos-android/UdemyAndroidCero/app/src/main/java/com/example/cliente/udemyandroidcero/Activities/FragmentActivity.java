package com.example.cliente.udemyandroidcero.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.cliente.udemyandroidcero.Fragments.DataFragment;
import com.example.cliente.udemyandroidcero.Fragments.DetailsFragment;
import com.example.cliente.udemyandroidcero.R;

public class FragmentActivity extends android.support.v4.app.FragmentActivity implements DataFragment.DataListener
{
    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        setMultiPanel();
    }

    @Override
    public void sendData(String text)
    {
        if(isMultiPanel)
        {
            //Obtiene la instancia del fragment del layout de la actividad.
            DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
            //Se invoca el método que renderiza el texto ingresado en el primer fragment.
            detailsFragment.renderText(text);
        } else
        {
            Intent intent = new Intent(FragmentActivity.this, DetailsActivity.class);
            intent.putExtra("text", text);
            startActivity(intent);
        }
    }

    private void setMultiPanel()
    {
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detailsFragment) != null);
    }
}
