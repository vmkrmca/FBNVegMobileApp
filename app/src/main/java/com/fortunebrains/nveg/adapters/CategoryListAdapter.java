package com.fortunebrains.nveg.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.DetailedCategoryActivity;
import com.fortunebrains.nveg.common.CategoryData;
import com.fortunebrains.nveg.common.MyCancelOrders;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private List<CategoryData> categoryDataList;
    Context mContext;
    int num;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvCategoryName, tvCategoryLocation, tvCategoryType,tvAmount,tvItemLocation;
        ImageView ivCategoryImage, ivFav, ivCart, ivRate;
        ElegantNumberButton button;

        public MyViewHolder(View view)
        {
            super(view);
            tvCategoryName = (TextView) view.findViewById(R.id.tvItemName);
            tvCategoryLocation = (TextView) view.findViewById(R.id.tvItemLocation);
            tvCategoryType = (TextView) view.findViewById(R.id.tvItemType);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            tvItemLocation = (TextView) view.findViewById(R.id.tvItemLocation);
            button = (ElegantNumberButton) view.findViewById(R.id.enbNumber);
            ivCategoryImage = (ImageView) view.findViewById(R.id.ivCategoryImage);
            ivFav = (ImageView) view.findViewById(R.id.ivFav);
            ivCart = (ImageView) view.findViewById(R.id.ivCart);
            ivRate = (ImageView) view.findViewById(R.id.ivRating);



        }
    }


    public CategoryListAdapter(Context mCtx,List<CategoryData> categoryDataList) {

        this.mContext = mCtx;
        this.categoryDataList = categoryDataList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycategorydata_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CategoryData categoryData = categoryDataList.get(position);
        holder.tvCategoryName.setText(categoryData.getCategoryName());
        holder.tvCategoryLocation.setText(categoryData.getLocation());
        holder.tvCategoryType.setText(categoryData.getCategoryType());
        holder.tvItemLocation.setText(categoryData.getLocation());
        holder.tvAmount.setText(categoryData.getCategoryAmt());

        holder.ivCategoryImage.setBackgroundResource(categoryData.getCategoryImage());
        holder.ivRate.setImageResource(R.mipmap.ic_starrate);
        holder.ivFav.setImageResource(R.mipmap.ic_favorite);
        holder.ivCart.setImageResource(R.mipmap.ic_shoppingcart);
        holder.button.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

        holder.button.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.i("Data", ""+oldValue+""+newValue);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext,DetailedCategoryActivity.class);
                i.putExtra("Location",categoryDataList.get(position).getLocation());
                mContext.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }
}