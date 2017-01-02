package com.fortunebrains.nveg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.DashBoardActivity;
import com.fortunebrains.nveg.adapters.ImageViewPagerAdapter;

/**
 * Created by sree on 12/21/2016.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private ViewPager _mViewPager;
    private ImageViewPagerAdapter _adapter;
    ImageView ivHome, ivFav, ivCart, ivNotifications, ivProfile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,null);

        ivHome = (ImageView)view. findViewById(R.id.ivHome);
        ivCart = (ImageView) view.findViewById(R.id.ivCart);
        ivFav = (ImageView) view.findViewById(R.id.ivFav);
        ivNotifications = (ImageView)view. findViewById(R.id.ivNotifications);
        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);

        ivHome.setOnClickListener(this);
        ivFav.setOnClickListener(this);
        ivCart.setOnClickListener(this);
        ivNotifications.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        _mViewPager = (ViewPager)view.findViewById(R.id.imageviewPager);
        _adapter = new ImageViewPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        return view;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        String title ="";
        switch (v.getId()) {
            case R.id.ivHome:
                fragment = new HomeFragment();
                title = "Home";

                break;
            case R.id.ivFav:
                title = "Favourites";

                break;
            case R.id.ivCart:
                title = "Cart";

                break;
            case R.id.ivNotifications:
                title = "Notifications";
                break;
            case R.id.ivProfile:
                fragment = new ProfileFragment();
                title = "Profile";
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
