package com.example.aiolo.testemapa;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterGroup extends AppCompatActivity {

    int minimumInteger = 0;

    private EditText title;
    private EditText description;
    private EditText motoCategory;
    private EditText city;
    private EditText state;
    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_group);
    }

    public void increaseNumber(View view){
        minimumInteger = minimumInteger + 1;
        display(minimumInteger);
    }

    public void decreaseNumber(View view){
        if (minimumInteger != 0){
            minimumInteger = minimumInteger - 1;
            display(minimumInteger);
        }
    }

    @SuppressLint("SetTextI18n")
    private void display(int number){
        TextView numberCount = findViewById(R.id.contador);
        numberCount.setText("" + number);
    }
}
