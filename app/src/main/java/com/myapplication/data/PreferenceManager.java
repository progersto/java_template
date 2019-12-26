package com.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import static com.myapplication.utils.Constants.*;

public class PreferenceManager {

    private static SharedPreferences mSharedPreferences;


    public PreferenceManager(Context context){
        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        mSharedPreferences.edit().putString(TOKEN, token).apply();
    }

    public String getToken(){
        return mSharedPreferences.getString(TOKEN, "");
    }
}
