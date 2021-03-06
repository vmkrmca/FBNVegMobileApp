package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 1/5/2017.
 */
public class ViewProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("   View Profile");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

    @Override
    public void onClick(View v) {

        finish();
    }
}
