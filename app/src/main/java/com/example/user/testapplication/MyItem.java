package com.example.user.testapplication;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by User on 9/15/2017.
 */

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    /*private final*/ String mTitle;
    /*private final */String mSnippet;

    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public MyItem(double lat, double lng, String title, String snippet) {
        this.mPosition = new LatLng(lat, lng);
        this.mTitle = title;
        this.mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
