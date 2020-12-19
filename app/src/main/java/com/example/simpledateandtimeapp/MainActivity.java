package com.example.simpledateandtimeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
        , TimePickerDialog.OnTimeSetListener {

    ImageButton dateButton, timeButton;
    TextView todayDate, chooseDate, currentTime, chooseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateButton = (ImageButton) findViewById(R.id.dateButton);
        timeButton = (ImageButton) findViewById(R.id.timeButton);

        todayDate = (TextView) findViewById(R.id.todayDate);
        String date_n =
                new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        todayDate.setText(date_n);

        currentTime = (TextView) findViewById(R.id.currentTime);
        String time = new SimpleDateFormat("K:mm a"
                , Locale.getDefault()).format(new Date());
        currentTime.setText(time);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.simpledateandtimeapp.DatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        chooseDate = (TextView) findViewById(R.id.chooseDate);
        chooseDate.setText(currentDateString);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        chooseTime = (TextView) findViewById(R.id.chooseTime);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat hformate = new SimpleDateFormat("K:mm a"
                , Locale.getDefault());
        String event_Time = hformate.format(c.getTime());
        chooseTime.setText(event_Time);
    }
}