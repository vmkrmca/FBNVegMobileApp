package com.fortunebrains.nveg.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fortunebrains.nveg.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class EnableLocationActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ImageView ivBack, ivNext;
    TextView tvUseMyLocation, tvPickUserLocation;
    LocationManager locationManager;
    Intent i;
    // LogCat tag
    private static final String TAG = EnableLocationActivity.class.getSimpleName();

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;


    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_loc_screen);

        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        // First we need to check availability of play services
        if (checkPlayServices()) {

            // Building the GoogleApi client
            //buildGoogleApiClient();
        }

        intializeControls();


    }


    /**
     * Creating google api client object
     * *//*
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(EnableLocationActivity.this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();


        Log.i("Client",""+mGoogleApiClient);
    }*/

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();
    }

    private void intializeControls() {
        tvUseMyLocation = (TextView) findViewById(R.id.tvUseMyLocation);
        tvPickUserLocation = (TextView) findViewById(R.id.tvPickUserLocation);


        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.enable_location_actionbar, null);
        ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        ivNext = (ImageView) mCustomView.findViewById(R.id.ivNext);
        ivBack.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        tvPickUserLocation.setOnClickListener(this);
        tvUseMyLocation.setOnClickListener(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    private void checkGps()
    {
        boolean deviceHasGPS = false;
        if (locationManager.getAllProviders().contains(
                LocationManager.GPS_PROVIDER)) {
            deviceHasGPS = true;
        }
        if (!deviceHasGPS) {
            // GPS not present on Device
            Toast.makeText(EnableLocationActivity.this,
                    "GPS is not present on the device !", Toast.LENGTH_SHORT)
                    .show();
            finish();
        } else {
            if (!locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        this);
                alertDialogBuilder
                        .setMessage("Please enable the GPS...")
                        .setCancelable(false)
                        .setPositiveButton("Goto Settings Page To Enable GPS",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Intent callGPSSettingIntent = new Intent(
                                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(callGPSSettingIntent);
                                    }
                                });
                alertDialogBuilder.show();
            } else {
                i = new Intent(getApplicationContext(), ShoppingActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvUseMyLocation:
//                checkGps();
                //displayLocation();
                break;
            case R.id.tvPickUserLocation:
                startActivity(new Intent(getApplicationContext(), ShoppingActivity.class));
                break;

            case R.id.ivBack:

                break;
            case R.id.ivNext:

                break;


        }
    }

   /* private void displayLocation()
    {
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);
        Log.i("Location ::",""+mLastLocation);
        if (mLastLocation != null)
        {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            Log.i("latitude ::",latitude+"");
            Log.i("longitude ::",longitude+"");
        }
        else
        {
            Log.i("Location ::","NULL");
        }
    }*/


    @Override
    public void onConnected(Bundle bundle) {

       //displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

    }
}
