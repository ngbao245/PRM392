package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
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
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTen = (TextView) view.findViewById(R.id.texviewTen);
            viewHolder.imgDelete = (ImageView) view.findViewById(R.id.imageviewDelete);
            viewHolder.imgEdit = (ImageView) view.findViewById(R.id.imageviewEdit);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CongViec congViec = congViecList.get(position);
        viewHolder.txtTen.setText(congViec.getTenCV());

        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView txtTen;
        ImageView imgDelete, imgEdit;
    }
}
