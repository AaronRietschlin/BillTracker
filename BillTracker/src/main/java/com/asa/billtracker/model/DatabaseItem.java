package com.asa.billtracker.model;

import android.content.ContentValues;

/**
 * Interface for anything that goes in the database on the phone.
 */
public interface DatabaseItem {

    /**
     * Returns the item in a key-value {@link android.content.ContentValues}.
     *
     * @return
     */
    public ContentValues toContentValues();

}
