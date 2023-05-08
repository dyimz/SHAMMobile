package com.example.shammobile.activities.teachers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersEditBackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_editbackground);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");
        String teacherID = bundle.getString("teacherID");
        String token = bundle.getString("token");

        String majorr = bundle.getString("major");
        String certificatee = bundle.getString("certificate");
        String minorr = bundle.getString("minor");
        String positionn = bundle.getString("position");
        String numberofteachingg = bundle.getString("numberofteaching");
        String educattainmentt = bundle.getString("educattainment");


        TextInputLayout major = (TextInputLayout) findViewById(R.id.major);
        TextInputLayout certificate = (TextInputLayout) findViewById(R.id.certificate);
        TextInputLayout minor = (TextInputLayout) findViewById(R.id.minor);
        TextInputLayout position = (TextInputLayout) findViewById(R.id.position);
        TextInputLayout numberofteaching = (TextInputLayout) findViewById(R.id.numberofteaching);
        TextInputLayout educattainment = (TextInputLayout) findViewById(R.id.educattainment);

        major.getEditText().setText(majorr);
        certificate.getEditText().setText(certificatee);
        minor.getEditText().setText(minorr);
        position.getEditText().setText(positionn);
        numberofteaching.getEditText().setText(numberofteachingg);
        educattainment.getEditText().setText(educattainmentt);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("teacherID", teacherID);
                bundlez.putString("token", token);
                startActivity(new Intent(TeachersEditBackgroundActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
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
                startActivity(new Intent(TeachersEditBackgroundActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TeachersEditBackgroundActivity.this);
                builder.setTitle("Editing Your Background Information");
                builder.setMessage("Are you sure you want to make this changes?");

                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TeachersEditBackgroundActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                        Teachers a = new Teachers();
                        a.setMajor(major.getEditText().getText().toString());
                        a.setCertificate(certificate.getEditText().getText().toString());
                        a.setMajor(minor.getEditText().getText().toString());
                        a.setPosition(position.getEditText().getText().toString());
                        a.setNumberofteaching(numberofteaching.getEditText().getText().toString());
                        a.setEducattainment(educattainment.getEditText().getText().toString());


                        Call<Teachers> call = APIUtils.getUserService().editTeacherbackground(teacherID, a);
                        call.enqueue(new Callback<Teachers>() {
                            @Override
                            public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(TeachersEditBackgroundActivity.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                                    Bundle bundlez = new Bundle();
                                    bundlez.putString("role", role);
                                    bundlez.putString("teacherID", teacherID);
                                    bundlez.putString("token", token);
                                    startActivity(new Intent(TeachersEditBackgroundActivity.this, TeachersProfileActivity.class).putExtras(bundlez));
                                }
                                else{
                                    Toast.makeText(TeachersEditBackgroundActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
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
}