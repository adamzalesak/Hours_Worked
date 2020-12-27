package com.example.hours_worked;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        readPrice();
        printPrice();
    }

    public void setPrice(View view) {
        EditText setPriceField = findViewById(R.id.set_price_field);
        if (setPriceField.getText().toString().contentEquals("")) {
            Context context = getApplicationContext();
            CharSequence text = "Zadejte novou hodinovou sazbu.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        price = Integer.parseInt(setPriceField.getText().toString());
        setPriceField.setText("");
        savePrice();
        setPriceField.setHint(Integer.toString(price));
    }

    @SuppressLint("SetTextI18n")
    public void printPrice() {
        EditText setPriceField = findViewById(R.id.set_price_field);
        setPriceField.setHint(Integer.toString(price));
    }

    public void savePrice() {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("price", price);
        editor.apply();
    }


    public void readPrice() {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        price = sp.getInt("price", 0);
    }

    public void restart(View view) {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("minutes", 0);
        editor.apply();
        finish();
    }
}