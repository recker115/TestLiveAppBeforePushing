package com.example.user.testapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

public class ClassForGoogleApiac extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_for_google_apiac);

        Log.e("latitude",""+latitude);


    }


    Location mcurrent_location;
    double latitude,longitude;
    List<Address> addresses;
    public String address;
    GoogleApiClient googleApiClient;



    ClassForGoogleApiac() {


        this.googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }
    Geocoder geocoder;



    LocationRequest locationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        geocoder=new Geocoder(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        mcurrent_location=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        latitude=mcurrent_location.getLatitude();
        longitude=mcurrent_location.getLongitude();
        Log.e("latitude",""+latitude);
        try {
            getfromGeocoder(latitude,longitude);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getfromGeocoder(double latitude, double longitude) throws IOException {
        addresses=geocoder.getFromLocation(latitude,longitude,1);
        this.address=addresses.get(0).getAddressLine(0);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
