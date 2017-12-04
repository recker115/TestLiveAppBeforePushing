package com.example.user.testapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 9/12/2017.
 */

public class ClassForGoogleApi implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Context context;
    Location mcurrent_location;
    public static double latitude,longitude;
    List<Address> addresses;
    public static String address;



    ClassForGoogleApi(Context context) {

        this.context = context;
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks( this)
                .addOnConnectionFailedListener( this)
                .build();
        googleApiClient.connect();
    }
    Geocoder geocoder;
    GoogleApiClient googleApiClient ;


    LocationRequest locationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        geocoder=new Geocoder(context);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        Log.e("address",""+address);
    }

    private void getfromGeocoder(double latitude, double longitude) throws IOException {
        addresses=geocoder.getFromLocation(latitude,longitude,1);
        this.address=addresses.get(0).getAddressLine(0)+" "+addresses.get(0).getCountryName()+" "+addresses.get(0).getLocality();
        OnItemClickActivity.address.setText(address);
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
