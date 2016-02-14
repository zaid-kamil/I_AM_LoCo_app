package com.xaidworkz.www.i_am_loco_app.activities;

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

import com.eftimoff.viewpagertransformers.AccordionTransformer;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.fragments.DetailFragment;
import com.xaidworkz.www.i_am_loco_app.helpers.BaseRecyclerViewAdapter;
import com.xaidworkz.www.i_am_loco_app.helpers.JobsHolder;

import java.util.ArrayList;
import java.util.List;

import static com.xaidworkz.www.i_am_loco_app.AppConfig.USERNAME;
import static com.xaidworkz.www.i_am_loco_app.AppConfig.USER_LOGIN_PREF;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DetailFragment.OnFragmentInteractionListener {


    private DrawerLayout drawerRoot;
    private SharedPreferences userLoginPreferences;
    private String prefUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLoginPreferences=getSharedPreferences(USER_LOGIN_PREF,MODE_PRIVATE);
        if (!isUserLoggedIn()) {
            jumpToLogin();
        } else {
            prefUserEmail = userLoginPreferences.getString(USERNAME, "");
        }
        initViews();
    }

    private boolean isUserLoggedIn() {
        return true;
    }

    private void jumpToLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }


    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        drawerRoot = (DrawerLayout) findViewById(R.id.drawerRoot);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ViewPager itemsPager = (ViewPager) findViewById(R.id.itemsPager);
        ItemsPagerAdapter pagerAdapter = new ItemsPagerAdapter(getSupportFragmentManager());
        itemsPager.setAdapter(pagerAdapter);
        itemsPager.setPageTransformer(true, new AccordionTransformer());
        /* RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerHomeList);*/
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

        /*recycler*/

        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(this, R.layout.home_simple_item_1, getDummyData());
        /*recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);*/

    }

    private void jumpToCreateNewJob() {
        startActivity(new Intent(MainActivity.this, AddJobActivity.class));
    }

    private void setUpNavigationHeader(NavigationView nav) {
        View headerView = nav.getHeaderView(0);
        TextView userEmail = (TextView) headerView.findViewById(R.id.textEmailAddress);
        userEmail.setText(prefUserEmail);
    }

    /*test list*/
    private List<?> getDummyData() {
        ArrayList<JobsHolder> list = new ArrayList<JobsHolder>();
        list.add(new JobsHolder(R.drawable.dummy_portrait_1, "Ceram gomez", "buy me grocery", "go to downtown and buy me mixed vegies", "3 days ago", R.drawable.dummy_portrait_2));
        list.add(new JobsHolder(R.drawable.thumb2, "Nonee vistra", "get me movie tickets", "go to downtown and buy me mixed vegies", "3 days ago", R.drawable.dummy_portrait_5));
        list.add(new JobsHolder(R.drawable.thumb4, "Sam alex", "buy me grocery", "go to downtown and buy me mixed vegies", "3 days ago", R.drawable.dummy_portrait_4));
        return list;
    }

    private List<?> getRealData() {
        ArrayList<JobsHolder> list = new ArrayList<JobsHolder>();
         return list;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_view_people:


                return true;
            case R.id.nav_view_profile:
                return true;
        }
        drawerRoot.closeDrawer(GravityCompat.START);
        return true;
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
            return DetailFragment.newInstance((JobsHolder) getDummyData().get(position));
        }

        @Override
        public int getCount() {
            return getDummyData().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ((JobsHolder) getDummyData().get(position)).getTextProfileName();
        }
    }
}
