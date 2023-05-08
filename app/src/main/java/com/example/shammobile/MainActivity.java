package com.example.shammobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.shammobile.activities.students.StudentsProfileActivity;
import com.example.shammobile.activities.teachers.attendance.AttendanceHomeActivity;
import com.example.shammobile.models.Announcement;
import com.example.shammobile.models.RegistrationStatus;
import com.example.shammobile.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<SlideModel> imageList = new  ArrayList<>(); // Create image list

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RegistrationStatus> callreg = APIUtils.getUserService().getRegistrationStatus();
                callreg.enqueue(new Callback<RegistrationStatus>() {
                    @Override
                    public void onResponse(Call<RegistrationStatus> callreg, Response<RegistrationStatus> response) {
                        if (response.isSuccessful()) {
                            RegistrationStatus registrationStatus = response.body();
                            if (registrationStatus.getStatus().equals("Open")){
                                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
                                }
                            else if (registrationStatus.getStatus().equals("Close")){
                                Toast.makeText(MainActivity.this, "Registration is CLOSED", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<RegistrationStatus> callreg, Throwable t) {
                        Toast.makeText(MainActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
                    }});
            }
        });

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        Button AppStatus = (Button) findViewById(R.id.appstatus);
        AppStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TicketingActivity.class));
            }
        });

    }
}