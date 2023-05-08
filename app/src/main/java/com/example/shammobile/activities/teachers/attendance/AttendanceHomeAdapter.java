package com.example.shammobile.activities.teachers.attendance;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shammobile.R;
import com.example.shammobile.models.AttendanceHome;

import java.util.List;

public class AttendanceHomeAdapter extends ArrayAdapter<AttendanceHome> {
    private Bundle bundle;
    private Context context;
    private List<AttendanceHome> attendancehomes;

    public AttendanceHomeAdapter(@NonNull Context context, int resource, @NonNull List<AttendanceHome> objects, Bundle bundle) {
        super(context, resource, objects);
        this.context = context;
        this.attendancehomes = objects;
        this.bundle = bundle;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.teachers_attendance_homeadapter, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.CurriculumName);
        TextView txtFName = (TextView) rowView.findViewById(R.id.SectionName);

        txtUserId.setText(String.format("%s", attendancehomes.get(pos).getCurriculumName()));
        txtFName.setText(String.format("%s", attendancehomes.get(pos).getSectionName()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("SectionID", attendancehomes.get(pos).getSectionID());
                bundle.putString("SectionName", attendancehomes.get(pos).getSectionName());
                bundle.putString("CurriculumName", attendancehomes.get(pos).getCurriculumName());
                bundle.putString("YearID", attendancehomes.get(pos).getYearID());
                bundle.putString("SemesterID", attendancehomes.get(pos).getSemesterID());
                context.startActivity(new Intent(context, AttendanceStudentsActivity.class).putExtras(bundle));
            }
        });

        return rowView;
    }

}