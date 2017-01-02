package com.fortunebrains.nveg.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.common.MyOrder;

import java.util.List;

/**
 * Created by sree on 12/22/2016.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {

    private List<MyOrder> myOrderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvOrderID,tvOrderDTime,tvDelveryOrder,tvOrderPrice,tvAction;

        public MyViewHolder(View view) {
            super(view);
            tvOrderID = (TextView) view.findViewById(R.id.tvOrderID);
            tvOrderDTime = (TextView) view.findViewById(R.id.tvOrderDTime);
            tvDelveryOrder = (TextView) view.findViewById(R.id.tvDelveryOrder);
            tvOrderPrice = (TextView) view.findViewById(R.id.tvOrderPrice);
            tvAction = (TextView) view.findViewById(R.id.tvAction);

        }
    }


    public MyOrderAdapter(List<MyOrder> myOrderList) {
        this.myOrderList = myOrderList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myorder_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyOrder myOrder = myOrderList.get(position);
        holder.tvOrderID.setText(myOrder.getOrderID());
        holder.tvOrderDTime.setText(myOrder.getOrderDateTime());
        holder.tvDelveryOrder.setText(myOrder.getDeliveryDateTime());
        holder.tvOrderDTime.setText(myOrder.getOrderAmount());
        holder.tvAction.setText(myOrder.getOrderAction());

    }

    @Override
    public int getItemCount() {
        return myOrderList.size();
    }
}
