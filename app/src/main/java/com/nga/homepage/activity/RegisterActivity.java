package com.nga.homepage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nga.homepage.R;
import com.nga.homepage.bean.LoginResponse;
import com.nga.homepage.bean.SpTools;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_usersname)
    EditText et_usersname;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.bt_longding)
    Button bt_longding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.bt_longding)
    void login(){
        String usersname = et_usersname.getText().toString();
        String pwd = et_pwd.getText().toString();
        String url="http://10.10.16.65:8089/MobileShop";
        //OkHttpClient build = new OkHttpClient.Builder().build();
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        FormBody body = new FormBody.Builder()
                .add("input", usersname)
                .add("password", pwd)
                .build();
        Request request = new Request.Builder().url(url).post(body).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final LoginResponse loginResponse = gson.fromJson(json, LoginResponse.class);
                //处理登录逻辑
                if(loginResponse.getStatus()==0){
                    SpTools.putBoolean("isLogni",true);
                    //登录成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(RegisterActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this,loginResponse.getStatus(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
/*
        new Thread(){
            @Override
            public void run() {
                super.run();
                OkHttpClient httpClient=new OkHttpClient();
                Request request = new Request
                        .Builder()
                        .url(url)
                        .get()
                        .build();
*/
/*
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
*//*

                try {
                    Response response = httpClient.newCall(request).execute();
                    response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
*/

    }
}
