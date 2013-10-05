package com.asa.billtracker.model;

import com.asa.billtracker.AppData;
import com.asa.billtracker.BillApplication;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Aaron on 9/29/13.
 */
public class Bill {

    private String objectId;
    private double amount;
    private String category;
    private String image;
    private long createdAt;
    private long updatedAt;

    // TODO - Need to update this for the correct model of the parse db

    public ParseObject toParseObject() {
        ParseObject o = new ParseObject(AppData.ParseTables.BILLS);
        ParseObject categoryO = new ParseObject(AppData.ParseTables.CATEGORY);
        categoryO.put("name", category);
        o.put("category", categoryO);
        o.put("amount", amount);
        o.put("owner", ParseUser.getCurrentUser());
        o.put("debug", BillApplication.DEBUG);
        return o;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double number) {
        this.amount = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
