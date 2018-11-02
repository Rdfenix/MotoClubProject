package com.example.aiolo.testemapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterEvent extends AppCompatActivity {

    private EditText localEvent;
    private Button registerEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);

        localEvent = findViewById(R.id.local_event);
        registerEvent = findViewById(R.id.buttom_register_event);

    }
}
