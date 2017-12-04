package com.example.user.testapplication;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class YeOrderFront extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    String url="http://yeorder.com/api1/restapiv2/customers/getAllBuyerCategories";

    ArrayList<FoodItem> foodItems=new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    Permissions permissions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ye_order_front);
        requestQueue= Volley.newRequestQueue(this);

        permissions=new Permissions(this);





        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        request_for_json();
        Log.e("fooditemssize",""+foodItems.size());





    }

    private void request_for_json() {

        JsonObjectRequest jsonobjectrequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("request",response.toString());
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    Log.e("Response",response.toString());
                    for (int i =0;i<jsonArray.length();i++)
                    {
                        JSONObject object_small= jsonArray.getJSONObject(i);
                        FoodItem foodItem=new FoodItem();
                        foodItem.setId(object_small.optInt("id"));
                        foodItem.setImage_url(object_small.optString("image_name"));
                        foodItem.setImage_url_large(object_small.optString("largeimage"));
                        foodItem.setName(object_small.optString("name"));
                        foodItem.setType(object_small.optString("type"));
                        foodItems.add(foodItem);
                        Log.e("fooditems",""+foodItems.size());



                    }
                    recyclerAdapter=new RecyclerAdapter(foodItems,YeOrderFront.this);
                    recyclerView.setAdapter(recyclerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Error",""+e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error","Error");

            }
        });

        requestQueue.add(jsonobjectrequest);



    }
}
