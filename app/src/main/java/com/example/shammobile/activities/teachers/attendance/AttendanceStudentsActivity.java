package com.example.shammobile.activities.teachers.attendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.TeachersProfileActivity;
import com.example.shammobile.models.Attendance;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceStudentsActivity extends AppCompatActivity {

    ListView listView;
    List<Students> attendances = new ArrayList<Students>();
    String okay = "okay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_attendance_students);

        Bundle bundle = getIntent().getExtras();
        String SectionID = bundle.getString("SectionID");
        String SectionName = bundle.getString("SectionName");
        String CurriculumName = bundle.getString("CurriculumName");
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        TextView tSectionName = (TextView) findViewById(R.id.SectionName);
        TextView tCurriculumName = (TextView) findViewById(R.id.CurriculumName);

        tSectionName.setText(SectionName + "\r\n" + date);
        tCurriculumName.setText(CurriculumName);

        listView = (ListView)findViewById(R.id.listView);

        Call<Students> callattendance = APIUtils.getUserService().getAttendance(SectionID);
        callattendance.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> callattendance, Response<Students> response) {

                if (response.isSuccessful()) {
                    attendances = response.body().getData();
                    listView.setAdapter(new AttendanceStudentsAdapter(AttendanceStudentsActivity.this, R.layout.teachers_attendance_studentsadapter, attendances, bundle));
                }
                else{
                    Toast.makeText(AttendanceStudentsActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<Students> call, Throwable t) {
                Toast.makeText(AttendanceStudentsActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }});


        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role = bundle.getString("role");
                String teacherID = bundle.getString("teacherID");
                String token = bundle.getString("token");
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("teacherID", teacherID);
                bundlez.putString("token", token);
                startActivity(new Intent(AttendanceStudentsActivity.this, AttendanceHomeActivity.class).putExtras(bundlez));
            }
        });

        Toolbar btnBackArrow = (Toolbar) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role = bundle.getString("role");
                String teacherID = bundle.getString("teacherID");
                String token = bundle.getString("token");
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("teacherID", teacherID);
                bundlez.putString("token", token);
                startActivity(new Intent(AttendanceStudentsActivity.this, AttendanceHomeActivity.class).putExtras(bundlez));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceStudentsActivity.this);
                builder.setTitle("Save attendance record");
                builder.setMessage("Do you want to save this record?");

                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AttendanceStudentsActivity.this, "Saving...", Toast.LENGTH_LONG).show();

                        String teacherID = bundle.getString("teacherID");
                        String CurriculumID = bundle.getString("CurriculumID");
                        String SectionID = bundle.getString("SectionID");
                        String YearID = bundle.getString("YearID");
                        String SemesterID = bundle.getString("SemesterID");
                        String role = bundle.getString("role");
                        String token = bundle.getString("token");

                        for (i = 0; i < attendances.size(); i++) {
                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                            Attendance a = new Attendance();
                            a.setStudentID(attendances.get(i).getId());
                            a.setStatus(attendances.get(i).getAttendance());
                            a.setDate(date);
                            a.setTeacherID(teacherID);
                            a.setCurriculumID(CurriculumID);
                            a.setSectionID(SectionID);
                            a.setYearID(YearID);
                            a.setSemesterID(SemesterID);
                            Call<Attendance> call = APIUtils.getUserService().addAttendance(a);
                            call.enqueue(new Callback<Attendance>() {
                                @Override
                                public void onResponse(Call<Attendance> call, Response<Attendance> response) {
                                    if (response.isSuccessful()) {

//                                        Toast.makeText(AttendanceStudentsActivity.this, "added", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        okay = "failed";
                                        Toast.makeText(AttendanceStudentsActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Attendance> call, Throwable t) {
                                    Log.e("Error: ", t.getMessage());
                                }
                            });

                        }
                        if(okay.equals("okay")){
                            Toast.makeText(AttendanceStudentsActivity.this, "Attendance Made Successfully", Toast.LENGTH_SHORT).show();
                            Bundle bundlez = new Bundle();
                            bundlez.putString("role", role);
                            bundlez.putString("teacherID", teacherID);
                            bundlez.putString("token", token);
                            startActivity(new Intent(AttendanceStudentsActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
                        }
                        else{
                            Toast.makeText(AttendanceStudentsActivity.this, "Attendance Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });

                builder.create().show();

            }
        });

    }
}