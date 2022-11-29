package com.company.takenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText edTitle,edDescription;
    Button save, cancel;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Note");
        setContentView(R.layout.activity_update);

        edTitle = findViewById(R.id.editTextTitleUpdate);
        edDescription = findViewById(R.id.editTextTDescriptionUpdate);
        save = findViewById(R.id.btnSaveUpdate);
        cancel = findViewById(R.id.btnCancelUpdate);

        getData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Nothing Updated ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void updateNote() {
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("titleLast",title);
        intent.putExtra("descriptionLast",description);
        if (noteId !=-1){
            intent.putExtra("noteId",noteId);
            setResult(RESULT_OK,intent);
            finish();
        }

    }

    public void getData(){
        Intent i = getIntent();
        noteId = i.getIntExtra("id",-1);
        String noteTitle = i.getStringExtra("title");
        String noteDescription = i.getStringExtra("description");

        edTitle.setText(noteTitle);
        edDescription.setText(noteDescription);


    }
}