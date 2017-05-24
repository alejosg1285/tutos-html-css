package com.example.alejosg.labandroidudemy.Application;

import android.app.Application;
import android.os.SystemClock;

import com.example.alejosg.labandroidudemy.Models.City;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Alejo Saa G on 17/05/2017.
 */

public class MyApplication extends Application
{
    public static AtomicInteger CityId = new AtomicInteger();

    @Override
    public void onCreate()
    {
        super.onCreate();

        Realm.init(this);
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        CityId = getIdByTable(realm, City.class);
        realm.close();

        //Pausar el sistema para visualizar el splash por 3 segundos.
        SystemClock.sleep(3000);
    }

    private void setUpRealmConfig()
    {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass)
    {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
