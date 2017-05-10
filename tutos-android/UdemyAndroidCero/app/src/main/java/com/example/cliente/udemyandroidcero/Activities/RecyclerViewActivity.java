package com.example.cliente.udemyandroidcero.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cliente.udemyandroidcero.Adapters.RecyclerAdapter;
import com.example.cliente.udemyandroidcero.Models.Movie;
import com.example.cliente.udemyandroidcero.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity
{
    private List<Movie> movies;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        movies = getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new RecyclerAdapter(movies, R.layout.recycler_view_item, new RecyclerAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClik(Movie movie, int position)
            {
                //Toast.makeText(RecyclerViewActivity.this, "Nombre: " + name, Toast.LENGTH_SHORT).show();
                removeMovie(position);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        TextView titleCard=(TextView)findViewById(R.id.cardTitle);
        titleCard.setText("Texto desde la clase");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.add_name, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.add_item:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies()
    {
        return new ArrayList<Movie>()
        {{
            add(new Movie("Man of Steel", R.drawable.superman_man_of_steel));
            add(new Movie("Terminator", R.drawable.terminator_movie));
            add(new Movie("Transformers", R.drawable.transformers_dark));
            add(new Movie("Green Lantern", R.drawable.green_lantern));
            add(new Movie("300", R.drawable.movie_300));
        }};
    }

    private void addMovie(int i)
    {
        movies.add(i, new Movie("New Generic movie "+(++counter), R.drawable.generic_no_image));
        mAdapter.notifyItemInserted(i);
        mLayoutManager.scrollToPosition(i);
    }

    private void removeMovie(int i)
    {
        movies.remove(i);
        mAdapter.notifyItemRemoved(i);
    }
}
