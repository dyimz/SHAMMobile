package com.example.shammobile.activities.teachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.attendance.AttendanceStudentsActivity;
import com.example.shammobile.models.AttendanceHome;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;

import java.util.List;

public class TeachersAdviseeAdapter extends ArrayAdapter<Students> {
    private Bundle bundle;
    private Context context;
    private List<Students> students;

    public TeachersAdviseeAdapter(@NonNull Context context, int resource, @NonNull List<Students> objects, Bundle bundle) {
        super(context, resource, objects);
        this.context = context;
        this.students = objects;
        this.bundle = bundle;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.teachers_adviseeadapter, parent, false);

        ImageView image = (ImageView) rowView.findViewById(R.id.StudentImage);
        TextView txtname = (TextView) rowView.findViewById(R.id.StudentName);
        TextView txtgender = (TextView) rowView.findViewById(R.id.StudentGender);

        RequestOptions requestOptions = new RequestOptions()
                .override(1000, 1000)
                .centerCrop()
                .encodeQuality(75);

        Glide.with(context)
                .load(APIUtils.API_URL + students.get(pos).getImage())
                .apply(requestOptions)
                .into(image);

        txtname.setText(String.format("%s, %s", students.get(pos).getLname(), students.get(pos).getFname()));
        txtgender.setText(String.format("%s , %s", students.get(pos).getGender(), students.get(pos).getAge()));

        return rowView;
    }

}