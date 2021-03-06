package com.example.michaelaki.teamjon.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.DatePicker;
import com.example.michaelaki.teamjon.model.Filter;
import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.RatSighting;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class GraphActivity extends AppCompatActivity {

    private final Filter filter = new Filter();
    private HashMap<Integer, Integer> map = new HashMap<>();   //key, value is date, number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Button backButton = (Button) findViewById(R.id.graphCancelButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        Button confirmButton = (Button) findViewById(R.id.graphConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                DatePicker startDate = (DatePicker) findViewById(R.id.fromDatePicker);
                String startDateString = startDate.getMonth() + 1 + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear();
                DatePicker endDate = (DatePicker) findViewById(R.id.toDatePicker);
                String endDateString = endDate.getMonth() + 1 + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date startDateFormatted;
                Date endDateFormatted;
                try {
                    startDateFormatted = format.parse(startDateString);
                    endDateFormatted = format.parse(endDateString);
                    filter.setStartDate( startDateFormatted.getTime());
                    filter.setEndDate( endDateFormatted.getTime());
                } catch (ParseException e) {
                    Log.e("Whoops", "There's a parse exception");
                }

                //display graph
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                //analyze data points -- to get data from earlier months increase the limit
                Query query = firebaseDatabase.getReference().limitToLast(50000);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot snapshot) {
                          ArrayList<RatSighting> rats = new ArrayList<>();

                          for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                              RatSighting rat = new RatSighting();
                              if (dataSnapshot.child("Created Date").getValue() != null) {
                                  rat.setDate((String) dataSnapshot.child("Created Date").getValue());
                                  SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                                  Date startDateFormatted;
                                  try {
                                      int index = rat.getDate().indexOf(' ');
                                      startDateFormatted = format.parse(rat.getDate().substring(0, index));
                                      rat.setCompareDate(startDateFormatted.getTime());
                                  } catch (ParseException e) {
                                      Log.e("Whoops", "There's a parse exception");
                                  }
                              }
                              rats.add(rat);
                          }
                          //get number of sightings per month/year
                          for (int k = 0; k < rats.size() - 2; k++) {
                              if (rats.get(k).getCompareDate() > filter.getStartDate() && rats.get(k).getCompareDate() < filter.getEndDate()) {
                                  //check which month/year it is and then increment the entry in the map
                                  long compareDate = rats.get(k).getCompareDate();
                                  SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
                                  SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
                                  String month = formatMonth.format(new Date(compareDate));
                                  String year = formatYear.format(new Date(compareDate));
                                  int x = (Integer.parseInt(month) * 10000) + Integer.parseInt(year);
                                  if (!map.containsKey(x)) {
                                      map.put(x, 1);
                                  } else {
                                      map.put(x, map.get(x) + 1);
                                  }
                              }
                          }
                          showGraph();
                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {
                          Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
                      }
                  });
            }
        });
    }

    /**
     * Go back to the LaunchActivity screen
     */
    private void goBack() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * Parse data for the graph and display the data
     */
    private void showGraph() {
        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        //convert map to a list of entries
        List<Entry> entries = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();
        for (int key : map.keySet()) {
            keys.add(key);
        }
        Collections.sort(keys);
        for (int key : keys) {
            System.out.println(key + " " + map.get(key));
            entries.add(new Entry(key, map.get(key)));
        }
        //sort entries to make keys in order
        LineDataSet dataset = new LineDataSet(entries, "# of Sightings");
        Log.d("APP", "Made dataset with : " + entries.size());
        LineData data = new LineData(dataset);
        lineChart.setData(data);
        lineChart.animateY(5000);
        lineChart.getDescription().setText("Number of Rat Sightings per Month/Year");
    }
}
