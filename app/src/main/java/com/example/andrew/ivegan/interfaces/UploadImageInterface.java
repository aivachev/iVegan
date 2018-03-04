package com.example.andrew.ivegan.interfaces;

import android.database.Observable;
import android.graphics.Bitmap;
import android.util.Config;

import com.example.andrew.ivegan.data.UploadObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Andrew on 04.03.2018.
 */

public interface UploadImageInterface {
    @Multipart
    @POST("/imagefolder/index.php")
    Call<UploadObject> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
