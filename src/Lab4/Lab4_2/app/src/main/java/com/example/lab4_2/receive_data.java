package com.example.lab4_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class receive_data extends AppCompatActivity {
    TextView txtResultString;
    TextView txtResultNumber;
    TextView txtResultArray;
    TextView txtResultObject;
    TextView txtResultBundle;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receive_data);

        layout = (LinearLayout) findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(receive_data.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        txtResultString = (TextView) findViewById(R.id.tvString);
        //Nhan Du lieu tu Main
        String dataString = intent.getStringExtra("DataString");
        if (dataString != null && !dataString.isEmpty()) {
            txtResultString.setText(dataString);
        }
        txtResultNumber = (TextView) findViewById(R.id.tvNumber);
        int dataNumber = intent.getIntExtra("DataNumber", 0);
        if (dataNumber != 0) {
            txtResultNumber.setText("" + dataNumber);
        }

        txtResultArray = (TextView) findViewById(R.id.tvArray);
        String[] dataArray = intent.getStringArrayExtra("DataArray");
        if (dataArray != null && dataArray.length > 0) {
            StringBuilder result = new StringBuilder();
            for (String item : dataArray) {
                result.append(item).append("\n");
            }
            txtResultArray.setText(result.toString());
        }

        TextView txtResultObject = findViewById(R.id.tvObject);
        Student dataStudent = (Student) intent.getSerializableExtra("DataObject");

        if (dataStudent != null) {
            String result = "Name: " + dataStudent.getName() + "\n" +
                    "Age: " + dataStudent.getAge() + "\n" +
                    "Major: " + dataStudent.getMajor() + "\n" +
                    "Graduated: " + (dataStudent.isGraduate() ? "Yes" : "No");
            txtResultObject.setText(result);
        }

        txtResultBundle = (TextView) findViewById(R.id.tvBundle);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String bundleString = bundle.getString("bundleString");
            int bundleInt = bundle.getInt("bundleInt");
            String[] bundleArray = bundle.getStringArray("bundleArray");
            Student bundleObject = (Student) bundle.getSerializable("bundleObject");

            //display bundle array
            StringBuilder array = new StringBuilder();
            for (String item : bundleArray) {
                array.append(item).append(" ");
            }

            //display bundle object
            String object = "Name: " + bundleObject.getName() + "\n" +
                    "Age: " + bundleObject.getAge() + "\n" +
                    "Major: " + bundleObject.getMajor() + "\n" +
                    "Graduated: " + (bundleObject.isGraduate() ? "Yes" : "No");

            String result = "Bundle String: " + bundleString + "\n" +
                    "Bundle Int: " + bundleInt + "\n" +
                    "Bundle Array: " + array + "\n" +
                    "Bundle Object: " + object;
            txtResultBundle.setText(result);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}