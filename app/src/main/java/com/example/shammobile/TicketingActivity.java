package com.example.shammobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shammobile.activities.students.StudentsEditInformationActivity;
import com.example.shammobile.activities.students.StudentsProfileActivity;
import com.example.shammobile.activities.teachers.TeachersProfileActivity;
import com.example.shammobile.models.LoginRequest;
import com.example.shammobile.models.LoginResponse;
import com.example.shammobile.models.Ticket;
import com.example.shammobile.remote.APIUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);

        TextInputLayout ticketLayout = findViewById(R.id.ticket);
        TextInputEditText ticketEditText = (TextInputEditText) ticketLayout.getEditText();
        TextView statusTextView = findViewById(R.id.status);

        ticketEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed. We don't need to do anything here.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is changed.
                statusTextView.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text is changed. We don't need to do anything here.
            }
        });

        Toolbar btnBackArrow = (Toolbar) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketingActivity.this, MainActivity.class));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(TicketingActivity.this, "Checking Application...", Toast.LENGTH_LONG).show();
                Ticket ticket = new Ticket();
                ticket.setTicket(ticketLayout.getEditText().getText().toString());

                Call<Ticket> call = APIUtils.getUserService().getTicket(ticket);
                call.enqueue(new Callback<Ticket>() {
                    @Override
                    public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                        if (response.isSuccessful()) {
                            Ticket ticketresponse = response.body();
                            String message = ticketresponse.getMessage();
                            statusTextView.setText(message);
                        }
                        else {
                            Toast.makeText(TicketingActivity.this, "Checking Failed", Toast.LENGTH_LONG).show();
                            Toast.makeText(TicketingActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ticket> call, Throwable t) {
                        Toast.makeText(TicketingActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(TicketingActivity.this, "Check Internet Connection or Restart the App", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}