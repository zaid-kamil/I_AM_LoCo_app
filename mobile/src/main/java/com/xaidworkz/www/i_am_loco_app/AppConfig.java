package com.xaidworkz.www.i_am_loco_app;

import android.os.Environment;

import java.io.File;

/**
 * Created by xaidi on 11/26/2015.
 */
public class AppConfig {
    public static final int TYPE_DESTINATION = 2;
    public static final int TYPE_STARTING = 1;
    public static final int TYPE_OPTIONAL = 3;

    public static final String BASE_ADDRESS = "http://dreamonweb.in/android/loco/";
    public static final String USER_LOGIN_PREF = "user_login_pref";
    public static final String USERNAME = "username";
    public static final String IS_LOGGED_IN = "is_logged_in";
    public static final String EXTRA_MARKER_TYPE = "EXTRA_MARKER_TYPE";
    // Server user login url
    public static String URL_LOGIN = BASE_ADDRESS + "getLoginHistory.php";
    // Server user register url
    public static String URL_REGISTER = BASE_ADDRESS + "insertLoginHistory.php";

    // for camera
    public static final class FOLDERS {
        private static final String ROOT = File.separator + "imloco";
        private static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getPath();
        public static final String PATH = SD_CARD_PATH + ROOT;
    }

}
