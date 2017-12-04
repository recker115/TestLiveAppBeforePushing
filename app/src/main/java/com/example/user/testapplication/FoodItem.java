package com.example.user.testapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 9/9/2017.
 */

class FoodItem implements Serializable {

    String image_url,image_url_large;
    String name;
    String type;
    int id;
    String logo;
    String locality;
    String category_id;

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    String sellerid;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    String distance;

    public String getCall_number() {
        return call_number;
    }

    public void setCall_number(String call_number) {
        this.call_number = call_number;
    }

    String call_number;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    String store_name;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    ArrayList<children_model> name_children= new ArrayList<>();

    public ArrayList<children_model> getName_children() {
        return name_children;
    }

    public void setName_children(ArrayList<children_model> name_children) {
        this.name_children = name_children;
    }







    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url_large() {
        return image_url_large;
    }

    public void setImage_url_large(String image_url_large) {
        this.image_url_large = image_url_large;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
