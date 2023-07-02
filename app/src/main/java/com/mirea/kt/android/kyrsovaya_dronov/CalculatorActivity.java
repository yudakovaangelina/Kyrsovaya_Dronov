package com.mirea.kt.android.kyrsovaya_dronov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        CalendarView calendarView = findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {


                Calendar birthDate = Calendar.getInstance();
                birthDate.set(year, month, dayOfMonth);

                long milliseconds = Calendar.getInstance().getTimeInMillis() - birthDate.getTimeInMillis();
                int seconds = (int) (milliseconds / 1000);
                int minutes = seconds / 60;
                int hours = minutes / 60;
                int days = hours / 24;
                int years = days / 365;

                String yourAge = new StringBuilder().append(years).append(" ").append(getString(R.string.years))
                        .append(days).append(" ").append(getString(R.string.hours)).append(hours).append(" ").append(getString(R.string.days))
                        .append(minutes).append(" ").append(getString(R.string.minutes)).append(seconds).append(" ").append(getString(R.string.seconds)).toString();
                TextView age = findViewById(R.id.tvAge);
                if (days <= 0) {
                    Log.d("app_tag", "не родились");
                    age.setText(getString(R.string.noBirth));
                }
                if (seconds > 0) {
                    age.setText(yourAge);

                }
                if (seconds == 0) {
                    Log.d("app_tag", "сегодня родились");
                    age.setText(yourAge + getString(R.string.todayBirth));
                }


                TextView daysToBirthdayView = findViewById(R.id.tvBirthday);

                Calendar today = Calendar.getInstance();
                Calendar birthday = Calendar.getInstance();
                birthday.set(year, month, dayOfMonth);

                if (birthday.before(today)) {
                    birthday.set(Calendar.YEAR, 2023);
                }


                if (birthday.before(today)) {
                    birthday.add(Calendar.YEAR, 1);
                }

                int daysToBirthday = (int) ((birthday.getTimeInMillis() - today.getTimeInMillis()) / (1000 * 60 * 60 * 24));

                Log.d("app_tag", String.valueOf(birthday.getTimeInMillis()));
                Log.d("app_tag", String.valueOf(today.getTimeInMillis()));



                if (daysToBirthday == 0) {
                    String daysText = new StringBuilder().append(getString(R.string.birthToday)).toString();

                    daysToBirthdayView.setText(daysText);
                } else if (daysToBirthday == 1) {
                    String daysText = new StringBuilder().append(getString(R.string.oneDayLeft)).toString();
                    daysToBirthdayView.setText(daysText);

                } else if (daysToBirthday < 5) {
                    String daysText = new StringBuilder().append(getString(R.string.leftDay)).append(" ").append(daysToBirthday).toString();

                    daysToBirthdayView.setText(daysText);

                } else {
                    String daysText = new StringBuilder().append(getString(R.string.leftDays)).append(" ").append(daysToBirthday).toString();

                    daysToBirthdayView.setText(daysText);

                }
            }

        });

    }
}