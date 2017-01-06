package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.os.Bundle;
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
import com.fortunebrains.nveg.common.MyCancelOrders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sree on 1/5/2017.
 */
public class CancelActivity extends AppCompatActivity implements View.OnClickListener {
    private List<MyCancelOrders> myCancelOrdersArrayList = new ArrayList<>();
    private RecyclerView recyclerViews;
    private MyCancelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("   Cancel Orders");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        recyclerViews = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MyCancelAdapter(myCancelOrdersArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViews.setLayoutManager(mLayoutManager);
        recyclerViews.setItemAnimator(new DefaultItemAnimator());
        recyclerViews.setAdapter(mAdapter);
        prepareMyOrderData();
    }

    private void prepareMyOrderData() {

        MyCancelOrders myOrder = new MyCancelOrders();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myCancelOrdersArrayList.add(myOrder);

        myOrder = new MyCancelOrders();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myCancelOrdersArrayList.add(myOrder);

        myOrder = new MyCancelOrders();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myCancelOrdersArrayList.add(myOrder);

        myOrder = new MyCancelOrders();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myCancelOrdersArrayList.add(myOrder);

        myOrder = new MyCancelOrders();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myCancelOrdersArrayList.add(myOrder);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
