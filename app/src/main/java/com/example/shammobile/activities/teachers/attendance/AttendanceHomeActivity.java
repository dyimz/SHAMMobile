package com.example.shammobile.activities.teachers.attendance;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.TeachersDrawerActivity;
import com.example.shammobile.databinding.TeachersAttendanceHomeBinding;
import com.example.shammobile.models.AttendanceHome;
import com.example.shammobile.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceHomeActivity extends TeachersDrawerActivity {

    ListView listView;
    List<AttendanceHome> attendancehomes = new ArrayList<AttendanceHome>();
    TeachersAttendanceHomeBinding teachersAttendanceHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teachersAttendanceHomeBinding = TeachersAttendanceHomeBinding.inflate(getLayoutInflater());
        setContentView(teachersAttendanceHomeBinding.getRoot());
        allocateActiviyTitle("Classes");

        Bundle bundle = getIntent().getExtras();

        String teacherID = bundle.getString("teacherID");
        listView = (ListView)findViewById(R.id.listView);

        Call<AttendanceHome> callattendancehome = APIUtils.getUserService().getAttendancehome(teacherID);
        callattendancehome.enqueue(new Callback<AttendanceHome>() {
            @Override
            public void onResponse(Call<AttendanceHome> callattendancehome, Response<AttendanceHome> response) {

                if (response.isSuccessful()) {
                    attendancehomes = response.body().getData();
                    listView.setAdapter(new AttendanceHomeAdapter(AttendanceHomeActivity.this, R.layout.teachers_attendance_homeadapter, attendancehomes, bundle));
                }
                else{
                    Toast.makeText(AttendanceHomeActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<AttendanceHome> call, Throwable t) {
                Toast.makeText(AttendanceHomeActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(AttendanceHomeActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
            }});

    }
}