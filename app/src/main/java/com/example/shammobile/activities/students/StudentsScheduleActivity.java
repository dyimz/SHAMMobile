package com.example.shammobile.activities.students;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.databinding.StudentsScheduleBinding;
import com.example.shammobile.models.StudentsSchedule;
import com.example.shammobile.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsScheduleActivity extends StudentsDrawerActivity {

    StudentsScheduleBinding studentsScheduleBinding;

    ListView listViewmonday;
    ListView listViewtuesday;
    ListView listViewwednesday;
    ListView listViewthursday;
    ListView listViewfriday;
    List<StudentsSchedule> schedulemonday = new ArrayList<StudentsSchedule>();
    List<StudentsSchedule> scheduletuesday = new ArrayList<StudentsSchedule>();
    List<StudentsSchedule> schedulewednesday = new ArrayList<StudentsSchedule>();
    List<StudentsSchedule> schedulethursday = new ArrayList<StudentsSchedule>();
    List<StudentsSchedule> schedulefriday = new ArrayList<StudentsSchedule>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentsScheduleBinding = StudentsScheduleBinding.inflate(getLayoutInflater());
        setContentView(studentsScheduleBinding.getRoot());
        allocateActiviyTitle("Schedule");

        Bundle bundle = getIntent().getExtras();
        String studentID = bundle.getString("studentID");

        listViewmonday = (ListView)findViewById(R.id.listView1);
        listViewtuesday = (ListView)findViewById(R.id.listView2);
        listViewwednesday = (ListView)findViewById(R.id.listView3);
        listViewthursday = (ListView)findViewById(R.id.listView4);
        listViewfriday = (ListView)findViewById(R.id.listView5);
        TextView monday = (TextView) findViewById(R.id.monday);
        TextView tuesday = (TextView) findViewById(R.id.tuesday);
        TextView wednesday = (TextView) findViewById(R.id.wednesday);
        TextView thursday = (TextView) findViewById(R.id.thursday);
        TextView friday = (TextView) findViewById(R.id.friday);

        Call<StudentsSchedule> call = APIUtils.getUserService().getStudentschedule(studentID);
        call.enqueue(new Callback<StudentsSchedule>() {
            @Override
            public void onResponse(Call<StudentsSchedule> call, Response<StudentsSchedule> response) {
                if (response.isSuccessful()) {
                    schedulemonday = response.body().getMonday();
                    scheduletuesday = response.body().getTuesday();
                    schedulewednesday = response.body().getWednesday();
                    schedulethursday = response.body().getThursday();
                    schedulefriday = response.body().getFriday();

                    listViewmonday.setAdapter(new StudentsScheduleAdapter(StudentsScheduleActivity.this, R.layout.teachers_scheduleadapter, schedulemonday, bundle));
                    ListAdapter mondaylistAdapter = listViewmonday.getAdapter();
                    int mondaytotalHeight = 0;
                    for (int i = 0; i < mondaylistAdapter.getCount(); i++) {
                        View mondaylistItem = mondaylistAdapter.getView(i, null, listViewmonday);
                        monday.setTextSize(30);
                        monday.setText("MONDAY");
                        if(mondaylistItem != null){
                            mondaylistItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                            mondaylistItem.measure(View.MeasureSpec.makeMeasureSpec(listViewmonday.getWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
                            mondaytotalHeight += mondaylistItem.getMeasuredHeight();}
                    }
                    ViewGroup.LayoutParams mondayparams = listViewmonday.getLayoutParams();
                    mondayparams.height = mondaytotalHeight + (mondaylistAdapter.getCount() * 10);
                    listViewmonday.setLayoutParams(mondayparams);

                    listViewtuesday.setAdapter(new StudentsScheduleAdapter(StudentsScheduleActivity.this, R.layout.teachers_scheduleadapter, scheduletuesday, bundle));
                    ListAdapter tuesdaylistAdapter = listViewtuesday.getAdapter();
                    int tuesdaytotalHeight = 0;
                    for (int i = 0; i < tuesdaylistAdapter.getCount(); i++) {
                        View tuesdaylistItem = tuesdaylistAdapter.getView(i, null, listViewtuesday);
                        tuesday.setTextSize(30);
                        tuesday.setText("TUESDAY");
                        if(tuesdaylistItem != null){
                            tuesdaylistItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                            tuesdaylistItem.measure(View.MeasureSpec.makeMeasureSpec(listViewtuesday.getWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
                            tuesdaytotalHeight += tuesdaylistItem.getMeasuredHeight();}
                    }
                    ViewGroup.LayoutParams tuesdayparams = listViewtuesday.getLayoutParams();
                    tuesdayparams.height = tuesdaytotalHeight + (tuesdaylistAdapter.getCount() * 10);
                    listViewtuesday.setLayoutParams(tuesdayparams);

                    listViewwednesday.setAdapter(new StudentsScheduleAdapter(StudentsScheduleActivity.this, R.layout.teachers_scheduleadapter, schedulewednesday, bundle));
                    ListAdapter wednesdaylistAdapter = listViewwednesday.getAdapter();
                    int wednesdaytotalHeight = 0;
                    for (int i = 0; i < wednesdaylistAdapter.getCount(); i++) {
                        View wednesdaylistItem = wednesdaylistAdapter.getView(i, null, listViewwednesday);
                        wednesday.setTextSize(30);
                        wednesday.setText("WEDNESDAY");
                        if(wednesdaylistItem != null){
                            wednesdaylistItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                            wednesdaylistItem.measure(View.MeasureSpec.makeMeasureSpec(listViewwednesday.getWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
                            wednesdaytotalHeight += wednesdaylistItem.getMeasuredHeight();}
                    }
                    ViewGroup.LayoutParams wednesdayparams = listViewwednesday.getLayoutParams();
                    wednesdayparams.height = wednesdaytotalHeight + (wednesdaylistAdapter.getCount() * 10);
                    listViewwednesday.setLayoutParams(wednesdayparams);

                    listViewthursday.setAdapter(new StudentsScheduleAdapter(StudentsScheduleActivity.this, R.layout.teachers_scheduleadapter, schedulethursday, bundle));
                    ListAdapter thursdaylistAdapter = listViewthursday.getAdapter();
                    int thursdaytotalHeight = 0;
                    for (int i = 0; i < thursdaylistAdapter.getCount(); i++) {
                        View thursdaylistItem = thursdaylistAdapter.getView(i, null, listViewthursday);
                        thursday.setTextSize(30);
                        thursday.setText("THURSDAY");
                        if(thursdaylistItem != null){
                            thursdaylistItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                            thursdaylistItem.measure(View.MeasureSpec.makeMeasureSpec(listViewthursday.getWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
                            thursdaytotalHeight += thursdaylistItem.getMeasuredHeight();}
                    }
                    ViewGroup.LayoutParams thursdayparams = listViewthursday.getLayoutParams();
                    thursdayparams.height = thursdaytotalHeight + (thursdaylistAdapter.getCount() * 10);
                    listViewthursday.setLayoutParams(thursdayparams);

                    listViewfriday.setAdapter(new StudentsScheduleAdapter(StudentsScheduleActivity.this, R.layout.teachers_scheduleadapter, schedulefriday, bundle));
                    ListAdapter fridaylistAdapter = listViewfriday.getAdapter();
                    int fridaytotalHeight = 0;
                    for (int i = 0; i < fridaylistAdapter.getCount(); i++) {
                        View fridaylistItem = fridaylistAdapter.getView(i, null, listViewfriday);
                        friday.setTextSize(30);
                        friday.setText("FRIDAY");
                        if(fridaylistItem != null){
                            fridaylistItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                            fridaylistItem.measure(View.MeasureSpec.makeMeasureSpec(listViewfriday.getWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
                            fridaytotalHeight += fridaylistItem.getMeasuredHeight();}
                    }
                    ViewGroup.LayoutParams fridayparams = listViewfriday.getLayoutParams();
                    fridayparams.height = fridaytotalHeight + (fridaylistAdapter.getCount() * 10);
                    listViewfriday.setLayoutParams(fridayparams);

                }
                else{
                    Toast.makeText(StudentsScheduleActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<StudentsSchedule> call, Throwable t) {
                Toast.makeText(StudentsScheduleActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(StudentsScheduleActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
            }});


    }
}