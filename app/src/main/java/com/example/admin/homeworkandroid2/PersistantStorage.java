package com.example.admin.homeworkandroid2;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.homeworkandroid2.MVP.MainContract;

public class PersistantStorage implements MainContract.Repository{
    public static final String STORAGE_NAME = "StorageName";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init( Context cntxt ){
        context = cntxt;
    }

    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void addProperty(Integer value ){
        if( settings == null ){
            init();
        }
        editor.putInt("TaskQuantity", + getProperty()+value);
        editor.apply();
    }

    public static Integer getProperty(){
        if( settings == null ){
            init();
        }
        return settings.getInt("TaskQuantity",0);
    }

    @Override
    public int loadMessage() {
        return getProperty();
    }
}
