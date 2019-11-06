package com.nga.homepage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.nga.homepage.R;
import com.nga.homepage.activity.RegisterActivity;
import com.nga.homepage.bean.SpTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalFragment extends Fragment {
    @Nullable
    @BindView(R.id.butt_bt)
    Button butt_bt;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean isLogni = SpTools.getBoolean("isLogni", false);
        if (isLogni){
            butt_bt.setText("退出登录");
        }else{
            butt_bt.setText("登录");
        }
    }

    @OnClick(R.id.butt_bt)
    void login(){
        boolean isLogni = SpTools.getBoolean("isLogni", false);
        if (isLogni){

            SpTools.putBoolean("isLogni",false);
            Toast.makeText(getActivity(),"退出登录成功",Toast.LENGTH_SHORT).show();
            onResume();
            //做退出登录操作
        }else{
            startActivity(new Intent(getActivity(), RegisterActivity.class));
        }

    }

}
