package com.example.shammobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shammobile.models.Applicants;
import com.example.shammobile.models.Ticket;
import com.example.shammobile.remote.APIUtils;
import com.example.shammobile.remote.UserService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private TextWatcher contactNumberTextWatcher;
    ImageView image;
    ImageView chooseimage;
    ImageView card;
    ImageView choosecard;
    ImageView cert;
    ImageView choosecert;
    Bitmap bitmapimage;
    Bitmap bitmapcard;
    Bitmap bitmapcert;
    private final int GALLERY_REQ_IMAGE = 1000;
    private final int GALLERY_REQ_CARD = 2000;
    private final int GALLERY_REQ_CERT = 3000;

    UserService userService;
    RadioGroup StudStat;
    RadioGroup LrnStat;
    RadioGroup SchoolType;
    RadioGroup Gender;
    RadioGroup Fatherworkstat;
    RadioGroup Motherworkstat;
    RadioGroup Guardianworkstat;

    RadioGroup Indigenous;
    RadioGroup Special;
    RadioGroup Devices;


    RadioGroup HasGuardian;

    LinearLayout gradeLevelDetails;
    LinearLayout gradelevel;
    LinearLayout personalinfoDetails;
    LinearLayout personalinfo;
    LinearLayout parentinfoDetails;
    LinearLayout parentinfo;
    ImageView arrowUp;
    TextInputLayout Yeartofinish;
    AutoCompleteTextView dropdown_Yeartofinish;
    TextInputLayout Firstchoice;
    AutoCompleteTextView dropdown_Firstchoice;
    TextInputLayout Secondchoice;
    AutoCompleteTextView dropdown_Secondchoice;
    TextInputLayout Thirdchoice;
    AutoCompleteTextView dropdown_Thirdchoice;
    TextInputLayout Extname;
    AutoCompleteTextView dropdown_Extname;
    TextInputLayout Fathereducation;
    AutoCompleteTextView dropdown_Fathereducation;
    TextInputLayout Mothereducation;
    AutoCompleteTextView dropdown_Mothereducation;
    TextInputLayout Guardianeducation;
    AutoCompleteTextView dropdown_Guardianeducation;
    TextInputLayout Fatheremployment;
    AutoCompleteTextView dropdown_Fatheremployment;
    TextInputLayout Motheremployment;
    AutoCompleteTextView dropdown_Motheremployment;
    TextInputLayout Guardianemployment;
    AutoCompleteTextView dropdown_Guardianemployment;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        image = (ImageView) findViewById(R.id.image);
        chooseimage = (ImageView) findViewById(R.id.chooseimage);
        chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent();
                iGallery.setType("image/*");
                iGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(iGallery,GALLERY_REQ_IMAGE);
            }
        });

        card = (ImageView) findViewById(R.id.card);
        choosecard = (ImageView) findViewById(R.id.choosecard);
        choosecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent();
                iGallery.setType("image/*");
                iGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(iGallery,GALLERY_REQ_CARD);
            }
        });

        cert = (ImageView) findViewById(R.id.cert);
        choosecert = (ImageView) findViewById(R.id.choosecert);
        choosecert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent();
                iGallery.setType("image/*");
                iGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(iGallery,GALLERY_REQ_CERT);
            }
        });

        gradeLevelDetails = findViewById(R.id.gradeleveldetails);
        gradelevel = findViewById(R.id.gradelevel);
        personalinfoDetails = findViewById(R.id.personalinfodetails);
        personalinfo = findViewById(R.id.personalinfo);
        parentinfoDetails = findViewById(R.id.parentinfodetails);
        parentinfo = findViewById(R.id.parentinfo);

        StudStat=(RadioGroup) findViewById(R.id.StudStat);
        LrnStat=(RadioGroup) findViewById(R.id.LrnStat);
        SchoolType=(RadioGroup) findViewById(R.id.SchoolType);
        Gender=(RadioGroup) findViewById(R.id.Gender);
        Fatherworkstat=(RadioGroup) findViewById(R.id.Fatherworkstat);
        Motherworkstat=(RadioGroup) findViewById(R.id.Motherworkstat);
        Guardianworkstat=(RadioGroup) findViewById(R.id.Guardianworkstat);

        Indigenous=(RadioGroup) findViewById(R.id.Indigenous);
        Special=(RadioGroup) findViewById(R.id.Special);
        Devices=(RadioGroup) findViewById(R.id.Devices);

        HasGuardian=(RadioGroup) findViewById(R.id.hasGuardian);

        TextInputLayout Section = (TextInputLayout) findViewById(R.id.Section);
        TextInputLayout edtULastschoolattended = (TextInputLayout) findViewById(R.id.edtULastschoolattended);
        TextInputLayout edtULastschooladdress = (TextInputLayout) findViewById(R.id.edtULastschooladdress);
        TextInputLayout edtUSchoolid = (TextInputLayout) findViewById(R.id.edtUSchoolid);
        TextInputLayout edtUEnglishgrade = (TextInputLayout) findViewById(R.id.edtUEnglishgrade);
        TextInputLayout edtUMathgrade = (TextInputLayout) findViewById(R.id.edtUMathgrade);
        TextInputLayout edtUSciencegrade = (TextInputLayout) findViewById(R.id.edtUSciencegrade);
        TextInputLayout edtUFilipinograde = (TextInputLayout) findViewById(R.id.edtUFilipinograde);
        TextInputLayout edtULRN= (TextInputLayout) findViewById(R.id.edtULRN);
        TextInputLayout edtUPSA= (TextInputLayout) findViewById(R.id.edtUPSA);
        TextInputLayout edtUEmail= (TextInputLayout) findViewById(R.id.edtUEmail);
        TextInputLayout edtUFname= (TextInputLayout) findViewById(R.id.edtUFname);
        TextInputLayout edtUMname= (TextInputLayout) findViewById(R.id.edtUMname);
        TextInputLayout edtULname= (TextInputLayout) findViewById(R.id.edtULname);
        TextInputLayout edtUAge = (TextInputLayout) findViewById(R.id.edtUAge);
        TextInputLayout edtUContact = (TextInputLayout) findViewById(R.id.edtUContact);
        TextInputLayout edtUMothertongue = (TextInputLayout) findViewById(R.id.edtUMothertongue);
        TextInputLayout edtUReligion = (TextInputLayout) findViewById(R.id.edtUReligion);
        TextInputLayout edtUIndigenous = (TextInputLayout) findViewById(R.id.edtUIndigenous);
        TextInputLayout edtUSpecial = (TextInputLayout) findViewById(R.id.edtUSpecial);
        TextInputLayout edtUDevices = (TextInputLayout) findViewById(R.id.edtUDevices);
        TextInputLayout edtUStreet = (TextInputLayout) findViewById(R.id.edtUStreet);
        TextInputLayout edtUBarangay = (TextInputLayout) findViewById(R.id.edtUBarangay);
        TextInputLayout edtUCity = (TextInputLayout) findViewById(R.id.edtUCity);
        TextInputLayout edtUProvince = (TextInputLayout) findViewById(R.id.edtUProvince);
        TextInputLayout edtURegion = (TextInputLayout) findViewById(R.id.edtURegion);
        TextInputLayout edtUFathername = (TextInputLayout) findViewById(R.id.edtUFathername);
        TextInputLayout edtUFathercontact = (TextInputLayout) findViewById(R.id.edtUFathercontact);
        TextInputLayout edtUMothername = (TextInputLayout) findViewById(R.id.edtUMothername);
        TextInputLayout edtUMothercontact = (TextInputLayout) findViewById(R.id.edtUMothercontact);
        TextInputLayout edtUGuardianname = (TextInputLayout) findViewById(R.id.edtUGuardianname);
        TextInputLayout edtUGuardiancontact = (TextInputLayout) findViewById(R.id.edtUGuardiancontact);

