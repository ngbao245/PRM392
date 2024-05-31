package com.example.lab3_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayListTraiCay;
    TraiCayAdapter adapter;
    Button btnCreate, btnUpdate, btnDelete;
    EditText edtTen, edtMota, edtURLHinh;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AnhXa();
        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, arrayListTraiCay);
        lvTraiCay.setAdapter(adapter);

        lvTraiCay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtTen.setText(arrayListTraiCay.get(position).getTen());
                edtMota.setText(arrayListTraiCay.get(position).getMota());
                edtURLHinh.setText(arrayListTraiCay.get(position).getURLHinh());
                index = position;
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    arrayListTraiCay.add(new TraiCay(edtTen.getText().toString(), edtMota.getText().toString(), edtURLHinh.getText().toString()));
                    adapter.notifyDataSetChanged();
                    finishAction();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    if (index == 0) {
                        Toast.makeText(MainActivity.this, "Vui lòng chọn trái cây cần cập nhật", Toast.LENGTH_LONG).show();
                    } else {
                        arrayListTraiCay.set(index, new TraiCay(edtTen.getText().toString(), edtMota.getText().toString(), edtURLHinh.getText().toString()));
                        adapter.notifyDataSetChanged();
                        finishAction();
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn trái cây cần xóa", Toast.LENGTH_LONG).show();
                } else {
                    arrayListTraiCay.remove(index);
                    adapter.notifyDataSetChanged();
                    finishAction();
                }
            }
        });


    }

    private void finishAction() {
        index = 0;
        edtTen.setText("");
        edtMota.setText("");
        edtURLHinh.setText("");
    }

    private boolean checkValid() {
        if (edtTen.getText().toString().isEmpty() || edtMota.getText().toString().isEmpty() || edtURLHinh.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin trái cây", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void AnhXa() {
        lvTraiCay = (ListView) findViewById(R.id.lvTraiCay);
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        edtTen = (EditText) findViewById(R.id.editTen);
        edtMota = (EditText) findViewById(R.id.editMota);
        edtURLHinh = (EditText) findViewById(R.id.editTextImageURL);

        arrayListTraiCay = new ArrayList<TraiCay>();
        int result = R.drawable.saurieng;
        arrayListTraiCay.add(new TraiCay("Chuoi Tieu", "Chuoi tieu Long An", R.drawable.chuoi));
        arrayListTraiCay.add(new TraiCay("Sau Rieng", "Sau rieng buon", R.drawable.saurieng));
        arrayListTraiCay.add(new TraiCay("Mit", "Mit rat la thui", R.drawable.mit));
        arrayListTraiCay.add(new TraiCay("Dua Hau", "Dua hau dau hua", R.drawable.duahau));
        arrayListTraiCay.add(new TraiCay("Quyt", "Quyt chu khong phai quyet", R.drawable.quyet));
    }
}