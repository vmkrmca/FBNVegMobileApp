package com.fortunebrains.nveg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fortunebrains.nveg.R;

/**
 * Created by sree on 1/16/2017.
 */

public class NavigationAdapter extends BaseAdapter
{

    Context context;

    public NavigationAdapter(Context context)
    {
        this.context = context;

    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).inflate(R.layout.main_row,null);


        LinearLayout llOffers = (LinearLayout) convertView.findViewById(R.id.llOffers);
        final LinearLayout llSOffers = (LinearLayout) convertView.findViewById(R.id.llSOffers);
        LinearLayout llHotDeals = (LinearLayout) convertView.findViewById(R.id.llHotDeals);
        final LinearLayout llSHoteDeals = (LinearLayout) convertView.findViewById(R.id.llSHoteDeals);

        final TextView tvOPlus = (TextView) convertView.findViewById(R.id.tvOPlus);
        final TextView tvOMinus = (TextView) convertView.findViewById(R.id.tvOMinus);
        final TextView tvHPlus = (TextView) convertView.findViewById(R.id.tvHPlus);
        final TextView tvHMinus = (TextView) convertView.findViewById(R.id.tvHMinus);




        llOffers.setOnClickListener(new View.OnClickListener()
        {
            boolean count = false;

            @Override
            public void onClick(View v)
            {
                if (count)
                {
                    count = false;
                    tvOPlus.setVisibility(View.VISIBLE);
                    tvOMinus.setVisibility(View.GONE);
                    llSOffers.setVisibility(View.GONE);
                }
                else
                {
                    count = true;
                    Toast.makeText(context,""+count,Toast.LENGTH_SHORT).show();
                    tvOPlus.setVisibility(View.GONE);
                    tvOMinus.setVisibility(View.VISIBLE);
                    llSOffers.setVisibility(View.VISIBLE);

                }

            }
        });

        llHotDeals.setOnClickListener(new View.OnClickListener()
        {
            boolean count = false;
            @Override
            public void onClick(View v)
            {
                if (count)
                {
                    count = false;
                    tvHPlus.setVisibility(View.VISIBLE);
                    tvHMinus.setVisibility(View.GONE);
                    llSHoteDeals.setVisibility(View.GONE);
                }
                else
                {
                    count = true;
                    tvHPlus.setVisibility(View.GONE);
                    tvHMinus.setVisibility(View.VISIBLE);
                    llSHoteDeals.setVisibility(View.VISIBLE);

                }

            }
        });


        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


            }
        });


        return convertView;
    }
}
