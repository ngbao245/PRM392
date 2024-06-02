package com.example.lab5_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RAMActivity extends AppCompatActivity implements RAMAdapter.OnItemClickListener {
    ArrayList<RAM> RAMList;
    RecyclerView rvRAM;
    RAMAdapter adapter;
    int position = -1;

    Button btnAdd, btnUpdate;
    EditText etName, etBrand, etDescription, etURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ram);

        mapping();
        onCreateData();

        adapter = new RAMAdapter(RAMList, this);

        rvRAM.setAdapter(adapter);
        rvRAM.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    RAMList.add(new RAM(
                            etName.getText().toString(),
                            etDescription.getText().toString(),
                            etBrand.getText().toString(),
                            etURL.getText().toString()));
                    adapter.notifyItemInserted(RAMList.size() - 1);
                    finished();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    if (position == -1) {
                        Toast.makeText(RAMActivity.this, "PLEASE CHOOSE ITEM", Toast.LENGTH_SHORT).show();
                    } else {
                        RAMList.set(position, new RAM(
                                etName.getText().toString(),
                                etDescription.getText().toString(),
                                etBrand.getText().toString(),
                                etURL.getText().toString()));
                        adapter.notifyDataSetChanged();
                        finished();
                    }
                }
            }
        });
    }

    private void finished() {
        position = -1;
        etName.setText("");
        etBrand.setText("");
        etDescription.setText("");
        etURL.setText("");
    }

    private boolean checkValid() {
        if (etName.getText().toString().isEmpty() || etBrand.getText().toString().isEmpty() || etDescription.getText().toString().isEmpty() || etURL.getText().toString().isEmpty()) {
            Toast.makeText(RAMActivity.this, "FIELD REQUIRED", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemClick(int position) {
        etName.setText(RAMList.get(position).getName());
        etDescription.setText(RAMList.get(position).getDescription());
        etBrand.setText(RAMList.get(position).getBrand());
        etURL.setText(RAMList.get(position).getImageURL());
        this.position = position;
        Toast.makeText(this, "Item: " + RAMList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(int position) {
        RAMList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, RAMList.size());
    }

    private void mapping() {
        rvRAM = findViewById(R.id.rvRAM);
        etName = findViewById(R.id.et_name);
        etBrand = findViewById(R.id.et_brand);
        etDescription = findViewById(R.id.et_description);
        etURL = findViewById(R.id.et_url);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void onCreateData() {
        RAMList = new ArrayList<>();
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
        RAMList.add(new RAM("RAM Laptop Samsung 8GB DDR4 3200MHz", "8GB Loại RAM DDR4 Bus RAM 3200MHz Hỗ trợ SO-DIMM (Laptop) Voltage 1.2v", "Samsung", R.drawable.ram1));
    }
}
