package com.stfalcon.sampleweather.models;

import android.location.Location;

/**
 * Created by artem on 18.03.16.
 */
public class LocationChangeEvent {
    public final Location currentLocation;

    public LocationChangeEvent(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
