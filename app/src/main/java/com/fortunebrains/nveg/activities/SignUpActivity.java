package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class SignUpActivity extends Activity implements View.OnClickListener {
    TextView tvSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        String text = "Already have an Account<font color='red'>Login</font> Here.";
        tvSignIn.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        tvSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvSignIn:

                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));

                break;
        }
    }
}
