package com.fortunebrains.nveg.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.common.RoundedImageView;
import com.fortunebrains.nveg.fragments.AboutUsFragment;
import com.fortunebrains.nveg.fragments.CategoryFragment;
import com.fortunebrains.nveg.fragments.ContactUsFragment;
import com.fortunebrains.nveg.fragments.FeedbackFragment;
import com.fortunebrains.nveg.fragments.FragmentDrawer;
import com.fortunebrains.nveg.fragments.HelpFragment;
import com.fortunebrains.nveg.fragments.HomeFragment;
import com.fortunebrains.nveg.fragments.HotDealsFragment;
import com.fortunebrains.nveg.fragments.NVegFragment;
import com.fortunebrains.nveg.fragments.OffersFragment;
import com.fortunebrains.nveg.fragments.RateUsFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

/**
 * Created by sree on 12/21/2016.
 */
public class ShoppingActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    Toolbar mToolbar;
    SharedPreferences sharedPreferences;
    ImageView rvProfilePic;
    GoogleApiClient mGoogleApiClient;
    private FragmentDrawer drawerFragment;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);
        sharedPreferences = getSharedPreferences("prefName", MODE_APPEND);
        rvProfilePic = (ImageView) findViewById(R.id.rvProfilePic);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);


        String uri = sharedPreferences.getString("URI", null);
        String name = sharedPreferences.getString("NAME", null);
        key = sharedPreferences.getInt("KEY", 0);

        Glide.with(ShoppingActivity.this).load(uri).asBitmap().centerCrop().override(150, 150).into(new BitmapImageViewTarget(rvProfilePic) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                rvProfilePic.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    private void displayView(int position)
    {
        Fragment fragment = new HomeFragment();
        String title = getString(R.string.app_name);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (item.getItemId())
        {
            case R.id.action_filter:
                startActivity(new Intent(getApplicationContext(),FilterActivity.class));

                break;
            case R.id.action_map:
                startActivity(new Intent(getApplicationContext(),SelectLocationActivity.class));

                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDrawerItemSelected(View view, int position)
    {
    }
    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            //Ask the user if they want to quit
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("QUIT APP")
                    .setMessage("Do You want to Reallly Quit the App")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }
    }
}
