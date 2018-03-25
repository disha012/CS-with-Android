package com.example.admin.scarnedice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {
    private ImageButton play,instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        play = (ImageButton) findViewById(R.id.Play);
        instructions = (ImageButton) findViewById(R.id.Instructions);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(myIntent);
            }
        });

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartActivity.this, InstructionsActivity.class);
                StartActivity.this.startActivity(myIntent);
            }
        });
    }
}

