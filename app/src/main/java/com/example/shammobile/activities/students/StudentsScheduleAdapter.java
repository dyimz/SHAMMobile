package com.example.shammobile.activities.students;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shammobile.R;
import com.example.shammobile.models.StudentsSchedule;

import java.util.List;

public class StudentsScheduleAdapter extends ArrayAdapter<StudentsSchedule> {
    private Bundle bundle;
    private Context context;
    private List<StudentsSchedule> schedules;

    public StudentsScheduleAdapter(@NonNull Context context, int resource, @NonNull List<StudentsSchedule> objects, Bundle bundle) {
        super(context, resource, objects);
        this.context = context;
        this.schedules = objects;
        this.bundle = bundle;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.students_scheduleadapter, parent, false);


        TextView CurriculumName = (TextView) rowView.findViewById(R.id.CurriculumName);
        TextView TeacherName = (TextView) rowView.findViewById(R.id.TeacherName);
        TextView SectionName = (TextView) rowView.findViewById(R.id.SectionName);
        TextView Room = (TextView) rowView.findViewById(R.id.Room);
        TextView Time = (TextView) rowView.findViewById(R.id.Time);


        CurriculumName.setText(String.format("%s", schedules.get(pos).getCurriculumName()));
        TeacherName.setText(String.format("%s", schedules.get(pos).getTeacherName()));
        SectionName.setText(String.format("%s", schedules.get(pos).getSectionName()));
        Room.setText(String.format("%s", schedules.get(pos).getRoom()));
        Time.setText(String.format("%s to %s", schedules.get(pos).getStart(), schedules.get(pos).getEnd()));

        return rowView;
    }

}