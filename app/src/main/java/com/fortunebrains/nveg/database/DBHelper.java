package com.fortunebrains.nveg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sree on 1/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper
{
    SQLiteDatabase db = null;

    public DBHelper(Context context)
    {
        super(context,"NVegDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table addCartTbl(itemPos Integer Primary key,itemName Text,itemCost Text,itemCount Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long addCartItems(String itemPos,String itemName,String itemCost,String itemCount)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues  =new ContentValues();
        contentValues.put("itemPos",itemPos);
        contentValues.put("itemName",itemName);
        contentValues.put("itemCost",itemCost);
        contentValues.put("itemCount",itemCount);
        long id = db.insert("addCartTbl",null,contentValues);
        return id;
    }


    public  long updateCartItem(String pos,String itemName,String itemCost,String itemCount)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues  =new ContentValues();
        contentValues.put("itemName",itemName);
        contentValues.put("itemCost",itemCost);
        contentValues.put("itemCount",itemCount);

        long id = db.update("addCartTbl",contentValues,"itemPos=?",new String[]{pos});
        return id;
    }

    public long deleteCart(String pos)
    {
        db = this.getWritableDatabase();
        long id = db.delete("addCartTbl","itemPos=?",new String[]{pos});

        return id;

    }
}
