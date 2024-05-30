package com.example.lab4_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4_1.Entities.Food;
import com.example.lab4_1.R;

import java.text.DecimalFormat;
import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;
    private DecimalFormat decimalFormat;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
        this.decimalFormat = new DecimalFormat("#.###");
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvCost = (TextView) view.findViewById(R.id.tvCost);
            holder.imgDrink = (ImageView) view.findViewById(R.id.IvImage);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Food food = foodList.get(position);

        holder.tvName.setText(food.getName());
        holder.tvCost.setText(String.format("%s VND", decimalFormat.format(food.getMoney())));
        holder.imgDrink.setImageResource(food.getImage());

        return view;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvCost;
        ImageView imgDrink;
    }
}
