package com.example.create;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ExperimentActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView contactRv;

    private static final String TAG = "MainActivity";
    private long initialTime; // Will store the starting time in milliseconds
    private long elapsedSeconds = 0;
    private Handler mHandler = new Handler();
    private Runnable mUpdateGraphRunnable = new Runnable() {
        @Override
        public void run() {
            updateGraph();
            mHandler.postDelayed(this, 1000); // update every 1 second, adjust as necessary
        }
    };

    private GraphView graph;
    private LineGraphSeries<DataPoint> series;
    private DbHelper dbHelper;

    //adapter
    private ExperimentAdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorsd);
        dbHelper = new DbHelper(this);
        initialTime = System.currentTimeMillis(); // Set initial time

        graph = findViewById(R.id.graph);

        series = new LineGraphSeries<>();
        graph.addSeries(series);

        // Customizing graph view
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(19); // Assuming 5 data points, with indices 0 to 4
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100); // Since we're generating random numbers between 0 and 99
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);


        updateGraph(); // Update the graph
        //initialization
//        fab = findViewById(R.id.fab);
//        contactRv = findViewById(R.id.contactRv);

//        contactRv.setHasFixedSize(true);

        // add listener
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // move to new activity to add contact
//                Intent intent = new Intent(ExperimentActivity.this, AddExperimentContact.class);
////                intent.putExtra("isEditMode",false);
//                startActivity(intent);
//            }
//        });

//        loadData(id);
    }

    private void loadData(String modalId) {
        adapterContact = new ExperimentAdapterContact(this, dbHelper.getExperimentsForModal(modalId));
        contactRv.setLayoutManager(new LinearLayoutManager(this));

        contactRv.setAdapter(adapterContact);

    }
    private void updateGraph() {
        Point newPoint = generateCardiogramData(elapsedSeconds);
        series.appendData(new DataPoint(newPoint.x, newPoint.y), true, 100); // allowing a max of 100 points on the graph, adjust as necessary

        elapsedSeconds++;
    }
    private Point generateCardiogramData(long second) {
        int y = (int) (100 * Math.sin(second * 0.2) * Math.sin(second * 0.6)); // Cardiogram-like function for simplicity
        return new Point((int) second, y);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.post(mUpdateGraphRunnable); // start updating graph when app is in the foreground
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mUpdateGraphRunnable); // stop updating when app goes to the background
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