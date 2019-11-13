package com.nga.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_register)
    void register(){
        String uname=ed_uname.getText().toString();
        String email=ed_email.getText().toString();
        String passworld1 = ed_password1.getText().toString();
        String passworld2 = ed_password2.getText().toString();
        String url="http://10.10.16.23:8088/MobileShop/member";
        if (TextUtils.isEmpty(uname)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"请输入邮箱",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(passworld1)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(passworld2)){
            Toast.makeText(this,"请输入确认密码",Toast.LENGTH_SHORT).show();
        }

        OkHttpUtils
                .post()
                .url(url)
                .addParams("uname", uname)
                .addParams("email",email)
                .addParams("password", passworld2)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //失败
                        Toast.makeText(RegisterActivity.this,"注册失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        RegisterResponse response1 = gson.fromJson(response, RegisterResponse.class);
                        if (response1!=null&&response1.getStatus()==0){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            //Toast.makeText(LonginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            SpTools.putBoolean("isLogni",true);
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败"+response1.getMsg(),Toast.LENGTH_SHORT).show();
                            //Toast.makeText(LonginActivity.this,response1.getStatus(),Toast.LENGTH_SHORT).show();
                            //Toast.makeText(RegisterActivity.this,response1,Toast.LENGTH_LONG);
                        }
                    }
                });

    }
    @OnClick(R.id.im_back)
    void back(){
        finish();
    }

}
