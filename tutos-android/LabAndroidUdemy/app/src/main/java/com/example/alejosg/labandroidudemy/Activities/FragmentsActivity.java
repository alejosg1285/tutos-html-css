package com.example.alejosg.labandroidudemy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alejosg.labandroidudemy.Fragments.DetailsFragment;
import com.example.alejosg.labandroidudemy.Fragments.ListFragment;
import com.example.alejosg.labandroidudemy.Models.Mail;
import com.example.alejosg.labandroidudemy.R;

public class FragmentsActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener
{
    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        setMultiPanel();
    }

    @Override
    public void onListClick(Mail mail){
        if(isMultiPanel){
            DetailsFragment detailsFragment=(DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDetailsMail);
            detailsFragment.renderMail(mail);
        }
        else {
            Intent intent=new Intent(this, DetailsActivity.class);
            intent.putExtra("subject", mail.getSubject());
            intent.putExtra("message", mail.getMessage());
            intent.putExtra("senderName", mail.getSenderName());
            startActivity(intent);
        }
    }

    private void setMultiPanel()
    {
        isMultiPanel=(getSupportFragmentManager().findFragmentById(R.id.fragmentDetailsMail)!=null);
    }
}
