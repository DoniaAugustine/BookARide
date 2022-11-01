package com.example.finalproject_4124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_Activity extends AppCompatActivity {

    private EditText loginUsername;
    private EditText loginPassword;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHandler = new DBHandler(login_Activity.this);
     //dbHandler.dropTable();
    // dbHandler.addTable();
     //dbHandler.addAdminData();
     //dbHandler.insertAdminDetails();
     //dbHandler.insertvehicleDetails();


        loginUsername = findViewById(R.id.etusername);
        loginPassword = findViewById(R.id.etpassword);



        Button btn1;
        btn1 = (Button) findViewById(R.id.signin);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String loginUsernameText = loginUsername.getText().toString();
                String loginPasswordText = loginPassword.getText().toString();

                if (loginUsernameText.isEmpty() || loginPasswordText.isEmpty()) {
                    Toast.makeText(login_Activity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {

                    //String passwordFromDB =  dbHandler.getPassword(loginUsernameText);
                  //Cursor res = dbHandler.getLoginID(loginUsernameText, loginPasswordText);

                    int login_userType = 0;
                    int login_userId = 0;
                  Cursor result = dbHandler.getLoginID(loginUsernameText,loginPasswordText);
//                    if (res != null) {
//                        if (res.moveToNext())
//                            loginId = res.getInt(0);

                    if (result != null) {
                        if (result.moveToFirst()) {
                            login_userType = result.getInt(1);
                            login_userId = result.getInt(0);
                            System.out.println(login_userId);
                        }
                        result.close();
                    }

                        Intent intent = new Intent();

                        if(login_userType == 1 ){
                        intent.setClass(getBaseContext(), AdminActivity.class);
                      //  intent.putExtra("loginId", loginId);
                        //   intent.putExtra("id", id);
                        startActivity(intent);
                    }
                        else if(login_userType == 2) {

                            //intent.setClass(getBaseContext(), com.example.finalproject_4124.input_info.class);
                            //intent.putExtra("login_userId", login_userId);
                           // intent.putExtra("id", id);
                            //startActivity(intent);


                            Intent myIntent = new Intent(login_Activity.this, input_info.class);
                            myIntent.putExtra("login_userId", login_userId);
                            startActivity(myIntent);
                        }

                        else {
                            Toast.makeText(login_Activity.this, "Incorrect Login Credentials", Toast.LENGTH_SHORT).show();
                            loginUsername.setText("");
                            loginPassword.setText("");
                        }

                }
            }
        });


        Button btn2;
        btn2 = (Button) findViewById(R.id.signup);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_Activity.this, signup_page.class));
            }
        });
    }
}