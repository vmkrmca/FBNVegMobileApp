package com.fortunebrains.nveg.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.AddAdreessActivity;
import com.fortunebrains.nveg.activities.AdreessBookActivity;
import com.fortunebrains.nveg.activities.CancelActivity;
import com.fortunebrains.nveg.activities.ChangePasswordActivity;
import com.fortunebrains.nveg.activities.DashBoardActivity;
import com.fortunebrains.nveg.activities.EditProfileActivity;
import com.fortunebrains.nveg.activities.MyOrdersActivity;
import com.fortunebrains.nveg.activities.ShoppingActivity;
import com.fortunebrains.nveg.activities.ViewProfileActivity;
import com.fortunebrains.nveg.common.RoundedImageView;
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
public class ProfileFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    android.app.ActionBar actionBar;
    int key;
    RoundedImageView rvProfilePic;
    TextView tvVegID,tvGCustomer,tvMyOrders,tvCancelOrders,tvWallet,tvEditProfile,tvChangePassword,tvViewProfile,tvAddAddress,tvAddressBook;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,null);
        actionBar = getActivity().getActionBar();
        tvGCustomer = (TextView) view.findViewById(R.id.tvGCustomer);
        tvMyOrders = (TextView) view.findViewById(R.id.tvMyOrders);
        tvCancelOrders = (TextView) view.findViewById(R.id.tvCancelOrders);
        tvWallet = (TextView) view.findViewById(R.id.tvWallet);
        tvEditProfile = (TextView) view.findViewById(R.id.tvEditProfile);
        rvProfilePic = (RoundedImageView) view.findViewById(R.id.rvProfilePic);
        tvChangePassword = (TextView) view.findViewById(R.id.tvChangePassword);
        tvVegID = (TextView) view.findViewById(R.id.tvVegID);
        tvViewProfile = (TextView) view.findViewById(R.id.tvViewProfile);
        tvAddAddress = (TextView) view.findViewById(R.id.tvAddAddress);
        tvAddressBook = (TextView) view.findViewById(R.id.tvAddressBook);






        sharedPreferences = getActivity().getSharedPreferences("prefName",getActivity().MODE_APPEND);

        tvGCustomer.setOnClickListener(this);
        tvMyOrders.setOnClickListener(this);
        tvCancelOrders.setOnClickListener(this);
        tvWallet.setOnClickListener(this);
        tvEditProfile.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvViewProfile.setOnClickListener(this);
        tvAddAddress.setOnClickListener(this);
        tvAddressBook.setOnClickListener(this);
        String uri = sharedPreferences.getString("URI",null);
        String name = sharedPreferences.getString("NAME",null);
        key = sharedPreferences.getInt("KEY",0);

        Log.i("KEY ",""+key);

        setHasOptionsMenu(true);
        Picasso.with(getActivity()).load(uri).into(rvProfilePic);

        return view;

    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View v)
    {

        Fragment fragment = null;
        switch (v.getId())
        {
            case R.id.tvGCustomer:
                fragment = new CustomerFragment();
                break;
            case R.id.tvMyOrders:
                startActivity(new Intent(getActivity(),MyOrdersActivity.class));
                break;
            case R.id.tvCancelOrders:
                startActivity(new Intent(getActivity(),CancelActivity.class));
                break;
            case R.id.tvWallet:
                fragment = new WalletFragment();
                break;
            case R.id.tvEditProfile:
                startActivity(new Intent(getActivity(),EditProfileActivity.class));
                break;
            case R.id.tvChangePassword:
                startActivity(new Intent(getActivity(),ChangePasswordActivity.class));
                break;
            case R.id.tvViewProfile:
                startActivity(new Intent(getActivity(),ViewProfileActivity.class));
                break;
            case R.id.tvAddAddress:
                startActivity(new Intent(getActivity(),AddAdreessActivity.class));
                break;
            case R.id.tvAddressBook:
                startActivity(new Intent(getActivity(),AdreessBookActivity.class));
                break;

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }

    }



    private void fbLogout()
    {
        FacebookSdk.sdkInitialize(getActivity());
        LoginManager.getInstance().logOut();
        startActivity(new Intent(getActivity(), DashBoardActivity.class));
        getActivity().finish();

    }

}
