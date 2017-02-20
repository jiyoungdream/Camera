package co.kr.timercamera.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by si on 2017. 2. 14..
 */

public class SharedPreference {
    public static void writeStringPreference(Context context, String Name, String Value){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Name, Value);
        editor.commit();
    }

    public static void writeIntPreference(Context context, String Name, int Value){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Name, Value);
        editor.commit();
    }


    public static String readStringPreference(Context context, String Name){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        String data = pref.getString(Name, "");
        return data;
    }

    public static int readIntPreference(Context context, String Name){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        int data = pref.getInt(Name, Constants.INT_DEFAULT_NOT_EXIST);
        return data;
    }
}
