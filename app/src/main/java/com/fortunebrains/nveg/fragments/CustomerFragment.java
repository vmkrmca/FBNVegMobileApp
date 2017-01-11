package com.fortunebrains.nveg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class CustomerFragment extends android.support.v4.app.Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer,null);

        setHasOptionsMenu(true);
        return view;
    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_map).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}
