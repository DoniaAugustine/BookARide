package com.example.finalproject_4124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IndividualBooking extends AppCompatActivity {

   private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_booking);
        dbHandler = new DBHandler(IndividualBooking.this);

        int position = getIntent().getIntExtra("Grid_position", 0);
        //int id = getIntent().getIntExtra("id", 0);
        Cursor res = dbHandler.getdetails(position);

        while(res.moveToNext()){

            TextView mname = (TextView)findViewById(R.id.dpmodelname);
            mname.setText(res.getString(2));
            TextView date = (TextView)findViewById(R.id.dpdate);
            date.setText(res.getString(3));
            TextView time = (TextView)findViewById(R.id.dptime);
            time.setText(res.getString(4));


        }



        Button btn1;
        btn1 = (Button) findViewById(R.id.viewmap);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndividualBooking.this,LocationsOnMapsActivity.class);
                intent.putExtra("PositionId",position);
                startActivity(intent);
            }
        });


        Button btn2;
        btn2 = (Button) findViewById(R.id.goback);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndividualBooking.this, All_bookings.class));
            }
        });


    }



}