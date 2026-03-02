package com.example.p2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

    private double firstNumber = 0;
    private String currentOperation = "";
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);


        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8,
                R.id.btn9
        };

        View.OnClickListener numberListener = v -> {

            Button btn = (Button) v;

            if (isNewInput) {
                tvDisplay.setText(btn.getText());
                isNewInput = false;
            } else {
                tvDisplay.append(btn.getText());
            }
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberListener);
        }


        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperation("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperation("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperation("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperation("/"));
        findViewById(R.id.btnPercent).setOnClickListener(v -> setOperation("%"));


        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());


        findViewById(R.id.btnClear).setOnClickListener(v -> {
            tvDisplay.setText("0");
            firstNumber = 0;
            currentOperation = "";
            isNewInput = true;
        });
    }

    private void setOperation(String operation) {

        firstNumber = Double.parseDouble(tvDisplay.getText().toString());
        currentOperation = operation;
        isNewInput = true;
    }

    private void calculateResult() {

        double secondNumber =
                Double.parseDouble(tvDisplay.getText().toString());

        double result = 0;

        switch (currentOperation) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0)
                    result = firstNumber / secondNumber;
                else
                    tvDisplay.setText("Error");
                break;
            case "%":
                result = firstNumber * secondNumber / 100;
                break;
        }

        tvDisplay.setText(String.valueOf(result));
        isNewInput = true;
    }
}