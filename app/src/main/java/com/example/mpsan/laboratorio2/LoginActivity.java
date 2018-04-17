package com.example.mpsan.laboratorio2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Context context = getApplicationContext();
        SharedPreferences sharedPref;

        final EditText logEmail = (EditText) findViewById(R.id.login_email);
        final EditText logPassword = (EditText) findViewById(R.id.login_password);
        final Button loginButton = (Button) findViewById(R.id.login_button);
        final TextView registerLink = (TextView) findViewById(R.id.login_register);

        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.email), String.valueOf(logEmail));
        editor.putString(getString(R.string.passsword), String.valueOf(logPassword));
        editor.commit();

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userAreaIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(userAreaIntent);

            }
        });

    }
}
