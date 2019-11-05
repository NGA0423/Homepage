package com.nga.homepage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.textclassifier.TextSelection;
import android.widget.EditText;

import com.google.gson.Gson;
import com.nga.homepage.R;
import com.nga.homepage.bean.LoginResponse;

import org.jetbrains.annotations.NotNull;

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
                LoginResponse loginResponse = gson.fromJson(json, LoginResponse.class);

                String msg = loginResponse.getMsg();
                String uname = loginResponse.getData().getUname();

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
