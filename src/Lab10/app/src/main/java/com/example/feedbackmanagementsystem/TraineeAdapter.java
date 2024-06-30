package com.example.feedbackmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TraineeAdapter extends RecyclerView.Adapter<TraineeAdapter.TraineeViewHolder> {

    private List<Trainee> traineeList;

    public TraineeAdapter(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    @NonNull
    @Override
    public TraineeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trainee, parent, false);
        return new TraineeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraineeViewHolder holder, int position) {
        Trainee trainee = traineeList.get(position);
        holder.tvName.setText(trainee.getName());
        holder.tvEmail.setText(trainee.getEmail());
        holder.tvPhone.setText(trainee.getPhone());
        holder.tvGender.setText(trainee.getGender());
    }

    @Override
    public int getItemCount() {
        return traineeList.size();
    }

    public static class TraineeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvPhone, tvGender;

        public TraineeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvGender = itemView.findViewById(R.id.tvGender);
        }
    }
}

