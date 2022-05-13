package com.example.cookncalc.recipes;

import java.util.ArrayList;
import java.util.List;

public class TotalAmountDTO {
    List<TotalAmountRow> resTable = new ArrayList<>();

    public TotalAmountDTO(List<Object[]> result) {
        for(Object[] object: result){
            resTable.add(new TotalAmountRow(object));
        }
    }

    public List<TotalAmountRow> getResTable() {
        return resTable;
    }

    public void setResTable(List<TotalAmountRow> resTable) {
        this.resTable = resTable;
    }
}
