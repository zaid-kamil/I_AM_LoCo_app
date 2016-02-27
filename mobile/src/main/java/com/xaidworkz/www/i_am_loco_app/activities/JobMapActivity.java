package com.xaidworkz.www.i_am_loco_app.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.helpers.PlaceAutoCompleteAdapter;
import com.xaidworkz.www.i_am_loco_app.helpers.Util;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class JobMapActivity extends AppCompatActivity implements RoutingListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback {

    private GoogleMap map;
    protected LatLng start;
    protected LatLng dest;
    private LatLng opt;
    private String LOG_TAG = "JobMapActivity";
    protected GoogleApiClient mGoogleApiClient;
    private PlaceAutoCompleteAdapter mAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<Polyline> polylines;
    private int[] colors = new int[]{R.color.primary_dark, R.color.black, R.color.primary_dark_material_light};
    private static final LatLngBounds BOUNDS_LOCATION = new LatLngBounds(
            new LatLng(26.400000, 80.916572),
            new LatLng(26.768933, 81.046177)
    );

    @InjectView(R.id.wrapper)
    CoordinatorLayout layout;
    private LocationManager locationManager;
    private double longitude;
    private double latitude;
    private boolean hasOptional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_map);
        ButterKnife.inject(this);
        polylines = new ArrayList<>();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        MapsInitializer.initialize(this);
        mGoogleApiClient.connect();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if (intent != null) {
            double startLat = intent.getDoubleExtra(MapActivity.EXTRA_MAP_START_LATITUDE, 0.0);
            double startLong = intent.getDoubleExtra(MapActivity.EXTRA_MAP_START_LONGITUDE, 0.0);
            double destLat = intent.getDoubleExtra(MapActivity.EXTRA_MAP_DEST_LATITUDE, 0.0);
            double destLong = intent.getDoubleExtra(MapActivity.EXTRA_MAP_DEST_LONGITUDE, 0.0);
            hasOptional = intent.getBooleanExtra(MapActivity.EXTRA_MAP_HAS_OPTIONAL, false);
            if (hasOptional) {
                double optLat = intent.getDoubleExtra(MapActivity.EXTRA_MAP_OPT_LATITUDE, 0.0);
                double optLong = intent.getDoubleExtra(MapActivity.EXTRA_MAP_OPT_LONGITUDE, 0.0);
                opt = new LatLng(optLat, optLong);
            }
            start = new LatLng(startLat, startLong);
            dest = new LatLng(destLat, destLong);

        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        mAdapter = new PlaceAutoCompleteAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_LOCATION, null);
        map.setBuildingsEnabled(true);
        map.setIndoorEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        /*
        * Updates the bounds being used by the auto complete adapter based on the position of the
        * map.
        * */
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;
                mAdapter.setBounds(bounds);
            }
        });


        CameraUpdate center = CameraUpdateFactory.newLatLngZoom(new LatLng(26.846471, 80.944798), 18);
        map.animateCamera(center);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 10000, 100,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        /*CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                        map.moveCamera(center);
                        map.animateCamera(zoom);*/
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000, 100, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                        map.moveCamera(center);
                        map.animateCamera(zoom);

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });

    }

    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRoutingIndex) {
        progressDialog.dismiss();
        CameraUpdate center = CameraUpdateFactory.newLatLngZoom(start, 18);
        map.animateCamera(center);


        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i < route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % colors.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(colors[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = map.addPolyline(polyOptions);
            polylines.add(polyline);

            Snackbar.make(layout, "Route " + (i + 1) + "\ndistance " + route.get(i).getDistanceValue(), Snackbar.LENGTH_INDEFINITE).setAction("confirm", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (start != null && dest != null) {

                    }
                }
            }).show();
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        options.draggable(true);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(dest);
        options.draggable(true);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        map.addMarker(options);
        if (hasOptional) {
            options = new MarkerOptions();
            options.position(opt);
            options.draggable(true);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            map.addMarker(options);
        }


    }

    @Override
    public void onRoutingCancelled() {

    }

    public void sendRequest() {
        if (Util.Operations.isOnline(this)) {
            route();
        } else {
            Snackbar.make(layout, "No internet connectivity", Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    public void route() {
        progressDialog = ProgressDialog.show(this,"Please    wait.", "Fetching route information.", true);
        progressDialog.setCancelable(false);
        if (start == null || dest == null) {
            if (start == null) {

            }
            if (dest == null) {

            }
            if (opt != null) {

            }
        } else {
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(start, dest)
                    .build();
            routing.execute();
        }
    }


}
