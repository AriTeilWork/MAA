package com.example.p2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    EditText idEdit, nameEdit, priceEdit, amountEdit;
    Button submitButton;
    TextView summaryText;

    class Product {
        String id;
        String name;
        double unitPrice;
        int amount;

        Product(String id, String name, double price, int amt) {
            this.id = id;
            this.name = name;
            this.unitPrice = price;
            this.amount = amt;
        }

        double totalValue() {
            return unitPrice * amount;
        }
    }

    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);


        idEdit = new EditText(this);
        idEdit.setHint("Product ID");
        layout.addView(idEdit, params);

        nameEdit = new EditText(this);
        nameEdit.setHint("Product Name");
        layout.addView(nameEdit, params);

        priceEdit = new EditText(this);
        priceEdit.setHint("Unit Price");
        priceEdit.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(priceEdit, params);

        amountEdit = new EditText(this);
        amountEdit.setHint("Amount");
        amountEdit.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(amountEdit, params);


        submitButton = new Button(this);
        submitButton.setText("Submit");
        layout.addView(submitButton, params);


        summaryText = new TextView(this);
        summaryText.setText("Summary will appear here...");
        summaryText.setTextColor(Color.BLACK);
        layout.addView(summaryText, params);

        setContentView(layout);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitProduct();
            }
        });
    }

    private void submitProduct() {
        String id = idEdit.getText().toString();
        String name = nameEdit.getText().toString();
        String priceStr = priceEdit.getText().toString();
        String amountStr = amountEdit.getText().toString();

        if (id.isEmpty() || name.isEmpty() || priceStr.isEmpty() || amountStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int amount = Integer.parseInt(amountStr);


        Product product = new Product(id, name, price, amount);
        products.add(product);


        StringBuilder sb = new StringBuilder();
        sb.append("Products Summary:\n");
        for (Product p : products) {
            sb.append(p.id).append(" - ").append(p.name)
                    .append(" | Unit Price: ").append(p.unitPrice)
                    .append(" | Amount: ").append(p.amount)
                    .append(" | Total: ").append(p.totalValue())
                    .append("\n");
        }
        summaryText.setText(sb.toString());


        idEdit.setText("");
        nameEdit.setText("");
        priceEdit.setText("");
        amountEdit.setText("");
    }
}