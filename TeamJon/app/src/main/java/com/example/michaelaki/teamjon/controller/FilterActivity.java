package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * File made by michaelaki on 10/31/17.
 */

public class FilterActivity extends Activity {

    private Filter filter;
    private Filter startFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_menu);

        Intent intent = getIntent();
        filter = (Filter) intent.getSerializableExtra("Filter");
        startFilter = (Filter) intent.getSerializableExtra("Filter");
        if (filter == null) {
            filter = new Filter();
        }

        Button cancel = (Button) findViewById(R.id.filterCancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaveIntent = new Intent(FilterActivity.this, MapActivity.class);
                leaveIntent.putExtra("Filter", startFilter);
                startActivity(leaveIntent);
            }
        });

        Button confirm = (Button) findViewById(R.id.filterConfirmButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker startDate = (DatePicker) findViewById(R.id.fromDatePicker);
                String startDateString = startDate.getMonth() + 1 + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear();
                DatePicker endDate = (DatePicker) findViewById(R.id.toDatePicker);
                String endDateString = endDate.getMonth() + 1 + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date startDateFormatted;
                Date endDateFormatted;
                try {

                    startDateFormatted = format.parse(startDateString);
                    endDateFormatted = format.parse(endDateString);
                    System.out.println(format.parse("12/31/2015").getTime());
                    if (validateDateRange(startDateFormatted.getTime(), endDateFormatted.getTime())) {
                        filter.setStartDate(startDateFormatted.getTime());
                        filter.setEndDate(endDateFormatted.getTime());
                        Intent leaveIntent = new Intent(FilterActivity.this, MapActivity.class);
                        leaveIntent.putExtra("Filter", filter);
                        startActivity(leaveIntent);
                    } else {
                        makeToast();
                    }

                } catch (ParseException e) {
                    Log.e("Whoops", "There's a parse exception");
                }
            }
        });
    }

    /**
     * Validates that date range is legal
     * @param start start date of date range
     * @param end end date of date range
     * @return whether or not the date range is legal
     */
    public boolean validateDateRange(long start, long end) {
        long begin = 1451624400000L;
        long late = 1511240400000L;
        return start >= begin && start < end && end < late;
    }

    /**
     * Print a message to the screen telling the user that they input invalid information
     */
    private void makeToast() {
        Toast.makeText(this, "Invalid Date Range Input", Toast.LENGTH_SHORT).show();
    }
}
