package com.example.cliente.udemyandroidcero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cliente.udemyandroidcero.Adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private List<String> names;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        names = getAllNames();

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new RecyclerAdapter(names, R.layout.recycler_view_item, new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClik(String name, int position) {
                Toast.makeText(RecyclerViewActivity.this, "Nombre: "+name, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getAllNames() {
        return new ArrayList<String>() {{
            add("Luna");
            add("Isma");
            add("Isis");
            add("Onix");
            add("Orion");
            add("Perla");
        }};
    }
}
