package com.example.finalproject_4124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class signup_page extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText phoneNum;
    private EditText username;
    private EditText password;

    private DBHandler dbHandler;

    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        dbHandler = new DBHandler(signup_page.this);
        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        phoneNum = findViewById(R.id.etphonenumber);
        username = findViewById(R.id.etuname);
        password = findViewById(R.id.etpass);



        Button btn1;
        btn1 = (Button) findViewById(R.id.Register);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Text_name = name.getText().toString();
                String Text_email = email.getText().toString();
                String Text_phonenum = phoneNum.getText().toString();
                String Text_username = username.getText().toString();
                String Text_password = password.getText().toString();

                // validating if the text fields are empty or not.
                if (Text_name.isEmpty() || Text_email.isEmpty() || Text_phonenum.isEmpty() || Text_username.isEmpty() || Text_password.isEmpty()) {
                    Toast.makeText(signup_page.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }


                //add new contact to sqlite data and pass all values to it.
              dbHandler.addUserDetails(Text_name, Text_email, Text_phonenum, Text_username, Text_password);
                //dbHandler.insertAdminDetails();

                // after adding the data we are displaying a toast message.
                Toast.makeText(signup_page.this, "User details are added", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                phoneNum.setText("");
                username.setText("");
                password.setText("");

                startActivity(new Intent(signup_page.this, login_Activity.class));
            }
        });




    }
}