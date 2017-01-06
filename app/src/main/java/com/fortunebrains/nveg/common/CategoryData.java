package com.fortunebrains.nveg.common;

/**
 * Created by sree on 1/6/2017.
 */

public class CategoryData
{
    int CategoryImage;
    String categoryName;
    String categoryType;
    String categoryAmt;

    public String getCategoryAmt() {
        return categoryAmt;
    }

    public void setCategoryAmt(String categoryAmt) {
        this.categoryAmt = categoryAmt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    String location;

    public int getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        CategoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
