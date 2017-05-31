package com.example.alejosg.labandroidudemy.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alejosg.labandroidudemy.Models.Mail;
import com.example.alejosg.labandroidudemy.R;

import java.util.List;

/**
 * Created by Alejo Saa G on 26/05/2017.
 */

public class MailAdapter extends BaseAdapter
{
    private Context context;
    private int layout;
    private List<Mail> list;
    private int SUBJECT_MAX_LENGTH = 40;
    private int MESSAGE_MAX_LENGTH = 80;

    public MailAdapter(Context context, int layout, List<Mail> list)
    {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Mail getItem(int position)
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
        ViewHolder holder;

        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(layout, null);
            holder = new ViewHolder();
            holder.subject = (TextView) view.findViewById(R.id.textViewListSubject);
            holder.message = (TextView) view.findViewById(R.id.textViewListMessage);
            holder.sender = (TextView) view.findViewById(R.id.textViewListSenderName);
            view.setTag(holder);
        } else
        {
            holder = (ViewHolder) view.getTag();
        }

        Mail currentMail = getItem(position);

        //Si el subject es mayor a 40 caracteres se corta y se agrega puntos suspensivos.
        String shortSubject;
        if(currentMail.getSubject().length() > SUBJECT_MAX_LENGTH)
        {
            shortSubject = currentMail.getSubject().substring(0, SUBJECT_MAX_LENGTH) + "...";
        } else
        {
            shortSubject = currentMail.getSubject();
        }

        holder.subject.setText(shortSubject);

        //Mismo procedimiento que con el asunto.
        String shortMessage;
        if(currentMail.getMessage().length() > MESSAGE_MAX_LENGTH)
        {
            shortMessage = currentMail.getMessage().substring(0, MESSAGE_MAX_LENGTH) + "...";
        } else
        {
            shortMessage = currentMail.getMessage();
        }

        holder.message.setText(shortMessage);

        //Se captura al primer letra del remitente.
        holder.sender.setText(currentMail.getSenderName().substring(0, 1));
        //Se cambia el color de fondo del remitente obteniendo un color aleatorio.
        holder.sender.getBackground().setColorFilter(Color.parseColor("#" + currentMail.getColor()), PorterDuff.Mode.SRC);

        return view;
    }

    public static class ViewHolder
    {
        private TextView subject;
        private TextView message;
        private TextView sender;
    }
}
