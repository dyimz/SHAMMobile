package com.example.shammobile.activities.teachers;

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
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;
import com.example.shammobile.databinding.TeachersProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersProfileActivity extends TeachersDrawerActivity {

    TeachersProfileBinding teachersProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teachersProfileBinding = TeachersProfileBinding.inflate(getLayoutInflater());
        setContentView(teachersProfileBinding.getRoot());
        allocateActiviyTitle("Profile");

        Bundle bundle = getIntent().getExtras();
        String teacherID = bundle.getString("teacherID");

        Call<Teachers> callteachers = APIUtils.getUserService().getTeacher(teacherID);

        callteachers.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                if (response.isSuccessful()) {
                    Teachers teachers = response.body();

                    TextView name = (TextView) findViewById(R.id.name);
                    TextView contact = (TextView) findViewById(R.id.contact);
                    TextView email = (TextView) findViewById(R.id.email);
                    TextView gender = (TextView) findViewById(R.id.gender);
                    TextView age = (TextView) findViewById(R.id.age);
                    TextView civilstatus = (TextView) findViewById(R.id.civilstatus);
                    TextView birthdate = (TextView) findViewById(R.id.birthdate);
                    TextView address = (TextView) findViewById(R.id.address);
                    TextView certificate = (TextView) findViewById(R.id.certificate);
                    TextView major = (TextView) findViewById(R.id.major);
                    TextView minor = (TextView) findViewById(R.id.minor);
                    TextView position = (TextView) findViewById(R.id.position);
                    TextView numberofteaching = (TextView) findViewById(R.id.numberofteaching);
                    TextView educattainment = (TextView) findViewById(R.id.educattainment);
                    ImageView image = (ImageView) findViewById(R.id.image);

                    Button changepass = (Button) findViewById(R.id.changepass);
                    changepass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(TeachersProfileActivity.this, TeachersChangePassword.class).putExtras(bundle));
                        }
                    });

                    ImageView chooseimage = (ImageView) findViewById(R.id.chooseimage);
                    chooseimage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bundle.putString("image", teachers.getImage());
                            startActivity(new Intent(TeachersProfileActivity.this, TeachersEditPictureActivity.class).putExtras(bundle));
                        }
                    });

                    ImageView teachereditinfo = (ImageView) findViewById(R.id.teachereditinfo);
                    teachereditinfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bundle.putString("birthdate", teachers.getBirthdate());
                            bundle.putString("age", teachers.getAge());
                            bundle.putString("gender", teachers.getGender());
                            bundle.putString("civilstatus", teachers.getCivilstatus());
                            bundle.putString("contact", teachers.getContact());
                            bundle.putString("address", teachers.getAddress());
                            startActivity(new Intent(TeachersProfileActivity.this, TeachersEditInformationActivity.class).putExtras(bundle));
                        }
                    });

                    ImageView teachereditbackground = (ImageView) findViewById(R.id.teachereditbackground);
                    teachereditbackground.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bundle.putString("major", teachers.getMajor());
                            bundle.putString("certificate", teachers.getCertificate());
                            bundle.putString("minor", teachers.getMinor());
                            bundle.putString("position", teachers.getPosition());
                            bundle.putString("numberofteaching", teachers.getNumberofteaching());
                            bundle.putString("educattainment", teachers.getEducattainment());
                            startActivity(new Intent(TeachersProfileActivity.this, TeachersEditBackgroundActivity.class).putExtras(bundle));
                        }
                    });

                    name.setText(teachers.getFname() + " " + teachers.getMname() + " " + teachers.getLname());
                    contact.setText("CONTACT: " + teachers.getContact());
                    email.setText("EMAIL: " + teachers.getEmail());
                    gender.setText(teachers.getGender());
                    age.setText(teachers.getAge());
                    civilstatus.setText(teachers.getCivilstatus());
                    birthdate.setText(teachers.getBirthdate());
                    address.setText("ADDRESS: " + teachers.getAddress());
                    certificate.setText(teachers.getCertificate());
                    major.setText(teachers.getMajor());
                    minor.setText(teachers.getMinor());
                    position.setText(teachers.getPosition());
                    numberofteaching.setText(teachers.getNumberofteaching());
                    educattainment.setText(teachers.getEducattainment());

                    RequestOptions requestOptions = new RequestOptions()
                            .override(1000, 1000)
                            .centerCrop()
                            .encodeQuality(75);

                    Glide.with(TeachersProfileActivity.this)
                            .load(APIUtils.API_URL + teachers.getImage())
                            .apply(requestOptions)
                            .into(image);

                }
                else{
                    Toast.makeText(TeachersProfileActivity.this, "Profile Failed to Load", Toast.LENGTH_LONG).show();
                    Toast.makeText(TeachersProfileActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) {
                Toast.makeText(TeachersProfileActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(TeachersProfileActivity.this, "Please Restart the APP", Toast.LENGTH_LONG).show();
            }});
    }
}