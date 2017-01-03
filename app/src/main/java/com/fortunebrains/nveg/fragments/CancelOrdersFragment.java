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
import com.fortunebrains.nveg.adapters.MyCancelAdapter;
import com.fortunebrains.nveg.common.MyCancelOrders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sree on 12/21/2016.
 */
public class CancelOrdersFragment extends android.support.v4.app.Fragment
{
    private List<MyCancelOrders> myCancelOrdersArrayList = new ArrayList<>();
    private RecyclerView recyclerViews;
    private MyCancelAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cancelorder,null);
        recyclerViews = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new MyCancelAdapter(myCancelOrdersArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViews.setLayoutManager(mLayoutManager);
        recyclerViews.setItemAnimator(new DefaultItemAnimator());
        recyclerViews.setAdapter(mAdapter);
        setHasOptionsMenu(true);
        prepareMyOrderData();
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
}
