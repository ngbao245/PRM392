package com.example.roomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.roomdatabase.adapter.AppDatabase;
import com.example.roomdatabase.constants.Constants;
import com.example.roomdatabase.model.Person;

public class EditPersonActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private Button btnSave;
    private int mPersonId;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        initViews();

        // Initialize Room database instance
        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        // Check if intent has extra with person ID
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            // Set button text to "Update" when editing existing person
            btnSave.setText("Update");

            // Retrieve person ID from intent extra
            mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);

            // Load person details from database and populate UI
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = mDb.personDao().loadPersonById(mPersonId);
                    // Update UI on the main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populateUI(person);
                        }
                    });
                }
            });
        }
    }

    // Initialize views
    private void initViews() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnSave = findViewById(R.id.btnSave);

        // Save button click listener
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    // Populate UI with person details
    private void populateUI(Person person) {
        if (person == null) {
            return;
        }
        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
    }

    // Handle save button click
    private void onSaveButtonClicked() {
        final Person person = new Person(
                etFirstName.getText().toString(),
                etLastName.getText().toString()
        );

        // Execute database operation on a background thread
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mPersonId != -1) {
                    // Set person ID when updating an existing person
                    person.setUid(mPersonId);
                    mDb.personDao().update(person);
                } else {
                    // Insert new person when person ID is not set
                    mDb.personDao().insert(person);
                }
                // Finish activity after database operation completes
                finish();
            }
        });
    }

    // Handle back button click in action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close activity when back button is clicked
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
