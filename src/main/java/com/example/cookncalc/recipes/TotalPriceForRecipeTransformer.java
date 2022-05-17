package com.example.cookncalc.recipes;

import java.util.ArrayList;
import java.util.List;

public class TotalPriceForRecipeTransformer {
    List<TotalPriceForRecipe> resTable = new ArrayList<>();

    public TotalPriceForRecipeTransformer(List<Object[]> result) {
        for(Object[] object: result){
            resTable.add(new TotalPriceForRecipe(object));
        }
    }

    public List<TotalPriceForRecipe> getResTable() {
        return resTable;
    }

    public void setResTable(List<TotalPriceForRecipe> resTable) {
        this.resTable = resTable;
    }
}
