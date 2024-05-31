package com.example.lab3_1;

import static com.example.lab3_1.MainActivity.isNullOrEmpty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MemberInfoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<MemberInfo> memberInfoArrayList;

    public MemberInfoAdapter(Context context, int layout, ArrayList<MemberInfo> memberInfoArrayList) {
        this.context = context;
        this.layout = layout;
        this.memberInfoArrayList = memberInfoArrayList;
    }

    @Override
    public int getCount() {
        return memberInfoArrayList.size();
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
        //Anh xa view
        TextView txtName = convertView.findViewById(R.id.textViewName);
        TextView txtDescription = convertView.findViewById(R.id.textViewDescription);
        ImageView imgMember = convertView.findViewById(R.id.imageViewMember);
        ImageView imgFlag = convertView.findViewById(R.id.imageViewFlat);

        MemberInfo memberInfo = memberInfoArrayList.get(position);
        txtName.setText(memberInfo.getName());
        txtDescription.setText(memberInfo.getDescription());
        String imageURL = "";
        if (memberInfo.getImageMember() != 0) {
            imgMember.setImageResource(memberInfo.getImageMember());
        } else {
            if (!isNullOrEmpty(memberInfo.getURLImageMember())) {
                imageURL = memberInfo.getURLImageMember();
                Picasso.get()
                        .load(imageURL)
                        .into(imgMember);
            }

        }
        if (memberInfo.getImageFlat() != 0) {
            imgFlag.setImageResource(memberInfo.getImageFlat());
        } else {
            if (!isNullOrEmpty(memberInfo.getURLImageFlat())) {
                imageURL = memberInfo.getURLImageFlat();
                Picasso.get()
                        .load(imageURL)
                        .into(imgFlag);
            }

        }

        return convertView;
    }


}
