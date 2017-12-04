package com.example.user.testapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
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

public class AnotherChild extends RecyclerView.Adapter<AnotherChild.VideoAdapter> {


    Context context;
    ArrayList<FoodItem> foodItems = new ArrayList<>();


    AnotherChild(Context context, ArrayList<FoodItem> foodItems) {

        Log.e("shekhawat", "sizeadpter=" + foodItems.size());
        this.context = context;
        this.foodItems = foodItems;
        Log.e("received item", "" + foodItems.size());

    }


    @Override
    public VideoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.childchilidchilid, parent, false);
        VideoAdapter adapter = new VideoAdapter(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(final VideoAdapter holder, final int position) {


        Picasso.with(context).load(foodItems.get(position).getLogo()).placeholder(R.drawable.cookies6_img).into(holder.foodimage);
        holder.name.setText(foodItems.get(position).getStore_name());
        holder.distance.setText(foodItems.get(position).getDistance()+" Mtr");
        holder.locality.setText(foodItems.get(position).getAddress());

        if (foodItems.get(position).getStatus().equalsIgnoreCase("active"))
            holder.status.setText("Open");
        else if (foodItems.get(position).getStatus().equalsIgnoreCase("shutter-up"))
            holder.status.setText("Closed");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + foodItems.get(position).getCall_number()));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
        }
    });

  holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
         Intent intent=new Intent(context,Store_Details_Activity.class);
          intent.putExtra("store_pic",foodItems.get(position).getLogo());
          intent.putExtra("seller_id",foodItems.get(position).getSellerid());
          intent.putExtra("category_id",FoodItemAcitivity.category_id);
          context.startActivity(intent);
      }
  });
        //holder.name.setText(foodItems.get(position).getName());
        /*holder.child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/


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

    public TextView name,status,distance,locality;
    //public LinearLayout child;

    public  CircleImageView foodimage;
    public ImageView imageView;
    public CardView cardView;




    public VideoAdapter(View itemView) {
        super(itemView);
        if (itemView!=null) {

            foodimage=(CircleImageView) itemView.findViewById(R.id.logo);
            name = (TextView) itemView.findViewById(R.id.storename);
            distance = (TextView) itemView.findViewById(R.id.distance);
            status = (TextView) itemView.findViewById(R.id.status);
            imageView=(ImageView) itemView.findViewById(R.id.callbutton);
            cardView=(CardView) itemView.findViewById(R.id.cardview);
            locality = (TextView) itemView.findViewById(R.id.locality);
            //child=(LinearLayout) itemView.findViewById(R.id.linearchild);*/


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
