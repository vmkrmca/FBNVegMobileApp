package com.fortunebrains.nveg.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class EnableLocationActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivBack,ivNext;
    LocationManager lm;
    TextView tvUseMyLocation,tvPickUserLocation;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_loc_screen);
        intializeControls();
        checkingGPSLocation();

    }

    private void intializeControls()
    {
        tvUseMyLocation = (TextView) findViewById(R.id.tvUseMyLocation);
        tvPickUserLocation = (TextView) findViewById(R.id.tvPickUserLocation);

        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
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

    private void checkingGPSLocation()
    {
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(EnableLocationActivity.this);
            dialog.setMessage("gps_network_not_enabled");
            dialog.setPositiveButton("OpenLocation", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvUseMyLocation:

                break;
            case R.id.tvPickUserLocation:
                startActivity(new Intent(getApplicationContext(),ShoppingActivity.class));
                break;

            case R.id.ivBack:

                break;
            case R.id.ivNext:

                break;


        }
    }
}
