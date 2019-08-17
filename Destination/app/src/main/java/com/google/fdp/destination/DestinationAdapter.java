package com.google.fdp.destination;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by gama on 2019-08-17.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder>{
    private ArrayList<Destination> destinations;
    private OnItemClickCallback onItemClickCallback;

    DestinationAdapter(ArrayList<Destination> list) {
        this.destinations = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_destination, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        Destination destination = destinations.get(i);
        Glide.with(holder.itemView.getContext())
                .load(destination.getImage())
                .into(holder.ivPhoto);
        holder.tvName.setText(destination.getName());
        holder.tvLocation.setText(destination.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(destinations.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvLocation;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLocation = itemView.findViewById(R.id.tv_location);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Destination destination);
    }
}
