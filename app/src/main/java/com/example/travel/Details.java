package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    TextView titleTextView;
    TextView hotelDescTextView;
    String hotel;
    int imageResourse;
    String hoteldesc;

    FloatingActionButton addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        imageView=findViewById(R.id.image);
        titleTextView=findViewById(R.id.titleTextView);
        hotelDescTextView =findViewById(R.id.hotelDescstextView);

        hotel=getIntent().getStringExtra("titleTextView");
        imageResourse=getIntent().getIntExtra("image",0);
        hoteldesc = getIntent().getStringExtra("hotelDescTextView");

        titleTextView.setText(hotel);
        imageView.setImageResource(imageResourse);
        hotelDescTextView.setText(hoteldesc);



        addbtn=(FloatingActionButton) findViewById(R.id.notepadbutton);
        addbtn.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Details.this, NoteActivity.class);
        startActivity(intent);
    }
}
