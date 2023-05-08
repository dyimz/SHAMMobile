package com.example.shammobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shammobile.activities.students.StudentsProfileActivity;
import com.example.shammobile.activities.teachers.TeachersProfileActivity;
import com.example.shammobile.models.LoginRequest;
import com.example.shammobile.models.LoginResponse;
import com.example.shammobile.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[2].getBounds().width()) {
                        togglePasswordVisibility(password);
                        return true;
                    }
                }
                return false;
            }
        });

        Button btnLogin = (Button)findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Email / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_LONG).show();
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(email.getText().toString());
                    loginRequest.setPassword(password.getText().toString());

                    Call<LoginResponse> loginResponseCall = APIUtils.getUserService().userLogin(loginRequest);
                    loginResponseCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            if (response.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                LoginResponse loginResponse = response.body();
                                String role = loginResponse.getRole();

                                if (role.equals("student")){
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("role", loginResponse.getRole());
                                            bundle.putString("studentID", loginResponse.getStudentID());
                                            bundle.putString("token", loginResponse.getToken());
                                            startActivity(new Intent(LoginActivity.this, StudentsProfileActivity.class).putExtras(bundle));
                                        }
                                    },700);}

                                else if (role.equals("teacher")){
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("role", loginResponse.getRole());
                                            bundle.putString("teacherID", loginResponse.getTeacherID());
                                            bundle.putString("token", loginResponse.getToken());
                                            startActivity(new Intent(LoginActivity.this, TeachersProfileActivity.class).putExtras(bundle));
                                        }
                                    },700);}

                                else{
                                    Toast.makeText(LoginActivity.this, "For Teachers/Students Only", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                Toast.makeText(LoginActivity.this, "Check Email or Password", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this, "Check Internet Connection or Restart the App", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }

    private void togglePasswordVisibility(EditText passwordEditText) {
        int selectionStart = passwordEditText.getSelectionStart();
        int selectionEnd = passwordEditText.getSelectionEnd();
        if (passwordEditText.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_lock_24, 0, R.drawable.ic_baseline_remove_red_eye_24off, 0);
        } else {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_lock_24, 0, R.drawable.ic_baseline_remove_red_eye_24, 0);
        }
        passwordEditText.setSelection(selectionStart, selectionEnd);
    }
}