package com.example.shammobile.activities.teachers;


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
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shammobile.R;
import com.example.shammobile.models.Teachers;
import com.example.shammobile.remote.APIUtils;
import com.example.shammobile.remote.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersEditPictureActivity extends AppCompatActivity {

    ImageView image;
    ImageView chooseimage;
    Bitmap bitmapimage;
    private final int GALLERY_REQ_IMAGE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachers_editpicture);

        Bundle bundle = getIntent().getExtras();
        String teacherID = bundle.getString("teacherID");
        String token = bundle.getString("token");
        String imagee = bundle.getString("image");

        image = (ImageView) findViewById(R.id.image);

        Glide.with(TeachersEditPictureActivity.this)
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
                startActivity(new Intent(TeachersEditPictureActivity.this, TeachersProfileActivity.class).putExtras(bundle));
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bitmapimage == null) {
                    Toast.makeText(TeachersEditPictureActivity.this, "Image Haven't Changed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(TeachersEditPictureActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                    RequestBody idd = RequestBody.create(MediaType.parse("text/plain"), teacherID);
                    RequestBody imagee = RequestBody.create(MediaType.parse("image/*"), imageToString());


                    HashMap<String, RequestBody> imageee = new HashMap<>();
                    imageee.put("image", imagee);
                    imageee.put("id", idd);

                    Call<Teachers> call = APIUtils.getUserService().editTeacherpicture(imageee);
                    call.enqueue(new Callback<Teachers>() {
                        @Override
                        public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(TeachersEditPictureActivity.this, "Edited Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(TeachersEditPictureActivity.this, TeachersProfileActivity.class).putExtras(bundle));
                            }
                            else{
                                Toast.makeText(TeachersEditPictureActivity.this, "Internet Connection LOST", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Teachers> call, Throwable t) {
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