package com.nga.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nga.homepage.R;

public class AdActivity extends AppCompatActivity {
    TextView tv_acount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        tv_acount = findViewById(R.id.tv_acount);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=5;i>=0;i--){
                    SystemClock.sleep(1000);
                    final int count=i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_acount.setText("点击跳转 "+count);
                        }
                    });
                }

               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       startActivity(new Intent(AdActivity.this,MainActivity.class));
                       finish();
                   }
               });

            }
        }).start();



    }

}
