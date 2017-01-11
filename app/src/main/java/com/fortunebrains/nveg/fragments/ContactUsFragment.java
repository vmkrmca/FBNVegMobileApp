package com.fortunebrains.nveg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fortunebrains.nveg.R;

/**
 * Created by sree on 12/21/2016.
 */
public class ContactUsFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    EditText etName, etMobile, etEmail, etSubject, etMessage;
    TextView tvSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_us, null);

        etName = (EditText) view.findViewById(R.id.etName);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etSubject = (EditText) view.findViewById(R.id.etSubject);
        etMessage = (EditText) view.findViewById(R.id.etMessage);

        tvSubmit = (TextView) view.findViewById(R.id.tvSubmit);
        tvSubmit.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

        String email = etEmail.getText().toString();
        String mobile = etMobile.getText().toString();
        String subject = etSubject.getText().toString();
        String message = etMessage.getText().toString();
        String name = etName.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://api.androidhive.info/contacts/", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.i("Response ::",response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.i("Response Error ::",""+error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}
