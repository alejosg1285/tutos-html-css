package com.example.alejosg.labandroidudemy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alejosg.labandroidudemy.Adapters.FrutaAdapter;
import com.example.alejosg.labandroidudemy.Models.Fruta;
import com.example.alejosg.labandroidudemy.R;

import java.util.ArrayList;
import java.util.List;

public class FruitWorld extends AppCompatActivity
{
    private List<Fruta> frutas;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FrutaAdapter adapter;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_world);

        //Activar flecha de retorno a la actividad principal
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        frutas = getAllFrutas();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerFruit);
        layoutManager = new LinearLayoutManager(this);

        adapter = new FrutaAdapter(frutas, R.layout.cardview_fruit_item, this, new FrutaAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Fruta fruta, int position)
            {
                adapter.notifyItemChanged(position);
                Toast.makeText(FruitWorld.this, "ffddd", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.add_item:
                int position = frutas.size();
                frutas.add(position, new Fruta("Frutas", "Frutas " + (++counter), R.drawable.fruit_clip, R.mipmap.ic_frutas));
                adapter.notifyItemInserted(position);
                layoutManager.scrollToPosition(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Fruta> getAllFrutas()
    {
        return new ArrayList<Fruta>()
        {{
            add(new Fruta("Sandia", "Tropical", R.drawable.watermelon_1624324_640, R.mipmap.ic_pineapple));
            add(new Fruta("Piña", "Tropical", R.drawable.pineapple_300038_640, R.mipmap.ic_watermelon));
        }};
    }
}
