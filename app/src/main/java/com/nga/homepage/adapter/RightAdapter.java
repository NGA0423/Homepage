package com.nga.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nga.homepage.R;
import com.nga.homepage.bean.SecondListResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.RightHoder>{
    private final Context context;
    private final ArrayList<SecondListResponse.DataBean> datas;

    public RightAdapter(Context context, ArrayList<SecondListResponse.DataBean> rightDatas) {
        this.context=context;
        this.datas=rightDatas;
    }

    @NonNull
    @Override
    public RightHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.right_itme, viewGroup, false);
        return new RightHoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RightHoder rightHoder, int i) {
        //获取名字
        SecondListResponse.DataBean dataBean = datas.get(i);
        rightHoder.tv_name.setText(dataBean.getName());

        //获取图片
        Glide.with(context)
                .load(dataBean.getImage())
                .into(rightHoder.iv_icon);
    }

    @Override
    public int getItemCount() {
        if (datas==null){
            return 0;
        }
        return datas.size();
    }

    public class RightHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.iv_icon)
        ImageView iv_icon;


        public RightHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
