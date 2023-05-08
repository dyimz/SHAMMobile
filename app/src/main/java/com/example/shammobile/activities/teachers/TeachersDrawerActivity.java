package com.example.shammobile.activities.teachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shammobile.R;
import com.example.shammobile.SplashscreenActivity;
import com.example.shammobile.activities.students.StudentsDrawerActivity;
import com.example.shammobile.activities.teachers.attendance.AttendanceHomeActivity;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawerLayout;

        @Override
        public void setContentView(View view) {
                Bundle bundle = getIntent().getExtras();
                String teacherID = bundle.getString("teacherID");

                drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.teachers_drawer, null);
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


                Call<Teachers> callteachers = APIUtils.getUserService().getTeacher(teacherID);

                callteachers.enqueue(new Callback<Teachers>() {
                        @Override
                        public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                                if (response.isSuccessful()) {
                                        Teachers teachers = response.body();

                                        Glide.with(TeachersDrawerActivity.this)
                                                .load(APIUtils.API_URL + teachers.getImage())
                                                .into(image);
                                        name.setText(teachers.getFname() + " " + teachers.getMname() + " " + teachers.getLname());
                                        email.setText(teachers.getEmail());
                                }
                                else{
                                }
                        }

                        @Override
                        public void onFailure(Call<Teachers> call, Throwable t) {

                        }
                });
        }


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);
                Bundle bundle = getIntent().getExtras();
                String role = bundle.getString("role");
                String teacherID = bundle.getString("teacherID");
                String token = bundle.getString("token");
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("teacherID", teacherID);
                bundlez.putString("token", token);

                switch (item.getItemId()){
                        case R.id.teacherprofile:
                                startActivity(new Intent(this, TeachersProfileActivity.class).putExtras(bundlez));
                                overridePendingTransition(0,0);
                                break;

                        case R.id.teacherclass:
                                startActivity(new Intent(this, AttendanceHomeActivity.class).putExtras(bundlez));
                                overridePendingTransition(0,0);
                                break;

                        case R.id.teacherschedule:
                                startActivity(new Intent(this, TeachersScheduleActivity.class).putExtras(bundlez));
                                overridePendingTransition(0,0);
                                break;

                        case R.id.teacheradvisee:
                                startActivity(new Intent(this, TeachersAdviseeActivity.class).putExtras(bundlez));
                                overridePendingTransition(0,0);
                                break;

                        case R.id.teacherlogout:
                                AlertDialog.Builder builder = new AlertDialog.Builder(TeachersDrawerActivity.this);
                                builder.setTitle("LOGOUT");
                                builder.setMessage("Are you sure want to exit?");
                                builder.setCancelable(false);

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(TeachersDrawerActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(TeachersDrawerActivity.this, SplashscreenActivity.class));
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
