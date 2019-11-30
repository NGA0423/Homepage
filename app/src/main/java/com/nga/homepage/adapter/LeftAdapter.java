package com.nga.homepage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nga.homepage.R;
import com.nga.homepage.bean.CategoryResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.LeftHoder> implements View.OnClickListener {

    private final Context context;
    private final List<CategoryResponse.DataBean> datas;
    private int cunrunt_pos;    //当前选中的位置
    private OnItemClickLisener onItemClickLisener;

    public LeftAdapter(Context context, List<CategoryResponse.DataBean> leftdara) {
        this.context =context;
        this.datas =leftdara;

    }

    @NonNull
    @Override
    public LeftHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.left_itme, viewGroup, false);
        view.setOnClickListener(this);
        return new LeftHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeftHoder leftHoder, int i) {
        leftHoder.itemView.setTag(i);
        CategoryResponse.DataBean dataBean = datas.get(i);
        leftHoder.tv_name.setText(dataBean.getName());
        if (cunrunt_pos==i){
            leftHoder.tv_name.setBackgroundColor(0xffffffff);
        }else {
            leftHoder.tv_name.setBackgroundColor(0xfff3f4f6);
        }
    }

    @Override
    public int getItemCount() {
        if (datas==null){
            return 0;
        }
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        int pos= (int) v.getTag();
        cunrunt_pos=pos;

        if (onItemClickLisener!=null){
            onItemClickLisener.onItemClick(pos);
        }
        notifyDataSetChanged();

    }

    public void setOnItemClickLisener(OnItemClickLisener l) {
        onItemClickLisener =l;
    }

    public class LeftHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tv_name;
        public LeftHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            //itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnItemClickLisener{
        void onItemClick(int pos);
    }
}
