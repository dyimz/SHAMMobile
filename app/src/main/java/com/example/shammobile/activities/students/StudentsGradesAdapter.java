package com.example.shammobile.activities.students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shammobile.R;
import com.example.shammobile.models.Students;
import com.example.shammobile.models.StudentsGrades;
import com.example.shammobile.remote.APIUtils;

import java.util.List;

public class StudentsGradesAdapter extends ArrayAdapter<StudentsGrades> {
    private Bundle bundle;
    private Context context;
    private List<StudentsGrades> grades;

    public StudentsGradesAdapter(@NonNull Context context, int resource, @NonNull List<StudentsGrades> objects, Bundle bundle) {
        super(context, resource, objects);
        this.context = context;
        this.grades = objects;
        this.bundle = bundle;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.students_gradesadapter, parent, false);

        TextView CurriculumName = (TextView) rowView.findViewById(R.id.CurriculumName);
        TextView TeacherName = (TextView) rowView.findViewById(R.id.TeacherName);
        TextView QuarterOne = (TextView) rowView.findViewById(R.id.QuarterOne);
        TextView QuarterTwo = (TextView) rowView.findViewById(R.id.QuarterTwo);
        TextView FinalGrade = (TextView) rowView.findViewById(R.id.FinalGrade);
        TextView Remarks = (TextView) rowView.findViewById(R.id.Remarks);

        CurriculumName.setText(String.format("%s", grades.get(pos).getCurriculumName()));
        TeacherName.setText(String.format("%s", grades.get(pos).getTeacherName()));

        if (grades.get(pos).getQuarterone() == null) {
            QuarterOne.setText(String.format("Q1: \t\t\t\t\t\t\t\t\t\t\t\t -"));
        } else {
            QuarterOne.setText(String.format("Q1: \t\t\t\t\t\t\t\t\t\t\t\t %s", grades.get(pos).getQuarterone()));
        }

        if (grades.get(pos).getQuartertwo() == null) {
            QuarterTwo.setText(String.format("Q2: \t\t\t\t\t\t\t\t\t\t\t\t -"));
        } else {
            QuarterTwo.setText(String.format("Q2: \t\t\t\t\t\t\t\t\t\t\t\t %s", grades.get(pos).getQuartertwo()));
        }

        if (grades.get(pos).getFinalgrade() == null) {
            FinalGrade.setText(String.format("Final: \t\t\t\t\t\t\t\t\t\t -"));
        } else {
            FinalGrade.setText(String.format("Final: \t\t\t\t\t\t\t\t\t\t %s", grades.get(pos).getFinalgrade()));
        }

        if (grades.get(pos).getRemarks() == null) {
            Remarks.setText(String.format("Remarks: \t\t\t\t\t\t -"));
        } else {
            Remarks.setText(String.format("Remarks: \t\t\t\t\t\t %s", grades.get(pos).getRemarks()));
        }

//        QuarterOne.setText(String.format("Q1: \t\t\t\t\t\t\t\t\t\t\t\t %s", grades.get(pos).getQuarterone()));
//        QuarterTwo.setText(String.format("Q2: \t\t\t\t\t\t\t\t\t\t\t\t %s", grades.get(pos).getQuartertwo()));
//        FinalGrade.setText(String.format("Final: \t\t\t\t\t\t\t\t\t\t %s", grades.get(pos).getFinalgrade()));
//        Remarks.setText(String.format("Remarks: \t\t\t\t\t\t %s", grades.get(pos).getRemarks()));

        return rowView;
    }

}