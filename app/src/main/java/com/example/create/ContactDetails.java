package com.example.create;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class ContactDetails extends AppCompatActivity {
    private TextView name;
    private Context context;

    private String id;

    //database helper
    private DbHelper dbHelper;
    private MaterialButton fab;
    private RecyclerView contactRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        dbHelper = new DbHelper(this);

        //get data from intent
        Intent intent = getIntent();
        id = intent.getStringExtra("contactId");

        fab = findViewById(R.id.fab);
        contactRv = findViewById(R.id.contactRv);
        name = findViewById(R.id.contact_name);
        contactRv.setHasFixedSize(true);
        ImageButton button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondActivity
                Intent intent = new Intent(ContactDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // add listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to new activity to add contact
                Intent intent = new Intent(ContactDetails.this, ExperimentActivity.class);
//                intent.putExtra("isEditMode",false);
                startActivity(intent);
            }
        });

        loadDataById();

    }

    private void loadDataById() {

        //get data from database
        //query for find data by id
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.C_ID + " =\"" + id + "\"";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                //get data
                String name = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME));

                //set data
                this.name.setText(name);

            } while (cursor.moveToNext());
        }

        db.close();

    }
}
