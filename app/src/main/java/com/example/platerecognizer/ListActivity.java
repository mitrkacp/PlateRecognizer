package com.example.platerecognizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListActivity extends AppCompatActivity {

    EditText mEditText;
    Button button_insert, button_edit;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mEditText = findViewById(R.id.edit_text);
        button_insert = findViewById(R.id.button_insert);
        button_edit = findViewById(R.id.button_edit);
        mDatabaseHelper = new DatabaseHelper(this);


        //edit list
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShowListActivity.class));
            }
        });

        //insert to database button
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEditText.getText().toString();
                String lines[] = text.split("\\r?\\n");

                if(mEditText.length() !=0){
                    for(String line: lines){
                        addData(line);
                    }

                    mEditText.setText("");
                } else{
                    toastMessage("Text field is empty!");
                }

            }
        });

        //initialize and assign bottomNavigationView variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set list view selected
        bottomNavigationView.setSelectedItemId(R.id.list);

        //item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.camera:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.list:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    //inserting data to database
    public void addData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            toastMessage("Data inserted successfully!");
        }else{
            toastMessage("Failed to insert data or already in database");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}