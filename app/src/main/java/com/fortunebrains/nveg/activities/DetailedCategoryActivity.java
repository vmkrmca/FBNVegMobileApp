package com.fortunebrains.nveg.activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.ImageViewPagerAdapter;

/**
 * Created by sree on 1/6/2017.
 */
public class DetailedCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CALL = 1;
    ViewPager viewPager;
    String Location;
    ImageView ivLeftNav,ivRightNav,ivCall,ivFav,ivShare,ivRating,ivCart,ivLocation;
    private ImageViewPagerAdapter _adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_category);

        Location = getIntent().getExtras().getString("Location","");

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("  Categoery ");
        ivBack.setOnClickListener(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ivLeftNav = (ImageView) findViewById(R.id.left_nav);

        ivCall = (ImageView) findViewById(R.id.ivCall);
        ivCart = (ImageView) findViewById(R.id.ivCart);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        ivRating = (ImageView) findViewById(R.id.ivRating);
        ivLocation = (ImageView) findViewById(R.id.ivLocation);
        ivFav = (ImageView) findViewById(R.id.ivFav);


        ivRightNav = (ImageView) findViewById(R.id.right_nav);
        _adapter = new ImageViewPagerAdapter(DetailedCategoryActivity.this, getSupportFragmentManager());
        viewPager.setAdapter(_adapter);
        ivLeftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        ivRightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });

        ivCall.setOnClickListener(this);
        ivCart.setOnClickListener(this);
        ivFav.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivLocation.setOnClickListener(this);
        ivRating.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivCall:
                callIntent();
                break;
            case R.id.ivCart:
                break;
            case R.id.ivFav:
                break;
            case R.id.ivLocation:

                Intent i = new Intent(getApplicationContext(),LocationActivity.class);
                i.putExtra("Location",Location);
                startActivity(i);
                break;
            case R.id.ivRating:
                break;
            case R.id.ivShare:
                shareIntent();
                break;

        }
    }

    private void shareIntent() {
        String shareBody = "Here is the share content body";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Sharing"));
    }

    private void callIntent() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("CALL")
                .setMessage("Do You want to Call to this Number")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callingIntent();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    private void callingIntent()
    {

        Intent callAct = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9966654254"));
        if (ContextCompat.checkSelfPermission(DetailedCategoryActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(DetailedCategoryActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            startActivity(callAct);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case R.id.ivCall:
                break;
        }
        if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
        }
        else {
        }
    }

}
