package com.fortunebrains.nveg.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
import com.fortunebrains.nveg.fragments.InviteFragment;
import com.fortunebrains.nveg.fragments.NVegFragment;
import com.fortunebrains.nveg.fragments.OffersFragment;
import com.fortunebrains.nveg.fragments.ProfileFragment;
import com.fortunebrains.nveg.fragments.RateUsFragment;
import com.fortunebrains.nveg.fragments.ShareFragment;
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
    RoundedImageView rvProfilePic;
    GoogleApiClient mGoogleApiClient;
    private FragmentDrawer drawerFragment;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);
        sharedPreferences = getSharedPreferences("prefName",MODE_APPEND);
        rvProfilePic = (RoundedImageView) findViewById(R.id.rvProfilePic);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        String uri = sharedPreferences.getString("URI",null);
        String name = sharedPreferences.getString("NAME",null);
         key = sharedPreferences.getInt("KEY",0);

        Log.i("uri ::",""+uri);
        Log.i("Name ::",""+name);
        Log.i("KEY ::",""+key);


        Picasso.with(getApplicationContext()).load(uri).into(rvProfilePic);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new CategoryFragment();
                title = getString(R.string.title_ocategory);
                break;
            case 2:
                fragment = new HotDealsFragment();
                title = getString(R.string.title_hotdeals);
                break;
            case 3:
                fragment = new OffersFragment();
                title = getString(R.string.title_offers);
                break;
            case 4:
                fragment = new NVegFragment();
                title = getString(R.string.title_nveg);
                break;
            case 5:
               inviteUs();
                break;
            case 6:
                fragment = new RateUsFragment();
                title = getString(R.string.title_rate);
                break;
            case 7:
                fragment = new FeedbackFragment();
                title = getString(R.string.title_feedback);
                break;

            case 8:
                fragment = new AboutUsFragment();
                title = getString(R.string.title_about);
                break;
            case 9:
                fragment = new HelpFragment();
                title = getString(R.string.title_help);
                break;
            case 10:
                fragment = new ContactUsFragment();
                title = getString(R.string.title_contact);
                break;
            case 11:
                logout();
                break;


            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    private void inviteUs() {

        String shareBody = "Here is the share content body";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Sharing"));
    }

    private void logout()
    {
        AlertDialog.Builder adb  =new AlertDialog.Builder(ShoppingActivity.this);
        adb.setMessage("Do You Want to Logout or Not");
        adb.setTitle("LOGOUT");
        adb.setIcon(R.mipmap.ic_launcher);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (key == 1001)
                {
                    FacebookSdk.sdkInitialize(getApplicationContext());
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
                    finish();

                }
                else
                {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status)
                                {
                                    startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
                                    finish();
                                }
                            });
                }

            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });


        adb.show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }




}
