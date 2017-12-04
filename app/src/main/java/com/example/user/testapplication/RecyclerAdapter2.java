package com.example.user.testapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by User on 8/24/2017.
 */

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.VideoAdapter>  {




  Context context;
    ArrayList<FoodItem> foodItems=new ArrayList<>();
    Animation animation;


    RecyclerAdapter2( Context context,ArrayList<FoodItem> foodItems)
    {

        Log.e("recyclerview adapter",""+foodItems.get(0).getName_children().size());
   this.context=context;
        this.foodItems=foodItems;
        Log.e("received item",""+foodItems.size());
        this.animation= AnimationUtils.loadAnimation(context,R.anim.fadein);

    }




    @Override
    public VideoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.from(parent.getContext()).inflate(R.layout.itemonclick,parent,false);
        VideoAdapter adapter=new VideoAdapter(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(final VideoAdapter holder, final int position) {


         Picasso.with(context).load(foodItems.get(position).getImage_url()).placeholder(R.drawable.cookies6_img).into(holder.image_equal);
        holder.name.setText(foodItems.get(position).getName());

        Log.e("shekhawat","size="+foodItems.get(position).getName_children().size());

        Recylse_adpter_children adpter_children= new Recylse_adpter_children(context,foodItems.get(position).getName_children());
        holder.rcycle_child.setAdapter(adpter_children);


        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.cardView2.getVisibility()==View.GONE)
                {
                    holder.cardView2.setVisibility(View.VISIBLE);
                    holder.cardView2.startAnimation(animation);
                    holder.image_equal.setVisibility(View.GONE);

                }

                else {
                    holder.cardView2.setVisibility(View.GONE);
                    holder.image_equal.setVisibility(View.VISIBLE);
                }
            }
        });


        }







    @Override
    public int getItemCount() {

        return foodItems.size();
    }




    public  class VideoAdapter extends RecyclerView.ViewHolder{
       public   ImageView image_equa2,card_image;
       public  TextView name;
        public CircleImageView image_equal;
        public  TextView firstname,secondname,thirdname;
        public  CardView cardView1,cardView2,cardView3;
        public  ImageView callbutton;

        public  RecyclerView rcycle_child;


       public VideoAdapter(View itemView) {
           super(itemView);
           if (itemView!=null) {
               image_equal = (CircleImageView) itemView.findViewById(R.id.main_thumbnail);
               image_equa2 = (ImageView) itemView.findViewById(R.id.small_image2);
               card_image=(ImageView) itemView.findViewById(R.id.card_image);
               cardView1=(CardView) itemView.findViewById(R.id.maincard);
               cardView2=(CardView) itemView.findViewById(R.id.secondarycard);
               cardView3=(CardView) itemView.findViewById(R.id.card3);
               name = (TextView) itemView.findViewById(R.id.main_name);
               rcycle_child= itemView.findViewById(R.id.recyclerview2);
               rcycle_child.setLayoutManager(new GridLayoutManager(context,3));


               /*//name = (TextView) itemView.findViewById(R.id.cookiesname);
               *//*textView2 = (TextView) itemView.findViewById(R.id.price);

               callbutton=(ImageView) itemView.findViewById(R.id.callbuton);*//*
               //cardView = (CardView) itemView.findViewById(R.id.cardview);
               firstname = (TextView) itemView.findViewById(R.id.firstname);
               secondname = (TextView) itemView.findViewById(R.id.secondname);
               thirdname = (TextView) itemView.findViewById(R.id.thirdname);
              *//* name = (TextView) itemView.findViewById(R.id.thirdname);*/


           }

       }
   }






}
