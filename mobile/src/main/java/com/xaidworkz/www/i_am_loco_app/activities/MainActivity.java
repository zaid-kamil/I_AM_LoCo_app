package com.xaidworkz.www.i_am_loco_app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.ForegroundToBackgroundTransformer;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xaidworkz.www.i_am_loco_app.AppConfig;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.database.JobInfo;
import com.xaidworkz.www.i_am_loco_app.fragments.DetailFragment;
import com.xaidworkz.www.i_am_loco_app.helpers.Util;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DetailFragment.OnFragmentInteractionListener {


    private DrawerLayout drawerRoot;
    private SharedPreferences userLoginPreferences;
    private String prefUserEmail;
    private ProgressDialog dialog;
    private List<JobInfo.DataEntity> data;
    private ViewPager itemsPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJobData();
        initViews();
    }


    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        drawerRoot = (DrawerLayout) findViewById(R.id.drawerRoot);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        itemsPager = (ViewPager) findViewById(R.id.itemsPager);

        /*toolbar setup*/
        setSupportActionBar(toolbar);

        /* fab*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToCreateNewJob();
            }
        });

        /*navigation Panel*/
        nav.setNavigationItemSelectedListener(this);
        setUpNavigationHeader(nav);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerRoot, toolbar, R.string.open, R.string.closed);
        drawerRoot.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

    private void jumpToCreateNewJob() {
        startActivity(new Intent(MainActivity.this, AddJobActivity.class));
    }

    private void setUpNavigationHeader(NavigationView nav) {
        View headerView = nav.getHeaderView(0);
        TextView userEmail = (TextView) headerView.findViewById(R.id.textEmailAddress);
        userEmail.setText(prefUserEmail);
    }


    private void getJobData() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("please wait...");
        dialog.show();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(AppConfig.URL_JOBS_LIST)
                    .build();
            Response response = null;

            Call call = client.newCall(request);
            if (Util.Operations.isOnline(getApplicationContext())) {
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Toast.makeText(MainActivity.this, "Error fetching job details", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String jsonData = response.body().string();
                        Gson gson = new Gson();
                        JobInfo jobInfo = gson.fromJson(jsonData, JobInfo.class);
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            setDataList(jobInfo.getData());

                        }
                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataList(final List<JobInfo.DataEntity> data) {
        this.data = data;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (data != null) {
                    ItemsPagerAdapter pagerAdapter = new ItemsPagerAdapter(getSupportFragmentManager());
                    itemsPager.setAdapter(pagerAdapter);
                    itemsPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
                }
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_view_jobs:
                startJobHistory();
                break;
            case R.id.nav_view_people:
                showsUsersList();
                break;
            case R.id.nav_view_profile:
                showUsersProfile();
                break;
        }
        drawerRoot.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startJobHistory() {
        Intent intent = new Intent(this, UserJobsActivity.class);
        startActivity(intent);
    }

    private void showUsersProfile() {
        Intent intent = new Intent(this, ViewProfileActivity.class);
        startActivity(intent);
    }

    private void showsUsersList() {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawerRoot.isDrawerOpen(GravityCompat.START)) {
            drawerRoot.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    /*adapter for pager*/

    class ItemsPagerAdapter extends FragmentStatePagerAdapter {

        public ItemsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            return DetailFragment.newInstance(data.get(position));
        }

        @Override
        public int getCount() {
            return data.size();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
