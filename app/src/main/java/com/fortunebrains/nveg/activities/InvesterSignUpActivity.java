package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 1/25/2017.
 */
public class InvesterSignUpActivity extends Activity implements View.OnClickListener {
    TextView tvClickHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invester_signup);

        tvClickHere = (TextView) findViewById(R.id.tvClickHere);
        tvClickHere.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvClickHere:
                startActivity(new Intent(getApplicationContext(),AgreeMentActivity.class));
                break;
        }
    }
}
