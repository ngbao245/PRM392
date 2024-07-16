package com.example.bookmanagement_njs1701_se172266.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagement_njs1701_se172266.R;
import com.example.bookmanagement_njs1701_se172266.model.Author;

import java.util.List;

public class AuthorAdapter extends ArrayAdapter<Author> {
    public AuthorAdapter(Context context, List<Author> authors) {
        super(context, 0, authors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Author author = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_author, parent, false);
        }

        TextView tvAuthorId = convertView.findViewById(R.id.tvId);
        TextView tvAuthorName = convertView.findViewById(R.id.tvAuthorName);
        TextView tvAuthorEmail = convertView.findViewById(R.id.tvAuthorEmail);
        TextView tvAuthorAddress = convertView.findViewById(R.id.tvAddress);
        TextView tvAuthorPhone = convertView.findViewById(R.id.tvPhone);


        tvAuthorId.setText("Id: " + author.getId());
        tvAuthorName.setText("Name: " + author.getName());
        tvAuthorEmail.setText("Email: " + author.getEmail());
        tvAuthorAddress.setText("Address: " + author.getAddress());
        tvAuthorPhone.setText("Phone: " + author.getPhone());

        return convertView;
    }
}
