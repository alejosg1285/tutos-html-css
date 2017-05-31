package com.example.alejosg.labandroidudemy.Adapters;

import android.app.Activity;
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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lenovo on 19/04/2017.
 */

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolder>
{
    //private Context context;
    private int layout;
    private List<Fruta> frutas;
    private OnItemClickListener itemClickListener;
    private Activity activity;

    public FrutaAdapter(List<Fruta> frutas, int layout, Activity activity, OnItemClickListener listener)
    {
        //this.context = context;
        this.layout = layout;
        this.frutas = frutas;
        this.itemClickListener = listener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);

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
            //se añade a la vista el evento de ContextMenu
            //Cargar la imagen con Picasso.
            v.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruta fruta, final OnItemClickListener listener)
        {
            txtNombre.setText(fruta.getNombre());
            txtOrigen.setText(fruta.getDescripcion());
            //imgIcono.setImageResource(fruta.getImgIcono());
            Picasso.with(activity).load(fruta.getImgIcono()).fit().into(imgIcono);

            imgIcono.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onItemClick(fruta, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
        {
            //Se obtiene el item seleccionado.
            Fruta frutaSelected = frutas.get(this.getAdapterPosition());
            //Se establece el titulo y el icono del menu.
            contextMenu.setHeaderTitle(frutaSelected.getNombre());
            contextMenu.setHeaderIcon(frutaSelected.getImgBackgroud());

            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu_fruit, contextMenu);
            //Se añade a cada elemento el evento onMenuItemClickListener
            for(int i = 0; i < contextMenu.size(); i++)
            {
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem)
        {
            switch(menuItem.getItemId())
            {
                case R.id.delete_fruit:
                    //Al estar dentro del adaptador, se pueden usar su metodos.
                    frutas.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Fruta fruta, int position);
    }
}
