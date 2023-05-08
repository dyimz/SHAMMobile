package com.example.shammobile.activities.teachers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shammobile.R;
import com.example.shammobile.models.ChangePassword;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_changepassword);

        Bundle bundle = getIntent().getExtras();
        String teacherID = bundle.getString("teacherID");
        String token = bundle.getString("token");

        EditText currentpassword = findViewById(R.id.currentpassword);
        currentpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= currentpassword.getRight() - currentpassword.getCompoundDrawables()[2].getBounds().width()) {
                        togglePasswordVisibility(currentpassword);
                        return true;
                    }
                }
                return false;
            }
        });

        EditText newpassword = findViewById(R.id.newpassword);
        newpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= newpassword.getRight() - newpassword.getCompoundDrawables()[2].getBounds().width()) {
                        togglePasswordVisibility(newpassword);
                        return true;
                    }
                }
                return false;
            }
        });

        EditText cnewpassword = findViewById(R.id.cnewpassword);
        cnewpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= cnewpassword.getRight() - cnewpassword.getCompoundDrawables()[2].getBounds().width()) {
                        togglePasswordVisibility(cnewpassword);
                        return true;
                    }
                }
                return false;
            }
        });

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeachersChangePassword.this, TeachersProfileActivity.class).putExtras(bundle));
            }
        });

        Toolbar btnBackArrow = (Toolbar) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeachersChangePassword.this, TeachersProfileActivity.class).putExtras(bundle));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(newpassword.getText().toString()) || TextUtils.isEmpty(cnewpassword.getText().toString())){
                    Toast.makeText(getApplicationContext(), "New Password Required", Toast.LENGTH_SHORT).show();
                }
                else if (newpassword.getText().toString().equals(cnewpassword.getText().toString())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TeachersChangePassword.this);
                    builder.setTitle("Editing Your Password");
                    builder.setMessage("Are you sure you want to make this changes?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ChangePassword a = new ChangePassword();
                            a.setCurrentpassword(currentpassword.getText().toString());
                            a.setNewpassword(newpassword.getText().toString());

                            Call<ChangePassword> call = APIUtils.getUserService().editTeacherpassword(teacherID, a);
                            call.enqueue(new Callback<ChangePassword>() {
                                @Override
                                public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(TeachersChangePassword.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(TeachersChangePassword.this, TeachersProfileActivity.class).putExtras(bundle));
                                    }
                                    else{
                                        Toast.makeText(TeachersChangePassword.this, "Check Current Password", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ChangePassword> call, Throwable t) {
                                    Log.e("Error: ", t.getMessage());
                                    Toast.makeText(TeachersChangePassword.this, "Check Internet Connection or Restart the App", Toast.LENGTH_LONG).show();
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
                } else {
                    Toast.makeText(getApplicationContext(), "New Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void togglePasswordVisibility(EditText passwordEditText) {
        int selectionStart = passwordEditText.getSelectionStart();
        int selectionEnd = passwordEditText.getSelectionEnd();
        if (passwordEditText.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_toggle_visibility_off, 0);
        } else {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_toggle_visibility, 0);
        }
        passwordEditText.setSelection(selectionStart, selectionEnd);
    }

}