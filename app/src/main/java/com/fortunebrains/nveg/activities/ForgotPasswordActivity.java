package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class ForgotPasswordActivity extends Activity implements View.OnClickListener {
    EditText etEmail;
    TextView tvSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpassword_screen);

        etEmail = (EditText) findViewById(R.id.etEmail);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);

        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


    }
}
