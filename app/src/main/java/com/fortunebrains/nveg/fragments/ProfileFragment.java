package com.fortunebrains.nveg.fragments;

import android.app.ActionBar;import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class ProfileFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    android.app.ActionBar actionBar;
    TextView tvVegID,tvGCustomer,tvMyOrders,tvCancelOrders,tvWallet,tvEditProfile,tvChangePassword,tvViewProfile,tvAddAddress;
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
        tvChangePassword = (TextView) view.findViewById(R.id.tvChangePassword);
        tvVegID = (TextView) view.findViewById(R.id.tvVegID);
        tvViewProfile = (TextView) view.findViewById(R.id.tvViewProfile);
        tvAddAddress = (TextView) view.findViewById(R.id.tvAddAddress);



        tvGCustomer.setOnClickListener(this);
        tvMyOrders.setOnClickListener(this);
        tvCancelOrders.setOnClickListener(this);
        tvWallet.setOnClickListener(this);
        tvEditProfile.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvViewProfile.setOnClickListener(this);
        tvAddAddress.setOnClickListener(this);



        return view;

    }

    @Override
    public void onClick(View v)
    {

        Fragment fragment = null;
        String title ="";
        switch (v.getId())
        {
            case R.id.tvGCustomer:
                fragment = new CustomerFragment();
                title ="Genuine Customers";
                break;
            case R.id.tvMyOrders:
                fragment = new MyOrdersFragment();
                title ="My Orders";
                break;
            case R.id.tvCancelOrders:
                fragment = new CancelOrdersFragment();
                title ="Cancel Orders";
                break;
            case R.id.tvWallet:
                fragment = new WalletFragment();
                title ="MyWallet";
                break;
            case R.id.tvEditProfile:
                fragment = new EditProfileFragment();
                title ="EditProfile";
                break;
            case R.id.tvChangePassword:
                fragment = new ChangePasswordFragment();
                title ="ChangePassword";
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }




    }

}
