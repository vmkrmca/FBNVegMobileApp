package com.fortunebrains.nveg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.DashBoardActivity;
import com.fortunebrains.nveg.adapters.CategoryAdapter;
import com.fortunebrains.nveg.adapters.ImageViewPagerAdapter;
import com.fortunebrains.nveg.common.Category;
import com.fortunebrains.nveg.common.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by sree on 12/21/2016.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private ViewPager _mViewPager;
    private ImageViewPagerAdapter _adapter;
    ImageView ivHome, ivFav, ivCart, ivNotifications, ivProfile;
    GridView gridView;

    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat"

    };

    int categoryImages[] = {
            R.mipmap.chicken,R.mipmap.chicken_image,
            R.mipmap.fish,R.mipmap.clarge,
            R.mipmap.chicken,R.mipmap.chicken_image,
            R.mipmap.fish,R.drawable.clarge};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,null);

        ivHome = (ImageView)view. findViewById(R.id.ivHome);
        ivCart = (ImageView) view.findViewById(R.id.ivCart);
        ivFav = (ImageView) view.findViewById(R.id.ivFav);
        ivNotifications = (ImageView)view. findViewById(R.id.ivNotifications);
        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);

        gridView = (GridView) view.findViewById(R.id.gvCategories);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
        final ArrayList<Category> categoryArrayList = prepareData();
        CategoryAdapter adapter = new CategoryAdapter(getActivity(),categoryArrayList);
        gridView.setAdapter(adapter);


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

    private ArrayList<Category> prepareData(){

        ArrayList<Category> categoryArrayList = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            Category category = new Category();
            category.setCategoryImage(categoryImages[i]);
            categoryArrayList.add(category);
        }
        return categoryArrayList;
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

        if (fragment != null)
        {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }
}
