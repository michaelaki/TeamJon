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
import com.github.mikephil.charting.utils.ColorTemplate;
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

public class GraphActivity extends AppCompatActivity {

    private Filter filter = new Filter();
    private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();   //key, value is date, number

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
                DatePicker startDate = (DatePicker) findViewById(R.id.fromDatePicker);
                String startDateString = startDate.getMonth() + 1 + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear();
                DatePicker endDate = (DatePicker) findViewById(R.id.toDatePicker);
                String endDateString = endDate.getMonth() + 1 + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date startDateFormatted = new Date();
                Date endDateFormatted = new Date();
                try {
                    startDateFormatted = format.parse(startDateString);
                    endDateFormatted = format.parse(endDateString);
                    filter.setStartDate( startDateFormatted.getTime());
                    filter.setEndDate( endDateFormatted.getTime());
                } catch (ParseException e) {
                    Log.e("Whoops", "There's a parse exception");
                }

                //display graph
                LineChart lineChart = (LineChart) findViewById(R.id.chart);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                //analyze data points
                Query markerQuery = firebaseDatabase.getReference().limitToLast(3000);
                //it's not going thru the database data correctly?
                System.out.println("something here");
                markerQuery.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot snapshot) {
                          List<RatSighting> rats = new ArrayList();
                          System.out.println("reading data hopefully");

                          for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                              RatSighting rat = new RatSighting();
                              if (dataSnapshot.child("Created Date").getValue() != null) {
                                  rat.setDate(dataSnapshot.child("Created Date").getValue().toString());
                                  SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                                  Date startDateFormatted = new Date();
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
                          System.out.println("Done reading data");
                          System.out.println(rats);
                          //get number of sightings per month/year
                          for (int k = 0; k < rats.size() - 2; k++) {
                              if (rats.get(k).getCompareDate() > filter.getStartDate() && rats.get(k).getCompareDate() < filter.getEndDate()) {
                                  //check which month/year it is and then increment the entry in the map
                                  long compareDate = rats.get(k).getCompareDate();
                                  SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
                                  SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
                                  String month = formatMonth.format(new Date(compareDate));
                                  String year = formatYear.format(new Date(compareDate));
                                  //x-coord is MMyyyy
                                  int x = (Integer.parseInt(month) * 10000) + Integer.parseInt(year);
                                  if (!map.containsKey(x)) {
                                      map.put(x, 0);
                                  } else {
                                      map.put(x, map.get(x) + 1);
                                  }
                              }
                          }
                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {
                          Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
                      }
                  });

                //convert map to a list of entries
                List<Entry> entries = new ArrayList<Entry>();
                for (int key : map.keySet()) {
                    entries.add(new Entry(key, map.get(key)));
                }
                //do you have to sort entries?
                LineDataSet dataset = new LineDataSet(entries, "# of Sightings");
                Log.d("APP", "Made dataset with : " + entries.size());
                LineData data = new LineData(dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                lineChart.setData(data);
                lineChart.animateY(5000);
                lineChart.getDescription().setText("Number of Rat Sightings per Month/Year");
            }
        });
    }

    /**
     * Go back to the LaunchActivity screen
     */
    public void goBack() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
