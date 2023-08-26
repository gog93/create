package com.example.create;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ExperimentActivity extends AppCompatActivity {
    private Switch markerSwitch;
    private EditText marker_N, descriptionEt;
    private DatabaseManager databaseManager;
    private LinearLayout markerLayout;
    private MaterialButton record;
    private MaterialButton stop;
    private boolean start = false;
    private boolean isRunning = false;

    private long initialTime;
    private long elapsedSeconds = 0;
    private Handler mHandler = new Handler();
    private GraphView graph;
    private LineGraphSeries<DataPoint> series;
    private DbHelper dbHelper;

    //adapter
    private ExperimentAdapterContact adapterContact;
    private Runnable mUpdateGraphRunnable = new Runnable() {
        @Override
        public void run() {
            updateGraph();
            if (start) {
                mHandler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorsd);
        dbHelper = new DbHelper(this);
        initialTime = System.currentTimeMillis(); // Set initial time

        graph = findViewById(R.id.graph);
        databaseManager = new DatabaseManager(this);

        series = new LineGraphSeries<>();
        graph.addSeries(series);
        marker_N = findViewById(R.id.markerN);  // Change to EditText if it's an EditText

        series.setAnimated(true);
        graph.addSeries(series);

        // Customizing graph view
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(19);

        graph.getViewport().setMinY(-100);
        graph.getViewport().setMaxY(100);
// Since we're generating random numbers between 0 and 99
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);
        markerSwitch = findViewById(R.id.switch1);
        markerLayout = findViewById(R.id.markerLayout);
        markerLayout.setVisibility(View.GONE);
        markerSwitch = findViewById(R.id.switch1);
        markerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    markerLayout.setVisibility(View.VISIBLE);
                } else {
                    markerLayout.setVisibility(View.GONE);
                }
            }
        });
        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!start) { // This ensures that the graph will start only if it isn't already running
                    String colorText =marker_N.getText().toString();
                    int color = Color.BLACK;  // default to black
                    Marker markerColor= databaseManager.findById(String.valueOf(colorText));
                    if (!colorText.isEmpty()) {
                        try {
                            color = markerColor.getColor();
                        } catch (IllegalArgumentException e) {
                            // Handle the error, for now, we default to black
                        }
                    }
                    series.setColor(color);
                    start = true;
                    mHandler.post(mUpdateGraphRunnable); // Start the Runnable immediately upon clicking
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start) { // This ensures that the graph will stop only if it's currently running
                    start = false;
                    mHandler.removeCallbacks(mUpdateGraphRunnable); // Stop the Runnable
                }
            }
        });
    }

    private void updateGraph() {
        Point newPoint = generateCardiogramData(elapsedSeconds);
        series.appendData(new DataPoint(newPoint.x, newPoint.y), true, 100); // allowing a max of 100 points on the graph, adjust as necessary

        elapsedSeconds++;
    }

    private Point generateCardiogramData(long second) {
        double t = second * 0.01; // adjust this value to increase/decrease the speed of the waveform
        double y = 5 * Math.sin(5 * Math.PI * t) * Math.exp(-t) + 0.25 * Math.sin(25 * Math.PI * t);
        return new Point((int) second, (int) (y * 20)); // Scale the y-value for visibility
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (start) {
            mHandler.post(mUpdateGraphRunnable);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mUpdateGraphRunnable);
        isRunning = false;
    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main_top_menu,menu);
//
//        //get search item from menu
//        MenuItem item = menu.findItem(R.id.searchContact);
//        //search area
//        SearchView searchView = (SearchView) item.getActionView();
//        //set max value for width
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchContact(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                searchContact(newText);
//                return true;
//            }
//        });
//
//
//        return true;
//
//    }
//
//    private void searchContact(String query) {
//        adapterContact = new AdapterContact(this,dbHelper.getSearchContact(query));
//        contactRv.setAdapter(adapterContact);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.deleteAllContact:
//                dbHelper.deleteAllContact();
//                onResume();
//                break;
//        }
//
//        return true;
//    }
}