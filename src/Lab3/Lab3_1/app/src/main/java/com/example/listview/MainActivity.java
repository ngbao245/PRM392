package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMonHoc;
    ArrayList<String> arraycourse;
    AppCompatButton btnAdd;
    AppCompatButton btnRemove;
    AppCompatButton btnUpdate;
    EditText editText;
    int vitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lvMonHoc = (ListView) findViewById(R.id.lvMonHoc);
        btnAdd = (AppCompatButton) findViewById(R.id.btnAdd);
        btnRemove = (AppCompatButton) findViewById(R.id.btnRemove);
        btnUpdate = (AppCompatButton) findViewById(R.id.btnUpdate);
        editText = (EditText) findViewById(R.id.editText);
        arraycourse = new ArrayList<>();
        vitri = -1;

        //Add data
        arraycourse = new ArrayList<>();
        arraycourse.add("Android");
        arraycourse.add("PHP");
        arraycourse.add("IOS");
        arraycourse.add("Unity");
        arraycourse.add("ASP.net");
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1, //listview có nhiều format
                arraycourse
        );
        lvMonHoc.setAdapter(adapter);

        //target
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(arraycourse.get(position));
                vitri = position;
                //Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, arraycourse.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //hold to delete
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(arraycourse.get(position));
                vitri = position;

                String removedItem = arraycourse.get(vitri);
                arraycourse.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted: " + removedItem, Toast.LENGTH_SHORT).show();
                vitri = -1;
                editText.setText("");
                return false;
            }
        });

        //add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHoc = editText.getText().toString();
                if (!arraycourse.contains(monHoc)) {
                    arraycourse.add(monHoc);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Added: " + monHoc, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, monHoc + " already exists.", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
                vitri = -1;
            }
        });

        //remove
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHoc = editText.getText().toString();
                if (arraycourse.contains(monHoc) && vitri != -1) {
                    String removedItem = arraycourse.get(vitri);
                    arraycourse.remove(vitri);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Deleted: " + removedItem, Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    vitri = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Choose an item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vitri != -1) {
                    arraycourse.set(vitri, editText.getText().toString());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Updated: " + arraycourse.get(vitri), Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    vitri = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Choose an item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}