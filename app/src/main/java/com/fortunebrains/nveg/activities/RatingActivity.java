package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 1/12/2017.
 */
public class RatingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvItemName,tvItemAddress,tvSubmit,tvRating;
    EditText etReview,etName,etMobile,etEmail;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("  Rating ");
        ivBack.setOnClickListener(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        tvItemName = (TextView) findViewById(R.id.tvItemName);
        tvItemAddress = (TextView) findViewById(R.id.tvItemAddress);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        tvRating = (TextView) findViewById(R.id.tvRating);

        etName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etReview = (EditText) findViewById(R.id.etReview);
        ratingBar = (RatingBar) findViewById(R.id.rbRating);
        tvSubmit.setOnClickListener(this);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                tvRating.setText(""+rating);
            }
        });




    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSubmit:



                break;
        }
    }
}
