package com.xaidworkz.www.i_am_loco_app.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.xaidworkz.www.i_am_loco_app.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final FloatingSearchView searchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery
                //pass them on to the search view
                //mSearchView.swapSuggestions(newSuggestions);
            }
        });
        searchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }
        });
        searchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

            }
        });
    }

}
