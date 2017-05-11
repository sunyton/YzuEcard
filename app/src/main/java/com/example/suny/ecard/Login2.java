package com.example.suny.ecard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suny.ecard.utils.Constant;
import com.example.suny.ecard.utils.SendHttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by suny on 2017/4/26.
 */

public class Login2 extends AppCompatActivity implements View.OnClickListener{


    private final int SUCCESS = 1;
    private final int FAIL = 0;
    private String session1 = "";




    EditText etUserName;
    EditText etPassword;
    EditText etCheckcode;
    ImageView imCheckcode;
    TextView tvLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_psw);
        etCheckcode = (EditText) findViewById(R.id.et_checkcode);
        imCheckcode = (ImageView) findViewById(R.id.img_check);
        tvLogin = (TextView) findViewById(R.id.tv_login);

        changeimg();
        tvLogin.setOnClickListener(this);
        imCheckcode.setOnClickListener(this);

    }

    public void login() {


        String lgUsername = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String base_psw = new String(Base64.encode(password.getBytes(), Base64.DEFAULT));
        String lgCheckcode = etCheckcode.getText().toString();

        String data = "CheckCode=" + lgCheckcode + "&Password=" + base_psw + "&SchoolCode=yzu&SignType=SynSno&UserAccount=" + lgUsername + "&openid=";
        Log.i("se", session1);
        SendHttpHelper.sendHttp(Constant.loginUrl, session1, data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("denglu", "0000");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s = response.body().string();
                try {
                    JSONObject jsonbody = new JSONObject(s);
                    if (jsonbody.getString("success").equals("true")) {
                        String session2 = jsonbody.getString("msg");
                        SharedPreferences mSharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putString("session", session1 + ";iPlanetDirectoryPro=" + session2);
                        editor.apply();
                        Intent intent = new Intent(Login2.this, MainActivity.class);
                        intent.putExtra("session", session1 + ";iPlanetDirectoryPro=" + session2);
                        startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public void changeimg() {
        SendHttpHelper.sendHttp(Constant.checkUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());

//                Message message = mHandler.obtainMessage();
//                message.obj = bitmap;
//                message.what = SUCCESS;
//                mHandler.sendMessage(message);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imCheckcode.setImageBitmap(bitmap);

                    }
                });

                session1 = response.header("Set-Cookie").split(";")[0];
                Log.i("session1", session1);
//                SharedPreferences.Editor editor = mSharedPreferences.edit();
//                editor.putString("cookie", session1);

            }
        });
    }


//    public Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    InputStream inputStream = (InputStream) msg.obj;
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    imCheckcode.setImageBitmap(bitmap);
//
//                    break;
//                case 0:
//                    Toast.makeText(Login2.this, "网络出现问题", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_check:
                changeimg();
                break;
            case R.id.tv_login:
                login();
                break;

        }
    }
}
