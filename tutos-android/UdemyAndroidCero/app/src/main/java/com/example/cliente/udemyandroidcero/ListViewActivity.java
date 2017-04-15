package com.example.cliente.udemyandroidcero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cliente.udemyandroidcero.Adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private GridView gridView;
    private List<String> nombre;
    private int counter;

    private MyAdapter myAdapter;
    private MyAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        //Mostrar icono en ActionBar.
        inforceIconBar();

        listView = (ListView) findViewById(R.id.lstOpciones);
        gridView = (GridView) findViewById(R.id.gridView);
        counter = 0;

        nombre = new ArrayList<String>() {{
            add("Luna");
            add("Izma");
            add("Isis");
            add("Onix");
            add("Orion");
            add("Perla");
        }};

        //Evento y adaptador para el ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "Gatito: " + nombre.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        myAdapter = new MyAdapter(this, R.layout.list_item, nombre);
        listView.setAdapter(myAdapter);

        //Evento y adaptador para el GridView.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "Gatito: " + nombre.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        gridAdapter = new MyAdapter(this, R.layout.grid_item, nombre);
        gridView.setAdapter(gridAdapter);

        registerForContextMenu(gridView);
    }

    //Forzar visualizar icono en ActionBar.
    private void inforceIconBar()
    {
        getSupportActionBar().setIcon(R.mipmap.ic_checklist);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //Crear menu lateral de la actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Captura el elemento seleccionado en el menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc1:
                Intent opc1 = new Intent(ListViewActivity.this, MainActivity.class);
                startActivity(opc1);
                return true;
            //break;
            case R.id.opc2:
                Intent opc2 = new Intent(ListViewActivity.this, ListViewActivity.class);
                startActivity(opc2);
                return true;
            case R.id.add_item:
                nombre.add("Item " + (++counter));
                //Notificar que se ha modificado el arreglo de datos.
                myAdapter.notifyDataSetChanged();
                gridAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Crear menu contextual.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Captura el elemento pulsado.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(nombre.get(info.position));
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    //Captura evento pulsado en el menu contextual.
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Captura el elemento pulsado.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_item:
                //Se elimina el item seleccionado.
                nombre.remove(info.position);
                //Notificar que se ha modificado el arreglo de datos.
                myAdapter.notifyDataSetChanged();
                gridAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
