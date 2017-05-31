package com.example.alejosg.labandroidudemy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alejosg.labandroidudemy.Models.Mail;
import com.example.alejosg.labandroidudemy.R;

public class DetailsFragment extends Fragment
{
    private TextView textViewSubject;
    private TextView textViewMessage;
    private TextView textViewSender;
    private LinearLayout wrapper;

    public DetailsFragment()
    {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textViewSubject = (TextView) view.findViewById(R.id.textViewFragmentSubject);
        textViewMessage = (TextView) view.findViewById(R.id.textViewFragmentMessage);
        textViewSender = (TextView) view.findViewById(R.id.textViewFragmentSenderName);
        wrapper = (LinearLayout) view.findViewById(R.id.fragmentDetailsMailWrapper);

        return view;
    }

    public void renderMail(Mail mail)
    {
        wrapper.setVisibility(View.VISIBLE);
        textViewSubject.setText(mail.getSubject());
        textViewSender.setText(mail.getSenderName());
        textViewMessage.setText(mail.getMessage());
    }
}
