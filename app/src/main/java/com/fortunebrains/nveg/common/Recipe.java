package com.fortunebrains.nveg.common;

import java.util.List;

/**
 * Created by sree on 1/3/2017.
 */

public class Recipe implements ParentListItem
{
    // a recipe contains several ingredients
    private List mIngredients;

    public Recipe(List ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public List getChildItemList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

}
