package com.fortunebrains.nveg.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.activities.CategoryActivity;
import com.fortunebrains.nveg.activities.ShoppingActivity;
import com.fortunebrains.nveg.common.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    private Context context;

    public CategoryAdapter(Context context,ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int i)
    {

        Picasso.with(context).load(categoryArrayList.get(i).getCategoryImage()).resize(240, 200).into(viewHolder.img_android);


        Glide.with(context).load(categoryArrayList.get(i).getCategoryImage()).asBitmap().centerCrop().override(150, 150).into(viewHolder.img_android);
        viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,CategoryActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            img_android = (ImageView) view.findViewById(R.id.img_android);
        }
    }

}