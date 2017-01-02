package com.fortunebrains.nveg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.ImageViewPagerAdapter;


public class DashBoardActivity extends FragmentActivity implements View.OnClickListener {
    TextView tvFLogin,tvGLogin,tvSignIn,tvSignUp,tvSkip;
    private ViewPager _mViewPager;
    private ImageViewPagerAdapter _adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);
        intialViewControls();

    }

    private void intialViewControls()
    {
        tvFLogin = (TextView) findViewById(R.id.tvFLogin);
        tvGLogin = (TextView) findViewById(R.id.tvGLogin);
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSkip   = (TextView) findViewById(R.id.tvSkip);

        tvFLogin.setOnClickListener(this);
        tvGLogin.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        tvSkip.setOnClickListener(this);



        _mViewPager = (ViewPager)findViewById(R.id.imageviewPager);
        _adapter = new ImageViewPagerAdapter(DashBoardActivity.this, getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvFLogin:
                Toast.makeText(getApplicationContext(),"Neeed to Time to setup Facebook Login",Toast.LENGTH_SHORT).show();


                break;
            case R.id.tvGLogin:
                Toast.makeText(getApplicationContext(),"Neeed to Time to setup GooglePlus Login",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tvSignIn:

                startActivity(new Intent(getApplicationContext(),SignInActivity.class));

                break;
            case R.id.tvSignUp:
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                break;
            case R.id.tvSkip:
                startActivity(new Intent(getApplicationContext(),EnableLocationActivity.class));
                break;

        }
    }
}
