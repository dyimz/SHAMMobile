package com.example.shammobile.activities.students;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.TeachersEditInformationActivity;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsEditInformationActivity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_editinformation);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");
        String studentID = bundle.getString("studentID");
        String token = bundle.getString("token");

        String birthdatee = bundle.getString("birthdate");
        String agee = bundle.getString("age");
        String contactt = bundle.getString("contact");
        String genderr = bundle.getString("gender");
        String mothertonguee = bundle.getString("mothertongue");
        String religionn = bundle.getString("religion");
        String housestreett = bundle.getString("housestreet");
        String barangayy = bundle.getString("barangay");
        String cityy = bundle.getString("city");
        String provincee = bundle.getString("province");
        String regionn = bundle.getString("region");

        TextView birthdate = (TextView) findViewById(R.id.birthdate);
        TextInputLayout age = (TextInputLayout) findViewById(R.id.age);
        TextInputLayout contact = (TextInputLayout) findViewById(R.id.contact);
        RadioGroup Gender =(RadioGroup) findViewById(R.id.Gender);
        RadioButton gendermale =(RadioButton) findViewById(R.id.Gendermale);
        RadioButton genderfemale =(RadioButton) findViewById(R.id.Genderfemale);
        TextInputLayout mothertongue = (TextInputLayout) findViewById(R.id.mothertongue);
        TextInputLayout religion = (TextInputLayout) findViewById(R.id.religion);
        TextInputLayout housestreet = (TextInputLayout) findViewById(R.id.housestreet);
        TextInputLayout barangay = (TextInputLayout) findViewById(R.id.barangay);
        TextInputLayout city = (TextInputLayout) findViewById(R.id.city);
        TextInputLayout province = (TextInputLayout) findViewById(R.id.province);
        TextInputLayout region = (TextInputLayout) findViewById(R.id.region);

        birthdate.setText(birthdatee);
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        StudentsEditInformationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "-" + day + "-" + year;
                birthdate.setText(date);
            }
        };

        age.getEditText().setText(agee);
        contact.getEditText().setText(contactt);

        if (genderr.equals("female")){
            genderfemale.setChecked(true);
        }
        else if (genderr.equals("male")){
            gendermale.setChecked(true);
        }

        mothertongue.getEditText().setText(mothertonguee);
        religion.getEditText().setText(religionn);
        housestreet.getEditText().setText(housestreett);
        barangay.getEditText().setText(barangayy);
        city.getEditText().setText(cityy);
        province.getEditText().setText(provincee);
        region.getEditText().setText(regionn);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("studentID", studentID);
                bundlez.putString("token", token);
                startActivity(new Intent(StudentsEditInformationActivity.this, StudentsProfileActivity.class).putExtras(bundlez));
            }
        });

        Toolbar btnBackArrow = (Toolbar) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("studentID", studentID);
                bundlez.putString("token", token);
                startActivity(new Intent(StudentsEditInformationActivity.this, StudentsProfileActivity.class).putExtras(bundlez));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentsEditInformationActivity.this);
                builder.setTitle("Editing Your Personal Information");
                builder.setMessage("Are you sure you want to make this changes?");

                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(StudentsEditInformationActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                        RadioButton gender =(RadioButton) findViewById(Gender.getCheckedRadioButtonId());
                        Students a = new Students();
                        a.setBirthdate(birthdate.getText().toString());
                        a.setAge(age.getEditText().getText().toString());
                        a.setContact(contact.getEditText().getText().toString());
                        a.setGender(gender.getText().toString());
                        a.setMothertongue(mothertongue.getEditText().getText().toString());
                        a.setReligion(religion.getEditText().getText().toString());
                        a.setHousestreet(housestreet.getEditText().getText().toString());
                        a.setBarangay(barangay.getEditText().getText().toString());
                        a.setCity(city.getEditText().getText().toString());
                        a.setProvince(province.getEditText().getText().toString());
                        a.setRegion(region.getEditText().getText().toString());

                        Call<Students> call = APIUtils.getUserService().editStudentinfo(studentID, a);
                        call.enqueue(new Callback<Students>() {
                            @Override
                            public void onResponse(Call<Students> call, Response<Students> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(StudentsEditInformationActivity.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                                    Bundle bundlez = new Bundle();
                                    bundlez.putString("role", role);
                                    bundlez.putString("studentID", studentID);
                                    bundlez.putString("token", token);
                                    startActivity(new Intent(StudentsEditInformationActivity.this, StudentsProfileActivity.class).putExtras(bundlez));
                                }
                                else{
                                    Toast.makeText(StudentsEditInformationActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Students> call, Throwable t) {
                                Log.e("Error: ", t.getMessage());
                            }
                        });

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });

                builder.create().show();

            }
        });
    }
}