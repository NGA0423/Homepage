package com.nga.homepage.bean;

import android.content.Context;
import android.content.SharedPreferences;

public class SpTools {
    private final static String SPNAME="my_sp";

    public static void putString( String key, String value){
        SharedPreferences my_sp = getSharedPreferences();
        SharedPreferences.Editor edit = my_sp.edit();
        edit.putString(key,value);
        edit.apply();
    }
    public static String getString(String key,String defValue){
        SharedPreferences my_sp = getSharedPreferences();
        return my_sp.getString(key,defValue);
    }

    public static void putLong(String key,long defValue){
        SharedPreferences.Editor edit = getEdit();
        edit.putLong(key,defValue);
    }
    public static long getLong(String key,long defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getLong(key,defValue);
    }
    public static void putBoolean(String key,boolean defValue){
        SharedPreferences.Editor edit = getEdit();
        edit.putBoolean(key,defValue);
    }
        public static boolean getBoolean(String key,boolean defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getBoolean(key,defValue);
    }

    public static void putInt(String key,int defValue){
        SharedPreferences.Editor edit = getEdit();
        edit.putInt(key,defValue);
    }

    public static int getInt(String key,int defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getInt(key,defValue);
    }

    public static void putFloat(String key,float defValue){
        SharedPreferences.Editor edit = getEdit();
        edit.putFloat(key,defValue);
    }

    public static float getFloat(String key,float defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getFloat(key,defValue);
    }
    public static SharedPreferences getSharedPreferences(){
        SharedPreferences my_sp = MyApp.getContext().getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return  my_sp;
    }

    public static SharedPreferences.Editor getEdit(){
        SharedPreferences my_sp = getSharedPreferences();
        SharedPreferences.Editor edit = my_sp.edit();
        return edit;
    }
}
