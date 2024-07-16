package com.example.bookmanagement_njs1701_se172266;

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

import com.example.bookmanagement_njs1701_se172266.adapter.BookAdapter;
import com.example.bookmanagement_njs1701_se172266.api.ApiService;
import com.example.bookmanagement_njs1701_se172266.api.BookRepository;
import com.example.bookmanagement_njs1701_se172266.model.Author;
import com.example.bookmanagement_njs1701_se172266.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView lvManagement;
    private BookAdapter bookAdapter;
    private ApiService apiService;
    List<Book> bookList;
    Button btnAdd, btnUpdate;
    EditText etName, etDate, etType, etAuthorId;
    long bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvManagement = findViewById(R.id.listView);
        bookList = new ArrayList<>();
        Map<Long, String> authorMap = new HashMap<>();
        bookAdapter = new BookAdapter(this, bookList, authorMap);
        lvManagement.setAdapter(bookAdapter);


        apiService = BookRepository.getBookService();

        fetchBooksAndAuthors();

        btnAdd = findViewById(R.id.Add);
        btnUpdate = findViewById(R.id.Update);
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etType = findViewById(R.id.etType);
        etAuthorId = findViewById(R.id.etAuthorId);

        lvManagement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bookList.size() > position) { // Check if position is valid
                    Book selected = bookList.get(position);
                    etName.setText(selected.getName());
                    etDate.setText(selected.getDate().toString());
                    etType.setText(selected.getType());
                    etAuthorId.setText(String.valueOf(selected.getAuthorId()));

                    bookId = selected.getId();
                } else {
                    // Handle error or log the issue
                    Log.e("ListView", "Invalid position clicked: " + position);
                }
            }
        });

        lvManagement.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Book selected = bookList.get(position);
                Call<Book> call = apiService.deleteBook(selected.getId());

                call.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.body() != null) {
                            Toast.makeText(MainActivity.this, "Delete successfully!",
                                    Toast.LENGTH_LONG).show();
                            fetchBooksAndAuthors();
                            reset();
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to delete",
                                Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String date = etDate.getText().toString();
                String type = etType.getText().toString();
                long authorId = Long.parseLong(etAuthorId.getText().toString());

                Book book = new Book(name, type, date, authorId);
                try {
                    Call<Book> call = apiService.addBook(book);
                    call.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            if (response.body() != null) {
                                Toast.makeText(MainActivity.this, "Add successfully",
                                        Toast.LENGTH_LONG).show();
                                fetchBooksAndAuthors();
                                reset();
                            }
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Add failed"
                                    , Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String date = etDate.getText().toString();
                String type = etType.getText().toString();
                long authorId = Long.parseLong(etAuthorId.getText().toString());

                try {
                    Book book = new Book(name, type, date, authorId);
                    Call<Book> call = apiService.updateBook(bookId, book);
                    call.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            Toast.makeText(MainActivity.this, "Update successfully",
                                    Toast.LENGTH_LONG).show();
                            fetchBooksAndAuthors();
                            reset();
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Update failed"
                                    , Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void fetchBooksAndAuthors() {
        Call<List<Book>> callBooks = apiService.getAllBooksents();
        Call<List<Author>> callAuthors = apiService.getAllAuthors();

        callBooks.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> bookResponse) {
                if (bookResponse.isSuccessful() && bookResponse.body() != null) {
                    List<Book> books = bookResponse.body();
                    callAuthors.enqueue(new Callback<List<Author>>() {
                        @Override
                        public void onResponse(Call<List<Author>> call, Response<List<Author>> authorResponse) {
                            if (authorResponse.isSuccessful() && authorResponse.body() != null) {
                                Map<Long, String> authorMap = new HashMap<>();
                                for (Author author : authorResponse.body()) {
                                    authorMap.put(author.getId(), author.getName());
                                }
                                bookList.clear();

                                List<Book> booksWithAuthors = new ArrayList<>();

                                for (Book book : books) {
                                    booksWithAuthors.add(book);
                                    bookList.add(book);
                                }
                                displayBooks(booksWithAuthors, authorMap);
                            } else {
                                showError("Failed to fetch authors");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Author>> call, Throwable t) {
                            showError("Failed to fetch authors");
                        }
                    });
                } else {
                    showError("Failed to fetch books");
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                showError("Failed to fetch books");
            }
        });
    }

    private void displayBooks(List<Book> books, Map<Long, String> authorMap) {
        bookAdapter = new BookAdapter(MainActivity.this, books, authorMap);
        lvManagement.setAdapter(bookAdapter);
    }

    private void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
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
        etName.setText("");
        etDate.setText("");
        etType.setText("");
        etAuthorId.setText("");
        bookId = -1;
    }
}