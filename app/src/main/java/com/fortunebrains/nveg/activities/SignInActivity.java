package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class SignInActivity extends Activity implements View.OnClickListener {
    TextView tvRegister,tvSignIn,tvFpassword;
    EditText etEmail,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initializeViewControls();



    }

    private void initializeViewControls() {
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvFpassword = (TextView) findViewById(R.id.tvFpassword);
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        String text = "Don't have an Account Click here to <font color='red'>Register</font>.";
        tvRegister.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        tvRegister.setOnClickListener(this);
        tvFpassword.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvRegister:
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                break;
            case R.id.tvSignIn:
                startActivity(new Intent(getApplicationContext(),SingleTimeActivity.class));
                break;
            case R.id.tvFpassword:
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
                break;


        }
    }
}
