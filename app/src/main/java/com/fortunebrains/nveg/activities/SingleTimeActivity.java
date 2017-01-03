package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class SingleTimeActivity extends Activity implements View.OnClickListener {
    TextView tvClickhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletime_screen);
        tvClickhere = (TextView) findViewById(R.id.tvClickhere);
        tvClickhere.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        startActivity(new Intent(getApplicationContext(),ShoppingActivity.class));
        finish();
    }
}
