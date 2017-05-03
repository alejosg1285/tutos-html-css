package com.example.alejosg.labandroidudemy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejosg.labandroidudemy.Models.Fruta;
import com.example.alejosg.labandroidudemy.R;

import java.util.List;

/**
 * Created by Lenovo on 19/04/2017.
 */

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolder>
{
    private Context context;
    private int layout;
    private List<Fruta> frutas;
    private OnItemClickListener itemClickListener;

    public FrutaAdapter(List<Fruta> frutas, int layout, OnItemClickListener listener)
    {
        //this.context = context;
        this.layout = layout;
        this.frutas = frutas;
        this.itemClickListener = listener;
    }

    @Override
    public FrutaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bind(frutas.get(position), itemClickListener);
    }

    @Override
    public int getItemCount()
    {
        return frutas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener
    {
        public TextView txtNombre;
        public TextView txtOrigen;
        public ImageView imgIcono;

        public ViewHolder(View v)
        {
            super(v);

            txtNombre = (TextView) v.findViewById(R.id.txtNombre);
            txtOrigen = (TextView) v.findViewById(R.id.txtOrigin);
            imgIcono = (ImageView) v.findViewById(R.id.imgFruit);

            v.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruta fruta, final OnItemClickListener listener)
        {
            txtNombre.setText(fruta.getNombre());
            txtOrigen.setText(fruta.getOrigen());
            imgIcono.setImageResource(fruta.getIcono());

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onItemClick(fruta, getAdapterPosition());
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem)
        {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
        {
            Fruta fruta = frutas.get(getAdapterPosition());

            contextMenu.setHeaderTitle(fruta.getNombre());

            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.delete_menu, contextMenu);

            for(int i = 0; i < contextMenu.size(); i++)
            {
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Fruta fruta, int position);
    }
}
