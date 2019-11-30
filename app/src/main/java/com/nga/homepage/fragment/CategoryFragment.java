package com.nga.homepage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;
import com.nga.homepage.R;
import com.nga.homepage.adapter.LeftAdapter;
import com.nga.homepage.adapter.RightAdapter;
import com.nga.homepage.bean.CategoryResponse;
import com.nga.homepage.bean.SecondListResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public class CategoryFragment extends Fragment {
    @BindView(R.id.rv_left)
    RecyclerView rv_left;
    @BindView(R.id.rv_right)
    RecyclerView rv_right;
    private List<CategoryResponse.DataBean> leftdara;
    private LeftAdapter leftAdapter;
    private ArrayList<SecondListResponse.DataBean> rightDatas;
    private RightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_category, container, false);
        ButterKnife.bind(this,view);
        initView();
        initData();
        return view;
    }
    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        leftdara = new ArrayList<>();
        rv_left.setLayoutManager(linearLayoutManager);
        leftAdapter = new LeftAdapter(getActivity(), leftdara);

        rv_left.setAdapter(leftAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        rv_right.setLayoutManager(gridLayoutManager);

        rightDatas =new ArrayList<SecondListResponse.DataBean>();
        rightAdapter = new RightAdapter(getActivity(), rightDatas);
        rv_right.setAdapter(rightAdapter);

        leftAdapter.setOnItemClickLisener(new LeftAdapter.OnItemClickLisener(){
            @Override
            public void onItemClick(int pos) {
                //加载二级列表
                getSecondList(pos);

            }


        });

    }
    private void getSecondList(int pos) {
        CategoryResponse.DataBean dataBean = leftdara.get(pos);
        int cat_id = dataBean.getCat_id();
        String url=""+cat_id;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SecondListResponse secondListResponse = gson.fromJson(response, SecondListResponse.class);
                        List<SecondListResponse.DataBean> data = secondListResponse.getData();
                        if (data!=null&&data.size()!=0){
                            rightDatas.clear();
                            rightDatas.addAll(data);
                            rightAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initData() {
        //发送网络请求获取顶级列表的数据
        OkHttpUtils.get()
                .url("")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        CategoryResponse categoryResponse = gson.fromJson(response, CategoryResponse.class);
                        List<CategoryResponse.DataBean> data = categoryResponse.getData();
                        if (data!=null&&data.size()!=0){
                            leftdara.clear();
                            leftdara.addAll(data);
                            leftAdapter.notifyDataSetChanged();

                            //加载默认的
                            getSecondList(0);
                        }
                    }
                });
    }


}
