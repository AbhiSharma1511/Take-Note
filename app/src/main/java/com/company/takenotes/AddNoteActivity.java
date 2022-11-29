package com.company.takenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    EditText edTitle,edDescription;
    Button save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_note);

        edTitle = findViewById(R.id.editTextTitle);
        edDescription = findViewById(R.id.editTextTDescription);
        save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Nothing Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void saveNote(){

        String noteTitle = edTitle.getText().toString();
        String noteDescription = edDescription.getText().toString();

        Intent i = new Intent();
        i.putExtra("noteTitle",noteTitle);
        i.putExtra("noteDescription",noteDescription);
        setResult(RESULT_OK,i);
        finish();
    }
}