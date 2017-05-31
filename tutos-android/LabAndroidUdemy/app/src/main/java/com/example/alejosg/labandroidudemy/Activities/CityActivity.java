package com.example.alejosg.labandroidudemy.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.alejosg.labandroidudemy.Adapters.CityAdapter;
import com.example.alejosg.labandroidudemy.Models.City;
import com.example.alejosg.labandroidudemy.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class CityActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<City>>
{
    private Realm realm;
    private FloatingActionButton fab;

    private RealmResults<City> cities;

    private RecyclerView recycler;
    private CityAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        //Activar flecha de retorno a la actividad principal
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();
        cities = realm.where(City.class).findAll();
        cities.addChangeListener(this);

        recycler = (RecyclerView) findViewById(R.id.cityRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        fab = (FloatingActionButton) findViewById(R.id.FabAddCity);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CityActivity.this, AddEditCityActivity.class);
                startActivity(intent);
            }
        });

        setHideShowFab();

        adapter = new CityAdapter(cities, R.layout.recycler_view_city_item, new CityAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(City city, int position)
            {
                Intent intent = new Intent(CityActivity.this, AddEditCityActivity.class);
                intent.putExtra("idCity", city.getId());
                startActivity(intent);
            }
        }, new CityAdapter.OnButtonClickListener()
        {
            @Override
            public void onButtonClick(City city, int position)
            {
                showAlertForRemovingCity("Delete city", "Are you sure you want to delete " + city.getName() + "?", position);
            }
        });

        recycler.setAdapter(adapter);
        cities.addChangeListener(this);
    }

    private void setHideShowFab()
    {
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0)
                {
                    fab.hide();
                } else if(dy < 0)
                {
                    fab.show();
                }
            }
        });
    }

    private void showAlertForRemovingCity(String title, String message, final int position)
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Remove", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        deleteCity(position);
                        Toast.makeText(CityActivity.this, "It has been deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }

    private void deleteCity(int position)
    {
        realm.beginTransaction();
        cities.remove(position);
        realm.commitTransaction();
    }

    @Override
    public void onChange(RealmResults<City> cities)
    {
        adapter.notifyDataSetChanged();
    }
}
