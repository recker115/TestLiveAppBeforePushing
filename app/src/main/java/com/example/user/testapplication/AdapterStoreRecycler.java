package com.example.user.testapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 9/16/2017.
 */

public class AdapterStoreRecycler extends RecyclerView.Adapter<AdapterStoreRecycler.VideoAdapter> {
    @Override
    public VideoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.childchilidchilid, parent, false);
        AdapterStoreRecycler.VideoAdapter adapter = new VideoAdapter(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(VideoAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VideoAdapter extends RecyclerView.ViewHolder {
        public VideoAdapter(View itemView) {
            super(itemView);
        }
    }
}
