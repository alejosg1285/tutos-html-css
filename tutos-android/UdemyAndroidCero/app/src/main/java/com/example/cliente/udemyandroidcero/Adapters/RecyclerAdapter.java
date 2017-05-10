package com.example.cliente.udemyandroidcero.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cliente.udemyandroidcero.Models.Movie;
import com.example.cliente.udemyandroidcero.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CLIENTE on 31/03/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public RecyclerAdapter(List<Movie> movies, int layout, OnItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context=parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster=(ImageView)itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {
            //Renderizar los datos en la vista.
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            this.textViewName.setText(movie.getTitle());
            //this.imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClik(movie, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClik(Movie movie, int position);
    }
}
