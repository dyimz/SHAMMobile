package com.example.shammobile.activities.teachers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shammobile.R;
import com.example.shammobile.models.TeachersSchedule;

import java.util.List;

public class TeachersScheduleAdapter extends ArrayAdapter<TeachersSchedule> {
    private Bundle bundle;
    private Context context;
    private List<TeachersSchedule> schedules;

    public TeachersScheduleAdapter(@NonNull Context context, int resource, @NonNull List<TeachersSchedule> objects, Bundle bundle) {
        super(context, resource, objects);
        this.context = context;
        this.schedules = objects;
        this.bundle = bundle;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.teachers_scheduleadapter, parent, false);


        TextView CurriculumName = (TextView) rowView.findViewById(R.id.CurriculumName);
        TextView SectionName = (TextView) rowView.findViewById(R.id.SectionName);
        TextView Room = (TextView) rowView.findViewById(R.id.Room);
        TextView Time = (TextView) rowView.findViewById(R.id.Time);


        CurriculumName.setText(String.format("%s", schedules.get(pos).getCurriculumName()));
        SectionName.setText(String.format("%s", schedules.get(pos).getSectionName()));
        Room.setText(String.format("%s", schedules.get(pos).getRoom()));
        Time.setText(String.format("%s to %s", schedules.get(pos).getStart(), schedules.get(pos).getEnd()));

        return rowView;
    }

}