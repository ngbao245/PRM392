package com.example.feedbackmanagementsystem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackmanagementsystem.API.TraineeRepository;
import com.example.feedbackmanagementsystem.API.TraineeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TraineeService traineeService;
    EditText etName, etEmail, etPhone, etGender;
    Button btnSave, btnUpdate, btnDelete;

    RecyclerView recyclerView;
    TraineeAdapter traineeAdapter;
    ArrayList<Trainee> traineeList = new ArrayList<>();

    int selectedTraineeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etGender = findViewById(R.id.etGender);
        btnSave = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });

        traineeService = TraineeRepository.getTraineeService();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        traineeAdapter = new TraineeAdapter(traineeList);
        recyclerView.setAdapter(traineeAdapter);

        loadTrainees();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Trainee selectedTrainee = traineeList.get(position);
                selectedTraineeId = (int) selectedTrainee.getId();
                etName.setText(selectedTrainee.getName());
                etEmail.setText(selectedTrainee.getEmail());
                etPhone.setText(selectedTrainee.getPhone());
                etGender.setText(selectedTrainee.getGender());
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // Do nothing for now
            }
        }));
    }

    private void loadTrainees() {
        Call<List<Trainee>> call = traineeService.getALLTrainees();
        call.enqueue(new Callback<List<Trainee>>() {
            @Override
            public void onResponse(Call<List<Trainee>> call, Response<List<Trainee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    traineeList.clear();
                    traineeList.addAll(response.body());
                    traineeAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Response unsuccessful or body is null");
                    Toast.makeText(MainActivity.this, "Failed to load trainees", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Trainee>> call, Throwable t) {
                Log.e(TAG, "Failed to load trainees", t);
                Toast.makeText(MainActivity.this, "Load Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Save() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = etGender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);
        try {
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                        traineeList.add(response.body());
                        traineeAdapter.notifyItemInserted(traineeList.size() - 1);
                        clearEditTextFields();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void Update() {
        if (selectedTraineeId == -1) {
            Toast.makeText(MainActivity.this, "Select a trainee to update", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = etGender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);
        trainee.setId(selectedTraineeId);

        Call<Trainee> call = traineeService.updateTrainee(selectedTraineeId, trainee);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                if (response.body() != null) {
                    Toast.makeText(MainActivity.this, "Update Successfully", Toast.LENGTH_LONG).show();
                    int index = findTraineeIndexById(selectedTraineeId);
                    if (index != -1) {
                        traineeList.set(index, response.body());
                        traineeAdapter.notifyItemChanged(index);
                        clearEditTextFields();
                    }
                }
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Update Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Delete() {
        if (selectedTraineeId == -1) {
            Toast.makeText(MainActivity.this, "Select a trainee to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Void> call = traineeService.deleteTrainee(selectedTraineeId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                int index = findTraineeIndexById(selectedTraineeId);
                if (index != -1) {
                    traineeList.remove(index);
                    traineeAdapter.notifyItemRemoved(index);
                    clearEditTextFields();
                }
                selectedTraineeId = -1;  // Reset selectedTraineeId
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    private int findTraineeIndexById(int id) {
        for (int i = 0; i < traineeList.size(); i++) {
            if (traineeList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void clearEditTextFields() {
        etName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etGender.setText("");
        selectedTraineeId = -1;
    }
}
