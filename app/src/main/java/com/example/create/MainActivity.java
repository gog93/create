package com.example.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
   private MaterialButton fab ;
    private RecyclerView contactRv;

    //db
    private DbHelper dbHelper;

    //adapter
    private AdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        //initialization
        fab = findViewById(R.id.fab);
        contactRv = findViewById(R.id.contactRv);

        contactRv.setHasFixedSize(true);

        // add listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to new activity to add contact
                Intent intent = new Intent(MainActivity.this, AddEditContact.class);
//                intent.putExtra("isEditMode",false);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {
        adapterContact = new AdapterContact(this, dbHelper.getAllData());
        contactRv.setLayoutManager(new LinearLayoutManager(this));

        contactRv.setAdapter(adapterContact);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // refresh data
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