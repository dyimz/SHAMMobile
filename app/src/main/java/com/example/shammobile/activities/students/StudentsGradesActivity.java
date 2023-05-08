package com.example.shammobile.activities.students;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.databinding.StudentsGradesBinding;
import com.example.shammobile.models.StudentsGrades;
import com.example.shammobile.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsGradesActivity extends StudentsDrawerActivity {

    StudentsGradesBinding studentsGradesBinding;
    ListView listView;
    List<StudentsGrades> grades = new ArrayList<StudentsGrades>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentsGradesBinding = StudentsGradesBinding.inflate(getLayoutInflater());
        setContentView(studentsGradesBinding.getRoot());
        allocateActiviyTitle("Grades");

        Bundle bundle = getIntent().getExtras();
        String studentID = bundle.getString("studentID");

        listView = (ListView)findViewById(R.id.listView);

        Call<StudentsGrades> callgrades = APIUtils.getUserService().getStudentsgrade(studentID);
        callgrades.enqueue(new Callback<StudentsGrades>() {
            @Override
            public void onResponse(Call<StudentsGrades> callgrades, Response<StudentsGrades> response) {
                if (response.isSuccessful()) {
                    grades = response.body().getData();
                    listView.setAdapter(new StudentsGradesAdapter(StudentsGradesActivity.this, R.layout.students_gradesadapter, grades, bundle));
                }
                else{
                    Toast.makeText(StudentsGradesActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<StudentsGrades> call, Throwable t) {
                Toast.makeText(StudentsGradesActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(StudentsGradesActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
            }});

    }
}