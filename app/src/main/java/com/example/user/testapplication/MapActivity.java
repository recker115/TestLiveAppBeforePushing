package com.example.user.testapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    TextView location;
    GoogleMap mGoogleMap;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    ImageView backButton,home_Button;
    double lat, lon;
    Location location_map;
    ArrayList<String> latitudes=new ArrayList<>(),longitudes=new ArrayList<>(),titles=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        backButton=(ImageView) findViewById(R.id.backbutton);
        home_Button=(ImageView) findViewById(R.id.homebutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapActivity.super.onBackPressed();
            }
        });
        home_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapActivity.super.onBackPressed();
            }
        });

        location = (TextView) findViewById(R.id.location);
        location.setText(ClassForGoogleApi.address);
        initMap();
        Intent intent=getIntent();
        latitudes=intent.getStringArrayListExtra("latitudes");
        longitudes=intent.getStringArrayListExtra("longitudes");
        titles=intent.getStringArrayListExtra("titles");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
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
        location_map = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        lat = location_map.getLatitude();
        lon = location_map.getLongitude();
        goToLocationZoom(lat, lon, 12);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
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
        location_map = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        lat=location_map.getLatitude();
        lon=location_map.getLongitude();
        goToLocationZoom(lat,lon,12);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            /*boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));*/

            /*if (!success) {
                //Log.e(TAG, "Style parsing failed.");
            }*/
        } catch (Resources.NotFoundException e) {
            //Log.e(TAG, "Can't find style. Error: ", e);
        }
        //goToLocationZoom(26.912434 ,75.787271,10);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void initMap() {

        MapFragment mapfragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapfragment.getMapAsync(this);


    }
    private void goToLocationZoom(double lat, double lon, float zoom) {

        LatLng ll = new LatLng(lat, lon);
        CameraUpdate cm = CameraUpdateFactory.newLatLngZoom(ll, zoom);

    for (int i=0;i<latitudes.size();i++) {
        MarkerOptions markerOptions = new MarkerOptions()
                .title(titles.get(i))
                .snippet(titles.get(i))
                .position(new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i))));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.tt1_uber));
        mGoogleMap.addMarker(markerOptions);

    }
        mGoogleMap.moveCamera(cm);
    }
}
