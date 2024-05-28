package com.example.lab4_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AppCompatButton btnString;
    AppCompatButton btnNumber;
    AppCompatButton btnArray;
    AppCompatButton btnObject;
    AppCompatButton btnBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnString = (AppCompatButton) findViewById(R.id.btnSendString);
        btnString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, receive_data.class);
                intent.putExtra("DataString", "Send data type string");
                startActivity(intent);
            }
        });

        btnNumber = (AppCompatButton) findViewById(R.id.btnSendNumber);
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, receive_data.class);
                intent.putExtra("DataNumber", 2003);
                startActivity(intent);
            }
        });
        btnArray = (AppCompatButton) findViewById(R.id.btnSendArray);
        btnArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, receive_data.class);
                String[] dataArray = {"Hao", "Bao", "Nghia"};
                intent.putExtra("DataArray", dataArray);
                startActivity(intent);
            }
        });

        btnObject = (AppCompatButton) findViewById(R.id.btnSendObject);
        btnObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, receive_data.class);
                Student student = new Student("Hoang Bao", 21, "Software Engineering", false);
                intent.putExtra("DataObject", (Serializable) student);
                startActivity(intent);
            }
        });

        btnBundle = (AppCompatButton) findViewById(R.id.btnSendBundle);
        btnBundle.setOnClickListener(new View.OnClickListener() {
            String[] bundleArray = {"Apple", "Banana", "Orange"};
            Student bundleStudent = new Student("BaoBiBo", 69, "Deisnging", true);

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, receive_data.class);
                Bundle bundle = new Bundle();
                bundle.putString("bundleString", "Send Bundle String");
                bundle.putInt("bundleInt", 6969);
                bundle.putStringArray("bundleArray", bundleArray);
                bundle.putSerializable("bundleObject", bundleStudent);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}