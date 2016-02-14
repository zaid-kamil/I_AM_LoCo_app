package com.xaidworkz.www.i_am_loco_app.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xaidworkz.www.i_am_loco_app.R;

import static com.xaidworkz.www.i_am_loco_app.AppConfig.IS_LOGGED_IN;
import static com.xaidworkz.www.i_am_loco_app.AppConfig.USER_LOGIN_PREF;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        final TextView textLabelLauncher = (TextView) findViewById(R.id.textLabelLauncher);
        ImageView imageLauncher = (ImageView) findViewById(R.id.imageLaunch);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarLauncher);

        imageLauncher.animate().alpha(1f).translationY(50f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                textLabelLauncher.animate().alpha(1f).translationY(50f).setInterpolator(new BounceInterpolator()).setDuration(600).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        progressBar.setVisibility(View.VISIBLE);
                        MyTask task = new MyTask();
                        task.execute();

                    }
                });
            }
        });


    }

    private class MyTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            if (isNetworkConnected()) {
                return Boolean.TRUE;
            }


            return Boolean.FALSE;
        }

        @Override
        protected void onPostExecute(Boolean status) {
            if (status.TRUE){
                if (isUserLoggedIn()) {
                    jumpToHome();
                } else {
                    jumpToLogin();
                }
            }else{
                Toast.makeText(LauncherActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }

        private boolean isNetworkConnected() {
           /* ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();*/
            boolean haveConnectedWifi = false;
            boolean haveConnectedMobile = false;

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
            return haveConnectedWifi || haveConnectedMobile;
        }
    }

    private void jumpToLogin() {
        Intent loginIntent = new Intent(LauncherActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private boolean isUserLoggedIn() {
        SharedPreferences userLoginPreferences = getSharedPreferences(USER_LOGIN_PREF, MODE_PRIVATE);
        return userLoginPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    private void jumpToHome() {
        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
        finish();
    }
}
