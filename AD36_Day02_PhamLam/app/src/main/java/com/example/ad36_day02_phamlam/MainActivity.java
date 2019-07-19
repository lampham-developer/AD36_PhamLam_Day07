package com.example.ad36_day02_phamlam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btStart = findViewById(R.id.btStart);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getBaseContext(), Calculator.class);
                    intent.putExtra("name", etName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
