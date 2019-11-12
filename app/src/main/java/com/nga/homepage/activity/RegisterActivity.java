package com.nga.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nga.homepage.R;
import com.nga.homepage.bean.LoginResponse;
import com.nga.homepage.bean.RegisterResponse;
import com.nga.homepage.bean.SpTools;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_password1)
    EditText ed_password1;
    @BindView(R.id.ed_password2)
    EditText ed_password2;
    @BindView(R.id.ed_uname)
    EditText ed_uname;
    private String password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    private String setpassword(){
        String passwpod1=ed_password1.getText().toString();
        String passwpod2=ed_password2.getText().toString();
        if (passwpod1==passwpod2){
            password =passwpod1;
        }
        else {
            password=null;
        }
        return password;
    }
    @OnClick(R.id.tv_register)
    void register(){
        String uname=ed_uname.getText().toString();
        String email=ed_email.getText().toString();
        String url="";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("uname", uname)
                .addParams("email",email)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //失败
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        RegisterResponse response1 = gson.fromJson(response, RegisterResponse.class);
                        if (response1.getStatus()==0){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG);
                            //Toast.makeText(LonginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            SpTools.putBoolean("isLogni",true);
                            finish();
                        }else {
                            //Toast.makeText(LonginActivity.this,response1.getStatus(),Toast.LENGTH_SHORT).show();
                            //Toast.makeText(RegisterActivity.this,response1,Toast.LENGTH_LONG);
                        }
                    }
                });

    }

}
