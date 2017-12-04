package com.example.user.testapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OnItemClickActivity extends AppCompatActivity {

    RecyclerView recyclerView1,recyclerView2;
    ArrayList<FoodItem> foodItems=new ArrayList<>();
    public static TextView address;

    String url="http://yeorder.com/api1/restapiv2/customers/getCategoriesById";
    HashMap<String,String> map_for_id=new HashMap<>();
    RequestQueue requestQueue2;
    int id;
    ClassForGoogleApi classForGoogleApi;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_item_click);
        recyclerView1=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView2=(RecyclerView) findViewById(R.id.recyclerview2);
        Intent intent=getIntent();
        requestQueue2= Volley.newRequestQueue(this);
        id=intent.getExtras().getInt("id");
        Log.e("id",""+id);
        map_for_id=new HashMap<>();
        map_for_id.put("id",""+id);

        JsonObjectRequest jsonObjectRequest=requestforjson(map_for_id);
        address=(TextView) findViewById(R.id.address);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        classForGoogleApi=new ClassForGoogleApi(OnItemClickActivity.this);
        requestQueue2.add(jsonObjectRequest);


        //address.setText(classForGoogleApi.address);


        /*foodItems=intent.getParcelableArrayListExtra("Arraylist");*/









        //recyclerView2.setLayoutManager(new GridLayoutManager(this,3));
    }
    public JsonObjectRequest requestforjson(HashMap<String,String> map_id)
    {
        JsonObjectRequest jsonobjectrequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map_for_id), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("map_id",""+map_for_id.get("id"));
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    Log.e("Response",response.toString());
                    for (int i =0;i<jsonArray.length();i++)
                    {


                        JSONObject object_small= jsonArray.optJSONObject(i);
                        FoodItem foodItem=new FoodItem();
                        foodItem.setId(object_small.optInt("id"));
                        foodItem.setImage_url(object_small.optString("thumbnail"));
                        Log.e("small image",object_small.optString("thumbnail"));
                        foodItem.setImage_url_large(object_small.optString("largeimage"));
                        Log.e("small image",object_small.optString("largeimage"));
                        foodItem.setName(object_small.optString("name"));
                        foodItem.setType(object_small.optString("type"));


                        JSONArray jarray = object_small.optJSONArray("children");
                        ArrayList<children_model> childeren_list = new ArrayList<>();
                        Log.e("children length",""+jarray.length());
                         for(int j=0;j<jarray.length();j++)
                         {

                             JSONObject as_object= jarray.optJSONObject(j);
                             children_model children_model = new children_model();
                             children_model.setId(as_object.optInt("id"));
                             children_model.setImage(as_object.optString("thumbnail"));
                             /*Log.e("small image", object_small.optString("thumbnail"));
                             foodItem.setImage_url_large(object_small.optString("largeimage"));
                             Log.e("small image", object_small.optString("largeimage"));*/
                             children_model.setName(as_object.optString("name"));;
                             childeren_list.add(children_model);


                         }
                        foodItem.setName_children(childeren_list);






                        foodItems.add(foodItem);




                    }





                    Log.e("activity",""+foodItems.get(0).getName_children().size());
                    RecyclerAdapter2 recyclerAdapter2=new RecyclerAdapter2(OnItemClickActivity.this,foodItems);
                    recyclerView1.setAdapter(recyclerAdapter2);

                   // address.setText(ClassForGoogleApi.address);*/
                    Log.e("itemclicksize",""+foodItems.size());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonobjectrequest.setShouldCache(false);
        return jsonobjectrequest;


    }


}
