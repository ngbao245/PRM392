package com.example.lab6;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    AppCompatButton btnPopup, btnPickColor;
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopup = (AppCompatButton) findViewById(R.id.btnPopup);
        btnPickColor = (AppCompatButton) findViewById(R.id.btnPickColor);
        main = (ConstraintLayout) findViewById(R.id.main);

        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });

        registerForContextMenu(btnPickColor);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRed:
                main.setBackgroundColor(Color.RED);
                break;
            case R.id.menuBlue:
                main.setBackgroundColor(Color.BLUE);
                break;
            case R.id.menuYellow:
                main.setBackgroundColor(Color.YELLOW);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void ShowMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnPopup);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuAdd:
                        btnPopup.setText("Menu Add");
                        Toast.makeText(MainActivity.this, "Add clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuUpdate:
                        btnPopup.setText("Menu Update");
                        Toast.makeText(MainActivity.this, "Update clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuDelete:
                        btnPopup.setText("Menu Delete");
                        Toast.makeText(MainActivity.this, "Delete clicked!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mExit:
                Toast.makeText(this, "Exit clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mSearch:
                Toast.makeText(this, "Search clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mFind:
                Toast.makeText(this, "Find clicked!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}