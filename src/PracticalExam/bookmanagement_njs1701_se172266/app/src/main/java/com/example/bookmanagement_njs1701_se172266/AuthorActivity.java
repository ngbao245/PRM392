package com.example.bookmanagement_njs1701_se172266;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookmanagement_njs1701_se172266.adapter.AuthorAdapter;
import com.example.bookmanagement_njs1701_se172266.api.ApiService;
import com.example.bookmanagement_njs1701_se172266.api.BookRepository;
import com.example.bookmanagement_njs1701_se172266.model.Author;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorActivity extends AppCompatActivity {
    private ApiService apiService;
    ListView lvAuthor;
    AuthorAdapter adapter;
    List<Author> authorList;

    Button btnAddAuthor, btnUpdateAuthor;
    EditText etAuthorName, etAuthorEmail, etAuthorAddress, etAuthorPhone;
    long AuthorId;

    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author selected = null;
                for (Author author : authorList) {
                    if (author.getId() == AuthorId) {
                        selected = author;
                        break;
                    }
                }

                if (selected != null) {
                    Intent intent = new Intent(AuthorActivity.this, MapsActivity.class);
                    intent.putExtra("address", selected.getAddress());
                    startActivity(intent);
                } else {
                    Toast.makeText(AuthorActivity.this, "Student not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvAuthor = findViewById(R.id.listView2);
        authorList = new ArrayList<>();
        adapter = new AuthorAdapter(this, authorList);
        lvAuthor.setAdapter(adapter);

        apiService = BookRepository.getBookService();

        fetchAuthor();

        btnAddAuthor = findViewById(R.id.btnAdd);
        btnUpdateAuthor = findViewById(R.id.btnUpdate);
        etAuthorName = findViewById(R.id.etAuthorName);
        etAuthorEmail = findViewById(R.id.etAuthorEmail);
        etAuthorAddress = findViewById(R.id.etAuthorAddress);
        etAuthorPhone = findViewById(R.id.etAuthorPhone);

        lvAuthor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Author selected = authorList.get(position);
                etAuthorName.setText(selected.getName());
                etAuthorEmail.setText(selected.getEmail());
                etAuthorAddress.setText(selected.getAddress());
                etAuthorPhone.setText(selected.getPhone());

                AuthorId = selected.getId();
            }
        });

        lvAuthor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Author selected = authorList.get(position);
                Call<Author> call = apiService.deleteAuthor(selected.getId());

                call.enqueue(new Callback<Author>() {
                    @Override
                    public void onResponse(Call<Author> call, Response<Author> response) {
                        if (response.body() != null) {
                            Toast.makeText(AuthorActivity.this, "Delete successfully!",
                                    Toast.LENGTH_LONG).show();
                            fetchAuthor();
                            reset();
                        }
                    }

                    @Override
                    public void onFailure(Call<Author> call, Throwable t) {
                        Toast.makeText(AuthorActivity.this, "Failed to delete",
                                Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });

        btnAddAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etAuthorName.getText().toString();
                String email = etAuthorEmail.getText().toString();
                String address = etAuthorAddress.getText().toString();
                String phone = etAuthorPhone.getText().toString();

                Author author = new Author(name, email, address, phone);
                try {
                    Call<Author> call = apiService.addAuthor(author);
                    call.enqueue(new Callback<Author>() {
                        @Override
                        public void onResponse(Call<Author> call, Response<Author> response) {
                            if (response.body() != null) {
                                Toast.makeText(AuthorActivity.this, "Add successfully",
                                        Toast.LENGTH_LONG).show();
                                fetchAuthor();
                                reset();
                            }
                        }

                        @Override
                        public void onFailure(Call<Author> call, Throwable t) {
                            Toast.makeText(AuthorActivity.this, "Add failed"
                                    , Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }
            }
        });

        btnUpdateAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etAuthorName.getText().toString();
                String email = etAuthorEmail.getText().toString();
                String address = etAuthorAddress.getText().toString();
                String phone = etAuthorPhone.getText().toString();

                Author author = new Author(name, email, address, phone);
                Call<Author> call = apiService.updateAuthor(AuthorId, author);

                call.enqueue(new Callback<Author>() {
                    @Override
                    public void onResponse(Call<Author> call, Response<Author> response) {
                        Toast.makeText(AuthorActivity.this, "Update successfully",
                                Toast.LENGTH_LONG).show();
                        fetchAuthor();
                        reset();
                    }

                    @Override
                    public void onFailure(Call<Author> call, Throwable t) {
                        Toast.makeText(AuthorActivity.this, "Update failed"
                                , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void fetchAuthor() {
        Call<List<Author>> call = apiService.getAllAuthors();
        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                List<Author> authors = response.body();
                if (authors == null) {
                    return;
                }
                authorList.clear();
                for (Author author : authors) {
                    authorList.add(author);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Toast.makeText(AuthorActivity.this, "Failed to fetch authors",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        com.example.bookmanagement_njs1701_se172266.Menu.onCreateOptionsMenu(this, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (com.example.bookmanagement_njs1701_se172266.Menu.onOptionsItemSelected(this, item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset() {
        etAuthorName.setText("");
        etAuthorEmail.setText("");
        etAuthorAddress.setText("");
        etAuthorPhone.setText("");
        AuthorId = -1;
    }
}
