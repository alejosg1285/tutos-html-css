package com.example.cliente.udemyandroidcero.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cliente.udemyandroidcero.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment
{
    private EditText textName;
    private Button btnSend;
    private DataListener callback;

    public DataFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            callback = (DataListener) context;
        }
        catch(Exception e)
        {
            throw new ClassCastException(context.toString() + " should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        //Se captura los elemntos de la UI.
        textName = (EditText) view.findViewById(R.id.nameDataFragment);
        btnSend = (Button) view.findViewById(R.id.btnSendDataFragment);

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                callback.sendData(textName.getText().toString());
            }
        });

        return view;
    }

    public interface DataListener
    {
        void sendData(String text);
    }
}
