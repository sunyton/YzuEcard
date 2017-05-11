package com.example.suny.ecard.utils;

import android.os.Handler;
import android.util.Log;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by suny on 2017/4/18.
 */

public class SendHttpHelper {

    public static void sendHttp(String address, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address)
                .addHeader("Host", "api.xzxyun.com")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void sendHttp(String address, String cookie, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address)
                .addHeader("accept", "text/html, */*; q=0.01")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; Touch; rv:11.0) like Gecko")
                .addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("accept-language", "zh-Hans-CN,zh-Hans;q=0.5")
                .addHeader("cookie", cookie)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void sendHttp(String address, String cookie,String data, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder().url(address).post(body)
                .addHeader("Host", "api.xzxyun.com")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Cookie", cookie)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
