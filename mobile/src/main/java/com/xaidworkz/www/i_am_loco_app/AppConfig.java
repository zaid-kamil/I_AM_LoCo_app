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
    public static final int TYPE_VIEW = 4;

    public static final String WEBSITE_URL = "http://dreamonweb.in";
    public static final String BASE_ADDRESS = WEBSITE_URL + "/android/loco/";
    public static final String EXTRA_MARKER_TYPE = "EXTRA_MARKER_TYPE";
    public static final String NEW_JOB_DETAILS_URL = BASE_ADDRESS + "insertJobInfo.php";
    public static final String NEW_BIDDER_DETAILS_URL = BASE_ADDRESS + "insertBidderInfo.php";
    public static final String GET_USERS_JOBS_LIST = BASE_ADDRESS + "getUsersJobList.php";

    public static final String START_LATITUDE = "com.xaidworkz.www.i_am_loco_app.startLatitude";
    public static final String END_LATITUDE = "com.xaidworkz.www.i_am_loco_app.endLatitude";
    public static final String START_LONGITUDE = "com.xaidworkz.www.i_am_loco_app.startLongitude";
    public static final String END_LONGITUDE = "com.xaidworkz.www.i_am_loco_app.endLongitude";
    public static final String URL_JOBS_LIST = BASE_ADDRESS + "getJobList.php";
    public static final String IMAGE_UPLOAD_URL = "http://dreamonweb.in/assets/portraits";

    // Server user login url
    public static String URL_LOGIN = BASE_ADDRESS + "login.php";
    // Server user register url
    public static String URL_REGISTER = BASE_ADDRESS + "insertLoginHistory.php";

    // for camera
    public static final class FOLDERS {
        private static final String ROOT = File.separator + "imloco";
        private static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getPath();
        public static final String PATH = SD_CARD_PATH + ROOT;
    }

}
