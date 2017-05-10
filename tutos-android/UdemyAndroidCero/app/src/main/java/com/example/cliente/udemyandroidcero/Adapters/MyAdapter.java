package com.example.cliente.udemyandroidcero.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cliente.udemyandroidcero.R;

import java.util.List;

/**
 * Created by CLIENTE on 29/03/2017.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> nombres;

    public MyAdapter(Context context, int layout, List<String> nombres) {
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
    }

    @Override
    public int getCount() {
        return this.nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return this.nombres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View Holder Pattern
        ViewHolder viewHolder;

        if(convertView==null) {
            //Se infla la vista que llega por controlador.
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            viewHolder=new ViewHolder();

            //Se referencia el elemento de la vista y se carga de datos.
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.lstItem);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder)convertView.getTag();
        }

        String name = this.nombres.get(position);
        //String name=(String)getItem(position);

        //Se referencia el elemento de la vista y se carga de datos.
        viewHolder.nameTextView.setText(name);

        return convertView;
    }

    static class ViewHolder{
        private TextView nameTextView;
    }
}
