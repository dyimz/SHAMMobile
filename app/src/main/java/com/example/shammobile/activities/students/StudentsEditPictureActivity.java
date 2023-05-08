package com.example.shammobile.activities.students;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shammobile.R;
import com.example.shammobile.activities.teachers.TeachersEditPictureActivity;
import com.example.shammobile.activities.teachers.TeachersProfileActivity;
import com.example.shammobile.models.Students;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsEditPictureActivity extends AppCompatActivity {

    ImageView image;
    ImageView chooseimage;
    Bitmap bitmapimage;
    private final int GALLERY_REQ_IMAGE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_editpicture);

        Bundle bundle = getIntent().getExtras();
        String studentID = bundle.getString("studentID");
        String token = bundle.getString("token");
        String imagee = bundle.getString("image");
        image = (ImageView) findViewById(R.id.image);

        Glide.with(StudentsEditPictureActivity.this)
                .load(APIUtils.API_URL + imagee)
                .into(image);

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

        Toolbar btnBackArrow = (Toolbar) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentsEditPictureActivity.this, StudentsProfileActivity.class).putExtras(bundle));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bitmapimage == null) {
                    Toast.makeText(StudentsEditPictureActivity.this, "Image Haven't Changed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(StudentsEditPictureActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                    RequestBody idd = RequestBody.create(MediaType.parse("text/plain"), studentID);
                    RequestBody imagee = RequestBody.create(MediaType.parse("image/*"), imageToString());


                    HashMap<String, RequestBody> imageee = new HashMap<>();
                    imageee.put("image", imagee);
                    imageee.put("id", idd);

                    Call<Students> call = APIUtils.getUserService().editStudentpicture(imageee);
                    call.enqueue(new Callback<Students>() {
                        @Override
                        public void onResponse(Call<Students> call, Response<Students> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(StudentsEditPictureActivity.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(StudentsEditPictureActivity.this, StudentsProfileActivity.class).putExtras(bundle));
                            }
                            else{
                                Toast.makeText(StudentsEditPictureActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Students> call, Throwable t) {
                            Log.e("Error: ", t.getMessage());
                        }
                    });
                }
            }
        });

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
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapimage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);
    }

}