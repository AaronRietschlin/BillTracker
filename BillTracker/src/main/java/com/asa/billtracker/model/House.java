package com.asa.billtracker.model;


import android.content.ContentValues;

import com.asa.billtracker.AppData;
import com.asa.billtracker.db.BillContract;
import com.parse.ParseObject;

/**
 * Created by Aaron on 11/16/13.
 */
public class House extends BaseItem implements DatabaseItem, ParseItem {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public ContentValues toContentValues() {
        ContentValues cv = getContentValues();
        // TODO - do this!
        return cv;
    }

    @Override
    public ParseObject toParseObject() {
        ParseObject obj = new ParseObject(AppData.ParseTables.HOUSE);
        obj.put(BillContract.HouseEntry.ADDRESS, address);
        obj.put(BillContract.HouseEntry.NAME, name);
        return obj;
    }
}
