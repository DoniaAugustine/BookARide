package com.example.finalproject_4124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Vehicle_Details extends AppCompatActivity {

    private EditText vehname;
    private EditText vehnumber;
    private DBHandler dbHandler;
    public Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        dbHandler = new DBHandler(Vehicle_Details.this);

        //initializing variables
        vehname = findViewById(R.id.etvehiclename);
        vehnumber = findViewById(R.id.etvehiclenumber);
        btn1 = (Button) findViewById(R.id.add);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehiclename = vehname.getText().toString();
                String vehiclenumber = vehnumber.getText().toString();

                // validating if the text fields are empty or not.
                if (vehiclename.isEmpty() && vehiclenumber.isEmpty()) {
                    Toast.makeText(Vehicle_Details.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }


                //add new contact to sqlite data and pass all values to it.
                dbHandler.add_veh_details(vehiclename, vehiclenumber);

                // after adding the data we are displaying a toast message.
                Toast.makeText(Vehicle_Details.this, "Vehicle details are added", Toast.LENGTH_SHORT).show();
                vehname.setText("");
                vehnumber.setText("");

                startActivity(new Intent(Vehicle_Details.this, All_bookings.class));
            }
        });
    }


}