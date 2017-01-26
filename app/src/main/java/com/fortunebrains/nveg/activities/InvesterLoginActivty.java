package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 1/25/2017.
 */
public class InvesterLoginActivty extends Activity implements View.OnClickListener {
    TextView tvForgtPassword,tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invester_login);

        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvForgtPassword = (TextView) findViewById(R.id.tvForgtPassword);

        String signUp="SignUp";
        String forgotPassword="Forgot Password";

        SpannableString content = new SpannableString(signUp);
        SpannableString content1 = new SpannableString(forgotPassword);

        content.setSpan(new UnderlineSpan(), 0, signUp.length(), 0);
        content1.setSpan(new UnderlineSpan(), 0, forgotPassword.length(), 0);
        tvSignUp.setText(content);
        tvForgtPassword.setText(content1);

        tvSignUp.setOnClickListener(this);
        tvForgtPassword.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvSignUp:
                startActivity(new Intent(getApplicationContext(),InvesterSignUpActivity.class));
                break;
            case R.id.tvForgtPassword:
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
                break;

        }
    }
}
