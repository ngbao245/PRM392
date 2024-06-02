package com.example.lab5_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RAMAdapter extends RecyclerView.Adapter<RAMAdapter.ViewHolder> {
    private ArrayList<RAM> ramList;
    private OnItemClickListener listener;

    public RAMAdapter(ArrayList<RAM> ramList, OnItemClickListener listener) {
        this.ramList = ramList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ram, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RAM ram = ramList.get(position);
        holder.tvName.setText(ram.getName());
        holder.tvDescription.setText(ram.getDescription());
        holder.tvBrand.setText(ram.getBrand());
        holder.ivImage.setImageResource(ram.getImage());

        if (ram.getImage() != 0) {
            holder.ivImage.setImageResource(ram.getImage());
        } else {
            String imageUrl = ram.getImageURL();
            Picasso.get()
                    .load(imageUrl)
                    .into(holder.ivImage);
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(currentPosition);
                }
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(currentPosition);
                }
            }
        });
    }

    private void removeItem(int position) {
        ramList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, ramList.size());
    }

    @Override
    public int getItemCount() {
        return ramList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        TextView tvBrand;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvBrand = itemView.findViewById(R.id.tv_brand);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }
}
