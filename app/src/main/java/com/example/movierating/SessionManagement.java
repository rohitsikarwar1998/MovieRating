package com.example.movierating;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";

    public SessionManagement(Context context){
        mSharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();
    }

    public void saveSession(User user){
        //Save user session
        String userName=user.username;
        mEditor.putString(SESSION_KEY,userName).commit();

    }

    public String getSession(){
        //return whether a session is saved or not
         return mSharedPreferences.getString(SESSION_KEY,"");
    }

    public void removeSession(){
        //this function will remove session
        mEditor.putString(SESSION_KEY,"").commit();
    }
}
