package com.example.user.testapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodItemAcitivity extends AppCompatActivity {
    ImageView food_image;
    RequestQueue requestQueue;
    String url="http://yeorder.com/api1/restapiv2/customers/getStoreByRadius",foodname;
    String latitude,longitude,radius,food_imgage;
    public  static String category_id;
    HashMap<String,String> hashMap;
    ArrayList<FoodItem> foodItems=new ArrayList<>();
    RecyclerView recyclerview;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView location;
    ImageView backbutton,location_image;
    ArrayList<String> latitudes=new ArrayList<>();
    ArrayList<String> longitudes=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_acitivity);
        food_image=(ImageView) findViewById(R.id.image_food);
        requestQueue= Volley.newRequestQueue(this);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        Intent intent=getIntent();
        category_id=intent.getExtras().get("Category_id").toString();
        foodname=intent.getExtras().getString("FoodCategory");
        collapsingToolbarLayout.setTitle(Html.fromHtml("<font color = #ffffff size=1 >" + foodname + "</font>"));
        Typeface font = Typer.set(this).getFont(Font.ROBOTO_MEDIUM);
        collapsingToolbarLayout.setExpandedTitleTypeface(font);
        latitude=String.valueOf(ClassForGoogleApi.latitude);
        longitude=String.valueOf(ClassForGoogleApi.longitude);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        radius=String.valueOf(10000);
        food_imgage=intent.getExtras().getString("FoodImage");
        Picasso.with(this).load(food_imgage).into(food_image);
        hashMap=new HashMap<>();
        hashMap.put("category_id",category_id);
        Log.e("category_id",""+category_id);
        hashMap.put("latitude",String.valueOf(26.9770001));
        hashMap.put("longitude",String.valueOf(75.7764381));
            hashMap.put("radius",radius);
        location=(TextView) findViewById(R.id.location);
        location.setText(ClassForGoogleApi.address);
        backbutton=(ImageView) findViewById(R.id.backbutton);
        location_image=(ImageView) findViewById(R.id.locationimage);


        location_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodItemAcitivity.this,MapActivity.class);
                intent.putExtra("latitudes",latitudes);
                intent.putExtra("longitudes",longitudes);
                intent.putExtra("titles",titles);
                startActivity(intent);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItemAcitivity.super.onBackPressed();
            }
        });



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(hashMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response",response.toString());
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object=jsonArray.optJSONObject(i);
                        FoodItem foodItem=new FoodItem();
                        foodItem.setLogo(object.optString("logo"));
                        foodItem.setStore_name(object.optString("store_name"));
                        foodItem.setStatus(object.optString("status"));
                        foodItem.setCall_number(object.optString("contact_number"));
                        foodItem.setDistance(object.optString("distance"));
                        foodItem.setLocality(object.optString("locality"));
                        foodItem.setAddress(object.optString("address"));
                        foodItem.setSellerid(object.optString("user_id"));
                        latitudes.add(object.optString("latitude"));
                        longitudes.add(object.optString("longitude"));
                        titles.add(object.optString("store_name"));
                        foodItems.add(foodItem);
                    }
                    AnotherChild anotherChild=new AnotherChild(FoodItemAcitivity.this,foodItems);
                    recyclerview.setNestedScrollingEnabled(false);
                    recyclerview.setAdapter(anotherChild);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonObjectRequest.setShouldCache(false);
      requestQueue.add(jsonObjectRequest);

    }
}
