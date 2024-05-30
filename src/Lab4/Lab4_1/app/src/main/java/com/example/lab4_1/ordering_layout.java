package com.example.lab4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab4_1.Entities.Drink;
import com.example.lab4_1.Entities.Food;

import java.util.ArrayList;

public class ordering_layout extends AppCompatActivity {
    AppCompatButton btnQuit;
    ImageView btnFood, btnDrink;
    TextView txtOrderSummary;
    ArrayList<Food> orderedFoodItems = new ArrayList<>();
    ArrayList<Drink> orderedDrinkItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ordering_layout);

        btnQuit = (AppCompatButton) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ordering_layout.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFood = (ImageView) findViewById(R.id.btnFood);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ordering_layout.this, ordering_food.class);
                intent.putExtra("OrderSummary", getIntent().getStringExtra("OrderSummary"));
                startActivity(intent);
            }
        });

        btnDrink = (ImageView) findViewById(R.id.btnDrink);
        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ordering_layout.this, ordering_drink.class);
                intent.putExtra("OrderSummary", getIntent().getStringExtra("OrderSummary"));
                startActivity(intent);
            }
        });

        txtOrderSummary = findViewById(R.id.tvOrderSummary);
        String orderSummary = getIntent().getStringExtra("OrderSummary");
        if (orderSummary == null) {
            orderSummary = "Your orders are:\n";
        }
        txtOrderSummary.setText(orderSummary);
    }
}
