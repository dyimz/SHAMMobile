package com.example.shammobile.activities.students;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.shammobile.R;
import com.example.shammobile.SplashscreenActivity;
import com.example.shammobile.activities.teachers.TeachersDrawerActivity;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentsDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        Bundle bundle = getIntent().getExtras();
        String studentID = bundle.getString("studentID");

        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.students_drawer, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View header = navigationView.getHeaderView(0);
        ImageView image = (ImageView) header.findViewById(R.id.image);
        TextView name = (TextView) header.findViewById(R.id.name);
        TextView email = (TextView) header.findViewById(R.id.email);

        Call<Students> callstudents = APIUtils.getUserService().getStudent(studentID);
        callstudents.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                if (response.isSuccessful()) {
                    Students students = response.body();

                    Glide.with(StudentsDrawerActivity.this)
                            .load(APIUtils.API_URL + students.getImage())
                            .into(image);
                    name.setText(students.getFname() + " " + students.getMname() + " " + students.getLname());
                    email.setText(students.getEmail());
                }
                else{
                    Toast.makeText(StudentsDrawerActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");
        String studentID = bundle.getString("studentID");
        String token = bundle.getString("token");
        Bundle bundlez = new Bundle();
        bundlez.putString("role", role);
        bundlez.putString("studentID", studentID);
        bundlez.putString("token", token);

        switch (item.getItemId()){
            case R.id.studentprofile:
                    startActivity(new Intent(this, StudentsProfileActivity.class).putExtras(bundlez));
                    overridePendingTransition(0,0);
                    break;

            case R.id.studentsschedule:
                startActivity(new Intent(this, StudentsScheduleActivity.class).putExtras(bundlez));
                overridePendingTransition(0,0);
                break;
//
            case R.id.studentgrade:
                startActivity(new Intent(this, StudentsGradesActivity.class).putExtras(bundlez));
                overridePendingTransition(0,0);
                break;

            case R.id.studentlogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentsDrawerActivity.this);
                builder.setTitle("LOGOUT");
                builder.setMessage("Are you sure want to exit?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(StudentsDrawerActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(StudentsDrawerActivity.this, SplashscreenActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.create().show();

                break;

        }
        return false;
    }

    protected void allocateActiviyTitle(String titleString){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}