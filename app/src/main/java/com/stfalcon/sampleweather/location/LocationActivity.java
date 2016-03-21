package com.stfalcon.sampleweather.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.ViewDataBinding;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.stfalcon.sampleweather.models.LocationChangeEvent;
import com.stfalcon.sampleweather.ui.custom.base.binding.activities.ActivityViewModel;
import com.stfalcon.sampleweather.ui.custom.base.binding.activities.BindingActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by artem on 18.03.16.
 */
public abstract class LocationActivity<B extends ViewDataBinding, VM extends ActivityViewModel> extends BindingActivity<B, VM> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = LocationActivity.class.getName();
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGoogleApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startGoogleApiClient();
    }

    @Override
    protected void onStop() {
        stopGoogleApiClient();
        super.onStop();
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5 * 1000); // 5 sec
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Log.i(TAG, "onConnected: lat" + String.valueOf(mLastLocation.getLatitude()));
            Log.i(TAG, "onConnected: lon" + String.valueOf(mLastLocation.getLongitude()));
            EventBus.getDefault().post(new LocationChangeEvent(mLastLocation));
            haveNewUserLocation(mLastLocation);
        } else {
            createLocationRequest();
            startLocationUpdates();
        }
    }

    private void initGoogleApiClient() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void startGoogleApiClient() {
        mGoogleApiClient.connect();
    }

    public void stopGoogleApiClient() {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        EventBus.getDefault().post(new LocationChangeEvent(location));
        haveNewUserLocation(location);
        stopLocationUpdates();
    }

    public abstract void haveNewUserLocation(Location location);

    public void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }
}

