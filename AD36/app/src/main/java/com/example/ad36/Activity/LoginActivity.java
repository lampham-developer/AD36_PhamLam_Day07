package com.example.ad36.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ad36.Data.DataHandler;
import com.example.ad36.Interface.ILogin;
import com.example.ad36.R;
import com.example.ad36.ToDo.LoginPresenter;

public class LoginActivity extends AppCompatActivity {

    LoginPresenter loginPresenter;
    DataHandler dataHandler;
    Button btLogin;
    EditText etLoginUsername, etLoginPassword;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataHandler = new DataHandler(getBaseContext());
        btLogin = findViewById(R.id.btLogin);
        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        tvRegister = findViewById(R.id.tvRegister);

        loginPresenter = new LoginPresenter(new ILogin() {
            @Override
            public void onSuccessFul() {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }

            @Override
            public void onMessenger(String mes) {
                Toast.makeText(getBaseContext(), mes, Toast.LENGTH_LONG).show();
            }
        }, getBaseContext());

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.onLogin(etLoginUsername.getText().toString(), etLoginPassword.getText().toString(), dataHandler.getUser(etLoginUsername.getText().toString()));
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }
}
