package com.tbcmad.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username , password;
    TextView info;
    SharedPreferences preference;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.login_activity_btn_login);
        username= findViewById(R.id.username);
        password=findViewById(R.id.password);
        info= findViewById(R.id.txt_info);
        username.setText("");
        password.setText("");
        preference = getSharedPreferences("myPreferences", MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()|| password.getText().toString().isEmpty())
                    info.setText("Please insert username and password");
                else {
                    DoLogin(username.getText().toString(), password.getText().toString());


                }
            }

            public void DoLogin(String userid, String password) {
                try {
                    if (password.equals("password")) {
                        if (preference == null)
                            preference = getSharedPreferences("myPreferences", MODE_PRIVATE);

                        editor = preference.edit();
                        editor.putString("name", userid);
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else
                        info.setText("Invalid Credentails");
                } catch (Exception ex) {
                    info.setText(ex.getMessage().toString());
                }
            }
        });
    }
}