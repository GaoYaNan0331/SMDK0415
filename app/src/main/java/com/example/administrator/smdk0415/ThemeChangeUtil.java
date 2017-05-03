package com.example.administrator.smdk0415;

import android.app.Activity;

/**
 * Created by 晓勇 on 2015/8/29 0029.
 */
public class ThemeChangeUtil {
    public static boolean isChange = false;
    public static void changeTheme(Activity activity){
        if(isChange){
            activity.setTheme(R.style.NightTheme);
        }
    }
}
