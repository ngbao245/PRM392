package com.example.lab4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab4_1.Adapter.FoodAdapter;
import com.example.lab4_1.Entities.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class ordering_food extends AppCompatActivity {
    ListView lvFood;
    ArrayList<Food> foodArrayList;
    FoodAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ordering_food);

        mapping();
        adapter = new FoodAdapter(this, R.layout.list_view_format, foodArrayList);
        lvFood.setAdapter(adapter);

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food selectedFood = foodArrayList.get(position);
                String currentSummary = getIntent().getStringExtra("OrderSummary");
                if (currentSummary == null) {
                    currentSummary = "";
                }
                currentSummary += "- Food: " + selectedFood.getName() + "-" + selectedFood.getMoney() + " VND\n";
                Intent intent = new Intent(ordering_food.this, ordering_layout.class);
                intent.putExtra("OrderSummary", currentSummary);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        lvFood = findViewById(R.id.listViewFood);
        foodArrayList = new ArrayList<>();
        foodArrayList.add(new Food("Phở Hà Nội", 50000, R.drawable.pho));
        foodArrayList.add(new Food("Bún Bò Huế", 65000, R.drawable.bunbo));
        foodArrayList.add(new Food("Mì Quảng", 40000, R.drawable.miquang));
        foodArrayList.add(new Food("Hủ Tíu Sài Gòn", 60000, R.drawable.hutieu));
    }
}