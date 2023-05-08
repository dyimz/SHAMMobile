package com.example.shammobile.activities.teachers.attendance;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shammobile.R;
import com.example.shammobile.models.Students;

import java.util.List;

public class AttendanceStudentsAdapter extends ArrayAdapter<Students> {
    private Bundle bundle;
    private Context context;
    private List<Students> attendances;

    public AttendanceStudentsAdapter(@NonNull Context context, int resource, @NonNull List<Students> objects, Bundle bundle) {
        super(context, resource, objects);
        this.context = context;
        this.attendances = objects;
        this.bundle = bundle;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.teachers_attendance_studentsadapter, parent, false);

        RadioGroup Attendance =(RadioGroup) rowView.findViewById(R.id.attendance);
        RadioButton attendance =(RadioButton) rowView.findViewById(Attendance.getCheckedRadioButtonId());
        attendances.get(pos).setAttendance(attendance.getText().toString());

        Attendance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton attendance =(RadioButton) rowView.findViewById(Attendance.getCheckedRadioButtonId());
                attendances.get(pos).setAttendance(attendance.getText().toString());
            }
        });

        TextView studentName = (TextView) rowView.findViewById(R.id.studentName);
        //TextView GenderAge = (TextView) rowView.findViewById(R.id.GenderAge);

        studentName.setText(String.format("%s, %s %s", attendances.get(pos).getLname(), attendances.get(pos).getFname(), attendances.get(pos).getMname()));
        //GenderAge.setText(String.format("%s , %s", attendances.get(pos).getGender(), attendances.get(pos).getAge()));

        return rowView;
    }
}