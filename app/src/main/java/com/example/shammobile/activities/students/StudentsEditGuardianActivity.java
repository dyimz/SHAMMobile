package com.example.shammobile.activities.students;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.shammobile.R;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsEditGuardianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_editguardian);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");
        String studentID = bundle.getString("studentID");
        String token = bundle.getString("token");

        String mothernamee = bundle.getString("mothername");
        String mothercontactt = bundle.getString("mothercontact");
        String fathernamee = bundle.getString("fathername");
        String fathercontactt = bundle.getString("fathercontact");
        String guardiannamee = bundle.getString("guardianname");
        String guardiancontactt = bundle.getString("guardiancontact");

        TextInputLayout mothername = (TextInputLayout) findViewById(R.id.mothername);
        TextInputLayout mothercontact = (TextInputLayout) findViewById(R.id.mothercontact);
        TextInputLayout fathername = (TextInputLayout) findViewById(R.id.fathername);
        TextInputLayout fathercontact = (TextInputLayout) findViewById(R.id.fathercontact);
        TextInputLayout guardianname = (TextInputLayout) findViewById(R.id.guardianname);
        TextInputLayout guardiancontact = (TextInputLayout) findViewById(R.id.guardiancontact);

        mothername.getEditText().setText(mothernamee);
        mothercontact.getEditText().setText(mothercontactt);
        fathername.getEditText().setText(fathernamee);
        fathercontact.getEditText().setText(fathercontactt);
        guardianname.getEditText().setText(guardiannamee);
        guardiancontact.getEditText().setText(guardiancontactt);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundlez = new Bundle();
                bundlez.putString("role", role);
                bundlez.putString("studentID", studentID);
                bundlez.putString("token", token);
                startActivity(new Intent(StudentsEditGuardianActivity.this, StudentsProfileActivity.class).putExtras(bundlez));
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
                startActivity(new Intent(StudentsEditGuardianActivity.this, StudentsProfileActivity.class).putExtras(bundlez));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentsEditGuardianActivity.this);
                builder.setTitle("Editing Your Parent/Guardian Information");
                builder.setMessage("Are you sure you want to make this changes?");

                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(StudentsEditGuardianActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                        Students a = new Students();

                        a.setMothername(mothername.getEditText().getText().toString());
                        a.setMothercontact(mothercontact.getEditText().getText().toString());
                        a.setFathername(fathername.getEditText().getText().toString());
                        a.setFathercontact(fathercontact.getEditText().getText().toString());
                        a.setGuardianname(guardianname.getEditText().getText().toString());
                        a.setGuardiancontact(guardiancontact.getEditText().getText().toString());

                        Call<Students> call = APIUtils.getUserService().editStudentguardian(studentID, a);
                        call.enqueue(new Callback<Students>() {
                            @Override
                            public void onResponse(Call<Students> call, Response<Students> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(StudentsEditGuardianActivity.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                                    Bundle bundlez = new Bundle();
                                    bundlez.putString("role", role);
                                    bundlez.putString("studentID", studentID);
                                    bundlez.putString("token", token);
                                    startActivity(new Intent(StudentsEditGuardianActivity.this, StudentsProfileActivity.class).putExtras(bundlez));
                                }
                                else{
                                    Toast.makeText(StudentsEditGuardianActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
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