//        Button btnBack = (Button) findViewById(R.id.btnBack);
       Button btnSave = (Button) findViewById(R.id.btnSave);
        userService = APIUtils.getUserService();

        Yeartofinish = findViewById(R.id.Yeartofinish);
        dropdown_Yeartofinish = findViewById(R.id.dropdown_Yeartofinish);
        ArrayAdapter<CharSequence> Yeartofinishadapter = ArrayAdapter.createFromResource(this,
                R.array.yeartofinish, R.layout.dropdown_item);
        dropdown_Yeartofinish.setAdapter(Yeartofinishadapter);

        Firstchoice = findViewById(R.id.Firstchoice);
        dropdown_Firstchoice = findViewById(R.id.dropdown_Firstchoice);
        Secondchoice = findViewById(R.id.Secondchoice);
        dropdown_Secondchoice = findViewById(R.id.dropdown_Secondchoice);
        Thirdchoice = findViewById(R.id.Thirdchoice);
        dropdown_Thirdchoice = findViewById(R.id.dropdown_Thirdchoice);


        Extname = findViewById(R.id.Extname);
        dropdown_Extname = findViewById(R.id.dropdown_Extname);
        ArrayAdapter<CharSequence> Extnameadapter = ArrayAdapter.createFromResource(this,
                R.array.extname, R.layout.dropdown_item);
        dropdown_Extname.setAdapter(Extnameadapter);

        Fathereducation = findViewById(R.id.Fathereducation);
        dropdown_Fathereducation = findViewById(R.id.dropdown_Fathereducation);
        Mothereducation = findViewById(R.id.Mothereducation);
        dropdown_Mothereducation = findViewById(R.id.dropdown_Mothereducation);
        Guardianeducation = findViewById(R.id.Guardianeducation);
        dropdown_Guardianeducation = findViewById(R.id.dropdown_Guardianeducation);
        ArrayAdapter<CharSequence> Educationadapter = ArrayAdapter.createFromResource(this,
                R.array.education, R.layout.dropdown_item);
        dropdown_Fathereducation.setAdapter(Educationadapter);
        dropdown_Mothereducation.setAdapter(Educationadapter);
        dropdown_Guardianeducation.setAdapter(Educationadapter);

        Fatheremployment = findViewById(R.id.Fatheremployment);
        dropdown_Fatheremployment = findViewById(R.id.dropdown_Fatheremployment);
        Motheremployment = findViewById(R.id.Motheremployment);
        dropdown_Motheremployment = findViewById(R.id.dropdown_Motheremployment);
        Guardianemployment = findViewById(R.id.Guardianemployment);
        dropdown_Guardianemployment = findViewById(R.id.dropdown_Guardianemployment);
        ArrayAdapter<CharSequence> Employmentadapter = ArrayAdapter.createFromResource(this,
                R.array.employment, R.layout.dropdown_item);
        dropdown_Fatheremployment.setAdapter(Employmentadapter);
        dropdown_Motheremployment.setAdapter(Employmentadapter);
        dropdown_Guardianemployment.setAdapter(Employmentadapter);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrationActivity.this,
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
                mDisplayDate.setText(date);
            }
        };

        RadioButton SchoolTypePublic = findViewById(R.id.SchoolTypepublic);
        RadioButton SchoolTypePrivate = findViewById(R.id.SchoolTypeprivate);

        RadioButton IndigenousNo = findViewById(R.id.Indigenousfalse);
        RadioButton SpecialNo = findViewById(R.id.Specialfalse);
        RadioButton DevicesNo = findViewById(R.id.Devicesfalse);
        RadioButton HasGuardianNo = findViewById(R.id.hasGuardianno);
        RadioButton GuardianworkstatNo = findViewById(R.id.Guardianworkstatno);
        RadioButton HasGuardianYes = findViewById(R.id.hasGuardianyes);

        IndigenousNo.setChecked(true);
        SpecialNo.setChecked(true);
        DevicesNo.setChecked(true);
