package com.fortunebrains.nveg.activities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortunebrains.nveg.R;
import com.fortunebrains.nveg.adapters.FavDetailsAdapter;
import com.fortunebrains.nveg.common.FavDetails;
import com.fortunebrains.nveg.database.DBHelper;

import java.util.ArrayList;

/**
 * Created by sree on 1/11/2017.
 */
public class ShortListCategory extends AppCompatActivity implements View.OnClickListener
{
    DBHelper dbHelper = null;
    SQLiteDatabase db  =null;
    ArrayList<FavDetails> favDetailsArrayList;
    RecyclerView favRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlist);
        dbHelper = new DBHelper(getApplicationContext());
        favDetailsArrayList = new ArrayList<FavDetails>();
        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        hideKeyBoard(mCustomView);
        ImageView ivBack = (ImageView) mCustomView.findViewById(R.id.ivBack);
        TextView tvTitle = (TextView) mCustomView.findViewById(R.id.tvTitle);
        tvTitle.setText("   ShortList Items");
        ivBack.setOnClickListener(this);

        favRecyclerView = (RecyclerView) findViewById(R.id.favRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        favRecyclerView.setLayoutManager(mLayoutManager);
        favRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        dataFromDB();
        FavDetailsAdapter adapter = new FavDetailsAdapter(ShortListCategory.this,favDetailsArrayList);
        favRecyclerView.setAdapter(adapter);




    }

    private void dataFromDB() {


        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from addFavTbl",null);
        if (cursor!=null)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    FavDetails favDetails = new FavDetails();
                    favDetails.setItemName(cursor.getString(cursor.getColumnIndex("itemName")));
                    favDetails.setItemType(cursor.getString(cursor.getColumnIndex("itemType")));
                    favDetails.setItemCost(cursor.getString(cursor.getColumnIndex("itemCost")));
                    favDetails.setItemCount(cursor.getString(cursor.getColumnIndex("itemCount")));
                    favDetailsArrayList.add(favDetails);
                }while (cursor.moveToNext());
            }
        }
    }

    private void hideKeyBoard(View mCustomView) {

        if (mCustomView != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mCustomView.getWindowToken(), 0);
        }
    }


    @Override
    public void onClick(View v)
    {

        finish();
    }
}
