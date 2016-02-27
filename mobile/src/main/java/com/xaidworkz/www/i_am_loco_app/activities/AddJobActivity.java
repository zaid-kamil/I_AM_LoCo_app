package com.xaidworkz.www.i_am_loco_app.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.desmond.squarecamera.CameraActivity;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xaidworkz.www.i_am_loco_app.AppConfig;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.database.PrefManager;
import com.xaidworkz.www.i_am_loco_app.helpers.Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.xaidworkz.www.i_am_loco_app.AppConfig.EXTRA_MARKER_TYPE;
import static com.xaidworkz.www.i_am_loco_app.AppConfig.TYPE_VIEW;

public class AddJobActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CAMERA_PERMISSION = 123;
    public static final int MAP_REQUEST_CODE = 53;
    private EditText editJobBudget;
    private EditText editJobDeliveryDate;
    private EditText editJobOptionnalDescription;
    private EditText editJobTitle;
    private EditText editStartingPoint;
    private EditText editDestinationPoint;
    private FloatingActionButton fab;
    private ImageView imageShowMap;
    private Toolbar toolbar;
    private AppCompatImageView imageJob;
    private EditText editJobDescription;
    private final static int CAMERA_REQUEST = 6969;
    private FrameLayout layoutWait;
    private Bundle mapExtras;
    private Uri photoUri;
    private boolean isImageUploaded = false;
    private boolean isJobPosted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editJobTitle = (EditText) findViewById(R.id.editJobTitle);
        editStartingPoint = (EditText) findViewById(R.id.editStartingPoint);
        editDestinationPoint = (EditText) findViewById(R.id.editDestinationPoint);
        editJobBudget = (EditText) findViewById(R.id.editJobBudget);
        editJobDeliveryDate = (EditText) findViewById(R.id.editJobDeliveryDate);
        editJobOptionnalDescription = (EditText) findViewById(R.id.editJobOptionnalDescription);
        editJobDescription = (EditText) findViewById(R.id.editJobDescription);
        layoutWait = (FrameLayout) findViewById(R.id.wrapper);
        Button buttonImageCapture = (Button) findViewById(R.id.buttonCaptureImage);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setEnabled(true);
        imageShowMap = (ImageView) findViewById(R.id.imageShowMap);
        imageJob = (AppCompatImageView) findViewById(R.id.imageJob);
        imageShowMap.setOnClickListener(this);

        buttonImageCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera(v);
            }
        });


        fab.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lara Higgins");

    }

    private void nextStep(View v) {


        Snackbar.make(v, "Confirm job   ", Snackbar.LENGTH_INDEFINITE).setAction("Continue", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadToServer()) {
                    Intent intent = new Intent(AddJobActivity.this, PaymentActivity.class);
                    startActivity(intent);
                }

            }
        }).show();
    }

    private boolean uploadToServer() {
        layoutWait.setVisibility(View.VISIBLE);
        fab.setVisibility(View.GONE);
        pushJobDataToServer();
        return isJobPosted;
    }

    private void pushJobDataToServer() {

        if (mapExtras != null) {
            try {
                File file = new File(AppConfig.FOLDERS.PATH + photoUri.getLastPathSegment());
                String contentType = file.toURL().openConnection().getContentType();
                RequestBody imageUpload = new MultipartBuilder()
                        .addFormDataPart("jobImage1", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file)).build();
                Request imageUploadRequest = new Request.Builder()
                        .url(AppConfig.IMAGE_UPLOAD_URL)
                        .post(imageUpload)
                        .build();
                OkHttpClient imageClient = new OkHttpClient();
                imageClient.newCall(imageUploadRequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Snackbar.make(fab, "There was an error accesssing the server", Snackbar.LENGTH_INDEFINITE).show();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.isSuccessful()) {
                            isImageUploaded = true;
                        } else {
                            Snackbar.make(fab, "Sorry could not upload image", Snackbar.LENGTH_INDEFINITE).show();
                        }
                    }
                });
                RequestBody addJobRequest = new FormEncodingBuilder()
                        .add("app", "true")
                        .add("login", PrefManager.getUserId(AddJobActivity.this))
                        .add("name", PrefManager.getUserName(AddJobActivity.this))
                        .add("profilepic", PrefManager.getProfilePicUrl(AddJobActivity.this))
                        .add("jobTitle", editJobTitle.getText().toString())
                        .add("jobDescriptionStart", editJobDescription.getText().toString())
                        .add("lat1", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_START_LATITUDE)))
                        .add("long1", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_START_LONGITUDE)))
                        .add("jobDescriptionOpt", editJobOptionnalDescription.getText().toString())
                        .add("lat2", mapExtras.getBoolean(MapActivity.EXTRA_MAP_HAS_OPTIONAL) ? String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_OPT_LATITUDE)) : "")
                        .add("long2", mapExtras.getBoolean(MapActivity.EXTRA_MAP_HAS_OPTIONAL) ? String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_OPT_LONGITUDE)) : "")
                        .add("destLat", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_DEST_LONGITUDE)))
                        .add("destLong", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_DEST_LONGITUDE)))
                        .add("budget", editJobBudget.getText().toString())
                        .add("jobDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                        .add("jobTime", String.valueOf(SimpleDateFormat.getTimeInstance()))
                        .add("jobDeadlineDate", editJobDeliveryDate.getText().toString())
                        .add("jobDeadlineTime", "00:00")
                        .add("Status", "waiting to confirm")
                        .add("jobImage1", AppConfig.IMAGE_UPLOAD_URL + "/" + file.getName())
                        .add("jobImage2", "")
                        .build();

                Request request = new Request.Builder()
                        .url(AppConfig.NEW_JOB_DETAILS_URL)
                        .post(addJobRequest)
                        .build();
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(fab, "Sorry could not post the job", Snackbar.LENGTH_INDEFINITE).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    isJobPosted = true;
                                    layoutWait.setVisibility(View.GONE);
                                    fab.setVisibility(View.VISIBLE);
                                    Snackbar.make(fab, "the job was posted", Snackbar.LENGTH_INDEFINITE).show();
                                }
                            });
                        } else {
                            Snackbar.make(fab, "Sorry could not post the job", Snackbar.LENGTH_INDEFINITE).show();
                            Log.d("ERROR", response.body().toString());
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            layoutWait.setVisibility(View.GONE);
            fab.setVisibility(View.VISIBLE);
            Snackbar.make(fab, "Please add all required fields", Snackbar.LENGTH_LONG).show();
        }

        //  startActivity(new Intent(this, PaymentActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.editDestinationPoint:
                showJobMap(TYPE_DESTINATION);
                break;
            case R.id.editStartingPoint:
                showJobMap(TYPE_STARTING);
                break;*/
            /*case R.id.editJobOptionnalDescription:
                showJobMap(TYPE_OPTIONAL);
                break;*/
            case R.id.imageShowMap:
                if (Util.Operations.isOnline(AddJobActivity.this)) {
                    showJobMap(TYPE_VIEW);
                } else {
                    Snackbar.make(fab, "No internet connection", Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.fab:
                if (Util.Operations.isOnline(AddJobActivity.this)) {
                    nextStep(v);
                } else {
                    Snackbar.make(fab, "No internet connection", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void showJobMap(int type) {
        Intent showMapIntent = new Intent();
        showMapIntent.setClass(this, MapActivity.class);
        showMapIntent.putExtra(EXTRA_MARKER_TYPE, type);
        startActivityForResult(showMapIntent, MAP_REQUEST_CODE);
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
            photoUri = data.getData();
            /*imageJob.setImageURI(photoUri);*/
            getContentResolver().notifyChange(photoUri, null);
            ContentResolver cr = this.getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, photoUri);
                imageJob.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();

            }
        }
        if (requestCode == MAP_REQUEST_CODE) {
            mapExtras = data.getExtras();
            editStartingPoint.setText(String.format("%s,%s", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_START_LATITUDE)).substring(0, 6), String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_START_LONGITUDE)).substring(0, 6)));
            editDestinationPoint.setText(String.format("%s,%s", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_DEST_LATITUDE)).substring(0, 6), String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_DEST_LONGITUDE)).substring(0, 6)));
            if (mapExtras.getBoolean(MapActivity.EXTRA_MAP_HAS_OPTIONAL)) {
                editJobOptionnalDescription.setText(String.format("%s,%s", String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_OPT_LATITUDE)).substring(0, 6), String.valueOf(mapExtras.getDouble(MapActivity.EXTRA_MAP_OPT_LONGITUDE)).substring(0, 6)));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
