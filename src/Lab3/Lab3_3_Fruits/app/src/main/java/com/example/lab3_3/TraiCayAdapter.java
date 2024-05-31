package com.example.lab3_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TraiCayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<TraiCay> traiCayList;

    public TraiCayAdapter(Context context, int layout, ArrayList<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }


    @Override
    public int getCount() {
        return traiCayList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        //AnhxaView
        TextView txtTen = convertView.findViewById(R.id.textViewTen);
        TextView txtMota = convertView.findViewById(R.id.textViewMoTa);
        ImageView imageHinh = convertView.findViewById(R.id.imageViewHinh);
        //Gan gia tri
        TraiCay traiCay = traiCayList.get(position);
        txtTen.setText(traiCay.getTen());
        txtMota.setText(traiCay.getMota());
        if (traiCay.getHinh() != 0) {
            imageHinh.setImageResource(traiCay.getHinh());
        } else {
            String imageUrl = traiCay.getURLHinh();
            Picasso.get()
                    .load(imageUrl)
                    .into(imageHinh);
        }
        return convertView;
    }
}
