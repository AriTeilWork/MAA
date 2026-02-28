package com.example.p1;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText numberEditText;
    TextView instructionTextView;
    LayoutParams viewLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 20;
        viewLayoutParams.bottomMargin = 20;


        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        instructionTextView = new TextView(this);
        instructionTextView.setText("Type your favourite number");
        instructionTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(instructionTextView);


        numberEditText = new EditText(this);
        numberEditText.setLayoutParams(viewLayoutParams);
        numberEditText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        numberEditText.setHint("Enter a number");
        linearLayout.addView(numberEditText);


        numberEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    checkFavoriteNumber();
                    return true; // consume the event
                }
                return false;
            }
        });


        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setContentView(linearLayout, linearLayoutParams);
    }


    private void checkFavoriteNumber() {
        String input = numberEditText.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int favoriteNumber = Integer.parseInt(input);


            Random random = new Random();
            int randomNumber = random.nextInt(100);

            if (favoriteNumber == randomNumber) {
                Toast.makeText(this,
                        "Congratulations! Your number matches the random number: " + randomNumber,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Sorry! Your number (" + favoriteNumber + ") does not match the random number (" + randomNumber + ")",
                        Toast.LENGTH_LONG).show();
            }

            numberEditText.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
        }
    }
}