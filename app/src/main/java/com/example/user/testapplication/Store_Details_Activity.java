package com.example.user.testapplication;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;

public class Store_Details_Activity extends AppCompatActivity {
    ImageView store_pic,back;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    String url="http://yeorder.com/api1/restapiv2/customers/getSubcategoryByStore";
    String url2="http://yeorder.com/api1/restapiv2/customers/getProductBySubcategoryPagingNew";
    String seller_id;
    HashMap<String,String> hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__details_);

        store_pic=(ImageView) findViewById(R.id.store_pic);
        Picasso.with(this).load(getIntent().getExtras().getString("store_pic")).placeholder(R.drawable.cookies6_img).into(store_pic);
        back=(ImageView) findViewById(R.id.backbutton);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue= Volley.newRequestQueue(this);
        Intent intent=getIntent();
        seller_id=intent.getExtras().getString("seller_id");
        hashMap=new HashMap<>();
        hashMap.put("seller_id",seller_id);
        Log.e("seller_id",seller_id);
        hashMap.put("category_id",FoodItemAcitivity.category_id);
        Log.e("category_id",FoodItemAcitivity.category_id);
        //recyclerView.setAdapter();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store_Details_Activity.super.onBackPressed();
            }
        });


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(hashMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("response",response.toString());







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
