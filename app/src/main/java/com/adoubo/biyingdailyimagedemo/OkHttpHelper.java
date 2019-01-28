package com.adoubo.biyingdailyimagedemo;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author adoubo
 */
public class OkHttpHelper {

    private static final String TAG = OkHttpClient.class.getSimpleName();
    private OkHttpClient client = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private String result = "";
    private static OkHttpHelper okHttpHelper;

    public static OkHttpHelper getInstance() {
        if (null == okHttpHelper) {
            okHttpHelper = new OkHttpHelper();
        }
        return okHttpHelper;
    }

    public String get(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();

//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                result = "failed";
//                Log.d(TAG, "onFailure");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                result = response.body().toString();
//                Log.d(TAG, response.body().toString());
//            }
//        });
//
//        call.execute();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