//        HasGuardianNo.setChecked(true);
//        GuardianworkstatNo.setChecked(true);

        edtUIndigenous.getEditText().setText("N/A");
        edtUSpecial.getEditText().setText("N/A");
        edtUDevices.getEditText().setText("N/A");

//        edtUGuardianname.getEditText().setText("N/A");
//        Guardianeducation.getEditText().setText("N/A");
//        Guardianemployment.getEditText().setText("N/A");
//        edtUGuardiancontact.getEditText().setText("N/A");

        edtUIndigenous.setEnabled(false);
        edtUSpecial.setEnabled(false);
        edtUDevices.setEnabled(false);

//        edtUGuardianname.setEnabled(false);
//        Guardianeducation.setEnabled(false);
//        Guardianemployment.setEnabled(false);
//        edtUGuardiancontact.setEnabled(false);

        StudStat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.StudStattrue) {
                    edtULastschoolattended.getEditText().setText("Signal Village National High School");
                    edtULastschoolattended.setEnabled(false);
                    edtULastschooladdress.getEditText().setText("Ballecer St., Central Signal Village, Taguig City");
                    edtULastschooladdress.setEnabled(false);
                    edtUSchoolid.getEditText().setText("305463");
                    edtUSchoolid.setEnabled(false);
                    SchoolTypePublic.setChecked(true);
                } else {
                    edtULastschoolattended.getEditText().setText("");
                    edtULastschoolattended.setEnabled(true);
                    edtULastschooladdress.getEditText().setText("");
                    edtULastschooladdress.setEnabled(true);
                    edtUSchoolid.getEditText().setText("");
                    edtUSchoolid.setEnabled(true);
                    SchoolTypePublic.setChecked(false);
                    SchoolTypePrivate.setChecked(false);
                }
            }
        });

        LrnStat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.LrnStatfalse) {
                    edtULRN.getEditText().setText("N/A");
                    edtULRN.setEnabled(false);
                } else {
                    edtULRN.getEditText().setText("");
                    edtULRN.setEnabled(true);
                }
            }
        });

        Indigenous.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.Indigenoustrue) {
                    edtUIndigenous.getEditText().setText("");
                    edtUIndigenous.setEnabled(true);
                } else {
                    edtUIndigenous.getEditText().setText("N/A");
                    edtUIndigenous.setEnabled(false);
                }
            }
        });

        Special.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.Specialtrue) {
                    edtUSpecial.getEditText().setText("");
                    edtUSpecial.setEnabled(true);
                } else {
                    edtUSpecial.getEditText().setText("N/A");
                    edtUSpecial.setEnabled(false);
                }
            }
        });

        Devices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.Devicestrue) {
                    edtUDevices.getEditText().setText("");
                    edtUDevices.setEnabled(true);
                } else {
                    edtUDevices.getEditText().setText("N/A");
                    edtUDevices.setEnabled(false);
                }
            }
        });

        InputFilter filter = new InputFilter() {
            int min = 1;
            int max = 100;

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    int input = Integer.parseInt(dest.toString() + source.toString());
                    if (input >= min && input <= max)
                        return null;
                } catch (NumberFormatException nfe) {
                    // do nothing
                }
                return "";
            }
        };

        edtUEnglishgrade.getEditText().setFilters(new InputFilter[]{filter});
        edtUMathgrade.getEditText().setFilters(new InputFilter[]{filter});
        edtUSciencegrade.getEditText().setFilters(new InputFilter[]{filter});
        edtUFilipinograde.getEditText().setFilters(new InputFilter[]{filter});

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed in this case
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Firstchoice.getEditText().setText("");
                Secondchoice.getEditText().setText("");
                Thirdchoice.getEditText().setText("");
                // Check if both Math and Science grades are between 85 to 99 (inclusive)
                if (edtUMathgrade.getEditText().getText().toString().matches("8[5-9]|[9][0-9]") &&
                        edtUSciencegrade.getEditText().getText().toString().matches("8[5-9]|[9][0-9]")) {
                    // Both grades are between 85 to 99 (inclusive), so set STEM field to "STEM"
                    ArrayAdapter<CharSequence> Strandchoiceadapter = ArrayAdapter.createFromResource(RegistrationActivity.this,
                            R.array.strandchoicestem, R.layout.dropdown_item);
                    dropdown_Firstchoice.setAdapter(Strandchoiceadapter);
                    dropdown_Secondchoice.setAdapter(Strandchoiceadapter);
                    dropdown_Thirdchoice.setAdapter(Strandchoiceadapter);
                } else {
                    // One or both grades are below 85 or above 99, so clear STEM field
                    ArrayAdapter<CharSequence> Strandchoiceadapter = ArrayAdapter.createFromResource(RegistrationActivity.this,
                            R.array.strandchoicenostem, R.layout.dropdown_item);
                    dropdown_Firstchoice.setAdapter(Strandchoiceadapter);
                    dropdown_Secondchoice.setAdapter(Strandchoiceadapter);
                    dropdown_Thirdchoice.setAdapter(Strandchoiceadapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed in this case
            }
        };

