package com.example.lab2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lab2_2 extends AppCompatActivity {
    //Khai báo
    TextView result;
    AppCompatButton btnPlus;
    AppCompatButton btnMinus;
    AppCompatButton btnMultiply;
    AppCompatButton btnDivide;
    EditText firstNum;
    EditText secondNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lab2_2);

        // ánh xạ
        result = findViewById(R.id.txtResult);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        firstNum = findViewById(R.id.firstNum);
        secondNum = findViewById(R.id.secondNum);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // bắt sự kiện
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }

    private void calculate(char operator) {
        int num1 = Integer.parseInt(firstNum.getText().toString());
        int num2 = Integer.parseInt(secondNum.getText().toString());
        int resultValue = 0;

        switch (operator) {
            case '+':
                resultValue = plus(num1, num2);
                break;
            case '-':
                resultValue = minus(num1, num2);
                break;
            case '*':
                resultValue = multiply(num1, num2);
                break;
            case '/':
                resultValue = divide(num1, num2);
                if(num2 == 0) {
                    result.setText("cannot divide by 0");
                    return;
                }
                break;
        }
        result.setText("Result: " + resultValue);
    }

    private int plus(int a, int b) {
        return a + b;
    }

    private int minus(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            return 0;
        }
    }
}
