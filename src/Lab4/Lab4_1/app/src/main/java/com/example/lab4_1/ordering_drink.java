package com.example.lab4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.lab4_1.Adapter.DrinkAdapter;
import com.example.lab4_1.Entities.Drink;

import java.io.Serializable;
import java.util.ArrayList;

public class ordering_drink extends AppCompatActivity {
    ListView lvDrink;
    ArrayList<Drink> drinkArrayList;
    DrinkAdapter adapter;
    Drink selectedDrink;
    AppCompatButton btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ordering_drink);

        mapping();
        adapter = new DrinkAdapter(this, R.layout.list_view_format, drinkArrayList);
        lvDrink.setAdapter(adapter);

        lvDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDrink = drinkArrayList.get(position);
            }
        });

        btnOrder = (AppCompatButton) findViewById(R.id.button);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentSummary = getIntent().getStringExtra("OrderSummary");
                if (currentSummary == null) {
                    currentSummary = "";
                }
                currentSummary += "- Drink: " + selectedDrink.getName() + "-" + selectedDrink.getMoney() + " VND\n";
                Intent intent = new Intent(ordering_drink.this, ordering_layout.class);
                intent.putExtra("OrderSummary", currentSummary);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        lvDrink = findViewById(R.id.listViewDrink);
        drinkArrayList = new ArrayList<>();
        drinkArrayList.add(new Drink("Pepsi", 15000, R.drawable.pepsi));
        drinkArrayList.add(new Drink("Heineken", 25000, R.drawable.heineken));
        drinkArrayList.add(new Drink("Tiger", 30000, R.drawable.tiger));
        drinkArrayList.add(new Drink("Sài gòn Đỏ ", 20000, R.drawable.saigondo));
    }
}