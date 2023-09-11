package com.example.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AcupunctureActivity  extends AppCompatActivity {
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment_page_activity);
        dbHelper = new DbHelper(this);

        TextView acupunctureTextView = findViewById(R.id.acupuncture);
        TextView bioTextView = findViewById(R.id.bio);
        acupunctureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcupunctureActivity.this, AcupunctureActivity.class);
//                intent.putExtra("isEditMode",false);
                startActivity(intent);}
        });


        bioTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondActivity
                Intent intent = new Intent(AcupunctureActivity.this,ExperimentActivity.class);
                startActivity(intent);
            }
        });

    }
}
