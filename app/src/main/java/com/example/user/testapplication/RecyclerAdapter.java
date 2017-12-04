package com.example.user.testapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcel;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by User on 8/24/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VideoAdapter>  {

   ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
    ArrayList<FoodItem> filter=new ArrayList<FoodItem>();


    Context context;
    ArrayList<FoodItem> foodItemsfilter=new ArrayList<FoodItem>();
    ArrayList<FoodItem> itemclick=new ArrayList<FoodItem>();
    int m_position;






    RecyclerAdapter(ArrayList<FoodItem> foodItems, Context context)
    {

        this.context=context;
        this.foodItemsfilter=foodItems;
        this.foodItems=foodItems;
        Log.e("foodItemsize",""+foodItems.size());


    }




    @Override
    public VideoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        VideoAdapter adapter=new VideoAdapter(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(final VideoAdapter holder, final int position) {

        if (position==0) {

            Picasso.with(context).load(foodItems.get(position).getImage_url_large()).into(holder.card_image);
            holder.firstname.setText(foodItems.get(position).getName());


            Picasso.with(context).load(foodItems.get(position + 1).getImage_url()).into(holder.image_equal);
            holder.secondname.setText(foodItems.get(position+1).getName());


            Picasso.with(context).load(foodItems.get(position + 2).getImage_url_large()).into(holder.image_equa2);
            holder.thirdname.setText(foodItems.get(position+2).getName());


        }
     else  {

         m_position=position *3;



         Picasso.with(context).load(foodItems.get(m_position).getImage_url_large()).into(holder.card_image);
            if ((foodItems.size()%3)!=1)
         Picasso.with(context).load(foodItems.get(m_position + 1).getImage_url()).into(holder.image_equal);
            if ((foodItems.size()%3)==0)
         Picasso.with(context).load(foodItems.get(m_position + 2).getImage_url()).into(holder.image_equa2);

            holder.firstname.setText(foodItems.get(m_position).getName());
            Log.e("firstname",foodItems.get(position).getName());
            holder.secondname.setText(foodItems.get(m_position+1).getName());
            holder.thirdname.setText(foodItems.get(m_position+2).getName());

            /*this.map_for_id.put("id",foodItems.get(position).getId());
            this.map_for_id.put("id",foodItems.get(position+1).getId());
            this.map_for_id.put("id",foodItems.get(position+2).getId());*/
     }
     if (foodItems.size()%3==1)
     {
         holder.cardView2.setVisibility(View.GONE);
         holder.cardView3.setVisibility(View.GONE);


     }
     else if (foodItems.size()%3==2)
         holder.cardView3.setVisibility(View.GONE);


     holder.cardView1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             if (position==0) {



                 Intent intent = new Intent(context, OnItemClickActivity.class);
                 intent.putExtra("id",foodItems.get(position).getId());
                 context.startActivity(intent);
             }
             else {

                 m_position=position*3;
                 Intent intent = new Intent(context, OnItemClickActivity.class);
                 intent.putExtra("id",foodItems.get(m_position).getId());
                 Log.e("parcelarraylist",""+itemclick.size());
                 context.startActivity(intent);

             }

         }
     });
        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position==0) {

                    Intent intent = new Intent(context, OnItemClickActivity.class);
                    intent.putExtra("id",foodItems.get(position+1).getId());
                    context.startActivity(intent);
                }
                else {

                    m_position=position*3;

                    Intent intent = new Intent(context, OnItemClickActivity.class);
                    intent.putExtra("id",foodItems.get(m_position+1).getId());
                    context.startActivity(intent);

                }
            }
        });
        holder.cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position==0) {

                    Intent intent = new Intent(context, OnItemClickActivity.class);
                    intent.putExtra("id",foodItems.get(position+2).getId());
                    context.startActivity(intent);
                }
                else {

                    m_position=position*3;
                    Intent intent = new Intent(context, OnItemClickActivity.class);
                    intent.putExtra("id",foodItems.get(m_position+2).getId());
                    context.startActivity(intent);

                }

            }
        });

        }







    @Override
    public int getItemCount() {
          if ((foodItems.size()%3)==0)
        return foodItems.size()/3;
        else if ((foodItems.size()%3)==1)
            return foodItems.size()/3+1;
        else
            return foodItems.size()/3+2;
    }




    public  class VideoAdapter extends RecyclerView.ViewHolder{
       public   ImageView image_equal,image_equa2,card_image;
       public  TextView name;
        public  TextView firstname,secondname,thirdname;
        public  CardView cardView1,cardView2,cardView3;
        public  ImageView callbutton;


       public VideoAdapter(View itemView) {
           super(itemView);
           if (itemView!=null) {
               image_equal = (ImageView) itemView.findViewById(R.id.small_image1);
               image_equa2 = (ImageView) itemView.findViewById(R.id.small_image2);
               card_image=(ImageView) itemView.findViewById(R.id.card_image);
               cardView1=(CardView) itemView.findViewById(R.id.card1);
               cardView2=(CardView) itemView.findViewById(R.id.card2);
               cardView3=(CardView) itemView.findViewById(R.id.card3);

               //name = (TextView) itemView.findViewById(R.id.cookiesname);
               /*textView2 = (TextView) itemView.findViewById(R.id.price);

               callbutton=(ImageView) itemView.findViewById(R.id.callbuton);*/
               //cardView = (CardView) itemView.findViewById(R.id.cardview);
               firstname = (TextView) itemView.findViewById(R.id.firstname);
               secondname = (TextView) itemView.findViewById(R.id.secondname);
               thirdname = (TextView) itemView.findViewById(R.id.thirdname);
              /* name = (TextView) itemView.findViewById(R.id.thirdname);*/


           }

       }
   }








}
