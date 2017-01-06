package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.CategoryAdapter;
import com.fortunebrains.nveg.adapters.CategoryListAdapter;
import com.fortunebrains.nveg.common.CategoryData;

import java.util.ArrayList;

/**
 * Created by sree on 1/6/2017.
 */
public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    ElegantNumberButton button;
    RecyclerView rvCategory;
    ArrayList<CategoryData> categoryDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvCategory = (RecyclerView) findViewById(R.id.rvCategories);

        categoryDataArrayList = new ArrayList<CategoryData>();

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("   Category");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        dataSaving();
        Log.i("Size",""+categoryDataArrayList.size());
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(CategoryActivity.this,categoryDataArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCategory.setLayoutManager(mLayoutManager);
        rvCategory.setItemAnimator(new DefaultItemAnimator());
        rvCategory.setAdapter(categoryListAdapter);


    }

    private void dataSaving() {

        CategoryData categoryData = new CategoryData();
        categoryData.setCategoryImage(R.mipmap.chicken);
        categoryData.setCategoryName("Chicken");
        categoryData.setCategoryType("NVeg");
        categoryData.setLocation("Hyderbad");
        categoryData.setCategoryAmt("200");
        categoryDataArrayList.add(categoryData);

        categoryData = new CategoryData();
        categoryData.setCategoryImage(R.mipmap.chicken);
        categoryData.setCategoryName("Mutton");
        categoryData.setCategoryType("NVeg");
        categoryData.setLocation("Madhapur");
        categoryData.setCategoryAmt("150");
        categoryDataArrayList.add(categoryData);


        categoryData = new CategoryData();
        categoryData.setCategoryImage(R.mipmap.chicken);
        categoryData.setCategoryName("Fist");
        categoryData.setCategoryType("NVeg");
        categoryData.setLocation("Kukatpally");
        categoryData.setCategoryAmt("350");
        categoryDataArrayList.add(categoryData);

        categoryData = new CategoryData();
        categoryData.setCategoryImage(R.mipmap.chicken);
        categoryData.setCategoryName("Chicken");
        categoryData.setCategoryType("NVeg");
        categoryData.setLocation("HitechCity");
        categoryData.setCategoryAmt("250");
        categoryDataArrayList.add(categoryData);

        categoryData = new CategoryData();
        categoryData.setCategoryImage(R.mipmap.chicken);
        categoryData.setCategoryName("Chicken");
        categoryData.setCategoryType("NVeg");
        categoryData.setLocation("Ameerpet");
        categoryData.setCategoryAmt("450");
        categoryDataArrayList.add(categoryData);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
