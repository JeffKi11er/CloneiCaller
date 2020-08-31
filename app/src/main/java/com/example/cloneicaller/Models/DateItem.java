package com.example.cloneicaller.Models;

import com.example.cloneicaller.ListItem;


public class DateItem implements ListItem {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getType() {
        return TYPE_DATE;
    }
}
