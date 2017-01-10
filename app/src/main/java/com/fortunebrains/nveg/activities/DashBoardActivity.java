package com.fortunebrains.nveg.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.ImageViewPagerAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class DashBoardActivity extends FragmentActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1001;
    TextView tvSignIn,tvSignUp,tvSkip;
    ImageView ivFacebook,ivGooglePlus;
    private ViewPager _mViewPager;
    private ImageViewPagerAdapter _adapter;
    GoogleApiClient mGoogleApiClient;
    CallbackManager callbackManager;
    LoginButton loginButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final int FBKEY = 1001;
    public static final int GKEY = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

        setContentView(R.layout.activity_dashboard_screen);

        intialViewControls();
        Log.i("FBLogin",""+isLoggedIn());


        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                if (loginResult!=null)
                {
                    Profile profile = Profile.getCurrentProfile();
                    if (profile!=null)
                    {
                        getDisplayMessage(profile);
                    }
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void getDisplayMessage(Profile profile) {

        Uri uri = profile.getProfilePictureUri(150,150);
        String Name = profile.getName();

        editor.putString("URI",""+uri);
        editor.putString("NAME",Name);
        editor.putInt("KEY",FBKEY);
        editor.commit();

        Log.i("Uri ::",""+uri);
        Log.i("Name ::",""+Name);
        startActivity(new Intent(getApplicationContext(),ShoppingActivity.class));
    }

    private void intialViewControls()
    {
        sharedPreferences = getSharedPreferences("prefName",MODE_APPEND);
        editor = sharedPreferences.edit();
        ivFacebook = (ImageView) findViewById(R.id.ivFLogin);
        ivGooglePlus = (ImageView) findViewById(R.id.ivGLogin);
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSkip   = (TextView) findViewById(R.id.tvSkip);

        ivFacebook.setOnClickListener(this);
        ivGooglePlus.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
        _mViewPager = (ViewPager)findViewById(R.id.imageviewPager);
        _adapter = new ImageViewPagerAdapter(DashBoardActivity.this, getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);


    }

    public boolean isLoggedIn()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivFLogin:

                if (v == ivFacebook)
                {
                    loginButton.performClick();
                }
                break;
            case R.id.ivGLogin:

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
            case R.id.tvSignIn:
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                break;
            case R.id.tvSignUp:
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                break;
            case R.id.tvSkip:
                startActivity(new Intent(getApplicationContext(),ShoppingActivity.class));
                break;
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.d("Data", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Uri  uri = acct.getPhotoUrl();
            String Name = acct.getDisplayName();
            editor.putString("URI",""+uri);
            editor.putString("NAME",Name);
            editor.putInt("KEY",GKEY);
            editor.commit();
            startActivity(new Intent(getApplicationContext(),ShoppingActivity.class));
        }
    }


}
