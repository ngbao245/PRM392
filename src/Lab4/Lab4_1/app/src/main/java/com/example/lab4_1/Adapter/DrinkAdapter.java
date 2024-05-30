package com.example.lab4_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4_1.Entities.Drink;
import com.example.lab4_1.R;

import java.text.DecimalFormat;
import java.util.List;

public class DrinkAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Drink> drinkList;
    private DecimalFormat decimalFormat;

    public DrinkAdapter(Context context, int layout, List<Drink> drinkList) {
        this.context = context;
        this.layout = layout;
        this.drinkList = drinkList;
        this.decimalFormat = new DecimalFormat("#.###");
    }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvCost = (TextView) view.findViewById(R.id.tvCost);
            holder.imgDrink = (ImageView) view.findViewById(R.id.IvImage);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Drink drink = drinkList.get(position);

        holder.tvName.setText(drink.getName());
        holder.tvCost.setText(String.format("%s VND", decimalFormat.format(drink.getMoney())));
        holder.imgDrink.setImageResource(drink.getImage());

        return view;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvCost;
        ImageView imgDrink;
    }
}
