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
import com.nostra13.universalimageloader.core.ImageLoader;

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

        ImageLoader.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574739843&di=0d4d5fda20cba0928b17ccf7293ec5e6&imgtype=jpg&er=1&src=http%3A%2F%2Fphotocdn.sohu.com%2F20150320%2Fmp7168150_1426841001024_3.gif",im_ad);
        /*Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574144421707&di=234d94a7675097f2344da9f12a6257bb&imgtype=0&src=http%3A%2F%2Fs6.sinaimg.cn%2Fmiddle%2F627ba38fga7f1056753a5%26690")
                .placeholder(R.mipmap.login)
                .error(R.mipmap.error)
                .into(im_ad);*/

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
