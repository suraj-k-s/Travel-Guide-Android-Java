package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel.Helpers.SimpleDatabase;
import com.example.travel.Models.Note;

import java.util.Calendar;

public class NoteAdd extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle,noteDetails;
    Calendar c;
    String todaysDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enter New Note");

        noteDetails = findViewById(R.id.note_Details);
        noteTitle = findViewById(R.id.note_Title);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // current date & time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: "+todaysDate);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));
        Log.d("TIME", "Time: "+currentTime);

    }

    private String pad(int time) {
        if(time < 10)
            return "0"+time;
        return String.valueOf(time);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            if(noteTitle.getText().length() != 0){
                Note note = new Note(noteTitle.getText().toString(),noteDetails.getText().toString(),todaysDate,currentTime);
                SimpleDatabase sDB = new SimpleDatabase(this);
                long id = sDB.addNote(note);
                Note check = sDB.getNote(id);
                Log.d("inserted a new note", "Note: "+ id + " -> Title:" + check.getTitle()+" Date: "+ check.getDate());
                onBackPressed();

                Toast.makeText(this, "Note Saved.", Toast.LENGTH_SHORT).show();
            }else {
                noteTitle.setError("Please enter Title.");
            }

        }else if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
