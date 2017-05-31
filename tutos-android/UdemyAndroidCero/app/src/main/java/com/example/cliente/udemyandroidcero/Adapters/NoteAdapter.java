package com.example.cliente.udemyandroidcero.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cliente.udemyandroidcero.Models.Note;
import com.example.cliente.udemyandroidcero.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by AlejoSaaG on 28/04/2017.
 */

public class NoteAdapter extends BaseAdapter
{
    private Context context;
    private List<Note> list;
    private int layout;

    public NoteAdapter(Context context, List<Note> list, int layout)
    {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Note getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        ViewHolder vh;

        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(layout, null);

            vh = new ViewHolder();
            vh.description = (TextView) view.findViewById(R.id.textNoteDescription);
            vh.createdAt = (TextView) view.findViewById(R.id.textNoteCreatedAt);

            view.setTag(vh);
        } else
        {
            vh = (ViewHolder) view.getTag();
        }

        Note note = list.get(position);

        vh.description.setText(note.getDescripcion());

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        vh.createdAt.setText(df.format(note.getCreatedAt()));
        return view;
    }

    public class ViewHolder
    {
        TextView description;
        TextView createdAt;
    }
}
