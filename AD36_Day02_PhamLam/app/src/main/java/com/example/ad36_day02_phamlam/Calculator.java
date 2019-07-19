package com.example.ad36_day02_phamlam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    TextView tvName, tvInput;
    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    Button btAC, btKQ, btCong, btTru, btNhan, btChia;
    double input1 = 0, input2 = 0, result = 0, current = 0;
    boolean numberClicked = false;
    String operator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        init();
    }

    void init() {
        tvName = findViewById(R.id.tvName);
        tvInput = findViewById(R.id.tvInput);
        tvName.setText(getIntent().getExtras().get("name").toString());
        tvInput.setText("0");
        bt0 = findViewById(R.id.bt0);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        btAC = findViewById(R.id.btAC);
        btCong = findViewById(R.id.btCong);
        btTru = findViewById(R.id.btTru);
        btNhan = findViewById(R.id.btNhan);
        btChia = findViewById(R.id.btChia);
        btKQ = findViewById(R.id.btKQ);

        addNumberInput(bt0);
        addNumberInput(bt1);
        addNumberInput(bt2);
        addNumberInput(bt3);
        addNumberInput(bt4);
        addNumberInput(bt5);
        addNumberInput(bt6);
        addNumberInput(bt7);
        addNumberInput(bt8);
        addNumberInput(bt9);

        addOperator(btCong);
        addOperator(btTru);
        addOperator(btNhan);
        addOperator(btChia);

        btAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInput.setText("0");
                input1 = 0;
                input2 = 0;
                result = 0;
                numberClicked = false;
                operator = null;
            }
        });

        btKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                numberClicked = false;
            }
        });
    }

    void addOperator(final Button bt){
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                operator = bt.getText().toString();
                numberClicked = false;
            }
        });
    }


    void addNumberInput(final Button bt) {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClicked = true;
                    current = current * 10 + Integer.parseInt(bt.getText().toString());
                    display(current);
            }
        });
    }

    void display(double rs) {
        if (rs % 1 == 0) tvInput.setText( String.valueOf((int)rs));
        else tvInput.setText(String.valueOf(rs));
    }


    void calculate() {
        if (operator == null) {
            input1 = Double.parseDouble(tvInput.getText().toString());
            tvInput.setText("0");
            current = 0;
        } else {
            if (numberClicked == true) {
                input2 = Double.parseDouble(tvInput.getText().toString());
                if (operator.equals("+")) {
                    result = input1 + input2;
                    display(result);
                    input1 = result;
                    current = 0;
                }
                if (operator.equals("-")) {
                    result = input1 + input2;
                    display(result);
                    input1 = result;
                    current = 0;
                }
                if (operator.equals("x")) {
                    result = input1 * input2;
                    display(result);
                    input1 = result;
                    current = 0;
                }
                if (operator.equals("/")) {
                    if (input2 == 0)
                        Toast.makeText(Calculator.this, "Số chia phải khác 0", Toast.LENGTH_SHORT).show();
                    else {
                        result = input1 / input2;
                        display(result);
                        input1 = result;
                        current = 0;
                    }
                }
            }
        }
    }
}
