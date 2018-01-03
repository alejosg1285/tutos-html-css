package com.example.alejosaag.udemy_newbies;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.alejosaag.udemy_newbies.POJO.Country;

import java.util.ArrayList;
import java.util.List;

public class GridviewActivity extends AppCompatActivity
{
    private List<Country> countries;
    private GridView countriesGrid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        
        countriesGrid = findViewById(R.id.gridView);
        
        countries = getCountries();
        countriesGrid.setAdapter(new CountryAdapter(this, R.layout.country, countries));
    }
    
    private class CountryAdapter extends ArrayAdapter<Country>
    {
        private LayoutInflater layoutInflater;
        private Resources resources;
        
        public CountryAdapter(@NonNull Context context, int resource, @NonNull List<Country> countries)
        {
            super(context, resource, countries);
            
            layoutInflater = LayoutInflater.from(context);
            resources = getResources();
        }
        
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            TextView countryTextView = (TextView) convertView;
            if (convertView != null)
                countryTextView = (TextView) layoutInflater.inflate(R.layout.country, null);
            
            Country country = getItem(position);
            countryTextView.setText(country.getName());
            countryTextView.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(country.getFlag()), null, null);
            
            return countryTextView;
        }
    }
    
    private List<Country> getCountries()
    {
        List<Country> countries = new ArrayList<Country>();
        
        countries.add(new Country("Albania", R.mipmap.albania));
        countries.add(new Country("Anguilla", R.mipmap.anguilla));
        countries.add(new Country("Armenia", R.mipmap.armenia));
        countries.add(new Country("Azerbaijan", R.mipmap.azerbaijan));
        countries.add(new Country("China", R.mipmap.china));
        countries.add(new Country("Colombia", R.mipmap.colombia));
        countries.add(new Country("Croatia", R.mipmap.croatia));
        countries.add(new Country("Cuba", R.mipmap.cuba));
        countries.add(new Country("England", R.mipmap.england));
        countries.add(new Country("Morocco", R.mipmap.morocco));
        countries.add(new Country("Suriname", R.mipmap.suriname));
        countries.add(new Country("United Kingdom", R.mipmap.united_kingdom));
        
        return countries;
    }
}
