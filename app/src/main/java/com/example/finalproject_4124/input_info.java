package com.example.finalproject_4124;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class input_info extends AppCompatActivity {

    private EditText modelnamefield;
    private EditText datefield;
    private EditText timefield;
    private EditText latitudefield;
    private EditText longitudefield;
    private Button datebutton;

    private DBHandler dbHandler;
    DatePickerDialog picker;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_info);

        dbHandler = new DBHandler(input_info.this);
        modelnamefield = findViewById(R.id.etmodelname);
        datefield=(EditText) findViewById(R.id.etdate);
        timefield = findViewById(R.id.ettime);
        latitudefield = findViewById(R.id.etlatitude);
        longitudefield = findViewById(R.id.etlongitude);
        datebutton = (Button) findViewById(R.id.getdate);



//        Spinner spinnerVehicleList=findViewById(R.id.vehicleList);
//        spinnerVehicleList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = spinnerVehicleList.getSelectedItem().toString();
//                System.out.println(text);
//                modelnamefield.setText(text);
//
//            }
//        });
//
//        // Fetching Values here ---------------------
//        Cursor result = dbHandler.getVehicleNames();
//
//
//
//
//        ArrayList<String> items = new ArrayList<String>();
//        while(result.moveToNext()) {
//            items.add(result.getString(1));
//           // System.out.println(result.getString(1));
//            //modelnamefield.setText(items.indexOf(1));
//        }
//
//
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        spinnerVehicleList.setAdapter(adapter);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        /*

        public void onItemSelected(final AdapterView<?> parent, View view, final int pos, long id) {
            // TODO Auto-generated method stub
            result = (String) spinnerVehicleList.getSelectedItem();
            modelnamefield.setText(result);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }


         */


       // result.close();


        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(input_info.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datefield.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



        Button btn1;
        btn1 = (Button) findViewById(R.id.submit);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Text_modelname = modelnamefield.getText().toString();
                String Text_date = datefield.getText().toString();
                String Text_time = timefield.getText().toString();
                String Text_latitude = latitudefield.getText().toString();
                String Text_longitude = longitudefield.getText().toString();

                System.out.println(Text_modelname);
                System.out.println(Text_date);
                System.out.println(Text_time);
                System.out.println(Text_latitude);
                System.out.println(Text_longitude);


                Intent mIntent = getIntent();
                int login_userId = mIntent.getIntExtra("login_userId", 0);


                //Intent intent = getIntent();
                //String LoginID = intent.getStringExtra("login_userId");
                //System.out.println(LoginID);
                //Integer login_userId = Integer.parseInt(LoginID);
                System.out.println(login_userId);



                // validating if the text fields are empty or not.
                if (Text_modelname.isEmpty() || Text_date.isEmpty() || Text_time.isEmpty() || Text_latitude.isEmpty() || Text_longitude.isEmpty()) {
                    Toast.makeText(input_info.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }


                //add new contact to sqlite data and pass all values to it.
                dbHandler.addBookingDetails(login_userId,Text_modelname, Text_date, Text_time, Text_latitude, Text_longitude);


                // after adding the data we are displaying a toast message.
                Toast.makeText(input_info.this, "Booking details are added", Toast.LENGTH_SHORT).show();
                modelnamefield.setText("");
                datefield.setText("");
                timefield.setText("");
                latitudefield.setText("");
                longitudefield.setText("");

                startActivity(new Intent(input_info.this, booking_status.class));
            }
        });


    }


}
