package com.example.shammobile.activities.teachers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.attendance.AttendanceStudentsActivity;
import com.example.shammobile.models.Attendance;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersEditInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextInputLayout civilstatus;
    AutoCompleteTextView dropdown_civilstatus;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_editinformation);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");
        String teacherID = bundle.getString("teacherID");
        String token = bundle.getString("token");

        String birthdatee = bundle.getString("birthdate");
        String agee = bundle.getString("age");
        String genderr = bundle.getString("gender");
        String civilstatuss = bundle.getString("civilstatus");
        String contactt = bundle.getString("contact");
        String addresss = bundle.getString("address");

        TextView birthdate = (TextView) findViewById(R.id.birthdate);
        TextInputLayout age = (TextInputLayout) findViewById(R.id.age);
        RadioGroup Gender =(RadioGroup) findViewById(R.id.Gender);

        RadioButton gendermale =(RadioButton) findViewById(R.id.Gendermale);
        RadioButton genderfemale =(RadioButton) findViewById(R.id.Genderfemale);
        civilstatus = findViewById(R.id.civilstatus);

        TextInputLayout contact = (TextInputLayout) findViewById(R.id.contact);
        TextInputLayout address = (TextInputLayout) findViewById(R.id.address);

        birthdate.setText(birthdatee);
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TeachersEditInformationActivity.this,
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

        if (genderr.equals("female")){
            genderfemale.setChecked(true);
        }
        else if (genderr.equals("male")){
            gendermale.setChecked(true);
        }

        civilstatus = findViewById(R.id.civilstatus);
        dropdown_civilstatus = findViewById(R.id.dropdown_civilstatus);
        ArrayAdapter<CharSequence> civilstatusadapter = ArrayAdapter.createFromResource(this,
                R.array.teachercivilstatus, R.layout.dropdown_item);
        civilstatusadapter.setDropDownViewResource(R.layout.dropdown_item);
        dropdown_civilstatus.setAdapter(civilstatusadapter);
        dropdown_civilstatus.setOnItemSelectedListener(this);

        dropdown_civilstatus.setText(civilstatuss, false);

        age.getEditText().setText(agee);
        contact.getEditText().setText(contactt);
        address.getEditText().setText(addresss);

//        if (civilstatuss.equals("single")){
//            dropdown_civilstatus.setSelection(0);
//        }
//        else if (civilstatuss.equals("married")){
//            dropdown_civilstatus.setSelection(1);
//        }
//        else if (civilstatuss.equals("divorced")){
//            dropdown_civilstatus.setSelection(2);
//        }
//        else if (civilstatuss.equals("separated")){
//            dropdown_civilstatus.setSelection(3);
//        }
//        else if (civilstatuss.equals("widowed")){
//            dropdown_civilstatus.setSelection(4);
//        }

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("teacherID", teacherID);
                bundlez.putString("token", token);
                startActivity(new Intent(TeachersEditInformationActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
            }
        });

        Toolbar btnBackArrow = (Toolbar) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("teacherID", teacherID);
                bundlez.putString("token", token);
                startActivity(new Intent(TeachersEditInformationActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TeachersEditInformationActivity.this);
                builder.setTitle("Editing Your Personal Information");
                builder.setMessage("Are you sure you want to make this changes?");

                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TeachersEditInformationActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                        RadioButton gender =(RadioButton) findViewById(Gender.getCheckedRadioButtonId());
                        Teachers a = new Teachers();
                        a.setBirthdate(birthdate.getText().toString());
                        a.setAge(age.getEditText().getText().toString());
                        a.setGender(gender.getText().toString());
                        a.setCivilstatus(civilstatus.getEditText().getText().toString());
                        a.setContact(contact.getEditText().getText().toString());
                        a.setAddress(address.getEditText().getText().toString());

                        Call<Teachers> call = APIUtils.getUserService().editTeacherinfo(teacherID, a);
                        call.enqueue(new Callback<Teachers>() {
                            @Override
                            public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(TeachersEditInformationActivity.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                                    Bundle bundlez = new Bundle();
                                    bundlez.putString("role", role);
                                    bundlez.putString("teacherID", teacherID);
                                    bundlez.putString("token", token);
                                    startActivity(new Intent(TeachersEditInformationActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
                                }
                                else{
                                    Toast.makeText(TeachersEditInformationActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Teachers> call, Throwable t) {
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}