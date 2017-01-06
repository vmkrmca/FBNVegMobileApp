package com.fortunebrains.nveg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class FeedbackFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    EditText etEmail,etFMessage,etMobile;
    TextView tvSubmit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback,null);

        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etFMessage = (EditText) view.findViewById(R.id.etMessage);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        tvSubmit = (TextView) view.findViewById(R.id.tvSubmit);


        tvSubmit.setOnClickListener(this);

        setHasOptionsMenu(true);

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
    public void onClick(View v) {

        String email = etEmail.getText().toString();
        String message = etFMessage.getText().toString();
        String phone = etMobile.getText().toString();

    }
}
