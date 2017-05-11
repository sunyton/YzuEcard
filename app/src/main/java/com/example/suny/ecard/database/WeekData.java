package com.example.suny.ecard.database;

import org.litepal.crud.DataSupport;

/**
 * Created by suny on 2017/4/27.
 */

public class WeekData extends DataSupport{

    private String data;
    private String cost;
    private String location;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
