package com.xaidworkz.www.i_am_loco_app.connectivity;

import android.app.Application;
import android.widget.Toast;

import com.xaidworkz.www.i_am_loco_app.helpers.Util;


/**
 * Created by xaidi on 11/26/2015.
 */
public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        boolean online = Util.Operations.isOnline(AppController.this);
        if (!online) {
            Toast.makeText(AppController.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}
