package com.example.mpsan.laboratorio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText regETName = (EditText) findViewById(R.id.new_register_name);
        final EditText regETPassword = (EditText) findViewById(R.id.new_register_password);
        final Button registerButton = (Button) findViewById(R.id.register_button);

    }
}
