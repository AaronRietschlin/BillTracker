package com.asa.billtracker.model;

import android.content.ContentValues;
import android.text.TextUtils;

/**
 * Base class for items that will be used with parse.
 */
public class BaseItem {

    private String objectId;
    private long createdAt;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    private long updatedAt;

    /**
     * Places the base parse items into the content values.
     * @return
     */
    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        if (!TextUtils.isEmpty(objectId)) {
            cv.put("objectId", objectId);
        }
        if (createdAt > 0) {
            cv.put("createdAt", createdAt);
        }
        if (updatedAt > 0) {
            cv.put("updatedAt", updatedAt);
        }
        return cv;
    }

}
