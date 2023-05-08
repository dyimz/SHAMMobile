package com.example.shammobile.activities.teachers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.databinding.TeachersAdviseeBinding;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersAdviseeActivity extends TeachersDrawerActivity {

    TeachersAdviseeBinding teachersAdviseeBinding;
    ListView listView;
    List<Students> students = new ArrayList<Students>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teachersAdviseeBinding = TeachersAdviseeBinding.inflate(getLayoutInflater());
        setContentView(teachersAdviseeBinding.getRoot());
        allocateActiviyTitle("Advisees");

        Bundle bundle = getIntent().getExtras();
        String teacherID = bundle.getString("teacherID");

        listView = (ListView)findViewById(R.id.listView);

        Call<Students> callstudents = APIUtils.getUserService().getTeacheradvisee(teacherID);
        callstudents.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> callstudents, Response<Students> response) {

                if (response.isSuccessful()) {
                    students = response.body().getData();
                    listView.setAdapter(new TeachersAdviseeAdapter(TeachersAdviseeActivity.this, R.layout.teachers_adviseeadapter, students, bundle));
                }
                else{
                    Toast.makeText(TeachersAdviseeActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<Students> call, Throwable t) {
                Toast.makeText(TeachersAdviseeActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(TeachersAdviseeActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
            }});

    }
}