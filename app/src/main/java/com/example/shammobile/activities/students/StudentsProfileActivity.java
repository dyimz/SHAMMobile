package com.example.shammobile.activities.students;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.TeachersChangePassword;
import com.example.shammobile.activities.teachers.TeachersEditPictureActivity;
import com.example.shammobile.activities.teachers.TeachersProfileActivity;
import com.example.shammobile.databinding.StudentsProfileBinding;
import com.example.shammobile.models.Students;
import com.example.shammobile.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsProfileActivity extends StudentsDrawerActivity {

    StudentsProfileBinding studentsProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentsProfileBinding = StudentsProfileBinding.inflate(getLayoutInflater());
        setContentView(studentsProfileBinding.getRoot());
        allocateActiviyTitle("Profile");

        Bundle bundle = getIntent().getExtras();
        String studentID = bundle.getString("studentID");
        Log.e("Tag", studentID);

        Call<Students> callstudents = APIUtils.getUserService().getStudent(studentID);
        callstudents.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                if (response.isSuccessful()) {
                    Students students = response.body();

                    TextView strand = (TextView) findViewById(R.id.strand);
                    TextView lrn = (TextView) findViewById(R.id.lrn);
                    TextView email = (TextView) findViewById(R.id.email);
                    TextView name = (TextView) findViewById(R.id.name);
                    TextView age = (TextView) findViewById(R.id.age);
                    TextView birthdate = (TextView) findViewById(R.id.birthdate);
                    TextView gender = (TextView) findViewById(R.id.gender);
                    TextView contact = (TextView) findViewById(R.id.contact);
                    TextView mothertongue = (TextView) findViewById(R.id.mothertongue);
                    TextView religion = (TextView) findViewById(R.id.religion); 
                    TextView address = (TextView) findViewById(R.id.address);
                    TextView mothername = (TextView) findViewById(R.id.mothername);
                    TextView mothercontact = (TextView) findViewById(R.id.mothercontact);
                    TextView fathername = (TextView) findViewById(R.id.fathername);
                    TextView fathercontact = (TextView) findViewById(R.id.fathercontact);
                    TextView guardianname = (TextView) findViewById(R.id.guardianname);
                    TextView guardiancontact = (TextView) findViewById(R.id.guardiancontact);
                    ImageView image = (ImageView) findViewById(R.id.image);

                    Button changepass = (Button) findViewById(R.id.changepass);
                    changepass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(StudentsProfileActivity.this, StudentsChangePassword.class).putExtras(bundle));
                        }
                    });

                    ImageView chooseimage = (ImageView) findViewById(R.id.chooseimage);
                    chooseimage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bundle.putString("image", students.getImage());
                            startActivity(new Intent(StudentsProfileActivity.this, StudentsEditPictureActivity.class).putExtras(bundle));
                        }
                    });

                    ImageView teachereditinfo = (ImageView) findViewById(R.id.studenteditinfo);
                    teachereditinfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bundle.putString("birthdate", students.getBirthdate());
                            bundle.putString("age", students.getAge());
                            bundle.putString("contact", students.getContact());
                            bundle.putString("gender", students.getGender());
                            bundle.putString("mothertongue", students.getMothertongue());
                            bundle.putString("religion", students.getReligion());
                            bundle.putString("housestreet", students.getHousestreet());
                            bundle.putString("barangay", students.getBarangay());
                            bundle.putString("city", students.getCity());
                            bundle.putString("province", students.getProvince());
                            bundle.putString("region", students.getRegion());
                            startActivity(new Intent(StudentsProfileActivity.this, StudentsEditInformationActivity.class).putExtras(bundle));
                        }
                    });

                    ImageView teachereditbackground = (ImageView) findViewById(R.id.studenteditguardian);
                    teachereditbackground.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bundle.putString("mothername", students.getMothername());
                            bundle.putString("mothercontact", students.getMothercontact());
                            bundle.putString("fathername", students.getFathername());
                            bundle.putString("fathercontact", students.getFathercontact());
                            bundle.putString("guardianname", students.getGuardianname());
                            bundle.putString("guardiancontact", students.getGuardiancontact());
                            startActivity(new Intent(StudentsProfileActivity.this, StudentsEditGuardianActivity.class).putExtras(bundle));
                        }
                    });

                    strand.setText("STRAND & SECTION: " + students.getStrandID() + " " + students.getSectionID());
                    lrn.setText("LRN: " + students.getLrn());
                    email.setText("EMAIL: " + students.getEmail());
                    age.setText(students.getAge());
                    birthdate.setText(students.getBirthdate());
                    gender.setText(students.getGender());
                    contact.setText("CONTACT: " + students.getContact());
                    mothertongue.setText(students.getMothertongue());
                    religion.setText(students.getReligion());
                    address.setText(students.getHousestreet() + " " + students.getBarangay() + " "
                            + students.getCity() + " " + students.getProvince() + " " + students.getRegion());
                    mothername.setText(students.getMothername());
                    mothercontact.setText(students.getMothercontact());
                    fathername.setText(students.getFathername());
                    fathercontact.setText(students.getFathercontact());
                    guardianname.setText(students.getGuardianname());
                    guardiancontact.setText(students.getGuardiancontact());

                    RequestOptions requestOptions = new RequestOptions()
                    .override(1000, 1000)
                    .centerCrop()
                    .encodeQuality(75);

                    Glide.with(StudentsProfileActivity.this)
                            .load(APIUtils.API_URL + students.getImage())
                            .apply(requestOptions)
                            .into(image);

                    String fullname = students.getExtname();
                    if (fullname.equals("N/A")) {
                        name.setText(students.getFname() + " " + students.getMname() + " " + students.getLname());
                    } else {
                        name.setText(students.getFname() + " " + students.getMname() + " " + students.getLname() + " " + students.getExtname());
                    }

                }
                else{
                    Toast.makeText(StudentsProfileActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {
                Toast.makeText(StudentsProfileActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(StudentsProfileActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
            }});


    }
}