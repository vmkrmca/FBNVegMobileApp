package com.fortunebrains.nveg.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.CartActivity;
import com.fortunebrains.nveg.activities.DetailedCategoryActivity;
import com.fortunebrains.nveg.activities.RatingActivity;
import com.fortunebrains.nveg.common.CategoryData;
import com.fortunebrains.nveg.common.MyCancelOrders;
import com.fortunebrains.nveg.database.DBHelper;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private List<CategoryData> categoryDataList;
    Context mContext;

    public static int num = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        DBHelper dbHelper = null;
        public TextView tvCategoryName, tvAmount;
        ImageView ivCategoryImage, ivFav, ivCart, ivRate;
        ElegantNumberButton button;
        Spinner spItemType;

        public MyViewHolder(View view)
        {
            super(view);
            dbHelper = new DBHelper(mContext);
            tvCategoryName = (TextView) view.findViewById(R.id.tvItemName);
            spItemType = (Spinner) view.findViewById(R.id.spItemType);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
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
        holder.tvAmount.setText(categoryData.getCategoryAmt());

        Glide.with(mContext).load(categoryDataList.get(position).getCategoryImage()).asBitmap().centerCrop().override(75, 75).into(holder.ivCategoryImage);
        holder.ivRate.setImageResource(R.mipmap.ic_starrate);
        holder.ivFav.setImageResource(R.mipmap.ic_favorite);
        holder.ivCart.setImageResource(R.mipmap.ic_shoppingcart);
        holder.button.setOnClickListener(new ElegantNumberButton.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String number = holder.button.getNumber();
            }
        });

        holder.button.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {


               String amount = categoryDataList.get(position).getCategoryAmt();
               String cName = categoryDataList.get(position).getCategoryName();

               num = Integer.parseInt(amount);
               if (newValue==0)
                {
                    holder.tvAmount.setText(""+num);
                }
                else
                {
                    int balAmount = newValue * num;
                    holder.tvAmount.setText(""+balAmount);

                    long id = holder.dbHelper.addCartItems(String.valueOf(position),cName,String.valueOf(balAmount),String.valueOf(newValue));
                    if (id!=-1)
                    {
                        Toast.makeText(mContext,"Item Added",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        long Updateid = holder.dbHelper.updateCartItem(String.valueOf(position),cName,String.valueOf(balAmount),String.valueOf(newValue));
                        Toast.makeText(mContext,"Item Updated",Toast.LENGTH_SHORT).show();
                    }
                }

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

        holder.ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContext.startActivity(new Intent(mContext, CartActivity.class));
            }
        });

        holder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String cName = categoryDataList.get(position).getCategoryName();
                String cAmount = categoryDataList.get(position).getCategoryAmt();
                String cLoc = categoryDataList.get(position).getLocation();
                String cType = categoryDataList.get(position).getCategoryType();
                String number = holder.button.getNumber();


                long id = holder.dbHelper.addFavItems(String.valueOf(position),cName,cType,"HYD",cAmount,number);
                if (id!=-1)
                {
                    Toast.makeText(mContext,"Item is Added as ShortList",Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.ivRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(mContext,RatingActivity.class);
                mContext.startActivity(i2);
            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }
}