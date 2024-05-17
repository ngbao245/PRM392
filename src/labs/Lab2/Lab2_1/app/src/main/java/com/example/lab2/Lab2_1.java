package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Lab2_1 extends AppCompatActivity {
    //Khai bao bien
    Random random = new Random();
    AppCompatButton btnGenerate;
    TextView txtResult;
    EditText minNumber;
    EditText maxNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lab2_1);

        // ánh xạ
        txtResult = findViewById(R.id.txtResult);
        btnGenerate = findViewById(R.id.btnGenerate);
        minNumber = findViewById(R.id.minNumber); // Add this line to initialize minNumber EditText
        maxNumber = findViewById(R.id.maxNumber); // Add this line to initialize maxNumber EditText

        // code
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate
                int min, max;
                try {
                    min = Integer.parseInt(minNumber.getText().toString());
                    max = Integer.parseInt(maxNumber.getText().toString());
                } catch (NumberFormatException e) {
                    txtResult.setText("Please enter number");
                    return;
                }
                if (min > max) {
                    txtResult.setText("minNumber must be less than maxNumber");
                    return;
                } else {
                    txtResult.setText("Result: " + generateRandom(min, max));
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lab2_1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private int generateRandom(int min, int max) {
        return random.nextInt((max - min + 1)) + min;
    }
}