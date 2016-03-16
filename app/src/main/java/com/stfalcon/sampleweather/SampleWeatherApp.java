package com.stfalcon.sampleweather;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by artem on 16.03.16.
 */
public class SampleWeatherApp extends Application {

    private static SampleWeatherApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public static SampleWeatherApp get() {
        return instance;
    }
}
