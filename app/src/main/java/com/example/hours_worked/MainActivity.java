package com.example.hours_worked;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int minutes = 0;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        readHours();
        readPrice();
        printHours();
        printPrice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public void noInputError() {
        Context context = getApplicationContext();
        CharSequence text = "Zadejte počet hodin a minut.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @SuppressLint("SetTextI18n")
    public void addHours(View view) {
        EditText addHoursH = findViewById(R.id.add_hours_h);
        EditText addHoursMin = findViewById(R.id.add_hours_min);
        if (addHoursH.getText().toString().contentEquals("") && addHoursMin.getText().toString().contentEquals("")) {
            noInputError();
            return;
        }
        if (!addHoursH.getText().toString().contentEquals("")) {
            minutes += Integer.parseInt(addHoursH.getText().toString()) * 60;
        }
        if (!addHoursMin.getText().toString().contentEquals("")) {
            minutes += Integer.parseInt(addHoursMin.getText().toString());
        }
        addHoursH.setText("");
        addHoursMin.setText("");
        printHours();
        saveHours();
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void printHours() {
        TextView hoursView = findViewById(R.id.hours);
        TextView minutesView = findViewById(R.id.minutes);
        hoursView.setText(String.format("%2d", minutes / 60));
        minutesView.setText(String.format("%02d", minutes % 60));
        printPrice();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void printPrice() {
        TextView priceView = findViewById(R.id.price);
        double pricePeerMin = price / 60.0;
        priceView.setText(String.format("%.2f", pricePeerMin * minutes));
    }

    public void readHours() {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        minutes = sp.getInt("minutes", 0);
    }

    public void saveHours() {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("minutes", minutes);
        editor.apply();
    }

    public void readPrice() {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        price = sp.getInt("price", 0);
    }


    public void openSettings(MenuItem menuItem) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }
}