package com.gp.hmb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int days;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String childDateOfBirth = getIntent().getStringExtra("childDateOfBirth");

        calculateDate(childDateOfBirth);
    }

    public void openChildGrowth(View view) {
        Intent intent = new Intent(MainActivity.this, ChildGrowthActivity.class);
        intent.putExtra("days", days);
        startActivity(intent);
    }

    private void calculateDate(String childDate) {
        childDate = childDate.replaceAll("/", " ");

        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = "1 01 2021";
        String inputString2 = "1 04 2021";
        try {
            Date date1 = myFormat.parse(childDate);
            Date date2 = myFormat.parse(currentDate);
            long millis = date2.getTime() - date1.getTime();
            days = (int) TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
            System.out.println("Days: " + days);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void openInstructions(View view) {
        Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
        intent.putExtra("days", days);
        startActivity(intent);
    }

    public void openVaccinateActivity(View view) {
        Intent intent = new Intent(MainActivity.this, VaccinateActivity.class);
        intent.putExtra("days", days);
        startActivity(intent);
    }

    public void openWarningsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, WarningActivity.class);
        intent.putExtra("days", days);
        startActivity(intent);
    }
}