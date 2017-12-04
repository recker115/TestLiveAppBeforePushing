package com.example.user.testapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 9/11/2017.
 */

public class Recylse_adpter_children  extends RecyclerView.Adapter<Recylse_adpter_children.VideoAdapter>  {




        Context context;
        ArrayList<children_model> foodItems=new ArrayList<>();


    Recylse_adpter_children( Context context,ArrayList<children_model> foodItems)
        {

            Log.e("shekhawat","sizeadpter="+foodItems.size());
        this.context=context;
        this.foodItems=foodItems;
        Log.e("received item",""+foodItems.size());

        }




@Override
public VideoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.from(parent.getContext()).inflate(R.layout.recyclerview2,parent,false);
        VideoAdapter adapter=new VideoAdapter(view);

        return adapter;
        }

@Override
public void onBindViewHolder(final VideoAdapter holder, final int position) {


       Picasso.with(context).load(foodItems.get(position).getImage()).placeholder(R.drawable.cookies6_img).into(holder.foodimage);
        holder.name.setText(foodItems.get(position).getName());
        holder.foodimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FoodItemAcitivity.class);
                intent.putExtra("Category_id",foodItems.get(position).getId());
                intent.putExtra("FoodImage",foodItems.get(position).getImage());
                intent.putExtra("FoodCategory",foodItems.get(position).getName());
                context.startActivity(intent);



            }
        });



/*

        holder.cardView1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        if (holder.cardView2.getVisibility()==View.GONE)
        holder.cardView2.setVisibility(View.VISIBLE);
        else
        holder.cardView2.setVisibility(View.GONE);
        }
        });
*/


        }







@Override
public int getItemCount() {

        return foodItems.size();
        }




public  class VideoAdapter extends RecyclerView.ViewHolder{

    public TextView name;
    public LinearLayout child;

    public  ImageView foodimage;




    public VideoAdapter(View itemView) {
        super(itemView);
        if (itemView!=null) {

            foodimage=(CircleImageView) itemView.findViewById(R.id.circle_child);
            name = (TextView) itemView.findViewById(R.id.name);
            //child=(LinearLayout) itemView.findViewById(R.id.linearchild);


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
