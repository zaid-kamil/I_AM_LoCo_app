package com.xaidworkz.www.i_am_loco_app.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zaid on 16-02-2016.
 */
public class PrefManager {

    public static final String USERNAME = "username";
    public static final String CURRENT_JOB = "current_job";
    public static final String PROFILE_PIC_URL = "profile_url";
    public static final String USER_TYPE = "user_type";
    public static final String LOGIN_STATE = "login_state";
    private static final String LOGIN_ID = "login_id";
    private static final String LOGIN_PASS = "login_password";
    private static final String PROFILE_ID = LOGIN_ID;

    public static SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public static String getUserName(Context context) {
        return PrefManager.getInstance(context).getString(USERNAME, "");
    }

    public static String getCurrentJob(Context context) {
        return PrefManager.getInstance(context).getString(CURRENT_JOB, "");
    }

    public static String getProfilePicUrl(Context context) {
        return PrefManager.getInstance(context).getString(PROFILE_PIC_URL, "");
    }


    public static boolean getLoginState(Context context) {
        return PrefManager.getInstance(context).getBoolean(LOGIN_STATE, false);
    }

    public static void setCurrentJob(Activity activity, String job) {
        PrefManager.getInstance(activity).edit().putString(CURRENT_JOB, job).apply();
    }

    public static void setUserType(Activity activity, String userType) {
        PrefManager.getInstance(activity).edit().putString(USER_TYPE, userType).apply();
    }

    public static void setProfilePicUrl(Activity activity, String profilePicUrl) {
        PrefManager.getInstance(activity).edit().putString(PROFILE_PIC_URL, profilePicUrl).apply();
    }

    public static void setLoginState(Activity activity, boolean loginState) {
        PrefManager.getInstance(activity).edit().putBoolean(LOGIN_STATE, loginState).apply();
    }

    public static void setUserName(Activity activity, String username) {
        PrefManager.getInstance(activity).edit().putString(LOGIN_STATE, username).apply();
    }

    public static void setUserId(Activity activity, String loginId) {
        PrefManager.getInstance(activity).edit().putString(LOGIN_ID, loginId).apply();
    }

    public static void setPassword(Activity activity, String password) {
        PrefManager.getInstance(activity).edit().putString(LOGIN_PASS, password).apply();
    }

    public static String getUserId(Activity activity) {
        return PrefManager.getInstance(activity).getString(PROFILE_ID, "");
    }
}
