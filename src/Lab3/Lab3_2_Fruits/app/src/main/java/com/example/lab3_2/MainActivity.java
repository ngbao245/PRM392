package com.example.lab3_2;

import android.os.Bundle;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getAnhXa();
        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay,arrayListTraiCay);
        lvTraiCay.setAdapter(adapter);
    }

    private void getAnhXa() {
        lvTraiCay = (ListView) findViewById(R.id.listViewTraiCay);
        arrayListTraiCay = new ArrayList<>();
        arrayListTraiCay.add(new TraiCay("Chuoi Tieu", "Chuoi tieu Long An", R.drawable.chuoi));
        arrayListTraiCay.add(new TraiCay("Sau Rieng", "Sau rieng buon", R.drawable.saurieng));
        arrayListTraiCay.add(new TraiCay("Mit", "Mit rat la thui", R.drawable.mit));
        arrayListTraiCay.add(new TraiCay("Dua Hau", "Dua hau dau hua", R.drawable.duahau));
        arrayListTraiCay.add(new TraiCay("Quyt", "Quyt chu khong phai quyet", R.drawable.quyet));
    }
}