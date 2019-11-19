package com.nga.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.nga.homepage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdActivity extends AppCompatActivity {
    @BindView(R.id.tv_acount)
            TextView tv_acount;
    @BindView(R.id.im_ad)
    ImageView im_ad;

    Thread thread;

   // boolean isStop=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        //绑定activi
        ButterKnife.bind(AdActivity.this);

        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574143415107&di=73a4438035ba12462174ad59f519f497&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1308%2F27%2Fc10%2F24963658_1377592533297_mthumb.jpg")
                .placeholder(R.mipmap.login)
                .error(R.mipmap.error)
                .into(im_ad);

        tv_acount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdActivity.this,MainActivity.class));
                /*if(thread!=null) {
                    thread.stop();
                }*/
                //isStop=true;
                finish();
            }
        });

        thread =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=5;i>=0;i--){
                    /*if(isStop){
                        return;
                    }*/
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
        });
        thread.start();
    }

}