// Set the text watcher on both Math and Science grade fields
        edtUMathgrade.getEditText().addTextChangedListener(textWatcher);
        edtUSciencegrade.getEditText().addTextChangedListener(textWatcher);

        edtUContact.getEditText().addTextChangedListener(getContactNumberTextWatcher(edtUContact));
        edtUFathercontact.getEditText().addTextChangedListener(getContactNumberTextWatcher(edtUFathercontact));
        edtUMothercontact.getEditText().addTextChangedListener(getContactNumberTextWatcher(edtUMothercontact));
        edtUGuardiancontact.getEditText().addTextChangedListener(getContactNumberTextWatcher(edtUGuardiancontact));

        HasGuardian.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.hasGuardianyes) {
                    contactNumberTextWatcher = getContactNumberTextWatcher(edtUGuardiancontact);
                    edtUGuardiancontact.getEditText().addTextChangedListener(contactNumberTextWatcher);
                    edtUGuardianname.getEditText().setText("");
                    Guardianeducation.getEditText().setText("");
                    Guardianemployment.getEditText().setText("");
                    edtUGuardiancontact.getEditText().setText("");
                    edtUGuardianname.setEnabled(true);
                    Guardianeducation.setEnabled(true);
                    Guardianemployment.setEnabled(true);
                    edtUGuardiancontact.setEnabled(true);
                } else {
                    edtUGuardiancontact.getEditText().removeTextChangedListener(contactNumberTextWatcher);
                    GuardianworkstatNo.setChecked(true);
                    edtUGuardianname.getEditText().setText("N/A");
                    Guardianeducation.getEditText().setText("N/A");
                    Guardianemployment.getEditText().setText("N/A");
//                    String contact = edtUGuardiancontact.getEditText().getText().toString().replace("+63", "");
                    edtUGuardiancontact.getEditText().setText("+63111111111");
                    edtUGuardianname.setEnabled(false);
                    Guardianeducation.setEnabled(false);
                    Guardianemployment.setEnabled(false);
                    edtUGuardiancontact.setEnabled(false);
                }
            }
        });





        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton studstat = (RadioButton) findViewById(StudStat.getCheckedRadioButtonId());
                RadioButton lrnstat = (RadioButton) findViewById(LrnStat.getCheckedRadioButtonId());
                RadioButton schooltype = (RadioButton) findViewById(SchoolType.getCheckedRadioButtonId());
                RadioButton gender = (RadioButton) findViewById(Gender.getCheckedRadioButtonId());
                RadioButton fatherworkstat = (RadioButton) findViewById(Fatherworkstat.getCheckedRadioButtonId());
                RadioButton motherworkstat = (RadioButton) findViewById(Motherworkstat.getCheckedRadioButtonId());
                RadioButton guardianworkstat = (RadioButton) findViewById(Guardianworkstat.getCheckedRadioButtonId());
                RadioButton indigenous = (RadioButton) findViewById(Indigenous.getCheckedRadioButtonId());
                RadioButton special = (RadioButton) findViewById(Special.getCheckedRadioButtonId());
                RadioButton devices = (RadioButton) findViewById(Devices.getCheckedRadioButtonId());

                String uGuardiancontactt = edtUGuardiancontact.getEditText().getText().toString();
                String uContact = edtUContact.getEditText().getText().toString().trim();
                String uFathercontact = edtUFathercontact.getEditText().getText().toString().trim();
                String uMothercontact = edtUMothercontact.getEditText().getText().toString().trim();
                String uGuardiancontact = edtUGuardiancontact.getEditText().getText().toString().trim();

                if (bitmapimage == null
                        || bitmapcard == null
                        || bitmapcert == null) {
                    Toast.makeText(RegistrationActivity.this, "Please Fill out All IMAGES field", Toast.LENGTH_LONG).show();
                }
                else if (studstat == null
                        || lrnstat == null
                        || TextUtils.isEmpty(edtULRN.getEditText().getText().toString())
                        || TextUtils.isEmpty(Section.getEditText().getText().toString())
                        || TextUtils.isEmpty(Yeartofinish.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtULastschoolattended.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtULastschooladdress.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUSchoolid.getEditText().getText().toString())
                        || schooltype == null
                        || TextUtils.isEmpty(Firstchoice.getEditText().getText().toString())
                        || TextUtils.isEmpty(Secondchoice.getEditText().getText().toString())
                        || TextUtils.isEmpty(Thirdchoice.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUEnglishgrade.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUMathgrade.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUSciencegrade.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUFilipinograde.getEditText().getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please Fill out All GRADE LEVEL AND SCHOOL INFORMATION field", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(edtUPSA.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUEmail.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUFname.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtULname.getEditText().getText().toString())
                        || TextUtils.isEmpty(Extname.getEditText().getText().toString())
                        || TextUtils.isEmpty(mDisplayDate.getText().toString())
                        || TextUtils.isEmpty(edtUAge.getEditText().getText().toString())
                        || gender == null
                        || indigenous == null
                        || special == null
                        || devices == null
                        || TextUtils.isEmpty(edtUContact.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUMothertongue.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUReligion.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUIndigenous.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUSpecial.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUDevices.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUStreet.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUBarangay.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUCity.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUProvince.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtURegion.getEditText().getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please Fill out All PERSONAL INFORMATION field", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(edtUFathername.getEditText().getText().toString())
                        || TextUtils.isEmpty(Fathereducation.getEditText().getText().toString())
                        || TextUtils.isEmpty(Fatheremployment.getEditText().getText().toString())
                        || fatherworkstat == null
                        || TextUtils.isEmpty(edtUFathercontact.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUMothername.getEditText().getText().toString())
                        || TextUtils.isEmpty(Mothereducation.getEditText().getText().toString())
                        || TextUtils.isEmpty(Motheremployment.getEditText().getText().toString())
                        || motherworkstat == null
                        || TextUtils.isEmpty(edtUMothercontact.getEditText().getText().toString())
                        || TextUtils.isEmpty(edtUGuardianname.getEditText().getText().toString())
                        || TextUtils.isEmpty(Guardianeducation.getEditText().getText().toString())
                        || TextUtils.isEmpty(Guardianemployment.getEditText().getText().toString())
                        || guardianworkstat == null
                        || TextUtils.isEmpty(edtUGuardiancontact.getEditText().getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please Fill out All PARENT/GUARDIAN field", Toast.LENGTH_LONG).show();
                }
                 else if (uContact.length() != 12) {
                    Toast.makeText(RegistrationActivity.this, "Invalid Contact Number", Toast.LENGTH_LONG).show();
                }
                else if (uFathercontact.length() != 12) {
                    Toast.makeText(RegistrationActivity.this, "Invalid Father Contact Number", Toast.LENGTH_LONG).show();
                }
                else if (uMothercontact.length() != 12) {
                    Toast.makeText(RegistrationActivity.this, "Invalid Mother Contact Number", Toast.LENGTH_LONG).show();
                }
                 else if (uGuardiancontact.length() != 12) {
                     Toast.makeText(RegistrationActivity.this, "Invalid Guardian Contact Number", Toast.LENGTH_LONG).show();
                 }
                else {
                    Ticket ticket = new Ticket();
                    ticket.setTicket(edtUEmail.getEditText().getText().toString());

                    Call<Ticket> call = APIUtils.getUserService().getEmail(ticket);
                    call.enqueue(new Callback<Ticket>() {
                        @Override
                        public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                            if (response.isSuccessful()) {
                                Ticket ticketresponse = response.body();
                                String message = ticketresponse.getMessage();
                                if (message.equals("success")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                                    builder.setTitle("Would you like to submit the form? ");
                                    builder.setMessage("Please make sure all your details are correct and true. After submitting, you will not be able to edit and submit a new application. ");

                                    builder.setCancelable(false);

                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(RegistrationActivity.this, "Sending Registration...", Toast.LENGTH_LONG).show();
                                            HashMap<String, RequestBody> map = new HashMap<>();

                                            map.put("image", RequestBody.create(MediaType.parse("image/*"), imageToString()));
                                            map.put("reportcard", RequestBody.create(MediaType.parse("image/*"), cardToString()));
                                            map.put("birthcertificate", RequestBody.create(MediaType.parse("image/*"), certToString()));

                                            map.put("studentstatus", RequestBody.create(MediaType.parse("text/plain"), studstat.getText().toString()));
                                            map.put("lrnstat", RequestBody.create(MediaType.parse("text/plain"), lrnstat.getText().toString()));
                                            map.put("section", RequestBody.create(MediaType.parse("text/plain"), Section.getEditText().getText().toString()));

                                            map.put("yeartofinish", RequestBody.create(MediaType.parse("text/plain"), Yeartofinish.getEditText().getText().toString()));
                                            map.put("lastschoolattended", RequestBody.create(MediaType.parse("text/plain"), edtULastschoolattended.getEditText().getText().toString()));
                                            map.put("lastschooladdress", RequestBody.create(MediaType.parse("text/plain"), edtULastschooladdress.getEditText().getText().toString()));
                                            map.put("schoolid", RequestBody.create(MediaType.parse("text/plain"), edtUSchoolid.getEditText().getText().toString()));
                                            map.put("schooltype", RequestBody.create(MediaType.parse("text/plain"), schooltype.getText().toString()));

                                            map.put("firstchoice", RequestBody.create(MediaType.parse("text/plain"), Firstchoice.getEditText().getText().toString()));
                                            map.put("secondchoice", RequestBody.create(MediaType.parse("text/plain"), Secondchoice.getEditText().getText().toString()));
                                            map.put("thirdchoice", RequestBody.create(MediaType.parse("text/plain"), Thirdchoice.getEditText().getText().toString()));

                                            map.put("englishgrade", RequestBody.create(MediaType.parse("text/plain"), edtUEnglishgrade.getEditText().getText().toString()));
                                            map.put("mathgrade", RequestBody.create(MediaType.parse("text/plain"), edtUMathgrade.getEditText().getText().toString()));
                                            map.put("sciencegrade", RequestBody.create(MediaType.parse("text/plain"), edtUSciencegrade.getEditText().getText().toString()));
                                            map.put("filipinograde", RequestBody.create(MediaType.parse("text/plain"), edtUFilipinograde.getEditText().getText().toString()));

                                            map.put("lrn", RequestBody.create(MediaType.parse("text/plain"), edtULRN.getEditText().getText().toString()));
                                            map.put("psanumber", RequestBody.create(MediaType.parse("text/plain"), edtUPSA.getEditText().getText().toString()));
                                            map.put("email", RequestBody.create(MediaType.parse("text/plain"), edtUEmail.getEditText().getText().toString()));
                                            map.put("fname", RequestBody.create(MediaType.parse("text/plain"), edtUFname.getEditText().getText().toString()));

                                            if(TextUtils.isEmpty(edtUMname.getEditText().getText().toString())){
                                                map.put("mname", RequestBody.create(MediaType.parse("text/plain"), "N/A"));
                                            }
                                            else {
                                                map.put("mname", RequestBody.create(MediaType.parse("text/plain"), edtUMname.getEditText().getText().toString()));
                                            }

                                            map.put("lname", RequestBody.create(MediaType.parse("text/plain"), edtULname.getEditText().getText().toString()));
                                            map.put("extname", RequestBody.create(MediaType.parse("text/plain"), Extname.getEditText().getText().toString()));

                                            map.put("birthdate", RequestBody.create(MediaType.parse("text/plain"), mDisplayDate.getText().toString()));
                                            map.put("age", RequestBody.create(MediaType.parse("text/plain"), edtUAge.getEditText().getText().toString()));
                                            map.put("gender", RequestBody.create(MediaType.parse("text/plain"), gender.getText().toString()));
                                            map.put("contact", RequestBody.create(MediaType.parse("text/plain"), edtUContact.getEditText().getText().toString()));

                                            map.put("mothertongue", RequestBody.create(MediaType.parse("text/plain"), edtUMothertongue.getEditText().getText().toString()));
                                            map.put("religion", RequestBody.create(MediaType.parse("text/plain"), edtUReligion.getEditText().getText().toString()));

                                            map.put("indipeople", RequestBody.create(MediaType.parse("text/plain"), indigenous.getText().toString()));
                                            map.put("specialneeds", RequestBody.create(MediaType.parse("text/plain"), special.getText().toString()));
                                            map.put("assistivedevices", RequestBody.create(MediaType.parse("text/plain"), devices.getText().toString()));

                                            map.put("yesindipeople", RequestBody.create(MediaType.parse("text/plain"), edtUIndigenous.getEditText().getText().toString()));
                                            map.put("yesspecialneeds", RequestBody.create(MediaType.parse("text/plain"), edtUSpecial.getEditText().getText().toString()));
                                            map.put("yesassistivedevices", RequestBody.create(MediaType.parse("text/plain"), edtUDevices.getEditText().getText().toString()));

                                            map.put("housestreet", RequestBody.create(MediaType.parse("text/plain"), edtUStreet.getEditText().getText().toString()));
                                            map.put("barangay", RequestBody.create(MediaType.parse("text/plain"), edtUBarangay.getEditText().getText().toString()));
                                            map.put("city", RequestBody.create(MediaType.parse("text/plain"), edtUCity.getEditText().getText().toString()));
                                            map.put("province", RequestBody.create(MediaType.parse("text/plain"), edtUProvince.getEditText().getText().toString()));
                                            map.put("region", RequestBody.create(MediaType.parse("text/plain"), edtURegion.getEditText().getText().toString()));

                                            map.put("fathername", RequestBody.create(MediaType.parse("text/plain"), edtUFathername.getEditText().getText().toString()));
                                            map.put("fathereducation", RequestBody.create(MediaType.parse("text/plain"), Fathereducation.getEditText().getText().toString()));
                                            map.put("fatheremployment", RequestBody.create(MediaType.parse("text/plain"), Fatheremployment.getEditText().getText().toString()));
                                            map.put("fatherworkstat", RequestBody.create(MediaType.parse("text/plain"), fatherworkstat.getText().toString()));
                                            map.put("fathercontact", RequestBody.create(MediaType.parse("text/plain"), edtUFathercontact.getEditText().getText().toString()));

                                            map.put("mothername", RequestBody.create(MediaType.parse("text/plain"), edtUMothername.getEditText().getText().toString()));
                                            map.put("mothereducation", RequestBody.create(MediaType.parse("text/plain"), Mothereducation.getEditText().getText().toString()));
                                            map.put("motheremployment", RequestBody.create(MediaType.parse("text/plain"), Motheremployment.getEditText().getText().toString()));
                                            map.put("motherworkstat", RequestBody.create(MediaType.parse("text/plain"), motherworkstat.getText().toString()));
                                            map.put("mothercontact", RequestBody.create(MediaType.parse("text/plain"), edtUMothercontact.getEditText().getText().toString()));

                                            map.put("guardianname", RequestBody.create(MediaType.parse("text/plain"), edtUGuardianname.getEditText().getText().toString()));
                                            map.put("guardianeducation", RequestBody.create(MediaType.parse("text/plain"), Guardianeducation.getEditText().getText().toString()));
                                            map.put("guardianemployment", RequestBody.create(MediaType.parse("text/plain"), Guardianemployment.getEditText().getText().toString()));
                                            map.put("guardianworkstat", RequestBody.create(MediaType.parse("text/plain"), guardianworkstat.getText().toString()));
                                            map.put("guardiancontact", RequestBody.create(MediaType.parse("text/plain"), edtUGuardiancontact.getEditText().getText().toString()));

                                            Call<Applicants> call = userService.addApplicants(map);
                                            call.enqueue(new Callback<Applicants>() {
                                                @Override
                                                public void onResponse(Call<Applicants> call, Response<Applicants> response) {
                                                    if (response.isSuccessful()) {
                                                        Applicants applicants = response.body();
                                                        Toast.makeText(RegistrationActivity.this, "Applicant Registered Successfully", Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<Applicants> call, Throwable t) {
                                                    Toast.makeText(RegistrationActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                                                    Log.e("Error: ", t.getMessage());
                                                }
                                            });

                                            finish();
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
                                else if (message.equals("failed")){
                                    Toast.makeText(RegistrationActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Ticket> call, Throwable t) {
                            Toast.makeText(RegistrationActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(RegistrationActivity.this, "Check Internet Connection or Restart the App", Toast.LENGTH_LONG).show();
                        }
                    });




            }
            }
        });

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//            }
//        });
    }

    public void expandgrade(View view) {
        int g = (gradeLevelDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        // TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        gradeLevelDetails.setVisibility(g);
    }

    public void expandpersonal(View view) {
        int p = (personalinfoDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        // TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        personalinfoDetails.setVisibility(p);

    }

    public void expandparent(View view) {
        int pg = (parentinfoDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        // TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        parentinfoDetails.setVisibility(pg);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQ_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmapimage = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                image.setImageBitmap(bitmapimage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == GALLERY_REQ_CARD && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmapcard = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                card.setImageBitmap(bitmapcard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == GALLERY_REQ_CERT && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmapcert = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                cert.setImageBitmap(bitmapcert);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapimage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);
    }

    private String cardToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapcard.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);
    }

    private String certToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapcert.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);
    }

    public TextWatcher getContactNumberTextWatcher(TextInputLayout textInputLayout) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if the prefix is missing and add it
                EditText editText = textInputLayout.getEditText();
                if (editText != null && !s.toString().startsWith("+63")) {
                    ((TextInputEditText) editText).setText("+63");
                    ((TextInputEditText) editText).setSelection(3);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if the input length is more than 12 characters and truncate it
                EditText editText = textInputLayout.getEditText();
                if (editText != null && s.length() > 12) {
                    ((TextInputEditText) editText).setText(s.subSequence(0, 12));
                    ((TextInputEditText) editText).setSelection(12);
                }
            }
        };
    }

}