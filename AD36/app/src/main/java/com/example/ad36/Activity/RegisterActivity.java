package com.example.ad36.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ad36.Data.DataHandler;
import com.example.ad36.Entity.User;
import com.example.ad36.Interface.ILogin;
import com.example.ad36.R;
import com.example.ad36.ToDo.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    RegisterPresenter registerPresenter;
    DataHandler dataHandler;

    EditText etReRegPassword, etRegPassword, etRegUsername;
    Button btReg,btCancelReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegUsername = findViewById(R.id.etRegUsername);
        etRegPassword = findViewById(R.id.etRegPassword);
        etReRegPassword = findViewById(R.id.etReRegPassword);
        btReg = findViewById(R.id.btReg);
        btCancelReg = findViewById(R.id.btCancelReg);

        dataHandler = new DataHandler(getBaseContext());

        registerPresenter = new RegisterPresenter(new ILogin() {
            @Override
            public void onSuccessFul() {
                dataHandler.addNewUser(new User(etRegUsername.getText().toString(), etRegPassword.getText().toString()));
                AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                       .setTitle("Thành công")
                       .setMessage("Đăng kí tài khoản thành công ! Bạn sẽ được đưa về trang đăng nhập")
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               startActivity(new Intent(getBaseContext(), LoginActivity.class));
                           }
                       })
                       .create();

                alertDialog.show();
            }

            @Override
            public void onMessenger(String mes) {
                Toast.makeText(getBaseContext(), mes, Toast.LENGTH_LONG).show();
            }
        }, getBaseContext());

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPresenter.onRegister(etRegUsername.getText().toString(), etRegPassword.getText().toString(), etReRegPassword.getText().toString(), dataHandler.getUser(etRegUsername.getText().toString()));
            }
        });


        btCancelReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });

    }
}
