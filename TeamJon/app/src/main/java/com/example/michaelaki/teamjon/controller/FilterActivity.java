package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michaelaki on 10/31/17.
 */

public class FilterActivity extends Activity {

    private Filter filter;
    private Filter startFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);

        Intent intent = getIntent();
        filter = (Filter) intent.getSerializableExtra("Filter");
        startFilter = (Filter) intent.getSerializableExtra("Filter");

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
                String startDateString = startDate.getMonth() + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear();
                DatePicker endDate = (DatePicker) findViewById(R.id.toDatePicker);
                String endDateString = endDate.getMonth() + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date startDateFormatted = new Date();
                Date endDateFormatted = new Date();
                try {

                    startDateFormatted = format.parse(startDateString);
                    endDateFormatted = format.parse(endDateString);
                    filter.setStartDate((int) startDateFormatted.getTime());
                    filter.setEndDate((int) endDateFormatted.getTime());

                } catch (ParseException e) {
                    Log.e("Whoops", "There's a parse exception");
                }
                Intent leaveIntent = new Intent(FilterActivity.this, MapActivity.class);
                leaveIntent.putExtra("Filter", filter);
                startActivity(leaveIntent);
            }
        });
    }
}
