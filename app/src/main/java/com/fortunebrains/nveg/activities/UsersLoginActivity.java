package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 1/25/2017.
 */

public class UsersLoginActivity extends Activity implements View.OnClickListener {
    TextView tvcLogin,tviLogin,tvbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login);

        tvcLogin = (TextView) findViewById(R.id.tvcLogin);
        tvbLogin = (TextView) findViewById(R.id.tvbLogin);
        tviLogin = (TextView) findViewById(R.id.tviLogin);

        tvcLogin.setOnClickListener(this);
        tvbLogin.setOnClickListener(this);
        tviLogin.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvcLogin:
//                startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
                openWhatsappContact("+91 9948982584");
                break;
            case R.id.tvbLogin:
                startActivity(new Intent(getApplicationContext(),BToBLoginActivty.class));
                break;
            case R.id.tviLogin:
                startActivity(new Intent(getApplicationContext(),InvesterLoginActivty.class));
                break;

        }

    }


    void openWhatsappContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }
}
