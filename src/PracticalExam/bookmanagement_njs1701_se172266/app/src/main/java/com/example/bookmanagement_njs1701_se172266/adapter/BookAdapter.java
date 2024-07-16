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
import com.example.bookmanagement_njs1701_se172266.model.Book;

import java.util.List;
import java.util.Map;

public class BookAdapter extends ArrayAdapter<Book> {
    private Map<Long, String> authorMap;

    public BookAdapter(Context context, List<Book> books, Map<Long, String> authorMap) {
        super(context, 0, books);
        this.authorMap = authorMap;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        Book book = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvType = convertView.findViewById(R.id.tvType);
        TextView tvAuthorName = convertView.findViewById(R.id.tvAuthorName);

        tvName.setText("Name: " + book.getName());
        tvDate.setText("Date: " + book.getDate());
        tvType.setText("Type: " + book.getType());

        if (authorMap != null && authorMap.containsKey(book.getAuthorId())) {
            tvAuthorName.setText("Author: " + authorMap.get(book.getAuthorId()));
        } else {
            tvAuthorName.setText("Author: N/A");
        }
        return convertView;
    }
}
