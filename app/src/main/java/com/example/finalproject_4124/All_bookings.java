package com.example.finalproject_4124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


import java.util.ArrayList;

public class All_bookings extends AppCompatActivity {


    private com.example.finalproject_4124.DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bookings);
        GridView gridview = (GridView) findViewById(R.id.gridcollection);

        dbHandler = new com.example.finalproject_4124.DBHandler(All_bookings.this);

        ArrayList<String> list = new ArrayList<String>();


        Cursor res = dbHandler.viewBookings();


        while(res.moveToNext()){

           // list.add(res.getString(3));
            list.add(res.getString(2));
           // list.add(res.getString(4));

        }

        //BooksAdapter booksAdapter = new BooksAdapter(this, list);



        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_griditem_textview, list);


        gridview.setAdapter(adapter);



        // Implement On Item click listener
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(All_bookings.this,IndividualBooking.class);
               intent.putExtra("Grid_position", position );
//                intent.putExtra("id", id);
              //  intent.setClass(getBaseContext(), com.example.finalproject_4124.IndividualBooking.class);
                startActivity(intent);
            }
        });




    }

}