package com.example.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddExperimentContact extends AppCompatActivity {
    private EditText nameEt;
    private FloatingActionButton fab;
    private String id,name;
    private Boolean isEditMode=false;
    private ActionBar actionBar;

    //permission constant
    private static final int STORAGE_PERMISSION_CODE = 200;


    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extantion);
        fab = findViewById(R.id.fab2);

        //init db
        dbHelper = new DbHelper(this);

        actionBar = getSupportActionBar();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to new activity to add contact
                Intent intent = new Intent(AddExperimentContact.this, ExperimentActivity.class);
//                intent.putExtra("isEditMode",false);
                startActivity(intent);
            }
        });
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

//        nameEt = findViewById(R.id.nameEt);
//        descriptionEt = findViewById(R.id.phoneEt);
//        fab = findViewById(R.id.fab);
//
//        // get intent data
//        Intent intent = getIntent();
//        isEditMode = intent.getBooleanExtra("isEditMode",false);
//
//        if (isEditMode){
//            //set toolbar title
////            actionBar.setTitle("Update Contact");
//
//            //get the other value from intent
//            id = intent.getStringExtra("ID");
//            name = intent.getStringExtra("NAME");
//            phone = intent.getStringExtra("PHONE");
//            email = intent.getStringExtra("EMAIL");
//            note = intent.getStringExtra("NOTE");
//            addedTime = intent.getStringExtra("ADDEDTIME");
//            updatedTime = intent.getStringExtra("UPDATEDTIME");
//            image = intent.getStringExtra("IMAGE");
//
//            //set value in editText field
//            nameEt.setText(name);
//            phoneEt.setText(phone);
//            emailEt.setText(email);
//            noteEt.setText(note);
//
//            imageUri = Uri.parse(image);
//
//            if (image.equals("")){
//                profileIv.setImageResource(R.drawable.ic_baseline_person_24);
//            }else {
//                profileIv.setImageURI(imageUri);
//            }
//
//        }else {
//            // add mode on
////            actionBar.setTitle("Add Contact");
//        }

        // add even handler
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveData();
//            }
//        });

    }

//    private void saveData() {
//        name = nameEt.getText().toString();
//        description = descriptionEt.getText().toString();
//
//        // get current time to save as added time
//        String timeStamp = ""+System.currentTimeMillis();
//
//
//        //check filed data
//        if (!name.isEmpty() || !description.isEmpty()){
//            //save data ,if user have only one data
//
//            //check edit or add mode to save data in sql
//            if (isEditMode){
//                // edit mode
//                dbHelper.updateContact(
//                        ""+id,
//                        ""+description,
//                        ""+name
//                );
//
//                Toast.makeText(getApplicationContext(), "Updated Successfully....", Toast.LENGTH_SHORT).show();
//
//            }else {
//                // add mode
//                long id =  dbHelper.insertContact(
//                        ""+name,
//                        ""+ description
//                );
//                //To check insert data successfully ,show a toast message
//                Toast.makeText(getApplicationContext(), "Inserted Successfully.... "+id, Toast.LENGTH_SHORT).show();
//            }
//
//        }else {
//            // show toast message
//            Toast.makeText(getApplicationContext(), "Nothing to save....", Toast.LENGTH_SHORT).show();
//        }
//
//    }

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
