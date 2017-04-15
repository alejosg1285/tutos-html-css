package com.example.cliente.udemyandroidcero.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cliente.udemyandroidcero.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by CLIENTE on 31/03/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> names;
    private int layout;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapter(List<String> names, int layout, OnItemClickListener listener) {
        this.names = names;
        this.layout = layout;
        this.onItemClickListener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }

        public void bind(final String name, final OnItemClickListener listener) {
            this.textViewName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClik(name, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClik(String name, int position);
    }
}
