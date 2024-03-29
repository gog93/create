package com.example.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.util.Random;

public class AddEditContact extends AppCompatActivity {
    private EditText nameEt, descriptionEt;
    private MaterialButton fab;
    private String id, name, description, autoGeneratedIdTextView;
    private Boolean isEditMode = false;
    private ActionBar actionBar;

    //permission constant
    private static final int STORAGE_PERMISSION_CODE = 200;
    Spinner spinner;
    Spinner spinner2;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);
        spinner = findViewById(R.id.spinner);
        spinner = findViewById(R.id.spinner2);
        // For spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        // For spinner2
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner_items2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        ImageButton button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondActivity
                Intent intent = new Intent(AddEditContact.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //init db
        dbHelper = new DbHelper(this);

        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        nameEt = findViewById(R.id.nameEt);
        descriptionEt = findViewById(R.id.phoneEt);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        // Example Java code:
        Random rand = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int randomDigit = rand.nextInt(10); // generates a random number between 0 (inclusive) and 10 (exclusive)
            id.append(randomDigit);
        }
        autoGeneratedIdTextView = id.toString();

        TextView autoGeneratedIdTextView = findViewById(R.id.autoGeneratedIdTextView);
        autoGeneratedIdTextView.setText("ID: " + id.toString());


    }

    private void saveData() {
        name = nameEt.getText().toString();
        description = descriptionEt.getText().toString();

        // get current time to save as added time
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            timeStamp = "" + LocalDate.now().toString();
        }


        //check filed data
        if (!name.isEmpty() || !description.isEmpty()) {
            //save data ,if user have only one data

            //check edit or add mode to save data in sql
            if (isEditMode) {
                // edit mode
                dbHelper.updateContact(
                        "" + id,
                        "" + description,
                        "" + name,
                        "" + autoGeneratedIdTextView,
                        "" + timeStamp
                );

                Toast.makeText(getApplicationContext(), "Updated Successfully....", Toast.LENGTH_SHORT).show();

            } else {
                // add mode
                long id = dbHelper.insertContact(
                        "" + description,
                        "" + name,
                        "" + autoGeneratedIdTextView,
                        "" + timeStamp
                );
                //To check insert data successfully ,show a toast message
                Toast.makeText(getApplicationContext(), "Inserted Successfully.... " + id, Toast.LENGTH_SHORT).show();
            }

        } else {
            // show toast message
            Toast.makeText(getApplicationContext(), "Nothing to save....", Toast.LENGTH_SHORT).show();
        }

    }

    //ctr + O

    //back button click
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    //handle request permission

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK){
//            if (requestCode == IMAGE_FROM_GALLERY_CODE){
//                // picked image from gallery
//                //crop image
//                CropImage.activity(data.getData())
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAspectRatio(1,1)
//                        .start(AddEditContact.this);
//
//            }else if (requestCode == IMAGE_FROM_CAMERA_CODE){
//                //picked image from camera
//                //crop Image
//                CropImage.activity(imageUri)
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAspectRatio(1,1)
//                        .start(AddEditContact.this);
//            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//
//                //cropped image received
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//                imageUri = result.getUri();
//                profileIv.setImageURI(imageUri);
//
//            }
//            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                //for error handling
//                Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


}
