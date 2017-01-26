package com.fortunebrains.nveg.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.common.RoundedImageView;
import com.fortunebrains.nveg.fragments.CustomerFragment;
import com.fortunebrains.nveg.fragments.WalletFragment;
import com.squareup.picasso.Picasso;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    android.app.ActionBar actionBar;
    int key;
    ImageView rvProfilePic;
    TextView tvVegID, tvGCustomer, tvMyOrders, tvCancelOrders, tvWallet, tvEditProfile, tvChangePassword, tvViewProfile, tvAddAddress, tvAddressBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("Profile  Details");
        ivBack.setOnClickListener(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        tvGCustomer = (TextView) findViewById(R.id.tvGCustomer);
        tvMyOrders = (TextView) findViewById(R.id.tvMyOrders);
        tvCancelOrders = (TextView) findViewById(R.id.tvCancelOrders);
        tvWallet = (TextView) findViewById(R.id.tvWallet);
        tvEditProfile = (TextView) findViewById(R.id.tvEditProfile);
        rvProfilePic = (ImageView) findViewById(R.id.rvProfilePic);
        tvChangePassword = (TextView) findViewById(R.id.tvChangePassword);
        tvVegID = (TextView) findViewById(R.id.tvVegID);
        tvViewProfile = (TextView) findViewById(R.id.tvViewProfile);
        tvAddAddress = (TextView) findViewById(R.id.tvAddAddress);
        tvAddressBook = (TextView) findViewById(R.id.tvAddressBook);

        sharedPreferences = getSharedPreferences("prefName", MODE_APPEND);

        tvGCustomer.setOnClickListener(this);
        tvMyOrders.setOnClickListener(this);
        tvCancelOrders.setOnClickListener(this);
        tvWallet.setOnClickListener(this);
        tvEditProfile.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvViewProfile.setOnClickListener(this);
        tvAddAddress.setOnClickListener(this);
        tvAddressBook.setOnClickListener(this);
        String uri = sharedPreferences.getString("URI", null);
        String name = sharedPreferences.getString("NAME", null);
        key = sharedPreferences.getInt("KEY", 0);

        Log.i("KEY ", "" + key);

        Glide.with(ProfileActivity.this).load(uri).asBitmap().centerCrop().override(150, 150).into(new BitmapImageViewTarget(rvProfilePic) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                rvProfilePic.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvGCustomer:
//                fragment = new CustomerFragment();
                break;
            case R.id.tvMyOrders:
                startActivity(new Intent(getApplicationContext(), MyOrdersActivity.class));
                break;
            case R.id.tvCancelOrders:
                startActivity(new Intent(getApplicationContext(), CancelActivity.class));
                break;
            case R.id.tvWallet:
//                fragment = new WalletFragment();
                break;
            case R.id.tvEditProfile:
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                break;
            case R.id.tvChangePassword:
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                break;
            case R.id.tvViewProfile:
                startActivity(new Intent(getApplicationContext(), ViewProfileActivity.class));
                break;
            case R.id.tvAddAddress:
                startActivity(new Intent(getApplicationContext(), AddAdreessActivity.class));
                break;
            case R.id.tvAddressBook:
                startActivity(new Intent(getApplicationContext(), AdreessBookActivity.class));
                break;
            case R.id.ivBack:
                finish();
                break;

        }
    }
}
