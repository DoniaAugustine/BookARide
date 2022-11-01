package com.example.finalproject_4124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class booking_status extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_status);

        Button btn1;
        btn1 = (Button) findViewById(R.id.newbooking);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(booking_status.this, input_info.class));
            }
        });

        Button btn2;
        btn2 = (Button) findViewById(R.id.exit);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(booking_status.this, login_Activity.class));
            }
        });
    }
}