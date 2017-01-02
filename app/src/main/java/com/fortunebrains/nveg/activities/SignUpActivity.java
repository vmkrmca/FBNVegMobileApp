package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class SignUpActivity extends Activity
{
    TextView tvSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        String text = "Already have an Account<font color='red'>Login</font> Here.";
        tvSignIn.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

    }
}
