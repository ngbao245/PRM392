package com.example.lab4_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class second_activity extends Activity {
    AppCompatButton btnSecondActivity;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "onStart SecondActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "onRestart SecondActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "onResume SecondActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "onPause SecondActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "onStop SecondActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "onDestroy SecondActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnSecondActivity = (AppCompatButton) findViewById(R.id.button2);
        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AAA", "Clicked Second Activity Button");
                Intent intent = new Intent(second_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Log.d("AAA", "onCreate SecondActivity");
    }
}