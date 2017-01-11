package com.fortunebrains.nveg.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.MyCancelAdapter;
import com.fortunebrains.nveg.adapters.MyOrderAdapter;
import com.fortunebrains.nveg.common.MyCancelOrders;
import com.fortunebrains.nveg.common.MyOrder;
import com.fortunebrains.nveg.fragments.ProfileFragment;

import java.util.ArrayList;
import java.util.List;


public class MyOrdersActivity extends AppCompatActivity implements View.OnClickListener {

    private List<MyOrder> myOrderArrayList = new ArrayList<MyOrder>();
    private RecyclerView recyclerViews;
    private MyOrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("   My Orders");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        recyclerViews = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MyOrderAdapter(myOrderArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViews.setLayoutManager(mLayoutManager);
        recyclerViews.setItemAnimator(new DefaultItemAnimator());
        recyclerViews.setAdapter(mAdapter);
        prepareMyOrderData();
    }

    @Override
    public void onClick(View v) {
        finish();

    }

    private void prepareMyOrderData() {

        MyOrder myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderArrayList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderArrayList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderArrayList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderArrayList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderArrayList.add(myOrder);

        mAdapter.notifyDataSetChanged();
    }
}
