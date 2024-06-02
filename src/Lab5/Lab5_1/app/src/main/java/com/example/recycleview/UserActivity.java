package com.example.recycleview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);

        RecyclerView rvUser = findViewById(R.id.rvUser);

        //init data
        userList = new ArrayList<>();
        userList.add(new User("NguyenTT", "Tran Thanh Nguyen", "Nguyentt@ftp.edu.vn"));
        userList.add(new User("Antv", "Tran Van An", "antv@gmail.com"));
        userList.add(new User("BangTT", "Tran Thanh Bang", "bangtt@gmail.com"));
        userList.add(new User("KhangTT", "Tran Thanh Khang", "khangtt@gmail.com"));
        userList.add(new User("BaoNT", "Nguyen Thanh Bao", "baott@gmail.com"));
        userList.add(new User("HungVH", "Vo Huy Hung", "hungvh@gmail.com"));

        UserAdapter adapter = new UserAdapter(userList);

        rvUser.setAdapter(adapter);

        rvUser.setLayoutManager(new LinearLayoutManager(this));
    }
}