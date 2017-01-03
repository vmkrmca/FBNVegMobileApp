package com.fortunebrains.nveg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.MyOrderAdapter;
import com.fortunebrains.nveg.common.MyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sree on 12/21/2016.
 */
public class MyOrdersFragment extends android.support.v4.app.Fragment
{
    private List<MyOrder> myOrderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyOrderAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myorders,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new MyOrderAdapter(myOrderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMyOrderData();
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_searchOne).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void prepareMyOrderData() {

        MyOrder myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderList.add(myOrder);

        myOrder = new MyOrder();
        myOrder.setOrderID("1001");
        myOrder.setOrderDateTime("12/12/2016 12:30 PM");
        myOrder.setDeliveryDateTime("12/12/2016 1:30 PM");
        myOrder.setOrderAmount("250/-");
        myOrder.setOrderAction("Success");
        myOrderList.add(myOrder);

        mAdapter.notifyDataSetChanged();

    }
}
