package com.fortunebrains.nveg.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.ShoppingActivity;
import com.fortunebrains.nveg.common.CartDetails;
import com.fortunebrains.nveg.common.FavDetails;
import com.fortunebrains.nveg.database.DBHelper;

import java.util.List;

/**
 * Created by sree on 1/16/2017.
 */
public class FavDetailsAdapter extends RecyclerView.Adapter<FavDetailsAdapter.MyViewHolder>
{

    private List<FavDetails> favDetailsList;
    Context mContext;

    public static int num = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        DBHelper dbHelper = null;
        public TextView tvItemName, tvItemType,tvAmount;
        ImageView ivCategoryImage, ivDelete;
        ElegantNumberButton button;

        public MyViewHolder(View view)
        {
            super(view);
            dbHelper = new DBHelper(mContext);
            tvItemName = (TextView) view.findViewById(R.id.tvItemName);
            tvItemType = (TextView) view.findViewById(R.id.tvItemType);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            button = (ElegantNumberButton) view.findViewById(R.id.enbNumber);
            ivCategoryImage = (ImageView) view.findViewById(R.id.ivCategoryImage);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);



        }
    }
    public FavDetailsAdapter(Context mCtx,List<FavDetails> favDetailsList) {

        this.mContext = mCtx;
        this.favDetailsList = favDetailsList;
    }
    @Override
    public FavDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_cart_row, parent, false);
        return new FavDetailsAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final FavDetailsAdapter.MyViewHolder holder, final int position) {
        FavDetails favDetails = favDetailsList.get(position);
        holder.tvItemName.setText(favDetails.getItemName());
        holder.tvAmount.setText(favDetails.getItemCost());
        holder.ivCategoryImage.setImageResource(R.mipmap.chicken);
        holder.button.setNumber(favDetails.getItemCount());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                adb.setTitle("Delete Cart Item");
                adb.setMessage("Do You want Delete this Item From Cart");
                adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        long id = holder.dbHelper.deleteCart(String.valueOf(position));

                        if (id!=-1)
                        {
                            mContext.startActivity(new Intent(mContext, ShoppingActivity.class));
                        }
                        else
                        {
                            Toast.makeText(mContext,"Not Deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return favDetailsList.size();
    }
}

