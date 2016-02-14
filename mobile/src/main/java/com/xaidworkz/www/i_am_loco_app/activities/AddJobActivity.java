package com.xaidworkz.www.i_am_loco_app.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desmond.squarecamera.CameraActivity;
import com.xaidworkz.www.i_am_loco_app.AppConfig;
import com.xaidworkz.www.i_am_loco_app.R;

import java.io.File;

import static com.xaidworkz.www.i_am_loco_app.AppConfig.EXTRA_MARKER_TYPE;
import static com.xaidworkz.www.i_am_loco_app.AppConfig.TYPE_DESTINATION;
import static com.xaidworkz.www.i_am_loco_app.AppConfig.TYPE_OPTIONAL;
import static com.xaidworkz.www.i_am_loco_app.AppConfig.TYPE_STARTING;

public class AddJobActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CAMERA_PERMISSION = 123;
    private EditText editJobBudget;
    private EditText editJobDeliveryDate;
    private EditText editJobOptionnalWork;
    private EditText editJobTitle;
    private Button editStartingPoint;
    private Button editDestinationPoint;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private EditText editJobDescription;
    private final static int CAMERA_REQUEST = 6969;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);


        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editJobTitle = (EditText) findViewById(R.id.editJobTitle);
        editStartingPoint = (Button) findViewById(R.id.editStartingPoint);
        editDestinationPoint = (Button) findViewById(R.id.editDestinationPoint);
        editJobBudget = (EditText) findViewById(R.id.editJobBudget);
        editJobDeliveryDate = (EditText) findViewById(R.id.editJobDeliveryDate);
        editJobOptionnalWork = (EditText) findViewById(R.id.editJobOptionnalWork);
        editJobDescription = (EditText) findViewById(R.id.editJobDescription);
        Button buttonImageCapture = (Button) findViewById(R.id.buttonCaptureImage);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        buttonImageCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera(v);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Confirm and Continue to payment ", Snackbar.LENGTH_INDEFINITE).setAction("confirm", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextStep(v);
                    }
                }).show();

            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lara Higgins");
        editStartingPoint.setOnClickListener(this);
        editDestinationPoint.setOnClickListener(this);
        editJobOptionnalWork.setOnClickListener(this);
    }

    private void nextStep(View v) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editDestinationPoint:
                showJobMap(TYPE_DESTINATION);
                break;
            case R.id.editStartingPoint:
                showJobMap(TYPE_STARTING);
                break;
            case R.id.editJobOptionnalWork:
                showJobMap(TYPE_OPTIONAL);
                break;
        }
    }

    private void showJobMap(int type) {
        Intent showMapIntent = new Intent();
        showMapIntent.setClass(this, MapActivity.class);
        showMapIntent.putExtra(EXTRA_MARKER_TYPE, type);
        startActivityForResult(showMapIntent, 12);
    }

    public void openCamera(View view) {

        File saveFolder = new File(AppConfig.FOLDERS.PATH);
        if (!saveFolder.exists() && !saveFolder.mkdirs()) {
            Toast.makeText(AddJobActivity.this, "could got access sdcard", Toast.LENGTH_SHORT).show();
        }
        requestForCameraPermission(view);


    }

    // Check for camera permission in MashMallow
    public void requestForCameraPermission(View view) {
        final String permission = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(AddJobActivity.this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddJobActivity.this, permission)) {
                // Show permission rationale
            } else {
                // Handle the result in Activity#onRequestPermissionResult(int, String[], int[])
                ActivityCompat.requestPermissions(AddJobActivity.this, new String[]{permission}, REQUEST_CAMERA_PERMISSION);
            }
        } else {
            // Start CameraActivity
            Intent startCustomCameraIntent = new Intent(this, CameraActivity.class);
            startActivityForResult(startCustomCameraIntent, CAMERA_REQUEST);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == CAMERA_REQUEST) {
            Uri photoUri = data.getData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
