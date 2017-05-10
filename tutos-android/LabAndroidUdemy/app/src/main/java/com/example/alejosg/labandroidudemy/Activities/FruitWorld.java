package com.example.alejosg.labandroidudemy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnList;
    private Button btnGrid;
    private ImageButton btnImage;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_world);

        //Activar flecha de retorno a la actividad principal
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        frutas = getAllFrutas();

        //Instancia la vista del recycler view y asigna el layout
        recyclerView = (RecyclerView) findViewById(R.id.recyclerFruit);
        layoutManager = new LinearLayoutManager(this);
        //Carga los datos en la vista por medio del adaptador
        adapter = new FrutaAdapter(frutas, R.layout.cardview_fruit_item, new FrutaAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Fruta fruta, int position)
            {
                Toast.makeText(FruitWorld.this, "ffddd", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //Instancia los elementos de boton de la interfaz.
        btnList = (Button) findViewById(R.id.btnListV);
        btnGrid = (Button) findViewById(R.id.btnGridV);
        btnImage = (ImageButton) findViewById(R.id.imgButton);
        //Define metodo a ejecutar al pulsar cada boton.
        btnList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                layoutManager = new LinearLayoutManager(FruitWorld.this);
                recyclerView.setLayoutManager(layoutManager);

                btnGrid.setVisibility(View.VISIBLE);
                btnList.setVisibility(View.GONE);
            }
        });
        btnGrid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                layoutManager = new GridLayoutManager(FruitWorld.this, 2);
                recyclerView.setLayoutManager(layoutManager);

                btnList.setVisibility(View.VISIBLE);
                btnGrid.setVisibility(View.GONE);
            }
        });
        btnImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int pos = frutas.size();
                frutas.add(pos, new Fruta("Fruta X", "Ni idea", R.drawable.frutas_272138));
                adapter.notifyItemInserted(pos);
                layoutManager.scrollToPosition(pos);
            }
        });

        registerForContextMenu(recyclerView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(frutas.get(info.position).getNombre());
        getMenuInflater().inflate(R.menu.delete_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        return super.onContextItemSelected(item);
    }

    private List<Fruta> getAllFrutas()
    {
        return new ArrayList<Fruta>()
        {{
            add(new Fruta("Sandia", "Tropical", R.drawable.watermelon_1624324_640));
            add(new Fruta("Piña", "Tropical", R.drawable.pineapple_300038_640));
        }};
    }
}
