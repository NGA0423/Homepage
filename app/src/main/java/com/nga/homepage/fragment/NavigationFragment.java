package com.nga.homepage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.nga.homepage.R;

public class NavigationFragment extends Fragment {

    private ImageView iv_home;
    private ImageView iv_personal;
    private ImageView iv_cart;
    private ImageView iv_category;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmrnt_navigation, container, false);
        initView(view);
        return view;

    }
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private CataFragment cataFragment;
    private PersonalFragment personalFragment;

    private void showFragment(View icon) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if(categoryFragment!=null){
            transaction.hide(categoryFragment);
        }if(cataFragment!=null){
            transaction.hide(cataFragment);
        }if(personalFragment!=null){
            transaction.hide(personalFragment);
        }

        if (icon.getId()==R.id.iv_home){
           if(homeFragment==null){
               homeFragment=new HomeFragment();
               transaction.add(R.id.fl_tag,homeFragment);
           }
           else {
               transaction.show(homeFragment);
           }
        }
        else  if (icon.getId()==R.id.iv_category){
            if(categoryFragment==null){
                categoryFragment=new CategoryFragment();
                transaction.add(R.id.fl_tag,categoryFragment);
            }
            else {
                transaction.show(categoryFragment);
            }
        }
        else  if (icon.getId()==R.id.iv_cart){
            if(cataFragment==null){
                cataFragment=new CataFragment();
                transaction.add(R.id.fl_tag,cataFragment);
            }
            else {
                transaction.show(cataFragment);
            }
        }
        else  if (icon.getId()==R.id.iv_personal){
            if(personalFragment==null){
                personalFragment=new PersonalFragment();
                transaction.add(R.id.fl_tag,personalFragment);
            }
            else {
                transaction.show(personalFragment);
            }
        }
        transaction.commit();
    }
    private void initView(View view) {
        iv_home = view.findViewById(R.id.iv_home);
        iv_category = view.findViewById(R.id.iv_category);
        iv_cart = view.findViewById(R.id.iv_cart);
        iv_personal = view.findViewById(R.id.iv_personal);
        resetImageResource(iv_home);
        showFragment(iv_home);

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showFragment(iv_home);
                resetImageResource(iv_home);

            }



        });
        iv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(iv_category);
                resetImageResource(iv_category);

            }
        });
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(iv_cart);
                resetImageResource(iv_cart);
            }
        });
        iv_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(iv_personal);
                resetImageResource(iv_personal);
            }
        });
    }
    private void resetImageResource(View icon) {
        icon.getId();
        iv_home.setImageResource(R.mipmap.tab_item_home_normal);
        iv_category.setImageResource(R.mipmap.tab_item_category_normal);
        iv_cart.setImageResource(R.mipmap.tab_item_cart_normal);
        iv_personal.setImageResource(R.mipmap.tab_item_personal_normal);

        if (icon.getId()==R.id.iv_home){
            iv_home.setImageResource(R.mipmap.tab_item_home_focus);
        }
        else  if (icon.getId()==R.id.iv_category){
            iv_category.setImageResource(R.mipmap.tab_item_category_focus);
        }
        else  if (icon.getId()==R.id.iv_cart){
            iv_cart.setImageResource(R.mipmap.tab_item_cart_focus);
        }
        else  if (icon.getId()==R.id.iv_personal){
            iv_personal.setImageResource(R.mipmap.tab_item_personal_focus);
        }
    }
}
