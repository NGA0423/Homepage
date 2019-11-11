package com.nga.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nga.homepage.R;

import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    @OnClick(R.id.im_back)
    void back(){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        finish();
    }
}
